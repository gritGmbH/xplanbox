/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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
        XPlanArchive archive = getTestArchive( "xplan41/BP2070.zip" );
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
