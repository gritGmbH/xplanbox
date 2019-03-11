package de.latlon.xplan.manager.web.client.gui;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.service.ManagerService.Util.getService;
import static de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener.FinishStatus.NEXT;

import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.ListDataProvider;

import de.latlon.xplan.commons.web.CloseableDialogBox;
import de.latlon.xplan.commons.web.DisengageableButtonCell;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.client.ValidatorOptionsDialog;
import de.latlon.xplan.validator.web.client.report.ReportDownloadFinishedListener;

/**
 * Files system panel of the xplan manager web gui.
 * 
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class UploadPanel extends DecoratorPanel {

    private final XPlanWebMessages messages = GWT.create( XPlanWebMessages.class );

    private final CellTable<XPlan> uploadedPlanTable = new CellTable<XPlan>();

    private final ListDataProvider<XPlan> dataProviderFileSystem = new ListDataProvider<XPlan>();

    private final ImportWizardCreator importWizardCreator;

    private DialogBox uploading;

    public UploadPanel( ManagerWebConfiguration configuration, PlanListPanel planListPanel ) {
        this.importWizardCreator = new ImportWizardCreator( configuration, planListPanel );
        createUi();
    }

    private void createUi() {
        HorizontalPanel uploadPanel = createUploadPanel();
        initUploadedPlanTable();
        FlexTable layout = createLayout( uploadPanel );
        this.setWidget( layout );
    }

    private HorizontalPanel createUploadPanel() {
        HorizontalPanel xPlanTableFileSystemHeader = new HorizontalPanel();
        xPlanTableFileSystemHeader.setSpacing( 10 );
        xPlanTableFileSystemHeader.add( createUploadWidget() );
        xPlanTableFileSystemHeader.add( createHelpButton() );
        return xPlanTableFileSystemHeader;
    }

    private FlexTable createLayout( HorizontalPanel uploadPanel ) {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_CENTER );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_CENTER );
        layout.setWidget( 1, 1, uploadPanel );
        layout.setWidget( 2, 1, uploadedPlanTable );
        return layout;
    }

    private void initUploadedPlanTable() {
        uploadedPlanTable.setKeyboardSelectionPolicy( HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED );
        uploadedPlanTable.setPageSize( 1 );
        initUploadedPlanTableColumns();
        dataProviderFileSystem.addDataDisplay( uploadedPlanTable );
        uploadedPlanTable.setVisible( false );
    }

    private void initUploadedPlanTableColumns() {
        addNameColumn( uploadedPlanTable );
        addValidationStatusColumn( uploadedPlanTable );
        addValidationColumn( uploadedPlanTable );
        addImportColumn( uploadedPlanTable );
        addRemoveColumn( uploadedPlanTable );
    }

    private void addNameColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> nameColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getName();
            }
        };
        nameColumn.setSortable( true );
        xPlanTable.addColumn( nameColumn, messages.nameColumn() );
    }

    private void addValidationColumn( CellTable<XPlan> xPlanTable ) {
        ButtonCell validatedButtonCell = new ButtonCell();
        Column<XPlan, String> validatedColumn = new Column<XPlan, String>( validatedButtonCell ) {
            @Override
            public String getValue( XPlan object ) {
                return messages.validate();
            }
        };
        validatedColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                final CloseableDialogBox dialog = new CloseableDialogBox( messages.validationTitle() );
                ValidatorOptionsDialog validatorOptions = createValidatorOptions( dialog );
                dialog.setContent( validatorOptions );
                dialog.show();
            }

        } );
        validatedColumn.setSortable( true );
        xPlanTable.addColumn( validatedColumn );
    }

    private void addValidationStatusColumn( CellTable<XPlan> xPlanTable ) {
        TextCell validatedButtonCell = new TextCell();
        Column<XPlan, String> validatedColumn = new Column<XPlan, String>( validatedButtonCell ) {
            @Override
            public String getValue( XPlan object ) {
                return " ";
            }

            @Override
            public String getCellStyleNames( Cell.Context context, XPlan object ) {
                if ( object.isValidated() )
                    return "cellButton " + ( object.isValid() ? "buttonValid" : "buttonNotValid" );
                else
                    return "cellButton buttonNotValidated";
            }
        };
        xPlanTable.addColumn( validatedColumn, messages.validated() );
    }

    private void addImportColumn( CellTable<XPlan> xPlanTable ) {
        final DisengageableButtonCell importButtonCell = new DisengageableButtonCell();
        importButtonCell.setDisabled();
        Column<XPlan, String> importButtonColumn = new Column<XPlan, String>( importButtonCell ) {
            @Override
            public String getValue( XPlan object ) {
                if ( object.isValid() )
                    importButtonCell.setEnabled();
                else
                    importButtonCell.setDisabled();
                return messages.load();
            }
        };
        importButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                if ( object.isValid() ) {
                    importWizardCreator.importPlan( object.getId() );
                } else {
                    Window.alert( messages.loadNotPossible() );
                }
            }
        } );
        xPlanTable.addColumn( importButtonColumn );
    }

    private void addRemoveColumn( final CellTable<XPlan> xPlanTable ) {
        ButtonCell removeButtonCell = new ButtonCell();
        final Column<XPlan, String> removeButtonColumn = new Column<XPlan, String>( removeButtonCell ) {
            @Override
            public String getValue( XPlan object ) {
                return "";
            }
        };
        removeButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                if ( Window.confirm( messages.reallyDiscardPlan( object.getName() ) ) )
                    removePlan( object.getId() );
            }
        } );
        removeButtonColumn.setCellStyleNames( "removeButtonColumn" );
        xPlanTable.addCellPreviewHandler( new Handler<XPlan>() {
            @Override
            public void onCellPreview( CellPreviewEvent<XPlan> event ) {
                int columnIndex = event.getColumn();
                if ( xPlanTable.getColumnIndex( removeButtonColumn ) == columnIndex
                     && "mouseover".equals( event.getNativeEvent().getType() ) ) {
                    int index = event.getIndex();
                    xPlanTable.getRowElement( index ).getCells().getItem( columnIndex ).setTitle( messages.deletePlan() );
                }
            }
        } );
        xPlanTable.addColumn( removeButtonColumn );
    }

    private ValidatorOptionsDialog createValidatorOptions( final CloseableDialogBox dialog ) {
        ReportDownloadFinishedListener reportDownloadFinishedListener = new ReportDownloadFinishedListener() {
            @Override
            public void downloadFinished( FinishStatus finishStatus ) {
                reload();
                if ( NEXT.equals( finishStatus ) )
                    dialog.hide();
            }
        };
        return new ValidatorOptionsDialog( reportDownloadFinishedListener, messages.reportCloseButtonTitle(),
                        messages.reportNextButtonTitle() );
    }

    private Widget createUploadWidget() {
        HorizontalPanel vPanel = new HorizontalPanel();
        vPanel.setVerticalAlignment( HasVerticalAlignment.ALIGN_MIDDLE );
        vPanel.getElement().setId( "fileUploadPanel" );
        final FormPanel form = new FormPanel();
        form.setAction( GWT.getHostPageBaseURL() + GWT.getModuleName() + "/rest/manager/plan/" );
        form.setEncoding( FormPanel.ENCODING_MULTIPART );
        form.setMethod( FormPanel.METHOD_POST );

        form.setWidget( vPanel );
        Label l = new Label( messages.addPlan() );
        l.setStylePrimaryName( "stdFont" );
        vPanel.add( l );

        final FileUpload upload = new FileUpload();
        upload.setName( "planZipFile" );
        upload.setStylePrimaryName( "stdFont" );
        vPanel.add( upload );

        Button uploadButton = createUploadButton( form, upload );
        uploadButton.setStylePrimaryName( "stdFont" );
        vPanel.add( uploadButton );

        form.addSubmitHandler( new FormPanel.SubmitHandler() {
            @Override
            public void onSubmit( FormPanel.SubmitEvent event ) {
                if ( upload.getFilename().length() == 0 ) {
                    uploading.hide();
                    Window.alert( messages.uploadFailed() );
                    event.cancel();
                }
            }
        } );
        form.addSubmitCompleteHandler( new FormPanel.SubmitCompleteHandler() {
            @Override
            public void onSubmitComplete( FormPanel.SubmitCompleteEvent event ) {
                uploading.hide();
                reload();
                Window.alert( event.getResults() );
            }
        } );

        return form;
    }

    private Button createUploadButton( final FormPanel form, final FileUpload upload ) {
        return new Button( messages.uploadButtonTitle(), new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                if ( !upload.getFilename().toLowerCase().endsWith( ".zip" ) )
                    showWrongFileEndingDialog();
                else {
                    form.submit();
                    showUploadDialogBox();
                }
            }

            private void showWrongFileEndingDialog() {
                final DialogBox errorUpload = new DialogBox( false, true );
                Button closeButton = new Button( messages.close(), new ClickHandler() {
                    public void onClick( ClickEvent event ) {
                        errorUpload.hide();
                    }
                } );
                errorUpload.setText( messages.fileNameMustEndWithZip() );
                errorUpload.add( closeButton );
                errorUpload.center();
                errorUpload.show();
            }

            private void showUploadDialogBox() {
                uploading = new DialogBox( false, true );
                uploading.setText( messages.uploadingFile() );
                uploading.center();
                uploading.show();
            }
        } );
    }

    private Button createHelpButton() {
        Button help = new Button( messages.help() );
        help.setStylePrimaryName( "stdFont" );
        help.addClickHandler( new ClickHandler() {
            public void onClick( ClickEvent event ) {
                final DialogBox helpDialog = new DialogBox( false, true );
                Button closeButton = new Button( messages.close(), new ClickHandler() {
                    @Override
                    public void onClick( ClickEvent event ) {
                        helpDialog.hide();
                    }
                } );
                VerticalPanel vPanel = new VerticalPanel();
                HTML html = new HTML( messages.helpContent() );
                vPanel.add( html );
                vPanel.add( closeButton );
                helpDialog.add( vPanel );
                helpDialog.setWidth( "400px" );
                helpDialog.center();
                helpDialog.show();
            }
        } );
        return help;
    }

    private void removePlan( String id ) {
        getService().removePlanFromFileSystem( id, new MethodCallback<Void>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                reload();
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, Void result ) {
                reload();
                Window.alert( messages.deleteSuccessful() );
            }
        } );
    }

    private void reload() {
        getService().getPlanFromLocal( new MethodCallback<XPlan>() {
            @Override
            public void onFailure( Method method, Throwable caught ) {
                uploadedPlanTable.setRowCount( 0, true );
                Window.alert( method.getResponse().getText() );
            }

            @Override
            public void onSuccess( Method method, XPlan plan ) {
                List<XPlan> list = dataProviderFileSystem.getList();
                list.clear();
                int newRowCount = plan != null ? 1 : 0;
                uploadedPlanTable.setRowCount( newRowCount, true );
                if ( plan != null ) {
                    list.add( plan );
                    uploadedPlanTable.setVisible( true );
                } else {
                    uploadedPlanTable.setVisible( false );
                }
                ColumnSortEvent.fire( uploadedPlanTable, uploadedPlanTable.getColumnSortList() );

            }
        } );
    }

}