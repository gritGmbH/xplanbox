/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.validator.wms.storage.PlanStorage;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManager {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorWmsManager.class);

	private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	private final XPlanSynthesizer synthesizer;

	private final PlanStorage planStorage;

	/**
	 * @param synthesizer used to synthesize the XPlan GML
	 * @param planStorage path to the workspace xplan-webservices-validator-wms-memory-workspace, the
	 * directory data is created if required
	 */
	public ValidatorWmsManager(XPlanSynthesizer synthesizer, PlanStorage planStorage) {
		this.synthesizer = synthesizer;
		this.planStorage = planStorage;
	}

	/**
	 * @param featureCollection to add to the XPlanValidatorWMS, never <code>null</code>
	 * @throws MapPreviewCreationException if the configuration could not be written
	 * @return
	 */
	public int insert(XPlanFeatureCollection featureCollection) throws MapPreviewCreationException {
		try {
			int planId = planStorage.retrieveUniquePlanid();
			AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
			FeatureCollection fc = synthesizer.synthesize(featureCollection);
			featureCollectionManipulator.addPlanIdToFeatures(fc, synSchema, planId);
			planStorage.storeSynFeatureCollection(planId, fc);
			return planId;
		}
		catch (MapPreviewCreationException e) {
			throw e;
		}
		catch (Exception e) {
			LOG.warn("Could not add featureCollection", e);
			throw new MapPreviewCreationException(e);
		}
	}

}
