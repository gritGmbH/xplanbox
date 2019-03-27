package de.latlon.xplan.planwerkwms;

import org.deegree.commons.xml.jaxb.JAXBUtils;
import org.deegree.metadata.persistence.MetadataStoreProvider;
import org.deegree.services.OWS;
import org.deegree.services.jaxb.wms.DeegreeWMS;
import org.deegree.services.metadata.OWSMetadataProvider;
import org.deegree.services.metadata.OWSMetadataProviderManager;
import org.deegree.services.planwerkwms.jaxb.PlanwerkWMS;
import org.deegree.theme.persistence.ThemeProvider;
import org.deegree.workspace.ResourceBuilder;
import org.deegree.workspace.ResourceIdentifier;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.AbstractResourceMetadata;
import org.deegree.workspace.standard.AbstractResourceProvider;
import org.deegree.workspace.standard.DefaultResourceIdentifier;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link ResourceMetadata} for the PlanwerkWMS. Provides one {@link PlanwerkMetadata} for each available Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkWmsMetadata extends AbstractResourceMetadata<OWS> {

    private static final String CONFIG_JAXB_PACKAGE = "org.deegree.services.planwerkwms.jaxb";

    private List<ResourceMetadata<OWS>> additionalResources = new ArrayList<>();

    private DeegreeWMS cfg;

    public PlanwerkWmsMetadata( Workspace workspace, ResourceLocation<OWS> location,
                                AbstractResourceProvider<OWS> provider ) {
        super( workspace, location, provider );
    }

    @Override
    public ResourceBuilder<OWS> prepare() {
        try {
            PlanwerkWMS planwerkWmsConfig = (PlanwerkWMS) JAXBUtils.unmarshall( CONFIG_JAXB_PACKAGE,
                                                                                provider.getSchema(),
                                                                                location.getAsStream(), workspace );

            cfg = planwerkWmsConfig.getDeegreeWMS();

            String id = cfg.getMetadataStoreId();
            if ( id != null ) {
                // is that really a metadata store id? Saw services with UUID here.
                softDependencies.add( new DefaultResourceIdentifier( MetadataStoreProvider.class, id ) );
            }

            for ( String tid : cfg.getServiceConfiguration().getThemeId() ) {
                dependencies.add( new DefaultResourceIdentifier<>( ThemeProvider.class, tid ) );
            }

            OWSMetadataProviderManager mmgr = workspace.getResourceManager( OWSMetadataProviderManager.class );
            for ( ResourceMetadata<OWSMetadataProvider> md : mmgr.getResourceMetadata() ) {
                ResourceIdentifier<OWSMetadataProvider> mdId = md.getIdentifier();
                if ( mdId.getId().equals( getIdentifier().getId() + "_metadata" ) ) {
                    softDependencies.add( mdId );
                }
            }

            return new PlanwerkWmsBuilder( this, workspace, planwerkWmsConfig );
        } catch ( Exception e ) {
            throw new ResourceInitException( e.getLocalizedMessage(), e );
        }
    }

    DeegreeWMS getDeegreeWmsConfig() {
        return cfg;
    }

    public List<ResourceMetadata<OWS>> getAdditionalResources( Workspace workspace ) {
        // TODO: aus Konfig/DB auslesen:
        String oldId = location.getIdentifier().getId();
        this.additionalResources.add(
                        new PlanwerkMetadata( workspace, createLocation( oldId + "/planname/Billstedt28" ), provider,
                                              this ) );
        this.additionalResources.add(
                        new PlanwerkMetadata( workspace, createLocation( oldId + "/planname/Billstedt73" ), provider,
                                              this ) );
        return this.additionalResources;
    }

    private ResourceLocation<OWS> createLocation( String identifier ) {
        return new PlanwerkResourceLocation( location, identifier );
    }

}