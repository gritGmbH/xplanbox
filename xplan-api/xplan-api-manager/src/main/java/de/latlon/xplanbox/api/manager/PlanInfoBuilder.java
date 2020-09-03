package de.latlon.xplanbox.api.manager;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import de.latlon.xplanbox.api.manager.v1.model.Link;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfoBbox;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfoXplanModelData;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanInfoBuilder {

    private XPlan xPlan;

    public PlanInfoBuilder( XPlan xPlan ) {
        this.xPlan = xPlan;
    }

    public PlanInfo build() {
        return new PlanInfo().id( Integer.parseInt( xPlan.getId() ) ).importDate(
                                xPlan.getImportDate() ).inspirePublished( xPlan.isInspirePublished() ).raster(
                                xPlan.isRaster() ).version( version() ).bbox( bbox() ).links( links() ).type(
                                xPlan.getType() ).xplanModelData( xPlanModelData() );
    }

    private PlanInfoXplanModelData xPlanModelData() {
        return new PlanInfoXplanModelData().name( xPlan.getName() ).nummer(
                                xPlan.getNumber() ).internalId( xPlan.getInternalId() ).inkrafttretensDatum(
                                xPlan.getReleaseDate() ).rechtsstand( xPlan.getLegislationStatus() ).ags(
                                xPlan.getGkz() ).gemeindeName( xPlan.getDistrict() );
    }

    private VersionEnum version() {
        return VersionEnum.fromValue( xPlan.getVersion() );
    }

    private List<Link> links() {
        return Collections.emptyList();
    }

    private PlanInfoBbox bbox() {
        XPlanEnvelope bbox = xPlan.getBbox();
        if ( bbox != null )
            return new PlanInfoBbox().crs( bbox.getCrs() ).minX( bbox.getMinX() ).minY( bbox.getMinY() ).maxX(
                                    bbox.getMaxX() ).maxY( bbox.getMaxY() );
        return null;
    }

}