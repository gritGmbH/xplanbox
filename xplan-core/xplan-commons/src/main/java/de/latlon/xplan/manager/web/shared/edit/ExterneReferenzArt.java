package de.latlon.xplan.manager.web.shared.edit;

/**
 * Used to discriminate XP_ExterneReferenzArt.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public enum ExterneReferenzArt {

    DOKUMENT( "Dokument" ), PLANMITGEOREFERENZ( "PlanMitGeoreferenz" );

    private String code;

    ExterneReferenzArt( String code ) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ExterneReferenzArt getByCode( String code ) {
        if ( code == null )
            return null;
        for ( ExterneReferenzArt value : values() ) {
            if ( value.code.equals( code.trim() ) )
                return value;
        }
        return null;
    }

}