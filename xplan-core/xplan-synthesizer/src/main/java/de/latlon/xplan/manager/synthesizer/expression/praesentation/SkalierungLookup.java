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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.castToPrimitive;
import static de.latlon.xplan.manager.synthesizer.utils.CastUtils.toPrimitiveValue;

/**
 * Calculates the skalierung dependent of the type of the referenced feature (via
 * xplan:dientZurDarstellungVon):
 *
 * <pre>
 *     planScale = XP_Plan/erstellungsMassstab or dependent on the planType (as listed below)
 *     objectScale = depends on the the feature type (as listed below)
 *     skalierung = planScale/objectScale
 *
 *     Scales dependent on plan type or feature type
 *        BP, LP, SO, XP: 1.000
 *        FP 5.000
 *        RP 50.000
 * </pre>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SkalierungLookup extends PraesentationsobjektLookup {

	private static final Logger LOG = LoggerFactory.getLogger(SkalierungLookup.class);

	private final Xpath skalierung;

	public SkalierungLookup() {
		super();
		this.skalierung = new Xpath("xplan:skalierung");
	}

	@Override
	protected TypedObjectNode evaluate(Feature feature, FeatureCollection features, PlanContext planContext,
			Feature referencedFeature, List<AttributeProperty> attributeProperty) {
		TypedObjectNode originalSkalierung = skalierung.evaluate(feature, features, planContext);
		if (originalSkalierung != null)
			return originalSkalierung;
		if (referencedFeature != null && attributeProperty != null) {
			int planScale = detectPlanScale(features);
			int objectScale = detectObjectScale(referencedFeature);
			double skalierung = Double.valueOf(planScale) / Double.valueOf(objectScale);
			return toPrimitiveValue(skalierung);
		}
		return null;
	}

	private int detectPlanScale(FeatureCollection features) {
		Feature planFeature = detectPlanFeature(features);
		if (planFeature != null) {
			List<Property> erstellungsMassstabProps = planFeature
				.getProperties(new QName(planFeature.getName().getNamespaceURI(), "erstellungsMassstab"));
			if (!erstellungsMassstabProps.isEmpty()) {
				PrimitiveValue erstellungsMassstabProp = castToPrimitive(erstellungsMassstabProps.get(0));
				return ((BigInteger) erstellungsMassstabProp.getValue()).intValue();
			}
			String planFeatureTypeName = planFeature.getName().getLocalPart();
			if (planFeatureTypeName.startsWith("FP_"))
				return 5000;
			if (planFeatureTypeName.startsWith("RP_"))
				return 50000;
		}
		return 1000;
	}

	private int detectObjectScale(Feature referencedFeature) {
		String referencedFeatureTypeName = referencedFeature.getName().getLocalPart();
		if (referencedFeatureTypeName.startsWith("FP_"))
			return 5000;
		if (referencedFeatureTypeName.startsWith("RP_"))
			return 50000;
		return 1000;
	}

	private Feature detectPlanFeature(FeatureCollection features) {
		List<Feature> planFeatures = features.stream()
			.filter(feature -> feature.getName().getLocalPart().matches("(BP|FP|LP|RP|SO)_Plan"))
			.collect(Collectors.toList());
		if (!planFeatures.isEmpty())
			return planFeatures.get(0);
		LOG.warn("Could not find Plan feature.");
		return null;
	}

}
