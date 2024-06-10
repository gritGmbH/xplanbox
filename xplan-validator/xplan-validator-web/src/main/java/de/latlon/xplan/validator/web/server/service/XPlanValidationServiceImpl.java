/*-
 * #%L
 * xplan-validator-web - Webanwendung XPlanValidatorWeb
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
package de.latlon.xplan.validator.web.server.service;

import com.google.gwt.user.server.rpc.jakarta.XsrfProtectedServiceServlet;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
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
 * Executes validation runs.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version 2.3
 * @since 2.3
 */
public class XPlanValidationServiceImpl extends XsrfProtectedServiceServlet implements ValidationService {

	private static final long serialVersionUID = -6841654850245762811L;

	private static final Logger LOG = LoggerFactory.getLogger(XPlanValidationServiceImpl.class);

	private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

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
		LOG.trace("Handling request {} in {}", request.getRequestURI(), this.getClass());
		session = request.getSession(true);
		super.service(request, response);
	}

	@Override
	public ValidationSummary validate(ValidationSettings validationSettings)
			throws ValidationException, InvalidParameterException {
		LOG.debug("Starting validation of plan with {}", validationSettings.toString());
		ValidationUtils.validate(validationSettings);
		try {
			XPlan planToVerify = planArchiveManager.readPlanFromSession(session);
			String planUuid = planToVerify.getId();
			LOG.debug("Validating plan {} with id {}", planToVerify.getName(), planUuid);
			File archive = planArchiveManager.retrieveXPlanArchiveFromFileSystem(planToVerify);
			LOG.debug("Writing validation report for plan {}", planUuid);
			ValidatorReport report = xPlanValidator.validateNotWriteReport(validationSettings, archive,
					planToVerify.getName());

			Path reportDirectory = planArchiveManager.createReportDirectory(planUuid);

			LOG.debug("Validation report for {} written to file {}", planUuid, reportDirectory);
			reportWriter.writeArtefacts(report, reportDirectory);

			return new ValidationSummary(planUuid, validationSettings.getValidationName());
		}
		catch (ValidatorException | IOException e) {
			LOG.error("An exception occurred during validation", e);
			throw new ValidationException(e.getMessage());
		}
	}

	public boolean poll() {
		LOG.trace("Client still polling");
		return true;
	}

}
