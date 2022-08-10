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

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.expression.praesentation.AttributePropertyType.STRING;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * Creates the schriftinhalt dependent on the referenced feature (via
 * xplan:dientZurDarstellungVon). Only string values are considered.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SchriftinhaltLookup extends PraesentationsobjektLookup {

	private final Xpath schriftinhalt;

	public SchriftinhaltLookup() {
		super();
		this.schriftinhalt = new Xpath("xplan:schriftinhalt");
	}

	@Override
	protected TypedObjectNode evaluate(Feature feature, FeatureCollection features, Feature referencedFeature,
			List<AttributeProperty> attributeProperty) {
		TypedObjectNode originalSchriftinhalt = schriftinhalt.evaluate(feature, features);
		if (originalSchriftinhalt != null)
			return originalSchriftinhalt;
		if (attributeProperty != null) {
			String stylesheetId = createSchriftinhalt(attributeProperty);
			return toPrimitiveValue(stylesheetId);
		}
		return schriftinhalt.evaluate(feature, features);
	}

	private String createSchriftinhalt(List<AttributeProperty> attributeProperties) {
		return attributeProperties.stream()
				.filter(attributeProperty -> STRING.equals(attributeProperty.getAttributePropertyType()))
				.map(attributeProperty -> attributeProperty.getValue()).collect(Collectors.joining(" "));
	}

}
