package de.latlon.xplan.manager.web.shared;

/**
 * Encapsulates results of the plan name.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanNameWithStatusResult {

    private String name;

    private String status;

    private boolean planWithSameNameAndStatusExists;

    public PlanNameWithStatusResult() {
    }

    public PlanNameWithStatusResult( String name, String status, boolean planWithSameNameAndStatusExists ) {
        this.name = name;
        this.status = status;
        this.planWithSameNameAndStatusExists = planWithSameNameAndStatusExists;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus( String status ) {
        this.status = status;
    }

    public boolean isPlanWithSameNameAndStatusExists() {
        return planWithSameNameAndStatusExists;
    }

    public void setPlanWithSameNameAndStatusExists( boolean planWithSameNameAndStatusExists ) {
        this.planWithSameNameAndStatusExists = planWithSameNameAndStatusExists;
    }
}