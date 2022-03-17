/*-
 * #%L
 * xplan-inspireplu-transformation - Transformation XPlanGML nach INSPIRE PLU
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
package de.latlon.xplan.inspire.plu.transformation;

import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.HasXPathMatcher;
import org.xmlunit.matchers.ValidationMatcher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.builder.Input.fromURI;
import static org.xmlunit.matchers.ValidationMatcher.valid;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Ignore
public class HaleCliInspirePluTransformatorTest {

	private final String testResource = "/tmp/Billstedt28/xplan.gml";

	private final String haleCli = "/tmp/hale/bin/hale";

	private final Path haleProjectDirectory = Paths.get("/tmp/hale");

	@Test
	public void testTransformationToPlu() throws Exception {
		HaleCliInspirePluTransformator transformator = new HaleCliInspirePluTransformator(haleCli,
				haleProjectDirectory);
		Path inspirePlu = transformator.transformToPlu(Paths.get(testResource), XPLAN_41);

		assertThat(inspirePlu, notNullValue());
		assertThat(the(inspirePlu), HasXPathMatcher.hasXPath("//plu:SpatialPlan").withNamespaceContext(nsContext()));
		assertThat(the(inspirePlu), valid(fromURI("http://inspire.ec.europa.eu/schemas/plu/4.0/PlannedLandUse.xsd")));
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
