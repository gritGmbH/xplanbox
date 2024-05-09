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

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.feature.types.property.SimplePropertyType;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.deegree.commons.tom.primitive.BaseType.STRING;

/**
 * Manipulates a deegree feature collection.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class FeatureCollectionManipulator {

	private static final String WMS_SORT_DATE_PROP_NAME = "wmsSortDate";

	private static final String INTERNAL_ID_PROP_NAME = "internalId";

	private static final String XPLAN_MGR_PLAN_ID_PROP_NAME = "xplanMgrPlanId";

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	private static final String START_DATE_TIME_PROP_NAME = "gueltigkeitBeginn";

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	private static final String END_DATE_TIME_PROP_NAME = "gueltigkeitEnde";

	/**
	 * Adds the internalId property to following feature types: BP_Plan, FP_Plan, LP_Plan,
	 * RP_Plan, SO_Plan.
	 * @param featureCollection feature collection that is manipulated, not
	 * <code>null</code>
	 * @param schema schema to determine the correct position of the internalId property,
	 * not <code>null</code>
	 * @param internalId value of the internalId property, not <code>null</code>
	 */
	public void addInternalId(FeatureCollection featureCollection, AppSchema schema, String internalId) {
		Iterator<Feature> iterator = featureCollection.iterator();
		while (iterator.hasNext()) {
			Feature feature = iterator.next();
			String nameOfFeature = feature.getName().getLocalPart();
			if (isPlanFeature(nameOfFeature)) {
				addInternalIdProperty(schema, internalId, feature);
			}
		}
	}

	/**
	 * Adds the following properties to each feature in the passed
	 * {@link FeatureCollection}:
	 * <ul>
	 * <li>the id of the plan in the manager (as property xplanMgrPlanId)</li>
	 * <li>the date used by the wms to sort by (as property wmsSortDate)</li>
	 * <li>the begin and end date of the validity period (as properties gueltigkeitBeginn
	 * and gueltigkeitEnde)</li>
	 * </ul>
	 * @param featureCollectionToModify {@link FeatureCollection} encapsulation a plan,
	 * never <code>null</code>
	 * @param applicationSchema describing the feature types, never <code>null</code>
	 * @param planId the id of the plan to add to all features, never <code>null</code>
	 * @param wmsSortDate the date to add to each sortable feature, may be
	 * <code>null</code>
	 * @param beginValidity the begin of the validity, may be <code>null</code>
	 * @param beginValidity the endo of the validity, may be <code>null</code>
	 */
	public void addAdditionalPropertiesToFeatures(FeatureCollection featureCollectionToModify,
			AppSchema applicationSchema, int planId, Date wmsSortDate, Date beginValidity, Date endValidity) {
		Iterator<Feature> featureCollectionIterator = featureCollectionToModify.iterator();
		while (featureCollectionIterator.hasNext()) {
			Feature feature = featureCollectionIterator.next();
			FeatureType featureType = applicationSchema.getFeatureType(feature.getName());
			addMgrPlanIdProperty(planId, feature, featureType);
			addWmsSortDatePropertyToFeature(wmsSortDate, feature, featureType);
			addStartAndEndDateTimeProperty(beginValidity, endValidity, feature, featureType);
		}
	}

	/**
	 * Adds the id of the plan in the manager (as property xplanMgrPlanId) to each feature
	 * in the passed {@link FeatureCollection}:
	 * @param featureCollectionToModify {@link FeatureCollection} encapsulation a plan,
	 * never <code>null</code>
	 * @param applicationSchema describing the feature types, never <code>null</code>
	 * @param planId the id of the plan to add to all features, never <code>null</code>
	 */
	public void addPlanIdToFeatures(FeatureCollection featureCollectionToModify, AppSchema applicationSchema,
			int planId) {
		Iterator<Feature> featureCollectionIterator = featureCollectionToModify.iterator();
		while (featureCollectionIterator.hasNext()) {
			Feature feature = featureCollectionIterator.next();
			FeatureType featureType = applicationSchema.getFeatureType(feature.getName());
			addMgrPlanIdProperty(planId, feature, featureType);
		}
	}

	private void addInternalIdProperty(AppSchema schema, String internalId, Feature feature) {
		Property property = createNewInternalIdProperty(internalId, feature);
		int internalIdIndex = calculateIndex(schema, feature, INTERNAL_ID_PROP_NAME);
		feature.getProperties().add(internalIdIndex, property);
	}

	private void addMgrPlanIdProperty(int planId, Feature feature, FeatureType featureType) {
		QName propName = new QName(feature.getName().getNamespaceURI(), XPLAN_MGR_PLAN_ID_PROP_NAME);
		SimplePropertyType pt = (SimplePropertyType) featureType.getPropertyDeclaration(propName);
		Property planIdProp = new SimpleProperty(pt, Integer.toString(planId));
		feature.getProperties().add(planIdProp);
	}

	private void addWmsSortDatePropertyToFeature(Date releaseDate, Feature feature, FeatureType featureType) {
		if (releaseDate != null && isSortableFeature(featureType))
			addDateProperty(feature, featureType, WMS_SORT_DATE_PROP_NAME, releaseDate);
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	private void addStartAndEndDateTimeProperty(Date beginValidity, Date endValidity, Feature feature,
			FeatureType featureType) {
		if (beginValidity != null)
			addDateProperty(feature, featureType, START_DATE_TIME_PROP_NAME, beginValidity);
		if (endValidity != null)
			addDateProperty(feature, featureType, END_DATE_TIME_PROP_NAME, endValidity);
	}

	private void addDateProperty(Feature feature, FeatureType featureType, String datePropName, Date dateValue) {
		QName featureName = feature.getName();
		QName propName = new QName(featureName.getNamespaceURI(), datePropName);
		PropertyType pt = featureType.getPropertyDeclaration(propName);
		if (pt == null)
			throw new IllegalArgumentException(
					"Cannot find declaration for property with name " + propName + " for feature " + featureName);
		org.deegree.commons.tom.datetime.Date date = new org.deegree.commons.tom.datetime.Date(dateValue, null);
		Property dateProp = new GenericProperty(pt, new PrimitiveValue(date));
		feature.getProperties().add(dateProp);
	}

	private Property createNewInternalIdProperty(String internalId, Feature feature) {
		String namespaceUri = feature.getName().getNamespaceURI();
		QName qName = new QName(namespaceUri, INTERNAL_ID_PROP_NAME);
		SimplePropertyType propertyType = new SimplePropertyType(qName, 0, 1, STRING, null, null);
		return new SimpleProperty(propertyType, internalId);
	}

	private int calculateIndex(AppSchema schema, Feature feature, String propertyName) {
		int internalIdIndex = 0;
		List<PropertyType> props = retrievePropertiesFromSchema(schema, feature);
		for (PropertyType prop : props) {
			QName propName = prop.getName();
			int numberOfPropsInFeature = feature.getProperties(propName).size();
			internalIdIndex += numberOfPropsInFeature;
			if (propertyName.equals(propName.getLocalPart()))
				break;
		}
		return internalIdIndex;
	}

	private List<PropertyType> retrievePropertiesFromSchema(AppSchema schema, Feature feature) {
		FeatureType ft = schema.getFeatureType(feature.getName());
		return ft.getPropertyDeclarations();
	}

	private boolean isPlanFeature(String nameOfFeature) {
		return "BP_Plan".equals(nameOfFeature) || "FP_Plan".equals(nameOfFeature) || "LP_Plan".equals(nameOfFeature)
				|| "RP_Plan".equals(nameOfFeature) || "SO_Plan".equals(nameOfFeature);
	}

	private boolean isSortableFeature(FeatureType featureType) {
		String ftName = featureType.getName().getLocalPart();
		if (ftName == null)
			return false;
		return ftName.startsWith("BP_") || ftName.startsWith("FP_") || ftName.startsWith("LP_")
				|| ftName.startsWith("RP_") || ftName.startsWith("SO_");
	}

}
