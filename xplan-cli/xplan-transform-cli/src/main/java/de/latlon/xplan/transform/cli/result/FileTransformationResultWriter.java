/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static java.nio.file.Files.newOutputStream;
import static org.apache.commons.io.IOUtils.write;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FileTransformationResultWriter implements TransformationResultWriter {

	private static final Logger LOG = LoggerFactory.getLogger(FileTransformationResultWriter.class);

	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");

	private Path outDir;

	public FileTransformationResultWriter(Path outDir) throws IOException {
		this.outDir = outDir;
	}

	@Override
	public void writeResult(String id, String name, SyntacticValidatorResult validatorResult,
			TransformationResult transformationResult) {
		String fileNameGml = id + "_transformedGml_" + dateTimeFormatter.format(LocalDateTime.now()) + ".xml";
		String fileNameValidationResult = id + "_validationResult_" + dateTimeFormatter.format(LocalDateTime.now())
				+ ".xml";
		Path gmlFile = outDir.resolve(fileNameGml);
		Path validationResultFile = outDir.resolve(fileNameValidationResult);
		try (OutputStream gmlOutputStream = newOutputStream(gmlFile);
				OutputStream validationResultOutputStream = newOutputStream(validationResultFile)) {
			write(transformationResult.getTransformationResult(), gmlOutputStream);
			String validationResult = validatorResult.isValid() ? "valid"
					: validatorResult.getMessages().stream().collect(Collectors.joining(","));
			write(validationResult, validationResultOutputStream, Charset.defaultCharset());
		}
		catch (IOException e) {
			LOG.warn("Could not write results to file");
		}
	}

	@Override
	public void close() throws IOException {
	}

}
