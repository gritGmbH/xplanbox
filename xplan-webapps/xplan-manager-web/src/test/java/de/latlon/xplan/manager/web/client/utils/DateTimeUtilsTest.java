/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DateTimeUtilsTest {

	@Test
	public void testIsCurrentDateTimeBetween() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = new Date(currentTimeMillis - 1000);
		Date endDateTime = new Date(currentTimeMillis + 1000000000);
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(true));
	}

	@Test
	public void testIsCurrentDateTimeBetweenStartAndEndIsNull() throws Exception {
		Date startDateTime = null;
		Date endDateTime = null;
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(true));
	}

	@Test
	public void testIsCurrentDateTimeBetweenStartIsNull() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = null;
		Date endDateTime = new Date(currentTimeMillis + 1000000000);
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(true));
	}

	@Test
	public void testIsCurrentDateTimeBetweenStartIsNullCurrentIsAfterEnd() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = null;
		Date endDateTime = new Date(currentTimeMillis - 1000);
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(false));
	}

	@Test
	public void testIsCurrentDateTimeBetweenEndIsNull() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = new Date(currentTimeMillis - 1000);
		Date endDateTime = null;
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(true));
	}

	@Test
	public void testIsCurrentDateTimeBetweenEndIsNullCurrentIsBeforeStart() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = new Date(currentTimeMillis + 1000000000);
		Date endDateTime = null;
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(false));
	}

	@Test
	public void testIsCurrentDateTimeBetweenCurrentIsAfter() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = new Date(currentTimeMillis - 10000);
		Date endDateTime = new Date(currentTimeMillis - 1000);
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(false));
	}

	@Test
	public void testIsCurrentDateTimeBetweenCurrentIsBefore() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Date startDateTime = new Date(currentTimeMillis + 100000000);
		Date endDateTime = new Date(currentTimeMillis + 1000000000);
		boolean isCurrentDateTimeBetween = DateTimeUtils.isCurrentDateTimeBetween(startDateTime, endDateTime);

		assertThat(isCurrentDateTimeBetween, is(false));
	}

}
