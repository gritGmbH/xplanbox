package de.latlon.xplan.manager.cli;

import static de.latlon.xplan.manager.cli.XPlanManagerCLI.printUsage;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;

/**
 * Kommandozeilen-Frontend zum Bearbeiten der Raster-Layer-Kategorien.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanRasterManagerCLI {

    public static void main( String[] args )
                    throws Exception {
        if ( args.length < 1 )
            printUsage();
        String mode = args[0];
        switch ( mode ) {
        case "-addlayer":
            addLayer( args );
            break;
        case "-removelayer":
            removeLayer( args );
            break;
        case "-addcategory":
            addCategory( args );
            break;
        case "-removecategory":
            removeCategory( args );
            break;
        case "-movelayer":
            moveLayer( args );
            break;
        default:
            System.out.println( "Unbekannte Option: " + mode );
            printUsage();
        }
    }

    private static void moveLayer( String[] args )
                    throws Exception {
        if ( args.length < 3 ) {
            printUsage();
        }
        String type = args[1];
        String layer = args[2];
        String cat = null;
        if ( args.length == 4 ) {
            cat = args[3];
        }
        new XPlanRasterManager().moveLayer( type, layer, cat );
    }

    private static void removeCategory( String[] args )
                    throws Exception {
        if ( args.length != 3 ) {
            printUsage();
        }
        String type = args[1];
        String name = args[2];
        new XPlanRasterManager().removeCategory( type, name );
    }

    private static void addCategory( String[] args )
                    throws Exception {
        if ( args.length < 4 || args.length > 5 ) {
            printUsage();
        }
        String type = args[1];
        String uppercategory = null;
        String name;
        String title;
        if ( args.length == 4 ) {
            name = args[2];
            title = args[3];
        } else {
            uppercategory = args[2];
            name = args[3];
            title = args[4];

        }
        new XPlanRasterManager().addCategory( type, name, title, uppercategory );
    }

    private static void removeLayer( String[] args )
                    throws Exception {
        if ( args.length != 3 ) {
            printUsage();
        }
        String type = args[1];
        String name = args[2];
        new XPlanRasterManager().removeLayer( type, name );
    }

    private static void addLayer( String[] args )
                    throws Exception {
        if ( args.length < 6 || args.length > 7 ) {
            printUsage();
        }
        String type = args[1];
        String planid = args[2];
        String tiffid = args[3];
        String name = args[4];
        String title = args[5];
        String category = null;
        if ( args.length == 7 ) {
            category = args[6];
        }
        new XPlanRasterManager().addLayer( type, planid, tiffid, name, title, category );
    }
}
