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
package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.gui.widget.CodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.MandatoryTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextArea;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBoxFormat;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

import java.util.ArrayList;
import java.util.List;

/**
 * Extends the {@link DialogBox} with a button to close the dialog and a button to save
 * the content.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public abstract class EditDialogBox extends DialogBox {

	protected static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private static final String DEFAULT_WIDTH = "200px";

	private final List<SavedHandler> savedHandlers = new ArrayList<SavedHandler>();

	protected final HTML validationErrors = new HTML();

	private Button button;

	/**
	 * Instantiates a new {@link DialogBox} without content. Invoke initDialog() and pass
	 * the content.
	 * @param title the title of the dialog, may be <code>null</code>
	 */
	public EditDialogBox(String title) {
		super(false);
		setText(title);
	}

	public void addSaveHandler(SavedHandler saveHandlerToAdd) {
		savedHandlers.add(saveHandlerToAdd);
	}

	protected void initDialog(Widget contentPanel) {
		VerticalPanel dialogBoxContent = new VerticalPanel();
		dialogBoxContent.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogBoxContent.add(contentPanel);
		dialogBoxContent.add(validationErrors);
		dialogBoxContent.add(createButtonBar());
		setWidget(dialogBoxContent);
	}

	protected void informSaveHandler() {
		for (SavedHandler savedHandler : savedHandlers) {
			savedHandler.changesSaved();
		}
	}

	protected void save() {
		informSaveHandler();
	}

	protected PatternTextBox createPatternTextInput(String pattern, int maxLength) {
		PatternTextBox textBox = new PatternTextBox(pattern, maxLength);
		textBox.setWidth(DEFAULT_WIDTH);
		return textBox;
	}

	protected MandatoryTextBox createMandatoryTextInput(String pattern, int maxLength) {
		MandatoryTextBox textBox = new MandatoryTextBox(pattern, maxLength);
		textBox.setWidth(DEFAULT_WIDTH);
		return textBox;
	}

	protected PatternTextArea createPatternTextAreaInput(String pattern, int maxLength) {
		PatternTextArea textArea = new PatternTextArea(pattern, maxLength);
		textArea.setWidth(DEFAULT_WIDTH);
		textArea.setHeight("150px");
		return textArea;
	}

	protected StrictDateBox createDateInput() {
		StrictDateBox dateBox = new StrictDateBox(new StrictDateBoxFormat());
		dateBox.setWidth(DEFAULT_WIDTH);
		return dateBox;
	}

	protected CodeListBox createMandatoryCodeListInput(EditVersion version, EditPlanType planType,
			CodelistType codelistType) {
		return createCodeListInput(version, planType, codelistType, true);
	}

	protected void showValidationError(List<String> validationFailures) {
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

	private Widget createButtonBar() {
		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(10);
		buttonBar.add(createCancelButton());
		buttonBar.add(createSaveButton());
		return buttonBar;
	}

	private CodeListBox createCodeListInput(EditVersion version, EditPlanType planType, CodelistType codelistType,
			boolean isManadatory) {
		CodeListBox listBox = new CodeListBox(version, planType, codelistType, isManadatory);
		listBox.setWidth(DEFAULT_WIDTH);
		return listBox;
	}

	private Button createCancelButton() {
		button = new Button();
		button.setText(MESSAGES.editCancelButton());
		button.addClickHandler(createCancelListener());
		return button;
	}

	private Button createSaveButton() {
		button = new Button();
		button.setText(MESSAGES.editSaveButton());
		button.addClickHandler(createSaveListener());
		return button;
	}

	private ClickHandler createCancelListener() {
		ClickHandler listener = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				EditDialogBox.this.hide();
			}
		};
		return listener;
	}

	private ClickHandler createSaveListener() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				save();
			}
		};
	}

}
