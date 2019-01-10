package de.latlon.xplan.commons;

/**
 * Enumeration for easy differentiating of XPlanGML plan types.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public enum XPlanType {

    BP_Plan, FP_Plan, RP_Plan, LP_Plan, SO_Plan;

    /**
     * @param name
     *                 the name
     * @return the matching entry or <code>null</code> if the XPlanType enum has no entry with the specified name
     */
    public static XPlanType valueOfDefaultNull( String name ) {
        try {
            return XPlanType.valueOf( name );
        } catch ( IllegalArgumentException e ) {
            return null;
        }
    }

}
