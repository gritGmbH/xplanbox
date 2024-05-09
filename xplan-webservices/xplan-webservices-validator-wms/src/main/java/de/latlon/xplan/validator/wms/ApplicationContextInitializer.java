/*-
 * #%L
 * xplan-webservices-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.job.validator.config.JobContext;
import de.latlon.xplan.job.validator.config.MemoryJobContext;
import de.latlon.xplan.validator.wms.config.JobWebContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Initializer of Spring ApplicationContext. Workaround to initialize Spring
 * ApplicationContext after the deegree </code>OGCFrontController</code>. TODO: This class
 * should be refactored to <code>AbstractContextLoaderInitializer</code> as soon as
 * deegree Workspace can be provided as a Spring Bean.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class ApplicationContextInitializer implements Servlet {

	private AnnotationConfigApplicationContext rootContext;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		rootContext = new AnnotationConfigApplicationContext(JobContext.class, MemoryJobContext.class,
				JobWebContext.class);
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest servletRequest, ServletResponse servletResponse)
			throws ServletException, IOException {
		// nothing to do, noop
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
		rootContext.close();
	}

}
