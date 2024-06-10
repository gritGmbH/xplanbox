/*-
 * #%L
 * xplan-core-security - Modul zur Gruppierung der Kernmodule
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.security.config;

import de.latlon.xplanbox.security.authentication.PropertiesFileUserDetailsManager;
import de.latlon.xplanbox.security.authentication.SecurityConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
@EnableWebSecurity
@Configuration
@Profile("enableSecurity")
@ComponentScan(basePackages = { "de.latlon.xplanbox.security" })
public class SecurityContext {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityContext.class);

	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		LOG.info("Configure security.");
		http.csrf((csrf) -> csrf.disable()).authorizeRequests().anyRequest().authenticated().and().httpBasic();
		return http.build();
	}

	@Bean
	public PropertiesFileUserDetailsManager userDetailsService(
			@Value("#{environment.XPLAN_SECURITY_USER_PROPERTIES_FILE}") String userPropertiesFile,
			PasswordEncoder passwordEncoder) throws SecurityConfigurationException {
		return new PropertiesFileUserDetailsManager(userPropertiesFile, passwordEncoder);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
