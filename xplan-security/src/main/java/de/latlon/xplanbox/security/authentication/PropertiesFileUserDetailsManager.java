package de.latlon.xplanbox.security.authentication;

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
