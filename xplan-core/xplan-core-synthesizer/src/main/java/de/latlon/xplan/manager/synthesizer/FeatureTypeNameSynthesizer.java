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

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;

import javax.xml.namespace.QName;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 *
 * Allows access to new names of synthesized feature type names
 */
public class FeatureTypeNameSynthesizer {

	public static final String SYN_FEATURETYPE_PREFIX = "XPLAN_";

	private static final Properties renamedFeatureTypes = new Properties();

	static {
		try {
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
	 * @param featureTypeName the original name of the feature type, never
	 * <code>null</code>
	 * @return the synthesized name of the feature type or the original feature type name
	 * if no synthesized name exists, never <code>null</code>.
	 */
	public String detectSynFeatureTypeName(QName featureTypeName) {
		String localPart = featureTypeName.getLocalPart();
		return renamedFeatureTypes.getProperty(localPart, localPart);
	}

	public boolean idsMatchSynFeatureType(XPlanFeatureCollection featureCollection) {
		return featureCollection.getFeatures().stream().allMatch(feature -> {
			String featureId = feature.getId();
			QName name = feature.getType().getName();
			String synFeatureTypeName = detectSynFeatureTypeName(name);
			return featureId.startsWith(SYN_FEATURETYPE_PREFIX + synFeatureTypeName.toUpperCase());
		});
	}

}
