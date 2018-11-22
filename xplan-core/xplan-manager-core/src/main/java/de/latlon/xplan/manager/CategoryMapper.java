package de.latlon.xplan.manager;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.archive.LocalCenterToDistrictMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;

/**
 * Maps a part to the category.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CategoryMapper implements LocalCenterToDistrictMapper {

    private static final Logger LOG = LoggerFactory.getLogger( CategoryMapper.class );

    private final Map<String, List<String>> categoryMapping;

    /**
     * @param managerConfiguration
     *            provides access to the mapping, never <code>null</code>
     * @throws NullPointerException
     *             - managerConfiguration is <code>null</code>
     */
    public CategoryMapper( ManagerConfiguration managerConfiguration ) {
        this.categoryMapping = managerConfiguration.getCategoryMapping();
    }

    /**
     * Does not check if a part is assigned to more than one category (first one is returned)!
     * 
     * @param part
     *            may be <code>null</code>
     * @return the category the part is assigned to, code>null</code> if no category could be found or part is
     *         <code>null</code>
     * @throws NullPointerException
     *             - part is <code>null</code>
     */
    public String mapToCategory( String part ) {
        LOG.debug( "Map part {} to category.", part );
        if ( part != null )
            for ( Entry<String, List<String>> category : categoryMapping.entrySet() ) {
                for ( String categoryPart : category.getValue() ) {
                    if ( part.equals( categoryPart ) ) {
                        LOG.info( "Found category: {}", category.getKey() );
                        return category.getKey();
                    }
                }
            }
        LOG.debug( "No category found." );
        return null;
    }

    @Override
    public String mapToDistrict( String localCenter ) {
        return mapToCategory( localCenter );
    }

}