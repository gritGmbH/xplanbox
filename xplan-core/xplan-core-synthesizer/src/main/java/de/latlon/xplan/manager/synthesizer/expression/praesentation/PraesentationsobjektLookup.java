/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyParser;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.gml.reference.FeatureReference;

import java.util.List;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;

/**
 * Abstract Lookup class for Praesentationsobjekte.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class PraesentationsobjektLookup implements Expression {

	private final AttributePropertyParser attributePropertyParser = new AttributePropertyParser();

	private final Xpath dientZurDarstellungVonXPath;

	private final Xpath artXPath;

	private final Xpath indexXPath;

	public PraesentationsobjektLookup() {
		this.dientZurDarstellungVonXPath = new Xpath("xplan:dientZurDarstellungVon");
		this.artXPath = new Xpath("xplan:art");
		this.indexXPath = new Xpath("xplan:index");
	}

	@Override
	public TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		Feature referencedFeature = resolveDientZurDarstellungVon(feature, features, planContext);
		List<AttributeProperty> attributeProperty = parseArtProperties(feature, features, planContext,
				referencedFeature);
		return evaluate(feature, features, planContext, referencedFeature, attributeProperty);
	}

	/**
	 * Evaluates on the given {@link Feature}.
	 * @param feature feature to operate on, must not be <code>null</code>
	 * @param features the feature collection the feature is part of, must not be
	 * <code>null</code>
	 * @param planContext
	 * @param referencedFeature the feature referenced by "dientZurDarstellungVon", may be
	 * <code>null</code> if not available
	 * @param attributeProperty the parsed "art" attribute, may be <code>null</code> if
	 * "art" or "dientZurDarstellungVon" is not available
	 * @return expression value, suitable as property value, can be <code>null</code> (no
	 * value, omit property)
	 */
	protected abstract TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext,
			Feature referencedFeature, List<AttributeProperty> attributeProperty);

	private Feature resolveDientZurDarstellungVon(Feature feature, FeatureCollection features,
			PlanContext planContext) {
		TypedObjectNode dientZurDarstellungVonProperty = dientZurDarstellungVonXPath.evaluate(feature, features,
				planContext);
		if (dientZurDarstellungVonProperty instanceof GenericProperty) {
			List<TypedObjectNode> children = ((GenericProperty) dientZurDarstellungVonProperty).getChildren();
			if (!children.isEmpty() && children.get(0) instanceof FeatureReference)
				return ((FeatureReference) children.get(0)).getReferencedObject();
		}
		else if (dientZurDarstellungVonProperty instanceof FeatureReference) {
			return ((FeatureReference) dientZurDarstellungVonProperty).getReferencedObject();
		}
		return null;
	}

	private List<AttributeProperty> parseArtProperties(Feature feature, FeatureCollection features,
			PlanContext planContext, Feature referencedFeature) {
		if (referencedFeature == null)
			return null;
		TypedObjectNodeArray<TypedObjectNode> artProperties = castToArray(
				artXPath.evaluate(feature, features, planContext));
		TypedObjectNodeArray<TypedObjectNode> indexProperties = castToArray(
				indexXPath.evaluate(feature, features, planContext));
		if (artProperties != null)
			return attributePropertyParser.parseAttributeProperties(referencedFeature, artProperties, indexProperties);
		return null;
	}

}
