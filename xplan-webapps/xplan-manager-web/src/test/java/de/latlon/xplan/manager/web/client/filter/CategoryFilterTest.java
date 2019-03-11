package de.latlon.xplan.manager.web.client.filter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import de.latlon.xplan.manager.web.shared.XPlan;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CategoryFilterTest {

    private static final boolean NEGATIVE = true;

    private static final boolean POSITIVE = false;

    private static final String CATEGORY = "category";

    private static final String CATEGORY_LOWER_UPPER_CASE = "caTegOry";

    private final CategoryFilter categoryFilter = new CategoryFilter( CATEGORY );

    private final CategoryFilter categoryListNegativeFilter = new CategoryFilter( createCategoryList(), NEGATIVE );

    private final CategoryFilter categoryListPositiveFilter = new CategoryFilter( createCategoryList(), POSITIVE );

    @Test
    public void testIsMatchingWithFilterCategoryShouldMatch()
                            throws Exception {
        boolean matching = categoryFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, is( true ) );
    }

    @Test
    public void testIsMatchingWithFilterCategoryAndLowerUpperPlanShouldMatch()
                            throws Exception {
        boolean matching = categoryFilter.isMatching( createPlanWithLowerUpperCategory() );

        assertThat( matching, is( true ) );
    }

    @Test
    public void testIsMatchingWithoutFilterCategoryShouldNotMatch()
                            throws Exception {
        boolean matching = categoryFilter.isMatching( createPlan( "UNKNOWN" ) );

        assertThat( matching, is( false ) );
    }

    @Test
    public void testIsMatchingWithCategoryListPositiveShouldMatch()
                            throws Exception {
        boolean matching = categoryListPositiveFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, is( true ) );
    }

    @Test
    public void testIsMatchingWithCategoryListNegativeShouldNotMatch()
                            throws Exception {
        boolean matching = categoryListNegativeFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, is( false ) );
    }

    @Test
    public void testIsMatchingWithNullCategoryFilterShouldMatch()
                            throws Exception {
        CategoryFilter nullFilter = new CategoryFilter( null );
        boolean matching = nullFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, CoreMatchers.is( true ) );
    }

    @Test
    public void testIsMatchingWithEmptyCategoryAndPositiveFilterShouldMatch()
                            throws Exception {
        CategoryFilter emptyFilter = new CategoryFilter( Collections.<String> emptyList(), POSITIVE );
        boolean matching = emptyFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, CoreMatchers.is( true ) );
    }

    @Test
    public void testIsMatchingWithEmptyCategoryAndNegativFilterShouldMatch()
                            throws Exception {
        CategoryFilter emptyFilter = new CategoryFilter( Collections.<String> emptyList(), NEGATIVE );
        boolean matching = emptyFilter.isMatching( createPlanWithCategory() );

        assertThat( matching, CoreMatchers.is( true ) );
    }

    private List<String> createCategoryList() {
        ArrayList<String> categories = new ArrayList<String>();
        categories.add( "cate1" );
        categories.add( CATEGORY );
        return categories;
    }

    private XPlan createPlanWithCategory() {
        return createPlan( CATEGORY );
    }

    private XPlan createPlanWithLowerUpperCategory() {
        return createPlan( CATEGORY_LOWER_UPPER_CASE );
    }

    private XPlan createPlan( String category ) {
        XPlan xPlan = new XPlan( "name", "id", "type" );
        xPlan.setDistrict( category );
        return xPlan;
    }

}