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
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.reference.ExternalReferenceStatus;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationService;
import de.latlon.xplanbox.core.gwt.commons.server.service.ValidationUtils;
import de.latlon.xplanbox.core.gwt.commons.shared.InvalidParameterException;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationException;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

/**
 * Executes validation runs
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanMgrValidationServiceImpl extends XsrfProtectedServiceServlet implements ValidationService {

	private static final long serialVersionUID = -8136082144921716747L;

	private static final Logger LOG = LoggerFactory.getLogger(XPlanMgrValidationServiceImpl.class);

	private final ManagerPlanArchiveManager archiveManager = new ManagerPlanArchiveManager();

	protected HttpSession session;

	@Autowired
	private XPlanValidator xPlanValidator;

	@Autowired
	private ReportWriter reportWriter;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	public void service(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession(true);
		super.service(request, response);
	}

	@Override
	public ValidationSummary validate(ValidationSettings validationSettings)
			throws ValidationException, IllegalArgumentException, InvalidParameterException {
		ValidationUtils.validate(validationSettings);
		try {
			XPlan planToVerify = archiveManager.retrieveRequiredPlanFromSession(session);
			File archive = archiveManager.readArchiveFromFilesystem(planToVerify);

			ValidatorReport report = xPlanValidator.validateNotWriteReport(validationSettings, archive,
					planToVerify.getName());

			writeArtifacts(planToVerify, report);

			updatePlanStatus(planToVerify, report);
			return new ValidationSummary(planToVerify.getId(), validationSettings.getValidationName());
		}
		catch (IOException | ReportGenerationException | ValidatorException e) {
			LOG.error("An exception occurred during validation", e);
			throw new ValidationException(e.getMessage());
		}
		catch (IllegalArgumentException e) {
			LOG.error("An exception occurred during validation", e);
			throw e;
		}
	}

	public boolean poll() {
		LOG.trace("Client still polling");
		return true;
	}

	private void writeArtifacts(XPlan planToVerify, ValidatorReport report)
			throws ReportGenerationException, IOException {
		Path targetDirectory = archiveManager.createReportDirectory(planToVerify.getId());
		reportWriter.writeArtefacts(report, targetDirectory);
	}

	private void updatePlanStatus(XPlan planToVerify, ValidatorReport report) {
		planToVerify.setValidated(true);
		planToVerify.setValid(report.isReportValid());
		planToVerify.setHasMultipleXPlanElements(report.hasMultipleXPlanElements());
		boolean hasUnresolvedReferences = false;
		if (report.getExternalReferenceReport() != null)
			hasUnresolvedReferences = report.getExternalReferenceReport()
				.getReferencesAndStatus()
				.containsValue(ExternalReferenceStatus.MISSING);
		planToVerify.setHasUnresolvedReferences(hasUnresolvedReferences);
	}

}
