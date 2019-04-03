package de.latlon.xplan.planwerkwms;

import org.deegree.commons.tom.ows.Version;
import org.deegree.protocol.wms.WMSConstants;
import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.ImplementationMetadata;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;

import java.net.URL;

import static org.deegree.protocol.wms.WMSConstants.VERSION_111;
import static org.deegree.protocol.wms.WMSConstants.VERSION_130;

/**
 * The {@link OWSProvider} of the PlanwerkWMS
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkProvider extends OWSProvider {

    public static final String NAMESPACE = "http://www.lat-lon.de/services/planwerk";

    protected static final ImplementationMetadata<WMSConstants.WMSRequestType> IMPLEMENTATION_METADATA = new ImplementationMetadata<WMSConstants.WMSRequestType>() {
        {
            supportedVersions = new Version[] { VERSION_111, VERSION_130 };
            handledNamespaces = new String[] { "" }; // WMS uses null namespace for SLD GetMap Post requests
            handledRequests = WMSConstants.WMSRequestType.class;
            supportedConfigVersions = new Version[] { Version.parseVersion( "3.0.0" ), Version.parseVersion( "3.1.0" ),
                                                      Version.parseVersion( "3.2.0" ),
                                                      Version.parseVersion( "3.4.0" ) };
            serviceName = new String[] { "WMS" };
        }
    };

    @Override
    public String getNamespace() {
        return NAMESPACE;
    }

    @Override
    public URL getSchema() {
        return PlanwerkProvider.class.getResource(
                        "/META-INF/schemas/services/planwerkwms/1.0/planwerk_configuration.xsd" );
    }

    @Override
    public ImplementationMetadata<WMSConstants.WMSRequestType> getImplementationMetadata() {
        return IMPLEMENTATION_METADATA;
    }

    @Override
    public ResourceMetadata<OWS> createFromLocation( Workspace workspace, ResourceLocation<OWS> location ) {
        return new PlanwerkMetadata( workspace, location, this );
    }

}