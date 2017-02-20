package de.latlon.xplan.update.from_1_0_to_1_3_1;

class PlanTableMetadata {

    private final String planTableName;

    private final String bereichTableName;

    private final String dateColumnName;
    
    

    public PlanTableMetadata( String planTableName,  String bereichTableName, String dateColumnName ) {
        this.planTableName = planTableName;
        this.bereichTableName = bereichTableName;
        this.dateColumnName = dateColumnName;
    }

    /**
     * @return the planTableName
     */
    public String getPlanTableName() {
        return planTableName;
    }

    /**
     * @return the dateColumnName
     */
    public String getDateColumnName() {
        return dateColumnName;
    }

    /**
     * @return the bereichTableName
     */
    public String getBereichTableName() {
        return bereichTableName;
    }

}