package de.latlon.xplan.manager.codelists;

import de.latlon.xplan.commons.XPlanVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */

@RunWith(Parameterized.class)
public class XPlanCodeListsFactoryTest {

    private XPlanVersion versionUnderTest;

    @Parameterized.Parameters
    public static List<XPlanVersion> xPlanVersions() {
        List<XPlanVersion> versions = new ArrayList<>();
        for ( XPlanVersion versionToAdd : XPlanVersion.values() )
            if ( !XPlanVersion.XPLAN_SYN.equals( versionToAdd ) )
                versions.add( versionToAdd );
        return versions;
    }

    public XPlanCodeListsFactoryTest( XPlanVersion versionUnderTest ) {
        this.versionUnderTest = versionUnderTest;
    }

    @Test
    public void testCreateSynFeatures() {
        XPlanCodeLists codeLists = XPlanCodeListsFactory.get( versionUnderTest );
        assertThat( codeLists, is( notNullValue() ) );
    }

}
