package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertThat;

public class XplanSynthesizerXplan41Test extends AbstractXplanSynthesizerTest {

    @Override
    XPlanVersion getXPlanVersion() {
        return XPLAN_41;
    }

    @Test
    public void testBp2070()
                    throws Exception {
        createSynFeatures( "xplan41/BP2070.zip" );
    }

    @Test
    public void testBp2135()
                    throws Exception {
        createSynFeatures( "xplan41/BP2135.zip" );
    }

    @Test
    public void testDemo()
                    throws Exception {
        createSynFeatures( "xplan41/Demo.zip" );
    }

    @Test
    public void testEidelstedt4()
                    throws Exception {
        createSynFeatures( "xplan41/Eidelstedt_4_V4.zip" );
    }

    @Test
    public void testEidelstedt4ContainsPropertyHoehenangabe()
                    throws Exception {
        FeatureCollection features = createSynFeatures( "xplan41/Eidelstedt_4_V4.zip" );

        assertThat( features, hasFeature( "BP_BaugebietsTeilFlaeche" ) );
        assertThat( features, hasHoehenangabeProperty( "BP_BaugebietsTeilFlaeche",
                                                       "[hoehenbezug=absolutNHN;bezugspunkt=HBA;h=23;]" ) );
    }

    @Test
    public void testFplan()
                    throws Exception {
        createSynFeatures( "xplan41/FPlan.zip" );
    }

    @Test
    public void testHc7Bereich2()
                    throws Exception {
        createSynFeatures( "xplan41/hc7_bereich_2_V4.zip" );
    }

    @Test
    public void testLa22()
                    throws Exception {
        createSynFeatures( "xplan41/LA22.zip" );
    }

    @Test
    public void testLa67()
                    throws Exception {
        createSynFeatures( "xplan41/LA67.zip" );
    }

    @Test
    public void testId103()
                    throws Exception {
        createSynFeatures( "xplan41/V4_1_ID_103.zip" );
    }

    private Matcher<? super FeatureCollection> hasFeature( final String featureName ) {
        return new BaseMatcher<FeatureCollection>() {

            @Override
            public boolean matches( Object item ) {
                FeatureCollection features = (FeatureCollection) item;
                for ( Feature feature : features ) {
                    if ( featureName.equals( feature.getName().getLocalPart() ) )
                        return true;
                }
                return false;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Expect a feature with name " + featureName );
            }

        };
    }

    private Matcher<? super FeatureCollection> hasHoehenangabeProperty( final String featureName,
                                                                        final String expectedPropertyValue ) {
        return new BaseMatcher<FeatureCollection>() {
            private QName propName = new QName( "http://www.deegree.org/xplanung/1/0", "hoehenangabe" );

            @Override
            public boolean matches( Object item ) {
                FeatureCollection features = (FeatureCollection) item;
                List<Property> properties = findHoehenangabeProperties( features, featureName );
                if ( properties != null && !properties.isEmpty() ) {
                    String propertyValue = properties.get( 0 ).getValue().toString();
                    return expectedPropertyValue.equals( propertyValue );
                }
                return false;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Expect a feature with name " + featureName + " and property " + propName
                                        + " with value " + expectedPropertyValue );
            }

            private List<Property> findHoehenangabeProperties( FeatureCollection features, String featureName ) {
                for ( Feature feature : features ) {
                    if ( featureName.equals( feature.getName().getLocalPart() ) ) {
                        return feature.getProperties( propName );
                    }
                }
                return null;
            }

        };
    }

}