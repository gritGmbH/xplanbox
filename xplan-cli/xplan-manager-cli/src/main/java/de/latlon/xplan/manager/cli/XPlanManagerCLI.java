package de.latlon.xplan.manager.cli;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import de.latlon.xplan.manager.log.SystemLog;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.persistence.FeatureStoreException;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;

/**
 * Kommandozeilen-Frontend zum Verwalten von XPlan-Archiven und zum Bearbeiten der Raster-Layer-Kategorien.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @see XPlanRasterManagerCLI
 * @since 1.0
 */
public class XPlanManagerCLI {

    /**
     * Usage
     * 
     * @param args
     * @throws Exception
     * @throws FeatureStoreException
     */
    public static void main( String[] args )
                    throws Exception {
        disableDerbyLog();
        if ( args.length < 1 ) {
            printUsage();
        }
        if ( "-v".equalsIgnoreCase( args[args.length - 1] ) )
            SystemLog.log();
        String mode = args[0];

        switch ( mode.toLowerCase() ) {
        case "-help":
            printUsage();
            break;
        case "-import":
            importOption( args, instantiateManager( args ), false );
            break;
        case "-export":
            exportOption( args, instantiateManager( args ) );
            break;
        case "-delete":
            deleteOption( args, instantiateManager( args ), false );
            break;
        case "-list":
            listOption( args, instantiateManager( args ) );
            break;
        case "-importmakeconfig":
            importOption( args, instantiateManager( args ), true );
            break;
        case "-deletewithconfig":
            deleteOption( args, instantiateManager( args ), true );
            break;
        case "-createdb":
            createDBOption( args, instantiateManager( args ) );
            break;
        case "-updatewmssortdate":
            createWmsSortDateOption( args, instantiateManager( args ) );
            break;
        default:
            XPlanRasterManagerCLI.main( args );
        }
    }

    private static XPlanManager instantiateManager( String[] args ) {
        Path directoryContainingTheManagerConfig = parseDirectoryFromArgs( args );
        System.out.println( "Die Konfigurationsdatei für den Manager (managerConfiguration.properties) wird im Verzeichnis "
                            + directoryContainingTheManagerConfig + " erwartet." );
        return createManager( directoryContainingTheManagerConfig );
    }

    private static XPlanManager createManager( Path directoryContainingTheManagerConfig ) {

        try {
            PropertiesLoader propertiesLoader = new ConfigurationDirectoryPropertiesLoader(
                            directoryContainingTheManagerConfig, ManagerConfiguration.class );
            ManagerConfiguration managerConfiguration = new ManagerConfiguration( propertiesLoader );
            CategoryMapper categoryMapper = new CategoryMapper( managerConfiguration );
            XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator( categoryMapper );
            WorkspaceReloader workspaceReloader = new WorkspaceReloader();
            return new XPlanManager( categoryMapper, archiveCreator, managerConfiguration, workspaceReloader, null,
                                     null );
        } catch ( Exception e ) {
            endWithFatalError( e.getMessage() );
        }
        return null;
    }

