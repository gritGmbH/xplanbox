/*-
 * #%L
 * xplan-validator-web-commons - Modul zur Gruppierung aller Webapps
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
package de.latlon.xplan.validator.web.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;
import de.latlon.xplan.validator.web.client.service.ValidationServiceAsync;

public class PollingTextBox extends TextBox {

	private final Timer timer;

	public PollingTextBox(ValidationServiceAsync validationService) {
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
