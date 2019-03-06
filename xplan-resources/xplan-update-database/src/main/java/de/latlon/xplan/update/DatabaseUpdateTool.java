package de.latlon.xplan.update;

import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_PRE1_0_to_1_0;
import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_1_0_to_1_3_1;
import static java.util.Arrays.asList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;
import org.deegree.workspace.Workspace;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION;

/**
 * Main entry point to update xplan data in databases. Schema must be updated already.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DatabaseUpdateTool {

    private static final String OPT_WORKSPACE_NAME = "workspaceName";

    private static final String OPT_CONFIG_DIR = "configurationDirectory";

    private static final String OPT_VERSION = "updateVersion";

    public static void main( String[] args )
                    throws ConfigurationException {
        if ( ( args.length > 0 && ( args[0].contains( "help" ) || args[0].contains( "?" ) ) ) ) {
            printHelp( initOptions() );
        }

        try {
            CommandLine cmdline = new PosixParser().parse( initOptions(), args );
            try {
                String workspaceName = cmdline.getOptionValue( OPT_WORKSPACE_NAME );
                if ( workspaceName == null || workspaceName.isEmpty() )
                    workspaceName = "xplan-manager-workspace";
                String configurationDirectory = cmdline.getOptionValue( OPT_CONFIG_DIR );

                List<UPDATE_VERSION> version = parseUpdateVersion( cmdline );

                DatabaseUpdateTool tool = new DatabaseUpdateTool();
                tool.run( workspaceName, configurationDirectory, version );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } catch ( ParseException exp ) {
            System.err.println( "Could nor parse command line" );
            exp.printStackTrace();
        }

    }

    private static Options initOptions() {
        Options opts = new Options();

        Option opt = new Option( "w", OPT_WORKSPACE_NAME, true,
                        "Default: xplan-manager-workspace. Name of the manager workspace pointing to the database to update "
                                        + "(must be located in the deegree workspace directory, usually .deegree)" );
        opt.setRequired( false );
        opts.addOption( opt );

        opt = new Option( "c", OPT_CONFIG_DIR, true, "the directory containing the manager configuration" );
        opt.setRequired( false );
        opts.addOption( opt );

        opt = new Option( "u", OPT_VERSION, true,
                        "the update version(s), must be 1 (pre1.0 to 1.0) or 2 (1.0 to 1.3.1); "
                                        + "a comma separeated list is possible; if missing all updates are executed." );
        opt.setRequired( false );
        opts.addOption( opt );

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        String help = "Update database.";
        CommandUtils.printHelp( options, DatabaseUpdateTool.class.getSimpleName(), help, null );
    }

    private void run( String workspaceName, String configurationFilePathVariable, List<UPDATE_VERSION> version )
                    throws Exception {
        Workspace workspace = initWorkspace( workspaceName );
        ManagerConfiguration managerConfiguration = new ManagerConfiguration( null );
        ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper( workspace,
                                                                                       managerConfiguration );
        XPlanDao xplanDao = createXplanDao( configurationFilePathVariable, managerWorkspaceWrapper );
        DatabaseDataUpdater dataUpdater = new DatabaseDataUpdater( xplanDao, managerWorkspaceWrapper );
        dataUpdater.updateData( version );
    }

    private static XPlanDao createXplanDao( String configurationFilePathVariable, ManagerWorkspaceWrapper managerWorkspaceWrapper )
                    throws ConfigurationException {
        Path file = configurationFilePathVariable != null ? Paths.get( configurationFilePathVariable ) : null;
        ConfigurationDirectoryPropertiesLoader loader = new ConfigurationDirectoryPropertiesLoader( file );
        ManagerConfiguration managerConfiguration = new ManagerConfiguration( loader );
        CategoryMapper categoryMapper = new CategoryMapper( managerConfiguration );
        return new XPlanDao( managerWorkspaceWrapper, categoryMapper, managerConfiguration );
    }

    private static Workspace initWorkspace( String workspaceName )
                    throws ResourceInitException {
        DeegreeWorkspace workspace = DeegreeWorkspace.getInstance( workspaceName );
        workspace.initAll();
        return workspace.getNewWorkspace();
    }

    private static List<UPDATE_VERSION> parseUpdateVersion( CommandLine cmdline ) {
        String updateVersion = cmdline.getOptionValue( OPT_VERSION );
        if ( updateVersion != null && !updateVersion.isEmpty() ) {
            List<UPDATE_VERSION> updateVersions = new ArrayList<DatabaseDataUpdater.UPDATE_VERSION>();
            String[] versions = updateVersion.split( "," );
            for ( String version : versions ) {
                if ( "1".equals( version ) )
                    updateVersions.add( FROM_PRE1_0_to_1_0 );
                if ( "2".equals( version ) )
                    updateVersions.add( FROM_1_0_to_1_3_1 );
            }
            return updateVersions;
        }
        return asList( UPDATE_VERSION.values() );

    }
}