package de.latlon.xplan.planwerkwms;

import org.deegree.commons.xml.jaxb.JAXBUtils;
import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.planwerk.jaxb.Planwerk;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.AbstractResourceProvider;
import org.deegree.workspace.standard.DefaultResourceIdentifier;

/**
 * {@link org.deegree.workspace.ResourceMetadata} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkMetadata extends AbstractResourceMetadata<OWS> {

    private static final String CONFIG_JAXB_PACKAGE = "org.deegree.services.planwerk.jaxb";

    public PlanwerkMetadata( Workspace workspace, ResourceLocation<OWS> location,
                             AbstractResourceProvider<OWS> provider ) {
        super( workspace, location, provider );
    }

    @Override
    public ResourceBuilder<OWS> prepare() {
        try {
            Planwerk planwerkConfig = (Planwerk) JAXBUtils.unmarshall( CONFIG_JAXB_PACKAGE, provider.getSchema(),
                                                                       location.getAsStream(), workspace );

            String planwerkId = planwerkConfig.getPlanwerkWms();
            dependencies.add( new DefaultResourceIdentifier<>( OWSProvider.class, planwerkId ) );

            WmsMetadata planwerkWms = (WmsMetadata) workspace.getResourceMetadata( OWSProvider.class, planwerkId );

            planwerkWms.getDependencies().add( location.getIdentifier() );

            return new PlanwerkBuilder( workspace, this, planwerkConfig );
        } catch ( Exception e ) {
            throw new ResourceInitException( e.getLocalizedMessage(), e );
        }
    }

}