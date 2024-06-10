/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import de.latlon.xplan.commons.util.UnsupportedContentTypeException;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.server.service.ManagerPlanArchiveManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.ResourceBundle;

import static de.latlon.xplan.commons.util.ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml;
import static java.lang.Double.doubleToLongBits;
import static java.lang.Long.toHexString;
import static java.lang.Math.random;
import static org.apache.commons.io.IOUtils.write;
import static org.springframework.http.MediaType.TEXT_HTML_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * REST-Interface for plan management (only for file upload).
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
	private XPlanManager manager;

	List<XPlan> getPlansFromManager(@Context HttpServletResponse response) throws Exception {
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

	@RequestMapping(value = "/plan/{planId}", method = GET)
	@ResponseBody
	// @formatter:off
    public void getPlan(@PathVariable String planId, @Context HttpServletRequest request,
                         @Context HttpServletResponse response ) {
        // @formatter:on
		response.addHeader("Expires", "-1");
		LOG.info("Retrieve plan " + planId);
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

	@RequestMapping(value = "/plan", method = POST, produces = TEXT_HTML_VALUE)
	@ResponseBody
	// @formatter:off
	@SuppressFBWarnings(value = "PREDICTABLE_RANDOM")
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

	private void exportPlan(String id, ByteArrayOutputStream exportOutputStream) {
		try {
			manager.export(id, exportOutputStream);
		}
		catch (Exception e) {
			String message = BUNDLE.getString("getPlansFailed") + ": " + e.getMessage();
			LOG.warn(message);
		}
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String handleAllExceptions(Exception e) {
		LOG.error("handleAllExceptions", e);
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
		LOG.info("UnsupportedContentTypeException", e.getMessage());
		return e.getMessage();
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

}
