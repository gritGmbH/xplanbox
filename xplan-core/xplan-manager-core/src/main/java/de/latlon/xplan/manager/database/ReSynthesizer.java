/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * Re-synthesizes single or all available plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ReSynthesizer {

	private static final Logger LOG = LoggerFactory.getLogger(ReSynthesizer.class);

	private final XPlanDao xPlanDao;

	private final XPlanSynthesizer xPlanSynthesizer;

	private final SortPropertyReader sortPropertyReader;

	private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	/**
	 * @param dao used to access the database, never <code>null</code>
	 * @param xPlanSynthesizer used to synthesize the plans, never <code>null</code>
	 * @param sortPropertyReader used to to retrieve the configured sort property, never
	 * <code>null</code>
	 */
	public ReSynthesizer(XPlanDao dao, XPlanSynthesizer xPlanSynthesizer, SortPropertyReader sortPropertyReader) {
		this.xPlanDao = dao;
		this.xPlanSynthesizer = xPlanSynthesizer;
		this.sortPropertyReader = sortPropertyReader;
	}

	/**
	 * re-synthesizes all available plans.
	 */
	public void reSynthesize() throws Exception {
		List<XPlan> plans = xPlanDao.getXPlanList(false);
		for (XPlan plan : plans) {
			reSynthesize(plan);
		}
	}

	/**
	 * re-synthesizes the plan with the passed id.
	 * @param mgrId the id of the plan to synthesize
	 * @throws IllegalArgumentException if a plan with the passed id is not available
	 */
	public void reSynthesize(int mgrId) throws Exception {
		XPlan xPlanById = xPlanDao.getXPlanById(mgrId);
		if (xPlanById == null)
			throw new IllegalArgumentException("A plan with the id '" + mgrId + "' is not available");
		reSynthesize(xPlanById);
	}

	private void reSynthesize(XPlan plan) throws Exception {
		LOG.debug("Synthesize plan with id {}, version {}", plan.getId(), plan.getVersion());
		XPlanType planType = XPlanType.valueOf(plan.getType());
		XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());

		FeatureCollection featureCollection = xPlanDao.retrieveFeatureCollection(plan);
		if (featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped", plan.getId());
		}
		XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder(featureCollection, planType)
				.build();
		Date sortDate = sortPropertyReader.readSortDate(planType, version, xPlanFeatureCollection.getFeatures());

		FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize(version, xPlanFeatureCollection);
		addInternalId(plan, synthesizedFeatureCollection);

		xPlanDao.updateXPlanSynFeatureCollection(plan, synthesizedFeatureCollection, sortDate);
	}

	private void addInternalId(XPlan plan, FeatureCollection synthesizedFeatureCollection) {
		String internalId = plan.getInternalId();
		if (internalId != null) {
			AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN, null);
			featureCollectionManipulator.addInternalId(synthesizedFeatureCollection, synSchema, internalId);
		}
	}

}
