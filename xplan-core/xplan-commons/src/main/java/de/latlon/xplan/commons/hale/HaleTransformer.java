package de.latlon.xplan.commons.hale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

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
        HaleIOProvider gmlWriter = new HaleIOProvider( "eu.esdihumboldt.hale.io.gml.writer" );
        transform( haleProject, sourceFile, targetFile, gmlWriter );
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
     * @param writer
     *                 writer, may be <code>null</code> (default is the GML Writer)
     * @throws TransformationException
     *                 if the transformation failed
     */
    public void transform( String haleProject, String sourceFile, String targetFile, HaleIOProvider writer )
                    throws TransformationException {
        try {
            String command = buildCommand( haleProject, sourceFile, targetFile, writer );
            LOG.info( "Execute the following command to transform the plan: {}", command );
            Process process = Runtime.getRuntime().exec( command );
            StreamGobbler streamGobbler = new StreamGobbler( process.getInputStream(),
                                                             LOG.isDebugEnabled() ? System.out::println : ( c ) -> {
                                                             } );
            Executors.newSingleThreadExecutor().submit( streamGobbler );
            int exitCode = process.waitFor();
            LOG.info( "Transformation command finished with exit code {}. ", exitCode );
        } catch ( IOException | InterruptedException e ) {
            LOG.error( "Could not transform", e );
            throw new TransformationException( "Could not transform", e );
        }
    }

    private String buildCommand( String haleProject, String source, String target, HaleIOProvider writer ) {
        StringBuilder sb = new StringBuilder();
        sb.append( haleCli );
        sb.append( " transform" );
        sb.append( " -project " + haleProject );
        sb.append( " -source " + source );
        sb.append( " -target " + target );
        sb.append( " -providerId " ).append( writer.getName() );
        for ( Map.Entry<String, String> setting : writer.getSettings().entrySet() ) {
            sb.append( " -S" ).append( setting.getKey() ).append( " " ).append( setting.getValue() ).append( " " );
        }
        return sb.toString();
    }

    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;

        private Consumer<String> consumer;

        public StreamGobbler( InputStream inputStream, Consumer<String> consumer ) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader( new InputStreamReader( inputStream ) ).lines().forEach( consumer );
        }
    }

}
