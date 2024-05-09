/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.feature.types.property.CustomPropertyType;
import org.deegree.feature.types.property.FeaturePropertyType;
import org.deegree.feature.types.property.SimplePropertyType;
import org.deegree.gml.reference.FeatureReference;
import org.deegree.gml.reference.GmlDocumentIdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;
import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;

/**
 * Modifies the {@link FeatureCollection} representing an XPlanGML.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanManipulator {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanManipulator.class);

	private static final QName XLINK_HREF_ATTRIBUTE = new QName("http://www.w3.org/1999/xlink", "href");

	/**
	 * Modifies the {@link FeatureCollection} representing an XPlanGML, by the changes
	 * described in {@link XPlanToEdit}.
	 * @param planToEdit the {@link FeatureCollection} to edit, never <code>null</code>
	 * @param planWithChanges containing the changes, never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 * @param type of the plan, never <code>null</code>
	 * @param schema of the plan, never <code>null</code>
	 */
	public void modifyXPlan(FeatureCollection planToEdit, XPlanToEdit planWithChanges, XPlanVersion version,
			XPlanType type, AppSchema schema) throws EditException {
		checkVersionAndType(version, type);

		GmlDocumentIdContext context = new GmlDocumentIdContext(version.getGmlVersion());
		context.setApplicationSchema(schema);

		List<String> previouslyReferencedTextFeatureIds = parseReferencedTextFeatureIds(planToEdit);

		List<Feature> featuresToAdd = new ArrayList<>();
		List<Feature> featuresToRemove = new ArrayList<>();
		List<String> referencesToRemove = new ArrayList<>();
		for (Feature feature : planToEdit) {
			QName featureName = feature.getName();
			if (isPlan(featureName))
				modifyPlan(context, version, type, planToEdit, feature, planWithChanges, schema, featuresToAdd,
						featuresToRemove, referencesToRemove, previouslyReferencedTextFeatureIds);
			if (isBereich(featureName))
				modifyBereich(context, version, type, planToEdit, feature, planWithChanges, schema, featuresToAdd,
						featuresToRemove, referencesToRemove);
		}
		removeAllPropertiesWithReferences(planToEdit, referencesToRemove);
		planToEdit.removeAll(featuresToRemove);
		planToEdit.addAll(featuresToAdd);
	}

	private void modifyPlan(GmlDocumentIdContext context, XPlanVersion version, XPlanType type,
			FeatureCollection planToEdit, Feature feature, XPlanToEdit changes, AppSchema schema,
			List<Feature> featuresToAdd, List<Feature> featuresToRemove, List<String> referencesToRemove,
			List<String> previouslyReferencedTextFeatureIds) throws EditException {
		modify(version, feature, "name", changes.getBaseData().getPlanName());
		modify(version, feature, "beschreibung", changes.getBaseData().getDescription());
		modify(version, feature, "technHerstellDatum", changes.getBaseData().getCreationDate());
		modify(version, feature, "untergangsDatum", changes.getBaseData().getLossDate());
		modify(version, feature, "rechtsverordnungsDatum", changes.getBaseData().getRegulationDate());
		modifyCode(version, feature, "rechtsstand", changes.getBaseData().getLegislationStatusCode());
		// https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1227
		// modifyCode(version, feature, "sonstPlanArt",
		// changes.getBaseData().getOtherPlanTypeCode());
		if (!SO_Plan.equals(type)) {
			modifyCode(version, feature, "planArt", changes.getBaseData().getPlanTypeCode());
		}
		if (XPLAN_60.equals(version)) {
			modifyChanges(version, feature, schema, "wurdeGeaendertVonPlan", changes.getChanges(), CHANGED_BY);
			modifyChanges(version, feature, schema, "aendertPlan", changes.getChanges(), CHANGES);
		}
		else {
			modifyChanges(version, feature, schema, "wurdeGeaendertVon", changes.getChanges(), CHANGED_BY);
			modifyChanges(version, feature, schema, "aendert", changes.getChanges(), CHANGES);
		}
		modifyTexts(context, version, type, planToEdit, feature, schema, changes.getTexts(), featuresToAdd,
				featuresToRemove, referencesToRemove, previouslyReferencedTextFeatureIds);
		if (XPLAN_41.equals(version) || XPLAN_50.equals(version) || XPLAN_51.equals(version) || XPLAN_52.equals(version)
				|| XPLAN_53.equals(version) || XPLAN_54.equals(version))
			modifyCode(version, feature, "verfahren", changes.getBaseData().getMethodCode());
		modifyReferences(version, feature, changes, schema);
	}

	private void modifyBereich(GmlDocumentIdContext context, XPlanVersion version, XPlanType type,
			FeatureCollection planToEdit, Feature bereichFeature, XPlanToEdit changes, AppSchema schema,
			List<Feature> featuresToAdd, List<Feature> featuresToRemove, List<String> referencesToRemove)
			throws EditException {
		List<RasterBasis> rasterBasis = changes.getRasterBasis();
		String nummer = retrieveNummer(bereichFeature);
		List<RasterBasis> rasterBasisOfBereich = rasterBasis.stream()
			.filter(rb -> nummer == null || nummer.equals(rb.getBereichNummer()))
			.collect(Collectors.toList());
		if (rasterBasisOfBereich.isEmpty() || countRasterReferences(rasterBasisOfBereich).isEmpty()) {
			removeRasterBasis(version, planToEdit, bereichFeature, rasterBasis, featuresToRemove, referencesToRemove,
					bereichFeature.getName().getNamespaceURI());
		}
		else {
			for (RasterBasis rb : rasterBasisOfBereich) {
				modifyRasterBasis(context, version, type, planToEdit, bereichFeature, schema, rb, featuresToAdd,
						featuresToRemove);
			}
		}
	}

	private void modifyCode(XPlanVersion version, Feature feature, String propertyName, int newCodeValue)
			throws EditException {
		QName propName = new QName(feature.getName().getNamespaceURI(), propertyName);
		if (newCodeValue > 0) {
			PropertyType propertyDeclaration = feature.getType().getPropertyDeclaration(propName);
			if (propertyDeclaration == null) {
				throw new EditException("Could not find property declaration of property " + propertyName + ".");
			}
			if (propertyDeclaration instanceof SimplePropertyType) {
				Property prop = createSimpleProperty(feature, propName, Integer.toString(newCodeValue));
				addOrReplaceProperty(version, feature, propName, prop);
			}
			else if (propertyDeclaration instanceof CustomPropertyType) {
				Property prop = new GenericProperty(propertyDeclaration,
						new PrimitiveValue(Integer.toString(newCodeValue)));
				addOrReplaceProperty(version, feature, propName, prop);
			}
			else {
				LOG.warn("Editing of " + propertyDeclaration.getClass() + " (PropertyName: " + propertyName
						+ ") is not supported yet.");
			}
		}
		else {
			removeProperties(feature, propName);
		}
	}

	private void modifyChanges(XPlanVersion version, Feature feature, AppSchema schema, String propertyName,
			List<Change> changes, ChangeType changedType) {
		QName propName = new QName(feature.getName().getNamespaceURI(), propertyName);
		List<Property> properties = new ArrayList<>();
		for (Change change : changes) {
			if (changedType.equals(change.getType())) {
				if (XPLAN_41.equals(version) || XPLAN_50.equals(version) || XPLAN_51.equals(version)
						|| XPLAN_52.equals(version) || XPLAN_53.equals(version) || XPLAN_54.equals(version)
						|| XPLAN_60.equals(version)) {
					addProperty(properties, createVerbundenerPlanProperty(version, feature, schema, change, propName));
				}
			}
		}
		addOrReplaceProperties(version, feature, propName, properties);
	}

	private void modifyTexts(GmlDocumentIdContext context, XPlanVersion version, XPlanType type,
			FeatureCollection planToEdit, Feature feature, AppSchema schema, List<Text> texts,
			List<Feature> featuresToAdd, List<Feature> featuresToRemove, List<String> referencesToRemove,
			List<String> previouslyReferencedTextFeatureIds) throws EditException {
		String namespaceUri = feature.getName().getNamespaceURI();
		QName propName = new QName(namespaceUri, "texte");
		List<Property> properties = new ArrayList<Property>();
		for (Text text : texts) {
			String gmlid = text.getFeatureId();
			Feature oldTextFeature = null;
			if (gmlid != null) {
				oldTextFeature = detectFeatureById(planToEdit, gmlid);
				if (oldTextFeature != null)
					featuresToRemove.add(oldTextFeature);
			}
			else {
				gmlid = generateGmlId(propName);
			}
			Property linkProp = createPropertyWithHrefAttribute(context, feature.getType(), propName, gmlid);
			addProperty(properties, linkProp);
			createAndAddTextFeature(version, type, schema, namespaceUri, text, gmlid, featuresToAdd, oldTextFeature);
		}
		for (String previouslyReferencedTextFeatureId : previouslyReferencedTextFeatureIds) {
			if (isNotLongerReferenced(texts, previouslyReferencedTextFeatureId)) {
				Feature oldTextFeature = detectFeatureById(planToEdit, previouslyReferencedTextFeatureId);
				if (oldTextFeature != null) {
					featuresToRemove.add(oldTextFeature);
					referencesToRemove.add(previouslyReferencedTextFeatureId);
				}
			}
		}
		addOrReplaceProperties(version, feature, propName, properties);
	}

	private void modifyRasterBasis(GmlDocumentIdContext context, XPlanVersion version, XPlanType type,
			FeatureCollection planToEdit, Feature bereichFeature, AppSchema schema, RasterBasis rasterBasis,
			List<Feature> featuresToAdd, List<Feature> featuresToRemove) throws EditException {
		String namespaceUri = bereichFeature.getName().getNamespaceURI();
		// XP_Rasterdarstellung.refScan --> XP_Bereich.refScan
		if (XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version) || XPLAN_54.equals(version)
				|| XPLAN_60.equals(version)) {
			modifyRasterBasis_XPlan5X(version, type, planToEdit, bereichFeature, schema, rasterBasis, featuresToRemove,
					namespaceUri);
		}
		else {
			modifyRasterBasis(context, version, planToEdit, bereichFeature, schema, rasterBasis, featuresToAdd,
					featuresToRemove, namespaceUri);
			removeProperties(bereichFeature, new QName(namespaceUri, "refScan"));
		}
	}

	private void removeRasterBasis(XPlanVersion version, FeatureCollection planToEdit, Feature bereichFeature,
			List<RasterBasis> allRasterBasis, List<Feature> featuresToRemove, List<String> referencesToRemove,
			String namespaceUri) {
		if (!XPLAN_60.equals(version)) {
			String previouslyReferencedRasterBasisFeatureId = parseReferencedRasterBasisFeatureId(bereichFeature);
			if (XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version)
					|| XPLAN_54.equals(version)) {
				QName rasterBasisElementName = getRasterBasisElementName(version, namespaceUri);
				Feature oldRasterBasisFeature = detectFeatureById(planToEdit, rasterBasisElementName,
						previouslyReferencedRasterBasisFeatureId);
				if (oldRasterBasisFeature != null) {
					featuresToRemove.add(oldRasterBasisFeature);
					referencesToRemove.add(previouslyReferencedRasterBasisFeatureId);
				}
			}
			else {
				QName rasterBasisElementName = getRasterBasisElementName(version, namespaceUri);
				Feature oldRasterBasisFeature = detectFeatureById(planToEdit, rasterBasisElementName,
						previouslyReferencedRasterBasisFeatureId);
				if (oldRasterBasisFeature != null
						&& !rasterBasisFeatureIsStillReferenced(oldRasterBasisFeature.getId(), allRasterBasis)) {
					featuresToRemove.add(oldRasterBasisFeature);
					referencesToRemove.add(previouslyReferencedRasterBasisFeatureId);
				}
			}
		}
		removeProperties(bereichFeature, new QName(namespaceUri, "refScan"));
	}

	private boolean rasterBasisFeatureIsStillReferenced(String oldRasterBasisFeatureId,
			List<RasterBasis> allRasterBasis) {
		return allRasterBasis.stream().anyMatch(rasterBasis -> {
			if (oldRasterBasisFeatureId.equals(rasterBasis.getFeatureId())) {
				return !rasterBasis.getRasterReferences().isEmpty();
			}
			return false;
		});
	}

	private void modifyRasterBasis(GmlDocumentIdContext context, XPlanVersion version, FeatureCollection planToEdit,
			Feature bereichFeature, AppSchema schema, RasterBasis rasterBasis, List<Feature> featuresToAdd,
			List<Feature> featuresToRemove, String namespaceUri) throws EditException {
		String gmlid = rasterBasis.getFeatureId();
		modifyRasterBasisReferences(context, version, planToEdit, bereichFeature, schema, featuresToAdd,
				featuresToRemove, namespaceUri, gmlid, rasterBasis);
	}

	private void modifyRasterBasis_XPlan5X(XPlanVersion version, XPlanType type, FeatureCollection planToEdit,
			Feature bereichFeature, AppSchema schema, RasterBasis rasterBasis, List<Feature> featuresToRemove,
			String namespaceUri) {
		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();

		// XP_Rasterdarstellung.refScan --> XP_Bereich.refScan
		List<RasterReference> scans = collectRasterReferencesByType(rasterReferences, SCAN);

		String rasterBasisGmlId = rasterBasis.getFeatureId();
		if (rasterBasisGmlId != null) {
			QName rasterBasisElementName = getRasterBasisElementName(version, namespaceUri);
			Feature oldRasterBasisFeature = detectFeatureById(planToEdit, rasterBasisElementName, rasterBasisGmlId);
			if (oldRasterBasisFeature != null)
				featuresToRemove.add(oldRasterBasisFeature);
		}

		if (!scans.isEmpty()) {
			QName bereichFeatureTypeName = getBereichFeatureTypeName(type, namespaceUri);
			FeatureType bereichFeatureType = schema.getFeatureType(bereichFeatureTypeName);
			List<Property> properties = new ArrayList<>();
			for (RasterReference scan : scans) {
				QName refPropName = new QName(namespaceUri, "refScan");
				String externeReferenzFeatureTypeName = "XP_ExterneReferenz";
				createAndAddExterneReferenz(schema, scan, bereichFeatureType, properties, refPropName,
						externeReferenzFeatureTypeName, null);
			}
			QName propName = new QName(namespaceUri, "refScan");
			addOrReplaceProperties(version, bereichFeature, propName, properties);
		}
		removeProperties(bereichFeature, new QName(namespaceUri, "rasterBasis"));
	}

	private void modifyRasterBasisReferences(GmlDocumentIdContext context, XPlanVersion version,
			FeatureCollection planToEdit, Feature bereichFeature, AppSchema schema, List<Feature> featuresToAdd,
			List<Feature> featuresToRemove, String namespaceUri, String rasterBasisGmlId, RasterBasis rasterBasis)
			throws EditException {
		QName propName = new QName(namespaceUri, "rasterBasis");
		if (rasterBasisGmlId != null) {
			QName rasterBasisElementName = getRasterBasisElementName(version, namespaceUri);
			Feature oldRasterBasisFeature = detectFeatureById(planToEdit, rasterBasisElementName, rasterBasisGmlId);
			if (oldRasterBasisFeature != null)
				featuresToRemove.add(oldRasterBasisFeature);
		}
		else {
			rasterBasisGmlId = generateGmlId(propName);
		}
		Property linkProp = createPropertyWithHrefAttribute(context, bereichFeature.getType(), propName,
				rasterBasisGmlId);
		List<Property> properties = new ArrayList<>();
		addProperty(properties, linkProp);
		createAndAddRasterBasisFeature(version, planToEdit, schema, namespaceUri, rasterBasis, rasterBasisGmlId,
				featuresToAdd, featuresToRemove);
		addOrReplaceProperties(version, bereichFeature, propName, properties);
	}

	private boolean isNotLongerReferenced(List<Text> texts, String previouslyReferencedTextFeatureId) {
		for (Text text : texts) {
			if (previouslyReferencedTextFeatureId.equals(text.getFeatureId()))
				return false;
		}
		return true;
	}

	private Feature detectFeatureById(FeatureCollection planToEdit, String featureId) {
		return detectFeatureById(planToEdit, null, featureId);
	}

	private Feature detectFeatureById(FeatureCollection planToEdit, QName featureTypeName, String featureId) {
		if (featureId == null)
			return null;
		for (Feature feature : planToEdit) {
			if (featureId.equals(feature.getId())
					&& (featureTypeName == null || featureTypeName.equals(feature.getName())))
				return feature;
		}
		return null;
	}

	private void modifyReferences(XPlanVersion version, Feature feature, XPlanToEdit changes, AppSchema schema) {
		if (XPLAN_41.equals(version)) {
			modifyReferences(version, feature, schema, "refRechtsplan", changes.getReferences(), RECHTSPLAN);
			modifyReferences(version, feature, schema, "refBegruendung", changes.getReferences(), BEGRUENDUNG);
			if (XPLAN_41.equals(version))
				modifyReferences(version, feature, schema, "refGruenordnungsplan", changes.getReferences(),
						GRUENORDNUNGSPLAN);
		}
		else if (XPLAN_50.equals(version) || XPLAN_51.equals(version) || XPLAN_52.equals(version)
				|| XPLAN_53.equals(version) || XPLAN_54.equals(version) || XPLAN_60.equals(version)) {
			modifyReferences_XPlan50(version, feature, changes, schema);
		}
	}

	private void modifyReferences_XPlan50(XPlanVersion version, Feature feature, XPlanToEdit changes,
			AppSchema schema) {
		String namespaceUri = feature.getName().getNamespaceURI();
		QName propName = new QName(namespaceUri, "externeReferenz");
		List<Property> properties = new ArrayList<>();
		FeatureType featureType = feature.getType();
		for (Reference reference : changes.getReferences()) {
			int spezExterneReferenzTyp = reference.getType().getSpezExterneReferenceType();
			createAndAddExterneReferenz(schema, reference, featureType, properties, propName, "XP_SpezExterneReferenz",
					Integer.toString(spezExterneReferenzTyp));
		}
		addOrReplaceProperties(version, feature, propName, properties);
	}

	private void modifyReferences(XPlanVersion version, Feature feature, AppSchema schema, String propertyName,
			List<Reference> references, ReferenceType refType) {
		String namespaceUri = feature.getName().getNamespaceURI();
		QName propName = new QName(namespaceUri, propertyName);
		List<Property> properties = new ArrayList<>();
		FeatureType featureType = feature.getType();
		for (Reference reference : references) {
			if (refType.equals(reference.getType())) {
				createAndAddExterneReferenz(schema, reference, featureType, properties, propName, "XP_ExterneReferenz",
						null);
			}
		}
		addOrReplaceProperties(version, feature, propName, properties);
	}

	private void modify(XPlanVersion version, Feature feature, String propertyName, String newValue)
			throws EditException {
		QName propName = new QName(feature.getName().getNamespaceURI(), propertyName);
		if (newValue != null && newValue.length() > 0) {
			Property prop = createSimpleProperty(feature, propName, newValue);
			addOrReplaceProperty(version, feature, propName, prop);
		}
		else {
			removeProperties(feature, propName);
		}
	}

	private void modify(XPlanVersion version, Feature feature, String propertyName, Date newValue)
			throws EditException {
		QName propName = new QName(feature.getName().getNamespaceURI(), propertyName);
		if (newValue != null) {
			SimplePropertyType propType = (SimplePropertyType) feature.getType().getPropertyDeclaration(propName);
			if (propType == null) {
				throw new EditException("Could not find property declaration of property " + propertyName + ".");
			}
			org.deegree.commons.tom.datetime.Date date = new org.deegree.commons.tom.datetime.Date(newValue, null);
			Property prop = new GenericProperty(propType, new PrimitiveValue(date));
			addOrReplaceProperty(version, feature, propName, prop);
		}
		else {
			removeProperties(feature, propName);
		}
	}

	private void removeAllPropertiesWithReferences(FeatureCollection planToEdit, List<String> referencesToRemove) {
		for (Feature feature : planToEdit) {
			List<Property> propertiesToRemove = new ArrayList<>();
			for (Property property : feature.getProperties()) {
				if (!isPropertySecured(feature, property)) {
					PrimitiveValue hrefValue = property.getAttributes().get(XLINK_HREF_ATTRIBUTE);
					if (hrefValue != null && referencesToRemove.contains(hrefValue.toString().substring(1)))
						propertiesToRemove.add(property);
				}
			}
			feature.getProperties().removeAll(propertiesToRemove);
		}
	}

	private boolean isPropertySecured(Feature feature, Property property) {
		QName featureName = feature.getName();
		if (isPlan(featureName) && new QName(featureName.getNamespaceURI(), "texte").equals(property.getName()))
			return true;
		return false;
	}

	private void createAndAddTextFeature(XPlanVersion version, XPlanType type, AppSchema schema, String namespaceUri,
			Text text, String gmlid, List<Feature> featuresToAdd, Feature oldTextFeature) {
		QName textFeatureTypeName = getTextAbschnittName(version, type, namespaceUri, oldTextFeature);
		FeatureType textFeatureType = schema.getFeatureType(textFeatureTypeName);
		List<Property> props = new ArrayList<>();
		addProperty(props, createKeyProperty(namespaceUri, text.getKey()));
		addProperty(props, createBasisProperty(namespaceUri, text.getBasis()));
		addProperty(props, createTextProperty(namespaceUri, text.getText()));

		QName refPropName = new QName(namespaceUri, "refText");
		createAndAddExterneReferenz(schema, text, textFeatureType, props, refPropName, "XP_ExterneReferenz", null);

		if (text.getRechtscharakter() != null
				&& (XPLAN_50.equals(version) || XPLAN_51.equals(version) || XPLAN_52.equals(version)
						|| XPLAN_53.equals(version) || XPLAN_54.equals(version) || XPLAN_60.equals(version))) {
			addProperty(props, createRechtscharakterProperty(namespaceUri, text.getRechtscharakter().getCode()));
		}
		if (props.isEmpty())
			return;
		featuresToAdd.add(textFeatureType.newFeature(gmlid, props, null));
	}

	private void createAndAddRasterBasisFeature(XPlanVersion version, FeatureCollection planToEdit, AppSchema schema,
			String namespaceUri, RasterBasis rasterBasis, String gmlid, List<Feature> featuresToAdd,
			List<Feature> featuresToRemove) {
		QName rasterBasisFeatureTypeName = getRasterBasisElementName(version, namespaceUri);
		FeatureType rasterBasisFeatureType = schema.getFeatureType(rasterBasisFeatureTypeName);
		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
		List<RasterReference> scans = collectRasterReferencesByType(rasterReferences, SCAN);
		List<RasterReference> texts = collectRasterReferencesByType(rasterReferences, TEXT);
		List<RasterReference> legends = collectRasterReferencesByType(rasterReferences, LEGEND);

		List<Property> props = new ArrayList<>();

		if (!scans.isEmpty()) {
			for (RasterReference scan : scans) {
				QName refPropName = new QName(namespaceUri, "refScan");
				String externeReferenzFeatureTypeName = "XP_ExterneReferenz";
				createAndAddExterneReferenz(schema, scan, rasterBasisFeatureType, props, refPropName,
						externeReferenzFeatureTypeName, null);
				if (scan.getFeatureId() != null) {
					QName externeReferenz = new QName(namespaceUri, externeReferenzFeatureTypeName);
					Feature oldReferenceFeature = detectFeatureById(planToEdit, externeReferenz, scan.getFeatureId());
					featuresToRemove.add(oldReferenceFeature);
				}
			}
		}
		if (!texts.isEmpty()) {
			createAndAddRasterBasisTexts(planToEdit, schema, namespaceUri, featuresToRemove, rasterBasisFeatureType,
					texts, props);
		}

		if (!legends.isEmpty()) {
			createAndAddRasterBasisLegends(planToEdit, schema, namespaceUri, featuresToRemove, rasterBasisFeatureType,
					legends, props);
		}
		if (!props.isEmpty())
			featuresToAdd.add(rasterBasisFeatureType.newFeature(gmlid, props, null));
	}

	private void createAndAddRasterBasisTexts(FeatureCollection planToEdit, AppSchema schema, String namespaceUri,
			List<Feature> featuresToRemove, FeatureType rasterBasisFeatureType, List<RasterReference> texts,
			List<Property> props) {
		QName refPropName = new QName(namespaceUri, "refText");
		RasterReference text = texts.get(0);
		createAndAddExterneReferenz(schema, text, rasterBasisFeatureType, props, refPropName, "XP_ExterneReferenz",
				null);
		if (text.getFeatureId() != null) {
			QName externeReferenz = new QName(namespaceUri, "XP_ExterneReferenz");
			Feature oldReferenceFeature = detectFeatureById(planToEdit, externeReferenz, text.getFeatureId());
			featuresToRemove.add(oldReferenceFeature);
		}
	}

	private void createAndAddRasterBasisLegends(FeatureCollection planToEdit, AppSchema schema, String namespaceUri,
			List<Feature> featuresToRemove, FeatureType rasterBasisFeatureType, List<RasterReference> legends,
			List<Property> props) {
		for (RasterReference legend : legends) {
			QName refPropName = new QName(namespaceUri, "refLegende");
			createAndAddExterneReferenz(schema, legend, rasterBasisFeatureType, props, refPropName,
					"XP_ExterneReferenz", null);
			if (legend.getFeatureId() != null) {
				QName externeReferenz = new QName(namespaceUri, "XP_ExterneReferenz");
				Feature oldReferenceFeature = detectFeatureById(planToEdit, externeReferenz, legend.getFeatureId());
				featuresToRemove.add(oldReferenceFeature);
			}
		}
	}

	private void createAndAddExterneReferenz(AppSchema schema, AbstractReference reference, FeatureType featureType,
			List<Property> props, QName refPropName, String externeReferenzElementName, String spezExterneReferenzTyp) {
		GenericProperty refProperty = createExterneReferenzProperty_XPlan41_XPlan50(schema, featureType, refPropName,
				reference, externeReferenzElementName, spezExterneReferenzTyp);
		addProperty(props, refProperty);
	}

	private GenericProperty createExterneReferenzProperty_XPlan41_XPlan50(AppSchema schema, FeatureType featureType,
			QName propName, AbstractReference reference, String externeReferenzElementName,
			String spezExterneReferenzTyp) {
		String namespaceUri = featureType.getName().getNamespaceURI();
		PropertyType propType = featureType.getPropertyDeclaration(propName);
		GenericProperty newProperty = new GenericProperty(propType, null);

		List<TypedObjectNode> subElementChilds = new ArrayList<>();
		add(subElementChilds, createGeoReferenzProperty(namespaceUri, reference.getGeoReference()));
		add(subElementChilds, createGeorefMimeTypeProperty(namespaceUri, reference.getGeorefMimeType()));
		add(subElementChilds, createArtProperty(namespaceUri, reference.getArt()));
		add(subElementChilds, createInformationssystemURLProperty(namespaceUri, reference.getInformationssystemURL()));
		add(subElementChilds, createReferenzNameProperty(namespaceUri, reference.getReferenzName()));
		add(subElementChilds, createReferenzProperty(namespaceUri, reference.getReference()));
		add(subElementChilds, createReferenzMimeTypeProperty(namespaceUri, reference.getReferenzMimeType()));
		add(subElementChilds, createBeschreibungProperty(namespaceUri, reference.getBeschreibung()));
		add(subElementChilds, createDatumProperty(namespaceUri, reference.getDatum()));
		if (subElementChilds.isEmpty())
			return null;
		if (spezExterneReferenzTyp != null) {
			add(subElementChilds, createReferenzTypProperty(namespaceUri, spezExterneReferenzTyp));
		}

		QName subElementName = new QName(namespaceUri, externeReferenzElementName);
		XSElementDeclaration subElementType = schema.getGMLSchema().getElementDecl(subElementName);
		GenericXMLElement gxe = new GenericXMLElement(subElementName, subElementType, null, subElementChilds);

		newProperty.setChildren(asList(gxe));
		return newProperty;
	}

	private GenericProperty createVerbundenerPlanProperty(XPlanVersion version, Feature feature, AppSchema schema,
			Change change, QName propName) {
		String namespaceUri = feature.getName().getNamespaceURI();
		PropertyType propType = feature.getType().getPropertyDeclaration(propName);
		GenericProperty newProperty = new GenericProperty(propType, null);

		List<TypedObjectNode> subElementChilds = new ArrayList<TypedObjectNode>();
		add(subElementChilds, createPlanNameProperty(namespaceUri, change.getPlanName()));
		if (XPLAN_60.equals(version)) {
			add(subElementChilds, createAenderungsArtProperty(namespaceUri, change.getLegalNatureCode()));
		}
		else {
			add(subElementChilds, createRechtscharakterProperty(namespaceUri, change.getLegalNatureCode()));
		}
		add(subElementChilds, createNumberProperty(namespaceUri, change.getNumber()));
		if (subElementChilds.isEmpty())
			return null;

		QName subElementName = new QName(namespaceUri, "XP_VerbundenerPlan");
		XSElementDeclaration subElementType = schema.getGMLSchema().getElementDecl(subElementName);
		GenericXMLElement gxe = new GenericXMLElement(subElementName, subElementType, null, subElementChilds);

		newProperty.setChildren(asList(gxe));
		return newProperty;
	}

	private GenericProperty createPlanNameProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "planName");
		return createProperty(propName, value, 1, 1);
	}

	private GenericProperty createAenderungsArtProperty(String namespaceUri, int value) {
		QName propName = new QName(namespaceUri, "aenderungsArt");
		return createProperty(propName, value, 1, 1);
	}

	private GenericProperty createRechtscharakterProperty(String namespaceUri, int value) {
		QName propName = new QName(namespaceUri, "rechtscharakter");
		return createProperty(propName, value, 1, 1);
	}

	private GenericProperty createNumberProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "nummer");
		return createProperty(propName, value, 1, 1);
	}

	private GenericProperty createArtProperty(String namespaceUri, ExterneReferenzArt value) {
		if (value != null) {
			QName propName = new QName(namespaceUri, "art");
			return createProperty(propName, value.getCode(), 0, 1);
		}
		return null;
	}

	private GenericProperty createInformationssystemURLProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "informationssystemURL");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createReferenzNameProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "referenzName");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createReferenzProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "referenzURL");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createReferenzMimeTypeProperty(String namespaceUri, MimeTypes value) {
		if (value != null) {
			QName propName = new QName(namespaceUri, "referenzMimeType");
			return createProperty(propName, value.getCode(), 0, 1);
		}
		return null;
	}

	private GenericProperty createGeoReferenzProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "georefURL");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createGeorefMimeTypeProperty(String namespaceUri, MimeTypes value) {
		if (value != null) {
			QName propName = new QName(namespaceUri, "georefMimeType");
			return createProperty(propName, value.getCode(), 0, 1);
		}
		return null;
	}

	private GenericProperty createBeschreibungProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "beschreibung");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createDatumProperty(String namespaceUri, Date value) {
		QName propName = new QName(namespaceUri, "datum");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createKeyProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "schluessel");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createBasisProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "gesetzlicheGrundlage");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createTextProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "text");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createReferenzTypProperty(String namespaceUri, String value) {
		QName propName = new QName(namespaceUri, "typ");
		return createProperty(propName, value, 0, 1);
	}

	private GenericProperty createProperty(QName propName, String value, int minOccurs, int maxOccurs) {
		if (value == null || "".equals(value))
			return null;
		CustomPropertyType planNameType = new CustomPropertyType(propName, minOccurs, maxOccurs, null, null);
		return new GenericProperty(planNameType, new PrimitiveValue(value));
	}

	private GenericProperty createProperty(QName propName, int value, int minOccurs, int maxOccurs) {
		if (value < 0)
			return null;
		CustomPropertyType planNameType = new CustomPropertyType(propName, minOccurs, maxOccurs, null, null);
		return new GenericProperty(planNameType, new PrimitiveValue(Integer.toString(value)));
	}

	private GenericProperty createProperty(QName propName, Date value, int minOccurs, int maxOccurs) {
		if (value == null)
			return null;
		org.deegree.commons.tom.datetime.Date date = new org.deegree.commons.tom.datetime.Date(value, null);
		CustomPropertyType type = new CustomPropertyType(propName, minOccurs, maxOccurs, null, null);
		return new GenericProperty(type, new PrimitiveValue(date));
	}

	private Property createSimpleProperty(Feature feature, QName propName, String newValue) throws EditException {
		SimplePropertyType propType = (SimplePropertyType) feature.getType().getPropertyDeclaration(propName);
		if (propType == null) {
			throw new EditException("Could not find property declaration of property " + propName + ".");
		}
		return new SimpleProperty(propType, newValue);
	}

	private Property createPropertyWithHrefAttribute(GmlDocumentIdContext context, FeatureType featureType,
			QName propName, String attributeValue) throws EditException {
		FeaturePropertyType propType = (FeaturePropertyType) featureType.getPropertyDeclaration(propName);
		if (propType == null) {
			throw new EditException("Could not find property declaration of property " + propName + ".");
		}
		Map<QName, PrimitiveValue> attributes = new HashMap<>();
		FeatureReference featureReference = new FeatureReference(context, "#" + attributeValue, null);
		attributes.put(XLINK_HREF_ATTRIBUTE, new PrimitiveValue("#" + attributeValue));
		List<TypedObjectNode> childs = new ArrayList<TypedObjectNode>();
		childs.add(featureReference);
		return new GenericProperty(propType, null, null, attributes, childs);
	}

	private List<String> parseReferencedTextFeatureIds(FeatureCollection planToEdit) {
		List<String> previouslyReferencedTextsFeatureIds = new ArrayList<>();
		for (Feature feature : planToEdit) {
			QName featureName = feature.getName();
			if (isPlan(featureName)) {
				QName propName = new QName(featureName.getNamespaceURI(), "texte");
				List<Property> properties = feature.getProperties(propName);
				for (Property property : properties) {
					PrimitiveValue hrefValue = property.getAttributes().get(XLINK_HREF_ATTRIBUTE);
					if (hrefValue != null)
						previouslyReferencedTextsFeatureIds.add(hrefValue.toString().substring(1));
				}
			}
		}
		return previouslyReferencedTextsFeatureIds;
	}

	private String parseReferencedRasterBasisFeatureId(Feature bereichFeature) {
		QName propName = new QName(bereichFeature.getName().getNamespaceURI(), "rasterBasis");
		List<Property> properties = bereichFeature.getProperties(propName);
		for (Property property : properties) {
			PrimitiveValue hrefValue = property.getAttributes().get(XLINK_HREF_ATTRIBUTE);
			if (hrefValue != null)
				return hrefValue.toString().substring(1);
		}
		return null;
	}

	private void add(List<TypedObjectNode> subElementChilds, Property propertyToAdd) {
		if (propertyToAdd != null)
			subElementChilds.add(propertyToAdd);
	}

	private boolean addProperty(List<Property> properties, Property propertyToAdd) {
		if (propertyToAdd != null) {
			properties.add(propertyToAdd);
			return true;
		}
		return false;
	}

	private void addOrReplaceProperty(XPlanVersion version, Feature feature, QName propName, Property property) {
		removeProperties(feature, propName);
		if (property != null) {
			int index = findIndex(version, feature, propName);
			feature.getProperties().add(index, property);
		}
	}

	private void addOrReplaceProperties(XPlanVersion version, Feature feature, QName propName,
			List<Property> properties) {
		removeProperties(feature, propName);
		if (properties != null && !properties.isEmpty()) {
			int index = findIndex(version, feature, propName);
			for (Property property : properties) {
				feature.getProperties().add(index, property);
			}
		}
	}

	private void removeProperties(Feature feature, QName propName) {
		List<Property> properties = feature.getProperties(propName);
		feature.getProperties().removeAll(properties);
	}

	private int findIndex(XPlanVersion version, Feature feature, QName propNameToFindIndexFor) {
		List<Property> properties = feature.getProperties();
		List<PropertyType> propertyTypes = feature.getType().getPropertyDeclarations();
		int index = calculateNumberOfPropertiesToSkip(properties, version);
		for (; index < properties.size(); index++) {
			Property actualProp = properties.get(index);
			boolean actualPropertyAfter = isActualPropertyAfterPropertyToFindIndexFor(propertyTypes, actualProp,
					propNameToFindIndexFor);
			if (actualPropertyAfter)
				return index;
		}
		return index;
	}

	private QName getBereichFeatureTypeName(XPlanType type, String namespaceUri) {
		switch (type) {
			case BP_Plan:
				return new QName(namespaceUri, "BP_Bereich");
			case FP_Plan:
				return new QName(namespaceUri, "FP_Bereich");
			case RP_Plan:
				return new QName(namespaceUri, "RP_Bereich");
			case LP_Plan:
				return new QName(namespaceUri, "LP_Bereich");
			case SO_Plan:
				return new QName(namespaceUri, "SO_Bereich");
		}
		return new QName(namespaceUri, "XP_Bereich");
	}

	private QName getTextAbschnittName(XPlanVersion version, XPlanType type, String namespaceUri,
			Feature oldTextFeature) {
		if (oldTextFeature != null)
			return oldTextFeature.getName();
		if (XPLAN_50.equals(version) || XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version)
				|| XPLAN_54.equals(version)) {
			switch (type) {
				case BP_Plan:
					return new QName(namespaceUri, "BP_TextAbschnitt");
				case FP_Plan:
					return new QName(namespaceUri, "FP_TextAbschnitt");
				case RP_Plan:
					return new QName(namespaceUri, "RP_TextAbschnitt");
				case LP_Plan:
					return new QName(namespaceUri, "LP_TextAbschnitt");
				case SO_Plan:
					return new QName(namespaceUri, "SO_TextAbschnitt");
			}
		}
		return new QName(namespaceUri, "XP_TextAbschnitt");
	}

	private QName getRasterBasisElementName(XPlanVersion version, String namespaceUri) {
		if (XPLAN_50.equals(version) || XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version)
				|| XPLAN_54.equals(version))
			return new QName(namespaceUri, "XP_Rasterdarstellung");
		return new QName(namespaceUri, "XP_RasterplanBasis");
	}

	private String generateGmlId(QName propName) {
		String prefix = SYN_FEATURETYPE_PREFIX + propName.getLocalPart() + "_";
		String uuid = UUID.randomUUID().toString();
		return prefix + uuid;
	}

	private List<TypedObjectNode> asList(GenericXMLElement child) {
		List<TypedObjectNode> childs = new ArrayList<>();
		childs.add(child);
		return childs;
	}

	private boolean isActualPropertyAfterPropertyToFindIndexFor(List<PropertyType> propertyTypes, Property actualProp,
			QName propNameToFindIndexFor) {
		boolean foundActual = false;
		boolean foundPropToFindIndexFor = false;
		for (PropertyType propertyType : propertyTypes) {
			if (propertyType.getName().equals(actualProp.getName()))
				foundActual = true;
			if (propertyType.getName().equals(propNameToFindIndexFor))
				foundPropToFindIndexFor = true;
			if (foundPropToFindIndexFor && !foundActual)
				return true;
		}
		return false;
	}

	private int calculateNumberOfPropertiesToSkip(List<Property> properties, XPlanVersion version) {
		int numberOfPropertiesToSkip = 0;
		for (Property property : properties) {
			if (property.getName().getNamespaceURI().equals(version.getGmlVersion().getNamespace()))
				numberOfPropertiesToSkip++;
		}
		return numberOfPropertiesToSkip;
	}

	private boolean isPlan(QName featureName) {
		return featureName.getLocalPart().matches("(BP|FP|LP|RP|SO)_Plan");
	}

	private boolean isBereich(QName featureName) {
		return featureName.getLocalPart().matches("(BP|FP|LP|RP|SO)_Bereich");
	}

	private String retrieveNummer(Feature bereichFeature) {
		List<Property> nummerProps = bereichFeature
			.getProperties(new QName(bereichFeature.getName().getNamespaceURI(), "nummer"));
		if (nummerProps != null && !nummerProps.isEmpty()) {
			TypedObjectNode nummerValue = nummerProps.get(0).getValue();
			if (nummerValue instanceof PrimitiveValue) {
				return ((PrimitiveValue) nummerValue).getAsText().trim();
			}
		}
		return null;
	}

	private List<RasterReference> collectRasterReferencesByType(List<RasterReference> rasterReferences,
			RasterReferenceType type) {
		return rasterReferences.stream()
			.filter(rasterReference -> type.equals(rasterReference.getType()))
			.collect(Collectors.toList());
	}

	private List<RasterReference> countRasterReferences(List<RasterBasis> rasterBasisOfBereich) {
		return rasterBasisOfBereich.stream()
			.flatMap(rb -> rb.getRasterReferences().stream())
			.collect(Collectors.toList());
	}

	private void checkVersionAndType(XPlanVersion version, XPlanType type) {
		switch (type) {
			case BP_Plan:
				if (!XPLAN_41.equals(version) && !XPLAN_50.equals(version) && !XPLAN_51.equals(version)
						&& !XPLAN_52.equals(version) && !XPLAN_53.equals(version) && !XPLAN_54.equals(version)
						&& !XPLAN_60.equals(version))
					throw new IllegalArgumentException("Unsupported Version: " + version);
				break;
			case SO_Plan:
			case FP_Plan:
			case RP_Plan:
				if (!XPLAN_50.equals(version) && !XPLAN_51.equals(version) && !XPLAN_52.equals(version)
						&& !XPLAN_53.equals(version) && !XPLAN_54.equals(version) && !XPLAN_60.equals(version))
					throw new IllegalArgumentException("Unsupported Version: " + version);
				break;
			case LP_Plan:
				if (!XPLAN_60.equals(version))
					throw new IllegalArgumentException("Unsupported Version: " + version);
				break;
		}
	}

}
