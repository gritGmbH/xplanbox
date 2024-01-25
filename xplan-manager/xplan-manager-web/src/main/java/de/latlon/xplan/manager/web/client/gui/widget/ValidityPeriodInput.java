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
package de.latlon.xplan.manager.web.client.gui.widget;

import static de.latlon.xplan.manager.web.client.gui.utils.ValidationUtils.areComponentsValid;
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.retrieveDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.utils.DateTimeUtils;

/**
 * Input widget to set/edit the validity period of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ValidityPeriodInput extends VerticalPanel implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private static final DateTimeFormat DATEFORMAT = DateTimeUtils.getDateFormat();

	private static final DateTimeFormat TIMEFORMAT = DateTimeUtils.getTimeFormat();

	private StrictDateBox startDateInput = initDateInput();

	private TimeBox startTimeInput = initTimeInput();

	private StrictDateBox endDateInput = initDateInput();

	private TimeBox endTimeInput = initTimeInput();

	private HTML validationErrors = new HTML();

	/**
	 * Instantiates a new {@link ValidityPeriodInput}.
	 */
	public ValidityPeriodInput() {
		addContent();
	}

	/**
	 * @return the start date and time selected by the user, may be <code>null</code> if
	 * no date was selected
	 */
	public Date retrieveStartDateTime() {
		return retrieveDateTime(startDateInput, startTimeInput);

	}

	/**
	 * @return the end date and time selected by the user, may be <code>null</code> if no
	 * date was selected
	 */
	public Date retrieveEndDateTime() {
		return retrieveDateTime(endDateInput, endTimeInput);
	}

	/**
	 * @param startDateTime the start date and time to put to the input fields, may be
	 * <code>null</code>
	 */
	public void setStartDateTime(Date startDateTime) {
		setDateAndTimeValues(startDateTime, startDateInput, startTimeInput);

	}

	/**
	 * @param endDateTime the end date and time to put to the input fields, may be
	 * <code>null</code>
	 */
	public void setEndDateTime(Date endDateTime) {
		setDateAndTimeValues(endDateTime, endDateInput, endTimeInput);
	}

	@Override
	public boolean isValid() {
		return validate();
	}

	private void addContent() {
		add(createStartDateTimeInputRow());
		add(createEndDateTimeInputRow());
		add(validationErrors);
	}

	private Widget createStartDateTimeInputRow() {
		String startTimeLabel = MESSAGES.validityPeriodDialogStartDateTime();
		return createDateTimeInputRow(startTimeLabel, startDateInput, startTimeInput);
	}

	private Widget createEndDateTimeInputRow() {
		String endTimeLabel = MESSAGES.validityPeriodDialogEndDateTime();
		return createDateTimeInputRow(endTimeLabel, endDateInput, endTimeInput);
	}

	private Widget createDateTimeInputRow(String label, DateBox dateInput, TextBox timeInput) {
		HorizontalPanel row = new HorizontalPanel();
		row.setSpacing(10);
		row.add(new Label(label));
		row.add(dateInput);
		row.add(timeInput);
		return row;
	}

	private StrictDateBox initDateInput() {
		StrictDateBox dateBox = new StrictDateBox(new StrictDateBoxFormat());
		dateBox.setWidth("150px");
		dateBox.addValueChangeHandler(new ClearValidationErrors<Date>());
		return dateBox;
	}

	private TimeBox initTimeInput() {
		TimeBox timeTextBox = new TimeBox(TIMEFORMAT);
		timeTextBox.setTitle(MESSAGES.validityPeriodDialogTimeTooltip());
		timeTextBox.setValue("00:00");
		timeTextBox.setWidth("100px");
		timeTextBox.addValueChangeHandler(new ClearValidationErrors<String>());
		return timeTextBox;
	}

	private void setDateAndTimeValues(Date dateTime, DateBox dateInput, TimeBox timeInput) {
		if (dateTime != null) {
			dateInput.setValue(DATEFORMAT.parseStrict(DATEFORMAT.format(dateTime)));
			timeInput.setValue(TIMEFORMAT.format(dateTime));
		}
		else {
			dateInput.setValue(null);
			timeInput.setValue((Date) null);
		}
	}

	private void showValidationError(List<String> validationFailures) {
		StringBuilder htmlMsg = new StringBuilder();
		htmlMsg.append("<div>");
		htmlMsg.append("<ul>");
		for (String validationFailure : validationFailures) {
			htmlMsg.append("<li class=\"validationError\">").append(validationFailure).append("</li>");
		}
		htmlMsg.append("</ul>");
		htmlMsg.append("</div>");
		validationErrors.setHTML(htmlMsg.toString());
	}

	private boolean validate() {
		boolean isInputValid = areComponentsValid(startDateInput, startTimeInput, endDateInput, endTimeInput);

		List<String> validationFailures = new ArrayList<String>();
		if (!isInputValid)
			return false;
		boolean isStartBeforeEnd = isStartBeforeEnd();
		if (!isStartBeforeEnd)
			validationFailures.add(MESSAGES.validityPeriodDialogStartNotBeforeEnd());
		if (!validationFailures.isEmpty()) {
			showValidationError(validationFailures);
			return false;
		}
		return true;
	}

	private boolean isStartBeforeEnd() {
		try {
			Date startDateTime = retrieveDateTime(startDateInput, startTimeInput);
			Date endDateTime = retrieveDateTime(endDateInput, endTimeInput);
			if (startDateTime == null || endDateTime == null)
				return true;
			return startDateTime.before(endDateTime);
		}
		catch (Exception e) {
			return false;
		}
	}

	private class ClearValidationErrors<T> implements ValueChangeHandler<T> {

		@Override
		public void onValueChange(ValueChangeEvent<T> event) {
			validationErrors.setText("");
			validate();
		}

	}

}
