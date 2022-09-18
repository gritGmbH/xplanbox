/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Reloads a workspace via HTTP.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloader {

	private final Logger LOG = LoggerFactory.getLogger(WorkspaceReloader.class);

	private WorkspaceReloaderConfiguration configuration;

	/**
	 * @param configuration configuration for {@link WorkspaceReloader}, if
	 * {@link WorkspaceReloaderConfiguration} is invalid, reload is skipped, never
	 * <code>null</code>
	 */
	public WorkspaceReloader(WorkspaceReloaderConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Reloads a workspace.
	 * @param planId the id of the plan forcing the workspace reload, never
	 * <code>null</code>
	 * @return true if all workspace reloads were successful, false otherwise.
	 */
	public boolean reloadWorkspace(int planId) {
		boolean isValid = checkConfiguration();
		if (isValid) {
			LOG.info("Workspace reloader configuration is valid.");
			return executeReload(planId);
		}
		else {
			LOG.info("Workspace reloader configuration is invalid. Reload of workspace is skipped!");
			return false;
		}
	}

	private boolean executeReload(int planId) {
		switch (configuration.getWorkspaceReloadAction()) {
			case RELOAD:
				return reload();
			case PLANWERKWMS:
				return deletePlanwerkWms(planId);
			default:
				boolean reload = reload();
				return deletePlanwerkWms(planId) && reload;
		}
	}

	private boolean deletePlanwerkWms(int planId) {
		List<String> urls = configuration.getUrls();
		boolean isSuccessfulForAll = true;
		for (String url : urls) {
			boolean reloadResult = deletePlanwerkWms(url, planId);
			isSuccessfulForAll = isSuccessfulForAll && reloadResult;
		}
		return isSuccessfulForAll;
	}

	private boolean deletePlanwerkWms(String url, int planId) {
		try {
			String reloadUrl = retrieveDeletePlanwerkWmsUrl(url, planId);
			LOG.info("Attempting to delete XPlanWerkWMS configuration with URL {}", reloadUrl);
			HttpDelete httpDelete = new HttpDelete(reloadUrl);
			addBasicAuth(configuration.getUser(), configuration.getPassword(), httpDelete);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpDelete);
			if (isResponseCodeOk(response)) {
				LOG.info("Delete completed successfully.");
				return true;
			}
			else {
				LOG.info("Error while deleting XPlanWerkWMS configuration: {}",
						response.getStatusLine().getReasonPhrase());
				return false;
			}
		}
		catch (IOException e) {
			LOG.error("Delete XPlanWerkWMS configuration failed!", e);
			return false;
		}
	}

	private boolean reload() {
		List<String> urls = configuration.getUrls();
		boolean isReloadSuccessfulForAllWorkspaces = true;
		for (String url : urls) {
			boolean reloadResult = reloadWorkspace(url);
			isReloadSuccessfulForAllWorkspaces = isReloadSuccessfulForAllWorkspaces && reloadResult;
		}
		return isReloadSuccessfulForAllWorkspaces;
	}

	private boolean reloadWorkspace(String url) {
		try {
			String reloadUrl = retrieveWorkspaceReloadUrl(url);
			LOG.info("Attempting to reload workspace with URL {}", reloadUrl);
			HttpGet httpGet = new HttpGet(reloadUrl);
			addBasicAuth(configuration.getUser(), configuration.getPassword(), httpGet);
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = client.execute(httpGet);
			if (isResponseCodeOk(response)) {
				LOG.info("Reload completed successfully.");
				return true;
			}
			else {
				LOG.info("Error while reloading workspace: {}", response.getStatusLine().getReasonPhrase());
				return false;
			}
		}
		catch (IOException e) {
			LOG.info("Reload of workspace failed: {}", e.getMessage());
			LOG.trace("Reload of workspace failed!", e);
			return false;
		}
	}

	private String retrieveWorkspaceReloadUrl(String url) {
		if (!url.endsWith("/"))
			url = url.concat("/");
		return url.concat("config/update");
	}

	private String retrieveDeletePlanwerkWmsUrl(String url, int planId) {
		if (!url.endsWith("/"))
			url = url.concat("/");
		return url.concat("planwerkwmsapi/").concat(Integer.toString(planId));
	}

	private static void addBasicAuth(String user, String password, HttpRequestBase httpRequest) {
		byte[] basicAuth = (user + ":" + password).getBytes();
		String basicAuthEncoded = new String(Base64.encodeBase64(basicAuth));
		httpRequest.addHeader("Authorization", "Basic " + basicAuthEncoded);
	}

	private boolean isResponseCodeOk(HttpResponse response) {
		return response.getStatusLine().getStatusCode() == 200;
	}

	private boolean checkConfiguration() {
		List<String> url = configuration.getUrls();
		String user = configuration.getUser();
		String password = configuration.getPassword();
		return isNotNullOrEmpty(url) && isNotNullOrEmpty(user) && isNotNullOrEmpty(password);
	}

	private boolean isNotNullOrEmpty(String configValue) {
		return configValue != null && !configValue.isEmpty();
	}

	private boolean isNotNullOrEmpty(List<String> configValue) {
		return configValue != null && !configValue.isEmpty();
	}

}
