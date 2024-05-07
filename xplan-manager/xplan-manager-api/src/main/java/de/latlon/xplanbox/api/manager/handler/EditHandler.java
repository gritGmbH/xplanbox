/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.UnsupportedContentTypeException;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanId;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanIdSyntax;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanToEdit;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.util.ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class EditHandler {

	private static final Logger LOG = getLogger(EditHandler.class);

	@Autowired
	protected XPlanManager manager;

	public XPlan findPlanById(String planId) throws Exception {
		LOG.info("Find plan by Id '{}'", StringUtils.normalizeSpace(planId));
		try {
			int id = Integer.parseInt(planId);
			return findPlanById(id);
		}
		catch (NumberFormatException e) {
			throw new InvalidPlanIdSyntax(planId);
		}
	}

	/**
	 * Stores the passed content as tmp file with the passed filename.
	 * @param content may be <code>null</code>
	 * @param fileMetadata name of the file, never <code>null</code> if content is
	 * <code>not null</code>
	 * @return the file, <code>null</code> if content is <code>null</code>
	 * @throws IOException
	 */
	public File storeAsFile(InputStream content, FormDataContentDisposition fileMetadata)
			throws IOException, UnsupportedContentTypeException {
		if (content == null)
			return null;
		java.nio.file.Path tmpDir = Files.createTempDirectory("postDokument");
		java.nio.file.Path targetFile = tmpDir.resolve(fileMetadata.getFileName());
		Files.copy(content, targetFile);
		checkContentTypeOfFileOfXPlanArchive(targetFile);
		content.close();
		return targetFile.toFile();
	}

	private XPlan findPlanById(int id) throws Exception {
		XPlan xPlanById = manager.getXPlanById(id);
		if (xPlanById == null) {
			throw new InvalidPlanId(id);
		}
		checkIfPlanIsSupported(xPlanById);
		return xPlanById;
	}

	private void checkIfPlanIsSupported(XPlan xPlanById) throws InvalidPlanToEdit {
		String version = xPlanById.getVersion();
		XPlanVersion xPlanVersion = XPlanVersion.valueOf(version);
		String type = xPlanById.getType();
		XPlanType xPlanType = XPlanType.valueOf(type);
		switch (xPlanVersion) {
			case XPLAN_40:
				throw new InvalidPlanToEdit(String.format(
						"Plan with ID %s can not be edited, because the version (%s) is not supported. Supported versions: 4.1 and higher",
						xPlanById.getId(), xPlanVersion));
			case XPLAN_41:
				if (LP_Plan.equals(xPlanType)) {
					throw new InvalidPlanToEdit(String.format(
							"Plan with ID %s can not be edited, because the version (%s) is not supported. Supported versions for %s: 6.0 and higher",
							xPlanById.getId(), xPlanVersion, xPlanType));
				}
				else if (!BP_Plan.equals(xPlanType)) {
					throw new InvalidPlanToEdit(String.format(
							"Plan with ID %s can not be edited, because the version (%s) is not supported. Supported versions for %s: 5.0 and higher",
							xPlanById.getId(), xPlanVersion, xPlanType));
				}
				return;
			case XPLAN_50:
			case XPLAN_51:
			case XPLAN_52:
			case XPLAN_53:
				if (LP_Plan.equals(xPlanType)) {
					throw new InvalidPlanToEdit(String.format(
							"Plan with ID %s can not be edited, because the version (%s) is not supported. Supported versions for %s: 6.0 and higher",
							xPlanById.getId(), xPlanVersion, xPlanType));
				}
				return;
		}
	}

}
