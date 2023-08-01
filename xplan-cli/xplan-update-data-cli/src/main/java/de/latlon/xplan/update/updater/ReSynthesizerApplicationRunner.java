/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.update.updater;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.update.config.ReSynthesizerApplicationContext;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;

/**
 * Re-synthesizes single or all available plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(ReSynthesizerApplicationContext.class)
public class ReSynthesizerApplicationRunner implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ReSynthesizerApplicationRunner.class);

	private static final String OPT_PLAN_ID = "planId";

	@Autowired
	private XPlanManagerDao xPlanDao;

	@Autowired
	private XPlanSynthesizer xPlanSynthesizer;

	@Autowired
	private SortPropertyReader sortPropertyReader;

	private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

	private final FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void run(ApplicationArguments args) throws Exception {
		if (args.containsOption(OPT_PLAN_ID)) {
			List<String> planIds = args.getOptionValues(OPT_PLAN_ID);
			String planId = planIds.get(0);
			try {
				int planIdInt = Integer.parseInt(planId);
				reSynthesize(planIdInt);
			}
			catch (NumberFormatException e) {
				System.out.println(
						"Invalid plan id (parameter " + OPT_PLAN_ID + "), '" + planId + "' is not an integer value");
			}
		}
		else {
			reSynthesize();
		}
		LOG.info("ReSynthesizerTool successfully executed!");
	}

	/**
	 * re-synthesizes all available plans.
	 */
	private void reSynthesize() throws Exception {
		List<XPlan> plans = xPlanDao.getXPlanList();
		for (XPlan plan : plans) {
			reSynthesize(plan);
		}
	}

	/**
	 * re-synthesizes the plan with the passed id.
	 * @param mgrId the id of the plan to synthesize
	 * @throws IllegalArgumentException if a plan with the passed id is not available
	 */
	private void reSynthesize(int mgrId) throws Exception {
		XPlan xPlanById = xPlanDao.getXPlanById(mgrId);
		if (xPlanById == null)
			throw new IllegalArgumentException("A plan with the id '" + mgrId + "' is not available");
		reSynthesize(xPlanById);
	}

	private void reSynthesize(XPlan plan) throws Exception {
		String planId = plan.getId();
		LOG.info("Synthesize plan with id {}, version {}, type {}", planId, plan.getVersion(), plan.getType());
		XPlanType planType = XPlanType.valueOf(plan.getType());
		XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());

		FeatureCollection featureCollection = xPlanDao.retrieveFeatureCollection(plan);
		if (featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped.", planId);
			return;
		}
		XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder(featureCollection, planType)
			.build();
		boolean useOriginalXPlan = !featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		if (useOriginalXPlan) {
			try (InputStream originalPlan = xPlanDao.retrieveXPlanArtefact(planId)) {
				xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
					.build()
					.parseXPlanFeatureCollection(originalPlan, version, planType);
				reassignFids(xPlanFeatureCollection);
			}
		}
		Date sortDate = sortPropertyReader.readSortDate(planType, version, xPlanFeatureCollection.getFeatures());
		FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize(version, xPlanFeatureCollection);
		String internalId = plan.getInternalId();
		xPlanDao.updateXPlanSynFeatureCollection(plan, synthesizedFeatureCollection, xPlanFeatureCollection, sortDate,
				internalId, useOriginalXPlan);
	}

	private void reassignFids(XPlanFeatureCollection fc) {
		for (Feature f : fc.getFeatures()) {
			String synFeatureTypeName = featureTypeNameSynthesizer.detectSynFeatureTypeName(f.getName());
			String prefix = SYN_FEATURETYPE_PREFIX + synFeatureTypeName.toUpperCase() + "_";
			String uuid = UUID.randomUUID().toString();
			f.setId(prefix + uuid);
		}
	}

}
