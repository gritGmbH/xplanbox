package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.manager.exception.InvalidPlan;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanName;
import de.latlon.xplanbox.api.manager.exception.UnsupportedParameterValue;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import org.apache.http.client.utils.URIBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.feature.FeatureCollectionManipulator.removeAllFeaturesExceptOfPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils.parseXPlanFeatureCollection;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class PlanHandler {

    private static final Logger LOG = getLogger( PlanHandler.class );

    private static final String DELETE_MSG = "Plan mit ID %s entfernt.";

    @Autowired
    private XPlanArchiveCreator archiveCreator;

    @Autowired
    private XPlanValidator xPlanValidator;

    @Autowired
    private XPlanInsertManager xPlanInsertManager;

    @Autowired
    private XPlanDeleteManager xPlanDeleteManager;

    @Autowired
    private XPlanDao xPlanDao;

    @Autowired
    private XPlanExporter xPlanExporter;

    @Autowired
    private ManagerConfiguration managerConfiguration;

    public XPlan importPlan( File uploadedPlan, String xFileName, ValidationSettings validationSettings,
                             String internalId, String planStatus )
                            throws Exception {
        LOG.info( "Importing plan using validation settings '{}'", validationSettings );
        String validationName = validationSettings.getValidationName();
        XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip( uploadedPlan );
        ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport( validationSettings, validationName,
                                                                                 xPlanArchive );
        if ( !validatorReport.isReportValid() ) {
            throw new InvalidPlan( validatorReport, xFileName );
        }
        LOG.info( "Plan is valid. Importing plan into storage for '{}'", planStatus );
        AdditionalPlanData metadata = createAdditionalPlanData( xPlanArchive, planStatus );
        int planId = xPlanInsertManager.importPlan( xPlanArchive, null, false, false, true, null, internalId,
                                                    metadata );

        XPlan planById = findPlanById( planId );
        LOG.info( "Plan with Id {} successfully imported", planById );
        return planById;
    }

    public StatusMessage deletePlan( String planId )
                            throws Exception {
        LOG.info( "Deleting plan with Id {}", planId );
        xPlanDeleteManager.delete( planId );
        return new StatusMessage().message( String.format( DELETE_MSG, planId ) );
    }

    public StreamingOutput exportPlan( String planId )
                            throws Exception {
        LOG.info( "Exporting plan with Id '{}'", planId );
        XPlanArchiveContent archiveContent = xPlanDao.retrieveAllXPlanArtefacts( planId );
        return outputStream -> xPlanExporter.export( outputStream, archiveContent );
    }

    public XPlan findPlanById( String planId )
                            throws Exception {
        LOG.info( "Find plan by Id '{}'", planId );
        int id = Integer.parseInt( planId );
        return findPlanById( id );
    }

    public XPlan findPlanByName( String planName )
                            throws Exception {
        LOG.info( "Find plan by name '{}'", planName );
        List<XPlan> xPlans = xPlanDao.getXPlanByName( planName );
        if ( xPlans.size() != 1 )
            throw new InvalidPlanName( planName );
        return xPlans.get( 0 );
    }

    public List<XPlan> findPlans( String planName )
                            throws Exception {
        LOG.info( "Search plan by name '{}'", planName );
        if ( planName != null )
            return xPlanDao.getXPlansLikeName( planName );
        return xPlanDao.getXPlanList( false );
    }

    private XPlan findPlanById( int id )
                            throws Exception {
        XPlan xPlanById = xPlanDao.getXPlanById( id );
        if ( xPlanById == null ) {
            throw new InvalidPlanId( id );
        }
        return xPlanById;
    }

    private URI createWmsUrl( XPlan xPlan )
                            throws URISyntaxException {
        URI wmsEndpoint = managerConfiguration.getWmsEndpoint();
        if ( wmsEndpoint == null )
            return null;
        URIBuilder uriBuilder = new URIBuilder( wmsEndpoint );
        List<String> pathSegments = new ArrayList<>();
        pathSegments.addAll( uriBuilder.getPathSegments() );
        pathSegments.add( "services" );
        pathSegments.add( detectService( xPlan ) );
        uriBuilder.setPathSegments( pathSegments );
        uriBuilder.addParameter( "PLANWERK_MANAGERID", xPlan.getId() );
        uriBuilder.addParameter( "SERVICE", "WMS" );
        uriBuilder.addParameter( "REQUEST", "GetCapabilities" );
        return uriBuilder.build();
    }

    private String detectService( XPlan xPlan ) {
        if ( xPlan.getXplanMetadata() != null )
            switch ( xPlan.getXplanMetadata().getPlanStatus() ) {
            case ARCHIVIERT:
                return "wmsarchive";
            case IN_AUFSTELLUNG:
                return "wmspre";
            }
        return "wms";
    }

    private AdditionalPlanData createAdditionalPlanData( XPlanArchive xPlanArchive, String requestedPlanStatus )
                            throws UnsupportedParameterValue, XMLStreamException, UnknownCRSException {
        AdditionalPlanData metadata = new AdditionalPlanData();
        PlanStatus planStatus;
        if ( requestedPlanStatus == null ) {
            PlanStatus planStatusFromPlan = determinePlanStatusByRechtsstand( xPlanArchive );
            planStatus = PlanStatus.valueOf( planStatusFromPlan.name() );
        } else {
            try {
                planStatus = PlanStatus.valueOf( requestedPlanStatus );
            } catch ( IllegalArgumentException e ) {
                throw new UnsupportedParameterValue( "planStatus", requestedPlanStatus );
            }
        }
        metadata.setPlanStatus( planStatus );
        return metadata;
    }

    private PlanStatus determinePlanStatusByRechtsstand( XPlanArchive xPlanArchive )
                            throws XMLStreamException, UnknownCRSException {
        XPlanFeatureCollection fc = parseXPlanFeatureCollection( xPlanArchive );
        removeAllFeaturesExceptOfPlanFeature( fc );

        String legislationStatus = retrieveLegislationStatus( fc.getFeatures(), fc.getType() );
        if ( legislationStatus != null && !legislationStatus.isEmpty() ) {
            try {
                int rechtsstand = parseInt( legislationStatus );
                return PlanStatus.findByLegislationStatusCode( rechtsstand );
            } catch ( NumberFormatException e ) {
                LOG.info( "Rechtsstand '{}' could not be parsed as integer.", legislationStatus );
            }
        }
        return IN_AUFSTELLUNG;
    }

}