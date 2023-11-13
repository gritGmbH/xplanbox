package de.latlon.xplanbox.security.authentication;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
@Component
public class UnauthorizedAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}