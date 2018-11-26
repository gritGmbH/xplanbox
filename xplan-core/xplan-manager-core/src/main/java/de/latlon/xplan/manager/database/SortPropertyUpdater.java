//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.database;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;

/**
 * Updates the sort property.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortPropertyUpdater {

    private static final Logger LOG = LoggerFactory.getLogger( SortPropertyUpdater.class );

    private final SortPropertyReader sortPropertyReader;

    private final XPlanDao dao;

    private final XPlanRasterManager xPlanRasterManager;

    /**
     * @param sortPropertyReader
     *            used to read the sort property from a feature collection, never <code>null</code>
     * @param dao
     *            used to access the database, never <code>null</code>
     * @param xPlanRasterManager
     *            used to update the raster configuration, never <code>null</code>
     */
    public SortPropertyUpdater( SortPropertyReader sortPropertyReader, XPlanDao dao,
                                XPlanRasterManager xPlanRasterManager ) {
        this.sortPropertyReader = sortPropertyReader;
        this.dao = dao;
        this.xPlanRasterManager = xPlanRasterManager;
    }

    /**
     * Retrieves all plans from the manager store, parses the date from the plan with the help of the
     * {@link SortPropertyReader} and updates the sort property in the syn schema data and reorders the wms
     * rasterlayers.
     */
    public void updateSortProperty()
                    throws Exception {
        Map<String, Date> planId2sortDate = updateColumnsInDB();
        updateWmsRasterLayerOrder( planId2sortDate );
    }

    private Map<String, Date> updateColumnsInDB()
                    throws Exception {
        Map<String, Date> planId2sortDate = new HashMap<String, Date>();
        List<XPlan> plans = dao.getXPlanList();
        for ( XPlan plan : plans ) {
            LOG.debug( "Update sort column value for plan with id {}", plan.getId() );
            FeatureCollection featureCollection = dao.retrieveFeatureCollection( plan );
            XPlanType planType = XPlanType.valueOf( plan.getType() );
            XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
            Date sortDate = sortPropertyReader.readSortDate( planType, version, featureCollection );
            planId2sortDate.put( plan.getId(), sortDate );
            dao.updateSortProperty( sortDate, plan );
        }
        return planId2sortDate;
    }

    private void updateWmsRasterLayerOrder( Map<String, Date> planId2sortDate )
                    throws Exception {
        xPlanRasterManager.reorderWmsLayers( planId2sortDate );
    }

}