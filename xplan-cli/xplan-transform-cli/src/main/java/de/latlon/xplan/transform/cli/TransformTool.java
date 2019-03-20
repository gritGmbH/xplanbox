package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlan41ToXPlan51Converter;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transformation.HaleXplan41ToXplan51Transformer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;
import org.deegree.workspace.Workspace;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.isDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformTool {

    private enum TYPE {VALIDATE, CONVERT}

    private static final String OPT_WORKSPACE_NAME = "workspaceName";

    private static final String OPT_CONFIG_DIR = "configurationDirectory";

    private static final String OPT_TYPE = "type";

    private static final String OPT_OUT_DIR = "output";

    public static void main( String[] args ) {
        if ( args.length == 0 || ( args.length > 0 && ( args[0].contains( "help" ) || args[0].contains( "?" ) ) ) ) {
            printHelp( initOptions() );
        }

        try {
            CommandLine cmdline = new PosixParser().parse( initOptions(), args );
            try {
                String workspaceName = cmdline.getOptionValue( OPT_WORKSPACE_NAME );
                if ( workspaceName == null || workspaceName.isEmpty() )
                    workspaceName = "xplan-manager-workspace";
                String configurationDirectory = cmdline.getOptionValue( OPT_CONFIG_DIR );
                TYPE type = determineType( cmdline );
                Path outDirectory = createOutDirectory( cmdline );

                TransformTool tool = new TransformTool();
                tool.run( workspaceName, configurationDirectory, type, outDirectory );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } catch ( ParseException exp ) {
            System.err.println( "Could not parse command line" );
            exp.printStackTrace();
            printHelp( initOptions() );
        }
    }

    private void run( String workspaceName, String configurationDirectory, TYPE type, Path outDirectory )
                    throws Exception {
        XPlan41ToXPlan51Converter converter = createConverter( workspaceName, configurationDirectory );
        switch ( type ) {
        case CONVERT:
            converter.convertXPlan41ToXPlan51( outDirectory );
            break;
        case VALIDATE:
        default:
            converter.validate( outDirectory );
        }
        System.out.println( "Results was written to " + outDirectory );
    }

    private static Path createOutDirectory( CommandLine cmdline )
                    throws IOException {
        String outDirectory = cmdline.getOptionValue( OPT_OUT_DIR );
        if ( outDirectory != null ) {
            Path path = Paths.get( outDirectory );
            if ( !exists( path ) ) {
                return createDirectory( path );
            }
            if ( isDirectory( path ) ) {
                return path;
            } else {
                throw new IllegalArgumentException( "Passed output directory exists but is not a directory" );
            }
        }

        return createTempDirectory( "validationresults" );
    }

    private static TYPE determineType( CommandLine cmdline ) {
        String type = cmdline.getOptionValue( OPT_TYPE );
        if ( type == null )
            return TYPE.VALIDATE;
        return TYPE.valueOf( type.toUpperCase() );
    }

    private XPlan41ToXPlan51Converter createConverter( String workspaceName, String configurationDirectory )
                    throws ResourceInitException, ConfigurationException {
        Workspace workspace = initWorkspace( workspaceName );
        ManagerConfiguration managerConfiguration = createManagerConfiguration( configurationDirectory );
        XPlanDao xplanDao = createXplanDao( workspace, managerConfiguration );
        HaleXplan41ToXplan51Transformer transformer = new HaleXplan41ToXplan51Transformer(
                        managerConfiguration.getPathToHaleCli(), managerConfiguration.getPathToHaleProjectDirectory() );
        XPlanGmlTransformer xPlanGmlTransformer = new XPlanGmlTransformer( transformer );
        return new XPlan41ToXPlan51Converter( xplanDao, xPlanGmlTransformer );
    }

    private static Workspace initWorkspace( String workspaceName )
                    throws ResourceInitException {
        DeegreeWorkspace workspace = DeegreeWorkspace.getInstance( workspaceName );
        workspace.initAll();
        return workspace.getNewWorkspace();
    }

    private static XPlanDao createXplanDao( Workspace workspace, ManagerConfiguration managerConfiguration ) {
        CategoryMapper categoryMapper = new CategoryMapper( managerConfiguration );
        ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper( workspace,
                                                                                       managerConfiguration );
        return new XPlanDao( managerWorkspaceWrapper, categoryMapper, managerConfiguration );
    }

    private static ManagerConfiguration createManagerConfiguration( String configurationFilePathVariable )
                    throws ConfigurationException {
        Path file = configurationFilePathVariable != null ? Paths.get( configurationFilePathVariable ) : null;
        ConfigurationDirectoryPropertiesLoader loader = new ConfigurationDirectoryPropertiesLoader( file );
        return new ManagerConfiguration( loader );
    }

    private static Options initOptions() {
        Options opts = new Options();

        Option opt = new Option( "w", OPT_WORKSPACE_NAME, true,
                                 "Default: xplan-manager-workspace. Name of the manager workspace pointing to the database to update "
                                 + "(must be located in the deegree workspace directory, usually .deegree)" );
        opt.setRequired( false );
        opts.addOption( opt );

        opt = new Option( "c", OPT_CONFIG_DIR, true, "the directory containing the manager configuration" );
        opt.setRequired( true );
        opts.addOption( opt );

        opt = new Option( "t", OPT_TYPE, true,
                          "one of 'VALIDATE' or 'CONVERT'; 'VALIDATE' validates all available XPlanGML 4.1 plans and writes the results (default if missing), 'CONVERT' transforms the available XPlanGML 4.1 plans, inserts the valid plans in the XPlan 5.1 datastore and removes the from XPlan 4.1 datastore" );
        opt.setRequired( false );
        opts.addOption( opt );

        opt = new Option( "f", OPT_OUT_DIR, true,
                          "directory to write the validation results into, directory will be created if it not exists (if missing a new tmp directory is created)" );
        opt.setRequired( false );
        opts.addOption( opt );

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        String help = "Reads all Plans from XPlan 4.1 feature store, transforms them to XPlanGML 5.1 and inserts them in the XPlan 5.1 feature store.";
        CommandUtils.printHelp( options, "xplan41To51converter", help, null );
    }

}
