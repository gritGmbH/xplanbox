package de.latlon.xplan.manager.web.shared.edit;

/**
 * Used to discriminate references types.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public enum ReferenceType {

    REASON( "1010" ), LEGISLATION_PLAN( "1030" ), GREEN_STRUCTURES_PLAN( "2300" );

    private String xplan50type;

    ReferenceType( String xplan50type ) {
        this.xplan50type = xplan50type;
    }

    public static ReferenceType getByXPlan50Type( String type ) {
        if ( type == null )
            return null;
        for ( ReferenceType referenceType : values() ) {
            if ( referenceType.xplan50type.equals( type.trim() ) )
                return referenceType;
        }
        return null;
    }

    public String getXplan50Type() {
        return xplan50type;
    }

}