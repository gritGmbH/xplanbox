package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.synthesizer.XpPpoLookup;
import de.latlon.xplan.manager.synthesizer.XplanAbschnittLookup;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.closeQuietly;

public class TestFeaturesUtils {

    public static FeatureCollection getTestFeatures( XPlanVersion version ) {
        FeatureCollection fc = load( version );
        XpPpoLookup.init( fc );
        XplanAbschnittLookup.init( fc );
        return fc;
    }

    public static FeatureCollection getTestFeatures( XPlanVersion version, String resourceName ) {
        FeatureCollection fc = load( version, resourceName );
        XpPpoLookup.init( fc );
        XplanAbschnittLookup.init( fc );
        return fc;
    }

    public static Feature getTestFeature( FeatureCollection fc, String gmlId ) {
        for ( Feature f : fc ) {
            if ( gmlId.equals( f.getId() ) ) {
                return f;
            }
        }
        return null;
    }

    @SuppressWarnings("incomplete-switch")
    private static FeatureCollection load( XPlanVersion version ) {
        switch ( version ) {
        case XPLAN_3:
            return load( version, "xplan3.xml" );
        case XPLAN_40:
            return load( version, "xplan40.xml" );
        case XPLAN_41:
            return load( version, "xplan41.xml" );
        case XPLAN_50:
            return load( version, "xplan50.xml" );
        }
        throw new IllegalArgumentException();
    }

    private static FeatureCollection load( XPlanVersion version, String resource ) {
        InputStream is = null;
        XMLStreamReader xmlReader = null;
        GMLStreamReader gmlReader = null;
        try {
            is = TestFeaturesUtils.class.getResourceAsStream( resource );
            xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( is );
            gmlReader = GMLInputFactory.createGMLStreamReader( version.getGmlVersion(), xmlReader );
            gmlReader.setApplicationSchema( XPlanSchemas.getInstance().getAppSchema( version, null ) );
            FeatureCollection fc = gmlReader.readFeatureCollection();
            gmlReader.getIdContext().resolveLocalRefs();
            return fc;
        } catch ( Exception e ) {
            throw new RuntimeException( e.getMessage() );
        } finally {
            try {
                if ( gmlReader != null ) {
                    gmlReader.close();
                }
                if ( xmlReader != null ) {
                    xmlReader.close();
                }
            } catch ( Exception e ) {
                // nothing to do
            }
            closeQuietly( is );
        }
    }
}