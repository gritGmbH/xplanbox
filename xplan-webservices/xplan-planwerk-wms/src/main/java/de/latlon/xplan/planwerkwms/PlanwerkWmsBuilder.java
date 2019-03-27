package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.planwerkwms.jaxb.PlanwerkWMS;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.Workspace;

/**
 * Instantiates a {@link PlanwerkWmsController} for the PlanwerkWMS
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkWmsBuilder implements ResourceBuilder<OWS> {

    private final Workspace workspace;

    private final PlanwerkWMS planwerkWmsConfig;

    private final PlanwerkWmsMetadata planwerkWmsMetadata;

    public PlanwerkWmsBuilder( PlanwerkWmsMetadata planwerkWmsMetadata, Workspace workspace,
                               PlanwerkWMS planwerkWmsConfig ) {
        this.planwerkWmsMetadata = planwerkWmsMetadata;
        this.workspace = workspace;
        this.planwerkWmsConfig = planwerkWmsConfig;
    }

    @Override
    public OWS build() {
        return new PlanwerkWmsController( planwerkWmsMetadata, workspace, planwerkWmsMetadata.getDeegreeWmsConfig() );
    }

}
