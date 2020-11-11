package de.latlon.xplan.validator.geometric;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

import java.io.IOException;

import org.deegree.feature.types.AppSchema;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;

/**
 * Abstract tests for {@link de.latlon.xplan.validator.geometric.GeometricValidator}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public abstract class GeometricValidatorAbstractTest {

    @Test
    public void testRetrieveGeometricallyValidXPlanFeaturesWithNullInternalIdShouldNotFail()
                    throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4.zip" );
        AppSchema schema = XPlanSchemas.getInstance().getAppSchema( XPLAN_41, null );
        createGeometricValidator().retrieveGeometricallyValidXPlanFeatures( archive, archive.getCrs(), schema, false,
                                                                            null );
    }

    protected abstract GeometricValidator createGeometricValidator();

    private XPlanArchive getTestArchive( String name )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
    }

}