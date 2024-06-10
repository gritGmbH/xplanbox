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
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.datetime.Temporal;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.BaseType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.getByCode;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;

/**
 * Factory to parse {@link XPlanToEdit}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanToEditFactory {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanToEditFactory.class);

	/**
	 * Parses an {@link XPlanToEdit} from the passed {@link FeatureCollection}.
	 * @param version of the plan, never <code>null</code>
	 * @param type of the plan, never <code>null</code>
	 * @param featureCollection to parse the editable values from, never <code>null</code>
	 * @return the xPlanToEdit, never <code>null</code>
	 */
	public XPlanToEdit createXPlanToEdit(XPlanVersion version, XPlanType type, FeatureCollection featureCollection)
			throws EditException {
		return createXPlanToEdit(version.name(), type.name(), featureCollection);
	}

	/**
	 * Parses an {@link XPlanToEdit} from the passed {@link FeatureCollection}.
	 * @param xPlan used to extract some metadata, never <code>null</code>
	 * @param featureCollection to parse the editable values from, never <code>null</code>
	 * @return the xPlanToEdit, never <code>null</code>
	 */
	public XPlanToEdit createXPlanToEdit(XPlan xPlan, FeatureCollection featureCollection) throws EditException {
		return createXPlanToEdit(xPlan.getVersion(), xPlan.getType(), featureCollection);
	}

	private XPlanToEdit createXPlanToEdit(String version, String type, FeatureCollection featureCollection)
			throws EditException {
		Iterator<Feature> iterator = featureCollection.iterator();
		XPlanToEdit xPlanToEdit = new XPlanToEdit();
		while (iterator.hasNext()) {
			Feature feature = iterator.next();
			String nameOfFeature = feature.getName().getLocalPart();
			if (nameOfFeature.matches("(BP|FP|LP|RP|SO)_Plan")) {
				parsePlan(version, type, feature, xPlanToEdit);
			}
			else if (nameOfFeature.matches("(BP|FP|LP|RP|SO)_Bereich")) {
				xPlanToEdit.setHasBereich(true);
				parseBereich(feature, xPlanToEdit, version);
			}
		}
		return xPlanToEdit;
	}

	private void parsePlan(String version, String type, Feature feature, XPlanToEdit xPlanToEdit) throws EditException {
		LOG.debug("Parse properties from Plan");
		BaseData baseData = xPlanToEdit.getBaseData();
		for (Property property : feature.getProperties()) {
			String propertyName = property.getName().getLocalPart();
			TypedObjectNode propertyValue = property.getValue();
			if ("name".equals(propertyName)) {
				baseData.setPlanName(asString(propertyValue));
			}
			else if ("beschreibung".equals(propertyName)) {
				baseData.setDescription(asString(propertyValue));
			}
			else if ("technHerstellDatum".equals(propertyName)) {
				baseData.setCreationDate(asDate(propertyValue));
			}
			else if ("untergangsDatum".equals(propertyName)) {
				baseData.setLossDate(asDate(propertyValue));
			}
			else if ("planArt".equals(propertyName)) {
				baseData.setPlanTypeCode(asInteger(propertyValue));
			}
			else if ("sonstPlanArt".equals(propertyName)) {
				try {
					int sonstPlanArtValue = Integer.parseInt(propertyValue.toString());
					baseData.setOtherPlanTypeCode(sonstPlanArtValue);
				}
				catch (NumberFormatException e) {
					LOG.warn("sonstPlanArt is not an integer value. Currently only integer values are supported.");
					baseData.setOtherPlanTypeCode(-1);
				}
			}
			else if ("verfahren".equals(propertyName)) {
				baseData.setMethodCode(asInteger(propertyValue));
			}
			else if ("rechtsstand".equals(propertyName)) {
				baseData.setLegislationStatusCode(asInteger(propertyValue));
			}
			else if ("rechtsverordnungsDatum".equals(propertyName)) {
				baseData.setRegulationDate(asDate(propertyValue));
			}
			else if ("aendert".equals(propertyName) || "aendertPlan".equals(propertyName)) {
				parseChange(property, xPlanToEdit, CHANGES);
			}
			else if ("wurdeGeaendertVon".equals(propertyName) || "wurdeGeaendertVonPlan".equals(propertyName)) {
				parseChange(property, xPlanToEdit, CHANGED_BY);
			}
			else if ("refBegruendung".equals(propertyName)) {
				parseReference(property, xPlanToEdit, BEGRUENDUNG);
			}
			else if ("refRechtsplan".equals(propertyName)) {
				parseReference(property, xPlanToEdit, RECHTSPLAN);
			}
			else if ("refGruenordnungsplan".equals(propertyName)) {
				parseReference(property, xPlanToEdit, GRUENORDNUNGSPLAN);
			}
			else if ("externeReferenz".equals(propertyName)) {
				parseExterneReference(property, xPlanToEdit);
			}
			else if ("texte".equals(propertyName)) {
				parseTextReference(version, type, property, xPlanToEdit);
			}
		}
	}

	private void parseBereich(Feature feature, XPlanToEdit xPlanToEdit, String version) {
		LOG.debug("Parse properties from Bereich");
		String bereichNummer = getPropertyByName(feature, "nummer");
		RasterBasis rasterBasis = createOrGetRasterBasis(xPlanToEdit, bereichNummer, null);
		for (Property property : feature.getProperties()) {
			String propertyName = property.getName().getLocalPart();
			if ("rasterBasis".equals(propertyName)) {
				parseRasterBasis(bereichNummer, property, xPlanToEdit, rasterBasis, version);
			}
			else if ("refScan".equals(propertyName)) {
				parseRasterBasisRefScan(bereichNummer, rasterBasis, property);
			}
		}
	}

	private void parseRasterBasisRefScan(String bereichNummer, RasterBasis rasterBasis, Property property) {
		if (rasterBasis == null) {
			rasterBasis = new RasterBasis();
		}
		RasterReference rasterReference = parseRasterReference(bereichNummer, property, SCAN);
		rasterBasis.addRasterReference(rasterReference);
	}

	private void parseRasterBasis(String bereichNummer, Property property, XPlanToEdit xPlanToEdit,
			RasterBasis rasterBasis, String version) {
		TypedObjectNode propertyValue = property.getValue();
		if (propertyValue instanceof FeatureReference) {
			parseRasterWithReferences(bereichNummer, propertyValue, xPlanToEdit, rasterBasis, version);
		}
	}

	private void parseRasterWithReferences(String bereichId, TypedObjectNode propertyValue, XPlanToEdit xPlanToEdit,
			RasterBasis rasterBasis, String version) {
		Feature referencedObject = ((FeatureReference) propertyValue).getReferencedObject();
		String featureId = referencedObject.getId();
		rasterBasis.setFeatureId(featureId);
		for (Property prop : referencedObject.getProperties()) {
			if (hasChilds(prop)) {
				parseRasterWithReferences(bereichId, xPlanToEdit, rasterBasis, version, prop);
			}
		}
	}

	private void parseRasterWithReferences(String bereichId, XPlanToEdit xPlanToEdit, RasterBasis rasterBasis,
			String version, Property prop) {
		String propName = prop.getName().getLocalPart();
		if ("refLegende".equals(propName)) {
			RasterReference rasterReference = parseRasterReference(bereichId, prop, LEGEND);
			if (isXPlan51OrHigher(version)) {
				Reference reference = new Reference(rasterReference.getReference(), rasterReference.getGeoReference(),
						ReferenceType.LEGENDE);
				copyReference(rasterReference, reference);
				xPlanToEdit.addReference(reference);
			}
			else {
				rasterBasis.addRasterReference(rasterReference);
			}
		}
		else if ("refScan".equals(propName)) {
			RasterReference rasterReference = parseRasterReference(bereichId, prop, SCAN);
			rasterBasis.addRasterReference(rasterReference);
		}
		else if ("refText".equals(propName)) {
			RasterReference rasterReference = parseRasterReference(bereichId, prop, TEXT);
			if (isXPlan51OrHigher(version)) {
				Text text = new Text(null, rasterReference.getReference());
				copyReference(rasterReference, text);
				xPlanToEdit.addText(text);
			}
			else {
				rasterBasis.addRasterReference(rasterReference);
			}
		}
	}

	private RasterBasis createOrGetRasterBasis(XPlanToEdit xPlanToEdit, String bereichNummer, String featureId) {
		Optional<RasterBasis> rasterBasisWithBereichId = xPlanToEdit.getRasterBasis()
			.stream()
			.filter(rasterbasis -> bereichNummer.equals(rasterbasis.getBereichNummer()))
			.findFirst();
		if (rasterBasisWithBereichId.isPresent())
			return rasterBasisWithBereichId.get();
		RasterBasis rasterBasis = new RasterBasis(featureId);
		rasterBasis.setBereichNummer(bereichNummer);
		xPlanToEdit.addRasterBasis(rasterBasis);
		return rasterBasis;
	}

	private void copyReference(RasterReference rasterReference, AbstractReference reference) {
		reference.setGeorefMimeType(rasterReference.getGeorefMimeType());
		reference.setGeoReference(rasterReference.getGeoReference());
		reference.setReferenzMimeType(rasterReference.getReferenzMimeType());
		reference.setArt(rasterReference.getArt());
		reference.setReferenzName(rasterReference.getReferenzName());
		reference.setBeschreibung(rasterReference.getBeschreibung());
		reference.setDatum(rasterReference.getDatum());
		reference.setInformationssystemURL(rasterReference.getInformationssystemURL());
	}

	private RasterReference parseRasterReference(String bereichNummer, Property prop,
			RasterReferenceType rasterReferenceType) {
		RasterReference rasterReference = new RasterReference();
		rasterReference.setBereichNummer(bereichNummer);
		rasterReference.setType(rasterReferenceType);
		List<TypedObjectNode> children = prop.getChildren();
		String featureId = parseReference(children, rasterReference);
		rasterReference.setFeatureId(featureId);
		if (children.get(0) instanceof GenericXMLElement) {
			GenericXMLElement genericXmlElement = (GenericXMLElement) children.get(0);
			parseRasterReference(rasterReference, genericXmlElement.getChildren());
		}
		else if (children.get(0) instanceof FeatureReference) {
			Feature referencedObject = ((FeatureReference) children.get(0)).getReferencedObject();
			parseRasterReference(rasterReference, referencedObject.getProperties());
		}
		return rasterReference;
	}

	private void parseRasterReference(RasterReference rasterReference, List<? extends TypedObjectNode> children) {
		for (TypedObjectNode child : children) {
			if (child instanceof SimpleProperty) {
				SimpleProperty childProperty = (SimpleProperty) child;
				String propName = childProperty.getName().getLocalPart();
				PrimitiveValue value = childProperty.getValue();
				parseRasterReference(rasterReference, propName, value);
			}
			else if (child instanceof GenericXMLElement) {
				GenericXMLElement childProperty = (GenericXMLElement) child;
				String propName = childProperty.getName().getLocalPart();
				PrimitiveValue value = childProperty.getValue();
				parseRasterReference(rasterReference, propName, value);
			}
		}
	}

	private void parseRasterReference(RasterReference rasterReference, String propName, PrimitiveValue value) {
		if ("art".equals(propName)) {
			rasterReference.setArt(ExterneReferenzArt.getByCode(asString(value)));
		}
		else if ("informationssystemURL".equals(propName)) {
			rasterReference.setInformationssystemURL(asString(value));
		}
		else if ("referenzName".equals(propName)) {
			rasterReference.setReferenzName(asString(value));
		}
		else if ("beschreibung".equals(propName)) {
			rasterReference.setBeschreibung(asString(value));
		}
		else if ("datum".equals(propName)) {
			rasterReference.setDatum(asDate(value));
		}
		else if ("referenzMimeType".equals(propName)) {
			rasterReference.setReferenzMimeType(getByCode(asString(value)));
		}
		else if ("georefMimeType".equals(propName)) {
			rasterReference.setGeorefMimeType(getByCode(asString(value)));
		}
	}

	private void parseTextReference(String version, String type, Property property, XPlanToEdit xPlanToEdit)
			throws EditException {
		TypedObjectNode propertyValue = property.getValue();
		if (propertyValue instanceof FeatureReference) {
			Feature referencedObject = ((FeatureReference) propertyValue).getReferencedObject();
			String featureId = referencedObject.getId();
			Text text = new Text(featureId);
			for (Property prop : referencedObject.getProperties()) {
				String propName = prop.getName().getLocalPart();
				TypedObjectNode propValue = prop.getValue();
				if ("schluessel".equals(propName)) {
					text.setKey(asString(propValue));
				}
				else if ("gesetzlicheGrundlage".equals(propName)) {
					text.setBasis(asString(propValue));
				}
				else if ("text".equals(propName)) {
					text.setText(asString(propValue));
				}
				else if ("refText".equals(propName)) {
					parseReference(prop.getChildren(), text);
				}
				else if ("rechtscharakter".equals(propName)) {
					text.setRechtscharakter(TextRechtscharacterType.fromCode(asInteger(propValue), version, type));
				}
			}
			xPlanToEdit.addText(text);
		}
	}

	private void parseChange(Property property, XPlanToEdit xPlanToEdit, ChangeType changeType) throws EditException {
		if (property instanceof GenericProperty) {
			List<TypedObjectNode> children = property.getChildren();
			if (children.size() == 1 && children.get(0) instanceof GenericXMLElement) {
				Change change = new Change();
				change.setType(changeType);
				GenericXMLElement genericXmlElement = (GenericXMLElement) children.get(0);
				for (TypedObjectNode child : genericXmlElement.getChildren()) {
					if (child instanceof GenericXMLElement) {
						GenericXMLElement childProperty = (GenericXMLElement) child;
						if ("planName".equals(childProperty.getName().getLocalPart())) {
							change.setPlanName(asString(childProperty.getValue()));
						}
						else if ("rechtscharakter".equals(childProperty.getName().getLocalPart())
								|| "aenderungsArt".equals(childProperty.getName().getLocalPart())) {
							change.setLegalNatureCode(asInteger(childProperty.getValue()));
						}
						else if ("nummer".equals(childProperty.getName().getLocalPart())) {
							change.setNumber(asString(childProperty.getValue()));
						}
					}
				}
				xPlanToEdit.addChange(change);
			}
			else {
				LOG.warn("Could not parse property " + property);
			}
		}
		else if (property instanceof SimpleProperty) {
			Change change = new Change();
			change.setType(changeType);
			change.setPlanName(asString(property.getValue()));
			xPlanToEdit.addChange(change);
		}
		else {
			LOG.warn("Could not parse property " + property);
		}
	}

	private void parseExterneReference(Property property, XPlanToEdit xPlanToEdit) {
		ReferenceType referenceType = detectType(property);
		if (referenceType != null)
			parseReference(property, xPlanToEdit, referenceType);
	}

	private void parseReference(Property property, XPlanToEdit xPlanToEdit, ReferenceType referenceType) {
		List<TypedObjectNode> children = property.getChildren();
		if (children.size() == 1 && children.get(0) instanceof GenericXMLElement) {
			Reference reference = new Reference();
			reference.setType(referenceType);
			parseReference(children, reference);
			xPlanToEdit.addReference(reference);
		}
		else if (children.size() == 1 && children.get(0) instanceof FeatureReference) {
			Feature referencedObject = ((FeatureReference) children.get(0)).getReferencedObject();
			Reference reference = new Reference();
			reference.setType(referenceType);
			parseReferenceProperties(referencedObject.getProperties(), reference);
			xPlanToEdit.addReference(reference);
		}
		else {
			LOG.warn("Could not parse property " + property);
		}
	}

	private String parseReference(List<TypedObjectNode> children, AbstractReference reference) {
		if (children.get(0) instanceof GenericXMLElement) {
			GenericXMLElement genericXmlElement = (GenericXMLElement) children.get(0);
			parseReferenceProperties(genericXmlElement.getChildren(), reference);
		}
		else if (children.get(0) instanceof FeatureReference) {
			Feature referencedObject = ((FeatureReference) children.get(0)).getReferencedObject();
			parseReferenceProperties(referencedObject.getProperties(), reference);
			return referencedObject.getId();
		}
		return null;
	}

	private void parseReferenceProperties(List<? extends TypedObjectNode> children, AbstractReference reference) {
		for (TypedObjectNode child : children) {
			if (child instanceof GenericXMLElement) {
				GenericXMLElement childProperty = (GenericXMLElement) child;
				parseReferenceProperty(reference, childProperty.getName(), childProperty.getValue());
			}
			else if (child instanceof SimpleProperty) {
				SimpleProperty childProperty = (SimpleProperty) child;
				parseReferenceProperty(reference, childProperty.getName(), childProperty.getValue());
			}
		}
	}

	private void parseReferenceProperty(AbstractReference reference, QName name, PrimitiveValue value) {
		if ("georefURL".equals(name.getLocalPart())) {
			reference.setGeoReference(asString(value));
		}
		else if ("georefMimeType".equals(name.getLocalPart())) {
			reference.setGeorefMimeType(getByCode(asString(value)));
		}
		else if ("art".equals(name.getLocalPart())) {
			reference.setArt(ExterneReferenzArt.getByCode(asString(value)));
		}
		else if ("informationssystemURL".equals(name.getLocalPart())) {
			reference.setInformationssystemURL(asString(value));
		}
		else if ("referenzName".equals(name.getLocalPart())) {
			reference.setReferenzName(asString(value));
		}
		else if ("referenzURL".equals(name.getLocalPart())) {
			reference.setReference(asString(value));
		}
		else if ("referenzMimeType".equals(name.getLocalPart())) {
			reference.setReferenzMimeType(getByCode(asString(value)));
		}
		else if ("beschreibung".equals(name.getLocalPart())) {
			reference.setBeschreibung(asString(value));
		}
		else if ("datum".equals(name.getLocalPart())) {
			reference.setDatum(asDate(value));
		}
	}

	private ReferenceType detectType(Property property) {
		List<TypedObjectNode> children = property.getChildren();
		if (children.size() == 1 && children.get(0) instanceof GenericXMLElement) {
			GenericXMLElement genericXmlElement = (GenericXMLElement) children.get(0);
			return detectType(genericXmlElement.getChildren());
		}
		else if (children.size() == 1 && children.get(0) instanceof FeatureReference) {
			Feature referencedObject = ((FeatureReference) children.get(0)).getReferencedObject();
			return detectType(referencedObject.getProperties());
		}
		return null;
	}

	private ReferenceType detectType(List<? extends TypedObjectNode> children) {
		for (TypedObjectNode child : children) {
			if (child instanceof GenericXMLElement) {
				GenericXMLElement childProperty = (GenericXMLElement) child;
				if ("typ".equals(childProperty.getName().getLocalPart())) {
					String type = asString(childProperty.getValue());
					return ReferenceType.getBySpezExterneReferenceType(type);
				}
			}
		}
		return null;
	}

	private String getPropertyByName(Feature feature, String propName) {
		List<Property> properties = feature.getProperties(new QName(feature.getName().getNamespaceURI(), propName));
		if (!properties.isEmpty()) {
			Property property = properties.get(0);
			return asString(property.getValue());
		}
		return null;
	}

	private String asString(TypedObjectNode value) {
		if (value instanceof PrimitiveValue) {
			return ((PrimitiveValue) value).getAsText().trim();
		}
		return null;
	}

	private int asInteger(TypedObjectNode value) throws EditException {
		String valueAsText = value.toString();
		try {
			return Integer.parseInt(valueAsText);
		}
		catch (NumberFormatException e) {
			throw new EditException("Could not parse " + valueAsText + " as integer.");
		}
	}

	private Date asDate(TypedObjectNode value) {
		if (value instanceof PrimitiveValue) {
			BaseType baseType = ((PrimitiveValue) value).getType().getBaseType();
			if (BaseType.TIME.equals(baseType) || BaseType.DATE.equals(baseType) || BaseType.DATE_TIME.equals(baseType))
				return ((Temporal) ((PrimitiveValue) value).getValue()).getDate();
		}
		return null;
	}

	private boolean isXPlan51OrHigher(String xPlanVersion) {
		XPlanVersion version = XPlanVersion.valueOf(xPlanVersion);
		return XPLAN_51.equals(version) || XPLAN_52.equals(version) || XPLAN_53.equals(version);
	}

	private boolean hasChilds(Property prop) {
		return prop.getChildren().size() > 0;
	}

}
