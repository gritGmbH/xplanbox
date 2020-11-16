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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.commons.xml.NamespaceBindings;

import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanAde.NSM;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static java.lang.String.format;

/**
 * Utility class containing convenience methods regarding {@link XPlanVersion} and {@link XPlanAde}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 *
 * @version $Revision: $, $Date: $
 */
public final class XPlanVersionUtils {

    private static final Map<XPlanVersion, NamespaceBindings> versionToNsContext = new HashMap<XPlanVersion, NamespaceBindings>();

    private static final String UNKNOWN_NAMESPACE = "Kann Version der XPlanGML-Datei nicht bestimmen. Unbekannter Namespace '%s'.";

    static {
        for ( XPlanVersion version : XPlanVersion.values() ) {
            NamespaceBindings nsContext = new NamespaceBindings();
            nsContext.addNamespace( "xplan", version.getNamespace() );
            nsContext.addNamespace( "gml", version.getGmlVersion().getNamespace() );
            if ( XPLAN_41.equals( version ) )
                nsContext.addNamespace( "xplanNSM", NSM.getNamespace() );
            versionToNsContext.put( version, nsContext );
        }
    }

    private XPlanVersionUtils() {
    }

    /**
     * Determines the {@link XPlanVersion} of the passed element. If the passed element is in an ADE namespace the
     * corresponding base version is returned.
     *
     * @param element
     *            of the feature to determine the {@link XPlanVersion}, never <code>null</code>
     * @return the {@link XPlanVersion} of the element, never <code>null</code>
     * @throws IllegalArgumentException
     *             if an exception occurred
     */
    public static XPlanVersion determineBaseVersion( QName element ) {
        String namespaceURI;
        try {
            namespaceURI = element.getNamespaceURI();
        } catch ( Exception e ) {
            String msg = "Kann kein XML-Wurzelelement in XPlanGML-Datei bestimmen. Keine XML-Datei!?";
            throw new IllegalArgumentException( msg );
        }
        return determineBaseVersion( namespaceURI );
    }

    /**
     * Determines the {@link XPlanVersion} by the passed namespaceUri. If the passed namespaceUri is an ADE namespace the
     * corresponding base version is returned.
     *
     * @param namespaceURI
     *                 of the feature to determine the {@link XPlanVersion}, never <code>null</code>
     * @return the {@link XPlanVersion} of the element, never <code>null</code>
     * @throws IllegalArgumentException
     */
    public static XPlanVersion determineBaseVersion( String namespaceURI ) {
        try {
            return XPlanVersion.valueOfNamespace( namespaceURI );
        } catch ( IllegalArgumentException e ) {
            return determineVersionByAde( namespaceURI );
        }
    }

    /**
     * Collects all {@link NamespaceBindings} for the given element. If the passed element is in an ADE namespace the
     * {@link NamespaceBindings} for the corresponding base version is returned.
     *
     * @param element
     *            of the feature to retrieve the {@link NamespaceBindings}, never <code>null</code>
     * @return the corresponding {@link NamespaceBindings}, never <code>null</code>
     * @throws IllegalArgumentException
     *             if the version could not determined
     */
    public static NamespaceBindings retrieveNamespaceBindings( QName element ) {
        XPlanVersion baseVersion = determineBaseVersion( element );
        return versionToNsContext.get( baseVersion );
    }

    private static XPlanVersion determineVersionByAde( String namespaceURI ) {
        try {
            XPlanAde ade = XPlanAde.valueOfNamespace( namespaceURI );
            if ( NSM.equals( ade ) )
                return XPLAN_41;
        } catch ( IllegalArgumentException e ) {
            throw new IllegalArgumentException( format( UNKNOWN_NAMESPACE, namespaceURI ) );
        }
        throw new IllegalArgumentException( format( UNKNOWN_NAMESPACE, namespaceURI ) );
    }

}
