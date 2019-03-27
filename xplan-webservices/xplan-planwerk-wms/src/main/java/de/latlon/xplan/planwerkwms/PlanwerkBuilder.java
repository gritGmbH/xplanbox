package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.Workspace;

import java.util.List;

/**
 * Instantiates a {@link WMSController} for the Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkBuilder implements ResourceBuilder<OWS> {

    private final Workspace workspace;

    private final PlanwerkMetadata planwerkMetadata;

    private List<Integer> managerIds;

    public PlanwerkBuilder( Workspace workspace, PlanwerkMetadata planwerkMetadata, List<Integer> managerIds ) {
        this.workspace = workspace;
        this.planwerkMetadata = planwerkMetadata;
        this.managerIds = managerIds;
    }

    @Override
    public OWS build() {
        return new PlanwerkController( planwerkMetadata, workspace, managerIds );
    }

}