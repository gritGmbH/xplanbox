package de.latlon.xplanbox.api.manager;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import de.latlon.xplanbox.api.manager.v1.model.Link;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfoBbox;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfoXplanModelData;
import org.apache.http.client.utils.URIBuilder;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.ALTERNATE;
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.PLANWERKWMS;
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.SELF;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanInfoBuilder {

    private final XPlan xPlan;

    private final UriInfo uriInfo;

    private final List<String> alternateMediaTypes = new ArrayList<>();

    private String wmsEndpoint;

    private String requestedMediaType;

    public PlanInfoBuilder( XPlan xPlan, UriInfo uriInfo ) {
        this.xPlan = xPlan;
        this.uriInfo = uriInfo;
    }

    public PlanInfoBuilder wmsEndpoint( String wmsEndpoint ) {
        this.wmsEndpoint = wmsEndpoint;
        return this;
    }

    public PlanInfoBuilder requestedMediaType( String requestedMediaType ) {
        this.requestedMediaType = requestedMediaType;
        return this;
    }

    public PlanInfoBuilder alternateMediaType( List<String> alternateMediaTypes ) {
        if ( alternateMediaTypes != null )
            this.alternateMediaTypes.addAll( alternateMediaTypes );
        return this;
    }

    public PlanInfo build()
                            throws URISyntaxException {
        return new PlanInfo().id( Integer.parseInt( xPlan.getId() ) ).importDate(
                                xPlan.getImportDate() ).inspirePublished( xPlan.isInspirePublished() ).raster(
                                xPlan.isRaster() ).version( version() ).bbox( bbox() ).links( links() ).type(
                                xPlan.getType() ).xplanModelData( xPlanModelData() );
    }

    private PlanInfoXplanModelData xPlanModelData() {
        return new PlanInfoXplanModelData().name( xPlan.getName() ).nummer( xPlan.getNumber() ).internalId(
                                xPlan.getInternalId() ).inkrafttretensDatum( xPlan.getReleaseDate() ).rechtsstand(
                                xPlan.getLegislationStatus() ).ags( xPlan.getGkz() ).gemeindeName(
                                xPlan.getDistrict() );
    }

    private VersionEnum version() {
        return VersionEnum.fromValue( xPlan.getVersion() );
    }

    private List<Link> links()
                            throws URISyntaxException {
        List<Link> links = new ArrayList<>();
        URI selfRef = uriInfo.getBaseUriBuilder().path( "plan" ).path( xPlan.getId() ).build();
        Link selfLink = new Link().href( selfRef ).rel( SELF ).type( requestedMediaType ).title( xPlan.getName() );
        links.add( selfLink );

        alternateMediaTypes.forEach( mediaType -> {
            Link alternateLink = new Link().href( selfRef ).rel( ALTERNATE ).type( mediaType ).
                                    title( xPlan.getName() );
            links.add( alternateLink );
        } );

        if ( wmsEndpoint != null ) {
            String planname = xPlan.getName().replaceAll( "[^a-zA-Z0-9\\\\-_]", "" );
            URIBuilder uriBuilder = new URIBuilder( wmsEndpoint );
            List<String> pathSegments = new ArrayList<>();
            pathSegments.addAll( uriBuilder.getPathSegments() );
            pathSegments.add( "services" );
            pathSegments.add( detectService() );
            pathSegments.add( "planname" );
            pathSegments.add( planname );
            uriBuilder.setPathSegments( pathSegments );
            URI planwerkWmsRef = uriBuilder.build();
            Link planwerkWmsLink = new Link().href( planwerkWmsRef ).rel( PLANWERKWMS ).title( xPlan.getName() );
            links.add( planwerkWmsLink );
        }
        return links;
    }

    private PlanInfoBbox bbox() {
        XPlanEnvelope bbox = xPlan.getBbox();
        if ( bbox != null )
            return new PlanInfoBbox().crs( bbox.getCrs() ).minX( bbox.getMinX() ).minY( bbox.getMinY() ).maxX(
                                    bbox.getMaxX() ).maxY( bbox.getMaxY() );
        return null;
    }

    private String detectService() {
        if ( xPlan.getXplanMetadata() != null )
            switch ( xPlan.getXplanMetadata().getPlanStatus() ) {
            case ARCHIVIERT:
                return "planwerkwmsarchive";
            case IN_AUFSTELLUNG:
                return "planwerkwmspre";
            }
        return "planwerkwms";
    }

}