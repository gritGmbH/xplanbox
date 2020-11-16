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

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

public class XplanTextAbschnitteTest {

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Baugebiet_1" );
        XplanTextAbschnitte expr = new XplanTextAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature, features );
        assertEquals( "[text1 | Das ist Textabschnitt No 1]"
                      + "[text2 | Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB)]", abschnitte.toString() );
    }
}
