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
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.parseTime;

import java.util.Date;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.TextBox;

/**
 * {@link TextBox} input for times.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class TimeBox extends TextBox implements Validable {

	private final DateTimeFormat format;

	/**
	 * @param format used to format the time, never <code>null</code>
	 */
	public TimeBox(DateTimeFormat format) {
		this.format = format;
		addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				reset();
				parse();
			}

			private void reset() {
				removeStyleName(EDITOR_VALIDATION_ERROR);
			}
		});
	}

	/**
	 * Sets the time.
	 * @param value to set, may be <code>null</code>
	 */
	public void setValue(Date value) {
		if (value != null)
			super.setValue(format.format(value));
		else
			super.setValue(null);
	}

	/**
	 * @return the time, may be <code>null</code> if not parseable
	 */
	public Date getTimeValue() {
		return parse();
	}

	@Override
	public boolean isValid() {
		try {
			parseTime(this);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	private Date parse() {
		try {
			return parseTime(this);
		}
		catch (Exception e) {
			addStyleName(EDITOR_VALIDATION_ERROR);
		}
		return null;
	}

}
