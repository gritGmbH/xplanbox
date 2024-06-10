/*-
 * #%L
 * xplan-validator-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import de.latlon.xplanbox.api.commons.exception.InvalidXPlanGmlOrArchive;
import jakarta.inject.Singleton;

import org.apache.http.client.utils.URIBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.validator.web.shared.ArtifactType.PDF;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ValidationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ValidationHandler.class);

	@Autowired
	private XPlanValidator xPlanValidator;

	@Autowired
	private Path uploadFolder;

	@Autowired
	private ReportWriter reportWriter;

	@Autowired(required = false)
	private ValidatorWmsManager validatorWmsManager;

	@Autowired
	private ValidatorConfiguration validatorConfiguration;

	@Autowired
	private GeometricValidator geometricValidator;

	private XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();

	public ValidatorReport validate(XPlanArchive archive, String xFileName, ValidationSettings validationSettings)
			throws ValidatorException {
		LOG.debug("Validate plan with validationName {}", validationSettings.getValidationName());
		return xPlanValidator.validateNotWriteReport(validationSettings, archive, xFileName);
	}

	public Path zipReports(ValidatorReport validatorReport) throws IOException {
		Path workDir = createWorkDir();
		String validationName = validatorReport.getValidationName();
		LOG.debug("Create zip report in directory {} with validationName {}", workDir, validationName);

		reportWriter.writeArtefacts(validatorReport, workDir);
		List<ArtifactType> artifacts = Arrays.asList(PDF);

		Path zipArchive = workDir.resolve(validationName + ".zip");
		try (OutputStream zipOutput = Files.newOutputStream(zipArchive)) {
			reportWriter.writeZipWithArtifacts(zipOutput, validationName, artifacts, workDir);
		}
		return zipArchive;
	}

	public Path writePdfReport(ValidatorReport validatorReport) throws IOException {
		Path workDir = createWorkDir();
		String validationName = validatorReport.getValidationName();
		LOG.debug("Create pdf report in directory {} with validationName {}", workDir, validationName);

		reportWriter.writeArtefacts(validatorReport, workDir);
		return reportWriter.retrieveArtifactFile(workDir, validationName, PDF);
	}

	public URI addToWms(XPlanArchive archive) {
		try {
			if (validatorWmsManager != null) {
				XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
					.withSkipResolveReferences(true)
					.build()
					.parseXPlanFeatureCollection(archive);
				int id = validatorWmsManager.insert(xPlanFeatureCollection);
				return createWmsUrl(id);
			}
		}
		catch (XMLStreamException | UnknownCRSException e) {
			LOG.error("Plan could not be parsed. Reason {}", e.getMessage(), e);
		}
		catch (Exception e) {
			LOG.error("Plan could not be added to the XPlanValidatorWMS. Reason {}", e.getMessage(), e);
		}
		return null;
	}

	public XPlanArchive createArchiveFromZip(File uploadedPlan, String validationName) throws InvalidXPlanGmlOrArchive {
		try {
			return archiveCreator.createXPlanArchiveFromZip(validationName, new FileInputStream(uploadedPlan));
		}
		catch (Exception e) {
			throw new InvalidXPlanGmlOrArchive("Could not read attached file as XPlanArchive", e);
		}
	}

	public XPlanArchive createArchiveFromGml(File uploadedPlan, String validationName) throws InvalidXPlanGmlOrArchive {
		try {
			return archiveCreator.createXPlanArchiveFromGml(validationName, new FileInputStream(uploadedPlan));
		}
		catch (Exception e) {
			throw new InvalidXPlanGmlOrArchive("Could not read attached file as XPlanGML", e);
		}
	}

	private URI createWmsUrl(int id) throws URISyntaxException {
		String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
		URIBuilder uriBuilder = new URIBuilder(validatorWmsEndpoint);
		uriBuilder.addParameter("PLANWERK_MANAGERID", Integer.toString(id));
		uriBuilder.addParameter("SERVICE", "WMS");
		uriBuilder.addParameter("REQUEST", "GetCapabilities");
		return uriBuilder.build();
	}

	private Path createWorkDir() throws IOException {
		String id = UUID.randomUUID().toString();
		Path workDir = uploadFolder.resolve(id);
		Files.createDirectory(workDir);
		return workDir;
	}

}
