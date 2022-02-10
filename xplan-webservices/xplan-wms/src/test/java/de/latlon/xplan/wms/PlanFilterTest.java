/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PlanFilterTest {

	private static final String HEIGHT = "height";

	private static final String WIDTH = "width";

	private static final String BBOX = "bbox";

	private static final String SRS = "srs";

	private static final String BP_LAYERS = "b1_layer,b2_layer";

	private static final String LP_LAYERS = "l_layer";

	private static PlanFilter filter;

	@BeforeClass
	public static void initFilter() throws ServletException {
		filter = new PlanFilter();
		filter.init(mockFilterConfig());
	}

	@Test
	public void testDoFilter() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequest("bp_plan"), response, chain);

		ArgumentCaptor<ServletRequest> requestArgumentCaptor = ArgumentCaptor.forClass(ServletRequest.class);

		verify(chain).doFilter(requestArgumentCaptor.capture(), eq(response));

		ServletRequest request = requestArgumentCaptor.getValue();

		assertThat(request.getParameter(SRS), is("epsg:4326"));
		assertThat(request.getParameter(BBOX), is("18,53,19,54"));
		assertThat(request.getParameter(WIDTH), is("27"));
		assertThat(request.getParameter(HEIGHT), is("72"));

		assertThat(request.getParameter("SERVICE"), is("WMS"));
		assertThat(request.getParameter("VERSION"), is("1.1.1"));
		assertThat(request.getParameter("REQUEST"), is("GetMap"));
		assertThat(request.getParameter("TRANSPARENT"), is("true"));

		assertThat(request.getParameter("LAYERS"), is(BP_LAYERS));
	}

	@Test(expected = ServletException.class)
	public void testDoFilterUnknownType() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequest("rplan"), response, chain);
	}

	@Test
	public void testDoFilterMissingType() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		HttpServletRequest originalRequest = mockWmsRequest();
		filter.doFilter(originalRequest, response, chain);

		ArgumentCaptor<HttpServletRequest> requestArgumentCaptor = ArgumentCaptor.forClass(HttpServletRequest.class);

		verify(chain).doFilter(requestArgumentCaptor.capture(), eq(response));

		HttpServletRequest request = requestArgumentCaptor.getValue();
		assertThat(request, is(originalRequest));
	}

	@Test(expected = ServletException.class)
	public void testDoFilterMissingSrs() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequestMissingSrs(), response, chain);
	}

	@Test(expected = ServletException.class)
	public void testDoFilterMissingBbox() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequestMissingBbox(), response, chain);
	}

	@Test(expected = ServletException.class)
	public void testDoFilterMissingWidth() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequestMissingWidth(), response, chain);
	}

	@Test(expected = ServletException.class)
	public void testDoFilterMissingHeight() throws Exception {
		FilterChain chain = mockChain();
		ServletResponse response = mockResponse();
		filter.doFilter(mockRequestMissingHeight(), response, chain);
	}

	private static FilterConfig mockFilterConfig() {
		FilterConfig mockedFilterConfig = mock(FilterConfig.class);
		when(mockedFilterConfig.getInitParameter("bpLayers")).thenReturn(BP_LAYERS);
		when(mockedFilterConfig.getInitParameter("fpLayers")).thenReturn(LP_LAYERS);
		when(mockedFilterConfig.getInitParameter("lpLayers")).thenReturn(null);
		when(mockedFilterConfig.getInitParameter("rpLayers")).thenReturn("");
		return mockedFilterConfig;
	}

	private HttpServletRequest mockRequest(String type) {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, PlanFilter.TYPE_PARAM, type);
		addParameter(mockedRequest, map, BBOX, "18,53,19,54");
		addParameter(mockedRequest, map, SRS, "epsg:4326");
		addParameter(mockedRequest, map, WIDTH, "27");
		addParameter(mockedRequest, map, HEIGHT, "72");
		when(mockedRequest.getParameterNames())
				.thenReturn(asEnumeration(PlanFilter.TYPE_PARAM, SRS, BBOX, WIDTH, HEIGHT));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private HttpServletRequest mockWmsRequest() {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, "request", "GetCapabilities");
		addParameter(mockedRequest, map, "service", "WMS");
		when(mockedRequest.getParameterNames()).thenReturn(asEnumeration("request", "service"));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private HttpServletRequest mockRequestMissingSrs() {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, PlanFilter.TYPE_PARAM, "bplan");
		addParameter(mockedRequest, map, BBOX, "18,53,19,54");
		addParameter(mockedRequest, map, WIDTH, "27");
		addParameter(mockedRequest, map, HEIGHT, "72");
		when(mockedRequest.getParameterNames()).thenReturn(asEnumeration(PlanFilter.TYPE_PARAM, BBOX, WIDTH, HEIGHT));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private HttpServletRequest mockRequestMissingBbox() {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, PlanFilter.TYPE_PARAM, "bplan");
		addParameter(mockedRequest, map, SRS, "epsg:4326");
		addParameter(mockedRequest, map, WIDTH, "27");
		addParameter(mockedRequest, map, HEIGHT, "72");
		when(mockedRequest.getParameterNames()).thenReturn(asEnumeration(PlanFilter.TYPE_PARAM, SRS, WIDTH, HEIGHT));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private HttpServletRequest mockRequestMissingWidth() {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, PlanFilter.TYPE_PARAM, "bplan");
		addParameter(mockedRequest, map, BBOX, "18,53,19,54");
		addParameter(mockedRequest, map, SRS, "epsg:4326");
		addParameter(mockedRequest, map, HEIGHT, "72");
		when(mockedRequest.getParameterNames()).thenReturn(asEnumeration(PlanFilter.TYPE_PARAM, SRS, BBOX, HEIGHT));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private HttpServletRequest mockRequestMissingHeight() {
		HttpServletRequest mockedRequest = mock(HttpServletRequest.class);
		Map<String, String[]> map = new HashMap<String, String[]>();
		addParameter(mockedRequest, map, PlanFilter.TYPE_PARAM, "bplan");
		addParameter(mockedRequest, map, BBOX, "18,53,19,54");
		addParameter(mockedRequest, map, SRS, "epsg:4326");
		addParameter(mockedRequest, map, WIDTH, "27");
		when(mockedRequest.getParameterNames()).thenReturn(asEnumeration(PlanFilter.TYPE_PARAM, SRS, BBOX, WIDTH));
		when(mockedRequest.getParameterMap()).thenReturn(map);
		return mockedRequest;
	}

	private void addParameter(HttpServletRequest mockedRequest, Map<String, String[]> map, String parameterName,
			String parameterValue) {
		when(mockedRequest.getParameter(parameterName)).thenReturn(parameterValue);
		map.put(parameterName, new String[] { parameterValue });
	}

	private ServletResponse mockResponse() {
		return mock(ServletResponse.class);
	}

	private FilterChain mockChain() {
		return mock(FilterChain.class);
	}

	private Enumeration<String> asEnumeration(String... value) {
		List<String> enumerationEntries = new ArrayList<String>();
		for (String entry : value) {
			enumerationEntries.add(entry);
		}
		return Collections.enumeration(enumerationEntries);
	}

}
