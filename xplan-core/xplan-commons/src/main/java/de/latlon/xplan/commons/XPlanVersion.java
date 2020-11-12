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

import static org.deegree.gml.GMLVersion.GML_30;
import static org.deegree.gml.GMLVersion.GML_31;
import static org.deegree.gml.GMLVersion.GML_32;

import java.net.URL;

import org.deegree.gml.GMLVersion;

/**
 * Enumeration for easy differentiating of XPlanGML versions in schemas and documents.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public enum XPlanVersion {

    /**
     * XPlan 3.x
     */
    XPLAN_3( "http://www.xplanung.de/xplangml/3/0", "/appschemas/XPlanGML_3_0/XPlanung-Operationen.xsd", GML_30 ),
    /**
     * XPlan 4.0
     */
    XPLAN_40( "http://www.xplanung.de/xplangml/4/0", "/appschemas/XPlanGML_4_0/XPlanung-Operationen.xsd", GML_32 ),
    /**
     * XPlan 4.1
     */
    XPLAN_41( "http://www.xplanung.de/xplangml/4/1", "/appschemas/XPlanGML_4_1/XPlanung-Operationen.xsd", GML_32 ),
    /**
     * XPlan 5.0
     */
    XPLAN_50( "http://www.xplanung.de/xplangml/5/0", "/appschemas/XPlanGML_5_0/XPlanung-Operationen.xsd", GML_32 ),
    /**
     * XPlan 5.1
     */
    XPLAN_51( "http://www.xplanung.de/xplangml/5/1", "/appschemas/XPlanGML_5_1/XPlanung-Operationen.xsd", GML_32 ),
    /**
     * XPlan 5.2
     */
    XPLAN_52( "http://www.xplanung.de/xplangml/5/2", "/appschemas/XPlanGML_5_2/XPlanung-Operationen.xsd", GML_32 ),
    /**
     * XPlan Syn
     */
    XPLAN_SYN( "http://www.deegree.org/xplanung/1/0", "/appschemas/XPlanGML_Syn/XPlanSyn.xsd", GML_32 );

    private final String namespace;

    private final URL schemaUrl;

    private final GMLVersion gmlVersion;

    XPlanVersion( String namespace, String schemaResourcePath, GMLVersion gmlVersion ) {
        this.namespace = namespace;
        schemaUrl = this.getClass().getResource( schemaResourcePath );
        this.gmlVersion = gmlVersion;
    }

    /**
     * Returns the XML namespace.
     * 
     * @return XML namespace, never <code>null</code>
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Returns the (local) URL of the XML schema.
     * 
     * @return URL of the XML schema, never <code>null</code>
     */
    public URL getSchemaUrl() {
        return schemaUrl;
    }

    /**
     * Returns the GML version that the schema is based upon.
     * 
     * @return GML version, never <code>null</code>
     */
    public GMLVersion getGmlVersion() {
        return gmlVersion;
    }

    /**
     * Determines the version for the given XPlanGML namespace.
     * 
     * @param ns
     *            XML namespace URL, can be <code>null</code>
     * @return corresponding version, never <code>null</code>
     * @throws IllegalArgumentException
     *             in case the namespace is not a known XPlanGML namespace
     */
    public static XPlanVersion valueOfNamespace( String ns ) {
        if ( !XPLAN_SYN.getNamespace().equals( ns ) ) {
            for ( XPlanVersion version : XPlanVersion.values() ) {
                if ( version.getNamespace().equals( ns ) ) {
                    return version;
                }
            }
        }
        throw new IllegalArgumentException( ns + " is not a known XPlanGML namespace." );
    }
}
