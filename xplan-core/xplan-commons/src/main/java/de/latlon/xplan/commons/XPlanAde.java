/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.commons;

import java.net.URL;

/**
 * Enumeration for easy differentiating of XPlanGML ADE extensions.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public enum XPlanAde {

	NSM("http://www.xplanung.de/4/1/ADE_NSM",
			"/appschemas/XPlanGML_4_1/XPlanung-Operationen_und_Regionalplanung_NSM_ADE.xsd");

	private final String namespace;

	private final URL schemaUrl;

	private XPlanAde(String namespace, String schemaResourcePath) {
		this.namespace = namespace;
		schemaUrl = this.getClass().getResource(schemaResourcePath);
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
	 * Determines the ade for the given namespace.
	 * @param ns namespace URL, never <code>null</code>
	 * @return corresponding ade, never <code>null</code>
	 * @throws IllegalArgumentException in case the namespace is not a known ade namespace
	 * or <code>null</code>
	 */
	public static XPlanAde valueOfNamespace(String ns) {
		if (NSM.getNamespace().equals(ns)) {
			return NSM;
		}
		throw new IllegalArgumentException(ns + " is not a known XPlanGML namespace.");
	}

}
