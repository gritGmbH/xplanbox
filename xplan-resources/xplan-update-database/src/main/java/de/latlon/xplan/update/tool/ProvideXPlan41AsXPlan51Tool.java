package de.latlon.xplan.update.tool;

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

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ProvideXPlan41AsXPlan51Tool {

    private static final String OPT_WORKSPACE_NAME = "workspaceName";

    private static final String OPT_CONFIG_DIR = "configurationDirectory";

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

                ProvideXPlan41AsXPlan51Tool tool = new ProvideXPlan41AsXPlan51Tool();
                tool.run( workspaceName, configurationDirectory );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } catch ( ParseException exp ) {
            System.err.println( "Could nor parse command line" );
            exp.printStackTrace();
        }

    }

    private void run( String workspaceName, String configurationDirectory )
                    throws Exception {
        XPlan41ToXPlan51Converter converter = createConverter( workspaceName, configurationDirectory );
        converter.convertXPlan41ToXPlan51();
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

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        String help = "Reads all Plans from XPlan 4.1 feature store, transforms them to XPlanGML 5.1 and inserts them in the XPlan 5.1 feature store.";
        CommandUtils.printHelp( options, ReSynthesizerTool.class.getSimpleName(), help, null );
    }

}
