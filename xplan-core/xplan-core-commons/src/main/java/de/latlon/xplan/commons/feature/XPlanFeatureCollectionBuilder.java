/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.expression.ValueReference;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.GeometryTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyStringValue;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValues;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.findPlanFeature;
import static org.deegree.cs.CRSUtils.EPSG_4326;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanFeatureCollectionBuilder {

	private final Logger LOG = LoggerFactory.getLogger(XPlanFeatureCollectionBuilder.class);

	public static final String BP_RELEASE_DATE_PROP_NAME = "inkrafttretensDatum";

	public static final String FP_RELEASE_DATE_PROP_NAME = "wirksamkeitsDatum";

	public static final String LP_RELEASE_DATE_PROP_NAME = "inkrafttretenDatum";

	public static final String RP_RELEASE_DATE_PROP_NAME = "datumDesInkrafttretens";

	private final FeatureCollection features;

	private final List<FeatureCollection> featuresCollections;

	private final XPlanType xPlanType;

	private ExternalReferenceInfo externalReferenceInfo;

	/**
	 * @param features to be encapsulated, never <code>null</code>
	 * @param xPlanType type of the plan, never <code>null</code>
	 */
	public XPlanFeatureCollectionBuilder(FeatureCollection features, XPlanType xPlanType) {
		this.features = features;
		this.featuresCollections = null;
		this.xPlanType = xPlanType;
	}

	/**
	 * @param featuresCollections to be encapsulated, never <code>null</code>
	 * @param xPlanType type of the plan, never <code>null</code>
	 */
	public XPlanFeatureCollectionBuilder(List<FeatureCollection> featuresCollections, XPlanType xPlanType) {
		this.featuresCollections = featuresCollections;
		this.features = null;
		this.xPlanType = xPlanType;
	}

	/**
	 * Sets the passed {@link ExternalReferenceInfo} instead of scanning from the
	 * {@link FeatureCollection}.
	 * @param externalReferenceInfo if <code>null</code> the {@link ExternalReferenceInfo}
	 * will be scanned from the {@link FeatureCollection}
	 * @return the builder instance
	 */
	public XPlanFeatureCollectionBuilder withExternalReferenceInfo(ExternalReferenceInfo externalReferenceInfo) {
		this.externalReferenceInfo = externalReferenceInfo;
		return this;
	}

	/**
	 * Build the {@link XPlanSingleInstanceFeatureCollection}.
	 * @return never <code>null</code>
	 */
	public XPlanSingleInstanceFeatureCollection build() {
		if (features == null) {
			throw new IllegalArgumentException("featuresPerInstance cannot be null");
		}
		return build(features);
	}

	/**
	 * Build the {@link XPlanFeatureCollection}.
	 * @return never <code>null</code>
	 */
	public XPlanFeatureCollections buildAllowMultipleInstances() {
		if (featuresCollections == null) {
			throw new IllegalArgumentException("featuresPerInstance cannot be null");
		}
		if (featuresCollections.isEmpty()) {
			throw new IllegalArgumentException("feature collection cannot be empty");
		}
		FeatureCollection featuresOfFirstCollection = featuresCollections.get(0);
		Feature planFeatures = findPlanFeature(featuresOfFirstCollection, xPlanType);
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(planFeatures.getName());

		List<XPlanFeatureCollection> xPlanGmlInstances = createListOfXPlanGmlInstances(featuresOfFirstCollection,
				version);
		return new XPlanFeatureCollections(version, xPlanGmlInstances);
	}

	private XPlanSingleInstanceFeatureCollection build(FeatureCollection features) {
		Feature planFeature = findPlanFeature(features, xPlanType);
		String planFeatureNamespaceUri = planFeature.getName().getNamespaceURI();
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(planFeature.getName());
		String name = FeatureCollectionUtils.retrievePlanName(planFeature);
		String nummer = parsePlanNummer(planFeature);
		String gkz = parsePlanGemeindeKennzahl(planFeature);
		Date planReleaseDate = parsePlanReleaseDate(xPlanType, planFeature);
		Envelope bboxIn4326 = createBboxIn4326(features);
		if (externalReferenceInfo == null)
			externalReferenceInfo = new ExternalReferenceScanner().scan(features, version);
		return new XPlanSingleInstanceFeatureCollection(features, xPlanType, name, nummer, gkz, planReleaseDate,
				externalReferenceInfo, bboxIn4326, version);
	}

	private List<XPlanFeatureCollection> createListOfXPlanGmlInstances(FeatureCollection featuresOfFirstCollection,
			XPlanVersion version) {
		if (featuresCollections.size() == 1) {
			return Collections.singletonList(build(featuresOfFirstCollection));
		}
		return featuresCollections.stream().map(featureCollection -> {
			Feature planFeature = findPlanFeature(featureCollection, xPlanType);
			String name = FeatureCollectionUtils.retrievePlanName(planFeature);
			String nummer = parsePlanNummer(planFeature);
			String gkz = parsePlanGemeindeKennzahl(planFeature);
			Date planReleaseDate = parsePlanReleaseDate(xPlanType, planFeature);
			Envelope bboxIn4326 = createBboxIn4326(featureCollection);
			ExternalReferenceInfo externalReferenceInfo = new ExternalReferenceScanner().scan(featureCollection,
					version);
			return new XPlanMultipleInstanceFeatureCollection(featureCollection, xPlanType, name, nummer, gkz,
					planReleaseDate, externalReferenceInfo, bboxIn4326, version);
		}).collect(Collectors.toList());
	}

	private String parsePlanNummer(Feature planFeature) {
		String ns = planFeature.getName().getNamespaceURI();
		return getPropertyStringValue(planFeature, new QName(ns, "nummer"));
	}

	private String parsePlanGemeindeKennzahl(Feature planFeature) {
		String ns = planFeature.getName().getNamespaceURI();
		List<TypedObjectNode> gkzValues = getPropertyValues(planFeature, new QName(ns, "gkz"));
		if (gkzValues.isEmpty()) {
			TypedObjectNodeXPathEvaluator evaluator = new TypedObjectNodeXPathEvaluator();
			NamespaceBindings nsBindings = new NamespaceBindings();
			nsBindings.addNamespace("xplan", ns);
			ValueReference propName = new ValueReference("xplan:gemeinde/xplan:XP_Gemeinde/xplan:ags", nsBindings);
			try {
				TypedObjectNode[] nodes = evaluator.eval(planFeature, propName);
				gkzValues = Arrays.asList(nodes);
			}
			catch (FilterEvaluationException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		String gkz = "";
		if (!gkzValues.isEmpty()) {
			gkz = "" + gkzValues.get(0);
			for (int i = 1; i < gkzValues.size(); i++) {
				gkz += ";" + gkzValues.get(i);
			}
		}
		return gkz;
	}

	private Date parsePlanReleaseDate(XPlanType type, Feature planFeature) {
		String propName = detectReleaseDatePropertyName(type);
		if (propName != null) {
			QName releaseDatePropName = new QName(planFeature.getName().getNamespaceURI(), propName);
			List<Property> releaseDateProps = planFeature.getProperties(releaseDatePropName);
			if (!releaseDateProps.isEmpty()) {
				Property releaseDateProp = releaseDateProps.get(0);
				PrimitiveValue value = (PrimitiveValue) releaseDateProp.getValue();
				if (value != null) {
					org.deegree.commons.tom.datetime.Date dateValue = (org.deegree.commons.tom.datetime.Date) value
						.getValue();
					return dateValue.getDate();
				}
			}
		}
		return null;
	}

	private Envelope createBboxIn4326(FeatureCollection fc) {
		try {
			Envelope envelope = fc.getEnvelope();
			if (envelope != null) {
				GeometryTransformer geometryTransformer = new GeometryTransformer(EPSG_4326);
				return geometryTransformer.transform(envelope);
			}
		}
		catch (IllegalArgumentException | UnknownCRSException | TransformationException e) {
			LOG.error("Could not create transformed envelope! Reason: " + e.getMessage(), e);
		}
		return null;
	}

	private String detectReleaseDatePropertyName(XPlanType type) {
		switch (type) {
			case BP_Plan:
				return BP_RELEASE_DATE_PROP_NAME;
			case FP_Plan:
				return FP_RELEASE_DATE_PROP_NAME;
			case LP_Plan:
				return LP_RELEASE_DATE_PROP_NAME;
			case RP_Plan:
				return RP_RELEASE_DATE_PROP_NAME;
			default:
				return null;
		}
	}

}
