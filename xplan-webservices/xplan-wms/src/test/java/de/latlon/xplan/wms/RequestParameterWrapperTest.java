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

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RequestParameterWrapperTest {

	private final HttpServletRequest request = mockHttpServletRequest();

	private final HttpServletRequest requestWithoutParams = mockHttpServletRequestWithoutParams();

	private final Map<String, String[]> additionalKeyValuePair = createAdditionalKeyValuePair();

	private final Map<String, String[]> additionalKeyValuePairWithoutEntries = new HashMap<String, String[]>();

	private final Map<String, String[]> additionalKeyValuePairWithTwoValues = createAdditionalKeyValuePairsWithTwoValues();

	private final Map<String, String[]> additionalKeyValuePairsWithThreeEntries = createAdditionalKeyValuePairsWithThreeEntries();

	@Test
	public void testGetQueryString() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		String queryString = wrapper.getQueryString();

		String exptectedQueryString = "?existingKey=existingValue&additionalKey=additionalValue";
		assertThat(queryString, is(exptectedQueryString));
	}

	@Test
	public void testGetQueryStringWithoutAdditional() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePairWithoutEntries);
		String queryString = wrapper.getQueryString();

		String exptectedQueryString = "?existingKey=existingValue";
		assertThat(queryString, is(exptectedQueryString));
	}

	@Test
	public void testGetQueryStringWithThreeAdditionalEntries() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePairsWithThreeEntries);
		String queryString = wrapper.getQueryString();

		String exptectedQueryString = "?existingKey=existingValue&additionalKey1=additionalValue1&additionalKey2=additionalValue2&additionalKey3=additionalValue3";
		assertThat(queryString, is(exptectedQueryString));
	}

	@Test
	public void testGetQueryStringWithoutOriginalParamsAndThreeAdditional() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(requestWithoutParams,
				additionalKeyValuePairsWithThreeEntries);
		String queryString = wrapper.getQueryString();

		String exptectedQueryString = "?additionalKey1=additionalValue1&additionalKey2=additionalValue2&additionalKey3=additionalValue3";
		assertThat(queryString, is(exptectedQueryString));
	}

	@Test
	public void testGetQueryStringWithTwoValues() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePairWithTwoValues);
		String queryString = wrapper.getQueryString();

		String exptectedQueryString = "?existingKey=existingValue&additionalKey=additionalValue1,additionalValue2";
		assertThat(queryString, is(exptectedQueryString));
	}

	@Test
	public void testGetParameterMap() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		Map<String, String[]> parameterMap = wrapper.getParameterMap();

		String expectedExistingKey = "existingKey";
		String expectedAdditionalKey = "additionalKey";
		String[] expectedExistingValue = new String[] { "existingValue" };
		String[] expectedAdditionalValue = new String[] { "additionalValue" };
		Set<String> actualKeySet = parameterMap.keySet();
		String[] actualExistingValue = parameterMap.get(expectedExistingKey);
		String[] actualAdditionalValue = parameterMap.get(expectedAdditionalKey);

		assertThat(parameterMap.size(), is(2));
		assertThat(actualKeySet, hasItem(expectedExistingKey));
		assertThat(actualKeySet, hasItem(expectedAdditionalKey));
		assertThat(actualExistingValue, is(expectedExistingValue));
		assertThat(actualAdditionalValue, is(expectedAdditionalValue));
	}

	@Test
	public void testGetParameterMapWithThreeAdditionalEntries() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePairsWithThreeEntries);
		Map<String, String[]> parameterMap = wrapper.getParameterMap();

		String expectedExistingKey = "existingKey";
		String expectedAdditionalKey1 = "additionalKey1";
		String expectedAdditionalKey2 = "additionalKey2";
		String expectedAdditionalKey3 = "additionalKey3";
		String[] expectedExistingValue = new String[] { "existingValue" };
		String[] expectedAdditionalValue1 = new String[] { "additionalValue1" };
		String[] expectedAdditionalValue2 = new String[] { "additionalValue2" };
		String[] expectedAdditionalValue3 = new String[] { "additionalValue3" };
		Set<String> actualKeySet = parameterMap.keySet();
		String[] actualExistingValue = parameterMap.get(expectedExistingKey);
		String[] actualAdditionalValue1 = parameterMap.get(expectedAdditionalKey1);
		String[] actualAdditionalValue2 = parameterMap.get(expectedAdditionalKey2);
		String[] actualAdditionalValue3 = parameterMap.get(expectedAdditionalKey3);

		assertThat(parameterMap.size(), is(4));

		assertThat(actualKeySet, hasItem(expectedExistingKey));
		assertThat(actualKeySet, hasItem(expectedAdditionalKey1));
		assertThat(actualKeySet, hasItem(expectedAdditionalKey2));
		assertThat(actualKeySet, hasItem(expectedAdditionalKey3));
		assertThat(actualExistingValue, is(expectedExistingValue));
		assertThat(actualAdditionalValue1, is(expectedAdditionalValue1));
		assertThat(actualAdditionalValue2, is(expectedAdditionalValue2));
		assertThat(actualAdditionalValue3, is(expectedAdditionalValue3));
	}

	@Test
	public void testGetParameter() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		String existingParameter = wrapper.getParameter("existingKey");
		String additionalParameter = wrapper.getParameter("additionalKey");

		assertThat(existingParameter, is("existingValue"));
		assertThat(additionalParameter, is("additionalValue"));
	}

	@Test
	public void testGetParameterWithNotExistingParameterShouldReturnNull() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		String notExistingParameter = wrapper.getParameter("notExisting");

		assertThat(notExistingParameter, is(nullValue()));
	}

	@Test
	public void testGetParameterValues() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		String[] existingParameterValues = wrapper.getParameterValues("existingKey");
		String[] additionalParameterValues = wrapper.getParameterValues("additionalKey");

		String[] expectedExistingParameterValues = new String[] { "existingValue" };
		String[] expectedAdditionalParameterValues = new String[] { "additionalValue" };

		assertThat(existingParameterValues, is(expectedExistingParameterValues));
		assertThat(additionalParameterValues, is(expectedAdditionalParameterValues));
	}

	@Test
	public void testGetParameterValuesWithNotExistingParameterShouldReturnNull() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		String[] notExistingParameterValues = wrapper.getParameterValues("netExisting");

		assertThat(notExistingParameterValues, is(nullValue()));
	}

	@Test
	public void testGetParameterNames() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePair);
		Enumeration<String> parameterNames = wrapper.getParameterNames();
		List<String> parameterNamesList = new ArrayList<String>();
		while (parameterNames.hasMoreElements()) {
			parameterNamesList.add(parameterNames.nextElement());
		}

		assertThat(parameterNamesList.size(), is(2));
		assertThat(parameterNamesList, hasItem("existingKey"));
		assertThat(parameterNamesList, hasItem("additionalKey"));
	}

	@Test
	public void testGetParameterNamesWithThreeAdditionalEntries() throws Exception {
		RequestParameterWrapper wrapper = new RequestParameterWrapper(request, additionalKeyValuePairsWithThreeEntries);
		Enumeration<String> parameterNames = wrapper.getParameterNames();
		List<String> parameterNamesList = new ArrayList<String>();
		while (parameterNames.hasMoreElements()) {
			parameterNamesList.add(parameterNames.nextElement());
		}

		assertThat(parameterNamesList.size(), is(4));
		assertThat(parameterNamesList, hasItem("existingKey"));
		assertThat(parameterNamesList, hasItem("additionalKey1"));
		assertThat(parameterNamesList, hasItem("additionalKey2"));
		assertThat(parameterNamesList, hasItem("additionalKey3"));
	}

	private Map<String, String[]> createAdditionalKeyValuePair() {
		Map<String, String[]> additionalKeyValuePair = new HashMap<String, String[]>();
		additionalKeyValuePair.put("additionalKey", new String[] { "additionalValue" });
		return additionalKeyValuePair;
	}

	private Map<String, String[]> createAdditionalKeyValuePairsWithThreeEntries() {
		Map<String, String[]> additionalKeyValuePairs = new HashMap<String, String[]>();
		additionalKeyValuePairs.put("additionalKey1", new String[] { "additionalValue1" });
		additionalKeyValuePairs.put("additionalKey2", new String[] { "additionalValue2" });
		additionalKeyValuePairs.put("additionalKey3", new String[] { "additionalValue3" });
		return additionalKeyValuePairs;
	}

	private Map<String, String[]> createAdditionalKeyValuePairsWithTwoValues() {
		Map<String, String[]> additionalKeyValuePair = new HashMap<String, String[]>();
		additionalKeyValuePair.put("additionalKey", new String[] { "additionalValue1", "additionalValue2" });
		return additionalKeyValuePair;
	}

	private Map<String, String[]> createExistingParameterMap() {
		Map<String, String[]> existingParameterMap = new HashMap<String, String[]>();
		existingParameterMap.put("existingKey", new String[] { "existingValue" });
		return existingParameterMap;
	}

	private HttpServletRequest mockHttpServletRequest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		doReturn("?existingKey=existingValue").when(request).getQueryString();
		Map<String, String[]> existingParameterMap = createExistingParameterMap();
		doReturn(existingParameterMap).when(request).getParameterMap();
		return request;
	}

	private HttpServletRequest mockHttpServletRequestWithoutParams() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		doReturn(null).when(request).getQueryString();
		Map<String, String[]> existingParameterMap = Collections.emptyMap();
		doReturn(existingParameterMap).when(request).getParameterMap();
		return request;
	}

}
