//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.commons.configuration;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortConfigurationTest {

    @Test
    public void testRetrieveFeatureType() {
        SortConfiguration sortConfiguration = new SortConfiguration();
        sortConfiguration.addSortField( BP_Plan, XPLAN_40, "ft1", "pn1" );
        sortConfiguration.addSortField( BP_Plan, XPLAN_41, "ft1", "pn2" );
        sortConfiguration.addSortField( FP_Plan, XPLAN_41, "ft2", "pn1" );
        sortConfiguration.addSortField( FP_Plan, XPLAN_2, null, "pn3" );
        sortConfiguration.addSortField( LP_Plan, XPLAN_2, "ft4", "pn4" );
        sortConfiguration.addSortField( LP_Plan, XPLAN_2, "ft5", "pn5" );

        String firstFeatureType = sortConfiguration.retrieveFeatureType( BP_Plan, XPLAN_40 );
        String secondFeatureType = sortConfiguration.retrieveFeatureType( BP_Plan, XPLAN_41 );
        String thirdFeatureType = sortConfiguration.retrieveFeatureType( FP_Plan, XPLAN_41 );
        String nullFeatureType = sortConfiguration.retrieveFeatureType( FP_Plan, XPLAN_2 );
        String overwrittenFeatureType = sortConfiguration.retrieveFeatureType( LP_Plan, XPLAN_2 );
        String unconfiguredPlanTypeFeatureType = sortConfiguration.retrieveFeatureType( LP_Plan, XPLAN_41 );
        String unconfiguredVersionFeatureType = sortConfiguration.retrieveFeatureType( BP_Plan, XPLAN_2 );

        assertThat( firstFeatureType, is( "ft1" ) );
        assertThat( secondFeatureType, is( "ft1" ) );
        assertThat( thirdFeatureType, is( "ft2" ) );
        assertThat( nullFeatureType, nullValue() );
        assertThat( overwrittenFeatureType, is( "ft5" ) );
        assertThat( unconfiguredPlanTypeFeatureType, nullValue() );
        assertThat( unconfiguredVersionFeatureType, nullValue() );
    }

    @Test
    public void testRetrievePropertyName() {
        SortConfiguration sortConfiguration = new SortConfiguration();
        sortConfiguration.addSortField( BP_Plan, XPLAN_40, "ft1", "pn1" );
        sortConfiguration.addSortField( BP_Plan, XPLAN_41, "ft1", "pn2" );
        sortConfiguration.addSortField( FP_Plan, XPLAN_41, "ft2", "pn1" );
        sortConfiguration.addSortField( FP_Plan, XPLAN_2, "ft3", null );
        sortConfiguration.addSortField( LP_Plan, XPLAN_2, "ft4", "pn4" );
        sortConfiguration.addSortField( LP_Plan, XPLAN_2, "ft5", "pn5" );

        String firstPropertyName = sortConfiguration.retrievePropertyName( BP_Plan, XPLAN_40 );
        String secondPropertyName = sortConfiguration.retrievePropertyName( BP_Plan, XPLAN_41 );
        String thirdPropertyName = sortConfiguration.retrievePropertyName( FP_Plan, XPLAN_41 );
        String nullPropertyName = sortConfiguration.retrievePropertyName( FP_Plan, XPLAN_2 );
        String overwrittenPropertyName = sortConfiguration.retrievePropertyName( LP_Plan, XPLAN_2 );
        String unconfiguredPlanTypePropertyName = sortConfiguration.retrievePropertyName( LP_Plan, XPLAN_41 );
        String unconfiguredVersionPropertyName = sortConfiguration.retrievePropertyName( BP_Plan, XPLAN_2 );

        assertThat( firstPropertyName, is( "pn1" ) );
        assertThat( secondPropertyName, is( "pn2" ) );
        assertThat( thirdPropertyName, is( "pn1" ) );
        assertThat( nullPropertyName, nullValue() );
        assertThat( overwrittenPropertyName, is( "pn5" ) );
        assertThat( unconfiguredPlanTypePropertyName, nullValue() );
        assertThat( unconfiguredVersionPropertyName, nullValue() );
    }

    @Test(expected = NullPointerException.class)
    public void testAddSortField_NullPlanType() {
        SortConfiguration sortConfiguration = new SortConfiguration();
        sortConfiguration.addSortField( null, XPLAN_40, "ft1", "pn1" );
    }

    @Test(expected = NullPointerException.class)
    public void testAddSortField_NullVersion() {
        SortConfiguration sortConfiguration = new SortConfiguration();
        sortConfiguration.addSortField( BP_Plan, null, "ft1", "pn1" );
    }
}