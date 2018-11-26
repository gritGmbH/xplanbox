package de.latlon.xplan.commons.archive;

/**
 * Maps local centers to districts.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface LocalCenterToDistrictMapper {

    /**
     * Maps a localCenter to a district.
     * 
     * @param localCenter
     *            to map to a district, may be <code>null</code>
     * @return the district the local center is part of, may be <code>null</code>
     */
    String mapToDistrict( String localCenter );

}