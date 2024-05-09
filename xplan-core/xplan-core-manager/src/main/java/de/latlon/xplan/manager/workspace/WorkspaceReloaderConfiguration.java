/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.workspace;

import java.util.List;

import static de.latlon.xplan.manager.workspace.WorkspaceReloadAction.ALL;
import static java.util.Collections.emptyList;

/**
 * Container object for {@link WorkspaceReloader} configuration.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloaderConfiguration {

	private final List<String> urls;

	private final String apiKey;

	private final String user;

	private final String password;

	private final WorkspaceReloadAction workspaceReloadAction;

	/**
	 * Invalid workspace reloader configuration.
	 */
	public WorkspaceReloaderConfiguration() {
		this(emptyList(), "", "", "", ALL);
	}

	/**
	 * Valid workspace reloader configuration.
	 * @param urls URLs of deegree services to reload, never <code>null</code>
	 * @param apiKey ApiKey used for authentication instead of user/password, may be
	 * <code>null</code> if user and password are not <code>null</code>
	 * @param user user used for authentication instead of apiKey, may be
	 * <code>null</code> if apiKey is not <code>null</code>
	 * @param password password used for authentication instead of apiKey, may be
	 * <code>null</code> if apiKey is not <code>null</code>
	 * @param workspaceReloadAction the operation to execute for workspace reload, may be
	 * <code>null</code> (defaults to ALL)
	 */
	public WorkspaceReloaderConfiguration(List<String> urls, String apiKey, String user, String password,
			WorkspaceReloadAction workspaceReloadAction) {
		this.urls = urls;
		this.apiKey = apiKey;
		this.user = user;
		this.password = password;
		if (workspaceReloadAction == null)
			this.workspaceReloadAction = ALL;
		else
			this.workspaceReloadAction = workspaceReloadAction;
	}

	/**
	 * @return URL of deegree service to reload, never <code>null</code>
	 */
	public List<String> getUrls() {
		return urls;
	}

	/**
	 * @return apiKey used for authentication instead of user/password, may be *
	 * <code>null</code> if user and password are not <code>null</code>
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @return user used for authentication instead of apiKey, may be * <code>null</code>
	 * if apiKey is not <code>null</code>
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return password used for authentication instead of apiKey, may be *
	 * <code>null</code> if apiKey is not <code>null</code>
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return operation to execute for workspace reload, never <code>null</code>
	 */
	public WorkspaceReloadAction getWorkspaceReloadAction() {
		return workspaceReloadAction;
	}

	/**
	 * @return <code>true</code> ith the apiKey is configured for authentication,
	 * <code>false</code> otherwise
	 */
	public boolean isApiKeyConfigured() {
		return apiKey != null && !apiKey.isEmpty();
	}

}
