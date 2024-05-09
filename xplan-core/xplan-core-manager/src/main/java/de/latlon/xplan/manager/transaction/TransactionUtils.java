/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer;
import org.deegree.feature.Feature;

import java.util.UUID;

import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public final class TransactionUtils {

	private TransactionUtils() {
	}

	private static final FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();

	public static void reassignFids(XPlanFeatureCollection fc) {
		for (Feature f : fc.getFeatures()) {
			String synFeatureTypeName = featureTypeNameSynthesizer.detectSynFeatureTypeName(f.getName());
			String prefix = SYN_FEATURETYPE_PREFIX + synFeatureTypeName.toUpperCase() + "_";
			String uuid = UUID.randomUUID().toString();
			f.setId(prefix + uuid);
		}
	}

}
