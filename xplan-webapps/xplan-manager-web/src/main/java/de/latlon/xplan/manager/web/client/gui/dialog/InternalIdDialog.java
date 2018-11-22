package de.latlon.xplan.manager.web.client.gui.dialog;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Dialog to select an internal id.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class InternalIdDialog extends WizardDialogBox {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final ListBox listBox;

    public InternalIdDialog( Map<String, String> result ) {
        super( MESSAGES.internalIdDialogBoxTitle() );
        listBox = createListBox( result );
        setContent( createPanel() );
    }

    /**
     * @return the selected internal id, <code>null</code> if no entry is selected
     */
    public String retrieveSelectedInternalId() {
        int selectedIndex = listBox.getSelectedIndex();
        if ( selectedIndex >= 0 ) {
            String internalIdFromBox = listBox.getValue( selectedIndex );
            return internalIdFromBox.substring( internalIdFromBox.indexOf( ": " ) + 2 );
        }
        return null;
    }

    private Panel createPanel() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 20 );
        panel.add( listBox );
        return panel;
    }

    private ListBox createListBox( Map<String, String> result ) {
        final ListBox listBox = new ListBox();
        for ( Map.Entry<String, String> id2name : result.entrySet() ) {
            String optionName = id2name.getValue() + ": " + id2name.getKey();
            listBox.addItem( optionName );
        }
        return listBox;
    }

}