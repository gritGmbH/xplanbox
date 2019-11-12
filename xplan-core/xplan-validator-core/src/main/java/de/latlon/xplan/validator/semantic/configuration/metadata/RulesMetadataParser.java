package de.latlon.xplan.validator.semantic.configuration.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesMetadataParser {

    private static final Logger LOG = LoggerFactory.getLogger( RulesMetadataParser.class );

    private static final String RELATIVE_PATH_TO_FILE = "../VERSION.txt";

    private static final String VERSION = "version";

    private static final String SOURCE = "source";

    /**
     * Parses the rule metadata from the VERSION.txt file in the rulesPath if available
     *
     * @param rulesPath
     *                         Path to rules, nevre <code>null</code>
     * @return never <code>null</code>
     */
    public RulesMetadata parserMetadata( Path rulesPath ) {
        Path metadataFile = rulesPath.resolve( RELATIVE_PATH_TO_FILE );
        if ( !Files.exists( metadataFile ) ) {
            LOG.info( "No file {} in {} available, metadata are unknown", RELATIVE_PATH_TO_FILE, rulesPath );
            return new RulesMetadata();
        }
        Properties properties = new Properties();
        try (InputStream props = Files.newInputStream( metadataFile )) {
            properties.load( props );
            String version = properties.getProperty( VERSION );
            String source = properties.getProperty( SOURCE );
            return new RulesMetadata( version, source );
        } catch ( IOException e ) {
            LOG.warn( "{} in {} could not be read: {}", RELATIVE_PATH_TO_FILE, rulesPath, e.getMessage() );
            return new RulesMetadata();
        }
    }

}