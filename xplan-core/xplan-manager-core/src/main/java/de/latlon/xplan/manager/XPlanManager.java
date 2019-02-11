package de.latlon.xplan.manager;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.archive.XPlanPartArchive;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.hale.TransformationException;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfigurationAnalyser;
import de.latlon.xplan.manager.database.DatabaseCreator;
import de.latlon.xplan.manager.database.SortPropertyUpdater;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.edit.XPlanManipulator;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.inspireplu.InspirePluPublisher;
import de.latlon.xplan.manager.jdbcconfig.JaxbJdbcConfigWriter;
import de.latlon.xplan.manager.jdbcconfig.JdbcConfigWriter;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceManager;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.commons.feature.FeatureCollectionManipulator.removeAllFeaturesExceptOfPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils.parseXPlanFeatureCollection;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.collectRemovedRefs;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefRemovedOrUpdated;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.findWorkspaceDirectory;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static java.lang.Integer.parseInt;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * An instance of XPlanManager provides the service methods to manage instances of XPlan. Supports XPLan version 2, 3,
 * 4.1, and 4.2.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanManager {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanManager.class );

    private static final LegislationStatus UNKNOWN_LEGISLATION_STATUS = new LegislationStatus( -1 );

    private static final DateFormat DATEFORMAT = createDateFormat();

    private final XPlanSynthesizer xPlanSynthesizer;

    private final XPlanArchiveCreator archiveCreator;

    private final XPlanDao xplanDao;

    private final ManagerConfiguration managerConfiguration;

    private final WorkspaceReloader workspaceReloader;

    private final DeegreeWorkspace managerWorkspace;

    private final XPlanGmlTransformer xPlanGmlTransformer;

    private final XPlanExporter xPlanExporter;

    private final XPlanToEditFactory planToEditFactory = new XPlanToEditFactory();

    private final XPlanManipulator planModifier = new XPlanManipulator();

    private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

    private final XPlanRasterManager xPlanRasterManager;

    private final SortPropertyReader sortPropertyReader;

    private final SortPropertyUpdater sortPropertyUpdater;

    private final InspirePluPublisher inspirePluPublisher;

    public XPlanManager() throws Exception {
        this( null, new XPlanArchiveCreator(), null, null );
    }

    /**
     * @param categoryMapper
     *            category mapper
     * @param archiveCreator
     *            archive creator
     * @param managerConfiguration
     *            manager configuration, may be <code>null</code>
     * @param workspaceReloader
     *            reloads a deegree workspace, if <code>null</code>, no workspace is reloaded
     * @throws Exception
     */
    public XPlanManager( CategoryMapper categoryMapper, XPlanArchiveCreator archiveCreator,
                         ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader )
                                                                                                         throws Exception {
        this( categoryMapper, archiveCreator, managerConfiguration, workspaceReloader, null, null );
    }

    /**
     * @param categoryMapper
     *                 category mapper
     * @param archiveCreator
     *                 archive creator
     * @param managerConfiguration
     *                 manager configuration, may be <code>null</code>
     * @param workspaceReloader
     *                 reloads a deegree workspace, if <code>null</code>, no workspace is reloaded
     * @param inspirePluTransformator
     *                 transforms XPlanGML to INSPIRE PLU, may be <code>null</code>
     * @param xPlanGmlTransformer
     *                 transforms between different versions of XPlanGML, may be <code>null</code>
     * @throws Exception
     */
    public XPlanManager( CategoryMapper categoryMapper, XPlanArchiveCreator archiveCreator,
                         ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
                         InspirePluTransformator inspirePluTransformator, XPlanGmlTransformer xPlanGmlTransformer )
                    throws Exception {
        this( categoryMapper, archiveCreator, managerConfiguration, null, null, workspaceReloader,
              inspirePluTransformator, xPlanGmlTransformer );
    }

    /**
     * @param categoryMapper
     *                 category mapper
     * @param archiveCreator
     *                 archive creator
     * @param managerConfiguration
     *                 manager configuration, may be <code>null</code>
     * @param workspaceDir
     *                 workspace directory
     * @param workspaceReloader
     *                 reloads a deegree workspace, if <code>null</code>, no workspace is reloaded
     * @param inspirePluTransformator
     *                 transforms XPlanGML to INSPIRE PLU, may be <code>null</code>
     * @param xPlanGmlTransformer
     *                 transforms between different versions of XPlanGML, may be <code>null</code>
     * @throws Exception
     */
    public XPlanManager( CategoryMapper categoryMapper, XPlanArchiveCreator archiveCreator,
                         ManagerConfiguration managerConfiguration, File workspaceDir, File wmsWorkspaceDir,
                         WorkspaceReloader workspaceReloader, InspirePluTransformator inspirePluTransformator,
                         XPlanGmlTransformer xPlanGmlTransformer )
                    throws Exception {
        this.archiveCreator = archiveCreator;
        this.managerConfiguration = managerConfiguration;
        this.managerWorkspace = instantiateManagerWorkspace( workspaceDir );
        this.xPlanGmlTransformer = xPlanGmlTransformer;
        this.xplanDao = new XPlanDao( managerWorkspace.getNewWorkspace(), categoryMapper, managerConfiguration );
        this.workspaceReloader = workspaceReloader;

        DeegreeWorkspaceWrapper wmsWorkspace = createWmsWorkspaceWrapper( wmsWorkspaceDir );
        WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper( wmsWorkspace.getWorkspaceInstance() );
        ManagerConfigurationAnalyser managerConfigurationAnalyser = new ManagerConfigurationAnalyser(
                        managerConfiguration, wmsWorkspaceWrapper );
        managerConfigurationAnalyser.checkConfiguration();
        this.xPlanRasterManager = new XPlanRasterManager( wmsWorkspaceWrapper, managerConfiguration );
        SortConfiguration sortConfiguration = createSortConfiguration( managerConfiguration );
        this.sortPropertyReader = new SortPropertyReader( sortConfiguration );
        this.sortPropertyUpdater = new SortPropertyUpdater( sortPropertyReader, xplanDao, xPlanRasterManager );
        this.xPlanExporter = new XPlanExporter( managerConfiguration );
        if ( managerConfiguration != null )
            this.xPlanSynthesizer = new XPlanSynthesizer( managerConfiguration.getConfigurationDirectory() );
        else
            this.xPlanSynthesizer = new XPlanSynthesizer();
        if ( inspirePluTransformator != null )
            this.inspirePluPublisher = new InspirePluPublisher( xplanDao, inspirePluTransformator );
        else
            this.inspirePluPublisher = null;
    }

    public XPlanArchive analyzeArchive( String fileName )
                    throws IOException {
        LOG.info( "- Analyse des XPlan-Archivs ('" + fileName + "')..." );
        XPlanArchive archive = archiveCreator.createXPlanArchive( new File( fileName ) );
        LOG.info( "OK. " + archive );
        return archive;
    }

    /**
     * Import a plan into the managed database
     *
     * @param archiveFileName
     *            the file name of the archive, never <code>null</code>
     * @param defaultCRS
     *            the default crs, may be <code>null</code> if no default crs should be set
     * @param force
     *            should import be forced?
     * @param makeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to import should be created,
     *            <code>false</code> otherwise. To use this option it is required, that makeRasterConfig is
     *            <code>true</code>
     * @param makeRasterConfig
     *            <code>true</code> if the configuration of raster files should be created, <code>false</code> otherwise
     * @param internalId
     *            is added to the feature collection of the plan, if <code>null</code>, internalId property is not added
     *            to the feature collection
     */
    public void importPlan( String archiveFileName, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
                            boolean makeRasterConfig, String internalId )
                    throws Exception {
        importPlan( archiveFileName, defaultCRS, force, makeWMSConfig, makeRasterConfig, null, internalId );
    }

    /**
     * Import a plan into the managed database
     *
     * @param archiveFileName
     *            the file name of the archive, never <code>null</code>
     * @param defaultCRS
     *            the default crs, may be <code>null</code> if no default crs should be set
     * @param force
     *            should import be forced?
     * @param makeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to import should be created,
     *            <code>false</code> otherwise. To use this option it is required, that makeRasterConfig is
     *            <code>true</code>
     * @param makeRasterConfig
     *            <code>true</code> if the configuration of raster files should be created, <code>false</code> otherwise
     * @param workspaceFolder
     *            workspace folder, may be <code>null</code> if default path should be used.
     * @param internalId
     *            is added to the feature collection of the plan, if <code>null</code>, internalId property is not added
     *            to the feature collection
     */
    public void importPlan( String archiveFileName, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
                            boolean makeRasterConfig, File workspaceFolder, String internalId )
                    throws Exception {
        XPlanArchive archive = analyzeArchive( archiveFileName );
        importPlan( archive, defaultCRS, force, makeWMSConfig, makeRasterConfig, workspaceFolder, internalId,
                    new AdditionalPlanData() );
    }

    /**
     * Import a plan into the managed database
     *
     * @param archive
     *            to import, never <code>null</code>
     * @param defaultCRS
     *            the default crs, may be <code>null</code> if no default crs should be set
     * @param force
     *            should import be forced?
     * @param makeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to import should be created,
     *            <code>false</code> otherwise. To use this option it is required, that makeRasterConfig is
     *            <code>true</code>
     * @param makeRasterConfig
     *            <code>true</code> if the configuration of raster files should be created, <code>false</code> otherwise
     * @param internalId
     *            is added to the feature collection of the plan, if <code>null</code>, internalId property is not added
     *            to the feature collection
     * @param xPlanMetadata
     *            containing some metadata about the xplan, never <code>null</code>
     */
    @PreAuthorize("hasPermission(#archive, 'hasDistrictPermission') or hasRole('ROLE_XPLAN_SUPERUSER')")
    public void importPlan( XPlanArchive archive, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
                            boolean makeRasterConfig, String internalId, AdditionalPlanData xPlanMetadata )
                    throws Exception {
        importPlan( archive, defaultCRS, force, makeWMSConfig, makeRasterConfig, null, internalId, xPlanMetadata );
    }

    /**
     *
     * @param archive
     *            to import, never <code>null</code>
     * @param defaultCRS
     *            the default crs, may be <code>null</code> if no default crs should be set
     * @param force
     *            should import be forced?
     * @param makeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to import should be created,
     *            <code>false</code> otherwise. To use this option it is required, that makeRasterConfig is
     *            <code>true</code>
     * @param makeRasterConfig
     *            <code>true</code> if the configuration of raster files should be created, <code>false</code> otherwise
     * @param workspaceFolder
     *            workspace folder, may be <code>null</code> if default path should be used.
     * @param internalId
     *            is added to the feature collection of the plan, if <code>null</code>, internalId property is not added
     *            to the feature collection
     * @param xPlanMetadata
     *            containing some metadata about the xplan, never <code>null</code>
     * @throws Exception
     */
    public void importPlan( XPlanArchive archive, ICRS defaultCRS, boolean force, boolean makeWMSConfig,
                            boolean makeRasterConfig, File workspaceFolder, String internalId,
                            AdditionalPlanData xPlanMetadata )
                    throws Exception {
        LOG.info( "- Importiere Plan " + archive );
        ICRS crs = determineActiveCrs( defaultCRS, archive );
        PlanStatus planStatus = xPlanMetadata.getPlanStatus();
        XPlanFeatureCollection fc = readAndValidateMainDocument( archive, crs, force, internalId, planStatus );
        FeatureCollection synFc = createSynFeatures( fc, archive.getVersion() );
        if ( internalId != null ) {
            AppSchema synSchema = xplanDao.lookupStore( XPLAN_SYN, null, planStatus ).getSchema();
            featureCollectionManipulator.addInternalId( synFc, synSchema, internalId );
        }
        Date sortDate = sortPropertyReader.readSortDate( archive.getType(), archive.getVersion(), fc.getFeatures() );
        int planId = insertPlan( archive, xPlanMetadata, fc, synFc, sortDate );
        createRasterConfigurations( archive, makeWMSConfig, makeRasterConfig, workspaceFolder, fc, planId, planStatus,
                                    sortDate );
        reloadWorkspace();
        LOG.info( "XPlan-Archiv wurde erfolgreich importiert. Zugewiesene Id: " + planId );
        LOG.info( "OK." );
    }

    /**
     * Retrieves plan name.
     *
     * @param archiveFileName
     *            the file name of the archive, never <code>null</code>
     *
     * @return plan name
     * @throws Exception
     */
    public String retrievePlanName( String archiveFileName )
                    throws Exception {
        // TODO: Simplify retrieval of plan name.
        XPlanArchive archive = analyzeArchive( archiveFileName );
        ICRS crs = determineActiveCrs( CRSManager.getCRSRef( "EPSG:4326" ), archive );
        XPlanFeatureCollection fc = ( new GeometricValidatorImpl() ).retrieveGeometricallyValidXPlanFeatures( archive,
                                                                                                              crs,
                                                                                                              getAppSchemaFromStore( archive,
                                                                                                                                     null ),
                                                                                                              true,
                                                                                                              null );
        return fc.getPlanName();
    }

    /**
     * Check if the crs is set in target file.
     *
     * @param archiveFileName
     *            path to the file
     * @return true if crs is set, false if crs is not set
     * @throws IOException
     */
    public boolean isCrsSet( String archiveFileName )
                    throws IOException {
        XPlanArchive archive = analyzeArchive( archiveFileName );
        return archive.getCrs() != null;
    }

    /**
     * Determines the legislation status of the plan referenced by the given path to the archive.
     *
     * @param pathToArchive
     *            path to the file, never <code>null</code>
     * @return the legislation status of a plan, code number is -1 if the status is not set
     * @throws IOException
     * @throws UnknownCRSException
     * @throws XMLStreamException
     */
    public LegislationStatus determineLegislationStatus( String pathToArchive )
                    throws IOException, XMLStreamException, UnknownCRSException {
        XPlanArchive archive = analyzeArchive( pathToArchive );
        XPlanFeatureCollection fc = parseXPlanFeatureCollection( archive );
        removeAllFeaturesExceptOfPlanFeature( fc );

        String legislationStatus = retrieveLegislationStatus( fc.getFeatures(), fc.getType() );
        if ( legislationStatus != null && !legislationStatus.isEmpty() ) {
            try {
                int legislationStatusCode = parseInt( legislationStatus );
                String legislationStatusTranslation = translateLegislationStatusCode( archive.getVersion(),
                                                                                      fc.getType(),
                                                                                      legislationStatusCode );
                return new LegislationStatus( legislationStatusCode, legislationStatusTranslation );
            } catch ( NumberFormatException e ) {
                LOG.info( "Legislationstatus '{}' could not be parsed as integer. Returning -1.", legislationStatus );
            }
        }
        return UNKNOWN_LEGISLATION_STATUS;
    }

    /**
     * @param pathToArchive
     *            the absolute path to the XPlanArchive to evaluate.
     * @return a list of {@link RasterEvaluationResult}, one for each referenced raster plan, may be
     *         {@link EmptyStackException} but never <code>null</code>
     * @throws IOException
     * @throws XMLStreamException
     * @throws UnknownCRSException
     * @throws XMLParsingException
     * @throws WorkspaceException
     */
    public List<RasterEvaluationResult> evaluateRasterdata( String pathToArchive )
                    throws IOException, XMLStreamException, XMLParsingException, UnknownCRSException,
                    WorkspaceException {
        XPlanArchive archive = analyzeArchive( pathToArchive );
        XPlanFeatureCollection fc = parseXPlanFeatureCollection( archive );
        return xPlanRasterManager.evaluateRasterdata( archive, fc );
    }

    /**
     * @param xPlanToEdit
     * @param uploadedArtefacts
     *            the absolute path to the XPlanArchive to evaluate.
     * @return a list of {@link RasterEvaluationResult}, one for each referenced raster plan, may be
     *         {@link EmptyStackException} but never <code>null</code>
     * @throws IOException
     * @throws XMLStreamException
     * @throws UnknownCRSException
     * @throws XMLParsingException
     * @throws WorkspaceException
     */
    public List<RasterEvaluationResult> evaluateRasterdata( XPlanToEdit xPlanToEdit, List<File> uploadedArtefacts )
                    throws IOException, XMLStreamException, XMLParsingException, UnknownCRSException,
                    WorkspaceException {
        XPlanArchiveContentAccess archive = new XPlanPartArchive( uploadedArtefacts );
        ExternalReferenceInfo externalReferenceInfoToUpdate = createExternalRefAddedOrUpdated( xPlanToEdit,
                                                                                               uploadedArtefacts );
        return xPlanRasterManager.evaluateRasterdata( archive, externalReferenceInfoToUpdate );
    }

    /**
     * Export a plan to the given stream
     *
     * @param planId
     *            plan id to export, never <code>null</code>
     * @param outputStream
     *            the stream to write the representation. must be closed afterwards
     * @throws Exception
     */
    public void export( String planId, OutputStream outputStream )
                    throws Exception {
        XPlanArchiveContent xplanContentsToExport = xplanDao.retrieveAllXPlanArtefacts( planId );
        xPlanExporter.export( outputStream, xplanContentsToExport );
        LOG.info( "XPlan-Archiv " + planId + " wurde erfolgreich exportiert" );
    }

    /**
     * Retrieve a list of all XPlans.
     *
     * @param includeNoOfFeature
     *                 <code>true</code> if the number of features of each feature collection should be requested, <code>false</code> otherwise
     * @return list of XPlans
     * @throws Exception
     */
    public List<XPlan> list( boolean includeNoOfFeature )
                    throws Exception {
        return xplanDao.getXPlanList( includeNoOfFeature );
    }

    /**
     * Retrieve a single {@link XPlan} by id.
     *
     * @param planId
     *            id of a plan, must not be <code>null</code>
     * @return a single plan
     * @throws Exception
     */
    public XPlan getXPlanById( int planId )
                    throws Exception {
        return xplanDao.getXPlanById( planId );
    }

    /**
     * Retrieve a {@link XPlanToEdit} by id.
     *
     * @param plan
     *            plan to be retrieved to edit, never <code>null</code>
     * @return the {@link XPlanToEdit}, <code>null</code> if not found
     * @throws Exception
     */
    @PreAuthorize("(hasPermission(#plan, 'hasDistrictPermission') and hasRole('ROLE_XPLAN_EDITOR')) or hasRole('ROLE_XPLAN_SUPERUSER')")
    public
                    XPlanToEdit getXPlanToEdit( XPlan plan )
                                    throws Exception {
        InputStream originalPlan = null;
        XMLStreamReader originalPlanAsXmlReader = null;
        try {
            XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
            XPlanAde ade = plan.getAde() != null ? XPlanAde.valueOf( plan.getAde() ) : null;
            AppSchema appSchema = xplanDao.lookupStore( version, ade, plan.getXplanMetadata().getPlanStatus() ).getSchema();
            originalPlan = xplanDao.retrieveXPlanArtefact( plan.getId() );
            originalPlanAsXmlReader = XMLInputFactory.newInstance().createXMLStreamReader( originalPlan );
            FeatureCollection originalPlanFC = parseFeatureCollection( originalPlanAsXmlReader, version, appSchema );
            return planToEditFactory.createXPlanToEdit( plan, originalPlanFC );
        } finally {
            closeQuietly( originalPlan );
            if ( originalPlanAsXmlReader != null )
                originalPlanAsXmlReader.close();
        }
    }

    /**
     * Modifies the plan with the passed ID.
     *
     * @param oldXplan
     *            the {@link XPlan} describing the plan before update, never <code>null</code>
     * @param xPlanToEdit
     *            the plan containing the edited field values, never <code>null</code>
     * @param makeRasterConfig
     *            <code>true</code> if the raster configuration should be created, <code>false</code> otherwise
     * @param uploadedArtefacts
     *            a list of artifacts uploaded by the user, may be empty but never <code>null</code>
     * @throws Exception
     */
    @PreAuthorize("(hasPermission(#oldXplan, 'hasDistrictPermission') and hasRole('ROLE_XPLAN_EDITOR')) or hasRole('ROLE_XPLAN_SUPERUSER')")
    public
                    void editPlan( XPlan oldXplan, XPlanToEdit xPlanToEdit, boolean makeRasterConfig,
                                   List<File> uploadedArtefacts )
                                    throws Exception {
        InputStream originalPlan = null;
        try {
            String planId = oldXplan.getId();
            LOG.info( "- Editiere Plan mit ID {}", planId );
            LOG.debug( "zu editierender Plan: {}", xPlanToEdit );
            XPlanVersion version = XPlanVersion.valueOf( oldXplan.getVersion() );
            XPlanType type = XPlanType.valueOf( oldXplan.getType() );
            XPlanAde ade = oldXplan.getAde() != null ? XPlanAde.valueOf( oldXplan.getAde() ) : null;
            PlanStatus oldPlanStatus = oldXplan.getXplanMetadata().getPlanStatus();
            AppSchema appSchema = xplanDao.lookupStore( version, ade, oldPlanStatus ).getSchema();
            originalPlan = xplanDao.retrieveXPlanArtefact( planId );
            XPlanFeatureCollection originalPlanFC = parseXPlanFeatureCollection( originalPlan, type,
                                                                                 version, ade, appSchema );
            String oldLegislationStatus = FeatureCollectionUtils.retrieveLegislationStatus( originalPlanFC.getFeatures(), type );
            FeatureCollection featuresToModify = originalPlanFC.getFeatures();
            ExternalReferenceInfo externalReferencesOriginal = new ExternalReferenceScanner().scan( featuresToModify );
            planModifier.modifyXPlan( featuresToModify, xPlanToEdit, version, type, appSchema );
            FeatureCollection modifiedFeatures = renewFeatureCollection( version, type, appSchema, featuresToModify );
            ExternalReferenceInfo externalReferencesModified = new ExternalReferenceScanner().scan( modifiedFeatures );

            byte[] xPlanGml = createXPlanGml( version, modifiedFeatures );
            ExternalReferenceInfo externalReferenceInfoToUpdate = createExternalRefAddedOrUpdated( externalReferencesModified,
                                                                                                   uploadedArtefacts );
            ExternalReferenceInfo externalReferenceInfoToRemove = createExternalRefRemovedOrUpdated( externalReferencesModified,
                                                                                                     uploadedArtefacts,
                                                                                                     externalReferencesOriginal );
            Set<String> removedRefs = collectRemovedRefs( externalReferencesModified, externalReferencesOriginal );
            XPlanFeatureCollection modifiedPlanFc = new XPlanFeatureCollection( modifiedFeatures, version, type, ade,
                                                                                externalReferenceInfoToUpdate );
            reassignFids( modifiedPlanFc );
            FeatureCollection synFc = createSynFeatures( modifiedPlanFc, version );
            String internalId = xplanDao.retrieveInternalId( planId, type );
            if ( internalId != null ) {
                AppSchema synSchema = xplanDao.lookupStore( XPLAN_SYN, null, oldPlanStatus ).getSchema();
                featureCollectionManipulator.addInternalId( synFc, synSchema, internalId );
            }

            // TODO: Validation required?
            PlanStatus newPlanStatus = detectNewPlanStatus( xPlanToEdit, oldLegislationStatus, oldPlanStatus );
            AdditionalPlanData xPlanMetadata = new AdditionalPlanData( newPlanStatus, xPlanToEdit.getValidityPeriod().getStart(),
                                                                       xPlanToEdit.getValidityPeriod().getEnd() );
            Date sortDate = sortPropertyReader.readSortDate( type, version, modifiedFeatures );
            xplanDao.update( oldXplan, xPlanMetadata, modifiedPlanFc, synFc, xPlanGml, xPlanToEdit, sortDate,
                             uploadedArtefacts, removedRefs );
            LOG.info( "XPlan-GML wurde erfolgreich editiert. ID: {}", planId );

            xPlanRasterManager.removeRasterLayers( planId, externalReferenceInfoToRemove );
            if ( makeRasterConfig ) {
                XPlanArchiveContentAccess archive = new XPlanPartArchive( uploadedArtefacts );
                createRasterConfiguration( archive, modifiedPlanFc, parseInt( planId ), BP_Plan, oldPlanStatus,
                                           newPlanStatus, sortDate );
                reloadWorkspace();
            } else {
                xPlanRasterManager.updateRasterLayers( parseInt( planId ), type, oldPlanStatus, newPlanStatus );
            }
            LOG.info( "Rasterkonfiguration für den Plan mit der ID {} wurde ausgetauscht (falls vorhanden).", planId );
        } finally {
            closeQuietly( originalPlan );
        }
    }

    /**
     * @param plan
     *            plan to delete
     * @throws Exception
     */
    @PreAuthorize("hasPermission(#plan, 'hasDistrictPermission') or hasRole('ROLE_XPLAN_SUPERUSER')")
    public void delete( XPlan plan )
                    throws Exception {
        String planId = plan.getId();
        delete( planId, false );
    }

    /**
     * @param planId
     *            the plan id to delete
     * @param removeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to delete should be removed,
     *            <code>false</code> otherwise
     * @throws Exception
     */
    public void delete( String planId, boolean removeWMSConfig )
                    throws Exception {
        delete( planId, removeWMSConfig, null );
    }

    /**
     * @param planId
     *            the plan id to delete
     * @param removeWMSConfig
     *            <code>true</code> if the WMS configuration for the plan to delete should be removed,
     *            <code>false</code> otherwise
     * @param workspaceFolder
     *            workspace folder, may be <code>null</code> if default path should be used.
     * @throws Exception
     */
    public void delete( String planId, boolean removeWMSConfig, File workspaceFolder )
                    throws Exception {
        xplanDao.deletePlan( planId );
        xPlanRasterManager.removeRasterLayers( planId );
        if ( removeWMSConfig ) {
            new WmsWorkspaceManager( findWorkspaceDirectory( workspaceFolder ) ).deleteWmsWorkspaceFilesForId( planId );
        }
        reloadWorkspace();
        LOG.info( "XPlan-Archiv " + planId + " wurde gelöscht" );
    }

    /**
     * @param jdbcConnection
     *            the JDBC connection string
     * @param dbName
     *            The name of the database which will be created
     * @param template
     *            The name of the PostGis-template
     * @param user
     *            user of the database
     * @param pw
     *            the password of the database
     * @throws ResourceInitException
     * @throws java.sql.SQLException
     */
    public void createInitialDB( String jdbcConnection, String dbName, String template, String user, String pw )
                    throws Exception {
        File wsDirectory = managerWorkspace.getLocation();

        writeJDBCFile( wsDirectory, jdbcConnection, dbName, user, pw );

        DatabaseCreator databaseCreator = new DatabaseCreator();
        databaseCreator.createInitialDB( jdbcConnection, dbName, template, user, pw, wsDirectory );

        LOG.info( "Datenbank " + dbName + " wurde erzeugt" );
    }

    /**
     * Update of wms sort columns in XPlan Syn datastores and reordering of the WMS layers.
     *
     * @throws Exception
     */
    public void updateWmsSortDate()
                    throws Exception {
        sortPropertyUpdater.updateSortProperty();
    }

    /**
     * Transforms the plans with the passed ID to INSPIRE PLU and imports them in the INSPIRE Download Service for PLU.
     *
     * @param plan
     *            plan to transform and publish
     */
    @PreAuthorize("hasPermission(#plan, 'hasDistrictPermission') or hasRole('ROLE_XPLAN_SUPERUSER')")
    public void publishPlu( XPlan plan )
                            throws Exception {
        if ( inspirePluPublisher == null ) {
            LOG.warn( "Transformation and publishing INSPIRE PLU datasets is not supported" );
            throw new Exception( "Transformation and publishing INSPIRE PLU datasets is not supported" );
        } else {
            String planId = plan.getId();
            inspirePluPublisher.transformAndPublish( planId, XPlanVersion.valueOf( plan.getVersion() ) );
            xplanDao.setPlanWasInspirePublished( planId );
        }
    }

    private int insertPlan( XPlanArchive archive, AdditionalPlanData xPlanMetadata, XPlanFeatureCollection fc,
                            FeatureCollection synFc, Date sortDate )
                    throws Exception {
        if ( xPlanGmlTransformer != null ) {
            try {
                XPlanFeatureCollection transformedFeatureCollection = xPlanGmlTransformer.transform( archive );
                if ( transformedFeatureCollection != null ) {
                    return xplanDao.insert( archive, transformedFeatureCollection, synFc, xPlanMetadata, sortDate );
                }
            } catch ( TransformationException e ) {
                LOG.warn( "Transformation of the XPlanGML 4.1 failed. The XPlanGml is inserted in the 4.1 data store. Failure: "
                          + e.getMessage() );
            }
        }
        return xplanDao.insert( archive, fc, synFc, xPlanMetadata, sortDate );
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

    private List<String> createRasterConfiguration( XPlanArchiveContentAccess archive, XPlanFeatureCollection fc,
                                                    int planId, XPlanType type, PlanStatus planStatus,
                                                    PlanStatus newPlanStatus, Date sortDate )
                    throws SQLException, WorkspaceException {
        String moreRecentPlanId = null;
        if ( sortDate != null ) {
            moreRecentPlanId = xplanDao.getPlanIdOfMoreRecentRasterPlan( sortDate );
        }
        return xPlanRasterManager.updateWmsWorkspaceWithRasterLayers( archive, fc, planId, moreRecentPlanId, type,
                                                                      planStatus, newPlanStatus, sortDate );
    }

    private byte[] createXPlanGml( XPlanVersion version, FeatureCollection plan )
                    throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String comment = "Zuletzt aktualisiert am: " + DATEFORMAT.format( new Date() );
        xPlanExporter.export( bos, version, plan, comment );
        return bos.toByteArray();
    }

    private void writeJDBCFile( File wsDirectory, String jdbcConnection, String dbName, String user, String pw )
                    throws Exception {

        try {
            File jdbcConnFile = new File( wsDirectory.toString() + "/jdbc/xplan.xml" );
            jdbcConnFile.createNewFile();

            OutputStream os = new FileOutputStream( jdbcConnFile );
            JdbcConfigWriter configWriter = new JaxbJdbcConfigWriter();
            configWriter.write( os, jdbcConnection, dbName, user, pw );

        } catch ( IOException e ) {
            throw new Exception( "Fehler beim Schreiben der Datei <" + wsDirectory.toString() + "/jdbc/xplan.xml>: "
                                 + e.getMessage() );
        }
    }

    private XPlanFeatureCollection readAndValidateMainDocument( XPlanArchive archive, ICRS crs, boolean force,
                                                                String internalId, PlanStatus planStatus )
                    throws Exception {
        performSchemaValidation( archive );
        try {
            GeometricValidatorImpl geometricValidator = new GeometricValidatorImpl();
            AppSchema appSchema = getAppSchemaFromStore( archive, planStatus );
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

    private void reassignFids( XPlanFeatureCollection fc ) {
        for ( Feature f : fc.getFeatures() ) {
            String prefix = "XPLAN_" + f.getName().getLocalPart().toUpperCase() + "_";
            String uuid = UUID.randomUUID().toString();
            f.setId( prefix + uuid );
        }
    }

    private FeatureCollection renewFeatureCollection( XPlanVersion version, XPlanType type, AppSchema appSchema,
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

    private AppSchema getAppSchemaFromStore( XPlanArchive archive, PlanStatus planStatus ) {
        return xplanDao.lookupStore( archive.getVersion(), archive.getAde(), planStatus ).getSchema();
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

    private ICRS determineActiveCrs( ICRS defaultCRS, XPlanArchive archive )
                    throws Exception {
        LOG.info( "- Überprüfung des räumlichen Bezugssystems..." );
        return determineCrs( defaultCRS, archive );
    }

    private ICRS determineCrs( ICRS defaultCRS, XPlanArchive archive )
                    throws Exception {
        ICRS crs = defaultCRS;
        if ( archive.getCrs() != null ) {
            crs = archive.getCrs();
            try {
                // called to force an UnknownCRSException
                CRSManager.lookup( crs.getName() );
                LOG.info( "OK, " + crs.getAlias() );
                crs = archive.getCrs();
            } catch ( UnknownCRSException e ) {
                if ( defaultCRS != null ) {
                    LOG.info( "OK" );
                    LOG.info( "Das im Dokument verwendete CRS '" + archive.getCrs().getName()
                              + "' ist unbekannt. Verwende benutzerspezifiziertes CRS '" + crs.getName() + "'." );
                } else {
                    throw new Exception( "Fehler: Das im Dokument verwendete CRS '" + archive.getCrs().getName()
                                         + "' ist unbekannt. Hinweis: Sie können das CRS als weiteren "
                                         + "Kommandozeilen-Parameter übergeben." );
                }
            }
        } else {
            if ( defaultCRS == null ) {
                throw new Exception(
                                "Fehler: Das Dokument enthält keine CRS-Informationen. Hinweis: Sie können das CRS als weiteren "
                                                + "Kommandozeilen-Parameter übergeben." );
            } else {
                LOG.info( "OK. Keine CRS-Informationen, verwende " + defaultCRS.getName() );
            }
        }
        return crs;
    }

    private FeatureCollection createSynFeatures( XPlanFeatureCollection fc, XPlanVersion version ) {
        FeatureCollection synFc;
        long begin = System.currentTimeMillis();
        LOG.info( "- Erzeugen der XPlan-Syn Features..." );
        synFc = xPlanSynthesizer.synthesize( version, fc );
        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [" + elapsed + " ms]" );
        return synFc;
    }

    private void reloadWorkspace() {
        if ( workspaceReloader != null ) {
            WorkspaceReloaderConfiguration configuration = managerConfiguration.getWorkspaceReloaderConfiguration();
            workspaceReloader.reloadWorkspace( configuration );
        }
    }

    private DeegreeWorkspace instantiateManagerWorkspace( File workspaceDir )
                    throws Exception {
        if ( workspaceDir != null )
            return instantiateWorkspace( workspaceDir );
        return instantiateWorkspace( DEFAULT_XPLAN_MANAGER_WORKSPACE );
    }

    private String translateLegislationStatusCode( XPlanVersion version, XPlanType type, int legislationStatusCode ) {
        XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get( version );
        String codeListId = findCodeListId( type );
        try {
            return xPlanCodeLists.getDescription( codeListId, Integer.toString( legislationStatusCode ) );
        } catch ( Exception e ) {
            LOG.error( "Could not translate rechtsstand code {}: {}", legislationStatusCode, e.getMessage() );
            LOG.trace( "Error translating rechtsstand code", e );
            return null;
        }
    }

    private String findCodeListId( XPlanType xPlanType ) {
        switch ( xPlanType ) {
        case BP_Plan:
            return "BP_Rechtsstand";
        case FP_Plan:
            return "FP_Rechtsstand";
        case LP_Plan:
            return "LP_Rechtsstand";
        case RP_Plan:
            return "RP_Rechtsstand";
        default:
            break;
        }
        return "XP_Rechtsstand";
    }

    private DeegreeWorkspaceWrapper createWmsWorkspaceWrapper( File wmsWorkspaceDir )
                    throws Exception {
        if ( wmsWorkspaceDir != null )
            return new DeegreeWorkspaceWrapper( wmsWorkspaceDir );
        return new DeegreeWorkspaceWrapper( DEFAULT_XPLANSYN_WMS_WORKSPACE );
    }

    private SortConfiguration createSortConfiguration( ManagerConfiguration managerConfiguration ) {
        if ( managerConfiguration != null )
            return managerConfiguration.getSortConfiguration();
        return new SortConfiguration();
    }

    private static DateFormat createDateFormat() {
        DateFormat simpleDateFormat = new SimpleDateFormat( "dd MMM yyyy HH:mm:ss z" );
        simpleDateFormat.setTimeZone( TimeZone.getTimeZone( "CET" ) );
        return simpleDateFormat;
    }

    private PlanStatus detectNewPlanStatus( XPlanToEdit xPlanToEdit, String oldLegislationStatus,
                                            PlanStatus oldPlanStatus ) {
        int newLegislationStatusCode = xPlanToEdit.getBaseData().getLegislationStatusCode();
        int oldLegislationStatusCode = -1;
        try {
            if ( oldLegislationStatus != null )
                oldLegislationStatusCode = Integer.parseInt( oldLegislationStatus );
        } catch ( NumberFormatException e ) {
            LOG.warn( "Could not parse legislation status code {} as integer", oldLegislationStatus );
        }
        if ( newLegislationStatusCode != oldLegislationStatusCode )
            return PlanStatus.findByLegislationStatusCode( newLegislationStatusCode );
        return oldPlanStatus;
    }
}