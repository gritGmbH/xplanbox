package de.latlon.xplan.manager.web.shared;

import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Encapsulates a manager web configuration
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class ManagerWebConfiguration implements Serializable {

    private static final long serialVersionUID = -6690846114049627139L;

    private boolean internalIdActivated;

    private boolean legislationStatusActivated;

    private boolean validityPeriodActivated;

    private boolean editorActivated;

    private boolean publishingInspirePluActivated;

    private String crsDialogDefaultCrs;

    private String[] crsDialogChooseCrs;

    private String[] categoryFilterValues;

    private String[] hiddenColumns;

    /**
     * Instantiates a {@link ManagerWebConfiguration} with default values.
     */
    public ManagerWebConfiguration() {
        this( false, false, false, false, false, null, new String[] {}, new String[] {}, new String[] {} );
    }

    /**
     * @param internalIdActivated
     *            <code>true</code> if the interal id dialog should be activated, <code>false</code> otherwise
     * @param legislationStatusActivated
     *            <code>true</code> if the dialog to select the legislation status should be activated,
     *            <code>false</code> otherwise
     * @param validityPeriodActivated
     *            <code>true</code> if the dialog to select the validity period of a plan should be activated,
     *            <code>false</code> otherwise
     * @param editorActivated
     *            <code>true</code> if editing of plans is activated, <code>false</code> otherwise
     * @param crsDialogDefaultCrs
     *            default crs of the crs dialog, never <code>null</code>
     * @param crsDialogChooseCrs
     *            list of possible crs for the crs dialog, never <code>null</code>
     * @param categoryFilterValues
     *            list of categories used for filtering, never <code>null</code>
     */
    public ManagerWebConfiguration( boolean internalIdActivated, boolean legislationStatusActivated,
                                    boolean validityPeriodActivated, boolean editorActivated,
                                    boolean publishingInspirePluActivated, String crsDialogDefaultCrs,
                                    String[] crsDialogChooseCrs, String[] categoryFilterValues, String[] hiddenColumns ) {
        this.internalIdActivated = internalIdActivated;
        this.legislationStatusActivated = legislationStatusActivated;
        this.validityPeriodActivated = validityPeriodActivated;
        this.editorActivated = editorActivated;
        this.publishingInspirePluActivated = publishingInspirePluActivated;
        this.crsDialogDefaultCrs = crsDialogDefaultCrs;
        this.crsDialogChooseCrs = crsDialogChooseCrs;
        this.categoryFilterValues = categoryFilterValues;
        this.hiddenColumns = hiddenColumns;
    }

    /**
     * @return <code>true</code> if the dialog to select the legislation status should be activated, <code>false</code>
     *         otherwise
     */
    public boolean getInternalIdActivated() {
        return internalIdActivated;
    }

    /**
     * @return <code>true</code> if the dialog to select the legislation status should be activated, <code>false</code>
     *         otherwise
     */
    public boolean isLegislationStatusActivated() {
        return legislationStatusActivated;
    }

    /**
     * @return <code>true</code> if the dialog to select the validity period of a plan should be activated,
     *         <code>false</code> otherwise
     */
    public boolean isValidityPeriodActivated() {
        return validityPeriodActivated;
    }

    /**
     * @return <code>true</code> if editing of plans is activated, <code>false</code> otherwise
     */
    public boolean isEditorActivated() {
        return editorActivated;
    }

    /**
     * @return <code>true</code> if publishing of plans as INSPIRE PLU datasets is activated, <code>false</code>
     *         otherwise
     */
    public boolean isPublishingInspirePluActivated() {
        return publishingInspirePluActivated;
    }

    /**
     * @return default crs of the crs dialog, never <code>null</code>
     */
    public String getCrsDialogDefaultCrs() {
        return crsDialogDefaultCrs;
    }

    /**
     * @return list of possible crs for the crs dialog, never <code>null</code>
     */
    public String[] getCrsDialogChooseCrs() {
        if ( crsDialogChooseCrs == null )
            return new String[] {};
        return crsDialogChooseCrs;
    }

    /**
     * @return list of categories used for filtering, never <code>null</code>
     */
    public String[] getCategoryFilterValues() {
        if ( categoryFilterValues == null )
            return new String[] {};
        return categoryFilterValues;
    }

    /**
     * @return list of hidden columns, never <code>null</code>
     */


    /**
     * @param planListColumnType
     * @return
     */
    public boolean isColumnVisible( PlanListColumnType planListColumnType ) {
        for ( String hiddenColumn : hiddenColumns ) {
            if ( hiddenColumn != null && hiddenColumn.trim().equalsIgnoreCase( planListColumnType.name() ) )
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode( categoryFilterValues );
        result = prime * result + Arrays.hashCode( crsDialogChooseCrs );
        result = prime * result + Arrays.hashCode( hiddenColumns );
        result = prime * result + ( ( crsDialogDefaultCrs == null ) ? 0 : crsDialogDefaultCrs.hashCode() );
        result = prime * result + ( editorActivated ? 1231 : 1237 );
        result = prime * result + ( internalIdActivated ? 1231 : 1237 );
        result = prime * result + ( legislationStatusActivated ? 1231 : 1237 );
        result = prime * result + ( validityPeriodActivated ? 1231 : 1237 );
        result = prime * result + ( publishingInspirePluActivated ? 1231 : 1237 );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        ManagerWebConfiguration other = (ManagerWebConfiguration) obj;
        if ( !Arrays.equals( categoryFilterValues, other.categoryFilterValues ) )
            return false;
        if ( !Arrays.equals( crsDialogChooseCrs, other.crsDialogChooseCrs ) )
            return false;
        if ( !Arrays.equals( hiddenColumns, other.hiddenColumns ) )
            return false;
        if ( crsDialogDefaultCrs == null ) {
            if ( other.crsDialogDefaultCrs != null )
                return false;
        } else if ( !crsDialogDefaultCrs.equals( other.crsDialogDefaultCrs ) )
            return false;
        if ( editorActivated != other.editorActivated )
            return false;
        if ( internalIdActivated != other.internalIdActivated )
            return false;
        if ( legislationStatusActivated != other.legislationStatusActivated )
            return false;
        if ( validityPeriodActivated != other.validityPeriodActivated )
            return false;
        if ( publishingInspirePluActivated != other.publishingInspirePluActivated )
            return false;
        return true;
    }
}