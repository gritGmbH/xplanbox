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

import static com.google.gwt.i18n.client.DateTimeFormat.getFormat;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 * Helper class to create specific {@link com.google.gwt.i18n.client.DateTimeFormat}s.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public final class DateTimeUtils {

	private static final String RELEASE_DATE_FORMAT = "dd.MM.yyyy";

	private static final String IMPORT_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

	private static final String VALIDITY_DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

	private static final String DATE_FORMAT = "dd.MM.yyyy";

	private static final String TIME_FORMAT = "HH:mm";

	private static final long MILLIS_PER_MINUTE = 1000 * 60;

	private static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;

	private DateTimeUtils() {
	}

	/**
	 * Get the {@link com.google.gwt.i18n.client.DateTimeFormat} for the release date.
	 * @return release date format
	 */
	public static DateTimeFormat getReleaseDateFormat() {
		return getFormat(RELEASE_DATE_FORMAT);
	}

	/**
	 * Get the {@link com.google.gwt.i18n.client.DateTimeFormat} for the release date.
	 * @return import date format
	 */
	public static DateTimeFormat getImportDateFormat() {
		return getFormat(IMPORT_DATE_FORMAT);
	}

	/**
	 * Get the {@link com.google.gwt.i18n.client.DateTimeFormat} for the validity date.
	 * @return validity date format
	 */
	public static DateTimeFormat getValidityDateFormat() {
		return getFormat(VALIDITY_DATE_FORMAT);
	}

	/**
	 * Get the {@link com.google.gwt.i18n.client.DateTimeFormat} for the dates.
	 * @return date format
	 */
	public static DateTimeFormat getDateFormat() {
		return getFormat(DATE_FORMAT);
	}

	/**
	 * Get the {@link com.google.gwt.i18n.client.DateTimeFormat} for the times.
	 * @return time format
	 */
	public static DateTimeFormat getTimeFormat() {
		return getFormat(TIME_FORMAT);
	}

	/**
	 * Checks if the current date is between the passed start and end date time.
	 * @param startDateTime limiting the start date, may be <code>null</code> (means no
	 * limit)
	 * @param endDateTime limiting the end date, may be <code>null</code> (means no limit)
	 * @return <code>true</code> if the current date is between start and end or both
	 * dates are <code>null</code>, <code>false</code> otherwise
	 */
	public static boolean isCurrentDateTimeBetween(Date startDateTime, Date endDateTime) {
		if (startDateTime == null && endDateTime == null)
			return true;
		Date now = new Date();
		if (startDateTime != null && endDateTime == null)
			return startDateTime.before(now);
		if (startDateTime == null && endDateTime != null)
			return endDateTime.after(now);
		return startDateTime.before(now) && endDateTime.after(now);
	}

	/**
	 * Creates a {@link Date} from input of {@link DateBox} and {@link TextBox}.
	 * @param dateInput date input, never <code>null</code>
	 * @param timeInput time input, never <code>null</code>
	 * @return date, may be <code>null</code>
	 */
	public static Date retrieveDateTime(DateBox dateInput, TextBox timeInput) {
		Date date = parseDate(dateInput);
		if (date == null)
			return null;
		long dateInMillis = date.getTime();
		Date time = parseTime(timeInput);
		if (time != null) {
			dateInMillis += retrieveTimeInMillis(time);
		}
		return new Date(dateInMillis);
	}

	/**
	 * Creates a {@link Date} from input of {@link DateBox}.
	 * @param dateInput date input, may be <code>null</code>
	 * @return date, may be <code>null</code>
	 */
	public static Date parseDate(DateBox dateInput) {
		String dateValue = retrieveDate(dateInput);
		if (dateValue != null && dateValue.length() > 0)
			return getDateFormat().parseStrict(dateValue);
		return null;
	}

	/**
	 * Creates a {@link Date} from input of {@link String}.
	 * @param format the dateValue must be
	 * @param dateValue date value as string, may be <code>null</code>
	 * @return date, may be <code>null</code> if input value is <code>null</code> or empty
	 * @throws IllegalArgumentException if date is invalid
	 */
	public static Date parseDateStrict(DateTimeFormat format, String dateValue) {
		if (dateValue != null && dateValue.length() > 0) {
			if (dateValue.length() != format.getPattern().length())
				throw new IllegalArgumentException(dateValue);
			return format.parseStrict(dateValue);
		}
		return null;
	}

	/**
	 * Retrieve date as string from input of {@link DateBox}.
	 * @param dateInput date input, never <code>null</code>
	 * @return date as string, may be <code>null</code>
	 */
	public static String retrieveDate(DateBox dateInput) {
		String dateValue = dateInput.getTextBox().getValue();
		if (dateValue != null && dateValue.length() > 0)
			return dateValue;
		return null;
	}

	/**
	 * Creates a {@link Date} from input of {@link TextBox} which contains time input.
	 * @param timeInput time input, never <code>null</code>
	 * @return date, may be <code>null</code>
	 */
	public static Date parseTime(TextBox timeInput) {
		String timeInputValue = timeInput.getValue();
		if (timeInputValue != null && timeInputValue.length() > 0)
			return getTimeFormat().parseStrict(timeInputValue);
		return null;
	}

	@SuppressWarnings("deprecation")
	private static long retrieveTimeInMillis(Date time) {
		int hours = time.getHours();
		int minutes = time.getMinutes();
		return MILLIS_PER_MINUTE * minutes + MILLIS_PER_HOUR * hours;
	}

}
