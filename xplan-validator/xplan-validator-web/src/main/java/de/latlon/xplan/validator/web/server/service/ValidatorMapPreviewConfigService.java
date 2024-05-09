/*-
 * #%L
 * xplan-validator-web - Webanwendung XPlanValidatorWeb
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
package de.latlon.xplan.validator.web.server.service;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.MapPreviewManager;
import de.latlon.xplanbox.core.gwt.commons.client.service.MapPreviewConfigService;
import de.latlon.xplanbox.core.gwt.commons.shared.MapPreviewException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

/**
 * Configuration service providing access to XPlanValidatorWMS settings.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @version 3.0
 * @since 3.0
 */
@RemoteServiceRelativePath("mappreviewconfig")
public class ValidatorMapPreviewConfigService extends XsrfProtectedServiceServlet implements MapPreviewConfigService {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorMapPreviewConfigService.class);

	private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

	protected HttpSession session;

	@Autowired
	private Optional<MapPreviewManager> mapPreviewManager;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	public void service(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session = request.getSession(true);
		super.service(request, response);
	}

	@Override
	public boolean isMapPreviewAvailable() throws MapPreviewException {
		return mapPreviewManager.isPresent();
	}

	@Override
	public MapPreviewMetadata createMapPreviewConfig() throws MapPreviewException {
		if (mapPreviewManager.isEmpty())
			throw new MapPreviewException("Map preview manager is not available");
		try {
			XPlan planToVerify = planArchiveManager.readPlanFromSession(session);
			File archive = planArchiveManager.retrieveXPlanArchiveFromFileSystem(planToVerify);

			return mapPreviewManager.get().createConfigurations(archive);
		}
		catch (ValidatorException | IOException | MapPreviewCreationException e) {
			LOG.error("An exception occurred during validation", e);
			throw new MapPreviewException(e.getMessage());
		}
	}

}
