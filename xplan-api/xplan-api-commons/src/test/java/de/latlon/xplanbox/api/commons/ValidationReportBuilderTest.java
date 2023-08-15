/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
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
package de.latlon.xplanbox.api.commons;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
class ValidationReportBuilderTest {

	@Test
	void verifyThat_Builder_ReturnsInstance() {
		ValidationReport report = new ValidationReportBuilder().build();
		assertNotNull(report);
	}

	@Test
	void verifyThat_Builder_AddsFilename() {
		ValidatorReport sourceReport = Mockito.mock(ValidatorReport.class);
		ValidationReport report = new ValidationReportBuilder().validatorReport(sourceReport)
			.filename("test.xml")
			.build();
		assertThat(report.getFilename()).contains("test.xml");
	}

	@Test
	void verifyThat_Builder_AddsVersion() {
		ValidatorReport sourceReport = Mockito.mock(ValidatorReport.class);
		when(sourceReport.getXPlanVersion()).thenReturn(XPLAN_52);
		ValidationReport report = new ValidationReportBuilder().validatorReport(sourceReport).build();
		assertThat(report.getVersion()).isEqualTo(VersionEnum.XPLAN_52);
	}

	@Test
	void verifyThat_Builder_AddsWmsUrl() throws URISyntaxException {
		ValidatorReport sourceReport = Mockito.mock(ValidatorReport.class);
		ValidationReport report = new ValidationReportBuilder().validatorReport(sourceReport)
			.wmsUrl(new URI("file://here"))
			.build();
		assertThat(report.getWmsUrl()).isNotNull();
	}

	@Test
	void verifyThat_Builder_ReturnsCompleteInstance() throws URISyntaxException {
		ValidatorReport sourceReport = Mockito.mock(ValidatorReport.class);
		ValidationReport validationReport = new ValidationReportBuilder().validatorReport(sourceReport)
			.filename("test.xml")
			.wmsUrl(new URI("file:///no/real/file/name"))
			.build();
		assertThat(validationReport.getWmsUrl()).isNotNull();
		assertThat(validationReport.getFilename()).contains("test");
	}

}
