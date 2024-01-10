/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.core.gwt.commons.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationServiceAsync;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class PollingTextBox extends TextBox {

	private final Timer timer;

	public PollingTextBox( ValidationServiceAsync validationService) {
		timer = new Timer() {
			@Override
			public void run() {
				String status = getText();
				setText(status + ".");
				validationService.poll(new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable throwable) {
						// dead
						// Window.alert("dead" + throwable.getMessage());
					}

					@Override
					public void onSuccess(Boolean aBoolean) {
						// alive
						// Window.alert("alive " + aBoolean);
					}
				});
			}
		};
		// Schedule the timer to run every 5 seconds.
		timer.scheduleRepeating(5000);
	}

	public void stop() {
		timer.cancel();
	}

}
