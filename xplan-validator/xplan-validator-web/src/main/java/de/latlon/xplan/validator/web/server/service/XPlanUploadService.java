/*-
 * #%L
 * xplan-validator-web - Modul zur Gruppierung aller Webapps
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
package de.latlon.xplan.validator.web.server.service;

import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import de.latlon.xplan.commons.util.UnsupportedContentTypeException;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static java.lang.String.format;
import static java.util.UUID.randomUUID;
import static org.apache.commons.fileupload.FileUploadBase.isMultipartContent;

/**
 * Stores an uploaded zip file into a tmp directory.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version 2.3
 * @since 2.3
 */
@SuppressWarnings("serial")
public class XPlanUploadService extends XsrfProtectedServiceServlet {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanUploadService.class);

	private static final ResourceBundle bundle = ResourceBundle
		.getBundle("de.latlon.xplan.validator.web.client.XPlanValidatorWebMessages");

	private static final int FIRST = 0;

	private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOG.trace("Handling request {} in {}", request.getRequestURI(), this.getClass());
		boolean isMultiPart = isMultipartContent(new ServletRequestContext(request));
		if (!isMultiPart)
			super.service(request, response);
		else {
			handleMultiPart(request, response);

		}
	}

	private void handleMultiPart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		try {
			FileItem uploadedFileItem = retrieveFirstUploadedFile(request, upload);
			if (checkUploadConditions(uploadedFileItem)) {
				String fileName = uploadedFileItem.getName();
				XPlan plan = createPlan(uploadedFileItem, fileName);

				planArchiveManager.writePlanToSession(request.getSession(true), plan);
				planArchiveManager.writeXPlanArchiveToFileSystem(uploadedFileItem, plan);
				populateResponse(response, uploadedFileItem, fileName);
				LOG.debug("Plan {} was successfully loaded.", fileName);
			}
			else {
				super.service(request, response);
			}
		}
		catch (IOException e) {
			throw e;
		}
		catch (UnsupportedContentTypeException e) {
			throw new IOException(e.getMessage());
		}
		catch (Exception e) {
			LOG.error("An error occurred during uploading a plan!", e);
		}
	}

	private FileItem retrieveFirstUploadedFile(HttpServletRequest request, ServletFileUpload upload)
			throws FileUploadException {
		return upload.parseRequest(request).get(FIRST);
	}

	private XPlan createPlan(FileItem uploadedFileItem, String fileName) {
		return new XPlan(fileName, randomUUID().toString(), uploadedFileItem.getContentType());
	}

	private void populateResponse(HttpServletResponse response, FileItem uploadedFileItem, String fileName)
			throws IOException {
		String message = format(bundle.getString("loadedPlan"), fileName, uploadedFileItem.getSize());

		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().println(message);
		response.flushBuffer();
	}

	private boolean checkUploadConditions(FileItem uploadedFileItem) {
		return uploadedFileItem != null && uploadedFileItem.getFieldName() != null
				&& "uploadPlanItem".equalsIgnoreCase(uploadedFileItem.getFieldName());
	}

}
