/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.codelists;

import de.latlon.xplan.commons.XPlanVersion;
import org.junit.Test;

import java.net.URL;

import static org.deegree.gml.GMLVersion.GML_30;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class XPlanCodeListsTest {

	@Test
	public void testGetTranslation_XPlan41() {
		XPlanVersion version = XPlanVersion.XPLAN_41;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		String translation = xPlanCodeLists.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan52() {
		XPlanVersion version = XPlanVersion.XPLAN_52;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		String translation = xPlanCodeLists.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan53() {
		XPlanVersion version = XPlanVersion.XPLAN_53;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		String translation = xPlanCodeLists.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan60() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		String translation = xPlanCodeLists.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("In Kraft getreten"));
	}

	@Test
	public void testGetTranslation_XPlan60_lesbarereName() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		String translation = xPlanCodeLists.getTranslation("XP_ArtHoehenbezug", "1000");

		assertThat(translation, is("Absolut NHN"));
	}

	@Test
	public void testGetCodeEntry_XPlan60_kuerzelAndLesbarerName() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(version);
		XPlanCodeList codeList = xPlanCodeLists.getCodeList("XP_ArtHoehenbezug");
		XPlanCodeEntry codeEntry = codeList.getCodeEntry("1000");

		assertThat(codeEntry.getName(), is("absolutNHN"));
		assertThat(codeEntry.getLesbarerName(), is("Absolut NHN"));
		assertThat(codeEntry.getKuerzel(), is("NHN"));
	}

	@Test
	public void testParseCodelist_GML3() throws Exception {
		URL codeListFile = XPlanCodeListsTest.class
				.getResource("../synthesizer/XP_BesondereArtDerBaulNutzung-XPlan4.xml");
		XPlanCodeLists codeLists = new XPlanCodeListsParser().parseCodelists(codeListFile, GML_30);
		XPlanCodeList codeList = codeLists.getCodeList("xplan_XP_BesondereArtDerBaulNutzung");

		assertThat(codeLists.getCodeLists().size(), is(1));
		assertThat(codeList.getCodeEntries().size(), is(5));
	}

	@Test
	public void testParseCodelist_GML32() throws Exception {
		URL codeListFile = XPlanCodeListsTest.class
				.getResource("../synthesizer/XP_BesondereArtDerBaulNutzung-XPlan5.xml");
		XPlanCodeLists codeLists = new XPlanCodeListsParser().parseCodelists(codeListFile);
		XPlanCodeList codeList = codeLists.getCodeList("XP_BesondereArtDerBaulNutzung");

		assertThat(codeLists.getCodeLists().size(), is(1));
		assertThat(codeList.getCodeEntries().size(), is(15));
	}

}
