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

import org.deegree.commons.tom.ows.CodeType;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.dictionary.Definition;
import org.deegree.gml.dictionary.Dictionary;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;

import static org.deegree.gml.GMLVersion.GML_32;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodeListsParser {

	/**
	 * Erzeugt eine neue {@link XPlanCodeLists} Instanz, die durch das Einlesen des
	 * spezifizierten GML Dictionaries initialisiert wird. Die Codeliste muss in GML 3.2
	 * vorliegen.
	 * @param codeListUrl
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 * @throws IOException
	 */
	public XPlanCodeLists parseCodelists(URL codeListUrl)
			throws XMLStreamException, FactoryConfigurationError, IOException {
		return parseCodelists(codeListUrl, GML_32);
	}

	/**
	 * Erzeugt eine neue {@link XPlanCodeLists} Instanz, die durch das Einlesen des
	 * spezifizierten GML Dictionaries initialisiert wird. Die Codeliste muss in der
	 * uebergebenen GML Version vorliegen.
	 * @param codeListUrl
	 * @param gmlVersion gml version of the dictionary, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 * @throws IOException
	 */
	public XPlanCodeLists parseCodelists(URL codeListUrl, GMLVersion gmlVersion)
			throws XMLStreamException, FactoryConfigurationError, IOException {
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, codeListUrl);
		Dictionary codeLists = gmlStream.readDictionary();
		gmlStream.close();
		return parseGmlCodelist(codeListUrl, codeLists);
	}

	private XPlanCodeLists parseGmlCodelist(URL codeListUrl, Dictionary codeLists) {
		XPlanCodeLists xPlanCodeLists = new XPlanCodeLists();
		if (!codeLists.isEmpty()) {
			Definition firstDefinition = codeLists.get(0);
			if (firstDefinition instanceof Dictionary) {
				for (Definition codeListDef : codeLists) {
					parseDictionary(codeListUrl, (Dictionary) codeListDef, xPlanCodeLists);
				}
			}
			else {
				parseDictionary(codeListUrl, codeLists, xPlanCodeLists);
			}
		}
		return xPlanCodeLists;
	}

	private void parseDictionary(URL codeListUrl, Dictionary codeListDef, XPlanCodeLists xPlanCodeLists) {
		Dictionary codeListDict = codeListDef;
		String codeListId = codeListDict.getId();
		if (xPlanCodeLists.hasCodeList(codeListId)) {
			String msg = "CodeList '" + codeListId + "' ist in Dictionary '" + codeListUrl + "' doppelt vorhanden.";
			throw new RuntimeException(msg);
		}
		if (codeListDict.isEmpty()) {
			xPlanCodeLists.addNewCodeList(codeListId);
		}
		for (Definition def : codeListDict) {
			parseDefinition(codeListUrl, codeListId, def, xPlanCodeLists);
		}
	}

	private void parseDefinition(URL codeListUrl, String codeListId, Definition def, XPlanCodeLists xPlanCodeLists) {
		if (def.getNames().length > 0) {
			String name = getNameWithoutCodeSpaceOrFirst(def);
			if (codeIsPartOfIdentifier(codeListId, def)) {
				String codeWithCodelistId = def.getGMLProperties().getIdentifier().getCode();
				String code = codeWithCodelistId.substring(codeWithCodelistId.indexOf(":") + 1);
				String lesbarerName = getName(def, "lesbarerName");
				String kuerzel = getName(def, "kuerzel");
				xPlanCodeLists.addNewCodeEntry(codeListId, code, name, lesbarerName, kuerzel);
			}
			else if (def.getDescription() != null) {
				String description = def.getDescription().getString();
				if (def.getNames() == null || def.getNames().length != 1) {
					String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
							+ "' definiert mehrerere Codes für '" + description + "'.";
					throw new RuntimeException(msg);
				}
				xPlanCodeLists.addNewCodeEntry(codeListId, name, description);
			}
			else {
				// no description -> treat
				xPlanCodeLists.addNewCodeEntry(codeListId, name, name);
			}
		}
		else {
			String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
					+ "' enthält Einträge ohne Code.";
			throw new RuntimeException(msg);
		}
	}

	private String getNameWithoutCodeSpaceOrFirst(Definition def) {
		CodeType[] names = def.getNames();
		for (CodeType name : names) {
			if (name.getCodeSpace() == null)
				return name.getCode();
		}
		return names[0].getCode();
	}

	private String getName(Definition def, String codeSpace) {
		CodeType[] names = def.getNames();
		for (CodeType name : names) {
			if (codeSpace.equals(name.getCodeSpace()) || (codeSpace == null && name.getCodeSpace() == null))
				return name.getCode();
		}
		return null;
	}

	private boolean codeIsPartOfIdentifier(String codeListId, Definition def) {
		CodeType identifier = def.getGMLProperties().getIdentifier();
		return identifier != null && identifier.getCode().startsWith(codeListId);
	}

}
