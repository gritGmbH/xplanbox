/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.pdf;

import de.latlon.xplan.validator.report.ValidatorReport;
import org.junit.Test;

import java.util.Collections;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PdfReportGeneratorTest {

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReportAsPdfWithNullReport() throws Exception {
		ReportBuilder reportBuilder = new ReportBuilder();
		reportBuilder.createReport(null);
	}

	@Test
	public void testCreateReportAsPdfWithNullValidationName() throws Exception {
		ReportBuilder reportBuilder = new ReportBuilder();
		reportBuilder.createReport(createReport(null, "PlanName"));
	}

	@Test
	public void testCreateReportAsPdfWithEmptyValidationName() throws Exception {
		ReportBuilder reportBuilder = new ReportBuilder();
		reportBuilder.createReport(createReport("", "PlanName"));
	}

	@Test
	public void testCreateReportAsPdfWithNullPlanName() throws Exception {
		ReportBuilder reportBuilder = new ReportBuilder();
		reportBuilder.createReport(createReport("ValName", null));
	}

	@Test
	public void testCreateReportAsPdfWithEmptyPlanName() throws Exception {
		ReportBuilder reportBuilder = new ReportBuilder();
		reportBuilder.createReport(createReport("ValName", ""));
	}

	private ValidatorReport createReport(String archiveName, String planName) {
		ValidatorReport validatorReport = new ValidatorReport();
		validatorReport.setPlanNames(Collections.singletonList(archiveName));
		validatorReport.setValidationName(planName);
		return validatorReport;
	}

}
