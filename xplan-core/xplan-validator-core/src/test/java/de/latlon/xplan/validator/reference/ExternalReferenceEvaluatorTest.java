/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.validator.reference;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import org.junit.Test;

import java.io.InputStream;

import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.AVAILABLE;
import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.MISSING;
import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.UNCHECKED;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ExternalReferenceEvaluatorTest {

	@Test
	public void testValidateExternalReferences_Avalaible() throws Exception {
		InputStream inputStream = ResourceAccessor.readResourceStream("xplan60/Blankenese29_Test_60.zip");
		XPlanArchive xPlanArchive = new XPlanArchiveCreator().createXPlanArchiveFromZip("Blankenese29_Test_60",
				inputStream);
		XPlanFeatureCollection features = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(xPlanArchive);
		ExternalReferenceReport externalReferenceReport = new ExternalReferenceEvaluator()
			.parseAndAddExternalReferences(features, xPlanArchive);

		assertThat(externalReferenceReport.getReferencesAndStatus().size(), is(2));
		assertThat(externalReferenceReport.getReferencesAndStatus().get("Blankenese29.png"), is(AVAILABLE));
		assertThat(externalReferenceReport.getReferencesAndStatus().get("Blankenese29.pgw"), is(AVAILABLE));
	}

	@Test
	public void testValidateExternalReferences_Missing() throws Exception {
		InputStream inputStream = ResourceAccessor.readResourceStream("xplan60/Blankenese29.gml");
		XPlanArchive xPlanArchive = new XPlanArchiveCreator().createXPlanArchiveFromGml("Blankenese29", inputStream);
		XPlanFeatureCollection features = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(xPlanArchive);
		ExternalReferenceReport externalReferenceReport = new ExternalReferenceEvaluator()
			.parseAndAddExternalReferences(features, xPlanArchive);

		assertThat(externalReferenceReport.getReferencesAndStatus().size(), is(2));
		assertThat(externalReferenceReport.getReferencesAndStatus().get("Blankenese29.png"), is(MISSING));
		assertThat(externalReferenceReport.getReferencesAndStatus().get("Blankenese29.pgw"), is(MISSING));
	}

	@Test
	public void testValidateExternalReferences_Http() throws Exception {
		InputStream inputStream = ResourceAccessor.readResourceStream("xplan60/StErhVO_Hamm_60_httpRef.gml");
		XPlanArchive xPlanArchive = new XPlanArchiveCreator().createXPlanArchiveFromGml("StErhVO_Hamm_60_httpRef.gml",
				inputStream);
		XPlanFeatureCollection features = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(xPlanArchive);
		ExternalReferenceReport externalReferenceReport = new ExternalReferenceEvaluator()
			.parseAndAddExternalReferences(features, xPlanArchive);

		assertThat(externalReferenceReport.getReferencesAndStatus().size(), is(1));
		assertThat(externalReferenceReport.getReferencesAndStatus().get("http://test.de/StErhVO_Hamm.pdf"),
				is(UNCHECKED));
	}

}
