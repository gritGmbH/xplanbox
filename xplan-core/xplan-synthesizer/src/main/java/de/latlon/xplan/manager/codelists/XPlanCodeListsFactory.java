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
import org.deegree.gml.GMLVersion;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * Instantiates {@link XPlanCodeLists} for different XPlan GML versions.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodeListsFactory {

	private static final String XPLAN_3_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_CodeLists.xml";

	private static final String XPLAN_3_EXT_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_ExternalCodeLists.xml";

	private static final String XPLAN_40_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_0.xml";

	private static final String XPLAN_41_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_1.xml";

	private static final String XPLAN_50_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_0.xml";

	private static final String XPLAN_51_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_1.xml";

	private static final String XPLAN_52_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_2.xml";

	private static final String XPLAN_53_CODE_LISTS = "/codelists/XPlanGML_5_3_Enumerationen.xml";

	private static final String XPLAN_54_CODE_LISTS = "/codelists/XPlanGML_5_4_Enumerationen.xml";

	private static final String XPLAN_SYN_CODE_LISTS = "/appschemas/XPlanGML_Syn/XPlanSyn_CodeLists.xml";

	private static final String XPLAN_SYN_EXT_CODE_LISTS_XP2 = "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP2.xml";

	private static final String XPLAN_SYN_EXT_CODE_LISTS_XP3 = "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP3.xml";

	private static XPlanCodeLists xplan3CodeLists;

	private static XPlanCodeLists xplan3ExtCodeLists;

	private static XPlanCodeLists xplanSynCodeLists;

	private static XPlanCodeLists xplanSynExtCodeLists;

	private static XPlanCodeLists xplan40CodeLists;

	private static XPlanCodeLists xplan41CodeLists;

	private static XPlanCodeLists xplan50CodeLists;

	private static XPlanCodeLists xplan51CodeLists;

	private static XPlanCodeLists xplan52CodeLists;

	private static XPlanCodeLists xplan53CodeLists;

	private static XPlanCodeLists xplan54CodeLists;

	private static XPlanCodeListsParser xPlanCodeListParser = new XPlanCodeListsParser();

	/**
	 * @param version the version of the XPlanGML, never <code>null</code>
	 * @return the {@link XPlanCodeLists} for the specified version, never
	 * <code>null</code>
	 * @throws IllegalArgumentException if the version is not supported
	 */
	public static XPlanCodeLists get(XPlanVersion version) {
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
		default:
			throw new IllegalArgumentException("Could not find codelists for version " + version);
		}
	}

	/**
	 * @param codeListUrl the url to parse the code list from, never <code>null</code>
	 * @param gmlVersion version of the dictionary, never <code>null</code>
	 * @return the {@link XPlanCodeLists} parsed from the codeListUrl, never
	 * <code>null</code>
	 * @throws IOException
	 * @throws XMLStreamException
	 */
	public static XPlanCodeLists getXPlanCodeLists(URL codeListUrl, GMLVersion gmlVersion)
			throws IOException, XMLStreamException {
		return xPlanCodeListParser.parseCodelists(codeListUrl, gmlVersion);
	}

	public static synchronized XPlanCodeLists getXPlanSyn() {
		if (xplanSynCodeLists == null) {
			try {
				xplanSynCodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_SYN_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg);
			}
		}
		return xplanSynCodeLists;
	}

	public static synchronized XPlanCodeLists getXPlanSynExt() {
		if (xplanSynExtCodeLists == null) {
			try {
				xplanSynExtCodeLists = mergeCodeLists(
						xPlanCodeListParser
								.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_SYN_EXT_CODE_LISTS_XP3), GML_30),
						xPlanCodeListParser.parseCodelists(
								XPlanCodeLists.class.getResource(XPLAN_SYN_EXT_CODE_LISTS_XP2), GML_30));
			}
			catch (Exception e) {
				String msg = "Fehler in Codelists Datei: " + e.getMessage();
				throw new RuntimeException(msg);
			}
		}
		return xplanSynExtCodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan40() {
		if (xplan40CodeLists == null) {
			try {
				xplan40CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_40_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan40CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan41() {
		if (xplan41CodeLists == null) {
			try {
				xplan41CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_41_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan41CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan50() {
		if (xplan50CodeLists == null) {
			try {
				xplan50CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_50_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan50CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan51() {
		if (xplan51CodeLists == null) {
			try {
				xplan51CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_51_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan51CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan52() {
		if (xplan52CodeLists == null) {
			try {
				xplan52CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_52_CODE_LISTS), GML_30);
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan52CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan53() {
		if (xplan53CodeLists == null) {
			try {
				xplan53CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_53_CODE_LISTS));
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan53CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan54() {
		if (xplan54CodeLists == null) {
			try {
				xplan54CodeLists = xPlanCodeListParser
						.parseCodelists(XPlanCodeLists.class.getResource(XPLAN_54_CODE_LISTS));
			}
			catch (Exception e) {
				String msg = "Internal error reading code lists file: " + e.getMessage();
				throw new RuntimeException(msg, e);
			}
		}
		return xplan54CodeLists;
	}

	private static XPlanCodeLists mergeCodeLists(XPlanCodeLists xplanCodeLists1, XPlanCodeLists xplanCodeLists2) {
		Map<String, Map<String, String>> codesToDesc1 = xplanCodeLists1.getCodesToDescriptions();
		Map<String, Map<String, String>> descToCodes1 = xplanCodeLists1.getDescriptionsToCodes();

		Map<String, Map<String, String>> codesToDesc2 = xplanCodeLists2.getCodesToDescriptions();
		Map<String, Map<String, String>> descToCodes2 = xplanCodeLists2.getDescriptionsToCodes();

		Map<String, Map<String, String>> synCodesToDesc = new HashMap<String, Map<String, String>>();
		Map<String, Map<String, String>> synDescToCodes = new HashMap<String, Map<String, String>>();
		for (String codelistId : codesToDesc1.keySet()) {
			synCodesToDesc.put(codelistId, codesToDesc1.get(codelistId));
			synDescToCodes.put(codelistId, descToCodes1.get(codelistId));
		}
		for (String codelistId : codesToDesc2.keySet()) {
			synCodesToDesc.put(codelistId, codesToDesc2.get(codelistId));
			synDescToCodes.put(codelistId, descToCodes2.get(codelistId));
		}

		return new XPlanCodeLists(synCodesToDesc, synDescToCodes);
	}

}
