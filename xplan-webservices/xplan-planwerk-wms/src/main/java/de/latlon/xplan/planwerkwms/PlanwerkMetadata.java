package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.jaxb.wms.DeegreeWMS;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.AbstractResourceProvider;

/**
 * {@link org.deegree.workspace.ResourceMetadata} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkMetadata extends AbstractResourceMetadata<OWS> {

    private PlanwerkWmsMetadata planwerkWmsMetadata;

    public PlanwerkMetadata( Workspace workspace, ResourceLocation<OWS> location,
                             AbstractResourceProvider<OWS> provider, PlanwerkWmsMetadata planwerkWmsMetadata ) {
        super( workspace, location, provider );
        this.planwerkWmsMetadata = planwerkWmsMetadata;
    }

    @Override
    public ResourceBuilder<OWS> prepare() {
        return new PlanwerkBuilder( workspace, this );
    }

    DeegreeWMS getDeegreeWmsConfig() {
        return planwerkWmsMetadata.getDeegreeWmsConfig();
    }
}