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
package de.latlon.xplan.manager.web.client.gui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterHandler;
import de.latlon.xplan.manager.web.client.gui.editor.basedata.BaseDataPanel;
import de.latlon.xplan.manager.web.client.gui.editor.change.ChangesXplanPanel;
import de.latlon.xplan.manager.web.client.gui.editor.raster.RasterBasisPanel;
import de.latlon.xplan.manager.web.client.gui.editor.reference.ReferencesPanel;
import de.latlon.xplan.manager.web.client.gui.editor.text.TextsPanel;
import de.latlon.xplan.manager.web.client.gui.editor.validityPeriod.ValidityPeriodPanel;
import de.latlon.xplan.manager.web.client.gui.event.EditorCanceledEvent;
import de.latlon.xplan.manager.web.client.gui.event.EditorFinishedEvent;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerService;
import de.latlon.xplan.manager.web.client.service.ManagerServiceAsync;
import de.latlon.xplan.manager.web.client.utils.AlertFailureCallback;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.BP_Plan;
import static de.latlon.xplan.manager.web.client.gui.utils.ValidationUtils.areComponentsValid;

/**
 * Main Editor Panel with different fieldsets and buttons to submit and cancel.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class EditorPanel extends DecoratorPanel {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private EditPlanType planType;

	private final HandlerManager eventBus;

	private final BaseDataPanel baseDataPanel;

	private final ValidityPeriodPanel validityPeriodPanel = new ValidityPeriodPanel();

	private final AbstractEditorSubPanelWithTable<Change> changesPanel;

	private final TextsPanel textsPanel;

	private final ReferencesPanel referencesPanel;

	private final RasterBasisPanel rasterBasisPanel;

	private String planId;

	public EditorPanel(EditVersion version, EditPlanType planType, List<Bereich> bereiche, HandlerManager eventBus) {
		this.planType = planType;
		this.eventBus = eventBus;
		baseDataPanel = new BaseDataPanel(version, planType);
		changesPanel = new ChangesXplanPanel(version, planType);
		textsPanel = new TextsPanel(version, planType);
		referencesPanel = new ReferencesPanel(version, planType);
		rasterBasisPanel = new RasterBasisPanel(version, planType, bereiche);
		FormPanel form = createForm();
		this.getElement().setId("editor-panel");
		this.setWidget(form);
	}

	void setXPlanToEdit(String planId, XPlanToEdit xPlantoEdit) {
		this.planId = planId;
		baseDataPanel.setBaseData(xPlantoEdit.getBaseData());
		validityPeriodPanel.setValidityPeriod(xPlantoEdit.getValidityPeriod());
		changesPanel.setValues(xPlantoEdit.getChanges());
		textsPanel.setValues(xPlantoEdit.getTexts());
		referencesPanel.setValues(xPlantoEdit.getReferences());
		rasterBasisPanel.setRasterBasis(xPlantoEdit);
	}

	private FormPanel createForm() {
		FormPanel form = new FormPanel();
		form.setWidget(createLayout(form));

		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(SubmitEvent event) {
				if (isValid()) {
					XPlanToEdit xPlanToEdit = retrieveXPlanToEdit();
					evaluateRasterAndUpdatePlan(xPlanToEdit);
				}
				else {
					Window.alert(MESSAGES.editInvalidInput());
				}
			}
		});
		return form;
	}

	private XPlanToEdit retrieveXPlanToEdit() {
		XPlanToEdit xPlanToEdit = new XPlanToEdit();
		xPlanToEdit.setBaseData(baseDataPanel.retrieveBaseDataToEdit());
		xPlanToEdit.setChanges(changesPanel.getValues());
		xPlanToEdit.setTexts(textsPanel.getValues());
		xPlanToEdit.setReferences(referencesPanel.getValues());
		xPlanToEdit.setValidityPeriod(validityPeriodPanel.retrieveValidityPeriodToEdit());
		xPlanToEdit.setRasterBasis(rasterBasisPanel.retrieveRasterBasis());
		return xPlanToEdit;
	}

	private HorizontalPanel createButtonBar(final FormPanel form) {
		Button saveButton = new Button(MESSAGES.editSaveButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				form.submit();
			}
		});

		Button cancelButton = new Button(MESSAGES.editCancelButton(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new EditorCanceledEvent());
			}
		});

		HorizontalPanel buttonBar = new HorizontalPanel();
		buttonBar.setSpacing(10);
		buttonBar.setHorizontalAlignment(ALIGN_CENTER);
		buttonBar.add(cancelButton);
		buttonBar.add(saveButton);
		return buttonBar;
	}

	private VerticalPanel createLayout(FormPanel form) {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(10);
		panel.setHorizontalAlignment(ALIGN_CENTER);
		panel.add(baseDataPanel);
		if (BP_Plan.equals(planType)) {
			panel.add(validityPeriodPanel);
		}
		panel.add(changesPanel);
		panel.add(textsPanel);
		panel.add(referencesPanel);
		panel.add(rasterBasisPanel);
		panel.add(createButtonBar(form));
		return panel;
	}

	private void updatePlan(XPlanToEdit xPlanToEdit, boolean updateRasterConfig, final DialogBox saveDialogBox) {
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = GWT.create(ManagerService.class);
				((HasRpcToken) managerService).setRpcToken(token);
				managerService.editPlan(planId, updateRasterConfig, xPlanToEdit, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void response) {
						if (saveDialogBox != null)
							saveDialogBox.hide();
						eventBus.fireEvent(new EditorFinishedEvent());
					}

					@Override
					public void onFailure(Throwable exception) {
						if (saveDialogBox != null)
							saveDialogBox.hide();
						Window.alert("Fehler beim Speichern: " + exception.getMessage());
					}
				});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private void evaluateRasterAndUpdatePlan(final XPlanToEdit xPlanToEdit) {
		final DialogBox saveDialogBox = createAndShowSaveDialogBox();
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = GWT.create(ManagerService.class);
				((HasRpcToken) managerService).setRpcToken(token);
				managerService.evaluateRaster(planId, xPlanToEdit,
						new AlertFailureCallback<List<RasterEvaluationResult>>() {

							@Override
							public void onSuccess(List<RasterEvaluationResult> response) {
								boolean allRastersAreValid = checkIfAllRastersAreValid(response);
								if (allRastersAreValid) {
									updatePlan(xPlanToEdit, true, saveDialogBox);
								}
								else {
									RasterDialog rasterDialog = new RasterDialog(response);
									rasterDialog.addRasterHandler(createRasterHandler());
									rasterDialog.addCancelClickedHandler(new ClickHandler() {
										@Override
										public void onClick(ClickEvent clickEvent) {
											saveDialogBox.hide();
										}
									});
								}
							}

							private RasterHandler createRasterHandler() {
								return new RasterHandler() {
									@Override
									public void onConfirmImport() {
										updatePlan(xPlanToEdit, false, saveDialogBox);
									}

									@Override
									public void onConfirmForceImport() {
										updatePlan(xPlanToEdit, true, saveDialogBox);
									}
								};
							}

							private boolean checkIfAllRastersAreValid(List<RasterEvaluationResult> response) {
								for (RasterEvaluationResult rasterResult : response) {
									if (!rasterResult.isConfiguredCrs() || !rasterResult.isSupportedImageFormat()) {
										return false;
									}
								}
								return true;
							}

						});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private boolean isValid() {
		return areComponentsValid(baseDataPanel, validityPeriodPanel, rasterBasisPanel);
	}

	private DialogBox createAndShowSaveDialogBox() {
		DialogBox saving = new DialogBox(false, true);
		saving.setText(MESSAGES.editingSaving());
		saving.center();
		saving.show();
		return saving;
	}

}
