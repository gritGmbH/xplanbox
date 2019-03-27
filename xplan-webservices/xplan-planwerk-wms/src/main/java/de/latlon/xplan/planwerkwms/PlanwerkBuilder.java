package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.Workspace;

/**
 * Instantiates a {@link WMSController} for the Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkBuilder implements ResourceBuilder<OWS> {

    private final Workspace workspace;

    private final PlanwerkMetadata planwerkWmsMetadata;

    public PlanwerkBuilder( Workspace workspace, PlanwerkMetadata planwerkWmsMetadata ) {
        this.workspace = workspace;
        this.planwerkWmsMetadata = planwerkWmsMetadata;
    }

    @Override
    public OWS build() {
        return new WMSController( planwerkWmsMetadata, workspace, planwerkWmsMetadata.getDeegreeWmsConfig() );
    }
}
