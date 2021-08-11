/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.web.client.gui;

import static de.latlon.xplan.manager.web.client.service.ManagerService.Util.getService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import de.latlon.xplan.manager.web.client.gui.dialog.PlanNameAndStatusDialogBox;
import de.latlon.xplan.manager.web.client.gui.dialog.WizardDialogBox;
import de.latlon.xplan.manager.web.client.i18n.DynamicXPlanWebMessages;
import de.latlon.xplan.manager.web.shared.PlanNameWithStatusResult;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DialogBox;

import de.latlon.xplan.manager.web.client.gui.dialog.CrsDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.InternalIdDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.LegislationStatusDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.NextSubmittedHandler;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterDialog;
import de.latlon.xplan.manager.web.client.gui.dialog.RasterHandler;
import de.latlon.xplan.manager.web.client.gui.dialog.ValidityPeriodDialog;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.LegislationStatus;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;

/**
 * Wizard to start imports of plans.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ImportWizardCreator {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final DynamicXPlanWebMessages DYNAMIC_MESSAGES = GWT.create( DynamicXPlanWebMessages.class );

    private final ManagerWebConfiguration configuration;

    private final PlanListPanel planListPanel;

    /**
     * 
     * @param configuration
     *            never <code>null</code>
     * @param planListPanel
     *            never <code>null</code>
     */
    public ImportWizardCreator( ManagerWebConfiguration configuration, PlanListPanel planListPanel ) {
        this.configuration = configuration;
        this.planListPanel = planListPanel;
    }

    /**
     * Imports a plan. Several dialogs are opened during this process, if configured.
     * 
     * @param id
     *            id of plan to import
     */
    public void importPlan( final String id ) {
        selectValidityPeriodAndImportPlan( id );
    }

    private void selectValidityPeriodAndImportPlan( final String id ) {
        if ( configuration.isValidityPeriodActivated() ) {
            final ValidityPeriodDialog dialog = new ValidityPeriodDialog();
            dialog.addNextSubmittedHandler( new NextSubmittedHandler() {
                @Override
                public void onNextSubmitted() {
                    try {
                        if ( dialog.isValid() ) {
                            Date startDateTime = dialog.retrieveStartDateTime();
                            Date endDateTime = dialog.retrieveEndDateTime();
                            dialog.hide();
                            selectInternalIdAndImportPlan( id, startDateTime, endDateTime );
                        } else {
                            Window.alert( MESSAGES.editInvalidInput() );
                        }
                    } catch ( Exception e ) {
                        Window.alert( e.getMessage() );
                    }
                }
            } );
            dialog.setWidth( "500px" );
            dialog.center();
            dialog.show();
        } else {
            selectInternalIdAndImportPlan( id, null, null );
        }
    }

    private void selectInternalIdAndImportPlan( final String id, final Date startDateTime, final Date endDateTime ) {
        if ( configuration.getInternalIdActivated() ) {
            getService().retrieveMatchingInternalIds( id, new MethodCallback<Map<String, String>>() {
                @Override
                public void onFailure( Method method, Throwable caught ) {
                    Window.alert( method.getResponse().getText() );
                }

                @Override
                public void onSuccess( Method method, Map<String, String> result ) {
                    if ( result.size() == 0 ) {
                        Window.alert( MESSAGES.noMatchingInternalIdFound() );
                    } else {
                        InternalIdDialog internalIdDialog = new InternalIdDialog( result );
                        NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler( internalIdDialog );
                        internalIdDialog.addNextSubmittedHandler( nextSubmittedHandler );
                        internalIdDialog.center();
                        internalIdDialog.show();
                    }
                }

                private NextSubmittedHandler createNextSubmittedHandler( final InternalIdDialog internalIdDialog ) {
                    return new NextSubmittedHandler() {
                        @Override
                        public void onNextSubmitted() {
                            String internalId = internalIdDialog.retrieveSelectedInternalId();
                            if ( internalId != null ) {
                                internalIdDialog.hide();
                                selectLegislationStatusAndImportPlan( id, internalId, startDateTime, endDateTime );
                            } else {
                                Window.alert( MESSAGES.noInternalIdSelected() );
                            }
                        }
                    };
                }
            } );
        } else {
            selectLegislationStatusAndImportPlan( id, null, startDateTime, endDateTime );
        }
    }

    private void selectLegislationStatusAndImportPlan( final String id, final String internalId,
                                                       final Date startDateTime, final Date endDateTime ) {
        if ( configuration.isLegislationStatusActivated() ) {
            getService().determineLegislationStatus( id, new MethodCallback<LegislationStatus>() {

                @Override
                public void onFailure( Method method, Throwable caught ) {
                    Window.alert( method.getResponse().getText() );
                }

                @Override
                public void onSuccess( Method method, LegislationStatus legislationStatus ) {
                    LegislationStatusDialog legislationStatusDialog = new LegislationStatusDialog( legislationStatus );
                    NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler( legislationStatusDialog );
                    legislationStatusDialog.addNextSubmittedHandler( nextSubmittedHandler );
                    legislationStatusDialog.center();
                    legislationStatusDialog.show();
                }

                private NextSubmittedHandler
                                createNextSubmittedHandler( final LegislationStatusDialog legislationStatusDialog ) {
                    return new NextSubmittedHandler() {
                        @Override
                        public void onNextSubmitted() {
                            PlanStatus planStatus = legislationStatusDialog.retrieveSelectedLegislationStatus();
                            legislationStatusDialog.hide();
                            checkCrsAndImportPlan( id, internalId, planStatus, startDateTime, endDateTime );
                        }
                    };
                }
            } );
        } else {
            checkCrsAndImportPlan( id, internalId, null, startDateTime, endDateTime );
        }
    }

    private void checkCrsAndImportPlan( final String id, final String internalId, final PlanStatus planStatus,
                                        final Date startDateTime, final Date endDateTime ) {
        getService().isCrsSet( id, new MethodCallback<Boolean>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, Boolean isCrsSet ) {
                if ( isCrsSet )
                    evaluateRasterAndImportPlan( id, internalId, null, planStatus, startDateTime, endDateTime );
                else {
                    CrsDialog crsWizard = new CrsDialog( configuration );
                    NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler( crsWizard );
                    crsWizard.addNextSubmittedHandler( nextSubmittedHandler );
                    crsWizard.center();
                    crsWizard.show();
                }
            }

            private NextSubmittedHandler createNextSubmittedHandler( final CrsDialog crsDialog ) {
                return new NextSubmittedHandler() {
                    @Override
                    public void onNextSubmitted() {
                        String crs = crsDialog.retrieveSelectedCrs();
                        if ( crs != null ) {
                            crsDialog.hide();
                            evaluateRasterAndImportPlan( id, internalId, crs, planStatus, startDateTime, endDateTime );
                        } else {
                            Window.alert( MESSAGES.crsDialogNoCrsChosen() );
                        }
                    }
                };
            }
        } );
    }

    private void evaluateRasterAndImportPlan( final String id, final String internalId, final String defaultCrs,
                                              final PlanStatus planStatus, final Date startDateTime,
                                              final Date endDateTime ) {
        getService().evaluateRaster( id, new MethodCallback<List<RasterEvaluationResult>>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, List<RasterEvaluationResult> response ) {
                boolean allRastersAreValid = checkIfAllRastersAreValid( response );
                if ( allRastersAreValid ) {
                    evaluatePlanNameAndStatusAndImportPlan( id, internalId, defaultCrs, true, planStatus, startDateTime, endDateTime );
                } else {
                    RasterDialog rasterDialog = new RasterDialog( response );
                    rasterDialog.addRasterHandler( createRasterHandler() );
                }
            }

            private RasterHandler createRasterHandler() {
                return new RasterHandler() {
                    @Override
                    public void onConfirmImport() {
                        evaluatePlanNameAndStatusAndImportPlan( id, internalId, defaultCrs, false, planStatus, startDateTime, endDateTime );
                    }

                    @Override
                    public void onConfirmForceImport() {
                        evaluatePlanNameAndStatusAndImportPlan( id, internalId, defaultCrs, true, planStatus, startDateTime, endDateTime );
                    }
                };
            }

            private boolean checkIfAllRastersAreValid( List<RasterEvaluationResult> response ) {
                for ( RasterEvaluationResult rasterResult : response ) {
                    if ( !rasterResult.isConfiguredCrs() || !rasterResult.isSupportedImageFormat() ) {
                        return false;
                    }
                }
                return true;
            }

        } );
    }

    private void evaluatePlanNameAndStatusAndImportPlan( final String id, final String internalId, final String defaultCrs,
                                                         final boolean makeRasterConfig, final PlanStatus planStatus,
                                                         final Date startDateTime, final Date endDateTime ) {
        getService().evaluatePlanNameAndStatus( id, planStatus.getMessage(), new MethodCallback<PlanNameWithStatusResult>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, PlanNameWithStatusResult planNameWithStatusResult ) {
                if ( planNameWithStatusResult.isPlanWithSameNameAndStatusExists() ) {
                    PlanNameAndStatusDialogBox planNameAndStatusDialogBox = new PlanNameAndStatusDialogBox(
                                    planNameWithStatusResult.getName(), planStatus );
                    NextSubmittedHandler nextSubmittedHandler = createNextSubmittedHandler(
                                    planNameAndStatusDialogBox );
                    planNameAndStatusDialogBox.addNextSubmittedHandler( nextSubmittedHandler );
                    planNameAndStatusDialogBox.center();
                    planNameAndStatusDialogBox.show();
                } else {
                    importPlan( id, internalId, defaultCrs, makeRasterConfig, planStatus, startDateTime, endDateTime );
                }
            }

            private NextSubmittedHandler createNextSubmittedHandler(
                            final PlanNameAndStatusDialogBox planNameAndStatusDialogBox ) {
                return new NextSubmittedHandler() {
                    @Override
                    public void onNextSubmitted() {
                        planNameAndStatusDialogBox.hide();
                        importPlan( id, internalId, defaultCrs, makeRasterConfig, planStatus, startDateTime,
                                    endDateTime );
                    }
                };
            }
        } );
    }

    private void importPlan( String id, String internalId, String defaultCrs, boolean makeRasterConfig,
                             PlanStatus planStatus, Date startDateTime, Date endDateTime ) {
        final DialogBox loading = createAndShowImportingDialogBox();
        getService().importPlan( id, internalId, defaultCrs, makeRasterConfig, planStatus, startDateTime, endDateTime,
                                 new MethodCallback<Boolean>() {
                                     @Override
                                     public void onFailure( Method method, Throwable caught ) {
                                         if ( loading != null )
                                             loading.hide();
                                         if ( 403 == method.getResponse().getStatusCode() ) {
                                             Window.alert( DYNAMIC_MESSAGES.unauthorizedCommunity_Import() );
                                         } else {
                                             Window.alert( method.getResponse().getText() );
                                         }
                                     }

                                     @Override
                                     public void onSuccess( Method method, Boolean result ) {
                                         planListPanel.reload( true );
                                         if ( loading != null )
                                             loading.hide();
                                         Window.alert( MESSAGES.loadSuccessful() );
                                     }
                                 } );
    }

    private DialogBox createAndShowImportingDialogBox() {
        DialogBox loading = new DialogBox( false, true );
        loading.setText( MESSAGES.loadingFile() );
        loading.center();
        loading.show();
        return loading;
    }

}
