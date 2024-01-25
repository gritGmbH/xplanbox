/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;

/**
 * Contains the configuration which feature type/property name should be used to fill the
 * sort property in Syn WMS.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortConfiguration {

	private final Map<SortKey, String> sortKeyToFeatureType = new HashMap<>();

	private final Map<SortKey, String> sortKeyToPropertyName = new HashMap<>();

	/**
	 * Adds a new featureType and propertyName to this {@link SortConfiguration}. If a
	 * configuration option with the same planType and version was already added, the new
	 * one overwrites the existing.
	 * @param planType the type of the plan, never <code>null</code>
	 * @param version the version of the plan, never <code>null</code>
	 * @param featureType the name of the feature type used to find the sort value, may be
	 * <code>null</code> if not configured
	 * @param propertyName the name of the feature types property used to find the sort
	 * value, may be <code>null</code> if not configured
	 * @throws NullPointerException if planType of version is <code>null</code>
	 */
	public void addSortField(XPlanType planType, XPlanVersion version, String featureType, String propertyName) {
		if (planType == null)
			throw new NullPointerException("planType must never be null");
		if (version == null)
			throw new NullPointerException("version must never be null");
		SortKey key = new SortKey(planType, version);
		sortKeyToFeatureType.put(key, featureType);
		sortKeyToPropertyName.put(key, propertyName);
	}

	/**
	 * @param planType the type of the plan, may be <code>null</code>
	 * @param version the version of the plan, may be <code>null</code>
	 * @return the name of the feature type used to find the sort value, may be
	 * <code>null</code> if not configured
	 */
	public String retrieveFeatureType(XPlanType planType, XPlanVersion version) {
		return sortKeyToFeatureType.get(new SortKey(planType, version));
	}

	/**
	 * @param planType the type of the plan, may be <code>null</code>
	 * @param version the version of the plan, may be <code>null</code>
	 * @return the name of the feature types property used to find the sort value, may be
	 * <code>null</code> if not configured
	 */
	public String retrievePropertyName(XPlanType planType, XPlanVersion version) {
		return sortKeyToPropertyName.get(new SortKey(planType, version));
	}

	/**
	 * Logs the configuration on info level.
	 * @param log to log into, never <code>null</code>
	 */
	public void logConfiguration(final Logger log) {
		log.info("  SortConfiguration");
		for (Entry<SortKey, String> sortKeyToFeatureTypeEntry : sortKeyToFeatureType.entrySet()) {
			SortKey key = sortKeyToFeatureTypeEntry.getKey();
			String featureType = sortKeyToFeatureTypeEntry.getValue();
			String propertyName = sortKeyToPropertyName.get(key);
			log.info("   - {}, {}: {}, {}", key.planType, key.version, featureType, propertyName);
		}
	}

	private class SortKey {

		XPlanType planType;

		XPlanVersion version;

		private SortKey(XPlanType planType, XPlanVersion version) {
			this.planType = planType;
			this.version = version;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((planType == null) ? 0 : planType.hashCode());
			result = prime * result + ((version == null) ? 0 : version.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SortKey other = (SortKey) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (planType != other.planType)
				return false;
			if (version != other.version)
				return false;
			return true;
		}

		private SortConfiguration getOuterType() {
			return SortConfiguration.this;
		}

	}

}
