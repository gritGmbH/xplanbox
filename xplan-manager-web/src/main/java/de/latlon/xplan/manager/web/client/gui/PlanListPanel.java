package de.latlon.xplan.manager.web.client.gui;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.ADDITIONAL_TYPE;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.ADE;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.ID;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.IMPORT_DATE;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.LEGISLATION_STATUS;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.NAME;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.PLANSTATUS;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.RELEASE_DATE;
import static de.latlon.xplan.manager.web.client.comparator.ColumnComparator.ComparatorType.TYPE;
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.getImportDateFormat;
import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.getReleaseDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.ListDataProvider;

import de.latlon.xplan.commons.web.DisengageableButtonCell;
import de.latlon.xplan.manager.web.client.comparator.ColumnComparator;
import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.gui.dialog.MapPreviewDialog;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.event.EditorStartedEvent;
import de.latlon.xplan.manager.web.client.gui.filter.FilterPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerService;
import de.latlon.xplan.manager.web.client.utils.DateTimeUtils;
import de.latlon.xplan.manager.web.shared.AuthorizationInfo;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.XPlanEnvelope;
import de.latlon.xplan.manager.web.shared.XPlanMetadata;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

/**
 * Manager panel of the xplan manager web gui.
 * 
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class PlanListPanel extends DecoratorPanel {

    private final XPlanWebMessages messages = GWT.create( XPlanWebMessages.class );

    private final CellTable<XPlan> planList = new CellTable<XPlan>();

    private final ListDataProvider<XPlan> dataProviderManager = new ListDataProvider<XPlan>();

    private final ManagerWebConfiguration configuration;

    private final AuthorizationInfo authorizationInfo;

    private final HandlerManager eventBus;

    private List<XPlan> importedPlans;

    private FilterPanel filterPanel;

    public PlanListPanel( HandlerManager eventBus, ManagerWebConfiguration configuration,
                          AuthorizationInfo authorizationInfo ) {
        this.eventBus = eventBus;
        this.configuration = configuration;
        this.authorizationInfo = authorizationInfo;
        FlexTable layout = createMainPanel();
        setWidget( layout );
        reload( true );
    }

    /**
     * loads all imported plans from server and updates the plan list gui component.
     * 
     * @param doReset
     *            true if all filters should be reset, false otherwise
     */
    void reload( final boolean doReset ) {
        ManagerService.Util.getService().getPlansFromManager( new MethodCallback<List<XPlan>>() {
            @Override
            public void onSuccess( Method method, List<XPlan> response ) {
                PlanListPanel.this.importedPlans = response;
                if ( doReset )
                    filterPanel.resetAndFilterPlanList();
                else
                    filterPanel.doFilter();
                ColumnSortEvent.fire( planList, planList.getColumnSortList() );
            }

            @Override
            public void onFailure( Method method, Throwable exception ) {
                planList.setRowCount( 0, true );
                Window.alert( exception.getMessage() );

            }
        } );
    }

    /**
     * Applies all given filters.
     * 
     * @param planFilters
     *            must not be <code>null</code>
     */
    public void applyFilter( List<PlanFilter> planFilters ) {
        List<XPlan> filteredPlans = createdFilteredPlanList( planFilters );
        updatePlanList( filteredPlans );
    }

    private List<XPlan> createdFilteredPlanList( List<PlanFilter> planFilters ) {
        List<XPlan> filteredPlans = new ArrayList<XPlan>();
        for ( XPlan plan : importedPlans ) {
            if ( doAllFiltersMatch( plan, planFilters ) )
                filteredPlans.add( plan );
        }
        return filteredPlans;
    }

    private boolean doAllFiltersMatch( XPlan plan, List<PlanFilter> planFilters ) {
        for ( PlanFilter planFilter : planFilters ) {
            if ( !planFilter.isMatching( plan ) )
                return false;
        }
        return true;
    }

    private void updatePlanList( List<XPlan> plans ) {
        planList.setRowCount( plans.size(), true );
        List<XPlan> planList = dataProviderManager.getList();
        planList.clear();
        planList.addAll( plans );
    }

    private FlexTable createMainPanel() {
        filterPanel = new FilterPanel( this, configuration );
        initPlanList();
        SimplePager managerTablePager = createPlanListPager();
        return createLayout( managerTablePager );
    }

    private SimplePager createPlanListPager() {
        SimplePager.Resources pagerResources = GWT.create( SimplePager.Resources.class );
        SimplePager pager = new SimplePager( SimplePager.TextLocation.CENTER, pagerResources, false, 0, true );
        pager.setDisplay( planList );
        return pager;
    }

    private FlexTable createLayout( SimplePager managerTablePager ) {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_CENTER );
        formatter.setHorizontalAlignment( 3, 1, ALIGN_CENTER );
        layout.setWidget( 1, 1, filterPanel );
        layout.setWidget( 2, 1, planList );
        layout.setWidget( 3, 1, managerTablePager );
        return layout;
    }

    private void initPlanList() {
        planList.setKeyboardSelectionPolicy( HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED );
        initPlanListColumns();
        dataProviderManager.addDataDisplay( planList );
        addColumnSortHandler();
        planList.getColumnSortList().push( planList.getColumn( 0 ) );
    }

    private void initPlanListColumns() {
        addNameColumn( planList );
        addIdColumn( planList );
        addTypeColumn( planList );
        addAdditionalTypeColumn( planList );
        addLegislationStatusColumn( planList );
        addReleaseDateColumn( planList );
        addImportDateColumn( planList );
        addPlanStatusColumn( planList );
        addValidityPeriodColumn( planList );
        addAdeColumn( planList );

        TextHeader actionHeader = new TextHeader( messages.actions() );
        actionHeader.setHeaderStyleNames( "actionHeaderStyle" );
        if ( configuration.isEditorActivated() )
            addEditColumn( planList, actionHeader );
        addPreviewColumn( planList, actionHeader );
        if ( configuration.isPublishingInspirePluActivated() )
            addPublishPluColumn( planList, actionHeader );
        addDownloadColumn( planList, actionHeader );
        addRemoveColumn( planList, actionHeader );
    }

    private void addNameColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> nameColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getName();
            }
        };
        nameColumn.setSortable( true );
        nameColumn.setCellStyleNames( "planListColumn nameColumn" );
        addDataDependentToolTip( xPlanTable, nameColumn, new TooltipCreator() {
            @Override
            public String createTooltip( CellPreviewEvent<XPlan> event ) {
                XPlan xplan = event.getValue();
                return xplan.getName();
            }
        } );
        xPlanTable.addColumn( nameColumn, messages.nameColumn() );
    }

    private void addIdColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> idColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getId();
            }
        };
        idColumn.setSortable( true );
        idColumn.setCellStyleNames( "planListColumn idColumn" );
        xPlanTable.addColumn( idColumn, messages.idColumn() );
    }

    private void addTypeColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> typeColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getType();
            }
        };
        typeColumn.setSortable( true );
        typeColumn.setCellStyleNames( "planListColumn typeColumn" );
        xPlanTable.addColumn( typeColumn, messages.planArt() );
    }

    private void addAdditionalTypeColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> additionalTypeColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getAdditionalType();
            }
        };
        additionalTypeColumn.setSortable( true );
        additionalTypeColumn.setCellStyleNames( "planListColumn additionalTypeColumn" );
        xPlanTable.addColumn( additionalTypeColumn, messages.sonstPlanArt() );
    }

    private void addLegislationStatusColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> legislationStatusColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getLegislationStatus();
            }
        };
        legislationStatusColumn.setSortable( true );
        legislationStatusColumn.setCellStyleNames( "planListColumn legislationStatusColumn" );
        xPlanTable.addColumn( legislationStatusColumn, messages.legislationStatus() );
    }

    private void addReleaseDateColumn( CellTable<XPlan> xPlanTable ) {
        DateTimeFormat dateFormat = getReleaseDateFormat();
        DateCell dateCell = new DateCell( dateFormat );
        Column<XPlan, Date> releaseDateColumn = new Column<XPlan, Date>( dateCell) {
            @Override
            public Date getValue( XPlan object ) {
                return object.getReleaseDate( );
            }
        };
        releaseDateColumn.setSortable( true );
        releaseDateColumn.setCellStyleNames( "planListColumn releaseDateColumn" );
        xPlanTable.addColumn( releaseDateColumn, messages.releaseDate() );
    }

    private void addImportDateColumn( CellTable<XPlan> xPlanTable ) {
        DateTimeFormat dateFormat = getImportDateFormat();
        DateCell dateCell = new DateCell( dateFormat );
        Column<XPlan, Date> importDateColumn = new Column<XPlan, Date>( dateCell) {
            @Override
            public Date getValue( XPlan object ) {
                return object.getImportDate( );
            }
        };
        importDateColumn.setSortable( true );
        importDateColumn.setCellStyleNames( "planListColumn importDateColumn" );
        xPlanTable.addColumn( importDateColumn, messages.importDate() );
    }

    private void addPlanStatusColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> planStatusColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                XPlanMetadata xPlanMetadata = object.getXplanMetadata();
                if ( xPlanMetadata != null && xPlanMetadata.getPlanStatus() != null )
                    return xPlanMetadata.getPlanStatus().getMessage();
                return null;
            }
        };
        planStatusColumn.setSortable( true );
        planStatusColumn.setCellStyleNames( "planListColumn planStatusColumn" );
        xPlanTable.addColumn( planStatusColumn, messages.planStatus() );
    }

    private void addValidityPeriodColumn( final CellTable<XPlan> xPlanTable ) {
        TextCell validatedButtonCell = new TextCell();
        final Column<XPlan, String> validityStatusColumn = new Column<XPlan, String>( validatedButtonCell) {
            @Override
            public String getValue( XPlan object ) {
                return " ";
            }

            @Override
            public String getCellStyleNames( Cell.Context context, XPlan object ) {
                XPlanMetadata xplanMetadata = object.getXplanMetadata();
                if ( xplanMetadata != null ) {
                    if ( isValid( xplanMetadata ) )
                        return "cellButton buttonValid";
                    else
                        return "cellButton buttonNotValid";
                }
                return "cellButton buttonNotValidated";
            }

            private Boolean isValid( XPlanMetadata xplanMetadata ) {
                Date startDateTime = xplanMetadata.getStartDateTime();
                Date endDateTime = xplanMetadata.getEndDateTime();
                return DateTimeUtils.isCurrentDateTimeBetween( startDateTime, endDateTime );
            }
        };
        validityStatusColumn.setCellStyleNames( "planListColumn validityStatusColumn" );
        addDataDependentToolTip( xPlanTable, validityStatusColumn, new TooltipCreator() {
            @Override
            public String createTooltip( CellPreviewEvent<XPlan> event ) {
                XPlanMetadata xplanMetadata = event.getValue().getXplanMetadata();
                Date startDateTime = xplanMetadata.getStartDateTime();
                Date endDateTime = xplanMetadata.getEndDateTime();
                DateTimeFormat dateFormat = DateTimeUtils.getValidityDateFormat();
                if ( startDateTime != null && endDateTime != null )
                    return messages.validityTooltip( dateFormat.format( startDateTime ),
                                                     dateFormat.format( endDateTime ) );
                if ( startDateTime != null && endDateTime == null )
                    return messages.validityTooltipLimitByStartDate( dateFormat.format( startDateTime ) );
                if ( startDateTime == null && endDateTime != null )
                    return messages.validityTooltipLimitByEndDate( dateFormat.format( endDateTime ) );
                return messages.validityTooltipUnlimited();
            }
        } );
        xPlanTable.addColumn( validityStatusColumn, messages.validityStatus() );
    }

    private void addAdeColumn( CellTable<XPlan> xPlanTable ) {
        TextColumn<XPlan> adeColumn = new TextColumn<XPlan>() {
            @Override
            public String getValue( XPlan object ) {
                return object.getAde();
            }
        };
        adeColumn.setSortable( true );
        adeColumn.setCellStyleNames( "planListColumn adeColumn" );
        xPlanTable.addColumn( adeColumn, messages.ade() );
    }

    private void addRemoveColumn( final CellTable<XPlan> xPlanTable, TextHeader columnHeader ) {
        final DisengageableButtonCell removeButtonCell = new DisengageableButtonCell();
        removeButtonCell.setDisabled();
        final Column<XPlan, String> removeButtonColumn = new Column<XPlan, String>( removeButtonCell) {
            @Override
            public String getValue( XPlan xPlan ) {
                if ( isDeletingPermitted( xPlan ) )
                    removeButtonCell.setEnabled();
                else
                    removeButtonCell.setDisabled( );
                return "";
            }
        };
        removeButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                if ( Window.confirm( messages.reallyDiscardPlan( object.getName() ) ) )
                    removePlan( object.getId() );
            }
        } );
        removeButtonColumn.setCellStyleNames( "planListColumn removeButtonColumn" );
        xPlanTable.addColumn( removeButtonColumn, columnHeader );
        addStaticToolTip( xPlanTable, removeButtonColumn, messages.deletePlan() );
    }

    private void addEditColumn( final CellTable<XPlan> xPlanTable, TextHeader columnHeader ) {
        final DisengageableButtonCell editButtonCell = new DisengageableButtonCell();
        final Column<XPlan, String> editButtonColumn = new Column<XPlan, String>( editButtonCell) {
            @Override
            public String getValue( XPlan xPlan ) {
                if ( "BP_Plan".equals( xPlan.getType() )
                     && ( "XPLAN_3".equals( xPlan.getVersion() ) || "XPLAN_41".equals( xPlan.getVersion() ) )
                     && isEditingPermitted( xPlan ) )
                    editButtonCell.setEnabled();
                else
                    editButtonCell.setDisabled( );
                return "";
            }
        };
        editButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan xplan, String value ) {
                editPlan( xplan.getVersion(), xplan.getId() );
            }
        } );
        editButtonColumn.setCellStyleNames( "planListColumn editButtonColumn" );
        xPlanTable.addColumn( editButtonColumn, columnHeader );
        addDataDependentToolTip( xPlanTable, editButtonColumn, new TooltipCreator() {
            @Override
            public String createTooltip( CellPreviewEvent<XPlan> event ) {
                XPlan xPlan = event.getValue();
                if ( !isEditingPermitted( xPlan ) )
                    return messages.editButtonTooltipPermissionDenied();
                else if ( !"BP_Plan".equals( xPlan.getType() ) )
                    return messages.editButtonTooltipIncorrectPlanType();
                else if ( !( "XPLAN_3".equals( xPlan.getVersion() ) || "XPLAN_41".equals( xPlan.getVersion() ) ) )
                    return messages.editButtonTooltipIncorrectVersion();
                return messages.editButtonTooltip();
            }
        } );
    }

    private void addPreviewColumn( final CellTable<XPlan> xPlanTable, TextHeader columnHeader ) {
        ButtonCell previewButtonCell = new ButtonCell();
        final Column<XPlan, String> previewButtonColumn = new Column<XPlan, String>( previewButtonCell) {
            @Override
            public String getValue( XPlan object ) {
                return "";
            }
        };
        previewButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan xplan, String value ) {
                String planName = xplan.getName();
                String planType = xplan.getType();
                PlanStatus planStatus = xplan.getXplanMetadata() != null ? xplan.getXplanMetadata().getPlanStatus()
                                                                         : null;
                XPlanEnvelope bbox = xplan.getBbox();
                MapPreviewDialog mapPreview = new MapPreviewDialog( planName, planType, planStatus, bbox );
                mapPreview.show();
            }
        } );
        previewButtonColumn.setCellStyleNames( "planListColumn previewButtonColumn" );
        xPlanTable.addColumn( previewButtonColumn, columnHeader );
        addStaticToolTip( xPlanTable, previewButtonColumn, messages.mapPreview() );
    }

    private void addDownloadColumn( final CellTable<XPlan> xPlanTable, TextHeader columnHeader ) {
        ButtonCell downloadButtonCell = new ButtonCell();
        final Column<XPlan, String> downloadButtonColumn = new Column<XPlan, String>( downloadButtonCell) {
            @Override
            public String getValue( XPlan object ) {
                return "";
            }
        };
        downloadButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                String url = GWT.getHostPageBaseURL() + GWT.getModuleName() + "/rest/manager/plan/" + object.getId();
                Window.Location.assign( url );
            }
        } );
        downloadButtonColumn.setCellStyleNames( "planListColumn downloadButtonColumn" );
        xPlanTable.addColumn( downloadButtonColumn, columnHeader );
        addStaticToolTip( xPlanTable, downloadButtonColumn, messages.downloadPlan() );
    }


    private void addPublishPluColumn( final CellTable<XPlan> xPlanTable, TextHeader columnHeader ) {
        final DisengageableButtonCell publishPluButtonCell = new DisengageableButtonCell();
        publishPluButtonCell.setDisabled();
        final Column<XPlan, String> publishPluButtonColumn = new Column<XPlan, String>( publishPluButtonCell) {
            @Override
            public String getValue( XPlan xPlan ) {
                if ( "BP_Plan".equals( xPlan.getType() ) && "XPLAN_41".equals( xPlan.getVersion() )
                     && isPublishingPluPermitted( xPlan ) )
                    publishPluButtonCell.setEnabled();
                else
                    publishPluButtonCell.setDisabled();
                return "";
            }
        };
        publishPluButtonColumn.setFieldUpdater( new FieldUpdater<XPlan, String>() {
            public void update( int index, XPlan object, String value ) {
                    publishPlu( object.getId() );
            }
        } );
        publishPluButtonColumn.setCellStyleNames( "planListColumn publishPluButtonColumn" );
        xPlanTable.addColumn( publishPluButtonColumn, columnHeader );
        addStaticToolTip( xPlanTable, publishPluButtonColumn, messages.publishPlu() );
    }

    private void addStaticToolTip( final CellTable<XPlan> xPlanTable, final Column<XPlan, String> column,
                                   final String tooltip ) {
        xPlanTable.addCellPreviewHandler( new Handler<XPlan>() {
            @Override
            public void onCellPreview( CellPreviewEvent<XPlan> event ) {
                int columnIndex = event.getColumn();
                if ( xPlanTable.getColumnIndex( column ) == columnIndex
                     && "mouseover".equals( event.getNativeEvent().getType() ) ) {
                    int index = event.getIndex();
                    xPlanTable.getRowElement( index ).getCells().getItem( columnIndex ).setTitle( tooltip );
                }
            }
        } );
    }

    private void addDataDependentToolTip( final CellTable<XPlan> xPlanTable, final Column<XPlan, String> column,
                                          final TooltipCreator tooltipCreator ) {
        xPlanTable.addCellPreviewHandler( new Handler<XPlan>() {

            @Override
            public void onCellPreview( CellPreviewEvent<XPlan> event ) {
                int columnIndex = event.getColumn();
                if ( xPlanTable.getColumnIndex( column ) == columnIndex
                     && "mouseover".equals( event.getNativeEvent().getType() ) ) {
                    int index = event.getIndex();
                    String tooltip = tooltipCreator.createTooltip( event );
                    xPlanTable.getRowElement( index ).getCells().getItem( columnIndex ).setTitle( tooltip );
                }
            }
        } );
    }

    private void addColumnSortHandler() {
        List<XPlan> plans = dataProviderManager.getList();
        ColumnSortEvent.ListHandler<XPlan> columnSortHandler = new ColumnSortEvent.ListHandler<XPlan>( plans );
        columnSortHandler.setComparator( planList.getColumn( 0 ), new ColumnComparator( NAME ) );
        columnSortHandler.setComparator( planList.getColumn( 1 ), new ColumnComparator( ID ) );
        columnSortHandler.setComparator( planList.getColumn( 2 ), new ColumnComparator( TYPE ) );
        columnSortHandler.setComparator( planList.getColumn( 3 ), new ColumnComparator( ADDITIONAL_TYPE ) );
        columnSortHandler.setComparator( planList.getColumn( 4 ), new ColumnComparator( LEGISLATION_STATUS ) );
        columnSortHandler.setComparator( planList.getColumn( 5 ), new ColumnComparator( RELEASE_DATE ) );
        columnSortHandler.setComparator( planList.getColumn( 6 ), new ColumnComparator( IMPORT_DATE ) );
        columnSortHandler.setComparator( planList.getColumn( 7 ), new ColumnComparator( PLANSTATUS ) );
        columnSortHandler.setComparator( planList.getColumn( 8 ), new ColumnComparator( ADE ) );
        planList.addColumnSortHandler( columnSortHandler );
    }

    private void editPlan( final String version, final String id ) {
        final DialogBox waitDialog = createAndShowDialogBox( messages.editingStarted() );
        ManagerService.Util.getService().getPlanToEdit( id, new MethodCallback<XPlanToEdit>() {

            @Override
            public void onFailure( Method method, Throwable exception ) {
                if ( waitDialog != null )
                    waitDialog.hide();
                Window.alert( exception.getMessage() + " " + method.getResponse().getStatusCode() );
            }

            @Override
            public void onSuccess( Method method, XPlanToEdit xPlantoEdit ) {
                if ( waitDialog != null )
                    waitDialog.hide();
                try {
                    EditVersion codelistVersion = EditVersion.valueOf( version );
                    eventBus.fireEvent( new EditorStartedEvent( id, codelistVersion, xPlantoEdit ) );
                } catch ( IllegalArgumentException e ) {
                    Window.alert( "Unsupported XPlan version for editing: " + version );
                }
            }
        } );
    }

    private void removePlan( String id ) {
        final DialogBox deleting = createAndShowDialogBox( messages.deletingPlan() );
        ManagerService.Util.getService().removePlanFromManager( id, new MethodCallback<Void>() {

            @Override
            public void onFailure( Method method, Throwable exception ) {
                reload( false );
                if ( deleting != null )
                    deleting.hide();
                if ( 403 == method.getResponse().getStatusCode() ) {
                    Window.alert( messages.unauthorizedDelete() );
                } else {
                    Window.alert( exception.getMessage() + " " + method.getResponse().getStatusCode() );
                }
            }

            @Override
            public void onSuccess( Method method, Void response ) {
                reload( false );
                if ( deleting != null )
                    deleting.hide();
                Window.alert( messages.deleteSuccessful() );
            }
        } );
    }

    private void publishPlu( String id ) {
        final DialogBox publishingPlu = createAndShowDialogBox( messages.publishingPlu() );
        ManagerService.Util.getService().publishPlan( id, new MethodCallback<Boolean>() {

            @Override
            public void onFailure( Method method, Throwable exception ) {
                reload( false );
                if ( publishingPlu != null )
                    publishingPlu.hide();
                if ( 403 == method.getResponse().getStatusCode() ) {
                    Window.alert( messages.unauthorizedPublishingPlu() );
                } else {
                    Window.alert( exception.getMessage() + " " + method.getResponse().getStatusCode() );
                }
            }

            @Override
            public void onSuccess( Method method, Boolean isSuccessful ) {
                reload( false );
                if ( publishingPlu != null )
                    publishingPlu.hide();
                if ( isSuccessful )
                    Window.alert( messages.publishingPluSuccessful() );
                else
                    Window.alert( messages.publishingPluFailed() );
            }
        } );
    }

    private DialogBox createAndShowDialogBox( String text ) {
        DialogBox dialog = new DialogBox( false, true );
        dialog.setText( text );
        dialog.center();
        dialog.show();
        return dialog;
    }

    private boolean isPublishingPluPermitted( XPlan xPlan ) {
        return authorizationInfo.isSuperUser() || isOwner( xPlan );
    }

    private boolean isDeletingPermitted( XPlan xPlan ) {
        return authorizationInfo.isSuperUser() || isOwner( xPlan );
    }

    private boolean isEditingPermitted( XPlan xPlan ) {
        return authorizationInfo.isSuperUser() || ( isOwner( xPlan ) && authorizationInfo.isEditor() );
    }

    private boolean isOwner( XPlan plan ) {
        return authorizationInfo.getAuthorizedDistricts().contains( plan.getDistrict() );
    }

    private interface TooltipCreator {
        String createTooltip( CellPreviewEvent<XPlan> event );
    }

}