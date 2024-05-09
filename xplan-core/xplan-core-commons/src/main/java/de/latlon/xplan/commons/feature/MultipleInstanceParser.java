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
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.IdFilter;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.feature.FeatureInspector;
import org.deegree.gml.reference.GmlDocumentIdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Parses multiple XPlan instances asFeatureCollections from one XPlan GML
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MultipleInstanceParser {

	private static final Logger LOG = LoggerFactory.getLogger(MultipleInstanceParser.class);

	private List<String> referenceIds = new ArrayList<>();

	private final List<String> rootIds = new ArrayList<>();

	private final Map<String, List<String>> featuresAndReferenceIds = new HashMap<>();

	private int remainingFeatures;

	/**
	 * @param xPlanGmlParser used to parse the xmlStream, never <code>null</code>
	 * @param xmlStream to parse, never <code>null</code>
	 * @param version the version of the XPlan GML, never <code>null</code>
	 * @param type the type of the XPlan GML, never <code>null</code>
	 * @return the parsed XPlan GML as XPlanFeatureCollections, never <code>null</code>
	 * @throws XMLStreamException
	 * @throws UnknownCRSException
	 */
	XPlanFeatureCollections parse(XPlanGmlParser xPlanGmlParser, XMLStreamReaderWrapper xmlStream, XPlanVersion version,
			XPlanType type) throws XMLStreamException, UnknownCRSException, FeatureCollectionParseException {
		GMLStreamReader gmlStream = null;
		try {
			FeatureInspector fi = feature -> {
				String id = feature.getId();
				if (feature.getName().getLocalPart().endsWith("_Plan")) {
					rootIds.add(id);
					featuresAndReferenceIds.put(id, referenceIds);
					referenceIds = new ArrayList<>();
				}
				else if (!"XPlanAuszug".equals(feature.getName().getLocalPart())) {
					featuresAndReferenceIds.put(id, referenceIds);
					referenceIds = new ArrayList<>();
				}
				return feature;
			};
			gmlStream = xPlanGmlParser.createGmlStreamReader(xmlStream, version);
			gmlStream.addInspector(fi);
			GmlDocumentIdContextListening idContext = new GmlDocumentIdContextListening(version.getGmlVersion());
			idContext.setApplicationSchema(gmlStream.getAppSchema());
			gmlStream.setIdContext(idContext);

			FeatureCollection features = xPlanGmlParser.parseAndResolveContext(xmlStream, gmlStream);
			if (rootIds.size() == 1) {
				return new XPlanFeatureCollectionBuilder(Collections.singletonList(features), type)
					.buildAllowMultipleInstances();
			}
			remainingFeatures = featuresAndReferenceIds.size();

			List<Set<String>> featuresPerInstance = new ArrayList<>();
			addRoots(featuresPerInstance);
			addReferenced(featuresPerInstance);

			List<FeatureCollection> featureCollections = asFeatureCollections(features, featuresPerInstance);
			return new XPlanFeatureCollectionBuilder(featureCollections, type).buildAllowMultipleInstances();
		}
		finally {
			if (gmlStream != null) {
				gmlStream.close();
			}
		}
	}

	private List<FeatureCollection> asFeatureCollections(FeatureCollection features,
			List<Set<String>> featuresPerInstance) {
		List<FeatureCollection> featureCollections = featuresPerInstance.stream().map(featureIds -> {
			try {
				return features.getMembers(new IdFilter(featureIds), new TypedObjectNodeXPathEvaluator());
			}
			catch (FilterEvaluationException e) {
				LOG.error("Could not filter feature collection by id", e);
			}
			return null;
		}).collect(Collectors.toList());
		return featureCollections;
	}

	private void addRoots(List<Set<String>> featuresPerInstance) {
		rootIds.forEach(rootId -> {
			Set<String> rootIdList = new HashSet<>();
			rootIdList.add(rootId);
			rootIdList.addAll(featuresAndReferenceIds.get(rootId));
			featuresPerInstance.add(rootIdList);
			featuresAndReferenceIds.remove(rootId);
		});
	}

	private void addReferenced(List<Set<String>> featuresPerInstance) throws FeatureCollectionParseException {
		Set<String> addedIds = new HashSet<>();
		featuresAndReferenceIds.forEach((candidate, referenceIds) -> {
			featuresPerInstance.forEach((assignedIds) -> {
				if (assignedIds.contains(candidate)
						|| assignedIds.stream().anyMatch(assignedId -> referenceIds.contains(assignedId))) {
					assignedIds.addAll(referenceIds);
					assignedIds.add(candidate);
					addedIds.addAll(referenceIds);
					addedIds.add(candidate);

				}
			});
		});
		addedIds.forEach(addedId -> featuresAndReferenceIds.remove(addedId));
		if (remainingFeatures == featuresAndReferenceIds.size()) {
			throw new FeatureCollectionParseException(
					"Could not split feature collection in separated plans. Remaining features: "
							+ featuresPerInstance);
		}
		remainingFeatures = featuresAndReferenceIds.size();
		if (!featuresAndReferenceIds.isEmpty()) {
			addReferenced(featuresPerInstance);
		}
	}

	private class GmlDocumentIdContextListening extends GmlDocumentIdContext {

		public GmlDocumentIdContextListening(GMLVersion version) {
			super(version);
		}

		@Override
		public void addReference(GMLReference<?> ref) {
			if (ref.isLocal()) {
				referenceIds.add(ref.getId());
			}
			super.addReference(ref);
		}

	}

}
