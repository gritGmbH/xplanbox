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

import com.google.gwt.user.server.rpc.jakarta.XsrfProtectedServiceServlet;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.client.service.ManagerService;
import de.latlon.xplan.manager.web.server.service.security.AuthorizationManager;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.Rechtsstand;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.apache.commons.lang3.StringUtils;
import org.deegree.commons.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

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

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		WebApplicationContextUtils.getWebApplicationContext(config.getServletContext())
			.getAutowireCapableBeanFactory()
			.autowireBean(this);
	}

	@Override
	public List<XPlan> getPlansFromManager() throws ManagerServiceImplException {
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

	@Override
	public XPlan getPlanFromLocal() {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Retrieve plan from session.");
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan xPlan = archiveManager.retrievePlanFromSession(session);
		return xPlan;
	}

	@Override
	public XPlanToEdit getPlanToEdit(String planId) throws ManagerServiceImplException {
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

	@Override
	public void editPlan(String planId, boolean updateRasterConfig, @Valid XPlanToEdit xPlanToEdit)
			throws ManagerServiceImplException {
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

	@Override
	public List<RasterEvaluationResult> evaluateRaster(String id, XPlanToEdit xPlanToEdit)
			throws ManagerServiceImplException {
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

	@Override
	public Boolean removePlanFromManager(String planId) throws ManagerServiceImplException {
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

	@Override
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

	@Override
	public Boolean importPlan(String planId, boolean makeRasterConfig, PlanStatus planStatus)
			throws ManagerServiceImplException {
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
				manager.importPlan(archive, false, makeRasterConfig, planStatus);
			}
			catch (Exception e) {
				LOG.error("importPlan failed", e);
				throw new ManagerServiceImplException(e);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<Bereich> retrieveBereiche(String planId) throws Exception {
		return manager.getBereicheOfPlanWithId(planId);
	}

	@Override
	public List<RasterEvaluationResult> evaluateRaster(String id) throws ManagerServiceImplException {

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

	@Override
	public List<PlanNameWithStatusResult> evaluatePlanNameAndStatus(String id, PlanStatus status)
			throws ManagerServiceImplException {
		getThreadLocalResponse().addHeader("Expires", "-1");
		LOG.info("Evaluate name of plan with id {}.", StringUtils.normalizeSpace(id));
		HttpSession session = getThreadLocalRequest().getSession();
		XPlan plan = archiveManager.retrievePlanFromSession(session);
		try {
			String fileToBeImported = retrieveFileToBeImported(plan);
			return manager.evaluatePlanNameAndStatus(fileToBeImported, status != null ? status.toString() : null);
		}
		catch (Exception e) {
			LOG.error("evaulatePlanNameAndStats failed", e);
			throw new ManagerServiceImplException(e);
		}
	}

	@Override
	public RechtsstandAndPlanStatus determineLegislationStatus(String id) throws ManagerServiceImplException {
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

	@Override
	public Boolean publishPlan(String planId) throws ManagerServiceImplException {
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

}
