/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.archive.XPlanPartArchive;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfigurationAnalyser;
import de.latlon.xplan.manager.database.DatabaseCreator;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.SortPropertyUpdater;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.inspireplu.InspirePluPublisher;
import de.latlon.xplan.manager.jdbcconfig.JaxbJdbcConfigWriter;
import de.latlon.xplan.manager.jdbcconfig.JdbcConfigWriter;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanEditManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.EmptyStackException;
import java.util.List;

import static de.latlon.xplan.commons.feature.FeatureCollectionManipulator.removeAllFeaturesExceptOfPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils.parseXPlanFeatureCollection;
import static de.latlon.xplan.manager.edit.ExternalReferenceUtils.createExternalRefAddedOrUpdated;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static java.lang.Integer.parseInt;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * An instance of XPlanManager provides the service methods to manage instances of XPlan. Supports XPlan version 3,
 * 4.x, and 5.x.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanManager {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanManager.class );

    private static final LegislationStatus UNKNOWN_LEGISLATION_STATUS = new LegislationStatus( -1 );

    private final XPlanArchiveCreator archiveCreator;

    private final XPlanDao xplanDao;

    private final WorkspaceReloader workspaceReloader;

    private final DeegreeWorkspace managerWorkspace;

    private final XPlanExporter xPlanExporter;

    private final XPlanToEditFactory planToEditFactory = new XPlanToEditFactory();

    private final XPlanRasterManager xPlanRasterManager;

    private final SortPropertyReader sortPropertyReader;

    private final SortPropertyUpdater sortPropertyUpdater;

    private final InspirePluPublisher inspirePluPublisher;

    private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

    private final XPlanInsertManager xPlanInsertManager;

    private final XPlanEditManager xPlanEditManager;

    private final XPlanDeleteManager xPlanDeleteManager;

    /**
     * @param xPlanDao
     *                 mandatory XPlanDao
     * @param archiveCreator
     *                 archive creator
     * @param managerWorkspaceWrapper
     *                 manager workspace configuration
     * @param workspaceReloader
     *                 reloads a deegree workspace, if <code>null</code>, no workspace is reloaded
     * @param inspirePluTransformator
     *                 transforms XPlanGML to INSPIRE PLU, may be <code>null</code>
     * @param xPlanGmlTransformer
     *                 transforms between different versions of XPlanGML, may be <code>null</code>
     * @param wmsWorkspaceWrapper
     *                 wms workspace configuration
     * @throws Exception if mandatory arguments are missing or something went wrong
     */
    public XPlanManager( XPlanDao xPlanDao, XPlanArchiveCreator archiveCreator,
                         ManagerWorkspaceWrapper managerWorkspaceWrapper, WorkspaceReloader workspaceReloader,
                         InspirePluTransformator inspirePluTransformator, XPlanGmlTransformer xPlanGmlTransformer,
                         WmsWorkspaceWrapper wmsWorkspaceWrapper )
                    throws Exception {
        if (xPlanDao == null || archiveCreator == null || managerWorkspaceWrapper == null || wmsWorkspaceWrapper == null) {
            throw new IllegalArgumentException( "Mandatory argument must not be null" );
        }
        this.xplanDao = xPlanDao;
        this.archiveCreator = archiveCreator;
        this.managerWorkspace = managerWorkspaceWrapper.getWorkspace();
        this.managerWorkspaceWrapper = managerWorkspaceWrapper;
        this.workspaceReloader = workspaceReloader;

        ManagerConfigurationAnalyser managerConfigurationAnalyser = new ManagerConfigurationAnalyser(
                managerWorkspaceWrapper.getConfiguration(), wmsWorkspaceWrapper );
        managerConfigurationAnalyser.checkConfiguration();

        this.xPlanRasterManager = new XPlanRasterManager( wmsWorkspaceWrapper, managerWorkspaceWrapper.getConfiguration() );
        SortConfiguration sortConfiguration = createSortConfiguration( managerWorkspaceWrapper.getConfiguration() );
        this.sortPropertyReader = new SortPropertyReader( sortConfiguration );
        this.sortPropertyUpdater = new SortPropertyUpdater( sortPropertyReader, xplanDao, xPlanRasterManager );
        this.xPlanExporter = new XPlanExporter( managerWorkspaceWrapper.getConfiguration() );
        XPlanSynthesizer xPlanSynthesizer = createXPlanSynthesizer( managerWorkspaceWrapper.getConfiguration() );
        if ( inspirePluTransformator != null )
            this.inspirePluPublisher = new InspirePluPublisher( xplanDao, inspirePluTransformator );
        else
            this.inspirePluPublisher = null;
        this.xPlanInsertManager = new XPlanInsertManager( xPlanSynthesizer, xPlanGmlTransformer, xplanDao,
                                                          xPlanExporter, xPlanRasterManager, workspaceReloader,
                                                          managerWorkspaceWrapper.getConfiguration(),
                                                          managerWorkspaceWrapper, sortPropertyReader );
        this.xPlanEditManager = new XPlanEditManager( xPlanSynthesizer, xPlanGmlTransformer, xplanDao, xPlanExporter,
                                                      xPlanRasterManager, workspaceReloader,
                                                      managerWorkspaceWrapper.getConfiguration(), managerWorkspaceWrapper,
                                                      sortPropertyReader );
        this.xPlanDeleteManager = new XPlanDeleteManager( xplanDao, xPlanRasterManager, workspaceReloader,
                                                          managerWorkspaceWrapper.getConfiguration() );
    }

    public XPlanArchive analyzeArchive( String fileName )
                    throws IOException {
        LOG.info( "- Analyse des XPlanArchivs ('" + fileName + "')..." );
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
        if ( archive.hasMultipleXPlanElements() )
            throw new IllegalArgumentException( "Das XPlanGML enthält mehrere XP_Plan-Elemente." );
        xPlanInsertManager.importPlan( archive, defaultCRS, force, makeWMSConfig, makeRasterConfig, workspaceFolder,
                                       internalId, new AdditionalPlanData() );
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
        xPlanInsertManager.importPlan( archive, defaultCRS, force, makeWMSConfig, makeRasterConfig, null, internalId,
                                       xPlanMetadata );
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
        ICRS crs = CrsUtils.determineActiveCrs( CRSManager.getCRSRef( "EPSG:4326" ), archive, LOG );
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
     *         empty but never <code>null</code>
     * @throws IOException
     * @throws XMLStreamException
     * @throws UnknownCRSException
     * @throws XMLParsingException
     * @throws WorkspaceException
     */
    public List<RasterEvaluationResult> evaluateRasterdata( String pathToArchive )
                    throws IOException, XMLStreamException, XMLParsingException, UnknownCRSException {
        XPlanArchive archive = analyzeArchive( pathToArchive );
        XPlanFeatureCollection fc = parseXPlanFeatureCollection( archive );
        return xPlanRasterManager.evaluateRasterdata( archive, fc );
    }

    /**
     * @param pathToArchive
     *                 the absolute path to the XPlanArchive to evaluate.
     * @param status
     *                 the selected status, never <code>null</code>
     * @return the result of the evaluation, never <code>null</code>
     * @throws IOException
     * @throws XMLStreamException
     * @throws UnknownCRSException
     */
    public PlanNameWithStatusResult evaluatePlanNameAndStatus( String pathToArchive, String status )
                    throws IOException, XMLStreamException, UnknownCRSException {
        LOG.info( "- Analyse des Vorkommens eines Plans mit gleichem Namen und Planstatus..." );
        XPlanArchive archive = analyzeArchive( pathToArchive );
        XPlanFeatureCollection fc = parseXPlanFeatureCollection( archive );
        String planName = fc.getPlanName();
        boolean planWithSameNameAndStatusExists = xplanDao.checkIfPlanWithSameNameAndStatusExists( planName, status );
        LOG.info( "OK, Plan mit Namen '{}' und Status '{}' existiert: {}", planName, status,
                  planWithSameNameAndStatusExists );
        return new PlanNameWithStatusResult( planName, status, planWithSameNameAndStatusExists );
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
                    throws IOException, XMLParsingException {
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
        LOG.info( "XPlanArchiv {} wurde erfolgreich exportiert", planId );
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
            AppSchema appSchema = managerWorkspaceWrapper.lookupStore( version, ade, plan.getXplanMetadata().getPlanStatus() ).getSchema();
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
        xPlanEditManager.editPlan( oldXplan, xPlanToEdit, makeRasterConfig, uploadedArtefacts );
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
        xPlanDeleteManager.delete( planId, removeWMSConfig, workspaceFolder );
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

    private AppSchema getAppSchemaFromStore( XPlanArchive archive, PlanStatus planStatus ) {
        return managerWorkspaceWrapper.lookupStore( archive.getVersion(), archive.getAde(), planStatus ).getSchema();
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

    private SortConfiguration createSortConfiguration( ManagerConfiguration managerConfiguration ) {
        if ( managerConfiguration != null )
            return managerConfiguration.getSortConfiguration();
        return new SortConfiguration();
    }

    private XPlanSynthesizer createXPlanSynthesizer( ManagerConfiguration managerConfiguration ) {
        if ( managerConfiguration != null )
            return new XPlanSynthesizer( managerConfiguration.getSynthesizerConfigurationDirectory() );
        return new XPlanSynthesizer();
    }

}
