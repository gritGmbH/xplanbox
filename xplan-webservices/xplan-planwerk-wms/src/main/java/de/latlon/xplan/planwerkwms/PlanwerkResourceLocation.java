package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.workspace.ResourceIdentifier;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.standard.DefaultResourceIdentifier;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * {@link ResourceLocation} for a Planwerk
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkResourceLocation implements ResourceLocation<OWS> {

    private final ResourceLocation<OWS> encapsulatedResourceLocation;

    private final String identifier;

    public PlanwerkResourceLocation( ResourceLocation<OWS> encapsulatedResourceLocation, String identifier ) {
        this.encapsulatedResourceLocation = encapsulatedResourceLocation;
        this.identifier = identifier;
    }

    @Override
    public String getNamespace() {
        return encapsulatedResourceLocation.getNamespace();
    }

    @Override
    public ResourceIdentifier getIdentifier() {
        return new DefaultResourceIdentifier( OWSProvider.class, identifier );
    }

    @Override
    public InputStream getAsStream() {
        return encapsulatedResourceLocation.getAsStream();
    }

    @Override
    public InputStream resolve( String path ) {
        return encapsulatedResourceLocation.resolve( path );
    }

    @Override
    public File resolveToFile( String path ) {
        return encapsulatedResourceLocation.resolveToFile( path );
    }

    @Override
    public URL resolveToUrl( String path ) {
        return encapsulatedResourceLocation.resolveToUrl( path );
    }

    @Override
    public void deactivate() {
        encapsulatedResourceLocation.deactivate();
    }

    @Override
    public void activate() {
        encapsulatedResourceLocation.activate();
    }

    @Override
    public void setContent( InputStream in ) {
        encapsulatedResourceLocation.setContent( in );

    }
}
