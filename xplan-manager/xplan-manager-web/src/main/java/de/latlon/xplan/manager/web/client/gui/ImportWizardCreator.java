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
package de.latlon.xplan.manager.web.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.DialogBox;
import de.latlon.xplan.manager.web.client.gui.dialog.CrsDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.InternalIdDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.LegislationStatusDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.NextSubmittedHandler;
import de.latlon.xplan.manager.web.client.gui.dialog.PlanNameAndStatusDialogBox;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterHandler;
import de.latlon.xplan.manager.web.client.gui.dialog.ValidityPeriodDialog;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerService;
import de.latlon.xplan.manager.web.client.service.ManagerServiceAsync;
import de.latlon.xplan.manager.web.client.utils.AlertFailureCallback;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.RechtsstandAndPlanStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wizard to start imports of plans.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ImportWizardCreator {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private final ManagerWebConfiguration configuration;

	private final PlanListPanel planListPanel;

	/**
	 * @param configuration never <code>null</code>
	 * @param planListPanel never <code>null</code>
	 */
	public ImportWizardCreator(ManagerWebConfiguration configuration, PlanListPanel planListPanel) {
		this.configuration = configuration;
		this.planListPanel = planListPanel;
	}

	/**
	 * Imports a plan. Several dialogs are opened during this process, if configured.
	 * @param id id of plan to import
	 * @param hasMultipleXPlanElements
	 */
	public void importPlan(final String id, final boolean hasMultipleXPlanElements) {
		selectValidityPeriodAndImportPlan(id, hasMultipleXPlanElements);
	}

	private void selectValidityPeriodAndImportPlan(final String id, final boolean hasMultipleXPlanElements) {
		if (configuration.isValidityPeriodActivated() && !hasMultipleXPlanElements) {
			final ValidityPeriodDialog dialog = new ValidityPeriodDialog();
			dialog.addNextSubmittedHandler(new NextSubmittedHandler() {
				@Override
				public void onNextSubmitted() {
					try {
						if (dialog.isValid()) {
							Date startDateTime = dialog.retrieveStartDateTime();
							Date endDateTime = dialog.retrieveEndDateTime();
							dialog.hide();
							selectInternalIdAndImportPlan(id, hasMultipleXPlanElements, startDateTime, endDateTime);
						}
						else {
							Window.alert(MESSAGES.editInvalidInput());
						}
					}
					catch (Exception e) {
						Window.alert(e.getMessage());
					}
				}
			});
			dialog.setWidth("500px");
			dialog.center();
			dialog.show();
		}
		else {
			selectInternalIdAndImportPlan(id, hasMultipleXPlanElements, null, null);
		}
	}

	private void selectInternalIdAndImportPlan(final String id, final boolean hasMultipleXPlanElements,
			final Date startDateTime, final Date endDateTime) {
		if (configuration.getInternalIdActivated() && !hasMultipleXPlanElements) {
			XsrfTokenServiceAsync xsrf = createTokenService();
			xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
				public void onSuccess(XsrfToken token) {
					ManagerServiceAsync managerService = createManagerService(token);
					managerService.retrieveMatchingInternalIds(id, new AlertFailureCallback<Map<String, String>>() {

						@Override
						public void onSuccess(Map<String, String> result) {
							if (result.size() == 0) {
								Window.alert(MESSAGES.noMatchingInternalIdFound());
							}
							else {
								InternalIdDialog internalIdDialog = new InternalIdDialog(result);
								NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler(
										internalIdDialog);
								internalIdDialog.addNextSubmittedHandler(nextSubmittedHandler);
								internalIdDialog.center();
								internalIdDialog.show();
							}
						}

						private NextSubmittedHandler createNextSubmittedHandler(
								final InternalIdDialog internalIdDialog) {
							return new NextSubmittedHandler() {
								@Override
								public void onNextSubmitted() {
									String internalId = internalIdDialog.retrieveSelectedInternalId();
									if (internalId != null) {
										internalIdDialog.hide();
										selectLegislationStatusAndImportPlan(id, hasMultipleXPlanElements, internalId,
												startDateTime, endDateTime);
									}
									else {
										Window.alert(MESSAGES.noInternalIdSelected());
									}
								}
							};
						}
					});
				}

				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}
			});
		}
		else {
			selectLegislationStatusAndImportPlan(id, hasMultipleXPlanElements, null, startDateTime, endDateTime);
		}
	}

	private void selectLegislationStatusAndImportPlan(final String id, final boolean hasMultipleXPlanElements,
			final String internalId, final Date startDateTime, final Date endDateTime) {
		if (configuration.isLegislationStatusActivated() && !hasMultipleXPlanElements) {
			XsrfTokenServiceAsync xsrf = createTokenService();
			xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
				public void onSuccess(XsrfToken token) {
					ManagerServiceAsync managerService = createManagerService(token);
					managerService.determineLegislationStatus(id, new AlertFailureCallback<RechtsstandAndPlanStatus>() {

						@Override
						public void onSuccess(RechtsstandAndPlanStatus legislationStatus) {
							LegislationStatusDialog legislationStatusDialog = new LegislationStatusDialog(
									legislationStatus);
							NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler(
									legislationStatusDialog);
							legislationStatusDialog.addNextSubmittedHandler(nextSubmittedHandler);
							legislationStatusDialog.center();
							legislationStatusDialog.show();
						}

						private NextSubmittedHandler createNextSubmittedHandler(
								final LegislationStatusDialog legislationStatusDialog) {
							return new NextSubmittedHandler() {
								@Override
								public void onNextSubmitted() {
									PlanStatus planStatus = legislationStatusDialog.retrieveSelectedLegislationStatus();
									legislationStatusDialog.hide();
									checkCrsAndImportPlan(id, internalId, planStatus, startDateTime, endDateTime);
								}
							};
						}
					});
				}

				public void onFailure(Throwable caught) {
					Window.alert(caught.getMessage());
				}
			});
		}
		else {
			checkCrsAndImportPlan(id, internalId, null, startDateTime, endDateTime);
		}
	}

	private void checkCrsAndImportPlan(final String id, final String internalId, final PlanStatus planStatus,
			final Date startDateTime, final Date endDateTime) {
		XsrfTokenServiceAsync xsrf = createTokenService();
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = createManagerService(token);
				managerService.isCrsSet(id, new AlertFailureCallback<Boolean>() {

					@Override
					public void onSuccess(Boolean isCrsSet) {
						if (isCrsSet)
							evaluateRasterAndImportPlan(id, internalId, null, planStatus, startDateTime, endDateTime);
						else {
							CrsDialog crsWizard = new CrsDialog(configuration);
							NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler(crsWizard);
							crsWizard.addNextSubmittedHandler(nextSubmittedHandler);
							crsWizard.center();
							crsWizard.show();
						}
					}

					private NextSubmittedHandler createNextSubmittedHandler(final CrsDialog crsDialog) {
						return new NextSubmittedHandler() {
							@Override
							public void onNextSubmitted() {
								String crs = crsDialog.retrieveSelectedCrs();
								if (crs != null) {
									crsDialog.hide();
									evaluateRasterAndImportPlan(id, internalId, crs, planStatus, startDateTime,
											endDateTime);
								}
								else {
									Window.alert(MESSAGES.crsDialogNoCrsChosen());
								}
							}
						};
					}
				});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private void evaluateRasterAndImportPlan(final String id, final String internalId, final String defaultCrs,
			final PlanStatus planStatus, final Date startDateTime, final Date endDateTime) {
		XsrfTokenServiceAsync xsrf = createTokenService();
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = createManagerService(token);
				managerService.evaluateRaster(id, new AlertFailureCallback<List<RasterEvaluationResult>>() {

					@Override
					public void onSuccess(List<RasterEvaluationResult> response) {
						boolean allRastersAreValid = checkIfAllRastersAreValid(response);
						if (allRastersAreValid) {
							evaluatePlanNameAndStatusAndImportPlan(id, internalId, defaultCrs, true, planStatus,
									startDateTime, endDateTime);
						}
						else {
							RasterDialog rasterDialog = new RasterDialog(response);
							rasterDialog.addRasterHandler(createRasterHandler());
						}
					}

					private RasterHandler createRasterHandler() {
						return new RasterHandler() {
							@Override
							public void onConfirmImport() {
								evaluatePlanNameAndStatusAndImportPlan(id, internalId, defaultCrs, false, planStatus,
										startDateTime, endDateTime);
							}

							@Override
							public void onConfirmForceImport() {
								evaluatePlanNameAndStatusAndImportPlan(id, internalId, defaultCrs, true, planStatus,
										startDateTime, endDateTime);
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

	private void evaluatePlanNameAndStatusAndImportPlan(final String id, final String internalId,
			final String defaultCrs, final boolean makeRasterConfig, final PlanStatus planStatus,
			final Date startDateTime, final Date endDateTime) {
		XsrfTokenServiceAsync xsrf = createTokenService();
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = createManagerService(token);
				managerService.evaluatePlanNameAndStatus(id, planStatus,
						new AlertFailureCallback<List<PlanNameWithStatusResult>>() {

							@Override
							public void onSuccess(List<PlanNameWithStatusResult> planNameWithStatusResults) {
								Map<String, PlanStatus> alreadyInserted = collectAlreadInsertedPlans(
										planNameWithStatusResults);
								if (alreadyInserted.size() == 0) {
									importPlan(id, internalId, defaultCrs, makeRasterConfig, planStatus, startDateTime,
											endDateTime);
								}
								else {
									PlanNameAndStatusDialogBox planNameAndStatusDialogBox = new PlanNameAndStatusDialogBox(
											alreadyInserted);
									NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler(
											planNameAndStatusDialogBox);
									planNameAndStatusDialogBox.addNextSubmittedHandler(nextSubmittedHandler);
									planNameAndStatusDialogBox.center();
									planNameAndStatusDialogBox.show();

								}
							}

							private Map<String, PlanStatus> collectAlreadInsertedPlans(
									List<PlanNameWithStatusResult> planNameWithStatusResults) {
								Map<String, PlanStatus> alreadyInserted = new HashMap<String, PlanStatus>();
								for (PlanNameWithStatusResult planNameWithStatusResult : planNameWithStatusResults) {
									if (planNameWithStatusResult.isPlanWithSameNameAndStatusExists()) {
										alreadyInserted.put(planNameWithStatusResult.getName(),
												planNameWithStatusResult.getStatus());
									}
								}
								return alreadyInserted;
							}

							private NextSubmittedHandler createNextSubmittedHandler(
									final PlanNameAndStatusDialogBox planNameAndStatusDialogBox) {
								return new NextSubmittedHandler() {
									@Override
									public void onNextSubmitted() {
										planNameAndStatusDialogBox.hide();
										importPlan(id, internalId, defaultCrs, makeRasterConfig, planStatus,
												startDateTime, endDateTime);
									}
								};
							}
						});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private void importPlan(String id, String internalId, String defaultCrs, boolean makeRasterConfig,
			PlanStatus planStatus, Date startDateTime, Date endDateTime) {
		final DialogBox loading = createAndShowImportingDialogBox();
		XsrfTokenServiceAsync xsrf = createTokenService();
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = createManagerService(token);
				managerService.importPlan(id, internalId, defaultCrs, makeRasterConfig, planStatus, startDateTime,
						endDateTime, new AsyncCallback<Boolean>() {
							@Override
							public void onFailure(Throwable caught) {
								if (loading != null)
									loading.hide();

								// if (403 == method.getResponse().getStatusCode()) {
								// Window.alert(DYNAMIC_MESSAGES.unauthorizedCommunity_Import());
								// }
								Window.alert(caught.getMessage());
							}

							@Override
							public void onSuccess(Boolean result) {
								planListPanel.reload(true);
								if (loading != null)
									loading.hide();
								Window.alert(MESSAGES.loadSuccessful());
							}
						});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private DialogBox createAndShowImportingDialogBox() {
		DialogBox loading = new DialogBox(false, true);
		loading.setText(MESSAGES.loadingFile());
		loading.center();
		loading.show();
		return loading;
	}

	private static XsrfTokenServiceAsync createTokenService() {
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		return xsrf;
	}

	private static ManagerServiceAsync createManagerService(XsrfToken token) {
		ManagerServiceAsync managerService = GWT.create(ManagerService.class);
		((HasRpcToken) managerService).setRpcToken(token);
		return managerService;
	}

}
