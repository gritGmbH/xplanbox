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
package de.latlon.xplan.validator.geometric.inspector.doppelbelegung;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.gml.feature.FeatureInspectionException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.geom.TopologyException;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.validator.i18n.ValidationMessages.format;

/**
 * Implements 4.5.2.4 (since XPlanGML 6.0): Verbot der Doppelbelegung gleichnamiger
 * Attribute
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DoppelbelegungInspector implements GeometricFeatureInspector {

	private static final double TOLERANCE_METRE = 0.002;

	private final List<String> errors = new ArrayList<>();

	private final List<String> namesOfFeatureTypesToCheck = Arrays.asList("BP_BaugebietsTeilFlaeche",
			"BP_BesondererNutzungszweckFlaeche", "BP_SpielSportanlagenFlaeche", "BP_GemeinbedarfsFlaeche",
			"BP_GruenFlaeche", "BP_VerEntsorgung", "SO_Strassenverkehr");

	private static final List<String> propertiesToCheck = Arrays.asList("bauweise", "bauweiseText",
			"abweichendeBauweise", "vertikaleDifferenzierung", "bebauungsArt", "bebauungVordereGrenze",
			"bebauungRueckwaertigeGrenze", "bebauungSeitlicheGrenze", "refGebaeudequerschnitt", "geschossMin",
			"geschossMax", "BP_GestaltungBaugebiet", "FR", "MaxZahlWohnungen", "MinGRWohneinheit", "Fmin", "Fmax",
			"Bmin", "Bmax", "Tmin", "Tmax", "GFZmin", "GFZmax", "GFZ", "GFZ_Ausn", "GFmin", "GFmax", "GF", "GF_Ausn",
			"BMZ", "BMZ_Ausn", "BM", "BM_Ausn", "GRZmin", "GRZmax", "GRZ", "GRZ_Ausn", "GRmin", "GRmax", "GR",
			"GR_Ausn", "Zmin", "Zmax", "Zzwingend", "Z", "Z_Ausn", "Z_Staffel", "Z_Dach", "ZUmin", "ZUmax",
			"ZUzwingend", "ZU", "ZU_Ausn", "MZspezial", "wohnnutzungEGStrasse", "ZWohn", "GFAntWohnen", "GFWohnen",
			"GFAntGewerbe", "GFGewerbe", "VF");

	private final List<Feature> ueberbaubareGrundstuecksflaecheFeatures = new ArrayList<>();

	private final List<Feature> featuresToCheck = new ArrayList<>();

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		QName name = feature.getName();
		if ("BP_UeberbaubareGrundstuecksFlaeche".equals(name.getLocalPart())) {
			ueberbaubareGrundstuecksflaecheFeatures.add(feature);
		}
		else if (namesOfFeatureTypesToCheck.contains(name.getLocalPart())) {
			featuresToCheck.add(feature);
		}
		return feature;
	}

	@Override
	public boolean checkGeometricRule() {
		featuresToCheck.forEach(featureToCheck -> checkFeature(featureToCheck));
		return getErrors().isEmpty();
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}

	@Override
	public List<String> getWarnings() {
		return Collections.emptyList();
	}

	@Override
	public List<BadGeometry> getBadGeometries() {
		return Collections.emptyList();
	}

	@Override
	public boolean applicableForVersion(XPlanVersion version) {
		return XPLAN_60.equals(version);
	}

	private void checkFeature(Feature featureToCheck) {
		List<Feature> overlappingFeatures = findOverlappingFeatures(getGeometry(featureToCheck));
		overlappingFeatures.forEach(overlappingFeature -> checkProperties(featureToCheck, overlappingFeature));
	}

	private void checkProperties(Feature featureToCheck, Feature overlappingFeature) {
		propertiesToCheck.forEach(propertyToCheck -> {
			QName propertyName = new QName(featureToCheck.getName().getNamespaceURI(), propertyToCheck);
			List<Property> properties = featureToCheck.getProperties(propertyName);
			if (!properties.isEmpty()) {
				checkIfPropertyExistInOverlappingFeature(featureToCheck, propertyName, overlappingFeature);
			}
		});
	}

	private void checkIfPropertyExistInOverlappingFeature(Feature featureToCheck, QName propertyName,
			Feature overlappingFeature) {
		if (!overlappingFeature.getProperties(propertyName).isEmpty()) {
			addError(featureToCheck.getId(), propertyName.getLocalPart(), overlappingFeature.getId());
		}
	}

	private List<Feature> findOverlappingFeatures(Geometry geom) {
		if (geom == null) {
			return Collections.emptyList();
		}
		Geometry geomWithTolerance = geom.buffer(TOLERANCE_METRE * -1);
		return ueberbaubareGrundstuecksflaecheFeatures.stream().filter(ueberbaubareGrundstuecksflaecheFeature -> {
			Geometry ueberbaubareGrundstuecksflaecheGeom = getGeometry(ueberbaubareGrundstuecksflaecheFeature);
			if (ueberbaubareGrundstuecksflaecheGeom != null) {
				IntersectionMatrix relate = ueberbaubareGrundstuecksflaecheGeom.relate(geomWithTolerance);
				return relate.matches("T********");
			}
			return false;
		}).collect(Collectors.toList());
	}

	private Geometry getGeometry(Feature feature) {
		TypedObjectNode value = feature.getGeometryProperties().get(0).getValue();
		try {
			if (value instanceof AbstractDefaultGeometry) {
				return ((AbstractDefaultGeometry) value).getJTSGeometry();
			}
		}
		catch (TopologyException | IllegalArgumentException e) {
			String error = format("DoppelbelegungInspector_invalid_geom", feature.getType().getName().getLocalPart(),
					feature.getId());
			errors.add(error);
		}
		return null;
	}

	private void addError(String featureId, String propertyName, String overlappingFeatureid) {
		errors.add(format("DoppelbelegungInspector_error", featureId, propertyName, overlappingFeatureid));
	}

}
