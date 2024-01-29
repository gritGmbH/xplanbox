/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.web;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplanbox.core.gwt.commons.web.i18n.XPlanMessages;

/**
 * Extends the {@link DialogBox} with a button to close the dialog
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class CloseableDialogBox extends DialogBox {

	private final XPlanMessages messages = GWT.create(XPlanMessages.class);

	private Button button;

	/**
	 * @param title the title of the dialog, may be <code>null</code>
	 */
	public CloseableDialogBox(String title) {
		super(false);
		setText(title);
		initDialog(null);
	}

	/**
	 * @param title the title of the dialog, may be <code>null</code>
	 * @param contentPanel the panel with the content to render in the box
	 */
	public CloseableDialogBox(String title, Panel contentPanel) {
		super(false);
		setText(title);
		initDialog(contentPanel);
	}

	/**
	 * @param title the title of the dialog, may be <code>null</code>
	 * @param message to show as content
	 */
	public CloseableDialogBox(String title, String message) {
		super(false);
		setText(title);

		SimplePanel contentPanel = createSimpleTextPanel(message);
		initDialog(contentPanel);
	}

	/**
	 * Set the text of the close button.
	 * @param text
	 */
	public void setCloseButtonText(String text) {
		button.setText(text);
	}

	private SimplePanel createSimpleTextPanel(String message) {
		SimplePanel contentPanel = new SimplePanel();
		contentPanel.add(new Label(message));
		return contentPanel;
	}

	public void setContent(Panel contentPanel) {
		initDialog(contentPanel);
	}

	private void initDialog(Panel contentPanel) {
		VerticalPanel dialogBoxContent = new VerticalPanel();
		dialogBoxContent.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		if (contentPanel != null)
			dialogBoxContent.add(contentPanel);
		createAndAddCloseButton(dialogBoxContent);
		setWidget(dialogBoxContent);
	}

	private void createAndAddCloseButton(VerticalPanel dialogBoxContent) {
		SimplePanel holder = createCloseButtonPanel();
		dialogBoxContent.add(holder);
	}

	private SimplePanel createCloseButtonPanel() {
		button = new Button();
		button.setText(messages.closeButton());
		button.addClickHandler(createCloseListener());

		SimplePanel holder = new SimplePanel();
		holder.add(button);
		return holder;
	}

	protected ClickHandler createCloseListener() {
		ClickHandler listener = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CloseableDialogBox.this.hide();
			}
		};
		return listener;
	}

}
