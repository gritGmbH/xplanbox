/*-
 * #%L
 * xplan-webservices-core - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
	public void testCreateShouldHaveCorrectName() throws Exception {
		Function create = now.create(Collections.<Expression>emptyList());

		assertThat(create.getName(), is(Now.NAME));
	}

	@Test
	public void testCreateShouldReturnCurrentDate() throws Exception {
		Date before = createDateAndSleep();
		Function create = now.create(Collections.<Expression>emptyList());

		TypedObjectNode[] evaluate = create.evaluate(Collections.<TypedObjectNode[]>emptyList());

		assertThat(evaluate.length, is(1));

		PrimitiveValue primitiveValue = (PrimitiveValue) evaluate[0];
		assertThat(primitiveValue.getType().getBaseType(), is(BaseType.DATE_TIME));

		Date nowDate = Now.DATE_FORMAT.parse(primitiveValue.getValue().toString());

		Date after = createDateAndSleep();
		assertTrue(nowDate.after(before));
		assertTrue(nowDate.before(after));
	}

	private Date createDateAndSleep() throws InterruptedException {
		Date before = new Date();
		Thread.sleep(1001);
		return before;
	}

}
