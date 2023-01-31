/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.flatten.complex.ComplexFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.lp.LpBiologischeVielfaltKomplexFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpBegruendungAbschnittFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpExterneReferenzFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpGenerAttributFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpRasterplanFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpTextAbschnittFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.xp.XpVerfahrensMerkmalFlattener;
import de.latlon.xplan.manager.synthesizer.utils.AlphanumericComparator;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.Reference;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.IdFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToArray;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * {@link Expression} that returns a "flat" textual representation for properties that
 * have a "complex" value ( {@link Feature} or {@link ElementNode}).
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @see Flattener
 * @since 1.0
 */
public class XplanFlattenProperty implements Expression {

	private static final Logger LOG = LoggerFactory.getLogger(XplanFlattenProperty.class);

	private final Expression exp;

	private final boolean sortProperties;

	private boolean keepCodes;

	private final List<Flattener> customFlatteners = new ArrayList<Flattener>();

	/**
	 * @param codelists used to translate codelist values, never <code>null</code>
	 * @param exp an expression that targets a property node
	 */
	public XplanFlattenProperty(XPlanCodelists codelists, Expression exp) {
		this(codelists, exp, false);
	}

	/**
	 * @param codelists used to translate codelist values, never <code>null</code>
	 * @param exp an expression that targets a property node
	 * @param sortProperties <code>true</code> if the properties should be sorted
	 * alphabetically, false otherwise
	 */
	public XplanFlattenProperty(XPlanCodelists codelists, Expression exp, boolean sortProperties) {
		this(codelists, exp, sortProperties, false);
	}

	/**
	 * @param codelists used to translate codelist values, never <code>null</code>
	 * @param exp an expression that targets a property node
	 * @param sortProperties <code>true</code> if the properties should be sorted
	 * alphabetically, false otherwise
	 * @param keepCodes <code>true</code> if code properties should not be translated,
	 * <code>false</code> otherwise
	 *
	 */
	public XplanFlattenProperty(XPlanCodelists codelists, Expression exp, boolean sortProperties, boolean keepCodes) {
		this.exp = exp;
		this.sortProperties = sortProperties;
		this.keepCodes = keepCodes;
		this.customFlatteners.add(new ComplexFlattener(codelists));
		customFlatteners.add(new LpBiologischeVielfaltKomplexFlattener(codelists, keepCodes));
		customFlatteners.add(new XpBegruendungAbschnittFlattener());
		customFlatteners.add(new XpGenerAttributFlattener());
		customFlatteners.add(new XpRasterplanFlattener());
		customFlatteners.add(new XpTextAbschnittFlattener());
		customFlatteners.add(new XpVerfahrensMerkmalFlattener());
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features, PlanContext planContext) {
		List<String> flattenedValues = new ArrayList<>();
		XpExterneReferenzFlattener extRefFlattener = new XpExterneReferenzFlattener(feature);
		try {
			TypedObjectNodeArray<TypedObjectNode> props = castToArray(exp.evaluate(feature, features, planContext));
			if (props != null && props.getElements().length > 0) {
				for (TypedObjectNode o : props.getElements()) {
					if (!(o instanceof Property)) {
						String msg = "Trying to flatten  '" + o.getClass() + "', but it can only flatten properties.";
						throw new IllegalArgumentException(msg);
					}
					String flattenedValue = flatten((Property) o, extRefFlattener, features);
					flattenedValues.add(flattenedValue);
				}
			}
		}
		catch (Exception e) {
			String msg = "Error flattening property '" + exp + "' of feature '" + feature.getId() + " : "
					+ e.getMessage();
			LOG.error(msg, e);
			return null;
		}
		if (sortProperties) {
			Collections.sort(flattenedValues, new AlphanumericComparator());
		}
		String s = flattenedValues.isEmpty() ? null : flattenedValues.stream().collect(Collectors.joining());
		return toPrimitiveValue(s);
	}

	private String flatten(Property prop, XpExterneReferenzFlattener extRefFlattener, FeatureCollection features) {
		TypedObjectNode value = prop.getValue();
		if (value instanceof ElementNode) {
			try {
				value = getFirstChild((ElementNode) value);
			}
			catch (Exception e) {
				return new DefaultFlattener().flatten(value, keepCodes);
			}
		}
		else if (value instanceof Reference) {
			Reference<?> reference = (Reference<?>) value;
			if (reference.isLocal()) {
				String id = reference.getId();
				try {
					FeatureCollection members = features.getMembers(new IdFilter(id),
							new TypedObjectNodeXPathEvaluator());
					if (members.size() == 1) {
						value = members.iterator().next();
					}
					else {
						LOG.warn("FeatureReference could not be resolved (URI: " + id + ")");
						return flatten(((Reference<?>) value));
					}
				}
				catch (FilterEvaluationException e) {
					LOG.warn("FeatureReference could not be resolved (URI: " + id + ")");
					return flatten(((Reference<?>) value));
				}
			}
			else {
				return flatten(((Reference<?>) value));
			}
		}
		else if (value != null) {
			LOG.error("Only feature- or element-valued properties can be flattened. ");
			throw new IllegalArgumentException();
		}
		if (value == null) {
			return "";
		}
		for (Flattener flattener : customFlatteners) {
			if (flattener.accepts(value)) {
				return flattener.flatten(value, keepCodes);
			}
		}
		if (extRefFlattener.accepts(value)) {
			return extRefFlattener.flatten(value, keepCodes);
		}
		return new DefaultFlattener().flatten(value, keepCodes);
	}

	private TypedObjectNode getFirstChild(ElementNode elNode) {
		if (elNode.getChildren().size() > 0 && elNode.getChildren().get(0) instanceof ElementNode) {
			return elNode.getChildren().get(0);
		}
		throw new IllegalArgumentException();
	}

	private String flatten(Reference<?> externalRef) {
		return "[" + escape(externalRef.getURI()) + "]";
	}

	private String escape(String desc) {
		String result = desc;
		if (result.startsWith("[") && result.endsWith("]")) {
			result = result.substring(1, result.length() - 1);
		}
		result = result.replace("][", "][][");
		return result;
	}

}
