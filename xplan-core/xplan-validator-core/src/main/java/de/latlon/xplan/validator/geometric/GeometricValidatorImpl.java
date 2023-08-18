/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.inspector.aenderungen.AenderungenInspector;
import de.latlon.xplan.validator.geometric.inspector.doppelbelegung.DoppelbelegungInspector;
import de.latlon.xplan.validator.geometric.inspector.flaechenschluss.OptimisedFlaechenschlussInspector;
import de.latlon.xplan.validator.geometric.inspector.geltungsbereich.GeltungsbereichInspector;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.commons.tom.ReferenceResolvingException;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.feature.FeatureInspector;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

/**
 * Validates <link>XPlanArchives</link> geometrically
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class GeometricValidatorImpl implements GeometricValidator {

	private static final Logger LOG = LoggerFactory.getLogger(GeometricValidatorImpl.class);

	public static final String SKIP_FLAECHENSCHLUSS_OPTION = "skip-flaechenschluss";

	public static final String SKIP_GELTUNGSBEREICH_OPTION = "skip-geltungsbereich";

	public static final String SKIP_LAUFRICHTUNG_OPTION = "skip-laufrichtung";

	public static final ValidationOption SKIP_FLAECHENSCHLUSS = new ValidationOption(SKIP_FLAECHENSCHLUSS_OPTION,
			Boolean.toString(true));

	public static final ValidationOption SKIP_GELTUNGSBEREICH = new ValidationOption(SKIP_GELTUNGSBEREICH_OPTION,
			Boolean.toString(true));

	public static final ValidationOption SKIP_LAUFRICHTUNG = new ValidationOption(SKIP_LAUFRICHTUNG_OPTION,
			Boolean.toString(true));

	public static final ArrayList<ValidationOption> SKIP_OPTIONS = new ArrayList<>();

	static {
		SKIP_OPTIONS.add(SKIP_FLAECHENSCHLUSS);
		SKIP_OPTIONS.add(SKIP_GELTUNGSBEREICH);
	}

	private final boolean treatAenderungIntegrityAsFailure;

	public GeometricValidatorImpl() {
		this(false);
	}

	public GeometricValidatorImpl(boolean treatAenderungIntegrityAsFailure) {
		this.treatAenderungIntegrityAsFailure = treatAenderungIntegrityAsFailure;
	}

	@Override
	public GeometricValidatorResult validateGeometry(XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
			List<ValidationOption> voOptions) throws ValidatorException {
		try {
			ValidatorResult result = readAndValidateArchive(archive, crs, voOptions);
			logResult(force, result);
			return new GeometricValidatorResult(result.warnings, result.errors, result.badGeometries, crs,
					result.isValid());
		}
		catch (XMLStreamException e) {
			LOG.trace("Geometric validation failed!", e);
			throw new ValidatorException(
					"Geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen", e);
		}
	}

	private ValidatorResult readAndValidateArchive(XPlanArchive archive, ICRS crs, List<ValidationOption> voOptions)
			throws XMLStreamException {
		ValidatorResult result = new ValidatorResult();
		XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper(archive.getMainFileXmlReader(), null);
		long begin = System.currentTimeMillis();
		LOG.info("- Einlesen der Features (+ Geometrievalidierung)...");
		boolean skipOrientation = isOptionTrue(voOptions, SKIP_LAUFRICHTUNG_OPTION);
		XPlanGeometryInspector geometryInspector = new XPlanGeometryInspector(xmlStream, skipOrientation);
		List<GeometricFeatureInspector> featureInspectors = createInspectorsForVersion(archive.getVersion(),
				archive.getType(), voOptions);
		AenderungenInspector aenderungenInspector = new AenderungenInspector();
		try {
			XPlanGmlParser xPlanGmlParser = createXPlanGmlParser(crs, geometryInspector, featureInspectors,
					aenderungenInspector);
			FeatureCollection xPlanFeatures = xPlanGmlParser.parseFeatureCollection(archive);
			result.setXplanFeatures(xPlanFeatures);
			result.elapsed = System.currentTimeMillis() - begin;
			result.addErrors(geometryInspector.getErrors());
			List<String> brokenGeometryErrors = extendMessagesOfBrokenGeometryErrors(xPlanGmlParser);
			result.addErrors(brokenGeometryErrors);
			result.addWarnings(geometryInspector.getWarnings());
			result.addBadGeometries(geometryInspector.getBadGeometries());
			featureInspectors.stream().forEach(fi -> checkAndAddRules(fi, result));

			resolveAndValidateXlinks(xPlanGmlParser, result, aenderungenInspector);
		}
		catch (Exception e) {
			String msg = getMessage("GeometricValidationImpl_error");
			result.addError(msg);
			LOG.info("Unexpected failure by geometry validation ", e);
		}
		return result;
	}

	private static XPlanGmlParser createXPlanGmlParser(ICRS crs, XPlanGeometryInspector geometryInspector,
			List<GeometricFeatureInspector> featureInspectors, AenderungenInspector aenderungenInspector) {
		List<FeatureInspector> allFeatureInspectors = featureInspectors.stream().collect(Collectors.toList());
		allFeatureInspectors.add(aenderungenInspector);

		return XPlanGmlParserBuilder.newBuilder()
			.withSkipResolveReferences(true)
			.withSkipBrokenGeometries(true)
			.withDefaultCrs(crs)
			.withFeatureInspectors(allFeatureInspectors)
			.withGeometryInspectors(geometryInspector)
			.build();
	}

	private void checkAndAddRules(GeometricFeatureInspector fi, ValidatorResult result) {
		fi.checkGeometricRule();
		result.addErrors(fi.getErrors());
		result.addWarnings(fi.getWarnings());
		result.addBadGeometries(fi.getBadGeometries());
	}

	private List<GeometricFeatureInspector> createInspectorsForVersion(XPlanVersion version, XPlanType type,
			List<ValidationOption> voOptions) {
		List<GeometricFeatureInspector> inspectors = new ArrayList<>();
		if (!isOptionTrue(voOptions, SKIP_FLAECHENSCHLUSS_OPTION))
			inspectors.add(new OptimisedFlaechenschlussInspector(version, type));
		if (!isOptionTrue(voOptions, SKIP_GELTUNGSBEREICH_OPTION))
			inspectors.add(new GeltungsbereichInspector(version));
		inspectors.add(new DoppelbelegungInspector());
		return inspectors.stream()
			.filter(inspector -> inspector.applicableForVersion(version))
			.collect(Collectors.toList());
	}

	private void resolveAndValidateXlinks(XPlanGmlParser xPlanGmlParser, ValidatorResult result,
			AenderungenInspector aenderungenInspector) {
		long begin = System.currentTimeMillis();
		LOG.info("- Überprüfung der XLink-Integrität...");
		List<GMLReference<?>> gmlRefs = xPlanGmlParser.getIdContext().getReferences();
		for (GMLReference<?> gmlReference : gmlRefs) {
			if (gmlReference instanceof FeatureReference) {
				if (gmlReference.isLocal()) {
					String id = gmlReference.getURI().substring(1);
					LOG.debug("Resolving reference to object '" + id + "'");
					try {
						gmlReference.getReferencedObject();
					}
					catch (ReferenceResolvingException e) {
						if (!treatAenderungIntegrityAsFailure && aenderungenInspector.isAenderungReference(id)) {
							String warning = format("GeometricValidatorImpl_error_XLink_intern_aenderungsplaene", id);
							LOG.info(warning);
							result.addWarning(warning);
						}
						else {
							String errorMessage = format("GeometricValidatorImpl_error_XLink_intern", id);
							LOG.info(errorMessage);
							result.addError(errorMessage);
						}
					}
				}
				else {
					String msg = format("GeometricValidatorImpl_error_XLink_extern", gmlReference.getURI());
					LOG.info(msg);
					result.addError(msg);
				}
			}
		}
		result.elapsed = System.currentTimeMillis() - begin;
	}

	private boolean isOptionTrue(List<ValidationOption> voOptions, String optionName) {
		if (voOptions == null)
			return false;
		for (ValidationOption voOption : voOptions) {
			if (optionName.equals(voOption.getName())) {
				if (voOption.getArgument() != null)
					return Boolean.valueOf(voOption.getArgument());
			}
		}
		return false;
	}

	private void logResult(boolean force, ValidatorResult result) {
		if (result.isValid())
			logSuccessMessages(result);
		else
			logErrorMessages(force, result);
	}

	private void logSuccessMessages(ValidatorResult result) {
		LOG.info("OK [{} ms]: {} Features", result.elapsed, result.xPlanFeatures.size());
		if (!result.warnings.isEmpty()) {
			LOG.info("Geometrie-Warnungen: {}", result.warnings.size());
			for (String warning : result.warnings)
				LOG.info(" - {}", warning);
		}
	}

	private void logErrorMessages(boolean force, ValidatorResult result) {
		if (!result.warnings.isEmpty()) {
			LOG.info("Geometrie-Warnungen: {}", result.warnings.size());
			for (String warning : result.warnings)
				LOG.info(" - {}", warning);
		}
		LOG.info("Geometrie-Fehler: {}", result.errors.size());
		for (String error : result.errors)
			LOG.info(" - {}", error);
		if (!force) {
			LOG.info(
					"{} Geometrie-Fehler, {} Geometrie-Warnung(en). Hinweis: Sie k\u00f6nnen das "
							+ "Importieren des Plans mit der Kommandozeilen-Option --force erzwingen.",
					result.errors.size(), result.warnings.size());
			throw new IllegalArgumentException(
					"Der Plan kann aufgrund von " + result.errors.size() + " Geometrie-Fehler(n) und "
							+ result.warnings.size() + " Geometrie-Warnung(en) nicht verarbeitet werden.");
		}
		else
			LOG.info("Fortsetzung trotz Geometrie-Fehlern (--force).");
	}

	private List<String> extendMessagesOfBrokenGeometryErrors(XPlanGmlParser xPlanGmlParser) {
		ArrayList<String> extendedBrokenGeometryErrors = new ArrayList<>();
		List<String> brokenGeometryErrors = xPlanGmlParser.getSkippedBrokenGeometryErrors();
		for (String brokenGeometryError : brokenGeometryErrors) {
			extendedBrokenGeometryErrors.add(brokenGeometryError + getMessage("GeometricValidatorImpl_brokenGeom"));
		}
		return extendedBrokenGeometryErrors;
	}

	private class ValidatorResult {

		private List<String> errors = new ArrayList<>();

		private List<String> warnings = new ArrayList<>();

		private List<BadGeometry> badGeometries = new ArrayList<>();

		private long elapsed;

		private FeatureCollection xPlanFeatures;

		private void addError(String errorToAdd) {
			this.errors.add(errorToAdd);
		}

		private void addErrors(List<String> errorsToAdd) {
			this.errors.addAll(errorsToAdd);
		}

		public void addWarning(String warning) {
			this.warnings.add(warning);
		}

		private void addWarnings(List<String> warningsToAdd) {
			this.warnings.addAll(warningsToAdd);
		}

		private void addBadGeometries(List<BadGeometry> badGeometries) {
			this.badGeometries.addAll(badGeometries);
		}

		private void setXplanFeatures(FeatureCollection xPlanFeatures) {
			this.xPlanFeatures = xPlanFeatures;
		}

		public boolean isValid() {
			return errors.isEmpty();
		}

	}

}
