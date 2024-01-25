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
package de.latlon.xplan.manager.synthesizer.expression.dictionary;

import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.dictionary.XPlanDictionariesParser;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.deegree.feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Path;

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * {@link AbstractXPlanDictionaryLookup} for translating codes from external
 * {@link XPlanDictionaries} to their textual representation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanExternalCodeLookup extends AbstractXPlanDictionaryLookup {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanExternalCodeLookup.class);

	private final XPlanDictionariesParser xPlanDictionariesParser = new XPlanDictionariesParser();

	private final String codeListFile;

	private final Path configurationFilePath;

	/**
	 * @param exp to the property to translate, never <code>null</code>
	 * @param codeListFile relative path (from the rule configuration) to the file
	 * containing the code list, never <code>null</code>
	 * @param codeListName the name of the code list to use for the translation, may be
	 * <code>null</code> if the codelists contains only one codelist
	 * @param configurationDirectoryPath the absolute path to the directory containing the
	 * rules configuration, never <code>null</code>
	 */
	public XPlanExternalCodeLookup(Expression exp, String codeListFile, String codeListName,
			Path configurationDirectoryPath) {
		super(exp, codeListName);
		this.codeListFile = codeListFile;
		this.configurationFilePath = configurationDirectoryPath;
		LOG.info("Configured codelist: codeList {} {}from directory {}.", codeListFile,
				codeListName != null ? "(with name " + codeListName + ")" : "", configurationDirectoryPath);
	}

	@Override
	protected XPlanDictionaries getXPlanDictionaries(Feature feature) {
		if (configurationFilePath != null) {
			Path codeList = configurationFilePath.resolve(codeListFile);
			LOG.debug("Use configured codelist from {}.", codeList);
			try {
				return xPlanDictionariesParser.parseDictionaries(codeList.toUri().toURL(), GML_30);
			}
			catch (XMLStreamException | IOException e) {
				LOG.error("Could not parse code list " + codeList + ". Code will not be translated.", e);
			}
		}
		return null;
	}

}
