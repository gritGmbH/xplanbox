package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.jaxb.wms.DeegreeWMS;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.AbstractResourceProvider;

import java.util.List;

/**
 * {@link org.deegree.workspace.ResourceMetadata} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkMetadata extends AbstractResourceMetadata<OWS> {

    private PlanwerkWmsMetadata planwerkWmsMetadata;

    private final List<Integer> managerIds;

    public PlanwerkMetadata( Workspace workspace, ResourceLocation<OWS> location,
                             AbstractResourceProvider<OWS> provider, PlanwerkWmsMetadata planwerkWmsMetadata,
                             List<Integer> managerIds ) {
        super( workspace, location, provider );
        this.planwerkWmsMetadata = planwerkWmsMetadata;
        this.managerIds = managerIds;
    }

    @Override
    public ResourceBuilder<OWS> prepare() {
        dependencies.add( planwerkWmsMetadata.getIdentifier() );
        return new PlanwerkBuilder( workspace, this, managerIds );
    }

    public DeegreeWMS getDeegreeWmsConfig() {
        return planwerkWmsMetadata.getDeegreeWmsConfig();
    }

}