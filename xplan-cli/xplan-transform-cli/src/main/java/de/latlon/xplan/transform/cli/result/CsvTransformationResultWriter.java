package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.nio.file.Files.newOutputStream;
import static org.apache.commons.io.IOUtils.write;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CsvTransformationResultWriter implements TransformationResultWriter {

    private static final Logger LOG = LoggerFactory.getLogger( CsvTransformationResultWriter.class );

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd-HH-mm" );

    private final CSVPrinter resultWriter;

    private Path outDir;

    public CsvTransformationResultWriter( Path outDir )
                    throws IOException {
        this.outDir = outDir;
        String fileName = "validationResults_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".csv";
        BufferedWriter writer = Files.newBufferedWriter( outDir.resolve( fileName ) );
        resultWriter = new CSVPrinter( writer,
                                       CSVFormat.DEFAULT.withHeader( "ID", "Name", "IsValid", "RefTranformedGML",
                                                                     "ValidationResult" ) );
    }

    @Override
    public void writeResult( String id, String name, SyntacticValidatorResult validatorResult,
                             TransformationResult transformationResult ) {
        String fileName = id + "_transformedGml_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".xml";
        Path gmlFile = outDir.resolve( fileName );
        try ( OutputStream gmlOutputStream = newOutputStream( gmlFile ) ) {
            write( transformationResult.getTransformationResult(), gmlOutputStream );
            String validationResult = validatorResult.getMessages().stream().collect( Collectors.joining( "," ) );
            resultWriter.printRecord( id, name, validatorResult.isValid(), gmlFile, validationResult );
        } catch ( IOException e ) {
            LOG.warn( "Could not write results to csv file" );
        }
    }

    @Override
    public void close()
                    throws IOException {
        resultWriter.close();
    }

}
