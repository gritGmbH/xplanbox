/*-
 * #%L
 * xplan-cli - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.admin.resynthesize;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
public class ReSynthesizer {

	private static final Logger LOG = LoggerFactory.getLogger(ReSynthesizer.class);

	private final FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();

	@Autowired
	@Lazy
	private XPlanManagerDao xPlanManagerDao;

	@Autowired
	@Lazy
	private XPlanSynthesizer xPlanSynthesizer;

	@Autowired
	@Lazy
	private SortPropertyReader sortPropertyReader;

	@Transactional(rollbackOn = Exception.class)
	public int resynthesize(List<String> planIds) throws Exception {
		if (planIds.size() > 1) {
			String planId = planIds.get(0);
			try {
				int planIdInt = Integer.parseInt(planId);
				reSynthesize(planIdInt, xPlanManagerDao, xPlanSynthesizer, sortPropertyReader);
			}
			catch (NumberFormatException e) {
				LOG.error("Invalid plan id, '" + planId + "' is not an integer value");
				return 1;
			}
		}
		else {
			reSynthesize(xPlanManagerDao, xPlanSynthesizer, sortPropertyReader);
		}
		LOG.info("ReSynthesizerTool successfully executed!");
		return 0;
	}

	/**
	 * re-synthesizes all available plans.
	 */
	private void reSynthesize(XPlanManagerDao xPlanManagerDao, XPlanSynthesizer xPlanSynthesizer,
			SortPropertyReader sortPropertyReader) throws Exception {
		List<XPlan> plans = xPlanManagerDao.getXPlanList();
		for (XPlan plan : plans) {
			reSynthesize(plan, xPlanManagerDao, xPlanSynthesizer, sortPropertyReader);
		}
	}

	/**
	 * re-synthesizes the plan with the passed id.
	 * @param mgrId the id of the plan to synthesize
	 * @throws IllegalArgumentException if a plan with the passed id is not available
	 */
	private void reSynthesize(int mgrId, XPlanManagerDao xPlanManagerDao, XPlanSynthesizer xPlanSynthesizer,
			SortPropertyReader sortPropertyReader) throws Exception {
		XPlan xPlanById = xPlanManagerDao.getXPlanById(mgrId);
		if (xPlanById == null)
			throw new IllegalArgumentException("A plan with the id '" + mgrId + "' is not available");
		reSynthesize(xPlanById, xPlanManagerDao, xPlanSynthesizer, sortPropertyReader);
	}

	private void reSynthesize(XPlan plan, XPlanManagerDao xPlanManagerDao, XPlanSynthesizer xPlanSynthesizer,
			SortPropertyReader sortPropertyReader) throws Exception {
		String planId = plan.getId();
		LOG.info("Synthesize plan with id {}, version {}, type {}", planId, plan.getVersion(), plan.getType());
		XPlanType planType = XPlanType.valueOf(plan.getType());
		XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());

		FeatureCollection featureCollection = xPlanManagerDao.retrieveFeatureCollection(plan);
		if (featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped.", planId);
			return;
		}
		XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder(featureCollection, planType)
			.build();
		boolean useOriginalXPlan = !featureTypeNameSynthesizer.idsMatchSynFeatureType(xPlanFeatureCollection);
		if (useOriginalXPlan) {
			try (InputStream originalPlan = xPlanManagerDao.retrieveXPlanArtefact(planId)) {
				xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
					.build()
					.parseXPlanFeatureCollection(originalPlan, version, planType);
				reassignFids(xPlanFeatureCollection);
			}
		}
		Date sortDate = sortPropertyReader.readSortDate(planType, version, xPlanFeatureCollection.getFeatures());
		FeatureCollection synthesizedFeatureCollection = xPlanSynthesizer.synthesize(version, xPlanFeatureCollection);
		String internalId = plan.getInternalId();
		xPlanManagerDao.updateXPlanSynFeatureCollection(plan, synthesizedFeatureCollection, xPlanFeatureCollection,
				sortDate, internalId, useOriginalXPlan);
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
