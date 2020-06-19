package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Geometry;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.deegree.gml.GMLVersion;
import org.junit.Test;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

public class XplanGeometryTest {

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        XPlanGeometry expr = new XPlanGeometry( new Xpath( "xplan:raeumlicherGeltungsbereich" ) );
        Geometry value = expr.evaluate( feature, features );
        assertNotNull( value );
    }

    @Test
    public void testEvaluateEmptyProperty() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "XP_PPO_3" );
        XPlanGeometry expr = new XPlanGeometry( new Xpath( "xplan:position" ) );
        Geometry value = expr.evaluate( feature, features );
        assertNull( value );
    }

    @Test
    public void testEvaluateCurve()
                            throws Exception {
        FeatureCollection features = getTestFeatures( XPLAN_41, "FeatureWithCurve.xml" );
        Feature feature = getTestFeature( features, "BP_BaugebietsTeilFlaeche" );

        XPlanGeometry expr = new XPlanGeometry( new Xpath( "xplan:position" ) );
        Geometry value = expr.evaluate( feature, features );
        assertNotNull( value );

        String geom = writeGMLGeometry( value );

        String xPath = "count(/gml:Polygon/gml:exterior/gml:Ring/gml:curveMember/gml:Curve/gml:segments/gml:LineStringSegment[@interpolation='linear'])";
        assertThat( the( geom ), hasXPath( xPath, nsContxt(), returningANumber(), is( 6d ) ) );

    }

    private NamespaceContext nsContxt() {
        NamespaceBindings nsContext = new NamespaceBindings();
        nsContext.addNamespace( "gml", GMLVersion.GML_32.getNamespace() );
        return nsContext;
    }

    private String writeGMLGeometry( Geometry value )
                            throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter xmlStreamWriter = new IndentingXMLStreamWriter(
                                XMLOutputFactory.newInstance().createXMLStreamWriter( bos ) );
        GMLStreamWriter gmlWriter = GMLOutputFactory.createGMLStreamWriter( GMLVersion.GML_32, xmlStreamWriter );
        gmlWriter.write( value );
        gmlWriter.close();
        xmlStreamWriter.close();
        bos.close();
        return bos.toString();
    }

}
