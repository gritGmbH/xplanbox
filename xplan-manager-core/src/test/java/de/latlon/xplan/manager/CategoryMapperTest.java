package de.latlon.xplan.manager;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CategoryMapperTest {

    @Test
    public void testMapToCategoryFromSingletonCategoryShoudBeFound()
                            throws Exception {
        CategoryMapper categoryMapper = new CategoryMapper( mockManagerConfiguration() );
        String part = categoryMapper.mapToCategory( "A F" );

        assertThat( part, is( "Cat1" ) );
    }

    @Test
    public void testMapToCategoryFromMultipleCategoryShoudBeFound()
                            throws Exception {
        CategoryMapper categoryMapper = new CategoryMapper( mockManagerConfiguration() );
        String part = categoryMapper.mapToCategory( "1" );

        assertThat( part, is( "Cat2" ) );
    }

    @Test
    public void testMapToCategoryWithNullPartShoudReturnNull()
                            throws Exception {
        CategoryMapper categoryMapper = new CategoryMapper( mockManagerConfiguration() );
        String part = categoryMapper.mapToCategory( null );

        assertThat( part, is( nullValue() ) );
    }

    @Test
    public void testMapToCategoryWithUnknownPartShoudNotBeFound()
                            throws Exception {
        CategoryMapper categoryMapper = new CategoryMapper( mockManagerConfiguration() );
        String part = categoryMapper.mapToCategory( "notKnown" );

        assertThat( part, is( nullValue() ) );
    }

    private ManagerConfiguration mockManagerConfiguration() {
        ManagerConfiguration managerConfiguration = mock( ManagerConfiguration.class );
        Map<String, List<String>> mapping = new HashMap<String, List<String>>();
        mapping.put( "Cat1", Collections.singletonList( "A F" ) );
        List<String> cat2Parts = new ArrayList<String>();
        cat2Parts.add( "1" );
        cat2Parts.add( "7" );
        mapping.put( "Cat2", cat2Parts );
        Mockito.when( managerConfiguration.getCategoryMapping() ).thenReturn( mapping );

        return managerConfiguration;
    }

}