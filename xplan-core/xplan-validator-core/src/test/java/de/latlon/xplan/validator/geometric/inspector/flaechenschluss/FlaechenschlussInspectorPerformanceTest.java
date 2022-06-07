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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Ignore
public class FlaechenschlussInspectorPerformanceTest {

	@Test
	public void testCheckFlaechenschluss_OldInspector() throws Exception {
		FlaechenschlussInspector flaechenschlussInspector = new FlaechenschlussInspector();
		long timeNeeded = checkFlaechenschluss(flaechenschlussInspector);
		System.out.println("Flaechenschluss with old implementation: " + timeNeeded + " [ms]");
	}

	@Test
	public void testCheckFlaechenschluss_OptimizedInspector() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54);
		long timeNeeded = checkFlaechenschluss(flaechenschlussInspector);
		System.out.println("Flaechenschluss with optimized implementation: " + timeNeeded + " [ms]");
	}

	private long checkFlaechenschluss(GeometricFeatureInspector flaechenschlussInspector)
			throws IOException, XMLStreamException, UnknownCRSException {
		long start = System.currentTimeMillis();
		readFeaturesFromZip("Testplan.zip", FlaechenschlussInspectorPerformanceTest.class, flaechenschlussInspector);
		flaechenschlussInspector.checkGeometricRule();
		long end = System.currentTimeMillis();
		return end - start;
	}

}
