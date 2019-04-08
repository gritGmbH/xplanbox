package de.latlon.xplan.update.tool;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.ServiceMetadataRecordCreator;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
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
 * Tool to re-synthesize all or single plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataRecordCreationTool {

    private static final String OPT_WORKSPACE_NAME = "workspaceName";

    private static final String OPT_CONFIG_DIR = "configurationDirectory";

    private static final String OPT_MGR_ID = "mgrId";

    public static void main( String[] args ) {
        if ( args.length == 0 || ( args[0].contains( "help" ) || args[0].contains( "?" ) ) ) {
            printHelp( initOptions() );
        }

        try {
            CommandLine cmdline = new PosixParser().parse( initOptions(), args );
            try {
                String workspaceName = cmdline.getOptionValue( OPT_WORKSPACE_NAME );
                if ( workspaceName == null || workspaceName.isEmpty() )
                    workspaceName = "xplan-manager-workspace";
                String configurationDirectory = cmdline.getOptionValue( OPT_CONFIG_DIR );
                String mgrId = cmdline.getOptionValue( OPT_MGR_ID );

                ServiceMetadataRecordCreationTool tool = new ServiceMetadataRecordCreationTool();
                tool.run( workspaceName, configurationDirectory, mgrId );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        } catch ( ParseException exp ) {
            System.err.println( "Could nor parse command line" );
            exp.printStackTrace();
        }

    }

    private void run( String workspaceName, String configurationDirectory, String mgrId )
                    throws Exception {
        ServiceMetadataRecordCreator serviceMetadataRecordCreator = createServiceMetadataRecordCreator( workspaceName,
                                                                                                        configurationDirectory );
        if ( mgrId == null ) {
            serviceMetadataRecordCreator.createServiceMetadataRecords();
        } else {
            try {
                int mgrIdInt = Integer.parseInt( mgrId );
                serviceMetadataRecordCreator.createServiceMetadataRecords( mgrIdInt );
            } catch ( NumberFormatException e ) {
                System.out.println( "Invalid manager id (parameter -i), '" + mgrId + "' is not an integer value" );
            }
        }
    }

    private ServiceMetadataRecordCreator createServiceMetadataRecordCreator( String workspaceName,
                                                                             String configurationDirectory )
                    throws ResourceInitException, ConfigurationException, DataServiceCouplingException {
        Workspace workspace = initWorkspace( workspaceName );
        ManagerConfiguration managerConfiguration = createManagerConfiguration( configurationDirectory );
        XPlanDao xplanDao = createXplanDao( workspace, managerConfiguration );
        return new ServiceMetadataRecordCreator( xplanDao, managerConfiguration );
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

        opt = new Option( "i", OPT_MGR_ID, true,
                          "The ID of a plan in the XPlanManager (manager-ID) of the plan to create the service metadataset for. If missing all plans are processed" );
        opt.setRequired( false );
        opts.addOption( opt );

        CommandUtils.addDefaultOptions( opts );
        return opts;
    }

    private static void printHelp( Options options ) {
        String help = "Creates service metadata records for all or a single plan and stores additional information written to the XPlanWerkWMS capabilities document";
        CommandUtils.printHelp( options, ServiceMetadataRecordCreationTool.class.getSimpleName(), help, null );
    }

}