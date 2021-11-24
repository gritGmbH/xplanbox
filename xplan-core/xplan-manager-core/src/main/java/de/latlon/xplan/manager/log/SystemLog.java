/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.log;

import org.apache.xalan.Version;
import org.apache.xalan.xslt.EnvironmentCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class SystemLog {

	private static final Logger LOG = LoggerFactory.getLogger(SystemLog.class);

	/**
	 * Logs important stuff about the system, like java version, operating system, xalan
	 * and xerces version.
	 */
	public static void log() {
		LOG.info("");
		LOG.info("--------------------------------------------------------------------------------");
		LOG.info("System info");
		LOG.info("--------------------------------------------------------------------------------");
		LOG.info("");
		LOG.info("- java version       " + System.getProperty("java.version") + " (" + System.getProperty("java.vendor")
				+ ")");
		LOG.info("- operating system   " + System.getProperty("os.name") + " (" + System.getProperty("os.version")
				+ ", " + System.getProperty("os.arch") + ")");
		LOG.info("- system encoding    " + Charset.defaultCharset().displayName());
		LOG.info("- XMLOutputFactory   " + XMLOutputFactory.newInstance().getClass().getCanonicalName());
		LOG.info("- XMLInputFactory    " + XMLInputFactory.newInstance().getClass().getCanonicalName());
		LOG.info("- xalan environment ");
		LOG.info("    - development version: " + Version.getDevelopmentVersionNum());
		LOG.info("    - implementation language: " + Version.getImplementationLanguage());
		LOG.info("    - maintenance version: " + Version.getMaintenanceVersionNum());
		LOG.info("    - major version: " + Version.getMajorVersionNum());
		LOG.info("    - product: " + Version.getProduct());
		LOG.info("    - release version: " + Version.getReleaseVersionNum());
		LOG.info("    - version: " + Version.getVersion());
		StringWriter envCheck = new StringWriter();
		PrintWriter pw = new PrintWriter(envCheck, true);
		(new EnvironmentCheck()).checkEnvironment(pw);
		LOG.info(envCheck.toString());
		LOG.info("- xerces environment ");
		LOG.info("    - version: " + org.apache.xerces.impl.Version.getVersion());
		logTransformer();
		LOG.info("- saxon environment ");
		LOG.info("    - product name: " + net.sf.saxon.Version.getProductName());
		LOG.info("    - product title: " + net.sf.saxon.Version.getProductTitle());
		LOG.info("    - product vendor: " + net.sf.saxon.Version.getProductVendor());
		LOG.info("    - product version: " + net.sf.saxon.Version.getProductVersion());

		LOG.info("--------------------------------------------------------------------------------");
	}

	private static void logTransformer() {
		try {
			LOG.info("- transformer ");
			LOG.info("    - system property (-Djava.xml.transform.TransformerFactory): '"
					+ System.getProperty("java.xml.transform.TransformerFactory") + "'");
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			LOG.info("    - factory " + transformerFactory.getClass());
			Transformer transformer = transformerFactory.newTransformer();
			LOG.info("    - " + transformer.getClass());
		}
		catch (TransformerConfigurationException e) {
			LOG.warn("An error occurred during creating a transformer instance, this may cause some "
					+ "problems during execution of the CLI.");
		}
	}

}
