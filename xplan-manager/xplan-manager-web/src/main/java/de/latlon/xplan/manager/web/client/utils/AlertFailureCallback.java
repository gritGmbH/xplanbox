package de.latlon.xplan.manager.web.client.utils;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * {@link AsyncCallback} Implementierung mit alert für failures.
 *
 * @param <T>
 */
public abstract class AlertFailureCallback<T> implements AsyncCallback<T> {

	@Override
	public void onFailure(Throwable caught) {
		Window.alert(caught.getMessage());
	}

}
