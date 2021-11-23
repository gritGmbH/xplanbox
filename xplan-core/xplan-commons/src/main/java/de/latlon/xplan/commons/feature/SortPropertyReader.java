/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.commons.feature;

import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.configuration.SortConfiguration;

/**
 * Reads the SortProperty value from a {@link FeatureCollection}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortPropertyReader {

	private SortConfiguration sortConfiguration;

	/**
	 * @param sortConfiguration containing the key how to find the sort property values,
	 * never <code>null</code>
	 * @throws NullPointerException if sortConfiguration is <code>null</code>
	 */
	public SortPropertyReader(SortConfiguration sortConfiguration) {
		if (sortConfiguration == null)
			throw new NullPointerException("sortConfiguration must not be null");
		this.sortConfiguration = sortConfiguration;
	}

	/**
	 * @param planType the type of the plan, never <code>null</code>
	 * @param version the version of the plan, never <code>null</code>
	 * @param featureCollection feature collection to parse, never <code>null</code>
	 * @return the property value as {@link Date}, <code>null</code> if the feature
	 * collection does not contain the property
	 */
	public Date readSortDate(XPlanType planType, XPlanVersion version, FeatureCollection featureCollection) {
		String featureType = sortConfiguration.retrieveFeatureType(planType, version);
		String propertyName = sortConfiguration.retrievePropertyName(planType, version);
		if (featureType != null && propertyName != null) {
			return readSortDate(featureCollection, featureType, propertyName);
		}
		return null;
	}

	private Date readSortDate(FeatureCollection featureCollection, String featureType, String propertyName) {
		Feature feature = findFeature(featureCollection, featureType);
		if (feature != null) {
			Property property = findProperty(feature, propertyName);
			if (property != null)
				return parsePropertyValueAsDate(property);
		}
		return null;
	}

	public static Feature findFeature(FeatureCollection fc, String featureTypeName) {
		for (Feature feature : fc) {
			QName featureName = feature.getName();
			if (featureName.getLocalPart().equals(featureTypeName))
				return feature;
		}
		return null;
	}

	private Property findProperty(Feature feature, String propertyName) {
		QName propertyQName = new QName(feature.getName().getNamespaceURI(), propertyName);
		List<Property> releaseDateProps = feature.getProperties(propertyQName);
		if (!releaseDateProps.isEmpty())
			return releaseDateProps.get(0);
		return null;
	}

	private Date parsePropertyValueAsDate(Property property) {
		PrimitiveValue value = (PrimitiveValue) property.getValue();
		if (value != null) {
			org.deegree.commons.tom.datetime.Date dateValue = (org.deegree.commons.tom.datetime.Date) value.getValue();
			return dateValue.getDate();
		}
		return null;
	}

}
