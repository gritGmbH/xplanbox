/*-
 * #%L
 * xplan-commons-webservices - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Date;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.BaseType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.filter.Expression;
import org.deegree.filter.expression.Function;
import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class NowTest {

    private final Now now = new Now();

    @Test
    public void testCreateShouldHaveCorrectName()
                    throws Exception {
        Function create = now.create( Collections.<Expression>emptyList() );

        assertThat( create.getName(), is( Now.NAME ) );
    }

    @Test
    public void testCreateShouldReturnCurrentDate()
                    throws Exception {
        Date before = createDateAndSleep();
        Function create = now.create( Collections.<Expression>emptyList() );

        TypedObjectNode[] evaluate = create.evaluate( Collections.<TypedObjectNode[]>emptyList() );

        assertThat( evaluate.length, is( 1 ) );

        PrimitiveValue primitiveValue = (PrimitiveValue) evaluate[0];
        assertThat( primitiveValue.getType().getBaseType(), is( BaseType.DATE_TIME ) );

        Date nowDate = Now.DATE_FORMAT.parse( primitiveValue.getValue().toString() );

        Date after = createDateAndSleep();
        assertTrue( nowDate.after( before ) );
        assertTrue( nowDate.before( after ) );
    }

    private Date createDateAndSleep()
                    throws InterruptedException {
        Date before = new Date();
        Thread.sleep( 1001 );
        return before;
    }

}