    private static void listOption( String[] args, XPlanManager manager ) {
        if ( args.length != 1 ) {
            printUsage();
        }
        try {
            List<XPlan> xPlanList = manager.list( true );
            printList( xPlanList );
        } catch ( Exception e ) {
            endWithFatalError( "Auflisten der Pläne fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage() );
        }
    }

    private static void deleteOption( String[] args, XPlanManager manager, boolean removeWMSConfig ) {
        if ( args.length < 2 || args.length > 4 ) {
            printUsage();
        }
        String planId = args[1];
        try {
            if ( args.length == 2 )
                manager.delete( planId, removeWMSConfig );
            else if ( args.length == 4 && "--workspace".equals( args[2] ) ) {
                File workspaceFolder = new File( args[3] );
                manager.delete( planId, removeWMSConfig, workspaceFolder );
            } else {
                printUsage();
            }
        } catch ( Exception e ) {
            endWithFatalError( "Löschen des Plans fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage() );
        }
        System.out.println( "XPlan " + planId + " wurde gelöscht." );
    }

    private static void exportOption( String[] args, XPlanManager manager ) {
        if ( args.length < 2 || args.length > 5 ) {
            printUsage();
        }
        String planId = args[1];
        String targetDir = ".";
        if ( args.length > 2 && !"--managerconfiguration".equals( args[2] ) ) {
            targetDir = args[2];
        }
        try {
            export( planId, targetDir, manager );
        } catch ( Exception e ) {
            endWithFatalError( "Export des Plans fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage() );
        }
    }

    private static void importOption( String[] args, XPlanManager manager, boolean makeWMSConfig ) {
        if ( args.length < 2 || args.length > 7 ) {
            printUsage();
        }
        int nextArg = 1;
        boolean force = false;
        if ( args.length >= nextArg + 1 ) {
            if ( "--force".equals( args[nextArg] ) ) {
                force = true;
                nextArg++;
            }
        }
        String fileName = null;
        if ( args.length >= nextArg + 1 ) {
            fileName = args[nextArg++];
        } else {
            printUsage();
        }
        ICRS defaultCRS = null;
        if ( args.length >= nextArg + 1 && "--crs".equals( args[nextArg] ) ) {
            try {
                nextArg++;
                CRSManager.lookup( args[nextArg] );
                defaultCRS = CRSManager.getCRSRef( args[nextArg] );
            } catch ( UnknownCRSException e ) {
                endWithFatalError( "Das angegebene CRS '" + args[nextArg] + "' ist unbekannt. Fehlermeldung: "
                                   + e.getLocalizedMessage() );
            }
            nextArg++;
        }
        try {
            if ( args.length == nextArg
                 || ( args.length >= nextArg + 1 && ( "--managerconfiguration".equals( args[nextArg] ) ) ) ) {
                importPlan( manager, makeWMSConfig, force, fileName, defaultCRS, null );
            } else if ( args.length >= nextArg + 1 && "--workspace".equals( args[nextArg] ) ) {
                nextArg++;
                File workspaceFolder = new File( args[nextArg] );
                importPlan( manager, makeWMSConfig, force, fileName, defaultCRS, workspaceFolder );
            } else {
                printUsage();
            }
        } catch ( Exception e ) {
            endWithFatalError( "Import des XPlan-Archivs fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage() );
        }
    }

    private static void importPlan( XPlanManager manager, boolean makeWMSConfig, boolean force, String fileName,
                                    ICRS defaultCRS, File workspaceFolder )
                                                    throws Exception {
        List<RasterEvaluationResult> evaluateRasterdata = manager.evaluateRasterdata( fileName );
        System.out.println( "Evaluationsergebniss von referenzierten Rasterdaten: " );
        boolean areAllValid = true;
        for ( RasterEvaluationResult result : evaluateRasterdata ) {
            boolean configuredCrs = result.isConfiguredCrs();
            boolean supportedImageFormat = result.isSupportedImageFormat();
            System.out.println( "  - Name: " + result.getRasterName() + " Unterstütztes CRS: "
                                + ( configuredCrs ? "Ja" : "Nein" ) + " Unterstütztes Bildformat: "
                                + ( supportedImageFormat ? "Ja" : "Nein" ) );
            if ( !configuredCrs || !supportedImageFormat )
                areAllValid = false;
        }
        boolean makeRasterConfig = true;
        if ( areAllValid ) {
            System.out.println( "Es existieren keine invaliden Rasterdaten" );
        } else {
            if ( !force ) {
                System.out.println( "Aufgrund invalider Rasterdaten wird der Import abgebrochen. Sie können den Import "
                                    + "ohne die Erzeugung von Rasterkonfigurationen erzwingen, indem Sie die Option --force angeben." );
                System.exit( 0 );
            }
            System.out.println( "Es existieren invaliden Rasterdaten. Da die Option --force gesetzt ist, "
                                + "wird der Plan importiert, es werden jedoch keine Konfigurationen für "
                                + "Rasterdaten geschrieben." );
            makeRasterConfig = true;
        }
        manager.importPlan( fileName, defaultCRS, force, makeWMSConfig, makeRasterConfig, workspaceFolder, null );
    }

    private static void createDBOption( String[] args, XPlanManager manager ) {
        if ( args.length != 7 && args.length != 9 ) {
            printUsage();
        }

        String dbName = args[1];
        String jdbcConn = args[2];
        String template = null;
        String user = null;
        String pw = null;

        for ( int i = 3; i < args.length; i += 2 ) {

            switch ( args[i] ) {
            case "-u":
                user = args[i + 1];
                break;
            case "-p":
                pw = args[i + 1];
                break;
            case "-t":
                template = args[i + 1];
                break;
            }

        }

        try {

            manager.createInitialDB( jdbcConn, dbName, template, user, pw );

        } catch ( Exception e ) {
            endWithFatalError( e.getMessage() );
        }
    }

    private static void createWmsSortDateOption( String[] args, XPlanManager manager ) {
        if ( args.length != 1 && args.length != 3 ) {
            printUsage();
        }
        try {
           manager.updateWmsSortDate();
        } catch ( Exception e ) {
            endWithFatalError( e.getMessage() );
        }
    }

    private static void export( String planId, String targetDir, XPlanManager manager )
                    throws Exception {

        File outputFile = new File( targetDir, "xplan-exported-" + planId + ".zip" );
        if ( outputFile.exists() ) {
            endWithFatalError( "Kann Datei '" + outputFile.getName() + "' nicht erzeugen. Datei existiert bereits." );
        }

        try {
            outputFile.createNewFile();
            OutputStream outputStream = new FileOutputStream( outputFile );
            manager.export( planId, outputStream );
        } catch ( IOException e ) {
            endWithFatalError( "Kann FileOutputStream nicht erzeugen: " + e.getMessage() );
        }
        System.out.print( "XPlan " + planId + " wurde nach '" + outputFile.getName() + "' exportiert." );

    }

    private static void printList( List<XPlan> xpList ) {

        System.out.println( "Anzahl Pläne: " + xpList.size() );
        for ( XPlan plan : xpList ) {
            System.out.print( "- Id: " + plan.getId() );
            System.out.print( ", Version: " + plan.getVersion() );
            System.out.print( ", Typ: " + plan.getType() );
            System.out.print( ", Name: " + plan.getName() );
            System.out.print( ", Nummer: " + plan.getNumber() );
            if ( plan.getGkz() != null ) {
                System.out.print( ", GKZ: " + plan.getGkz() );
            }
            System.out.print( ", Features: " + plan.getNumFeatures() );
            System.out.print( ", Raster: " + ( plan.isRaster() ? "ja" : "nein" ) );
            System.out.print( ", Veröffentlichungsdatum: " + plan.getReleaseDate() );
            System.out.println( ", Importiert: " + plan.getImportDate() );
        }
    }

    private static void disableDerbyLog() {
        // avoid annoying creation of derby.log (because of internal CRS database)
        System.setProperty( "derby.stream.error.method", "de.latlon.xplan.manager.XPlanManager.disableDerbyLogFile" );
    }

    private static Path parseDirectoryFromArgs( String[] args ) {
        for ( int i = 0; i < args.length; i++ ) {
            if ( "--managerconfiguration".equals( args[i] ) && args.length > i + 1 ) {
                String pathToDirectory = args[i + 1];
                Path managerConfigurationDirectory = Paths.get( pathToDirectory );
                if ( Files.exists( managerConfigurationDirectory ) && Files.isDirectory( managerConfigurationDirectory ) )
                    return managerConfigurationDirectory;
            }
        }
        String path = XPlanManagerCLI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarLocation = new File( path );
        return Paths.get( jarLocation.getParentFile().getParent()).resolve( "etc" );
    }

    static void printUsage() {
        System.out.println( "Usage: XPlanManager <options>" );
        System.out.println();
        System.out.println( " -help" );
        System.out.println( " -list" );
        System.out.println( " -delete   <planid>" );
        System.out.println( " -import   [--force] <xplanarchiv> [--crs <CRS>] [--workspace <workspace verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]" );
        System.out.println( " -export   <planid> [<verzeichnis>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]" );
        System.out.println();
        System.out.println( "Alternativer Betriebsmodus:" );
        System.out.println();
        System.out.println( " -deletewithconfig   <planid> [--workspace <workspace verzeichnis>]" );
        System.out.println( " -importmakeconfig   [--force] <xplanarchiv> [--crs <CRS>] [--workspace <workspace verzeichnis>] [--crs <CRS>] [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]" );
        System.out.println( " -createdb <DB Name> <JDBC-Connection> -u <user> -p <passwort> [-t <PostGis Template>]" );
        System.out.println( " -updateWmsSortDate [--managerconfiguration <PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]" );
        System.out.println();
        System.out.println( "Raster-Operationen:" );
        System.out.println();
        System.out.println( " -addlayer <bplan|lplan|rplan|fplan|soplan> <rasterplanid> "
                            + "<tiffid> <layername> <layertitle> [<categoryname>]" );
        System.out.println( " -removelayer <bplan|lplan|rplan|fplan|soplan> <layername>" );
        System.out.println( " -addcategory <bplan|lplan|rplan|fplan|soplan> [<uppercategory>] <categoryname> <categorytitle>" );
        System.out.println( " -removecategory <bplan|lplan|rplan|fplan|soplan> <categoryname>" );
        System.out.println( " -movelayer <bplan|lplan|rplan|fplan|soplan> <layername> <categoryname>" );
        System.exit( 0 );
    }

    /**
     * TODO refactoring required: change modifier to private, resolve usage by using types
     * 
     * @param msg
     */
    @Deprecated
    private static void endWithFatalError( String msg ) {
        System.out.println( msg );
        System.exit( 0 );
    }

}