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
package de.latlon.xplan.manager.web.client.gui.editor.basedata;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.gui.widget.CodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextArea;
import de.latlon.xplan.manager.web.client.gui.widget.PatternTextBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBoxFormat;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.BaseData;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_TOP;
import static de.latlon.xplan.commons.util.TextPatternConstants.DESCRIPTION_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.L_LENGTH;
import static de.latlon.xplan.commons.util.TextPatternConstants.NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.BP_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.SO_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.PlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.Rechtsstand;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.SonstPlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.Verfahren;
import static de.latlon.xplan.manager.web.client.gui.utils.ValidationUtils.areComponentsValid;

/**
 * CaptionPanel with editor for the base data section.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class BaseDataPanel extends CaptionPanel implements Validable {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private static final String DEFAULT_WIDTH = "175px";

	private static final String TEXTAREA_HEIGHT = "125px";

	private final PatternTextBox name = createTextInput(NAME_PATTERN, S_LENGTH);

	private final PatternTextArea description = createTextAreaInput(DESCRIPTION_PATTERN, L_LENGTH);

	private final StrictDateBox creationDate = createDateInput();

	private final StrictDateBox lossDate = createDateInput();

	private final StrictDateBox regulationDate = createDateInput();

	private final CodeListBox planType;

	private final CodeListBox otherPlanType;

	private final CodeListBox method;

	private final CodeListBox legislationStatus;

	private EditPlanType type;

	public BaseDataPanel(EditVersion version, EditPlanType type) {
		this.type = type;
		setCaptionText(MESSAGES.editCaptionBasedata());
		this.planType = createMandatoryCodeListInput(version, type, PlanArt);
		this.otherPlanType = createCodeListInput(version, type, SonstPlanArt);
		this.method = createMethodInput(version, type);
		this.legislationStatus = createCodeListInput(version, type, Rechtsstand);
		add(createBaseDataLayout(version, type));
	}

	public void setBaseData(BaseData baseData) {
		if (baseData != null) {
			name.setValue(baseData.getPlanName());
			description.setValue(baseData.getDescription());
			creationDate.setValue(baseData.getCreationDate());
			lossDate.setValue(baseData.getLossDate());
			regulationDate.setValue(baseData.getRegulationDate());
			planType.selectItem(baseData.getPlanTypeCode());
			otherPlanType.selectItem(baseData.getOtherPlanTypeCode());
			method.selectItem(baseData.getMethodCode());
			legislationStatus.selectItem(baseData.getLegislationStatusCode());
		}
		else {
			name.setValue(null);
			description.setValue(null);
			creationDate.setValue(null);
			lossDate.setValue(null);
			regulationDate.setValue(null);
		}
	}

	public BaseData retrieveBaseDataToEdit() {
		BaseData baseData = new BaseData();
		baseData.setPlanName(name.getText());
		baseData.setDescription(description.getText());
		baseData.setCreationDate(creationDate.getValue());
		baseData.setLossDate(lossDate.getValue());
		baseData.setRegulationDate(regulationDate.getValue());
		baseData.setPlanTypeCode(planType.getValueAsCode());
		baseData.setOtherPlanTypeCode(otherPlanType.getValueAsCode());
		baseData.setLegislationStatusCode(legislationStatus.getValueAsCode());
		baseData.setMethodCode(method.getValueAsCode());
		return baseData;
	}

	@Override
	public boolean isValid() {
		if (SO_Plan.equals(type))
			return areComponentsValid(name, description, creationDate, lossDate, regulationDate);
		return areComponentsValid(name, description, planType, creationDate, lossDate, regulationDate);
	}

	private FlexTable createBaseDataLayout(EditVersion version, EditPlanType type) {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(3, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(4, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(5, 1, ALIGN_LEFT);
		formatter.setHorizontalAlignment(1, 3, ALIGN_LEFT);
		formatter.setHorizontalAlignment(2, 3, ALIGN_LEFT);
		formatter.setHorizontalAlignment(3, 3, ALIGN_LEFT);
		formatter.setHorizontalAlignment(4, 3, ALIGN_LEFT);
		formatter.setHorizontalAlignment(5, 3, ALIGN_LEFT);
		formatter.setVerticalAlignment(5, 1, ALIGN_TOP);

		formatter.setColSpan(5, 2, 3);

		layout.setWidget(1, 1, new Label(MESSAGES.editCaptionBasedataName()));
		layout.setWidget(1, 2, name);
		if (!SO_Plan.equals(type)) {
			layout.setWidget(1, 3, new Label(MESSAGES.editCaptionBasedataPlanType()));
			layout.setWidget(1, 4, planType);
		}

		layout.setWidget(2, 1, new Label(MESSAGES.editCaptionBasedataCreationDate()));
		layout.setWidget(2, 2, creationDate);

		// https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1227
		// layout.setWidget(2, 3, new Label(MASSAGE.editCaptionBasedataOtherPlanType()));
		// layout.setWidget(2, 4, otherPlanType);

		layout.setWidget(3, 1, new Label(MESSAGES.editCaptionBasedataLossDate()));
		layout.setWidget(3, 2, lossDate);

		if (!XPLAN_60.equals(version) && !SO_Plan.equals(type)) {
			layout.setWidget(3, 3, new Label(MESSAGES.editCaptionBasedataMethod()));
			layout.setWidget(3, 4, method);
		}

		if (BP_Plan.equals(type)) {
			layout.setWidget(4, 1, new Label(MESSAGES.editCaptionBasedataRegulationDate()));
			layout.setWidget(4, 2, regulationDate);
		}

		if (!SO_Plan.equals(type)) {
			layout.setWidget(4, 3, new Label(MESSAGES.editCaptionBasedataLegislationStatus()));
			layout.setWidget(4, 4, legislationStatus);
		}
		layout.setWidget(5, 1, new Label(MESSAGES.editCaptionBasedataDescription()));
		layout.setWidget(5, 2, description);

		return layout;
	}

	private CodeListBox createMethodInput(EditVersion version, EditPlanType planType) {
		return createCodeListInput(version, planType, Verfahren);
	}

	private CodeListBox createMandatoryCodeListInput(EditVersion version, EditPlanType planType,
			CodelistType codelistType) {
		return createCodeListInput(version, planType, codelistType, true);
	}

	private CodeListBox createCodeListInput(EditVersion version, EditPlanType planType, CodelistType codelistType) {
		return createCodeListInput(version, planType, codelistType, false);
	}

	private CodeListBox createCodeListInput(EditVersion version, EditPlanType planType, CodelistType codelistType,
			boolean isMandatory) {
		CodeListBox codeListBox = new CodeListBox(version, planType, codelistType, isMandatory);
		codeListBox.setWidth(DEFAULT_WIDTH);
		return codeListBox;
	}

	private PatternTextArea createTextAreaInput(String pattern, int maxLength) {
		PatternTextArea textArea = new PatternTextArea(pattern, maxLength);
		setTitle(MESSAGES.textPatternTooltip(pattern, maxLength));
		textArea.setWidth("100%");
		textArea.setHeight(TEXTAREA_HEIGHT);
		return textArea;
	}

	private PatternTextBox createTextInput(String pattern, int maxLength) {
		PatternTextBox textBox = new PatternTextBox(pattern, maxLength);
		setTitle(MESSAGES.textPatternTooltip(pattern, maxLength));
		textBox.setWidth(DEFAULT_WIDTH);
		return textBox;
	}

	private StrictDateBox createDateInput() {
		StrictDateBox dateBox = new StrictDateBox(new StrictDateBoxFormat());
		dateBox.setWidth(DEFAULT_WIDTH);
		return dateBox;
	}

}
