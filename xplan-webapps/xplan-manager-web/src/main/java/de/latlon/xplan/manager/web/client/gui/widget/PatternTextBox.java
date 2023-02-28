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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.ui.TextBox;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

import static de.latlon.xplan.manager.web.client.gui.StyleNames.EDITOR_VALIDATION_ERROR;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class PatternTextBox extends TextBox implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final String pattern;

	public PatternTextBox(String pattern) {
		this.pattern = pattern;
		setTitle(MESSAGES.textPatternTooltip(pattern));
		addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				validateAndParse();
			}

		});
		setValue(null);
	}

	@Override
	public void setText(String text) {
		super.setText(text);
		validateAndParse();
	}

	@Override
	public String getText() {
		return validateAndParse();
	}

	@Override
	public boolean isValid() {
		String value = validateAndParse();
		return value != null;
	}

	private String validateAndParse() {
		reset();
		String value = super.getText();
		if (isValidAgainstPattern(value)) {
			setTitle(MESSAGES.textPatternTooltip(pattern));
			return value;
		}
		else {
			addStyleName(EDITOR_VALIDATION_ERROR);
			setTitle(MESSAGES.editInvalidAgainstPatternInput(pattern));
			return null;
		}
	}

	private boolean isValidAgainstPattern(String value) {
		if (value == null || "".equals(value))
			return true;
		RegExp regExp = RegExp.compile(pattern);
		return regExp.test(value);
	}

	private void reset() {
		removeStyleName(EDITOR_VALIDATION_ERROR);
		setTitle("");
	}

}
