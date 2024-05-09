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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * Transforms an {@link XPlanFeatureCollection} (with XPlan 3/4.0/4.1/5.0/5.1/5.2
 * features) into a {@link FeatureCollection} that contains flat XPlanSyn features.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanSynthesizer {

	private final static String SYN_NS = XPLAN_SYN.getNamespace();

	private static final AppSchema synSchema;

	private final SynRulesAccessor synRulesAccessor;

	private final FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();

	static {
		try {
			synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param synRulesAccessor used to retrieve the syn rules, never <code>null</code>
	 */
	public XPlanSynthesizer(SynRulesAccessor synRulesAccessor) {
		this.synRulesAccessor = synRulesAccessor;
	}

	/**
	 * Transforms the features of the passed {@link XPlanFeatureCollection} to flat
	 * XPlanSyn features. First the required rules are parsed from the rules files, then
	 * the transformation starts.
	 * @param xplanFc the feature collection to transform, never <code>null</code>
	 * @return a feature collection with the flat XPlanSyn features, never
	 * <code>null</code>
	 */
	public FeatureCollection synthesize(XPlanFeatureCollection xplanFc) {
		return synthesize(xplanFc.getVersion(), xplanFc);
	}

	/**
	 * Transforms the features of the passed {@link XPlanFeatureCollection} to flat
	 * XPlanSyn features. First the required rules are parsed from the rules files, then
	 * the transformation starts.
	 * @param version the version of the XPlanGML, never <code>null</code>
	 * @param xplanFc the feature collection to transform, never <code>null</code>
	 * @return a feature collection with the flat XPlanSyn features, never
	 * <code>null</code>
	 */
	public FeatureCollection synthesize(XPlanVersion version, XPlanFeatureCollection xplanFc) {
		XPlanType xplanType = xplanFc.getType();
		String xplanName = xplanFc.getPlanName();
		FeatureCollection fc = xplanFc.getFeatures();

		List<Feature> featureMembers = new ArrayList<>();
		PlanContext planContext = new PlanContext(xplanType, xplanName);
		for (Feature feature : fc) {
			Feature synFeature = synthesize(version, feature, fc, planContext);
			featureMembers.add(synFeature);
		}

		return new GenericFeatureCollection(fc.getId(), featureMembers);
	}

	private Feature synthesize(XPlanVersion version, Feature feature, FeatureCollection features,
			PlanContext planContext) {
		List<Property> newProps = new ArrayList<>();
		String synFeatureTypeName = featureTypeNameSynthesizer.detectSynFeatureTypeName(feature.getType().getName());
		QName synFeatureName = new QName(SYN_NS, synFeatureTypeName);

		FeatureType synFeatureType = synSchema.getFeatureType(synFeatureName);
		if (synFeatureType == null) {
			String msg = "Interner Fehler. Das XPlanSyn Schema definiert keinen Feature Type mit Namen '"
					+ synFeatureName + "'.";
			throw new RuntimeException(msg);
		}
		List<PropertyType> propTypes = synFeatureType.getPropertyDeclarations();
		for (PropertyType propType : propTypes) {
			// the rule keys are specified in "<featureName>/<propName>" format
			String key = synFeatureTypeName + "/" + propType.getName().getLocalPart();
			Expression expression = synRulesAccessor.getExpression(version, key);
			if (expression != null) {
				TypedObjectNode newPropValue = expression.evaluate(feature, features, planContext);
				if (newPropValue == null) {
					continue;
				}
				if (newPropValue instanceof Property) {
					newPropValue = ((Property) newPropValue).getValue();
				}
				if (newPropValue instanceof TypedObjectNodeArray<?>) {
					newPropValue = toString(((TypedObjectNodeArray<?>) newPropValue));
				}
				if (newPropValue instanceof GenericXMLElement) {
					newPropValue = getNewPropValue((GenericXMLElement) newPropValue);
				}
				Property newProp = new GenericProperty(propType, newPropValue);
				newProps.add(newProp);
			}
			else if (propType.getMinOccurs() != 0) {
				throw new RuntimeException("Interner Fehler. Die Regeldatei enthält keine Regel für " + key + ".");
			}
		}
		return synFeatureType.newFeature(feature.getId(), newProps, null);
	}

	private PrimitiveValue toString(TypedObjectNodeArray<?> array) {
		StringBuilder sBuilder = new StringBuilder();
		for (TypedObjectNode n : array.getElements()) {
			sBuilder.append(n);
		}
		return new PrimitiveValue(sBuilder.toString());
	}

	private TypedObjectNode getNewPropValue(GenericXMLElement valueNode) {
		if (isCodeType(valueNode)) {
			String s = toString(valueNode);
			return new PrimitiveValue(s);
		}
		return new PrimitiveValue(valueNode.getClass() + "");
	}

	private boolean isCodeType(GenericXMLElement valueNode) {
		XSElementDeclaration xsType = valueNode.getXSType();
		return xsType != null && xsType.getTypeDefinition() != null
				&& "CodeType".equals(xsType.getTypeDefinition().getName());
	}

	private String toString(TypedObjectNode value) {
		if (value == null) {
			return null;
		}
		if (value instanceof ElementNode) {
			ElementNode el = (ElementNode) value;
			String s = "";
			PrimitiveValue codeSpace = el.getAttributes().get(new QName("codeSpace"));
			if (codeSpace != null) {
				s = "{" + codeSpace + "}";
			}
			for (TypedObjectNode child : el.getChildren()) {
				s += child;
			}
			return s;
		}
		return value.toString();
	}

}
