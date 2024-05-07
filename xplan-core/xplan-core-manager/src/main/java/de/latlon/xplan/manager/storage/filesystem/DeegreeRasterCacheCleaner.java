package de.latlon.xplan.manager.storage.filesystem;

import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Required to remove file locks on windows to ensure raster files can be removed. Invokes
 * http://HOST:PORT/xplan-wms/removecache/TILESTOREID
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1.2
 */
public class DeegreeRasterCacheCleaner {

	private final Logger LOG = LoggerFactory.getLogger(DeegreeRasterCacheCleaner.class);

	private WorkspaceReloaderConfiguration configuration;

	/**
	 * @param configuration configuration for {@link WorkspaceReloader}, if
	 * {@link WorkspaceReloaderConfiguration} is invalid, reload is skipped, never
	 * <code>null</code>
	 */
	public DeegreeRasterCacheCleaner(WorkspaceReloaderConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Clears the internal cache of the XPlanWMS
	 * @param tileStoreId the tileStoreId, never <code>null</code>
	 * @return true if clearing the cache was successful, false otherwise.
	 */
	public boolean clearCache(String tileStoreId) {
		boolean isValid = checkIfConfigurationIsValid();
		if (isValid) {
			LOG.info("Workspace reloader configuration is valid and used to clear cache.");
			return executeClearCache(tileStoreId);
		}
		else {
			LOG.info("Workspace reloader configuration is invalid. Reload of workspace is skipped!");
			LOG.info("Workspace reloader configuration is invalid. Cache cannot be cleared.");
			return false;
		}
	}

	private boolean executeClearCache(String tileStoreId) {
		List<String> urls = configuration.getUrls();
		boolean isSuccessfulForAll = true;
		for (String url : urls) {
			boolean clearCacheResult = clearCache(url, tileStoreId);
			isSuccessfulForAll = isSuccessfulForAll && clearCacheResult;
		}
		return isSuccessfulForAll;
	}

	@SuppressFBWarnings(value = "HTTP_PARAMETER_POLLUTION")
	private boolean clearCache(String url, String tileStoreId) {
		try {
			String clearCacheUrl = retrieveDeletePlanwerkWmsUrl(url, tileStoreId);
			LOG.info("Attempting to clear internal raster cache with URL {}", clearCacheUrl);
			HttpGet httpGet = new HttpGet(clearCacheUrl);
			addAuthentication(httpGet);

			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = client.execute(httpGet);
			if (isResponseCodeOk(response)) {
				LOG.info("Clearing raster cache was successful.");
				return true;
			}
			else {
				LOG.info(
						"Error while clearing the internal raster cache. Statuscode: {}, Reason: {}. Check your configuration if workspace reload is configured correctly.",
						response.getStatusLine().getStatusCode(),
						response.getStatusLine().getReasonPhrase() != null
								&& !response.getStatusLine().getReasonPhrase().isEmpty()
										? response.getStatusLine().getReasonPhrase() : "-");
				return false;
			}
		}
		catch (IOException e) {
			LOG.error("Clearing raster cache  failed!", e);
			LOG.trace(e.getMessage(), e);
			return false;
		}
	}

	private String retrieveDeletePlanwerkWmsUrl(String url, String tileStoreId) {
		if (!url.endsWith("/"))
			url = url.concat("/");
		return url.concat("removecache/").concat(tileStoreId);
	}

	private void addAuthentication(HttpRequestBase httpRequest) {
		if (configuration.isApiKeyConfigured()) {
			LOG.debug("apiKey is used for authentication");
			String apiKey = configuration.getApiKey();
			httpRequest.addHeader("X-API-Key", apiKey);
		}
		else {
			LOG.debug("user/password is used for authentication");
			String user = configuration.getUser();
			String password = configuration.getPassword();
			byte[] basicAuth = (user + ":" + password).getBytes();
			String basicAuthEncoded = new String(Base64.encodeBase64(basicAuth));
			httpRequest.addHeader("Authorization", "Basic " + basicAuthEncoded);
		}
	}

	private boolean isResponseCodeOk(HttpResponse response) {
		return response.getStatusLine().getStatusCode() == 200;
	}

	private boolean checkIfConfigurationIsValid() {
		List<String> url = configuration.getUrls();
		String apiKey = configuration.getApiKey();
		String user = configuration.getUser();
		String password = configuration.getPassword();
		return isNotNullOrEmpty(url)
				&& (isNotNullOrEmpty(apiKey) || (isNotNullOrEmpty(user) && isNotNullOrEmpty(password)));
	}

	private boolean isNotNullOrEmpty(String configValue) {
		return configValue != null && !configValue.isEmpty();
	}

	private boolean isNotNullOrEmpty(List<String> configValue) {
		return configValue != null && !configValue.isEmpty();
	}

}
