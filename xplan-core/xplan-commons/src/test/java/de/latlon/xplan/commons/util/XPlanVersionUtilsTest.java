package de.latlon.xplan.commons.util;

import static de.latlon.xplan.commons.XPlanAde.NSM;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

import javax.xml.namespace.QName;

import org.deegree.commons.xml.NamespaceBindings;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import de.latlon.xplan.commons.XPlanVersion;

public class XPlanVersionUtilsTest {

    @Test
    public void testDetermineBaseVersionFor3() {
        QName element = new QName( XPLAN_3.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_3 ) );
    }

    @Test
    public void testDetermineBaseVersionFor40() {
        QName element = new QName( XPLAN_40.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_40 ) );
    }

    @Test
    public void testDetermineBaseVersionFor41() {
        QName element = new QName( XPLAN_41.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_41 ) );
    }

    @Test
    public void testDetermineBaseVersionFor50() {
        QName element = new QName( XPLAN_50.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_50 ) );
    }

    @Test
    public void testDetermineBaseVersionFor51() {
        QName element = new QName( XPLAN_51.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_51 ) );
    }

    @Test
    public void testDetermineBaseVersionForNsmShouldReturnVersion41() {
        QName element = new QName( NSM.getNamespace(), "element" );
        XPlanVersion version = XPlanVersionUtils.determineBaseVersion( element );
        assertThat( version, is( XPLAN_41 ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDetermineBaseVersionForUnknownNamespaceShouldFail() {
        QName element = new QName( "http://unknown.namespaceuri.de", "element" );
        XPlanVersionUtils.determineBaseVersion( element );
    }

    @Test
    public void testRetrieveNamespaceBindingsFor3() {
        QName element = new QName( XPLAN_3.getNamespace(), "element" );
        NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings( element );
        assertThat( namespaceBindings, hasNamespace( XPLAN_3.getNamespace(), "xplan" ) );
        assertThat( namespaceBindings, hasNamespace( XPLAN_3.getGmlVersion().getNamespace(), "gml" ) );
    }

    @Test
    public void testRetrieveNamespaceBindingsFor40() {
        QName element = new QName( XPLAN_40.getNamespace(), "element" );
        NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings( element );
        assertThat( namespaceBindings, hasNamespace( XPLAN_40.getNamespace(), "xplan" ) );
        assertThat( namespaceBindings, hasNamespace( XPLAN_40.getGmlVersion().getNamespace(), "gml" ) );
    }

    @Test
    public void testRetrieveNamespaceBindingsFor41() {
        QName element = new QName( XPLAN_41.getNamespace(), "element" );
        NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings( element );
        assertThat( namespaceBindings, hasNamespace( XPLAN_41.getNamespace(), "xplan" ) );
        assertThat( namespaceBindings, hasNamespace( XPLAN_41.getGmlVersion().getNamespace(), "gml" ) );
        assertThat( namespaceBindings, hasNamespace( NSM.getNamespace(), "xplanNSM" ) );
    }

    @Test
    public void testRetrieveNamespaceBindingsFor50() {
        QName element = new QName( XPLAN_50.getNamespace(), "element" );
        NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings( element );
        assertThat( namespaceBindings, hasNamespace( XPLAN_50.getNamespace(), "xplan" ) );
        assertThat( namespaceBindings, hasNamespace( XPLAN_50.getGmlVersion().getNamespace(), "gml" ) );
    }

    @Test
    public void testRetrieveNamespaceBindingsForNsmShouldReturnVersion41() {
        QName element = new QName( NSM.getNamespace(), "element" );
        NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings( element );
        assertThat( namespaceBindings, hasNamespace( XPLAN_41.getNamespace(), "xplan" ) );
        assertThat( namespaceBindings, hasNamespace( XPLAN_41.getGmlVersion().getNamespace(), "gml" ) );
        assertThat( namespaceBindings, hasNamespace( NSM.getNamespace(), "xplanNSM" ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRetrieveNamespaceBindingsForUnknownNamespace() {
        QName element = new QName( "http://unknown.namespaceuri.de", "element" );
        XPlanVersionUtils.retrieveNamespaceBindings( element );
    }

    private Matcher<? super NamespaceBindings> hasNamespace( final String namespace, final String prefix ) {
        return new TypeSafeMatcher<NamespaceBindings>() {

            @Override
            protected boolean matchesSafely( NamespaceBindings bindings ) {
                Iterator<String> prefixes = bindings.getPrefixes();
                while ( prefixes.hasNext() ) {
                    if ( prefix.equals( prefixes.next() ) ) {
                        String namespaceURI = bindings.getNamespaceURI( prefix );
                        if ( namespace.equals( namespaceURI ) )
                            return true;
                    }
                }
                return false;
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "NamespaceBindings must contain namespace " + namespace + " with prefix "
                                        + prefix );
            }
        };
    }

}