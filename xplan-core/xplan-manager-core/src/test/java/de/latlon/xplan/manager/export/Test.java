package de.latlon.xplan.manager.export;

import org.xmlmatchers.validation.SchemaFactory;

import java.io.File;
import java.net.URL;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Test {

    @org.junit.Test
    public void test() {
        System.out.println( "VORHER" );
        try {
            SchemaFactory.w3cXmlSchemaFrom(
                            new File( "/home/lyn/checkouts/xplanbox/xplanbox/xplan-resources/xplan-schemas/src/main/resources/appschemas/XPlanGML_4_1/XPlanung-Operationen.xsd" ) );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        System.out.println( "NACHHER" );
    }
}
