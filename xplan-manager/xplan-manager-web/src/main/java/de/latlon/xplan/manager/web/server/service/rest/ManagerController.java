/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.web.server.service.rest;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.util.UnsupportedContentTypeException;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.web.server.service.ManagerPlanArchiveManager;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.Rechtsstand;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.core.gwt.commons.shared.InvalidParameterException;
import org.deegree.commons.utils.Pair;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.latlon.xplan.commons.util.ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml;
import static de.latlon.xplan.commons.util.TextPatternConstants.INTERNALID_PATTERN;
import static java.lang.Double.doubleToLongBits;
import static java.lang.Long.toHexString;
import static java.lang.Math.random;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.apache.commons.io.IOUtils.write;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * REST-Interface for plan management.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

	private static final ResourceBundle BUNDLE = ResourceBundle
		.getBundle("de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages");

	private final ManagerPlanArchiveManager archiveManager = new ManagerPlanArchiveManager();

	@Autowired
	private AuthorizationManager authorizationManager;

	@Autowired
	private XPlanManager manager;

	@Autowired
	private InternalIdRetriever internalIdRetriever;

	@RequestMapping(value = "/plans", method = GET, produces = APPLICATION_JSON)
	@ResponseBody
	public List<XPlan> getPlansFromManager(@Context HttpServletResponse response) throws Exception {
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve all plans.");
		List<XPlan> xPlanList;
		try {
			xPlanList = manager.list();
		}
		catch (Exception e) {
			LOG.error(BUNDLE.getString("getPlansFailed") + ": " + e.getMessage());
			throw e;
		}
		return xPlanList;
	}

	@RequestMapping(value = "/local/plan", method = GET)
	@ResponseBody
	// @formatter:off
    public ResponseEntity<XPlan> getPlanFromLocal( @Context HttpServletRequest request, @Context HttpServletResponse response ) {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve plan from session.");
		HttpSession session = request.getSession();
		XPlan xPlan = archiveManager.retrievePlanFromSession(session);
		if (xPlan == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(xPlan, HttpStatus.OK);
	}

	@RequestMapping(value = "/plan/{planId}", method = GET)
	@ResponseBody
	// @formatter:off
    public void getPlan( @PathVariable String planId, @Context HttpServletRequest request,
                         @Context HttpServletResponse response ) {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve all plans.");
		try {
			XPlan requestedPlan = retrieveRequestedPlan(response, planId);
			if (requestedPlan != null) {
				try (ByteArrayOutputStream exportOutputStream = new ByteArrayOutputStream()) {
					exportPlan(planId, exportOutputStream);
					populateResponseAndWriteOutput(response, requestedPlan, exportOutputStream);
				}
			}
		}
		catch (Exception e) {
			String message = BUNDLE.getString("downloadPlanFailed") + ": " + e.getMessage();
			LOG.info(message);
		}
	}

	@RequestMapping(value = "/edit/plan/{planId}", method = GET)
	@ResponseBody
	// @formatter:off
    public XPlanToEdit getPlanToEdit( @PathVariable int planId, @Context HttpServletRequest request,
                                      @Context HttpServletResponse response )
                                                      throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve plan with id {} to edit.", planId);
		try {
			XPlan plan = manager.getXPlanById(planId);
			if (plan != null)
				return manager.getXPlanToEdit(plan);
			else {
				String message = BUNDLE.getString("getPlanToEditAbortedAsNoPlanMatchedId");
				LOG.info(message);
				throw new IllegalArgumentException(message);
			}
		}
		catch (Exception e) {
			LOG.info(BUNDLE.getString("getPlanToEditFailed") + ": " + e.getMessage());
			throw e;
		}
	}

	@RequestMapping(value = "/edit/plan/{planId}", method = POST)
	@ResponseBody
	// @formatter:off
    public void editPlan( @PathVariable int planId,
						  @RequestBody @Valid XPlanToEdit xPlanToEdit,
                          @RequestParam(value = "updateRasterConfig", required = false) boolean updateRasterConfig,
                          @Context HttpServletRequest request, @Context HttpServletResponse response )
                                          throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Try to edit plan with id {}.", planId);
		try {
			XPlan plan = manager.getXPlanById(planId);
			if (plan != null) {
				HttpSession session = request.getSession();
				List<File> uploadedArtefacts = archiveManager.retrieveUploadedArtefacts(session);
				manager.editPlan(plan, xPlanToEdit, updateRasterConfig, uploadedArtefacts);
			}
			else {
				String message = BUNDLE.getString("editPlanAbortedAsNoPlanMatchedId");
				LOG.info(message);
				throw new IllegalArgumentException(message);
			}
		}
		catch (Exception e) {
			String message = BUNDLE.getString("editPlanFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "edit/raster/{id}", method = POST)
	@ResponseBody
	// @formatter:off
    public List<RasterEvaluationResult> evaluateRaster( @PathVariable String id,
                                                        @RequestBody XPlanToEdit xPlanToEdit,
                                                        @Context HttpServletRequest request,
                                                        @Context HttpServletResponse response )
                                                                        throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Evaluate uploaded raster of plan with id {}.", id);
		try {
			HttpSession session = request.getSession();
			List<File> uploadedArtefacts = archiveManager.retrieveUploadedArtefacts(session);
			return manager.evaluateRasterdata(xPlanToEdit, uploadedArtefacts);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("evaluationRasterFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/edit/plan/artefact", method = POST, produces = TEXT_HTML_VALUE)
	@ResponseBody
	// @formatter:off
    public void uploadPlanArtefact( @RequestParam("referenceArtefact") MultipartFile referenceArtefact,
                                    @RequestParam(value="geoReferenceArtefact", required = false) MultipartFile geoReferenceArtefact,
                                    @Context HttpServletRequest request, @Context HttpServletResponse response)
                                                    throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		uploadArtefact(referenceArtefact, request, response);
		uploadArtefact(geoReferenceArtefact, request, response);
	}

	@RequestMapping(value = "/plan/{planId}", method = DELETE)
	@ResponseBody
	// @formatter:off
    public boolean removePlanFromManager( @PathVariable String planId )
                    throws Exception {
        // @formatter:on
		LOG.info("Try to remove plan with id {}.", planId);
		if (planId == null)
			return false;
		try {
			int id = Integer.parseInt(planId);
			XPlan plan = manager.getXPlanById(id);
			if (plan != null) {
				manager.delete(plan);
				return true;
			}
		}
		catch (Exception e) {
			String message = BUNDLE.getString("deleteFailed") + ": " + e.getMessage();
			LOG.info(message);
			throw e;
		}
		return false;
	}

	@RequestMapping(value = "/local/plan/{planId}", method = DELETE)
	@ResponseBody
	// @formatter:off
    public boolean removePlanFromFileSystem( @PathVariable String planId, @Context HttpServletRequest request ) {
        // @formatter:on
		LOG.info("Try to remove local plan.");
		HttpSession session = request.getSession();
		if (planId == null)
			return false;
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		if (plan != null && planId.equals(plan.getId())) {
			archiveManager.clearPlanInSession(session);
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/plan", method = POST, produces = TEXT_HTML_VALUE)
	@ResponseBody
	// @formatter:off
    public void uploadPlan( @RequestParam("planZipFile" ) MultipartFile file, HttpServletRequest request,
                            HttpServletResponse response) throws IOException, UnsupportedContentTypeException {
        // @formatter:on
		LOG.info("Try to upload plan.");
		try {
			if (file != null && !file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String contentType = file.getContentType();
				long size = file.getSize();
				HttpSession session = request.getSession(true);
				XPlan plan = new XPlan(fileName, toHexString(doubleToLongBits(random())), contentType);
				uploadZipFile(file, plan);
				archiveManager.savePlanInSession(session, plan);
				populateResponse(response, size, fileName);
			}
		}
		catch (Exception e) {
			String message = BUNDLE.getString("loadFailed") + ": " + e.getMessage();
			LOG.info(message);
			throw e;
		}
	}

	@RequestMapping(value = "/plan/{planId}", method = PUT)
	@ResponseBody
	// @formatter:off
    public boolean importPlan( @PathVariable String planId,
                               @RequestParam(value = "internalId", required = false ) String internalId,
                               @RequestParam(value = "defaultCrs", required = false) String defaultCrs,
                               @RequestParam(value = "makeRasterConfig", required = false) boolean makeRasterConfig,
                               @RequestParam(value = "planStatus", required = false) PlanStatus planStatus,
                               @RequestParam(value = "startDateTime", required = false) Date startDateTime,
                               @RequestParam(value = "endDateTime", required = false) Date endDateTime,
                               @Context HttpServletRequest request, @Context HttpServletResponse response)
                                               throws Exception {
        // @formatter:on
		checkInternalId(internalId);
		response.addHeader("Expires", "-1");
		LOG.info("Try to import plan with id {}", planId);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		if (planId != null && plan != null) {
			LOG.info("Found local plan to import.");
			archiveManager.savePlanInSession(session, plan);
			try {
				String fileToBeImported = retrieveFileToBeImported(plan);
				XPlanArchive archive = manager.analyzeArchive(fileToBeImported);
				ICRS crs = null;
				if (defaultCrs != null)
					crs = CRSManager.getCRSRef(defaultCrs);
				AdditionalPlanData xPlanMetadata = new AdditionalPlanData(planStatus, startDateTime, endDateTime);
				manager.importPlan(archive, crs, false, makeRasterConfig, internalId, xPlanMetadata);
			}
			catch (Exception e) {
				String message = BUNDLE.getString("loadFailed") + ": " + e.getMessage();
				LOG.error(message, e);
				throw e;
			}
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/plan/{planId}/bereiche", method = GET)
	@ResponseBody
	public List<Bereich> retrieveBereiche(@PathVariable String planId, @Context HttpServletRequest request,
			@Context HttpServletResponse response) throws Exception {
		return manager.getBereicheOfPlanWithId(planId);
	}

	@RequestMapping(value = "/internalid/{id}", method = GET)
	@ResponseBody
	// @formatter:off
    public Map<String, String> retrieveMatchingInternalIds( @PathVariable String id,
															@Context HttpServletRequest request,
															@Context HttpServletResponse response )
                                                                            throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve internal id of plan with id {}.", id);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			String planName = manager.retrievePlanName(fileToBeImported);
			Map<String, String> matchingInternalIds = internalIdRetriever.getMatchingInternalIds(planName);
			if (!matchingInternalIds.isEmpty())
				return matchingInternalIds;
			if (authorizationManager.isSuperUser())
				return internalIdRetriever.getAllInternalIds();
			return Collections.emptyMap();
		}
		catch (Exception e) {
			String message = BUNDLE.getString("retrieveMatchingInternalIdsFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/crs/{id}", method = GET)
	@ResponseBody
	@Deprecated
	// @formatter:off
    public boolean isCrsSet( @PathVariable String id,@Context HttpServletRequest request,  @Context HttpServletResponse response )
                    throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve crs of plan with id {}.", id);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.isCrsSet(fileToBeImported);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("checkingIfCrsIsSetFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/raster/{id}", method = GET)
	@ResponseBody
	// @formatter:off
    public List<RasterEvaluationResult> evaluateRaster( @PathVariable String id, @Context HttpServletRequest request, @Context HttpServletResponse response )
                    throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Evaluate raster of with id {}.", id);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.evaluateRasterdata(fileToBeImported);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("evaluationRasterFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/plannamestatus/{id}/{status}", method = GET)
	@ResponseBody
	public List<PlanNameWithStatusResult> evaluatePlanNameAndStatus(@PathVariable String id,
			@PathVariable String status, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws Exception {
		response.addHeader("Expires", "-1");
		LOG.info("Evaluate name of plan with id {}.", id);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			if ("null".equals(status))
				status = null;
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.evaluatePlanNameAndStatus(fileToBeImported, status);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("evaluatePlanNameAndStatus") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/legislationstatus/{id}", method = GET)
	@ResponseBody
	// @formatter:off
    public RechtsstandAndPlanStatus determineLegislationStatus( @PathVariable String id,
																@Context HttpServletRequest request, @Context HttpServletResponse response )
                                                                         throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Evaluate legislation status of plan with id {}.", id);
		HttpSession session = request.getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			Pair<Rechtsstand, PlanStatus> rechtsstandPlanStatusPair = manager.determineRechtsstand(fileToBeImported);
			return new RechtsstandAndPlanStatus(rechtsstandPlanStatusPair.first, rechtsstandPlanStatusPair.second);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("determinationLegislationStatusFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
	}

	@RequestMapping(value = "/plu/plan/{planId}", method = GET)
	@ResponseBody
	// @formatter:off
    public boolean publishPlu( @PathVariable String planId,
                            @Context HttpServletResponse response )
                            throws Exception {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Publish plan with id {} as INSPIRE dataset.", planId);
		if (planId == null)
			return false;
		try {
			int id = Integer.parseInt(planId);
			XPlan plan = manager.getXPlanById(id);
			if (plan != null) {
				manager.publishPlu(plan);
				return true;
			}
		}
		catch (Exception e) {
			String message = BUNDLE.getString("publishingPluFailed") + ": " + e.getMessage();
			LOG.error(message, e);
			throw e;
		}
		return false;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleAllExceptions(Exception e) {
		return e.getMessage();
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ResponseBody
	public String handleAccessDeniedExceptions(AccessDeniedException e) {
		return e.getMessage();
	}

	@ExceptionHandler(UnsupportedContentTypeException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public String handleUnsupportedContentTypeExceptions(UnsupportedContentTypeException e) {
		return e.getMessage();
	}

	private void exportPlan(String id, ByteArrayOutputStream exportOutputStream) {
		try {
			manager.export(id, exportOutputStream);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("getPlansFailed") + ": " + e.getMessage();
			LOG.warn(message);
		}
	}

	private void uploadArtefact(MultipartFile artefact, HttpServletRequest request, HttpServletResponse response)
			throws IOException, UnsupportedContentTypeException {
		if (artefact != null && !artefact.isEmpty()) {
			String fileName = artefact.getOriginalFilename();
			LOG.info("Add artefact {}.", fileName);
			HttpSession session = request.getSession(true);
			archiveManager.saveArtefactInFilesystem(session, fileName, artefact.getBytes());
			populateArtefactUploadResponse(response, fileName);
		}
	}

	private String retrieveFileToBeImported(XPlan xPlan) {
		String fileName = archiveManager.determineFileNameAndFolder(xPlan);
		return archiveManager.getUploadFolder() + "/" + fileName;
	}

	private void populateResponseAndWriteOutput(HttpServletResponse response, XPlan requestedPlan,
			ByteArrayOutputStream exportOutputStream) throws IOException {
		response.setBufferSize(32768);
		response.addHeader("Content-Disposition", "attachment; filename=\"" + requestedPlan.getName() + ".zip" + "\"");
		response.setContentType("application/zip");
		response.setContentLength(exportOutputStream.size());
		OutputStream out = response.getOutputStream();
		out.write(exportOutputStream.toByteArray());
		out.flush();
		out.close();
	}

	private XPlan retrieveRequestedPlan(HttpServletResponse response, String id) throws Exception {
		XPlan requestedPlan = null;
		List<XPlan> planList = getPlansFromManager(response);
		for (XPlan plan : planList) {
			if (plan.getId().equals(id)) {
				requestedPlan = plan;
			}
		}
		if (requestedPlan == null) {
			response.sendError(666, BUNDLE.getString("missingFileName"));
			return null;
		}
		return requestedPlan;
	}

	private XPlan createAndSavePlan(HttpSession session, String contentType, String fileName) {
		XPlan plan = new XPlan(fileName, toHexString(doubleToLongBits(random())), contentType);
		archiveManager.savePlanInSession(session, plan);
		return plan;
	}

	private void populateResponse(HttpServletResponse response, long fileSize, String fileName) throws IOException {
		String message = BUNDLE.getString("loadedPlan");
		message = message.replace("{0}", fileName).replace("{1}", "" + fileSize);
		populateResponse(response, message);
	}

	private void populateArtefactUploadResponse(HttpServletResponse response, String fileName) throws IOException {
		String message = String.format(BUNDLE.getString("loadedArtfact"), fileName);
		populateResponse(response, message);
	}

	private void populateResponse(HttpServletResponse response, String message) throws IOException {
		response.addHeader("Content-Type", TEXT_HTML_VALUE);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().println("<html><body>" + message + "</body></html>");
		response.flushBuffer();
	}

	private void uploadZipFile(MultipartFile file, XPlan plan) throws IOException, UnsupportedContentTypeException {
		File localFile = archiveManager.readArchiveFromFilesystem(plan);
		localFile.getParentFile().mkdir();
		localFile.createNewFile();
		try (FileOutputStream localOutput = new FileOutputStream(localFile)) {
			write(file.getBytes(), localOutput);
		}
		checkContentTypesOfXPlanArchiveOrGml(localFile.toPath());
	}

	private void checkInternalId(String internalIdToCheck) throws InvalidParameterException {
		if (internalIdToCheck == null)
			return;
		Pattern pattern = Pattern.compile(INTERNALID_PATTERN);
		Matcher matcher = pattern.matcher(internalIdToCheck);
		if (!matcher.matches()) {
			throw new InvalidParameterException("InternalId does not match expected patter " + INTERNALID_PATTERN
					+ " but was " + internalIdToCheck);
		}
	}

}
