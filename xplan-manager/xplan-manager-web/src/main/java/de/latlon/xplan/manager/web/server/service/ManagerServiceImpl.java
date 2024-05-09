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
package de.latlon.xplan.manager.web.server.service;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.web.client.service.ManagerService;
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
import org.apache.commons.lang3.StringUtils;
import org.deegree.commons.utils.Pair;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.latlon.xplan.commons.util.TextPatternConstants.INTERNALID_PATTERN;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * REST-Interface for plan management.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerServiceImpl extends XsrfProtectedServiceServlet implements ManagerService {

	private static final Logger LOG = LoggerFactory.getLogger(ManagerServiceImpl.class);

	private static final ResourceBundle BUNDLE = ResourceBundle
		.getBundle("de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages");

	private final ManagerPlanArchiveManager archiveManager = new ManagerPlanArchiveManager();

	@Autowired
	private AuthorizationManager authorizationManager;

	@Autowired
	private XPlanManager manager;

	@Autowired
	private InternalIdRetriever internalIdRetriever;

	static class ManagerServiceImplException extends RuntimeException {

		ManagerServiceImplException(Exception e) {
			super(e.getMessage());
		}

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		WebApplicationContextUtils.getWebApplicationContext(config.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
	}

	public List<XPlan> getPlansFromManager() {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve all plans.");
		List<XPlan> xPlanList;
		try {
			xPlanList = manager.list();
		}
		catch (Exception e) {
			LOG.error("getPlansFailed", e);
			throw new ManagerServiceImplException(e);
		}
		return xPlanList;
	}

	public XPlan getPlanFromLocal() {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve plan from session.");
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan xPlan = archiveManager.retrievePlanFromSession(session);
		return xPlan;
	}

	public XPlanToEdit getPlanToEdit(String planId) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve plan with id {} to edit.", StringUtils.normalizeSpace(planId));
		try {
			int id = Integer.parseInt(planId);
			XPlan plan = manager.getXPlanById(id);
			if (plan != null)
				return manager.getXPlanToEdit(plan);
			else {
				String message = BUNDLE.getString("getPlanToEditAbortedAsNoPlanMatchedId");
				LOG.info(message);
				throw new IllegalArgumentException(message);
			}
		}
		catch (Exception e) {
			LOG.error("getPlanToEdit failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public void editPlan(String planId, boolean updateRasterConfig, @Valid XPlanToEdit xPlanToEdit) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Try to edit plan with id {}.", StringUtils.normalizeSpace(planId));
		try {
			int id = Integer.parseInt(planId);
			XPlan plan = manager.getXPlanById(id);
			if (plan != null) {
				HttpSession session = getThreadLocalRequest().getSession();
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
			LOG.error("editPlan failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public List<RasterEvaluationResult> evaluateRaster(String id, XPlanToEdit xPlanToEdit) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Evaluate uploaded raster of plan with id {}.", StringUtils.normalizeSpace(id));
		try {
			HttpSession session = getThreadLocalRequest().getSession();
			List<File> uploadedArtefacts = archiveManager.retrieveUploadedArtefacts(session);
			return manager.evaluateRasterdata(xPlanToEdit, uploadedArtefacts);
		}
		catch (Exception e) {
			LOG.error("evaluationRaster failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public Boolean removePlanFromManager(String planId) {
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
			LOG.error("removePlanFromManager failed", e);
			throw new ManagerServiceImplException(e);
		}
		return false;
	}

	public Boolean removePlanFromFileSystem(String planId) {
		LOG.info("Try to remove local plan.");
		HttpSession session = getThreadLocalRequest().getSession();
		if (planId == null)
			return false;
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		if (plan != null && planId.equals(plan.getId())) {
			archiveManager.clearPlanInSession(session);
			return true;
		}
		return false;
	}

	public Boolean importPlan(String planId, String internalId, String defaultCrs, boolean makeRasterConfig,
			PlanStatus planStatus, Date startDateTime, Date endDateTime) throws InvalidParameterException {
		checkInternalId(internalId);
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Try to import plan with id {}", StringUtils.normalizeSpace(planId));
		HttpSession session = getThreadLocalRequest().getSession();
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
				LOG.error("importPlan failed", e);
				throw new ManagerServiceImplException(e);
			}
			return true;
		}
		return false;
	}

	public List<Bereich> retrieveBereiche(String planId) throws Exception {
		return manager.getBereicheOfPlanWithId(planId);
	}

	public Map<String, String> retrieveMatchingInternalIds(String id) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve internal id of plan with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
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
			LOG.error("retrieveMatchingInternalIds failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	@RequestMapping(value = "/crs/{id}", method = GET)
	@ResponseBody
	@Deprecated
	public Boolean isCrsSet(String id) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve crs of plan with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.isCrsSet(fileToBeImported);
		}
		catch (Exception e) {
			LOG.error("isCrsSet failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public List<RasterEvaluationResult> evaluateRaster(String id) {

		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Evaluate raster of with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.evaluateRasterdata(fileToBeImported);
		}
		catch (Exception e) {
			LOG.error("evaluateRaster failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public List<PlanNameWithStatusResult> evaluatePlanNameAndStatus(String id, PlanStatus status) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Evaluate name of plan with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			if ("null".equals(status))
				status = null;
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.evaluatePlanNameAndStatus(fileToBeImported, status.toString());
		}
		catch (Exception e) {
			LOG.error("evaulatePlanNameAndStats failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public RechtsstandAndPlanStatus determineLegislationStatus(String id) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Evaluate legislation status of plan with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			Pair<Rechtsstand, PlanStatus> rechtsstandPlanStatusPair = manager.determineRechtsstand(fileToBeImported);
			return new RechtsstandAndPlanStatus(rechtsstandPlanStatusPair.first, rechtsstandPlanStatusPair.second);
		}
		catch (Exception e) {
			LOG.error("determineLegislationStatus failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	public Boolean publishPlan(String planId) {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Publish plan with id {} as INSPIRE dataset.", StringUtils.normalizeSpace(planId));
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
			LOG.error("publishPlan failed", e);
			throw new ManagerServiceImplException(e);
		}
		return false;
	}

	private String retrieveFileToBeImported(XPlan xPlan) {
		String fileName = archiveManager.determineFileNameAndFolder(xPlan);
		return archiveManager.getUploadFolder() + "/" + fileName;
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
