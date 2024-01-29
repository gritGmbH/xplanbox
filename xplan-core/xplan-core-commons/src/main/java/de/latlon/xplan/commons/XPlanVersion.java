/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons;

import org.deegree.gml.GMLVersion;

import java.net.URL;

import static org.deegree.gml.GMLVersion.GML_32;

/**
 * Enumeration for easy differentiating of XPlanGML versions in schemas and documents.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public enum XPlanVersion {

	/**
	 * XPlan 4.0
	 */
	XPLAN_40("http://www.xplanung.de/xplangml/4/0", "/appschemas/XPlanGML_4_0/XPlanung-Operationen.xsd", "xplan40.syn",
			"xplangml40", GML_32),
	/**
	 * XPlan 4.1
	 */
	XPLAN_41("http://www.xplanung.de/xplangml/4/1", "/appschemas/XPlanGML_4_1/XPlanung-Operationen.xsd", "xplan41.syn",
			"xplangml41", GML_32),
	/**
	 * XPlan 5.0
	 */
	XPLAN_50("http://www.xplanung.de/xplangml/5/0", "/appschemas/XPlanGML_5_0/XPlanung-Operationen.xsd", "xplan50.syn",
			"xplangml50", GML_32),
	/**
	 * XPlan 5.1
	 */
	XPLAN_51("http://www.xplanung.de/xplangml/5/1", "/appschemas/XPlanGML_5_1/XPlanung-Operationen.xsd", "xplan51.syn",
			"xplangml51", GML_32),
	/**
	 * XPlan 5.2
	 */
	XPLAN_52("http://www.xplanung.de/xplangml/5/2", "/appschemas/XPlanGML_5_2/XPlanung-Operationen.xsd", "xplan52.syn",
			"xplangml52", GML_32),
	/**
	 * XPlan 5.3
	 */
	XPLAN_53("http://www.xplanung.de/xplangml/5/3", "/appschemas/XPlanGML_5_3/XPlanung-Operationen.xsd", "xplan53.syn",
			"xplangml53", GML_32),
	/**
	 * XPlan 5.4
	 */
	XPLAN_54("http://www.xplanung.de/xplangml/5/4", "/appschemas/XPlanGML_5_4/XPlanung-Operationen.xsd", "xplan54.syn",
			"xplangml54", GML_32),
	/**
	 * XPlan 6.0
	 */
	XPLAN_60("http://www.xplanung.de/xplangml/6/0", "/appschemas/XPlanGML_6_0/XPlanung-Operationen.xsd", "xplan60.syn",
			"xplangml60", GML_32),
	/**
	 * XPlan Syn
	 */
	XPLAN_SYN("http://www.deegree.org/xplanung/1/0", "/appschemas/XPlanGML_Syn/XPlanSyn.xsd", null, null, GML_32);

	private final String namespace;

	private final URL schemaUrl;

	private final String synRulesFileName;

	private final String versionDir;

	private final GMLVersion gmlVersion;

	XPlanVersion(String namespace, String schemaResourcePath, String synRulesFileName, String versionDir,
			GMLVersion gmlVersion) {
		this.namespace = namespace;
		this.schemaUrl = this.getClass().getResource(schemaResourcePath);
		this.synRulesFileName = synRulesFileName;
		this.versionDir = versionDir;
		this.gmlVersion = gmlVersion;
	}

	/**
	 * Returns the XML namespace.
	 * @return XML namespace, never <code>null</code>
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * Returns the (local) URL of the XML schema.
	 * @return URL of the XML schema, never <code>null</code>
	 */
	public URL getSchemaUrl() {
		return schemaUrl;
	}

	/**
	 * Returns the name of the file containing the syn rules.
	 * @return name of the syn rules, <code>null</code> if no syn rules exists (XPLAN_SYN)
	 */
	public String getSynRulesFileName() {
		return synRulesFileName;
	}

	/**
	 * Returns the name of the directory containing specific configurations for this
	 * version
	 * @return name of the syn rules, <code>null</code> if no syn rules exists (XPLAN_SYN)
	 */
	public String getVersionDir() {
		return versionDir;
	}

	/**
	 * Returns the GML version that the schema is based upon.
	 * @return GML version, never <code>null</code>
	 */
	public GMLVersion getGmlVersion() {
		return gmlVersion;
	}

	/**
	 * Determines the version for the given XPlanGML namespace.
	 * @param ns XML namespace URL, can be <code>null</code>
	 * @return corresponding version, never <code>null</code>
	 * @throws IllegalArgumentException in case the namespace is not a known XPlanGML
	 * namespace
	 */
	public static XPlanVersion valueOfNamespace(String ns) {
		if (!XPLAN_SYN.getNamespace().equals(ns)) {
			for (XPlanVersion version : XPlanVersion.values()) {
				if (version.getNamespace().equals(ns)) {
					return version;
				}
			}
		}
		throw new IllegalArgumentException(ns + " is not a known XPlanGML namespace.");
	}

	/**
	 * Determines the version for the given version directory.
	 * @param versionDir the name of the version directory, never <code>null</code>
	 * @return corresponding version, never <code>null</code>
	 * @throws IllegalArgumentException in case the versionDir cannot be assigned to a
	 * XPlanGML version
	 */
	public static XPlanVersion valueOfVersionDir(String versionDir) {
		if (versionDir != null) {
			for (XPlanVersion version : XPlanVersion.values()) {
				if (version.getVersionDir() != null && version.getVersionDir().equals(versionDir)) {
					return version;
				}
			}
		}
		throw new IllegalArgumentException(versionDir + " is not a known XPlanGML version directory.");
	}

}
