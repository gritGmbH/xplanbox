package de.latlon.xplan.manager.metadata.csw;

/**
 * Encapsulates data from metadata record
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanRecordMetadata {

    private final String recordId;

    private final String resourceIdentifier;

    /**
     * @param recordId
     *                 id of the record, never <code>null</code>
     * @param resourceIdentifier
     *                 resource identifier of the record, may be <code>null</code>
     */
    public PlanRecordMetadata( String recordId, String resourceIdentifier ) {
        this.recordId = recordId;
        this.resourceIdentifier = resourceIdentifier;
    }

    /**
     * @return id of the record, never <code>null</code>
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * @return resource identifier of the record, may be <code>null</code>
     */
    public String getResourceIdentifier() {
        return resourceIdentifier;
    }

}