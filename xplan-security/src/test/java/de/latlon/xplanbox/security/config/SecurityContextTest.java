package de.latlon.xplanbox.security.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@SpringBootTest
@ContextConfiguration(classes = SecurityContext.class)
public class SecurityContextTest {

	@Test
	public void contextLoad() {

	}

}
