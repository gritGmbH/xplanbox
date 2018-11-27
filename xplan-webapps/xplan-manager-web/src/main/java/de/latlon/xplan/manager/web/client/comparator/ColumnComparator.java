package de.latlon.xplan.manager.web.client.comparator;

import static java.lang.Integer.parseInt;

import java.util.Comparator;
import java.util.Date;

import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.XPlanMetadata;

/**
 * Compares different columns (name, id, type, additional type, legislation status, release date, import date and ade).
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ColumnComparator implements Comparator<XPlan> {

    private static final int IS_EQUAL = 0;

    private static final int IS_GREATER = 1;

    private static final int IS_SMALLER = -1;

    private PlanListColumnType type;

    /**
     * @param type
     *            indicating the column to compare, never <code>null</code>
     * @throws NullPointerException
     *             if type is <code>null</code>
     */
    public ColumnComparator( PlanListColumnType type ) {
        if ( type == null )
            throw new NullPointerException();
        this.type = type;
    }

    @Override
    public int compare( XPlan first, XPlan second ) {
        if ( first == null && second == null )
            return IS_EQUAL;
        if ( first == null )
            return IS_SMALLER;
        if ( second == null )
            return IS_GREATER;
        if ( first.equals( second ) )
            return IS_EQUAL;
        switch ( type ) {
        case NAME:
            return compareString( first.getName(), second.getName() );
        case ID:
            Integer firstIdNumber = parseInt( first.getId() );
            Integer secondIdNumber = parseInt( second.getId() );
            return firstIdNumber.compareTo( secondIdNumber );
        case NUMBER:
            return compareString( first.getNumber(), second.getNumber() );
        case TYPE:
            return compareString( first.getType(), second.getType() );
        case ADDITIONALTYPE:
            return compareString( first.getAdditionalType(), second.getAdditionalType() );
        case LEGISLATIONSTATUS:
            return compareString( first.getLegislationStatus(), second.getLegislationStatus() );
        case RELEASEDATE:
            return compareDate( first.getReleaseDate(), second.getReleaseDate() );
        case IMPORTDATE:
            return compareDate( first.getImportDate(), second.getImportDate() );
        case PLANSTATUS:
            String firstPlanStatus = retrievePlanStatus( first );
            String secondPlanStatus = retrievePlanStatus( second );
            return compareString( firstPlanStatus, secondPlanStatus );
        case ADE:
            return compareString( first.getAde(), second.getAde() );
        }
        return IS_SMALLER;
    }

    private int compareString( String first, String second ) {
        return first == null ? ( second == null ? IS_EQUAL : IS_SMALLER )
                            : ( second == null ? IS_GREATER : first.compareTo( second ) );
    }

    private int compareDate( Date first, Date second ) {
        return first == null ? ( second == null ? IS_EQUAL : IS_SMALLER )
                            : ( second == null ? IS_GREATER : first.compareTo( second ) );
    }

    private String retrievePlanStatus( XPlan plan ) {
        XPlanMetadata xPlanMetadata = plan.getXplanMetadata();
        if ( xPlanMetadata != null && xPlanMetadata.getPlanStatus() != null )
            return xPlanMetadata.getPlanStatus().getMessage();
        return null;
    }

}
