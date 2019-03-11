package de.latlon.xplan.manager.wmsconfig;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * Contains convenience methods to write the wms configuration for a plan.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ConfigWriterUtils {

    private static final Map<XPlanType, String> typeToExtension = new HashMap<XPlanType, String>();

    static {
        typeToExtension.put( BP_Plan, "bplan" );
        typeToExtension.put( FP_Plan, "fplan" );
        typeToExtension.put( LP_Plan, "lplan" );
        typeToExtension.put( RP_Plan, "rplan" );
        typeToExtension.put( SO_Plan, "soplan" );
    }

    private ConfigWriterUtils() {
    }

    /**
     * Detects the type of the archive
     * 
     * @param type
     *            never <code>null</code>
     * @param planStatus
     *            may be <code>null</code> (same as FESTGESTELLT)
     * @return the type, never <code>null</code>
     * @throws IllegalArgumentException
     *             if the archive type is not supported
     */
    public static String detectType( XPlanType type, PlanStatus planStatus ) {
        if ( typeToExtension.containsKey( type ) ) {
            String extension = typeToExtension.get( type );
            if ( IN_AUFSTELLUNG.equals( planStatus ) )
                return extension + "pre";
            if ( ARCHIVIERT.equals( planStatus ) )
                return extension + "archive";
            return extension;
        }
        String msg = "Plan with type " + type + " is not supported for wms configuration creation!";
        throw new IllegalArgumentException( msg );
    }

    /**
     * @return a collection of all supported plan types
     */
    public static Collection<String> retrieveAllPlanTypes() {
        return typeToExtension.values();
    }

}