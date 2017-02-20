//$HeadURL$
package de.latlon.xplan.manager.cli;

import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

import org.apache.xalan.Version;
import org.apache.xalan.xslt.EnvironmentCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanFeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class SystemLog {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanFeatureCollection.class );

    /**
     * Logs important stuff about the system, like java version, operating system, xalan and xerces version.
     */
    public static void log() {
        LOG.info( "" );
        LOG.info( "--------------------------------------------------------------------------------" );
        LOG.info( "System info" );
        LOG.info( "--------------------------------------------------------------------------------" );
        LOG.info( "" );
        LOG.info( "- java version       " + System.getProperty( "java.version" ) + " ("
                  + System.getProperty( "java.vendor" ) + ")" );
        LOG.info( "- operating system   " + System.getProperty( "os.name" ) + " (" + System.getProperty( "os.version" )
                  + ", " + System.getProperty( "os.arch" ) + ")" );
        LOG.info( "- system encoding    " + Charset.defaultCharset().displayName() );
        LOG.info( "- XMLOutputFactory   " + XMLOutputFactory.newInstance().getClass().getCanonicalName() );
        LOG.info( "- XMLInputFactory    " + XMLInputFactory.newInstance().getClass().getCanonicalName() );
        LOG.info( "- xalan environment " );
        LOG.info( "    - development version: " + Version.getDevelopmentVersionNum() );
        LOG.info( "    - implementation language: " + Version.getImplementationLanguage() );
        LOG.info( "    - maintenance version: " + Version.getMaintenanceVersionNum() );
        LOG.info( "    - major version: " + Version.getMajorVersionNum() );
        LOG.info( "    - product: " + Version.getProduct() );
        LOG.info( "    - release version: " + Version.getReleaseVersionNum() );
        LOG.info( "    - version: " + Version.getVersion() );
        PrintWriter pw = new PrintWriter( System.out );
        ( new EnvironmentCheck() ).checkEnvironment( pw );
        LOG.info( "- xerces environment " );
        LOG.info( "    - version: " + org.apache.xerces.impl.Version.getVersion() );
        logTransformer();

        LOG.info( "--------------------------------------------------------------------------------" );
    }

    private static void logTransformer() {
        try {
            LOG.info( "- transformer " );
            LOG.info( "    - system property (-Djava.xml.transform.TransformerFactory): '"
                      + System.getProperty( "java.xml.transform.TransformerFactory" ) + "'" );
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            LOG.info( "    - factory " + transformerFactory.getClass() );
            Transformer transformer = transformerFactory.newTransformer();
            LOG.info( "    - " + transformer.getClass() );
        } catch ( TransformerConfigurationException e ) {
            LOG.warn( "An error occured during creating a transformer instance, this may cause some "
                      + "problems during execution of the CLI." );
        }
    }

}