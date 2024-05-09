/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.dictionary;

import de.latlon.xplan.commons.XPlanVersion;

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * Instantiates {@link XPlanDictionaries} for different XPlan GML versions.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanEnumerationFactory {

	private static final String XPLAN_40_CODE_LISTS = "/enumeration/XPlanGML_4_0_Enumerationen.xml";

	private static final String XPLAN_41_CODE_LISTS = "/enumeration/XPlanGML_Enumerationen_4_1.xml";

	private static final String XPLAN_50_CODE_LISTS = "/enumeration/XPlanGML_5_0_Enumerationen.xml";

	private static final String XPLAN_51_CODE_LISTS = "/enumeration/XPlanGML_5_1_Enumerationen.xml";

	private static final String XPLAN_52_CODE_LISTS = "/enumeration/XPlanGML_5_2_Enumerationen.xml";

	private static final String XPLAN_53_CODE_LISTS = "/enumeration/XPlanGML_5_3_Enumerationen.xml";

	private static final String XPLAN_54_CODE_LISTS = "/enumeration/XPlanGML_5_4_Enumerationen.xml";

	private static final String XPLAN_60_CODE_LISTS = "/enumeration/XPlanGML_6_0_2_Enumerationen.xml";

	private static XPlanDictionaries xplan40Enumerations;

	private static XPlanDictionaries xplan41Enumerations;

	private static XPlanDictionaries xplan50Enumerations;

	private static XPlanDictionaries xplan51Enumerations;

	private static XPlanDictionaries xplan52Enumerations;

	private static XPlanDictionaries xplan53Enumerations;

	private static XPlanDictionaries xplan54Enumerations;

	private static XPlanDictionaries xplan60Enumerations;

	private static XPlanDictionariesParser xPlanDictionariesParser = new XPlanDictionariesParser();

	private XPlanEnumerationFactory() {
	}

	/**
	 * @param version the version of the XPlanGML, never <code>null</code>
	 * @return the {@link XPlanDictionaries} for the specified version, never
	 * <code>null</code>
	 * @throws IllegalArgumentException if the version is not supported
	 */
	public static XPlanDictionaries get(XPlanVersion version) {
		switch (version) {
			case XPLAN_40:
				return getXPlan40();
			case XPLAN_41:
				return getXPlan41();
			case XPLAN_50:
				return getXPlan50();
			case XPLAN_51:
				return getXPlan51();
			case XPLAN_52:
				return getXPlan52();
			case XPLAN_53:
				return getXPlan53();
			case XPLAN_54:
				return getXPlan54();
			case XPLAN_60:
				return getXPlan60();
			default:
				throw new IllegalArgumentException("Could not find enumerations for version " + version);
		}
	}

	private static synchronized XPlanDictionaries getXPlan40() {
		if (xplan40Enumerations == null) {
			xplan40Enumerations = parseDictionary(XPLAN_40_CODE_LISTS);
		}
		return xplan40Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan41() {
		if (xplan41Enumerations == null) {
			xplan41Enumerations = parseDictionaryFromGml30(XPLAN_41_CODE_LISTS);
		}
		return xplan41Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan50() {
		if (xplan50Enumerations == null) {
			xplan50Enumerations = parseDictionary(XPLAN_50_CODE_LISTS);
		}
		return xplan50Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan51() {
		if (xplan51Enumerations == null) {
			xplan51Enumerations = parseDictionary(XPLAN_51_CODE_LISTS);
		}
		return xplan51Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan52() {
		if (xplan52Enumerations == null) {
			xplan52Enumerations = parseDictionary(XPLAN_52_CODE_LISTS);
		}
		return xplan52Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan53() {
		if (xplan53Enumerations == null) {
			xplan53Enumerations = parseDictionary(XPLAN_53_CODE_LISTS);
		}
		return xplan53Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan54() {
		if (xplan54Enumerations == null) {
			xplan54Enumerations = parseDictionary(XPLAN_54_CODE_LISTS);
		}
		return xplan54Enumerations;
	}

	private static synchronized XPlanDictionaries getXPlan60() {
		if (xplan60Enumerations == null) {
			xplan60Enumerations = parseDictionary(XPLAN_60_CODE_LISTS);
		}
		return xplan60Enumerations;
	}

	private static XPlanDictionaries parseDictionary(String dictionaryResource) {
		try {
			return xPlanDictionariesParser.parseDictionaries(XPlanDictionaries.class.getResource(dictionaryResource));
		}
		catch (Exception e) {
			String msg = "Internal error reading dictionary file: " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
	}

	private static XPlanDictionaries parseDictionaryFromGml30(String dictionaryResource) {
		try {
			return xPlanDictionariesParser.parseDictionaries(XPlanDictionaries.class.getResource(dictionaryResource),
					GML_30);
		}
		catch (Exception e) {
			String msg = "Internal error reading dictionary file: " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
	}

}
