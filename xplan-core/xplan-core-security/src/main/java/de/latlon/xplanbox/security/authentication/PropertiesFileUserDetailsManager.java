/*-
 * #%L
 * xplan-security - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.security.authentication;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * {@link UserDetailsManager} managing user details from properties file
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
public class PropertiesFileUserDetailsManager implements UserDetailsManager {

	private static final Logger LOG = LoggerFactory.getLogger(PropertiesFileUserDetailsManager.class);

	private final Map<String, String> usersAndEncryptedPasswords;

	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public PropertiesFileUserDetailsManager(String userPropertiesFile, PasswordEncoder passwordEncoder)
			throws SecurityConfigurationException {
		try (FileInputStream inputStream = new FileInputStream(userPropertiesFile)) {
			Properties users = new Properties();
			users.load(inputStream);
			this.usersAndEncryptedPasswords = users.entrySet()
				.stream()
				.collect(Collectors.toMap(entry -> (String) entry.getKey(),
						entry -> passwordEncoder.encode((String) entry.getValue())));
		}
		catch (IOException e) {
			LOG.error("Properties file with users could not be read. ", e);
			throw new SecurityConfigurationException(e);
		}
	}

	@Override
	public void createUser(UserDetails user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateUser(UserDetails user) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteUser(String username) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean userExists(String username) {
		return usersAndEncryptedPasswords.containsKey(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!userExists(username)) {
			throw new UsernameNotFoundException(username);
		}
		String password = this.usersAndEncryptedPasswords.get(username);
		return User.withUsername(username).password(password).roles("USER_ROLE").build();
	}

}
