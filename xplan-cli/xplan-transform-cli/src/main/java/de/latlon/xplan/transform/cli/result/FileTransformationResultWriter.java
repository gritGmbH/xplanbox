package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.nio.file.Files.newOutputStream;
import static org.apache.commons.io.IOUtils.write;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FileTransformationResultWriter implements TransformationResultWriter {

    private static final Logger LOG = LoggerFactory.getLogger( FileTransformationResultWriter.class );

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd-HH-mm" );

    private Path outDir;

    public FileTransformationResultWriter( Path outDir )
                    throws IOException {
        this.outDir = outDir;
    }

    @Override
    public void writeResult( String id, String name, SyntacticValidatorResult validatorResult,
                             TransformationResult transformationResult ) {
        String fileNameGml = id + "_transformedGml_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".xml";
        String fileNameValidationResult =
                        id + "_validationResult_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".xml";
        Path gmlFile = outDir.resolve( fileNameGml );
        Path validationResultFile = outDir.resolve( fileNameValidationResult );
        try (
                        OutputStream gmlOutputStream = newOutputStream( gmlFile );
                        OutputStream validationResultOutputStream = newOutputStream( validationResultFile ) ) {
            write( transformationResult.getTransformationResult(), gmlOutputStream );
            String validationResult = validatorResult.isValid() ?
                                      "valid" :
                                      validatorResult.getMessages().stream().collect( Collectors.joining( "," ) );
            write( validationResult, validationResultOutputStream );
        } catch ( IOException e ) {
            LOG.warn( "Could not write results to file" );
        }
    }

    @Override
    public void close()
                    throws IOException {
    }

}