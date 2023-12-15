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
package de.latlon.xplan.manager.web.client.gui.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.gui.widget.ValidityPeriodInput;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

import java.util.Date;

/**
 * Dialog to set the validity period of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @deprecated class will be removed in a future version.
 */
@Deprecated
public class ValidityPeriodDialog extends WizardDialogBox implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ValidityPeriodInput validityPeriodInput = new ValidityPeriodInput();

	/**
	 * Instantiates a new {@link ValidityPeriodDialog}.
	 */
	public ValidityPeriodDialog() {
		super(MESSAGES.validityPeriodDialogTitle());
		setContent(createMainPanel());
	}

	@Override
	public boolean isValid() {
		return validityPeriodInput.isValid();
	}

	/**
	 * @return the start date and time selected by the user, may be <code>null</code> if
	 * no date was selected
	 */
	public Date retrieveStartDateTime() {
		return validityPeriodInput.retrieveStartDateTime();

	}

	/**
	 * @return the end date and time selected by the user, may be <code>null</code> if no
	 * date was selected
	 */
	public Date retrieveEndDateTime() {
		return validityPeriodInput.retrieveEndDateTime();
	}

	private Panel createMainPanel() {
		VerticalPanel mainPanel = new VerticalPanel();
		Label description = new Label(MESSAGES.validityPeriodDialogDescription());
		description.setWordWrap(true);
		mainPanel.add(description);
		mainPanel.add(validityPeriodInput);
		return mainPanel;
	}

}
