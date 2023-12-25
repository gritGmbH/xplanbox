/*-
 * #%L
 * xplan-inspireplu-transformation - Transformation XPlanGML nach INSPIRE PLU
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
package de.latlon.xplan.inspire.plu.transformation;

import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj3.XmlAssert;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.xmlunit.builder.Input.fromURI;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Disabled
class HaleCliInspirePluTransformatorTest {

	private final String testResource = "/tmp/Billstedt28/xplan.gml";

	private final String haleCli = "/tmp/hale/bin/hale";

	private final Path haleProjectDirectory = Paths.get("/tmp/hale");

	@Test
	void testTransformationToPlu() throws Exception {
		HaleCliInspirePluTransformator transformator = new HaleCliInspirePluTransformator(haleCli,
				haleProjectDirectory);
		Path inspirePlu = transformator.transformToPlu(Paths.get(testResource), XPLAN_41);

		assertNotNull(inspirePlu);
		XmlAssert.assertThat(the(inspirePlu)).withNamespaceContext(nsContext()).hasXPath("//plu:SpatialPlan");
		XmlAssert.assertThat(the(inspirePlu))
			.isValidAgainst(fromURI("http://inspire.ec.europa.eu/schemas/plu/4.0/PlannedLandUse.xsd"));
	}

	private String the(Path path) throws Exception {
		InputStream is = new FileInputStream(path.toFile());
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));

		String line = buf.readLine();
		StringBuilder sb = new StringBuilder();
		while (line != null) {
			sb.append(line).append("\n");
			line = buf.readLine();
		}
		return sb.toString();
	}

	private Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("plu", "http://inspire.ec.europa.eu/schemas/plu/4.0");
		return nsContext;
	}

}
