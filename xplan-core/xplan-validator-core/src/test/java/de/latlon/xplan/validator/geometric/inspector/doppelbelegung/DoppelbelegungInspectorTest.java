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
package de.latlon.xplan.validator.geometric.inspector.doppelbelegung;

import org.junit.Test;

import java.util.List;

import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DoppelbelegungInspectorTest {

	@Test
	public void test_InspectDoppelbelegung() throws Exception {
		DoppelbelegungInspector doppelbelegungInspector = new DoppelbelegungInspector();
		readFeaturesFromZip("xplan60/BPlan001_6-0.zip", doppelbelegungInspector);
		doppelbelegungInspector.checkGeometricRule();

		List<String> errors = doppelbelegungInspector.getErrors();
		assertThat(errors.size(), is(0));
	}

	@Test
	public void test_InspectDoppelbelegungWithFailures() throws Exception {
		DoppelbelegungInspector doppelbelegungInspector = new DoppelbelegungInspector();
		readFeaturesFromZip("xplan60/Blankenese29_Test_60.zip", doppelbelegungInspector);
		doppelbelegungInspector.checkGeometricRule();

		List<String> errors = doppelbelegungInspector.getErrors();
		assertThat(errors.size(), is(3));
	}

	@Test
	public void test_InspectDoppelbelegungGmlWithFailures() throws Exception {
		DoppelbelegungInspector doppelbelegungInspector = new DoppelbelegungInspector();
		readFeaturesFromGml("xplan60-4_5_2_4.gml", DoppelbelegungInspectorTest.class, doppelbelegungInspector);
		doppelbelegungInspector.checkGeometricRule();

		List<String> errors = doppelbelegungInspector.getErrors();
		assertThat(errors.size(), is(5));
	}

	@Test
	public void test_InspectDoppelbelegung_Toleranzbereich() throws Exception {
		DoppelbelegungInspector doppelbelegungInspector = new DoppelbelegungInspector();
		readFeaturesFromGml("BPlan_6-0_4-5-2-4-Ueberlappung_valide.gml", DoppelbelegungInspectorTest.class,
				doppelbelegungInspector);
		doppelbelegungInspector.checkGeometricRule();

		List<String> errors = doppelbelegungInspector.getErrors();
		assertThat(errors.size(), is(0));
	}

}
