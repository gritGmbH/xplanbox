/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static com.google.gwt.user.client.ui.HasVerticalAlignment.ALIGN_TOP;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_PlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Rechtsstand;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_SonstPlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Verfahren;
import static de.latlon.xplan.manager.web.client.gui.validation.ValidationUtils.areComponentsValid;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType;
import de.latlon.xplan.manager.web.client.gui.widget.CodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBoxFormat;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.BaseData;

/**
 * CaptionPanel with editor for the base data section.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class BaseDataPanel extends CaptionPanel implements Validable {

	private static final XPlanWebMessages MASSAGE = GWT.create(XPlanWebMessages.class);

	private static final String DEFAULT_WIDTH = "175px";

	private static final String TEXTAREA_HEIGHT = "125px";

	private final TextBox name = createTextInput();

	private final TextArea description = createTextAreaInput();

	private final StrictDateBox creationDate = createDateInput();

	private final StrictDateBox lossDate = createDateInput();

	private final StrictDateBox regulationDate = createDateInput();

	private final CodeListBox planType;

	private final CodeListBox otherPlanType;

	private final CodeListBox method;

	private final CodeListBox legislationStatus;

	public BaseDataPanel(EditVersion version) {
		setCaptionText(MASSAGE.editCaptionBasedata());
		planType = createMandatoryCodeListInput(version, BP_PlanArt);
		otherPlanType = createCodeListInput(version, BP_SonstPlanArt);
		method = createMethodInput(version);
		legislationStatus = createCodeListInput(version, BP_Rechtsstand);
		add(createBaseDataLayout());
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
		return areComponentsValid(planType, creationDate, lossDate, regulationDate);
	}

	private FlexTable createBaseDataLayout() {
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

		layout.setWidget(1, 1, new Label(MASSAGE.editCaptionBasedataName()));
		layout.setWidget(1, 2, name);
		layout.setWidget(1, 3, new Label(MASSAGE.editCaptionBasedataPlanType()));
		layout.setWidget(1, 4, planType);

		layout.setWidget(2, 1, new Label(MASSAGE.editCaptionBasedataCreationDate()));
		layout.setWidget(2, 2, creationDate);
		layout.setWidget(2, 3, new Label(MASSAGE.editCaptionBasedataOtherPlanType()));
		layout.setWidget(2, 4, otherPlanType);

		layout.setWidget(3, 1, new Label(MASSAGE.editCaptionBasedataLossDate()));
		layout.setWidget(3, 2, lossDate);
		layout.setWidget(3, 3, new Label(MASSAGE.editCaptionBasedataMethod()));
		layout.setWidget(3, 4, method);

		layout.setWidget(4, 1, new Label(MASSAGE.editCaptionBasedataRegulationDate()));
		layout.setWidget(4, 2, regulationDate);
		layout.setWidget(4, 3, new Label(MASSAGE.editCaptionBasedataLegislationStatus()));
		layout.setWidget(4, 4, legislationStatus);

		layout.setWidget(5, 1, new Label(MASSAGE.editCaptionBasedataDescription()));
		layout.setWidget(5, 2, description);

		return layout;
	}

	private CodeListBox createMethodInput(EditVersion version) {
		CodeListBox methodInput = createCodeListInput(version, BP_Verfahren);
		if (XPLAN_3.equals(version)) {
			methodInput.setEnabled(false);
		}
		return methodInput;
	}

	private CodeListBox createMandatoryCodeListInput(EditVersion version, CodelistType codelistType) {
		return createCodeListInput(version, codelistType, true);
	}

	private CodeListBox createCodeListInput(EditVersion version, CodelistType codelistType) {
		return createCodeListInput(version, codelistType, false);
	}

	private CodeListBox createCodeListInput(EditVersion version, CodelistType codelistType, boolean isMandatory) {
		CodeListBox codeListBox = new CodeListBox(version, codelistType, isMandatory);
		codeListBox.setWidth(DEFAULT_WIDTH);
		return codeListBox;
	}

	private TextArea createTextAreaInput() {
		TextArea textArea = new TextArea();
		textArea.setWidth("100%");
		textArea.setHeight(TEXTAREA_HEIGHT);
		return textArea;
	}

	private TextBox createTextInput() {
		TextBox textBox = new TextBox();
		textBox.setWidth(DEFAULT_WIDTH);
		return textBox;
	}

	private StrictDateBox createDateInput() {
		StrictDateBox dateBox = new StrictDateBox(new StrictDateBoxFormat());
		dateBox.setWidth(DEFAULT_WIDTH);
		return dateBox;
	}

}
