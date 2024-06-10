/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.server.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.jakarta.RemoteServiceServlet;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.profile.SemanticProfiles;
import de.latlon.xplanbox.core.gwt.commons.client.service.ValidationConfigService;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationProfile;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RemoteServiceRelativePath("validationconfig")
public class DefaultValidationConfigService extends RemoteServiceServlet implements ValidationConfigService {

	@Autowired
	private SemanticProfiles semanticProfiles;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	/**
	 * @return the validation config
	 */
	@Override
	public ValidationConfig retrieveValidationConfig() {
		ValidationConfig validationConfig = new ValidationConfig();
		if (semanticProfiles.getProfileValidators() != null) {
			for (RulesMetadata profile : semanticProfiles.getProfileMetadata()) {
				ValidationProfile validationProfile = new ValidationProfile(profile.getId(), profile.getName(),
						profile.getDescription());
				validationConfig.addProfile(validationProfile);
			}
		}
		return validationConfig;
	}

}
