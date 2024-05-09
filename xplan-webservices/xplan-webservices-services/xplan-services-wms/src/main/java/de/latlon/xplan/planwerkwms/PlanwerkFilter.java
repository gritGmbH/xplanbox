/*-
 * #%L
 * xplan-services-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.OGCFrontController;
import de.latlon.xplan.planwerkwms.jaxb.Planwerk;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkFilter implements Filter {

	private static final Logger LOG = LoggerFactory.getLogger(PlanwerkFilter.class);

	private PlanwerkReader planwerkReader;

	private String wmsId;

	private String planStatus;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String jdbcConnId = filterConfig.getInitParameter("JdbcConnId");
		wmsId = filterConfig.getInitParameter("WmsId");
		planStatus = filterConfig.getInitParameter("PlanstatusDB");
		planwerkReader = new PlanwerkReader(jdbcConnId);
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		tryToAddIfUnavailable(servletRequest);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
	}

	private void tryToAddIfUnavailable(ServletRequest servletRequest) {
		try {
			if (servletRequest instanceof HttpServletRequest) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
				String pathInfo = httpServletRequest.getPathInfo();
				if (pathInfo != null) {
					String serviceId = pathInfo.substring(1);
					Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
					OWS resource = workspace.getResource(OWSProvider.class, serviceId);
					if (resource == null) {
						tryToAdd(workspace, serviceId);
					}
				}
			}
		}
		catch (Exception e) {
			LOG.error("Requested plan could not be added", e);
		}
	}

	private void tryToAdd(Workspace workspace, String serviceId) throws IOException, JAXBException {
		String planName = serviceId.substring(serviceId.lastIndexOf("/") + 1, serviceId.length());
		Plan plan = planwerkReader.retrieveAvailablePlanwerke(workspace, planName, planStatus);
		if (plan == null) {
			LOG.info("Plan with name " + planName + " is not available from database");
			return;
		}

		byte[] planConfig = writePlanwerkConfig(planName, plan, wmsId);
		DefaultResourceIdentifier<OWS> identifier = new DefaultResourceIdentifier<>(OWSProvider.class, serviceId);
		WmsMetadata wmsMetadata = (WmsMetadata) workspace.getResourceMetadata(OWSProvider.class, wmsId);
		PlanwerkResourceLocation resourceLocation = new PlanwerkResourceLocation(planConfig, identifier, wmsMetadata);

		workspace.add(resourceLocation);
		workspace.init(identifier, null);
	}

	private byte[] writePlanwerkConfig(String planname, Plan plan, String wmsId) throws IOException, JAXBException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Planwerk planwerk = new Planwerk();
		planwerk.setName(planname);
		planwerk.setEnvelope(plan.getBbox());
		planwerk.setPlanwerkWms(wmsId);
		for (int managerId : plan.getManagerIds()) {
			planwerk.getManagerId().add(managerId);
		}
		for (String resourceidentifiers : plan.getResourceidentifiers()) {
			planwerk.getResourceIdentifier().add(resourceidentifiers);
		}
		for (String datametadataurls : plan.getDataMetadataUrls()) {
			planwerk.getDataMetadataUrl().add(datametadataurls);
		}
		for (String servicemetadataurls : plan.getServiceMetadataUrls()) {
			planwerk.getServiceMetadataUrl().add(servicemetadataurls);
		}
		for (String wmstitle : plan.getWmsTitles()) {
			planwerk.getWmsTitle().add(wmstitle);
		}

		JAXBContext jaxbContext = JAXBContext.newInstance(Planwerk.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(planwerk, bos);

		bos.close();
		return bos.toByteArray();
	}

}
