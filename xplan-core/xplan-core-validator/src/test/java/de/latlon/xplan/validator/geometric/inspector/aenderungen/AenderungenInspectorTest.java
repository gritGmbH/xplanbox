/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.aenderungen;

import org.junit.Test;

import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AenderungenInspectorTest {

	@Test
	public void test_InspectAendertAndWurdeGeaendertVonReferences() throws Exception {
		AenderungenInspector aenderungenInspector = new AenderungenInspector();
		readFeaturesFromGml("aendertUndWurdeGeandertVon.gml", AenderungenInspectorTest.class, aenderungenInspector);

		assertTrue(aenderungenInspector.isAenderungReference("GML_18e6f5cd-9896-4e80-b4f3-ce0d8cc8a0c4"));
		assertTrue(aenderungenInspector.isAenderungReference("GML_5792983e-433f-11e8-88d4-b3eda89dad90"));
		assertFalse(aenderungenInspector.isAenderungReference("GML_7af470e9-0167-43ae-823d-56e4241eab9d"));
		assertFalse(aenderungenInspector.isAenderungReference("GML_7af470e9-0167-43ae-823d-56e4241eab9d"));
	}

	@Test
	public void test_InspectAendertAndWurdeGeaendertVonReferences_XPlanGml60() throws Exception {
		AenderungenInspector aenderungenInspector = new AenderungenInspector();
		readFeaturesFromGml("aendertPlanUndAendertPlanBereich.gml", AenderungenInspectorTest.class,
				aenderungenInspector);

		assertTrue(aenderungenInspector.isAenderungReference("REF_1"));
		assertTrue(aenderungenInspector.isAenderungReference("REF_2"));
		assertFalse(aenderungenInspector.isAenderungReference("GML_9ee7445b-a079-403e-8717-cbc0dd9687ee"));
		assertFalse(aenderungenInspector.isAenderungReference("GML_033a8b97-b5f7-4453-99d7-b624fc1b4ef5"));
	}

}
