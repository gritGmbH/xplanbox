package de.latlon.xplan.manager.web.client.gui.dialog;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

/**
 * Wizard to select a crs.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class CrsDialog extends WizardDialogBox {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final ManagerWebConfiguration configuration;

    private final RadioButton defaultCrsRadioButton;

    private final RadioButton selectCrsRadioButton;

    private final ListBox crsSelection;

    public CrsDialog( ManagerWebConfiguration configuration ) {
        super( MESSAGES.crsDialogHeader() );
        this.configuration = configuration;
        defaultCrsRadioButton = createRadioButton( MESSAGES.crsDialogDefaultCrs(), true );
        selectCrsRadioButton = createRadioButton( MESSAGES.crsDialogSelectCrs(), false );
        crsSelection = createCrsSelectionListBox();
        setContent( createGui() );
    }

    /**
     * @return the CRS selected by the user, may be <code>null</code>
     */
    public String retrieveSelectedCrs() {
        if ( selectCrsRadioButton.getValue() ) {
            return retrieveSelectedCrsFromSelectListBox();
        } else if ( defaultCrsRadioButton.getValue() )
            return configuration.getCrsDialogDefaultCrs();
        return null;
    }

    private Panel createGui() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing( 10 );
        mainPanel.setWidth( "100%" );
        mainPanel.setHorizontalAlignment( ALIGN_LEFT );
        mainPanel.add( new Label( MESSAGES.crsDialogDescription() ) );
        mainPanel.add( defaultCrsRadioButton );
        mainPanel.add( createSelectCrsPanel() );
        return mainPanel;
    }

    private Widget createSelectCrsPanel() {
        HorizontalPanel selectCrsPanel = new HorizontalPanel();
        selectCrsPanel.add( selectCrsRadioButton );
        selectCrsPanel.add( crsSelection );
        return selectCrsPanel;
    }

    private ListBox createCrsSelectionListBox() {
        ListBox listBox = new ListBox();
        listBox.addChangeHandler( new ChangeHandler() {
            @Override
            public void onChange( ChangeEvent event ) {
                selectCrsRadioButton.setValue( true );
            }
        } );
        for ( String crs : configuration.getCrsDialogChooseCrs() )
            listBox.addItem( crs );
        return listBox;
    }

    private String retrieveSelectedCrsFromSelectListBox() {
        int selectedIndex = crsSelection.getSelectedIndex();
        if ( selectedIndex > -1 )
            return crsSelection.getValue( selectedIndex );
        return null;
    }

    private RadioButton createRadioButton( String text, boolean isSelected ) {
        RadioButton radioButton = new RadioButton( "crsSelect", text );
        radioButton.setValue( isSelected );
        return radioButton;
    }

}