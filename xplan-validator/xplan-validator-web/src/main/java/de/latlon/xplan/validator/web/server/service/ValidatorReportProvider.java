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

import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.apache.commons.io.IOUtils.copy;

/**
 * Provides reports after validation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version 2.3
 * @since 2.3
 */
public class ValidatorReportProvider implements ReportProvider {

	private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

	@Autowired
	private ReportWriter reportWriter;

	@Override
	public void writeHtmlReport(HttpServletResponse response, String planUuid, String validationName)
			throws IOException {
		Path planDirectory = planArchiveManager.createReportDirectory(planUuid);
		Path htmlReport = reportWriter.retrieveHtmlReport(validationName, planDirectory);
		try (InputStream fileInputStream = Files.newInputStream(htmlReport)) {
			copy(fileInputStream, response.getOutputStream());
		}
	}

	@Override
	public void writeZipReport(HttpServletResponse response, String planUuid, String validationName,
			List<ArtifactType> artifacts) throws IOException {
		Path planDirectory = planArchiveManager.createReportDirectory(planUuid);
		reportWriter.writeZipWithArtifacts(response.getOutputStream(), validationName, artifacts, planDirectory);
	}

}
