package de.latlon.xplan.commons.web;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Simple {@link ButtonCell} which can be disabled.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class DisengageableButtonCell extends com.google.gwt.cell.client.ButtonCell {

    private boolean disabled = false;

    @Override
    public void render( Context context, SafeHtml data, SafeHtmlBuilder sb ) {
        String disabledString = createDisabledString();
        sb.appendHtmlConstant( "<button type=\"button\" tabindex=\"-1\"" + disabledString + ">" );
        if ( data != null ) {
            sb.append( data );
        }
        sb.appendHtmlConstant( "</button>" );
    }

    private String createDisabledString() {
        if ( disabled ) {
            return "disabled=\"disabled\"";
        }
        return "";
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled() {
        this.disabled = true;
    }

    public void setEnabled() {
        this.disabled = false;
    }

}