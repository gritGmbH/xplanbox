/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.utils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
