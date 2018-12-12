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

}