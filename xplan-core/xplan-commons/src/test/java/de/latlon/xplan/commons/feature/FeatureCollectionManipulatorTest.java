package de.latlon.xplan.commons.feature;

import static de.latlon.xplan.commons.feature.FeatureCollectionManipulator.removeAllFeaturesExceptOfPlanFeature;
import static org.deegree.commons.tom.primitive.BaseType.STRING;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.feature.types.GenericFeatureType;
import org.deegree.feature.types.property.SimplePropertyType;
import org.deegree.gml.GMLStreamReader;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;

/**
 * Tests for {@link de.latlon.xplan.commons.feature.FeatureCollectionManipulator}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class FeatureCollectionManipulatorTest {

    private static final String NAMESPACE_URI = "http://www.xplanung.de/xplangml/4/1";

    private static final String FEATURE_NAME_BP_TEST = "BP_Test";

    private static final String FEATURE_NAME_BP_PLAN = "BP_Plan";

    private static final String FEATURE_NAME_TEST_PLAN = "Test_Plan";

    private FeatureCollection fcWithAllProperties;

    private FeatureCollection fcWithoutNameProperty;

    private FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

    @Before
    public void initialize() {
        fcWithAllProperties = new GenericFeatureCollection( "fc_id1", createFeaturesWithAllProperties() );
        fcWithoutNameProperty = new GenericFeatureCollection( "fc_id2", createFeaturesWithoutNameProperties() );
    }

    @Test
    public void testProcessAdditionOfInternalIdWithBpPlanFeatureShouldContainInternalId() {
        String internalId = "test_internal_id";
        featureCollectionManipulator.addInternalId( fcWithAllProperties, createSchema(), internalId );

        assertThat( fcWithAllProperties, hasNumberOfProperties( FEATURE_NAME_BP_PLAN, 7 ) );
        assertThat( fcWithAllProperties, hasProperty( FEATURE_NAME_BP_PLAN, "internalId", internalId ) );
    }

    @Test
    public void testProcessAdditionOfInternalIdWithTestBpFeaturesShouldNotContainInternalId() {
        String internalId = "test_internal_id";
        featureCollectionManipulator.addInternalId( fcWithAllProperties, createSchema(), internalId );

        assertThat( fcWithAllProperties, hasNumberOfProperties( FEATURE_NAME_BP_TEST, 6 ) );
        assertThat( fcWithAllProperties, hasNoProperty( FEATURE_NAME_BP_TEST, "internalId" ) );
    }

    @Test
    public void testProcessAdditionOfInternalIdWithBpTestShouldNotContainInternalId() {
        String internalId = "test_internal_id";
        featureCollectionManipulator.addInternalId( fcWithAllProperties, createSchema(), internalId );

        assertThat( fcWithAllProperties, hasNumberOfProperties( FEATURE_NAME_TEST_PLAN, 6 ) );
        assertThat( fcWithAllProperties, hasNoProperty( FEATURE_NAME_TEST_PLAN, "internalId" ) );
    }

    @Test
    public void testProcessAdditionOfInternalIdWithBpPlanFeatureShouldContainInternalIdOnCorrectPosition() {
        String propValue = "test_internal_id";
        featureCollectionManipulator.addInternalId( fcWithAllProperties, createSchema(), propValue );

        assertThat( fcWithAllProperties, hasProperty( FEATURE_NAME_BP_PLAN, "internalId", propValue, 2 ) );
    }

    @Test
    public
                    void
                    testProcessAdditionOfInternalIdWithBpPlanFeatureAndReducedPropertiesShouldContainInternalIdOnCorrectPosition() {
        String propValue = "test_internal_id";
        featureCollectionManipulator.addInternalId( fcWithoutNameProperty, createSchema(), propValue );

        assertThat( fcWithoutNameProperty, hasProperty( FEATURE_NAME_BP_PLAN, "internalId", propValue, 1 ) );
    }

    @Test
    public void testRemoveAllFeaturesExceptOfPlanFeatureWithXPlan41()
                    throws Exception {
        XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection( "xplan41/Eidelstedt_4_V4.zip" );
        removeAllFeaturesExceptOfPlanFeature( fc );
        FeatureCollection features = fc.getFeatures();
        QName nameOfFirstFeature = features.iterator().next().getName();

        assertThat( features.size(), is( 1 ) );
        assertThat( nameOfFirstFeature, is( new QName( "http://www.xplanung.de/xplangml/4/1", "BP_Plan" ) ) );
    }

    @Test
    public void testRemoveAllFeaturesExceptOfPlanFeatureWithXPlan3()
                    throws Exception {
        XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection( "xplan3/Plessa.zip" );
        removeAllFeaturesExceptOfPlanFeature( fc );
        FeatureCollection features = fc.getFeatures();
        QName nameOfFirstFeature = features.iterator().next().getName();

        assertThat( features.size(), is( 1 ) );
        assertThat( nameOfFirstFeature, is( new QName( "http://www.xplanung.de/xplangml/3/0", "BP_Plan" ) ) );
    }

    @Test
    public void testRemoveAllFeaturesExceptOfPlanFeatureWithXPlan2()
                    throws Exception {
        XPlanFeatureCollection fc = getMainFileAsXplanFeatureCollection( "xplan2/BP2070.zip" );
        removeAllFeaturesExceptOfPlanFeature( fc );
        FeatureCollection features = fc.getFeatures();
        QName nameOfFirstFeature = features.iterator().next().getName();

        assertThat( features.size(), is( 1 ) );
        assertThat( nameOfFirstFeature, is( new QName( "http://www.xplanung.de/xplangml", "BP_Plan" ) ) );
    }

    private ArrayList<Feature> createFeaturesWithAllProperties() {
        ArrayList<Property> properties = createProperties();
        return createFeatures( properties );
    }

    private ArrayList<Feature> createFeaturesWithoutNameProperties() {
        ArrayList<Property> properties = createProperties();
        properties.remove( 0 );
        return createFeatures( properties );
    }

    private ArrayList<Property> createProperties() {
        ArrayList<Property> properties = new ArrayList<Property>();
        createAndAddProperty( properties, "name", "test_name \"27\"" );
        createAndAddProperty( properties, "nummer", "test_nummer" );
        createAndAddProperty( properties, "beschreibung", "test_beschreibung" );
        createAndAddProperty( properties, "wurdeGeaendertVon", "test_wurdeGeaendertVon_1" );
        createAndAddProperty( properties, "wurdeGeaendertVon", "test_wurdeGeaendertVon_2" );
        createAndAddProperty( properties, "wurdeGeaendertVon", "test_wurdeGeaendertVon_3" );
        return properties;
    }

    private void createAndAddProperty( ArrayList<Property> properties, String propertyName, String propertyValue ) {
        QName qName = new QName( NAMESPACE_URI, propertyName );
        SimplePropertyType propertyType = new SimplePropertyType( qName, 0, 1, STRING, null, null );
        Property property = new SimpleProperty( propertyType, propertyValue );
        properties.add( property );
    }

    private ArrayList<Feature> createFeatures( ArrayList<Property> properties ) {
        ArrayList<Feature> features = new ArrayList<Feature>();
        createAndAddFeature( properties, features, FEATURE_NAME_BP_TEST, "feature1" );
        createAndAddFeature( properties, features, FEATURE_NAME_BP_PLAN, "feature2" );
        createAndAddFeature( properties, features, FEATURE_NAME_TEST_PLAN, "feature3" );
        return features;
    }

    private void createAndAddFeature( ArrayList<Property> properties, ArrayList<Feature> features, String name,
                                      String featureName ) {
        QName qName = new QName( NAMESPACE_URI, name );
        FeatureType ft = new GenericFeatureType( qName, new ArrayList<PropertyType>(), false );
        Feature feature = ft.newFeature( featureName, properties, null );
        features.add( feature );
    }

    private AppSchema createSchema() {
        return XPlanSchemas.getInstance().getAppSchema( XPlanVersion.XPLAN_41, null );
    }

    private XPlanFeatureCollection getMainFileAsXplanFeatureCollection( String name )
                    throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        XMLStreamReader xmlReader = archive.getMainFileXmlReader();
        GMLStreamReader gmlReader = createGMLStreamReader( version.getGmlVersion(), xmlReader );
        gmlReader.setApplicationSchema( XPlanSchemas.getInstance().getAppSchema( version, ade ) );
        FeatureCollection fc = gmlReader.readFeatureCollection();
        gmlReader.getIdContext().resolveLocalRefs();
        gmlReader.close();
        xmlReader.close();
        return new XPlanFeatureCollectionBuilder( fc, archive.getType() ).build();
    }

    private BaseMatcher<FeatureCollection> hasNumberOfProperties( final String featureName, final int noOfProperties ) {
        return new BaseMatcher<FeatureCollection>() {
            @Override
            public boolean matches( Object item ) {
                Iterator<Feature> iterator = ( (FeatureCollection) item ).iterator();
                while ( iterator.hasNext() ) {
                    Feature feature = iterator.next();
                    if ( feature.getType().getName().toString().contains( featureName ) ) {
                        return noOfProperties == feature.getProperties().size();
                    }
                }
                throw new IllegalArgumentException( "Feature with name " + featureName + " not found!" );
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Feature with name " + featureName + " should have exactly " + noOfProperties
                                        + " properties." );
            }
        };

    }

    private BaseMatcher<FeatureCollection> hasProperty( final String featureName, final String propertyName,
                                                        final String propertyValue ) {
        return hasProperty( featureName, propertyName, propertyValue, -1 );
    }

    private BaseMatcher<FeatureCollection> hasProperty( final String featureName, final String propertyName,
                                                        final String propertyValue, final int index ) {
        return new BaseMatcher<FeatureCollection>() {

            @Override
            public boolean matches( Object item ) {
                Iterator<Feature> iterator = ( (FeatureCollection) item ).iterator();
                while ( iterator.hasNext() ) {
                    Feature feature = iterator.next();
                    if ( feature.getType().getName().toString().contains( featureName ) ) {
                        int position = 0;
                        for ( Property property : feature.getProperties() ) {
                            String name = property.getName().getLocalPart();
                            if ( propertyName.equals( name ) && indexIsCorrect( index, position ) ) {
                                return propertyValue.equals( property.getValue().toString() );
                            }
                            position++;
                        }
                    }
                }
                return false;
            }

            private boolean indexIsCorrect( final int index, int position ) {
                return index == -1 || index == position;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Feature with name " + featureName + " should have a property with name "
                                        + propertyName + " and value " + propertyValue
                                        + "  at index (0 based, -1 means the index can be ignored) " + index );
            }
        };

    }

    private BaseMatcher<FeatureCollection> hasNoProperty( final String featureName, final String propertyName ) {
        return new BaseMatcher<FeatureCollection>() {

            @Override
            public boolean matches( Object item ) {
                Iterator<Feature> iterator = ( (FeatureCollection) item ).iterator();
                while ( iterator.hasNext() ) {
                    Feature feature = iterator.next();
                    if ( feature.getType().getName().toString().contains( featureName ) ) {
                        for ( Property property : feature.getProperties() ) {
                            String name = property.getName().getLocalPart();
                            if ( propertyName.equals( name ) ) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Feature with name " + featureName + " shouldn't have a property with name "
                                        + propertyName );
            }
        };

    }

}
