/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.web;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Simple {@link ButtonCell} which can be disabled.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DisengageableButtonCell extends com.google.gwt.cell.client.ButtonCell {

	private boolean disabled = false;

	@Override
	public void render(Context context, SafeHtml data, SafeHtmlBuilder sb) {
		String disabledString = createDisabledString();
		sb.appendHtmlConstant("<button type=\"button\" tabindex=\"-1\"" + disabledString + ">");
		if (data != null) {
			sb.append(data);
		}
		sb.appendHtmlConstant("</button>");
	}

	private String createDisabledString() {
		if (disabled) {
			return "disabled=\"disabled\"";
		}
		return "";
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled() {
		this.disabled = true;
	}

	public void setEnabled() {
		this.disabled = false;
	}

}
