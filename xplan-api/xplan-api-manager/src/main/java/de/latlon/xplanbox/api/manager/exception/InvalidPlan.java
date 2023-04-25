/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplanbox.api.commons.ValidationReportBuilder;
import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlan extends XPlanApiException {

	private static final String EXCEPTION_MESSAGE = "Invalid plan";

	private final ValidatorReport validatorReport;

	private final String xFileName;

	public InvalidPlan(ValidatorReport validatorReport, String xFileName) {
		super(EXCEPTION_MESSAGE);
		this.validatorReport = validatorReport;
		this.xFileName = xFileName;
	}

	@Override
	public int getStatusCode() {
		return BAD_REQUEST.getStatusCode();
	}

	@Override
	public Object getResponseEntity() {
		return new ValidationReportBuilder().validatorReport(validatorReport).filename(xFileName).build();
	}

}
