//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.client.gui.editor;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.client.gui.validation.ValidationUtils.areComponentsValid;
import static de.latlon.xplan.manager.web.client.service.ManagerService.Util.getService;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
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
import de.latlon.xplan.manager.web.client.gui.editor.change.ChangesXplan30Panel;
import de.latlon.xplan.manager.web.client.gui.editor.change.ChangesXplan41Panel;
import de.latlon.xplan.manager.web.client.gui.editor.raster.RasterBasisPanel;
import de.latlon.xplan.manager.web.client.gui.editor.raster.RasterPlanChangesPanel;
import de.latlon.xplan.manager.web.client.gui.editor.reference.ReferencesPanel;
import de.latlon.xplan.manager.web.client.gui.editor.text.TextsPanel;
import de.latlon.xplan.manager.web.client.gui.editor.validityPeriod.ValidityPeriodPanel;
import de.latlon.xplan.manager.web.client.gui.event.EditorCanceledEvent;
import de.latlon.xplan.manager.web.client.gui.event.EditorFinishedEvent;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * Main Editor Panel with different fieldsets and buttons to submit and cancel.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class EditorPanel extends DecoratorPanel {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final HandlerManager eventBus;

    private final BaseDataPanel baseDataPanel;

    private final ValidityPeriodPanel validityPeriodPanel = new ValidityPeriodPanel();

    private final AbstractEditorSubPanelWithTable<Change> changesPanel;

    private final TextsPanel textsPanel;

    private final ReferencesPanel referencesPanel;

    private final RasterBasisPanel rasterBasisPanel;

    private final RasterPlanChangesPanel rasterPlanChangesPanel;

    private String planId;

    public EditorPanel( EditVersion version, HandlerManager eventBus ) {
        this.eventBus = eventBus;
        baseDataPanel = new BaseDataPanel( version );
        changesPanel = createChangePanel( version );
        textsPanel = new TextsPanel( version );
        referencesPanel = new ReferencesPanel( version );
        rasterBasisPanel = new RasterBasisPanel( version );
        rasterPlanChangesPanel = new RasterPlanChangesPanel( version );
        FormPanel form = createForm();
        this.getElement().setId( "editor-panel" );
        this.setWidget( form );
    }

    void setXPlanToEdit( String planId, XPlanToEdit xPlantoEdit ) {
        this.planId = planId;
        baseDataPanel.setBaseData( xPlantoEdit.getBaseData() );
        validityPeriodPanel.setValidityPeriod( xPlantoEdit.getValidityPeriod() );
        changesPanel.setValues( xPlantoEdit.getChanges() );
        textsPanel.setValues( xPlantoEdit.getTexts() );
        referencesPanel.setValues( xPlantoEdit.getReferences() );
        rasterBasisPanel.setRasterBasis( xPlantoEdit.getRasterBasis() );
        rasterPlanChangesPanel.setRasterPlanChanges( xPlantoEdit.getRasterPlanChanges() );
    }

    private FormPanel createForm() {
        FormPanel form = new FormPanel();
        form.setWidget( createLayout( form ) );

        form.addSubmitHandler( new FormPanel.SubmitHandler() {
            @Override
            public void onSubmit( SubmitEvent event ) {
                if ( isValid() ) {
                    XPlanToEdit xPlanToEdit = retrieveXPlanToEdit();
                    evaluateRasterAndUpdatePlan( xPlanToEdit );
                } else {
                    Window.alert( MESSAGES.editInvalidInput() );
                }
            }
        } );
        return form;
    }

    private XPlanToEdit retrieveXPlanToEdit() {
        XPlanToEdit xPlanToEdit = new XPlanToEdit();
        xPlanToEdit.setBaseData( baseDataPanel.retrieveBaseDataToEdit() );
        xPlanToEdit.setChanges( changesPanel.getValues() );
        xPlanToEdit.setTexts( textsPanel.getValues() );
        xPlanToEdit.setReferences( referencesPanel.getValues() );
        xPlanToEdit.setValidityPeriod( validityPeriodPanel.retrieveValidityPeriodToEdit() );
        xPlanToEdit.setRasterBasis( rasterBasisPanel.retrieveRasterBasis() );
        xPlanToEdit.setRasterPlanChanges( rasterPlanChangesPanel.retrieveRasterPlanChanges() );
        return xPlanToEdit;
    }

    private HorizontalPanel createButtonBar( final FormPanel form ) {
        Button saveButton = new Button( MESSAGES.editSaveButton(), new ClickHandler() {
            public void onClick( ClickEvent event ) {
                form.submit();
            }
        } );

        Button cancelButton = new Button( MESSAGES.editCancelButton(), new ClickHandler() {
            public void onClick( ClickEvent event ) {
                eventBus.fireEvent( new EditorCanceledEvent() );
            }
        } );

        HorizontalPanel buttonBar = new HorizontalPanel();
        buttonBar.setSpacing( 10 );
        buttonBar.setHorizontalAlignment( ALIGN_CENTER );
        buttonBar.add( cancelButton );
        buttonBar.add( saveButton );
        return buttonBar;
    }

    private VerticalPanel createLayout( FormPanel form ) {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 10 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( baseDataPanel );
        panel.add( validityPeriodPanel );
        panel.add( changesPanel );
        panel.add( textsPanel );
        panel.add( referencesPanel );
        panel.add( rasterBasisPanel );
        // #3305 - "Aenderung von Rasterplaenen" was scope of the project lgvxplanisk4, but not longer needed.
        // panel.add( rasterPlanChangesPanel );
        panel.add( createButtonBar( form ) );
        return panel;
    }

    private void updatePlan( XPlanToEdit xPlanToEdit, boolean updateRasterConfig, final DialogBox saveDialogBox ) {
        getService().editPlan( planId, updateRasterConfig, xPlanToEdit, new MethodCallback<Void>() {

            @Override
            public void onSuccess( Method method, Void response ) {
                if ( saveDialogBox != null )
                    saveDialogBox.hide();
                eventBus.fireEvent( new EditorFinishedEvent() );
            }

            @Override
            public void onFailure( Method method, Throwable exception ) {
                if ( saveDialogBox != null )
                    saveDialogBox.hide();
                Window.alert( "Fehler beim Speichern: " + method.getResponse().getText() );
            }
        } );
    }

    private void evaluateRasterAndUpdatePlan( final XPlanToEdit xPlanToEdit ) {
        final DialogBox saveDialogBox = createAndShowSaveDialogBox();
        getService().evaluateRaster( planId, xPlanToEdit, new MethodCallback<List<RasterEvaluationResult>>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, List<RasterEvaluationResult> response ) {
                boolean allRastersAreValid = checkIfAllRastersAreValid( response );
                if ( allRastersAreValid ) {
                    updatePlan( xPlanToEdit, true, saveDialogBox );
                } else {
                    RasterDialog rasterDialog = new RasterDialog( response );
                    rasterDialog.addRasterHandler( createRasterHandler() );
                }
            }

            private RasterHandler createRasterHandler() {
                return new RasterHandler() {
                    @Override
                    public void onConfirmImport() {
                        updatePlan( xPlanToEdit, false, saveDialogBox );
                    }

                    @Override
                    public void onConfirmForceImport() {
                        updatePlan( xPlanToEdit, true, saveDialogBox );
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

    private boolean isValid() {
        return areComponentsValid( baseDataPanel, validityPeriodPanel );
    }

    private DialogBox createAndShowSaveDialogBox() {
        DialogBox saving = new DialogBox( false, true );
        saving.setText( MESSAGES.editingSaving() );
        saving.center();
        saving.show();
        return saving;
    }

    private AbstractEditorSubPanelWithTable<Change> createChangePanel( EditVersion version ) {
        if ( XPLAN_3.equals( version ) )
            return new ChangesXplan30Panel();
        return new ChangesXplan41Panel();
    }

}