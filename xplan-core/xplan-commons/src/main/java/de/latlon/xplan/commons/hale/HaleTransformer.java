package de.latlon.xplan.commons.hale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleTransformer {

    private static final Logger LOG = LoggerFactory.getLogger( HaleTransformer.class );

    private final String haleCli;

    /**
     * @param haleCli
     *                 the path to the the hale CLI, never <code>null</code>
     */
    public HaleTransformer( String haleCli ) {
        this.haleCli = haleCli;
    }

    /**
     * Transform the passed file with the passed project, the result will be stored in the target file.
     *
     * @param haleProject
     *                 to hale project to use for the transformation, never <code>null</code>
     * @param sourceFile
     *                 the source file to transform, never <code>null</code>
     * @param targetFile
     *                 the file to store the transformation result, never <code>null</code>
     * @throws TransformationException
     *                 if the transformation failed
     */
    public void transform( String haleProject, String sourceFile, String targetFile )
                    throws TransformationException {
        try {
            String command = buildCommand( haleProject, sourceFile, targetFile );
            LOG.info( "Execute the following command to transform the plan: {}", command );
            Process process = Runtime.getRuntime().exec( command );
            process.waitFor();
        } catch ( IOException | InterruptedException e ) {
            LOG.error( "Could not transform", e );
            throw new TransformationException( "Could not transform", e );
        }
    }

    private String buildCommand( String haleProject, String source, String target ) {
        StringBuilder sb = new StringBuilder();
        sb.append( haleCli );
        sb.append( " transform" );
        sb.append( " -project " + haleProject );
        sb.append( " -source " + source );
        sb.append( " -target " + target );
        sb.append( " -providerId eu.esdihumboldt.hale.io.gml.writer" );
        return sb.toString();
    }

}
