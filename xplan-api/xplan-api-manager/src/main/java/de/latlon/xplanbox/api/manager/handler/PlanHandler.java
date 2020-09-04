package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.ValidationReportBuilder;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.manager.exception.InvalidPlan;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.exception.UnsupportedParameterValue;
import de.latlon.xplanbox.api.manager.v1.model.Status;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class PlanHandler {

    private static final Logger LOG = getLogger( PlanHandler.class );

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
    private ManagerConfiguration managerConfiguration;

    public Status importPlan( File uploadedPlan, String validationName, ValidationSettings validationSettings,
                              String internalId, String planStatus )
                            throws Exception {
        XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip( uploadedPlan );
        ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport( validationSettings, validationName,
                                                                                 xPlanArchive );
        if ( !validatorReport.isReportValid() ) {
            throw new InvalidPlan();
        }
        AdditionalPlanData metadata = createAdditionalPlanData( xPlanArchive, planStatus );
        int planId = xPlanInsertManager.importPlan( xPlanArchive, null, false, false, true, null, internalId,
                                                    metadata );

        XPlan planById = findPlanById( planId );
        ValidationReport validationReport = new ValidationReportBuilder().validatorReport( validatorReport ).filename(
                                validationName ).build();
        return new Status().planId( planId ).link( createWmsUrl( planById ) ).validationReport( validationReport );

    }

    public void deletePlan( String planId )
                            throws Exception {
        xPlanDeleteManager.delete( planId );
    }

    public StreamingOutput exportPlan( String planId )
                            throws Exception {
        InputStream inputStream = xPlanDao.retrieveXPlanArtefact( planId );
        return outputStream -> IOUtils.copy( inputStream, outputStream );
    }

    public XPlan findPlanById( String planId )
                            throws Exception {
        int id = Integer.parseInt( planId );
        return findPlanById( id );
    }

    private XPlan findPlanById( int id )
                            throws Exception {
        XPlan xPlanById = xPlanDao.getXPlanById( id );
        if ( xPlanById == null ) {
            throw new InvalidPlanId( id );
        }
        return xPlanById;
    }

    private URI createWmsUrl( XPlan id )
                            throws URISyntaxException {
        String wmsEndpoint = managerConfiguration.getwmsEndpoint();
        if ( wmsEndpoint == null )
            return null;
        URIBuilder uriBuilder = new URIBuilder( wmsEndpoint );
        List<String> pathSegments = uriBuilder.getPathSegments();
        pathSegments.add( "services" );
        pathSegments.add( detectService( id ) );
        uriBuilder.setPathSegments( pathSegments );
        uriBuilder.addParameter( "PLANWERK_MANAGERID", id.getId() );
        uriBuilder.addParameter( "SERVICE", "WMS" );
        uriBuilder.addParameter( "REQUEST", "GetCapabilities" );
        return uriBuilder.build();
    }

    private String detectService( XPlan id ) {
        if ( id.getXplanMetadata() != null )
            switch ( id.getXplanMetadata().getPlanStatus() ) {
            case ARCHIVIERT:
                return "wmsarchive";
            case IN_AUFSTELLUNG:
                return "wmspre";
            }
        return "wms";
    }

    private AdditionalPlanData createAdditionalPlanData( XPlanArchive xPlanArchive, String planStatus )
                            throws UnsupportedParameterValue {
        AdditionalPlanData metadata = new AdditionalPlanData();
        if ( planStatus != null )
            try {
                metadata.setPlanStatus( PlanStatus.valueOf( planStatus ) );
            } catch ( IllegalArgumentException e ) {
                throw new UnsupportedParameterValue( "planStatus", planStatus );
            }
        return metadata;
    }
}