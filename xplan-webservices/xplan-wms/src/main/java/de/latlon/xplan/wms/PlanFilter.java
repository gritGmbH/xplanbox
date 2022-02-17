/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.wms;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter implementation to modify the incoming request to a GetMap request.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PlanFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(PlanFilter.class);

	public static final String TYPE_PARAM = "type";

	private final Map<String, String> typeToLayers = new HashMap<String, String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("Init PlanFilter...");
		addLayer(filterConfig, "bp_plan", "bpLayers");
		addLayer(filterConfig, "lp_plan", "lpLayers");
		addLayer(filterConfig, "fp_plan", "fpLayers");
		addLayer(filterConfig, "rp_plan", "rpLayers");
		LOG.info("Types: " + typeToLayers);
	}

	private void addLayer(FilterConfig filterConfig, String type, String initParamName) {
		String initParameter = filterConfig.getInitParameter(initParamName);
		if (initParameter != null && !initParameter.isEmpty())
			typeToLayers.put(type, initParameter);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!isPlanRequest(request)) {
			chain.doFilter(request, response);
		}
		else {
			LOG.debug("Retrieved plan request...");
			RequestParameterWrapper wrappedRequest = new RequestParameterWrapper((HttpServletRequest) request);
			modifyPlanRequest(wrappedRequest);
			chain.doFilter(wrappedRequest, response);
		}
	}

	@Override
	public void destroy() {
	}

	private boolean isPlanRequest(ServletRequest request) {
		String type = getParameter(TYPE_PARAM, request);
		return type != null;
	}

	private void modifyPlanRequest(RequestParameterWrapper wrappedRequest) throws ServletException {
		checkManadatoryRequestParameters(wrappedRequest);
		String layers = checkTypeParameterAndRetrieveLayers(wrappedRequest);

		wrappedRequest.addParameter("SERVICE", "WMS");
		wrappedRequest.addParameter("VERSION", "1.1.1");
		wrappedRequest.addParameter("REQUEST", "GetMap");
		wrappedRequest.addParameter("EXCEPTIONS", "application/vnd.ogc.se_inimage");
		wrappedRequest.addParameter("FORMAT", "image/png");
		wrappedRequest.addParameter("TRANSPARENT", "true");
		wrappedRequest.addParameter("STYLES", "");
		wrappedRequest.addParameter("LAYERS", layers);
	}

	private String checkTypeParameterAndRetrieveLayers(RequestParameterWrapper wrappedRequest) throws ServletException {
		String type = getParameter(TYPE_PARAM, wrappedRequest);
		String layers = typeToLayers.get(type.toLowerCase());
		if (layers == null || layers.isEmpty())
			throw new ServletException("Invalid type parameter value '" + type + "'");
		return layers;
	}

	private void checkManadatoryRequestParameters(HttpServletRequest request) throws ServletException {
		checkRequestParameter(request, TYPE_PARAM);
		checkRequestParameter(request, "srs");
		checkRequestParameter(request, "bbox");
		checkRequestParameter(request, "width");
		checkRequestParameter(request, "height");
	}

	private void checkRequestParameter(HttpServletRequest request, String parameterName) throws ServletException {
		String parameter = getParameter(parameterName, request);
		if (parameter == null || parameter.isEmpty())
			throw new ServletException("Missing parameter '" + parameterName + "'");

	}

	private String getParameter(String key, ServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			if (parameterName.equalsIgnoreCase(key))
				return request.getParameter(parameterName);
		}
		return null;
	}

}
