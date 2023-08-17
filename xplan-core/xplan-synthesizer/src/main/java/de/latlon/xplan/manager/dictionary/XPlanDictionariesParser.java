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
public class XPlanDictionariesParser {

	/**
	 * Erzeugt eine neue {@link XPlanDictionaries} Instanz, die durch das Einlesen des
	 * spezifizierten GML Dictionaries initialisiert wird. Die Codeliste muss in GML 3.2
	 * vorliegen.
	 * @param dictionaryUrl
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 * @throws IOException
	 */
	public XPlanDictionaries parseDictionaries(URL dictionaryUrl)
			throws XMLStreamException, FactoryConfigurationError, IOException {
		return parseDictionaries(dictionaryUrl, GML_32);
	}

	/**
	 * Erzeugt eine neue {@link XPlanDictionaries} Instanz, die durch das Einlesen des
	 * spezifizierten GML Dictionaries initialisiert wird. Das Dictionary muss in der
	 * uebergebenen GML Version vorliegen.
	 * @param dictionaryUrl
	 * @param gmlVersion gml version of the dictionary, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws FactoryConfigurationError
	 * @throws IOException
	 */
	public XPlanDictionaries parseDictionaries(URL dictionaryUrl, GMLVersion gmlVersion)
			throws XMLStreamException, FactoryConfigurationError, IOException {
		GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader(gmlVersion, dictionaryUrl);
		Dictionary dictionary = gmlStream.readDictionary();
		gmlStream.close();
		return parseGmlDictionary(dictionaryUrl, dictionary);
	}

	private XPlanDictionaries parseGmlDictionary(URL dictionaryUrl, Dictionary dictionary) {
		XPlanDictionaries dictionaries = new XPlanDictionaries();
		if (!dictionary.isEmpty()) {
			Definition firstDefinition = dictionary.get(0);
			if (firstDefinition instanceof Dictionary) {
				for (Definition codeListDef : dictionary) {
					parseDictionary(dictionaryUrl, (Dictionary) codeListDef, dictionaries);
				}
			}
			else {
				parseDictionary(dictionaryUrl, dictionary, dictionaries);
			}
		}
		return dictionaries;
	}

	private void parseDictionary(URL dictionaryUrl, Dictionary codeListDef, XPlanDictionaries dictionaries) {
		Dictionary dictionary = codeListDef;
		String dictionaryId = dictionary.getId();
		if (dictionaries.hasDictionary(dictionaryId)) {
			String msg = "Dictionary mit der ID '" + dictionaryId + "' ist in Dictionary '" + dictionaryUrl
					+ "' doppelt vorhanden.";
			throw new RuntimeException(msg);
		}
		if (dictionary.isEmpty()) {
			dictionaries.addDictionary(dictionaryId);
		}
		for (Definition def : dictionary) {
			parseDefinition(dictionaryUrl, dictionaryId, def, dictionaries);
		}
	}

	private void parseDefinition(URL dictionaryUrl, String dictionaryId, Definition def,
			XPlanDictionaries dictionaries) {
		if (def.getNames().length > 0) {
			String name = getNameWithoutCodeSpaceOrFirst(def);
			if (codeIsPartOfIdentifier(dictionaryId, def)) {
				String codeWithDictionaryId = def.getGMLProperties().getIdentifier().getCode();
				String code = codeWithDictionaryId.substring(codeWithDictionaryId.indexOf(":") + 1);
				String lesbarerName = getName(def, "lesbarerName");
				String kuerzel = getName(def, "kuerzel");
				dictionaries.addDictionaryEntry(dictionaryId, code, name, lesbarerName, kuerzel);
			}
			else if (def.getDescription() != null) {
				String description = def.getDescription().getString();
				if (def.getNames() == null || def.getNames().length != 1) {
					String msg = "Dictionary '" + dictionaryId + "' in Dictionary '" + dictionaryUrl
							+ "' definiert mehrerere Codes für '" + description + "'.";
					throw new RuntimeException(msg);
				}
				dictionaries.addDictionaryEntry(dictionaryId, name, description);
			}
			else {
				// no description -> treat
				dictionaries.addDictionaryEntry(dictionaryId, name, name);
			}
		}
		else {
			String msg = "Dictionary '" + dictionaryId + "' in Dictionary '" + dictionaryUrl
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
