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
package de.latlon.xplan.manager.web.client.gui.widget;

import static de.latlon.xplan.manager.web.client.gui.StyleNames.EDITOR_VALIDATION_ERROR;
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.parseDateStrict;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.Format;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.utils.DateTimeUtils;

/**
 * Implements the {@link Format} of {@link DateBox} to be more strict by
 * validation/parsing. Sets the style class to EDITOR_VALIDATION_ERROR and adds a tooltip
 * if input is invalid.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class StrictDateBoxFormat implements Format {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final DateTimeFormat format = DateTimeUtils.getDateFormat();

	@Override
	public String format(DateBox dateBox, Date date) {
		if (date == null) {
			return "";
		}
		else {
			return format.format(date);
		}
	}

	@Override
	public Date parse(DateBox dateBox, String text, boolean reportError) {
		try {
			return parseDateStrict(format, text);
		}
		catch (Exception e) {
			if (reportError) {
				dateBox.addStyleName(EDITOR_VALIDATION_ERROR);
				dateBox.setTitle(MESSAGES.editInvalidDate());
			}
			return null;
		}
	}

	@Override
	public void reset(DateBox dateBox, boolean abandon) {
		dateBox.removeStyleName(EDITOR_VALIDATION_ERROR);
		dateBox.setTitle("");
	}

	/**
	 * @return the format to use for parsing, never <code>null</code>
	 */
	public DateTimeFormat getFormat() {
		return format;
	}

}
