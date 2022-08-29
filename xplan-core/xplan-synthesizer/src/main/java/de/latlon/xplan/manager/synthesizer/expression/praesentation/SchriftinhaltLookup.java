/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import java.util.List;
import java.util.stream.Stream;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.PRIMITIVE;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;

/**
 * Creates the schriftinhalt dependent on the referenced feature (via
 * xplan:dientZurDarstellungVon). Only string values are considered.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SchriftinhaltLookup extends PraesentationsobjektLookup {

	private final Xpath schriftinhalt;

	private AttributePropertyType propertyType = PRIMITIVE;

	public SchriftinhaltLookup() {
		super();
		this.schriftinhalt = new Xpath("xplan:schriftinhalt");
	}

	public SchriftinhaltLookup(String type) {
		super();
		if (type != null) {
			propertyType = AttributePropertyType.valueOf(type);
		}
		this.schriftinhalt = new Xpath("xplan:schriftinhalt");
	}

	public AttributePropertyType getPropertyType() {
		return propertyType;
	}

	@Override
	protected TypedObjectNode evaluate(Feature feature, FeatureCollection features, Feature referencedFeature,
			List<AttributeProperty> attributeProperty) {
		XPlanVersion xPlanVersion = XPlanVersion.valueOfNamespace(feature.getName().getNamespaceURI());
		TypedObjectNode originalSchriftinhalt = schriftinhalt.evaluate(feature, features);
		if (originalSchriftinhalt != null)
			return originalSchriftinhalt;
		if (referencedFeature != null && attributeProperty != null) {
			String text = createSchriftinhalt(attributeProperty, xPlanVersion);
			return toPrimitiveValue(text);
		}
		return null;
	}

	private String createSchriftinhalt(List<AttributeProperty> attributeProperties, XPlanVersion xPlanVersion) {
		Stream<String> schriftinhaltParts = filterProperties(attributeProperties, xPlanVersion);
		return schriftinhaltParts.collect(collectingAndThen(joining(" "), schriftinhalt -> {
			if (schriftinhalt.isEmpty())
				return null;
			return schriftinhalt;
		}));
	}

	private Stream<String> filterProperties(List<AttributeProperty> attributeProperties, XPlanVersion xPlanVersion) {
		if (ENUM.equals(propertyType))
			return filterEnums(attributeProperties, xPlanVersion);
		return filterStrings(attributeProperties);
	}

	private Stream<String> filterStrings(List<AttributeProperty> attributeProperties) {
		return attributeProperties.stream()
				.filter(attributeProperty -> PRIMITIVE.equals(attributeProperty.getAttributePropertyType()))
				.map(attributeProperty -> attributeProperty.getValue());
	}

	private Stream<String> filterEnums(List<AttributeProperty> attributeProperties, XPlanVersion xPlanVersion) {
		return attributeProperties.stream()
				.filter(attributeProperty -> ENUM.equals(attributeProperty.getAttributePropertyType()))
				.map(attributeProperty -> {
					XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(xPlanVersion);
					return xPlanCodeLists.getKuerzel(attributeProperty.getCodeListId(), attributeProperty.getValue());
				}).filter(kuerzel -> kuerzel != null);
	}

}
