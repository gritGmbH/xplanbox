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
import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import java.util.List;

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

	public SchriftinhaltLookup() {
		super();
		this.schriftinhalt = new Xpath("xplan:schriftinhalt");
	}

	@Override
	protected TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext,
			Feature referencedFeature, List<AttributeProperty> attributeProperty) {
		XPlanVersion xPlanVersion = XPlanVersion.valueOfNamespace(feature.getName().getNamespaceURI());
		TypedObjectNode originalSchriftinhalt = schriftinhalt.evaluate(feature, features, planContext);
		if (originalSchriftinhalt != null)
			return originalSchriftinhalt;
		if (referencedFeature != null && attributeProperty != null) {
			String text = createSchriftinhalt(attributeProperty, xPlanVersion);
			return toPrimitiveValue(text);
		}
		return null;
	}

	private String createSchriftinhalt(List<AttributeProperty> attributeProperties, XPlanVersion xPlanVersion) {
		return attributeProperties.stream().map(attributeProperty -> {
			if (ENUM.equals(attributeProperty.getAttributePropertyType())) {
				XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get(xPlanVersion);
				String codeListId = attributeProperty.getCodeListId();
				if (XPlanVersion.XPLAN_40.equals(xPlanVersion) && codeListId.endsWith("Type"))
					codeListId = codeListId.substring(0, codeListId.length() - 4);
				return xPlanCodeLists.getKuerzel(codeListId, attributeProperty.getValue());
			}
			else if (PRIMITIVE.equals(attributeProperty.getAttributePropertyType())) {
				return attributeProperty.getValue();
			}
			return null;
		}).filter(value -> value != null).collect(collectingAndThen(joining(" "), schriftinhalt -> {
			if (schriftinhalt.isEmpty())
				return null;
			return schriftinhalt;
		}));
	}

}
