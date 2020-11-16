/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LatestDateTest {

    @Test
    public void testEvaluate_Empty() {
        FeatureCollection features = getTestFeatures( XPLAN_51, "Praesentationsobjekte.gml" );
        Feature feature = getTestFeature( features, "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        TypedObjectNode property = latestDate.evaluate( feature, features );
        assertThat( property, is( nullValue() ) );
    }

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_51, "MultipleDates.gml" );
        Feature feature = getTestFeature( features, "BP_PLAN" );
        LatestDate latestDate = new LatestDate( new Xpath( "xplan:auslegungsStartDatum" ) );
        SimpleProperty property = (SimpleProperty) latestDate.evaluate( feature, features );
        assertThat( property.getValue().getAsText(), is( "1998-01-01" ) );
    }

}
