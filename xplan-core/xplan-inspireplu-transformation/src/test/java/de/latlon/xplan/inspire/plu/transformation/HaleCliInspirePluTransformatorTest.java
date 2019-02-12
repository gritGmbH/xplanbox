package de.latlon.xplan.inspire.plu.transformation;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import org.junit.Ignore;
import org.junit.Test;
import org.xmlmatchers.XmlMatchers;
import org.xmlmatchers.namespace.SimpleNamespaceContext;
import org.xmlmatchers.transform.XmlConverters;
import org.xmlmatchers.validation.SchemaFactory;

import javax.xml.namespace.NamespaceContext;
import javax.xml.transform.Source;
import javax.xml.validation.Schema;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Ignore
public class HaleCliInspirePluTransformatorTest {

    private final String testResource = "/tmp/Billstedt28/xplan.gml";

    private final String haleCli = "/tmp/hale/bin/hale";

    private final Path haleProjectDirectory = Paths.get( "/tmp/hale" );

    @Test
    public void testTransformationToPlu()
                    throws Exception {
        HaleCliInspirePluTransformator transformator = new HaleCliInspirePluTransformator( haleCli,
                                                                                           haleProjectDirectory );
        Path inspirePlu = transformator.transformToPlu( Paths.get( testResource ), XPLAN_41 );

        assertThat( inspirePlu, notNullValue() );
        assertThat( the( inspirePlu ), hasXPath( "//plu:SpatialPlan", nsContext() ) );
        assertThat( the( inspirePlu ), XmlMatchers.conformsTo( schema() ) );
    }

    private Source the( Path path )
                    throws Exception {
        InputStream is = new FileInputStream( path.toFile() );
        BufferedReader buf = new BufferedReader( new InputStreamReader( is ) );

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        while ( line != null ) {
            sb.append( line ).append( "\n" );
            line = buf.readLine();
        }
        return XmlConverters.the( sb.toString() );
    }

    private NamespaceContext nsContext() {
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext = nsContext.withBinding( "plu", "http://inspire.ec.europa.eu/schemas/plu/4.0" );
        return nsContext;
    }

    private Schema schema()
                    throws Exception {
        URL schemaUrl = new URL( "http://inspire.ec.europa.eu/schemas/plu/4.0/PlannedLandUse.xsd" );
        return SchemaFactory.w3cXmlSchemaFrom( schemaUrl );
    }

}
