/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
