/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.utils;

import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WmsUrlUtilsTest {

	@Test
	public void testDetermineWmsUrlWithEndpointAndQuestionmark() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/wms?";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is(wmsBaseUrl));
	}

	@Test
	public void testDetermineWmsUrlWithEndpoint() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/wms";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusFestgestellt() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(FESTGESTELLT, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusInAufstellung() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wmspre?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndNullPlanStatus() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(null, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndSlashAndPlanStatusFestgestellt() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(FESTGESTELLT, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndSlashAndPlanStatusInAufstellung() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wmspre?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndSlashAndNullPlanStatus() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(null, mockConfiguration(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusFestgestelltNoWmsEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(FESTGESTELLT, mockConfigurationWithoutWmsEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wmspre?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusInAufstellungNoWmsEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfigurationWithoutWmsEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wmspre?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndNullPlanStatusNoWmsEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(null, mockConfigurationWithoutWmsEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wmspre?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusFestgestelltNoWmsPreEndpointConfigured()
			throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(FESTGESTELLT, mockConfigurationWithoutWmsPreEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusInAufstellungNoWmsPreEndpointConfigured()
			throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfigurationWithoutWmsPreEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndNullPlanStatusNoWmsPreEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(null, mockConfigurationWithoutWmsPreEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services/wms?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusFestgestelltNoEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(FESTGESTELLT, mockConfigurationWithoutEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndPlanStatusInAufstellungNoEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(IN_AUFSTELLUNG, mockConfigurationWithoutEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services?"));
	}

	@Test
	public void testDetermineWmsUrlWithoutEndpointAndNullPlanStatusNoEndpointConfigured() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/";
		String wmsUrl = WmsUrlUtils.determineWmsUrl(null, mockConfigurationWithoutEndpoint(wmsBaseUrl));

		assertThat(wmsUrl, is("http://localhost:8080/xplan-wms/services?"));
	}

	@Test
	public void testCreatePlanwerkWmsUrl() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/wms?";
		String planwerkWmsUrl = WmsUrlUtils.createPlanwerkWmsUrl("PlanName10", mockConfiguration(wmsBaseUrl),
				ARCHIVIERT);

		assertThat(planwerkWmsUrl, is(
				"http://localhost:8080/xplan-wms/services/planwerkwmsarchive/planname/PlanName10?request=GetCapabilities&service=WMS&version=1.3.0"));
	}

	@Test
	public void testCreatePlanwerkWmsUrlReplaceRequired() throws Exception {
		String wmsBaseUrl = "http://localhost:8080/xplan-wms/services/wms?";
		String planwerkWmsUrl = WmsUrlUtils.createPlanwerkWmsUrl("Plan Name 10 mit /", mockConfiguration(wmsBaseUrl),
				FESTGESTELLT);

		assertThat(planwerkWmsUrl, is(
				"http://localhost:8080/xplan-wms/services/planwerkwms/planname/Plan Name 10 mit /?request=GetCapabilities&service=WMS&version=1.3.0"));
	}

	@Test
	public void testCreatePlanwerkWmsUrlReplaceRequiredWithServices() throws Exception {
		String wmsBaseUrl = "http://xplanservices.xplanbox.de/xplan-wms/services/wms?";
		String planwerkWmsUrl = WmsUrlUtils.createPlanwerkWmsUrl("Plan Name 10 mit /", mockConfiguration(wmsBaseUrl),
				IN_AUFSTELLUNG);

		assertThat(planwerkWmsUrl, is(
				"http://xplanservices.xplanbox.de/xplan-wms/services/planwerkwmspre/planname/Plan Name 10 mit /?request=GetCapabilities&service=WMS&version=1.3.0"));
	}

	private MapPreviewConfiguration mockConfigurationWithoutWmsEndpoint(String wmsUrl) {
		return mockConfiguration(wmsUrl, null, "wmspre");
	}

	private MapPreviewConfiguration mockConfigurationWithoutWmsPreEndpoint(String wmsUrl) {
		return mockConfiguration(wmsUrl, "wms", null);
	}

	private MapPreviewConfiguration mockConfigurationWithoutEndpoint(String wmsUrl) {
		return mockConfiguration(wmsUrl, null, null);
	}

	private MapPreviewConfiguration mockConfiguration(String wmsUrl) {
		return mockConfiguration(wmsUrl, "wms", "wmspre");
	}

	private MapPreviewConfiguration mockConfiguration(String wmsUrl, String wmsEndpoint, String wmsPreEndpoint) {
		MapPreviewConfiguration mockedConfiguration = Mockito.mock(MapPreviewConfiguration.class);
		when(mockedConfiguration.getWmsUrl()).thenReturn(wmsUrl);
		when(mockedConfiguration.getWmsEndpoint()).thenReturn(wmsEndpoint);
		when(mockedConfiguration.getWmsPreEndpoint()).thenReturn(wmsPreEndpoint);
		return mockedConfiguration;
	}

}
