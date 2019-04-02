package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadataBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDescription;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanTransactionManager {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanTransactionManager.class );

    private static final DateFormat DATEFORMAT = createDateFormat();

    protected final XPlanSynthesizer xPlanSynthesizer;

    protected final XPlanGmlTransformer xPlanGmlTransformer;

    protected final XPlanDao xplanDao;

    protected final XPlanExporter xPlanExporter;

    protected final XPlanRasterManager xPlanRasterManager;

    protected final WorkspaceReloader workspaceReloader;

    protected final ManagerConfiguration managerConfiguration;

    protected final ManagerWorkspaceWrapper managerWorkspaceWrapper;

    protected final SortPropertyReader sortPropertyReader;

    protected final XPlanManipulator planModifier = new XPlanManipulator();

    protected final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

    public XPlanTransactionManager( XPlanSynthesizer xPlanSynthesizer, XPlanGmlTransformer xPlanGmlTransformer,
                                    XPlanDao xplanDao, XPlanExporter xPlanExporter,
                                    XPlanRasterManager xPlanRasterManager, WorkspaceReloader workspaceReloader,
                                    ManagerConfiguration managerConfiguration,
                                    ManagerWorkspaceWrapper managerWorkspaceWrapper,
                                    SortPropertyReader sortPropertyReader ) {
        this.xPlanSynthesizer = xPlanSynthesizer;
        this.xPlanGmlTransformer = xPlanGmlTransformer;
        this.xplanDao = xplanDao;
        this.xPlanExporter = xPlanExporter;
        this.xPlanRasterManager = xPlanRasterManager;
        this.workspaceReloader = workspaceReloader;
        this.managerConfiguration = managerConfiguration;
        this.managerWorkspaceWrapper = managerWorkspaceWrapper;
        this.sortPropertyReader = sortPropertyReader;
    }

    protected void reloadWorkspace() {
        if ( workspaceReloader != null ) {
            WorkspaceReloaderConfiguration configuration = managerConfiguration.getWorkspaceReloaderConfiguration();
            workspaceReloader.reloadWorkspace( configuration );
        }
    }

    protected FeatureCollection createSynFeatures( XPlanFeatureCollection fc, XPlanVersion version ) {
        FeatureCollection synFc;
        long begin = System.currentTimeMillis();
        LOG.info( "- Erzeugen der XPlan-Syn Features..." );
        synFc = xPlanSynthesizer.synthesize( version, fc );
        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [" + elapsed + " ms]" );
        return synFc;
    }

    protected XPlanFeatureCollection createXPlanFeatureCollection( TransformationResult transformationResult,
                                                                   XPlanType type, XPlanAde ade )
                    throws Exception {
        byte[] resultAsBytes = transformationResult.getTransformationResult();
        XPlanVersion resultVersion = transformationResult.getVersionOfTheResult();
        try ( InputStream inputStream = new ByteArrayInputStream( resultAsBytes ) ) {
            AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema( resultVersion, ade );
            return XPlanFeatureCollectionUtils.parseXPlanFeatureCollection( inputStream, type, resultVersion,
                                                                            appSchema );
        }
    }

    protected ValidatorResult validateSyntactically( TransformationResult transformationResult, XPlanAde ade )
                    throws IOException {
        byte[] transformedPlan = transformationResult.getTransformationResult();
        try ( InputStream is = new ByteArrayInputStream( transformedPlan ) ) {
            XPlanVersion version = transformationResult.getVersionOfTheResult();
            return new SyntacticValidatorImpl().validateSyntax( is, version, ade );
        }
    }

    protected List<String> createRasterConfiguration( XPlanArchiveContentAccess archive, XPlanFeatureCollection fc,
                                                      int planId, XPlanType type, PlanStatus planStatus,
                                                      PlanStatus newPlanStatus, Date sortDate )
                    throws SQLException {
        String moreRecentPlanId = null;
        if ( sortDate != null ) {
            moreRecentPlanId = xplanDao.getPlanIdOfMoreRecentRasterPlan( sortDate );
        }
        return xPlanRasterManager.updateWmsWorkspaceWithRasterLayers( archive, fc, planId, moreRecentPlanId, type,
                                                                      planStatus, newPlanStatus, sortDate );
    }

    protected byte[] createXPlanGml( XPlanVersion version, FeatureCollection plan )
                    throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String comment = "Zuletzt aktualisiert am: " + DATEFORMAT.format( new Date() );
        xPlanExporter.export( bos, version, plan, comment );
        return bos.toByteArray();
    }

    protected void reassignFids( XPlanFeatureCollection fc ) {
        for ( Feature f : fc.getFeatures() ) {
            String prefix = "XPLAN_" + f.getName().getLocalPart().toUpperCase() + "_";
            String uuid = UUID.randomUUID().toString();
            f.setId( prefix + uuid );
        }
    }

    protected FeatureCollection renewFeatureCollection( XPlanVersion version, XPlanType type, AppSchema appSchema,
                                                        FeatureCollection modifiedFeatures )
                    throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        xPlanExporter.export( outputStream, version, modifiedFeatures, null );
        ByteArrayInputStream originalPlan = new ByteArrayInputStream( outputStream.toByteArray() );
        XMLStreamReader originalPlanAsXmlReader = XMLInputFactory.newInstance().createXMLStreamReader( originalPlan );
        try {
            return parseFeatureCollection( originalPlanAsXmlReader, version, appSchema );
        } finally {
            originalPlanAsXmlReader.close();
        }
    }

    protected void startCreationOfDataServicesCoupling( XPlanFeatureCollection featureCollection, ICRS crs ) {
        CoupledResourceConfiguration coupledResourceConfiguration = this.managerConfiguration.getCoupledResourceConfiguration();
        if ( coupledResourceConfiguration != null ) {
            LOG.info( "Start creation of the data services coupling." );
            PlanwerkServiceMetadata planwerkServiceMetadata = createPlanwerkServiceMetadata( featureCollection, crs,
                                                                                             coupledResourceConfiguration );
            DataServicesCouplingRunnable runnable = new DataServicesCouplingRunnable( coupledResourceConfiguration,
                                                                                      planwerkServiceMetadata );
            Thread thread = new Thread( runnable );
            thread.start();
        } else {
            LOG.info( "Creation of data services coupling is disabled." );
        }
    }

    private PlanwerkServiceMetadata createPlanwerkServiceMetadata( XPlanFeatureCollection featureCollection, ICRS crs,
                                                                   CoupledResourceConfiguration coupledResourceConfiguration ) {
        String title = featureCollection.getPlanName();
        String description = retrieveDescription( featureCollection.getFeatures(), featureCollection.getType() );
        Envelope envelope = featureCollection.getBboxIn4326();

        PlanwerkServiceMetadataBuilder builder = new PlanwerkServiceMetadataBuilder( XPlanType.BP_Plan, title,
                                                                                     description, envelope,
                                                                                     coupledResourceConfiguration );
        return builder.build( crs );
    }

    class DataServicesCouplingRunnable implements Runnable {

        private final Logger LOG = LoggerFactory.getLogger( DataServicesCouplingRunnable.class );

        private final CoupledResourceConfiguration configuration;

        private final PlanwerkServiceMetadata planwerkServiceMetadata;

        public DataServicesCouplingRunnable( CoupledResourceConfiguration configuration,
                                             PlanwerkServiceMetadata planwerkServiceMetadata ) {
            this.configuration = configuration;
            this.planwerkServiceMetadata = planwerkServiceMetadata;
        }

        @Override
        public void run() {
            try {
                MetadataCouplingHandler handler = new MetadataCouplingHandler( configuration );
                handler.processMetadataCoupling( planwerkServiceMetadata );
            } catch ( DataServiceCouplingException e ) {
                LOG.error( "Could not create data services coupling", e );
            }
        }

    }
    private static DateFormat createDateFormat() {
        DateFormat simpleDateFormat = new SimpleDateFormat( "dd MMM yyyy HH:mm:ss z" );
        simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "CET" ) );
        return simpleDateFormat;
    }

}