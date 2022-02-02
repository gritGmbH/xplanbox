/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.codelists;

import de.latlon.xplan.commons.XPlanVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */

@RunWith(Parameterized.class)
public class XPlanCodeListsFactoryTest {

	private XPlanVersion versionUnderTest;

	@Parameterized.Parameters
	public static List<XPlanVersion> xPlanVersions() {
		List<XPlanVersion> versions = new ArrayList<>();
		for (XPlanVersion versionToAdd : XPlanVersion.values())
			if (!XPlanVersion.XPLAN_SYN.equals(versionToAdd))
				versions.add(versionToAdd);
		return versions;
	}

	public XPlanCodeListsFactoryTest(XPlanVersion versionUnderTest) {
		this.versionUnderTest = versionUnderTest;
	}

	@Test
	public void testCreateSynFeatures() {
		XPlanCodeLists codeLists = XPlanCodeListsFactory.get(versionUnderTest);
		assertThat(codeLists, is(notNullValue()));
	}

}
