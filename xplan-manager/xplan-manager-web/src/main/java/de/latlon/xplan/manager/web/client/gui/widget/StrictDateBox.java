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

import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.parseDateStrict;

import com.google.gwt.user.datepicker.client.DateBox;

import de.latlon.xplan.manager.web.client.utils.DateTimeUtils;

/**
 * {@link DateBox} implementation being strict by by parsing the input as date. Component
 * can validate himself by implementing {@link Validable}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class StrictDateBox extends DateBox implements Validable {

	private StrictDateBoxFormat dateBoxFormat;

	/**
	 * @param dateBoxFormat the {@link StrictDateBoxFormat} to use for validation
	 */
	public StrictDateBox(StrictDateBoxFormat dateBoxFormat) {
		setFormat(dateBoxFormat);
	}

	@Override
	public void setFormat(Format format) {
		if (!(format instanceof StrictDateBoxFormat))
			throw new IllegalArgumentException("Format must be a StrictDateBoxFormat");
		super.setFormat(format);
		this.dateBoxFormat = (StrictDateBoxFormat) format;
	}

	@Override
	public boolean isValid() {
		String text = DateTimeUtils.retrieveDate(this);
		try {
			parseDateStrict(dateBoxFormat.getFormat(), text);
		}
		catch (Exception e) {
			return false;
		}
		return true;
	}

}
