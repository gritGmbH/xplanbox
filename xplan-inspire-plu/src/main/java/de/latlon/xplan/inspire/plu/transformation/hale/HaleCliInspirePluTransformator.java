package de.latlon.xplan.inspire.plu.transformation.hale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.TransformationException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleCliInspirePluTransformator implements InspirePluTransformator {

    private static final Logger LOG = LoggerFactory.getLogger( HaleCliInspirePluTransformator.class );

    private final String haleCli;

    private final String haleProject;

    public HaleCliInspirePluTransformator( String haleCli, String haleProject ) {
        this.haleCli = haleCli;
        this.haleProject = haleProject;
    }

    @Override
    public Path transformToPlu( Path xPlanGml )
                            throws TransformationException {
        try {
            Path targetFile = Files.createTempFile( "inspirePlu", ".xml" );
            String command = buildCommand( xPlanGml, targetFile );
            LOG.info( "Execute the following command to transform the plan to INSPIRE PLU: {}", command );
            Process process = Runtime.getRuntime().exec( command );
            process.waitFor();
            return targetFile;
        } catch ( IOException | InterruptedException e ) {
            LOG.error( "Could not transform to INSPIRE PLU", e );
            throw new TransformationException( "Could not transform to INSPIRE PLU", e );
        }
    }

    private String buildCommand( Path source, Path target ) {
        StringBuilder sb = new StringBuilder();
        sb.append( haleCli );
        sb.append( " transform" );
        sb.append( " -project " + haleProject );
        sb.append( " -source " + source.toString() );
        sb.append( " -target " + target.toString() );
        sb.append( " -providerId eu.esdihumboldt.hale.io.gml.writer" );
        return sb.toString();
    }

}
