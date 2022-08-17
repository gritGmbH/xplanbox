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

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * Instantiates {@link XPlanCodeLists} for different XPlan GML versions.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodeListsFactory {

	private static final String XPLAN_40_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_0.xml";

	private static final String XPLAN_41_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_1.xml";

	private static final String XPLAN_50_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_0.xml";

	private static final String XPLAN_51_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_1.xml";

	private static final String XPLAN_52_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_5_2.xml";

	private static final String XPLAN_53_CODE_LISTS = "/codelists/XPlanGML_5_3_Enumerationen.xml";

	private static final String XPLAN_54_CODE_LISTS = "/codelists/XPlanGML_5_4_Enumerationen.xml";

	private static final String XPLAN_60_CODE_LISTS = "/codelists/XPlanGML_6_0_1_Enumerationen.xml";

	private static final String XPLAN_SYN_CODE_LISTS = "/appschemas/XPlanGML_Syn/XPlanSyn_CodeLists.xml";

	private static XPlanCodeLists xplanSynCodeLists;

	private static XPlanCodeLists xplan40CodeLists;

	private static XPlanCodeLists xplan41CodeLists;

	private static XPlanCodeLists xplan50CodeLists;

	private static XPlanCodeLists xplan51CodeLists;

	private static XPlanCodeLists xplan52CodeLists;

	private static XPlanCodeLists xplan53CodeLists;

	private static XPlanCodeLists xplan54CodeLists;

	private static XPlanCodeLists xplan60CodeLists;

	private static XPlanCodeListsParser xPlanCodeListParser = new XPlanCodeListsParser();

	private XPlanCodeListsFactory() {
	}

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
		case XPLAN_60:
			return getXPlan60();
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

	private static synchronized XPlanCodeLists getXPlan40() {
		if (xplan40CodeLists == null) {
			xplan40CodeLists = createGml30Codelist(XPLAN_40_CODE_LISTS);
		}
		return xplan40CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan41() {
		if (xplan41CodeLists == null) {
			xplan41CodeLists = createGml30Codelist(XPLAN_41_CODE_LISTS);
		}
		return xplan41CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan50() {
		if (xplan50CodeLists == null) {
			xplan50CodeLists = createGml30Codelist(XPLAN_50_CODE_LISTS);
		}
		return xplan50CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan51() {
		if (xplan51CodeLists == null) {
			xplan51CodeLists = createGml30Codelist(XPLAN_51_CODE_LISTS);
		}
		return xplan51CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan52() {
		if (xplan52CodeLists == null) {
			xplan52CodeLists = createGml30Codelist(XPLAN_52_CODE_LISTS);
		}
		return xplan52CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan53() {
		if (xplan53CodeLists == null) {
			xplan53CodeLists = createCodelist(XPLAN_53_CODE_LISTS);
		}
		return xplan53CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan54() {
		if (xplan54CodeLists == null) {
			xplan54CodeLists = createCodelist(XPLAN_54_CODE_LISTS);
		}
		return xplan54CodeLists;
	}

	private static synchronized XPlanCodeLists getXPlan60() {
		if (xplan60CodeLists == null) {
			xplan60CodeLists = createCodelist(XPLAN_60_CODE_LISTS);
		}
		return xplan60CodeLists;
	}

	private static XPlanCodeLists createCodelist(String xplanCodeList) {
		try {
			return xPlanCodeListParser.parseCodelists(XPlanCodeLists.class.getResource(xplanCodeList));
		}
		catch (Exception e) {
			String msg = "Internal error reading code lists file: " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
	}

	private static XPlanCodeLists createGml30Codelist(String xplanCodeList) {
		try {
			return xPlanCodeListParser.parseCodelists(XPlanCodeLists.class.getResource(xplanCodeList), GML_30);
		}
		catch (Exception e) {
			String msg = "Internal error reading code lists file: " + e.getMessage();
			throw new RuntimeException(msg, e);
		}
	}

}
