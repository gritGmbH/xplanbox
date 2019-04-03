package de.latlon.xplan.planwerkwms;

import org.apache.commons.io.IOUtils;
import org.deegree.services.OWS;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.ResourceException;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.deegree.workspace.standard.DefaultResourceLocation;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * {@link ResourceLocation} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkResourceLocation extends DefaultResourceLocation {

    private byte[] bytes;

    private final WmsMetadata wmsMetadata;

    public PlanwerkResourceLocation( byte[] bytes, DefaultResourceIdentifier<OWS> identifier,
                                     WmsMetadata wmsMetadata ) {
        super( null, identifier );
        this.bytes = bytes;
        this.wmsMetadata = wmsMetadata;
    }

    @Override
    public InputStream getAsStream() {
        return new ByteArrayInputStream( bytes );
    }

    @Override
    public InputStream resolve( String path ) {
        return wmsMetadata.getLocation().resolve( path );
    }

    @Override
    public File resolveToFile( String path ) {
        return wmsMetadata.getLocation().resolveToFile( path );
    }

    @Override
    public URL resolveToUrl( String path ) {
        return wmsMetadata.getLocation().resolveToUrl( path );
    }

    @Override
    public void deactivate() {
        wmsMetadata.getLocation().deactivate();
    }

    @Override
    public void activate() {
        wmsMetadata.getLocation().activate();
    }

    @Override
    public void setContent( InputStream in ) {
        try {
            bytes = IOUtils.toByteArray( in );
        } catch ( IOException e ) {
            throw new ResourceException( e.getLocalizedMessage(), e );
        }
    }

}