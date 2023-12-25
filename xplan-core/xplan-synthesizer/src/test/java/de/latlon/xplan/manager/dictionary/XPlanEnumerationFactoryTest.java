/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.dictionary;

import de.latlon.xplan.commons.XPlanVersion;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */

class XPlanEnumerationFactoryTest {

	static List<XPlanVersion> xPlanVersions() {
		List<XPlanVersion> versions = new ArrayList<>();
		for (XPlanVersion versionToAdd : XPlanVersion.values())
			if (!XPlanVersion.XPLAN_SYN.equals(versionToAdd))
				versions.add(versionToAdd);
		return versions;
	}

	@ParameterizedTest
	@MethodSource("xPlanVersions")
	void testCreateSynFeatures(XPlanVersion versionUnderTest) {
		XPlanDictionaries enumerations = XPlanEnumerationFactory.get(versionUnderTest);
		assertNotNull(enumerations);
	}

}
