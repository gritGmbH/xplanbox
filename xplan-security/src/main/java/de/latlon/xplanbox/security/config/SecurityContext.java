package de.latlon.xplanbox.security.config;

import de.latlon.xplanbox.security.authentication.UnauthorizedAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.security" })
public class SecurityContext {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityContext.class);

	@Autowired
	private UnauthorizedAuthenticationEntryPoint unauthorizedAuthenticationEntryPoint;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		LOG.info("Configure security.");
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic()
			.authenticationEntryPoint(unauthorizedAuthenticationEntryPoint);
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
			.password(passwordEncoder().encode("password"))
			.roles("USER_ROLE")
			.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}