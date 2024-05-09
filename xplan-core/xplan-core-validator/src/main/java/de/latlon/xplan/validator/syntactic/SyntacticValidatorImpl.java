/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.syntactic;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.apache.xerces.xni.parser.XMLParseException;
import org.deegree.commons.xml.schema.SchemaValidationEvent;
import org.deegree.commons.xml.schema.SchemaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

/**
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class SyntacticValidatorImpl implements SyntacticValidator {

	private static final Logger LOG = LoggerFactory.getLogger(SyntacticValidatorImpl.class);

	private static final String INVALID_HINT = getMessage("SyntacticValidatorImpl_invalid");

	@Override
	public ValidatorResult validateSyntax(XPlanArchive archive) {
		try (InputStream is = archive.getMainFileInputStream()) {
			return validateSyntax(is, archive.getVersion());
		}
		catch (IOException e) {
			LOG.error("Syntaktische Valdierung wurde aufgrund eines Fehlers abgebrochen: {}", e.getMessage());
			LOG.trace("Syntactically validation failed!", e);
		}
		return new SyntacticValidatorResult(Collections.emptyList(), true, createDetail(true));
	}

	@Override
	public ValidatorResult validateSyntax(InputStream is, XPlanVersion version) {
		List<String> resultMessages = new ArrayList<>();
		String schemaUrl = version.getSchemaUrl().toString();
		List<SchemaValidationEvent> schemaValidationEvents = SchemaValidator.validate(is, schemaUrl);
		appendResultMessages(resultMessages, schemaValidationEvents);
		boolean isValid = resultMessages.isEmpty();
		ValidatorDetail detailsHint = createDetail(isValid);
		return new SyntacticValidatorResult(resultMessages, isValid, detailsHint);
	}

	@Override
	public void validateReferences(XPlanArchive archive, ExternalReferenceInfo externalReferenceInfo, boolean force)
			throws ValidatorException {
		for (ExternalReference ref : externalReferenceInfo.getAllReferences()) {
			String referenceUrl = ref.getReferenzUrl();
			if (referenceUrl != null && !referenceUrl.contains(":/") && archive.getEntry(referenceUrl) == null) {
				sendErrorReference(referenceUrl, force);
			}
			String geoRefUrl = ref.getGeoRefUrl();
			if (geoRefUrl != null && !geoRefUrl.contains(":/") && archive.getEntry(geoRefUrl) == null) {
				sendErrorReference(geoRefUrl, force);
			}
		}
	}

	private void sendErrorReference(String referenceURL, boolean force) throws ValidatorException {
		LOG.info("Referenz-Fehler: Das Hauptdokument enth\u00e4lt die relative URL '{}', "
				+ "aber die Datei ist nicht im Archiv enthalten.", referenceURL);
		if (!force) {
			LOG.error("Aufgrund von Referenz-Fehlern wurde die Validierung abgebrochen");
			throw new ValidatorException("Validierung wurde aufgrund von Referenz-Fehlern abgebrochen");
		}
		else {
			LOG.info("Fortsetzung trotz Referenz-Fehlern.");
		}
	}

	private void appendResultMessages(List<String> resultMessages, List<SchemaValidationEvent> schemaValidationEvents) {
		for (SchemaValidationEvent schemaValidationEvent : schemaValidationEvents) {
			XMLParseException exception = schemaValidationEvent.getException();
			int line = exception.getLineNumber();
			int column = exception.getColumnNumber();
			String exceptionMessage = exception.getMessage();
			String resultMessage = format("SyntacticValidatorImpl_invalid_location", exceptionMessage, line, column);
			resultMessages.add(resultMessage);
		}
	}

	private ValidatorDetail createDetail(boolean isValid) {
		ValidatorDetail detail = null;
		if (!isValid)
			detail = new ValidatorDetail(INVALID_HINT);
		return detail;
	}

}
