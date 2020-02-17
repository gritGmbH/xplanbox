package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadataParser;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidatorRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.newInputStream;

/**
 * Retrieves XQuery configurations from file system
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorConfigurationRetriever implements SemanticValidatorConfigurationRetriever {

    private static final Logger LOG = LoggerFactory.getLogger( XQuerySemanticValidatorConfigurationRetriever.class );

    private static final XPlanVersion UNKNOWN_VERSION = null;

    private static final SemanticValidationOptions UNKNOWN_OPTION = NONE;

    private final Path rulesPath;

    private final RulesMetadataParser rulesMetadataParser = new RulesMetadataParser();

    public XQuerySemanticValidatorConfigurationRetriever( Path rulesPath ) {
        this.rulesPath = rulesPath;
    }

    @Override
    public SemanticValidatorConfiguration retrieveConfiguration()
                            throws IOException {
        SemanticValidatorConfiguration config = new SemanticValidatorConfiguration();

        if ( isDirectory( rulesPath ) ) {
            RulesMetadata rulesMetadata = rulesMetadataParser.parserMetadata( rulesPath );
            config.setRulesMetadata( rulesMetadata );
            try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules( rulesPath )) {
                for ( Path path : directoryStream ) {
                    if ( isDirectory( path ) ) {
                        XPlanVersion planVersion = parseXPlanVersion( path );
                        SemanticValidationOptions validationOption = parseSemanticValidationOption( path );
                        boolean isVersionDirectory = isVersionDirectory( planVersion );
                        boolean isIgnoreOptionDirectory = isIgnoreOptionDirectory( validationOption );
                        if ( isVersionDirectory ) {
                            collectAllRulesFromVersionDirectory( config, path, planVersion );
                        } else if ( isIgnoreOptionDirectory ) {
                            collectAllRulesFromDirectory( config, path, UNKNOWN_VERSION, validationOption );
                        } else {
                            collectAllRulesFromDirectory( config, path, UNKNOWN_VERSION, UNKNOWN_OPTION );
                        }
                    } else {
                        createAndAddRule( config, path, UNKNOWN_VERSION, UNKNOWN_OPTION );
                    }
                }
            }
        } else {
            createAndAddRule( config, rulesPath, UNKNOWN_VERSION, UNKNOWN_OPTION );
        }

        return config;
    }

    private void collectAllRulesFromVersionDirectory( SemanticValidatorConfiguration config, Path versionDirectory,
                                                      XPlanVersion planVersion )
                            throws IOException {
        try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules( versionDirectory )) {
            for ( Path path : directoryStream ) {
                if ( isDirectory( path ) ) {
                    SemanticValidationOptions validationOption = parseSemanticValidationOption( path );
                    if ( isIgnoreOptionDirectory( validationOption ) ) {
                        collectAllRulesFromDirectory( config, path, planVersion, validationOption );
                    } else {
                        collectAllRulesFromDirectory( config, path, planVersion, validationOption );
                    }
                } else {
                    createAndAddRule( config, path, planVersion, UNKNOWN_OPTION );
                }
            }
        }
    }

    private void collectAllRulesFromDirectory( SemanticValidatorConfiguration config, Path validationDirectory,
                                               XPlanVersion planVersion, SemanticValidationOptions validationOption )
                            throws IOException {
        try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules( validationDirectory )) {
            for ( Path path : directoryStream ) {
                if ( isDirectory( path ) ) {
                    collectAllRulesFromDirectory( config, path, planVersion, validationOption );
                } else {
                    createAndAddRule( config, path, planVersion, validationOption );
                }
            }
        }
    }

    private void createAndAddRule( SemanticValidatorConfiguration config, Path path, XPlanVersion version,
                                   SemanticValidationOptions option ) {
        LOG.debug( "Parse rule {}", path );
        String nameWithType = path.getFileName().toString();
        String name = nameWithType.substring( 0, nameWithType.lastIndexOf( '.' ) );
        try {
            XQuerySemanticValidatorRule rule = new XQuerySemanticValidatorRule( newInputStream( path ), name, version,
                                                                                option );
            config.addRule( rule );
            LOG.debug( format( "New rule: %s from file rulesPath %s", name, path.toAbsolutePath().toString() ) );
        } catch ( Throwable e ) {
            LOG.warn( format( "Rule '%s' could not be parsed and will be skipped, reason: %s",
                               path.toAbsolutePath().toString(), e.getMessage() ), e );
        }
    }

    private static DirectoryStream<Path> retrieveDirectoriesAndRules( Path filesPath )
                            throws IOException {
        return newDirectoryStream( filesPath, new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept( Path entry )
                                    throws IOException {
                return isDirectory( entry ) || valueOf( entry.getFileName() ).endsWith( ".xq" );
            }
        } );
    }

    private boolean isVersionDirectory( XPlanVersion planVersion ) {
        return planVersion != UNKNOWN_VERSION;
    }

    private boolean isIgnoreOptionDirectory( SemanticValidationOptions validationOption ) {
        return !UNKNOWN_OPTION.equals( validationOption );
    }

    private SemanticValidationOptions parseSemanticValidationOption( Path path ) {
        String dirName = extractDirectoryName( path );
        return SemanticValidationOptions.getByDirectoryName( dirName );
    }

    private XPlanVersion parseXPlanVersion(Path path) {
        String dirName = extractDirectoryName(path);
        if ("xplangml52".equals(dirName))
            return XPLAN_52;
        if ("xplangml51".equals(dirName))
            return XPLAN_51;
        if ("xplangml50".equals(dirName))
            return XPLAN_50;
        if ("xplangml41".equals(dirName))
            return XPLAN_41;
        if ("xplangml40".equals(dirName))
            return XPLAN_40;
        if ("xplangml3".equals(dirName))
            return XPLAN_3;
        if ("xplangml2".equals(dirName))
            return XPLAN_2;
        LOG.info("{} is not a known XPlanVersion", dirName);
        return UNKNOWN_VERSION;
    }

    private String extractDirectoryName( Path path ) {
        Path name = path.getFileName();
        return name.toFile().getName();
    }

}