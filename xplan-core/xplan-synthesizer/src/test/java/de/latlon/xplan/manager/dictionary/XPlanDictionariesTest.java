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
import org.junit.Test;

import java.net.URL;

import static org.deegree.gml.GMLVersion.GML_30;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class XPlanDictionariesTest {

	@Test
	public void testGetTranslation_XPlan41() {
		XPlanVersion version = XPlanVersion.XPLAN_41;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String translation = dictionaries.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan52() {
		XPlanVersion version = XPlanVersion.XPLAN_52;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String translation = dictionaries.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan53() {
		XPlanVersion version = XPlanVersion.XPLAN_53;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String translation = dictionaries.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("InkraftGetreten"));
	}

	@Test
	public void testGetTranslation_XPlan60() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String translation = dictionaries.getTranslation("BP_Rechtsstand", "4000");

		assertThat(translation, is("In Kraft getreten"));
	}

	@Test
	public void testGetTranslation_XPlan60_lesbarereName() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		String translation = dictionaries.getTranslation("XP_ArtHoehenbezug", "1000");

		assertThat(translation, is("Absolut NHN"));
	}

	@Test
	public void testGetCodeEntry_XPlan60_kuerzelAndLesbarerName() {
		XPlanVersion version = XPlanVersion.XPLAN_60;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		XPlanDictionary dictionary = dictionaries.getDictionary("XP_ArtHoehenbezug");
		XPlanDictionaryEntry dictionaryEntry = dictionary.getDictionaryEntry("1000");

		assertThat(dictionaryEntry.getName(), is("absolutNHN"));
		assertThat(dictionaryEntry.getLesbarerName(), is("Absolut NHN"));
		assertThat(dictionaryEntry.getKuerzel(), is("NHN"));
	}

	@Test
	public void testParseCodelist_GML3() throws Exception {
		URL codeListFile = XPlanDictionariesTest.class
			.getResource("../synthesizer/XP_BesondereArtDerBaulNutzung-XPlan4.xml");
		XPlanDictionaries dictionaries = new XPlanDictionariesParser().parseDictionaries(codeListFile, GML_30);
		XPlanDictionary dictionary = dictionaries.getDictionary("xplan_XP_BesondereArtDerBaulNutzung");

		assertThat(dictionaries.getDictionaries().size(), is(1));
		assertThat(dictionary.getDictionaryEntries().size(), is(5));
	}

	@Test
	public void testParseCodelist_GML32() throws Exception {
		URL codeListFile = XPlanDictionariesTest.class
			.getResource("../synthesizer/XP_BesondereArtDerBaulNutzung-XPlan5.xml");
		XPlanDictionaries dictionaries = new XPlanDictionariesParser().parseDictionaries(codeListFile);
		XPlanDictionary dictionary = dictionaries.getDictionary("XP_BesondereArtDerBaulNutzung");

		assertThat(dictionaries.getDictionaries().size(), is(1));
		assertThat(dictionary.getDictionaryEntries().size(), is(15));
	}

	@Test
	public void testGetZweckbestimmungGruenTypeCodelistEntry_GML40() {
		XPlanVersion version = XPlanVersion.XPLAN_40;
		XPlanDictionaries dictionaries = XPlanEnumerationFactory.get(version);
		XPlanDictionary xpZweckbestimmungGruen = dictionaries.getDictionary("XP_ZweckbestimmungGruen");
		XPlanDictionaryEntry xpZweckbestimmungGruenCodeEntry = xpZweckbestimmungGruen.getDictionaryEntry("9999");
		assertThat(xpZweckbestimmungGruenCodeEntry.getName(), is("Sonstiges"));
	}

}
