package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MissingRequestEntity extends XPlanApiException {

	public MissingRequestEntity(String message) {
		super(message);
	}

	public int getStatusCode() {
		return BAD_REQUEST.getStatusCode();
	}

}
