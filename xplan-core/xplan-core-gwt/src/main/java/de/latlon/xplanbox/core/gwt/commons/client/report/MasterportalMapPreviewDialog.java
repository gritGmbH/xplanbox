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
package de.latlon.xplanbox.core.gwt.commons.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorWebCommonsMessages;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MasterportalMapPreviewDialog extends DialogBox {

	private static final String FRAME_WIDTH = "800px";

	private static final String FRAME_HEIGHT = "630px";

	private final ValidatorWebCommonsMessages messages = GWT.create(ValidatorWebCommonsMessages.class);

	private final MapPreviewMetadata mapPreviewMetadata;

	public MasterportalMapPreviewDialog(MapPreviewMetadata mapPreviewMetadata) {
		super(false);
		this.mapPreviewMetadata = mapPreviewMetadata;
		initDialog();
	}

	private void initDialog() {
		setText(messages.mapPreviewDialogTitle(mapPreviewMetadata.getValidationName()));
		VerticalPanel dialogBoxContent = createDialogBoxContent();
		createAndAddMap(dialogBoxContent);
		createAndAddCloseButton(dialogBoxContent);
		setWidget(dialogBoxContent);
	}

	private VerticalPanel createDialogBoxContent() {
		VerticalPanel dialogBoxContent = new VerticalPanel();
		dialogBoxContent.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		dialogBoxContent.setSpacing(20);
		return dialogBoxContent;
	}

	private void createAndAddMap(final VerticalPanel dialogBoxContent) {
		Frame mapFrame = new Frame();
		mapFrame.setWidth(FRAME_WIDTH);
		mapFrame.setHeight(FRAME_HEIGHT);
		mapFrame.setUrl(createUrl());
		dialogBoxContent.add(mapFrame);
	}

	private String createUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append("./masterportal?");
		sb.append("style=simple&");
		sb.append("zoomToExtent=");
		XPlanEnvelope bbox = mapPreviewMetadata.getBbox();
		sb.append(bbox.getMinX()).append(",");
		sb.append(bbox.getMinY()).append(",");
		sb.append(bbox.getMaxX()).append(",");
		sb.append(bbox.getMaxY()).append("&");
		sb.append("config=config/").append(mapPreviewMetadata.getConfigFileName());
		return sb.toString();
	}

	private void createAndAddCloseButton(VerticalPanel dialogBoxContent) {
		SimplePanel simplePanel = createButtonPanel(messages.validationPopupClose(), createCloseHandler());
		dialogBoxContent.add(simplePanel);
	}

	private ClickHandler createCloseHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MasterportalMapPreviewDialog.this.hide();
			}
		};
	}

	private SimplePanel createButtonPanel(String message, ClickHandler clickHandler) {
		Button button = new Button();
		button.setText(message);
		button.addClickHandler(clickHandler);
		return addButtonToSimplePanel(button);
	}

	private SimplePanel addButtonToSimplePanel(Button button) {
		SimplePanel simplePanel = new SimplePanel();
		simplePanel.add(button);
		return simplePanel;
	}

}
