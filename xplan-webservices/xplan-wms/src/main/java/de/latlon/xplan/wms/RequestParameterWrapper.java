/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.wms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Custom request wrapper that allows to add additional key-value-pairs.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {

	private final Map<String, String[]> additionalParameters = new TreeMap<String, String[]>();

	private String pathInfo;

	/**
	 * @param request to wrap, never <code>null</code>
	 */
	public RequestParameterWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * @param request to wrap, never <code>null</code>
	 * @param additionalParameters map with addional parameters, never <code>null</code>
	 */
	public RequestParameterWrapper(HttpServletRequest request, Map<String, String[]> additionalParameters) {
		super(request);
		this.additionalParameters.putAll(additionalParameters);
	}

	@Override
	public String getQueryString() {
		String originalQueryString = super.getQueryString();
		return addAdditionalKeyValuePairsAsString(originalQueryString);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> parameters = new TreeMap<String, String[]>();
		parameters.putAll(super.getParameterMap());
		parameters.putAll(additionalParameters);
		return Collections.unmodifiableMap(parameters);
	}

	@Override
	public String getParameter(final String name) {
		String[] strings = getParameterMap().get(name);
		if (strings != null) {
			return strings[0];
		}
		return super.getParameter(name);
	}

	@Override
	public String[] getParameterValues(final String name) {
		return getParameterMap().get(name);
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return Collections.enumeration(getParameterMap().keySet());
	}

	@Override
	public String getPathInfo() {
		if (pathInfo != null)
			return pathInfo;
		return super.getPathInfo();
	}

	/**
	 * Appends a new additional key value pair. If an entry with the same key already
	 * exists the existing key is overwritten.
	 * @param key of the entry, if <code>null</code> the entry is not added
	 * @param value of the new entry
	 */
	public void addParameter(String key, String value) {
		if (key != null)
			additionalParameters.put(key, new String[] { value });
	}

	private String addAdditionalKeyValuePairsAsString(String originalQueryString) {
		StringBuilder queryString = new StringBuilder();
		boolean appendAmp = appendBeginning(originalQueryString, queryString);
		for (Map.Entry<String, String[]> entry : additionalParameters.entrySet()) {
			String key = entry.getKey();
			String value = commaSeparatedValue(entry);
			if (appendAmp)
				queryString.append("&");
			queryString.append(key).append("=").append(value);
			appendAmp = true;
		}
		return queryString.toString();
	}

	private boolean appendBeginning(String originalQueryString, StringBuilder queryString) {
		if (originalQueryString == null || originalQueryString.isEmpty()) {
			queryString.append('?');
			return false;
		}
		else {
			queryString.append(originalQueryString);
			return true;
		}
	}

	private String commaSeparatedValue(Map.Entry<String, String[]> entry) {
		StringBuilder valuesAsString = new StringBuilder();
		String[] values = entry.getValue();
		boolean appendComma = false;
		for (String value : values) {
			if (appendComma)
				valuesAsString.append(',');
			valuesAsString.append(value);
			appendComma = true;
		}
		return valuesAsString.toString();
	}

	public void setPathInfo(String pathInfo) {
		this.pathInfo = pathInfo;
	}

}
