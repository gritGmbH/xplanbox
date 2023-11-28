/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.web.client.gui.editor.validityPeriod;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.gui.widget.ValidityPeriodInput;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;

import java.util.Date;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;

/**
 * CaptionPanel with editor for the validity period section.
 *
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ValidityPeriodPanel extends CaptionPanel implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ValidityPeriodInput validityPeriodInput = new ValidityPeriodInput();

	public ValidityPeriodPanel() {
		setCaptionText(MESSAGES.editCaptionValidityPeriod());
		add(createValidityPeriodPanelLayout());
	}

	@Override
	public boolean isValid() {
		return validityPeriodInput.isValid();
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		if (validityPeriod != null) {
			validityPeriodInput.setStartDateTime(validityPeriod.getStart());
			validityPeriodInput.setEndDateTime(validityPeriod.getEnd());
		}
	}

	public ValidityPeriod retrieveValidityPeriodToEdit() {
		Date startDate = validityPeriodInput.retrieveStartDateTime();
		Date endDate = validityPeriodInput.retrieveEndDateTime();
		return new ValidityPeriod(startDate, endDate);
	}

	private Widget createValidityPeriodPanelLayout() {
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(ALIGN_CENTER);
		panel.add(validityPeriodInput);
		return panel;
	}

}
