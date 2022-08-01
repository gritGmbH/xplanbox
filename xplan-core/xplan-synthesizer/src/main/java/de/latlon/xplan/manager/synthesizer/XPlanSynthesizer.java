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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.apache.commons.io.IOUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * Transforms an {@link XPlanFeatureCollection} (with XPlan 3/4.0/4.1/5.0/5.1/5.2
 * features) into a {@link FeatureCollection} that contains flat XPlanSyn features.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanSynthesizer {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanSynthesizer.class);

	private final static String SYN_NS = XPLAN_SYN.getNamespace();

	private static final Properties renamedFeatureTypes = new Properties();

	private static final AppSchema synSchema;

	private final Map<String, Expression> rules = new HashMap<String, Expression>();

	private final Path rulesDirectory;
	static {
		try {
			synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
			InputStream renamedFeatureTypesResource = XPlanSynthesizer.class
					.getResourceAsStream("/featuretypes/renamedFeatureTypes.properties");
			renamedFeatureTypes.load(renamedFeatureTypesResource);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Instantiates a new XPlanSynthesizer with default rules (from internal rules
	 * directory).
	 */
	public XPlanSynthesizer() {
		this(null);
	}

	/**
	 * @param rulesDirectory the directory containing additional rules overwriting the
	 * internal rules, may be <code>null</code>
	 */
	public XPlanSynthesizer(Path rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
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

		processRuleFile(version, xplanType.name(), xplanName);

		// initialize lookup for all Fachobjekte that are referenced by XP_PPO features
		XpPpoLookup.init(fc);

		List<Feature> featureMembers = new ArrayList<Feature>();
		for (Feature feature : fc) {
			Feature synFeature = synthesize(feature, fc);
			featureMembers.add(synFeature);
		}

		return new GenericFeatureCollection(fc.getId(), featureMembers);
	}

	/**
	 * Retrieve the directory containing the external rules configuration used for the
	 * transformation.
	 * @return the external rules configuration file of the transformation. may be
	 * <code>null</code>if not set)
	 *
	 */
	public Path getExternalConfigurationFile() {
		return rulesDirectory;
	}

	private void processRuleFile(XPlanVersion version, String xplanType, String xplanName) {
		rules.clear();
		String rulesFileName = detectRulesFileName(version);
		InputStream rulesFromClasspath = retrieveRulesFileFromClasspath(rulesFileName);
		processRules(xplanType, xplanName, rulesFromClasspath);
		InputStream rulesFromFileSystem = retrieveRulesFileFromFileSystem(rulesFileName);
		if (rulesFromFileSystem != null)
			processRules(xplanType, xplanName, rulesFromFileSystem);
	}

	private void processRules(String xplanType, String xplanName, InputStream is) {
		try {
			RuleParser parser = new RuleParser(xplanType, xplanName, this);
			for (String line : IOUtils.readLines(is)) {
				if (!line.startsWith("#") && !"".equals(line.trim())) {
					int firstEquals = line.indexOf("=");
					rules.put(line.substring(0, firstEquals), parser.parse(line.substring(firstEquals + 1)));
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException("Error while reading the rules file ", e);
		}
		finally {
			closeQuietly(is);
		}
	}

	private InputStream retrieveRulesFileFromFileSystem(String rulesFileName) {
		if (rulesDirectory != null) {
			Path rulesFile = rulesDirectory.resolve(rulesFileName);
			LOG.info("Read additional/overwriting rules from directory: {}", rulesFile);
			if (Files.exists(rulesFile)) {
				try {
					return Files.newInputStream(rulesFile);
				}
				catch (IOException e) {
					LOG.info("Could not read rules in configuration directory.");
				}
			}
			LOG.info("Could not find rules in configuration directory.");
		}
		return null;
	}

	private InputStream retrieveRulesFileFromClasspath(String rulesFileName) {
		String rulesResource = "/rules/" + rulesFileName;
		LOG.info("Read rules from internal directory: {}", rulesResource);
		return XPlanSynthesizer.class.getResourceAsStream(rulesResource);
	}

	private String detectRulesFileName(XPlanVersion version) {
		String synRulesFileName = version.getSynRulesFileName();
		if (synRulesFileName == null) {
			throw new IllegalArgumentException("Could not find rules file for XPlan version " + version);
		}
		return synRulesFileName;
	}

	private Feature synthesize(Feature feature, FeatureCollection features) {
		List<Property> newProps = new ArrayList<Property>();
		String synFeatureTypeName = detectSynFeatureTypeName(feature.getType().getName());
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
			String key = feature.getName().getLocalPart() + "/" + propType.getName().getLocalPart();
			if (rules.containsKey(key)) {
				TypedObjectNode newPropValue = rules.get(key).evaluate(feature, features);
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

	private static String detectSynFeatureTypeName(QName featureTypeName) {
		String localPart = featureTypeName.getLocalPart();
		return renamedFeatureTypes.getProperty(localPart, localPart);
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
