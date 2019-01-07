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
package de.latlon.xplan.update.from_pre1_0_to_1_0;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveAdditionalType;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.GeometryTransformer;
import org.deegree.geometry.io.WKTWriter;
import org.deegree.gml.GMLStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.update.AbstractUpdater;

/**
 * Updates the data from version pre 1.0 to 1.0.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class UpdaterFromPre1_0To1_0 extends AbstractUpdater {

    private final Logger LOG = LoggerFactory.getLogger( UpdaterFromPre1_0To1_0.class );

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    /**
     * @param xplanDao
     *            allows access to the database, never <code>null</code>
     */
    public UpdaterFromPre1_0To1_0( XPlanDao xplanDao ) {
        super( xplanDao );
    }

    @Override
    public void update( Connection conn )
                    throws Exception {
        List<XPlan> plans = xplanDao.getXPlanList( false );
        updateXplan3GmlObjectsTable( conn );
        updateXplanMgrPlansTable( plans, conn );
    }

    private void updateXplan3GmlObjectsTable( Connection conn ) {
        LOG.info( "Update xplan3 data!" );
        String updateSql = "UPDATE xplan3.gml_objects SET " + "plan_id=subquery.plan_id, "
                           + "plan_name=subquery.plan_name, " + "internal_id=subquery.internal_id, "
                           + "rechtsstand=subquery.rechtsstand " + "FROM ( " + " SELECT id,"
                           + "  (XPATH('/xplan:BP_Plan/xplan:nummer/text() | /xplan:BP_Bereich/xplan:nummer/text()', XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS plan_id, "
                           + "  (XPATH('/xplan:BP_Plan/xplan:name/text() | /xplan:BP_Bereich/xplan:name/text()', XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS plan_name,"
                           + "  (XPATH('/xplan:BP_Plan/xplan:internalId/text()', XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS internal_id,"
                           + "  (XPATH('//xplan:rechtsstand/text()', XMLPARSE(DOCUMENT binary_object), ARRAY[ARRAY['xplan', 'http://www.xplanung.de/xplangml/3/0']]))[1] AS rechtsstand"
                           + " FROM xplan3.gml_objects ) AS subquery WHERE xplan3.gml_objects.id=subquery.id;";
        LOG.info( updateSql );
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( updateSql );
            stmt.executeUpdate();
            conn.commit();
            LOG.info( "Update of xplan3 data finished!" );
        } catch ( SQLException e ) {
            LOG.error( "An exception occured during update of the xplan3 data", e );
            rollback( conn );
        } finally {
            closeQuietly( stmt );
        }

    }

    private void updateXplanMgrPlansTable( List<XPlan> plans, Connection conn )
                    throws Exception {
        for ( XPlan plan : plans ) {
            String id = plan.getId();
            LOG.info( "Update xplanmgr.plans table for plan with id {}", id );
            updateXPlanMgrTable( plan, conn );
            LOG.info( "Finished updated of xplanmgr.plans table for plan with id {}", id );
        }
    }

    private void updateXPlanMgrTable( XPlan plan, Connection conn )
                    throws Exception {
        try {
            int planId = Integer.parseInt( plan.getId() );
            XPlanType type = XPlanType.valueOf( plan.getType() );
            XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
            XPlanAde ade = plan.getAde() != null ? XPlanAde.valueOf( plan.getAde() ) : null;

            FeatureCollection featureCollection = createFeatureCollection( conn, planId, version, ade );

            XPlanFeatureCollection fc = new XPlanFeatureCollection( featureCollection, type );
            FeatureCollection synFc = xPlanSynthesizer.synthesize( version, fc );

            updatePlanInPlansTable( conn, type, version, fc, synFc, planId );
        } catch ( Exception e ) {
            LOG.warn( "Table xplanmgr.plans could not be updated for plan with ID {}! "
                      + "Plan will be ignored. insert plan manualy. Reason: {}", plan.getId(), e.getCause() );
            LOG.trace( "Plan could not be updated", e );
        }
    }

    private void updatePlanInPlansTable( Connection conn, XPlanType type, XPlanVersion version,
                                         XPlanFeatureCollection fc, FeatureCollection synFc, int planId ) {
        String updateSql = "UPDATE xplanmgr.plans SET " + "rechtsstand  = ?, " + "sonst_plan_art = ?, "
                           + "district = ?, " + "bbox = ST_GeometryFromText(?, 4326) WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement( updateSql );

            stmt.setString( 1, retrieveLegislationStatus( synFc, type ) );
            stmt.setString( 2, retrieveAdditionalType( synFc, type ) );
            stmt.setString( 3, retrieveDistrict( fc.getFeatures(), type, version ) );
            stmt.setString( 4, createWktFromTransformedEnvelope( fc ) );
            stmt.setInt( 5, planId );

            stmt.executeUpdate();
            conn.commit();
        } catch ( SQLException e ) {
            LOG.error( "Update of table xplanmgr.plans for plan with id " + planId + " failed.", e );
            rollback( conn );
        } finally {
            closeQuietly( stmt );
        }
    }

    private FeatureCollection createFeatureCollection( Connection conn, int planId, XPlanVersion version, XPlanAde ade )
                    throws Exception {
        InputStream xplan = null;
        XMLStreamReader xmlReader = null;
        try {
            xplan = xplanDao.retrieveXPlanArtefact( conn, planId );
            xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( xplan );
            GMLStreamReader gmlReader = createGMLStreamReader( version.getGmlVersion(), xmlReader );
            gmlReader.setApplicationSchema( XPlanSchemas.getInstance().getAppSchema( version, ade ) );
            FeatureCollection fc = gmlReader.readFeatureCollection();
            gmlReader.getIdContext().resolveLocalRefs();
            return fc;
        } finally {
            closeQuietly( xplan );
            if ( xmlReader != null )
                xmlReader.close();
        }
    }

    private String createWktFromTransformedEnvelope( XPlanFeatureCollection fc ) {
        try {
            Envelope envelope = fc.getFeatures().getEnvelope();
            GeometryTransformer geometryTransformer = new GeometryTransformer( "epsg:4326" );
            Envelope envelopeIn4326 = geometryTransformer.transform( envelope );
            return WKTWriter.write( envelopeIn4326 );
        } catch ( IllegalArgumentException | UnknownCRSException | TransformationException e ) {
            LOG.error( "Could not create transformed envelope" );
        }
        return null;
    }

}