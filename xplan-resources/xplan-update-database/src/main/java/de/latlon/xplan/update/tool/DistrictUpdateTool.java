package de.latlon.xplan.update.tool;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.DistrictUpdater;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.update.AbstractUpdater;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Update tool to update the district of all plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DistrictUpdateTool {

    private static final Logger LOG = LoggerFactory.getLogger( DistrictUpdateTool.class );

    private static final String OPT_WORKSPACE_NAME = "workspaceName";

    private static final String OPT_CONFIG_DIR = "configurationDirectory";

    /**
     * @param args
     *                 may be null
     */
    public static void main( String[] args ) {
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

                DistrictUpdateTool tool = new DistrictUpdateTool();
                tool.run( workspaceName, configurationDirectory );
            } catch ( Exception e ) {
                LOG.error( "DistrictUpdateTool could not be executed!", e );
            }
        } catch ( ParseException exp ) {
            System.err.println( "Could nor parse command line" );
            exp.printStackTrace();
        }

    }

    private void run( String workspaceName, String configurationDirectory )
                    throws Exception {
        Workspace workspace = initWorkspace( workspaceName );
        XPlanDao xplanDao = createXplanDao( workspace, configurationDirectory );
        DistrictUpdater updater = new DistrictUpdater( xplanDao );
        updater.updateDistricts();
    }

    private static Workspace initWorkspace( String workspaceName )
                            throws ResourceInitException {
        DeegreeWorkspace workspace = DeegreeWorkspace.getInstance( workspaceName );
        File location = workspace.getLocation();
        LOG.info( "Initialise Workspace " + location );
        workspace.initAll();
        return workspace.getNewWorkspace();
    }

    private static XPlanDao createXplanDao( Workspace workspace, String configurationFilePathVariable )
                    throws ConfigurationException {
        Path file = configurationFilePathVariable != null ? Paths.get( configurationFilePathVariable ) : null;
        ConfigurationDirectoryPropertiesLoader loader = new ConfigurationDirectoryPropertiesLoader( file );
        ManagerConfiguration managerConfiguration = new ManagerConfiguration( loader );
        CategoryMapper categoryMapper = new CategoryMapper( managerConfiguration );
        ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper( workspace,
                                                                                       managerConfiguration );
        return new XPlanDao( managerWorkspaceWrapper, categoryMapper, managerConfiguration );
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

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        String help = "Update column district of table xplanmgr.plans.";
        CommandUtils.printHelp( options, DistrictUpdateTool.class.getSimpleName(), help, null );
    }

}
