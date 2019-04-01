package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.CrsUtils;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import de.latlon.xplan.manager.metadata.MetadataCouplingHandler;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceManager;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.findWorkspaceDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanInsertManager extends XPlanTransactionManager {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanInsertManager.class );

    public XPlanInsertManager( XPlanSynthesizer xPlanSynthesizer, XPlanGmlTransformer xPlanGmlTransformer,
                               XPlanDao xplanDao, XPlanExporter xPlanExporter, XPlanRasterManager xPlanRasterManager,
                               WorkspaceReloader workspaceReloader, ManagerConfiguration managerConfiguration,
                               ManagerWorkspaceWrapper managerWorkspaceWrapper,
                               SortPropertyReader sortPropertyReader ) {
        super( xPlanSynthesizer, xPlanGmlTransformer, xplanDao, xPlanExporter, xPlanRasterManager, workspaceReloader,
               managerConfiguration, managerWorkspaceWrapper, sortPropertyReader );
    }

    /**
     * @param archive
     *                 to import, never <code>null</code>
     * @param defaultCRS
     *                 the default crs, may be <code>null</code> if no default crs should be set
     * @param force
     *                 should import be forced?
     * @param makeWMSConfig
     *                 <code>true</code> if the WMS configuration for the plan to import should be created,
     *                 <code>false</code> otherwise. To use this option it is required, that makeRasterConfig is
     *                 <code>true</code>
     * @param makeRasterConfig
     *                 <code>true</code> if the configuration of raster files should be created, <code>false</code> otherwise
     * @param workspaceFolder
     *                 workspace folder, may be <code>null</code> if default path should be used.
     * @param internalId
     *                 is added to the feature collection of the plan, if <code>null</code>, internalId property is not added
     *                 to the feature collection
     * @param xPlanMetadata
     *                 containing some metadata about the xplan, never <code>null</code>
     * @throws Exception
     */
    public void importPlan( XPlanArchive archive, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
                            boolean makeRasterConfig, File workspaceFolder, String internalId,
                            AdditionalPlanData xPlanMetadata )
                    throws Exception {
        LOG.info( "- Importiere Plan " + archive );
        ICRS crs = CrsUtils.determineActiveCrs( defaultCRS, archive, LOG );
        PlanStatus planStatus = xPlanMetadata.getPlanStatus();
        XPlanFeatureCollection fc = readAndValidateMainDocument( archive, crs, force, internalId, planStatus );
        FeatureCollection synFc = createSynFeatures( fc, archive.getVersion() );
        if ( internalId != null ) {
            AppSchema synSchema = managerWorkspaceWrapper.lookupStore( XPLAN_SYN, null, planStatus ).getSchema();
            featureCollectionManipulator.addInternalId( synFc, synSchema, internalId );
        }
        Date sortDate = sortPropertyReader.readSortDate( archive.getType(), archive.getVersion(), fc.getFeatures() );
        int planId = insertPlan( archive, xPlanMetadata, fc, synFc, sortDate );
        createRasterConfigurations( archive, makeWMSConfig, makeRasterConfig, workspaceFolder, fc, planId, planStatus,
                                    sortDate );
        reloadWorkspace();
        startCreationOfDataServicesCoupling( fc );
        LOG.info( "XPlan-Archiv wurde erfolgreich importiert. Zugewiesene Id: " + planId );
        LOG.info( "OK." );
    }

    private int insertPlan( XPlanArchive archive, AdditionalPlanData xPlanMetadata, XPlanFeatureCollection fc,
                            FeatureCollection synFc, Date sortDate )
                    throws Exception {
        if ( managerConfiguration.isProvidingXPlan41As51Active() && xPlanGmlTransformer != null ) {
            TransformationResult transformationResult = xPlanGmlTransformer.transform( fc );
            if ( transformationResult != null ) {
                ValidatorResult validatorResult = validateSyntactically( transformationResult, archive.getAde() );
                if ( validatorResult.isValid() ) {
                    XPlanFeatureCollection transformedXPlanFc = createXPlanFeatureCollection( transformationResult,
                                                                                              archive.getType(),
                                                                                              archive.getAde() );
                    return xplanDao.insert( archive, transformedXPlanFc, synFc, xPlanMetadata, sortDate );
                } else {
                    throw new Exception(
                                    "Transformation of the XPlanGML 4.1 plan to XPlanGml 5.1 results in syntactically invalid GML: "
                                    + validatorResult );
                }
            }
        }
        return xplanDao.insert( archive, fc, synFc, xPlanMetadata, sortDate );
    }

    private XPlanFeatureCollection readAndValidateMainDocument( XPlanArchive archive, ICRS crs, boolean force,
                                                                String internalId, PlanStatus planStatus )
                    throws Exception {
        performSchemaValidation( archive );
        try {
            GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
            AppSchema appSchema = managerWorkspaceWrapper.lookupStore( archive.getVersion(), archive.getAde(),
                                                                       planStatus ).getSchema();
            XPlanFeatureCollection fc = geometricValidator.retrieveGeometricallyValidXPlanFeatures( archive, crs,
                                                                                                    appSchema, force,
                                                                                                    internalId );
            reassignFids( fc );
            long begin = System.currentTimeMillis();
            new SyntacticValidatorImpl().validateReferences( archive, fc.getExternalReferenceInfo(), force );
            LOG.info( "- Überprüfung der externen Referenzen..." );
            long elapsed = System.currentTimeMillis() - begin;
            LOG.info( "OK [" + elapsed + " ms]" );
            return fc;
        } catch ( XMLStreamException | UnknownCRSException e ) {
            LOG.error( "Could not read and validate xplan gml", e );
            return null;
        }
    }

    private void createRasterConfigurations( XPlanArchive archive, boolean makeWMSConfig, boolean makeRasterConfig,
                                             File workspaceFolder, XPlanFeatureCollection fc, int planId,
                                             PlanStatus planStatus, Date sortDate )
                    throws Exception {
        if ( makeRasterConfig ) {
            List<String> rasterIds = createRasterConfiguration( archive, fc, planId, archive.getType(), planStatus,
                                                                null, sortDate );
            if ( makeWMSConfig ) {
                WmsWorkspaceManager wmsWorkspaceManager = new WmsWorkspaceManager(
                                findWorkspaceDirectory( workspaceFolder ) );
                wmsWorkspaceManager.updateWmsWorkspace( archive, planId, rasterIds, planStatus, fc.getBboxIn4326(),
                                                        managerConfiguration.getDefaultBboxIn4326() );
            }
        }
    }

    private void performSchemaValidation( XPlanArchive archive )
                    throws Exception {
        long begin = System.currentTimeMillis();
        LOG.info( "- Schema-Validierung (Hauptdokument)..." );
        SyntacticValidatorResult result;
        try {
            result = (SyntacticValidatorResult) new SyntacticValidatorImpl().validateSyntax( archive );
        } catch ( Exception e ) {
            throw new Exception( e.getMessage() );
        }

        long elapsed = System.currentTimeMillis() - begin;
        if ( result.isValid() ) {
            LOG.info( "OK [" + elapsed + " ms]." );
        } else {
            List<String> messages = result.getMessages();
            LOG.info( messages.size() + " Problem(e) gefunden [" + elapsed + " ms]" );
            for ( String message : messages ) {
                LOG.info( message );
            }
            throw new Exception( "Das Hauptdokument ist nicht schema-valide." );
        }
    }

    private void startCreationOfDataServicesCoupling( XPlanFeatureCollection featureCollection ) {
        CoupledResourceConfiguration coupledResourceConfiguration = this.managerConfiguration.getCoupledResourceConfiguration();
        if ( coupledResourceConfiguration != null ) {
            DataServicesCouplingRunnable runnable = new DataServicesCouplingRunnable( coupledResourceConfiguration,
                                                                                      featureCollection );
            Thread thread = new Thread( runnable );
            thread.start();
        }
    }

    class DataServicesCouplingRunnable implements Runnable {

        private final Logger LOG = LoggerFactory.getLogger( DataServicesCouplingRunnable.class );

        private final CoupledResourceConfiguration configuration;

        private final XPlanFeatureCollection featureCollection;

        public DataServicesCouplingRunnable( CoupledResourceConfiguration configuration,
                                             XPlanFeatureCollection featureCollection ) {
            this.configuration = configuration;
            this.featureCollection = featureCollection;
        }

        @Override
        public void run() {
            try {
                MetadataCouplingHandler handler = new MetadataCouplingHandler( configuration );
                handler.processMetadataCoupling( featureCollection );
            } catch ( DataServiceCouplingException e ) {
                LOG.error( "Could not create data services coupling", e );
            }
        }

    }

}