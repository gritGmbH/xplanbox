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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.newDirectoryStream;

/**
 * Contains all configured codelists, parsed from <XPLANBOX_CONFIG>/synthesizer.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodelists {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanCodelists.class);

	private final XPlanDictionariesParser dictionariesParser = new XPlanDictionariesParser();

	private final Map<XPlanVersion, XPlanDictionaries> xPlanCodelists = new HashMap<>();

	/**
	 * Instantiates an empty XPlanCodelist instance.
	 */
	public XPlanCodelists() throws XMLStreamException, IOException {
		this(null);
	}

	/**
	 * @param synthesizerDir the path of the directory containing the xplan version
	 * directories with the external codelists, maybe <code>null</code> (nothing is
	 * parsed)
	 * @throws XMLStreamException
	 * @throws IOException
	 */
	public XPlanCodelists(Path synthesizerDir) throws XMLStreamException, IOException {
		if (synthesizerDir != null && isDirectory(synthesizerDir)) {
			parseCodelists(synthesizerDir);
		}
	}

	/**
	 * @param version the version of the codelist to return, never <code>null</code>
	 * @return the dictionaries with the passed version, may be <code>null</code> if not
	 * available
	 */
	public XPlanDictionaries getCodelists(XPlanVersion version) {
		return xPlanCodelists.get(version);
	}

	private void parseCodelists(Path synthesizerDir) throws XMLStreamException, IOException {
		try (DirectoryStream<Path> directoryStream = newDirectoryStream(synthesizerDir)) {
			for (Path path : directoryStream) {
				XPlanVersion version = isVersionDirectory(path);
				if (version != null) {
					parseCodelists(path, version);
				}
			}
		}
	}

	private void parseCodelists(Path versionDir, XPlanVersion version) throws IOException, XMLStreamException {
		try (DirectoryStream<Path> codelists = newDirectoryStream(versionDir,
				entry -> valueOf(entry.getFileName()).endsWith(".xml")
						|| valueOf(entry.getFileName()).endsWith(".gml"))) {
			for (Path codelist : codelists) {
				XPlanDictionaries xPlanDictionaries = dictionariesParser.parseDictionaries(codelist.toUri().toURL());
				if (xPlanDictionaries != null) {
					if (!xPlanCodelists.containsKey(version)) {
						xPlanCodelists.put(version, xPlanDictionaries);
					}
					else {
						xPlanCodelists.get(version).addDictionaries(xPlanDictionaries);
					}
				}
			}
		}
	}

	private XPlanVersion isVersionDirectory(Path path) {
		Path name = path.getFileName();
		String dirName = name.toFile().getName();
		try {
			return XPlanVersion.valueOfVersionDir(dirName);
		}
		catch (IllegalArgumentException e) {
			LOG.info("{} cannot be assigned to a known XPlanVersion", dirName);
		}
		return null;
	}

}
