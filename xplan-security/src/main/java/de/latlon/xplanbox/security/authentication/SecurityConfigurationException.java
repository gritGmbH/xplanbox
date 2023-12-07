package de.latlon.xplanbox.security.authentication;

/**
 * Indicates a misconfiguration in the security module.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
public class SecurityConfigurationException extends Exception {

	public SecurityConfigurationException(Throwable e) {
		super(e);
	}

}
