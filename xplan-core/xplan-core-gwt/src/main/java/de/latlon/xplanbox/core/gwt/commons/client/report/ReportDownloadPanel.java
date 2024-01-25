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
package de.latlon.xplanbox.core.gwt.commons.client.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationSummary;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorWebCommonsMessages;
import de.latlon.xplan.validator.web.shared.ArtifactType;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PDF;
import static de.latlon.xplan.validator.web.shared.ArtifactType.SHP;
import static de.latlon.xplan.validator.web.shared.ArtifactType.XML;

/**
 * Encapulates the download options.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ReportDownloadPanel extends CaptionPanel {

	private static final ValidatorWebCommonsMessages messages = GWT.create(ValidatorWebCommonsMessages.class);

	private final CheckBox htmlCheckBox = new CheckBox(messages.reportDownloadHtml());

	private final CheckBox xmlCheckBox = new CheckBox(messages.reportDownloadXml());

	private final CheckBox pdfCheckBox = new CheckBox(messages.reportDownloadPdf());

	private final CheckBox shpCheckBox = new CheckBox(messages.reportDownloadShp());

	private final ReportUrlBuilder urlBuilder = new ReportUrlBuilder();

	private ValidationSummary validationSummary;

	public ReportDownloadPanel(ValidationSummary validationSummary) {
		super(messages.reportDownloadBoxTitle());
		this.validationSummary = validationSummary;
		initPanel();
	}

	private void initPanel() {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(10);

		mainPanel.add(htmlCheckBox);
		mainPanel.add(pdfCheckBox);
		mainPanel.add(xmlCheckBox);

		mainPanel.add(createGeometryErrorSeperator());

		mainPanel.add(shpCheckBox);

		mainPanel.add(createDownloadButton());

		setContentWidget(mainPanel);
	}

	private Widget createGeometryErrorSeperator() {
		Label label = new Label(messages.reportDownloadGeometryErrors());
		return label;
	}

	private Widget createDownloadButton() {
		Button download = new Button(messages.reportDownloadButtonTitle());
		download.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				List<ArtifactType> selectedArtifacts = getSelectedArtifacts();
				if (selectedArtifacts.size() > 0) {
					String zipUrl = urlBuilder.createZipUrl(validationSummary, selectedArtifacts);
					GWT.log("Requested URL to receive the zip file with reports: " + zipUrl);
					Window.open(zipUrl, "", "");
				}
				else {
					Window.alert(messages.reportDownloadNoArtefactsSelected());
				}
			}
		});
		return download;
	}

	private List<ArtifactType> getSelectedArtifacts() {
		List<ArtifactType> selectedArtifacts = new ArrayList<ArtifactType>();
		if (htmlCheckBox.getValue())
			selectedArtifacts.add(HTML);
		if (xmlCheckBox.getValue())
			selectedArtifacts.add(XML);
		if (pdfCheckBox.getValue())
			selectedArtifacts.add(PDF);
		if (shpCheckBox.getValue())
			selectedArtifacts.add(SHP);
		return selectedArtifacts;
	}

}
