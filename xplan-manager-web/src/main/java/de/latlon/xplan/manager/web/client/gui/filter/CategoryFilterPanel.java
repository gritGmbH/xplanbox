package de.latlon.xplan.manager.web.client.gui.filter;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static java.util.Arrays.asList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.filter.CategoryFilter;
import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

/**
 * GUI component containing the category filter of the plan list.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CategoryFilterPanel extends AbstractFilterPanel implements ResetableFilterPanel {

    private final XPlanWebMessages messages = GWT.create( XPlanWebMessages.class );

    private final ListBox categoryListBox;

    public CategoryFilterPanel( FilterExecutor filterExecutor, ManagerWebConfiguration configuration ) {
        super( filterExecutor );
        categoryListBox = createCategoryListBox( configuration );
        createUi();
    }

    @Override
    public void reset() {
        categoryListBox.setSelectedIndex( 0 );
        updateFilter( null );
    }

    private void createUi() {
        Widget layout = createLayout();
        this.setWidget( layout );
    }

    private Widget createLayout() {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        layout.setCellSpacing( 5 );
        layout.setWidget( 1, 1, createCategoryLabel() );
        layout.setWidget( 2, 1, categoryListBox );
        return layout;
    }

    private Widget createCategoryLabel() {
        return new Label( messages.filterCategoryLabel() );
    }

    private ListBox createCategoryListBox( final ManagerWebConfiguration configuration ) {
        final ListBox categoryListBox = new ListBox();
        categoryListBox.addChangeHandler( new ChangeHandler() {

            @Override
            public void onChange( ChangeEvent event ) {
                PlanFilter categoryFilter = createCategoryFilter( categoryListBox );
                updateAndExecuteFilter( categoryFilter );
            }

            private CategoryFilter createCategoryFilter( final ListBox categoryListBox ) {
                int selectedIndex = categoryListBox.getSelectedIndex();
                if ( selectedIndex <= 0 )
                    return new CategoryFilter( null );
                else if ( selectedIndex == categoryListBox.getItemCount() - 1 ) {
                    return new CategoryFilter( asList( configuration.getCategoryFilterValues() ), true );
                }
                return new CategoryFilter( categoryListBox.getValue( selectedIndex ) );
            }
        } );
        categoryListBox.setTitle( messages.filterCategoryTooltip() );
        addItems( categoryListBox, configuration );
        return categoryListBox;
    }

    private void addItems( ListBox categoryListBox, ManagerWebConfiguration configuration ) {
        categoryListBox.addItem( messages.filterCategorySelectionAll() );
        for ( String category : configuration.getCategoryFilterValues() ) {
            categoryListBox.addItem( category );
        }
        categoryListBox.addItem( messages.filterCategorySelectionOther() );
    }

}