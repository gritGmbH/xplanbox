/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.expression.flatten.BP_EmissionskontingentLaermFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.BP_EmissionskontingentLaermGebietFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.BpDachgestaltungFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.BpRichtungssektorFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.DefaultFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.Flattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpBegruendungAbschnittFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpExterneReferenzFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpGemeindeFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpGenerAttributFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpHoehenangabeFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpRasterplanFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpSPEMassnahmenDatenFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpTextAbschnittFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpVerbundenerPlanFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XpVerfahrensMerkmalFlattener;
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

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.castToArray;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

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

	private final List<Flattener> customFlatteners = new ArrayList<Flattener>();

	/**
	 * @param exp an expression that targets a property node
	 */
	public XplanFlattenProperty(Expression exp) {
		this(exp, false);
	}

	/**
	 * @param exp an expression that targets a property node
	 * @param sortProperties <code>true</code> if the properties should be sorted
	 * alphabetically, false otherwise
	 */
	public XplanFlattenProperty(Expression exp, boolean sortProperties) {
		this.exp = exp;
		this.sortProperties = sortProperties;
		customFlatteners.add(new XpBegruendungAbschnittFlattener());
		customFlatteners.add(new XpGemeindeFlattener());
		customFlatteners.add(new XpGenerAttributFlattener());
		customFlatteners.add(new XpHoehenangabeFlattener());
		customFlatteners.add(new XpRasterplanFlattener());
		customFlatteners.add(new XpTextAbschnittFlattener());
		customFlatteners.add(new XpVerfahrensMerkmalFlattener());
		customFlatteners.add(new XpVerbundenerPlanFlattener());
		customFlatteners.add(new BpDachgestaltungFlattener());
		customFlatteners.add(new XpSPEMassnahmenDatenFlattener());
		customFlatteners.add(new BpRichtungssektorFlattener());
		customFlatteners.add(new BP_EmissionskontingentLaermFlattener());
		customFlatteners.add(new BP_EmissionskontingentLaermGebietFlattener());
	}

	@Override
	public PrimitiveValue evaluate(Feature feature, FeatureCollection features) {
		List<String> flattenedValues = new ArrayList<>();
		XpExterneReferenzFlattener extRefFlattener = new XpExterneReferenzFlattener(feature);
		try {
			TypedObjectNodeArray<TypedObjectNode> props = castToArray(exp.evaluate(feature, features));
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
				return new DefaultFlattener().flatten(value);
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
				return flattener.flatten(value);
			}
		}
		if (extRefFlattener.accepts(value)) {
			return extRefFlattener.flatten(value);
		}
		return new DefaultFlattener().flatten(value);
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
