/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.junit.Test;

import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorTest {

	@Test
	public void testCheck() throws Exception {
		long startTimeMillis = System.currentTimeMillis();
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromZip("xplan51/V4_1_ID_103_geltungsbereich-erfuellt.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		long endTimeMillis = System.currentTimeMillis();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(0));

		System.out.println(String.format("Geltungsbereich check needed %s [ms]", endTimeMillis - startTimeMillis));
	}

	@Test
	public void testCheck_invalid() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromZip("xplan51/V4_1_ID_103.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));

		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getOriginalGeometry(), is(notNullValue()));
	}

	@Test
	public void testCheck_invalid_41() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromZip("xplan41/V4_1_ID_103.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));

		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getOriginalGeometry(), is(notNullValue()));
	}

	@Test
	public void testCheck_invalid_withLine() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromZip("xplan51/BP2070.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(2));

		BadGeometry badGeometry1 = geltungsbereichInspector.getBadGeometries().get(0);
		assertThat(badGeometry1.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry1.getMarkerGeometries().size(), is(1));

		BadGeometry badGeometry2 = geltungsbereichInspector.getBadGeometries().get(1);
		assertThat(badGeometry2.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry2.getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_valid_tolerance() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromZip("xplan51/V4_1_ID_103_withtolerance.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_MultipePlanNoBereich() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector();
		readFeaturesFromGml("HafenCity11_HafenCity14_Bereich_ohne_Geometrie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

}
