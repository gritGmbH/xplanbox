package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import com.google.gwt.user.client.ui.TextBox;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class UrlTextBox extends TextBox implements Validable {

	@Override
	public boolean isValid() {
		String value = getValue();
		if (value != null && !value.isEmpty()) {
			return value.startsWith("http");
		}
		return true;
	}

}
