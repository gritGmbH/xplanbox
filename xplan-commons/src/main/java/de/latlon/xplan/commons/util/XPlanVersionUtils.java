package de.latlon.xplan.commons.util;

import static de.latlon.xplan.commons.XPlanAde.NSM;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.deegree.commons.xml.NamespaceBindings;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanVersion;

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

    private static final String UNKNOWN_NAMESPACE = "Kann XPlan Version der Datei XPlan-GML-Datei nicht bestimmen. Unbekannter Namespace '%s'.";

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
        String namespaceURI = element.getNamespaceURI();
        try {
            return XPlanVersion.valueOfNamespace( namespaceURI );
        } catch ( IllegalArgumentException e ) {
            return determineVersionByAde( namespaceURI );
        } catch ( Exception e ) {
            String msg = "Kann kein XML-Wurzelelement in Datei XPlan-GML-Datei bestimmen. Keine XML Datei!?";
            throw new IllegalArgumentException( msg );
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