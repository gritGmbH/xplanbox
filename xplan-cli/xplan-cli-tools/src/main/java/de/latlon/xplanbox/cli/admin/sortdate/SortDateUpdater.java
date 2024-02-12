package de.latlon.xplanbox.cli.admin.sortdate;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplanbox.cli.admin.db.SortPropertyDbUpdater;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
public class SortDateUpdater {

	private static final Logger LOG = LoggerFactory.getLogger(SortDateUpdater.class);

	@Autowired
	@Lazy
	private XPlanDao xPlanDao;

	@Autowired
	@Lazy
	private SortPropertyReader sortPropertyReader;

	@Autowired
	@Lazy
	private SortPropertyDbUpdater sortPropertyDbUpdater;

	@Autowired
	@Lazy
	private XPlanRasterManager xPlanRasterManager;

	@Transactional(rollbackOn = Exception.class)
	public void sortDates() throws Exception {
		Map<String, Date> planId2sortDate = updateColumnsInDB(xPlanDao, sortPropertyReader, sortPropertyDbUpdater);
		xPlanRasterManager.reorderWmsLayers(planId2sortDate);
		LOG.info("SortDateUpdateTool successfully executed!");
	}

	private Map<String, Date> updateColumnsInDB(XPlanDao xPlanDao, SortPropertyReader sortPropertyReader,
			SortPropertyDbUpdater sortPropertyDbUpdater) throws Exception {
		Map<String, Date> planId2sortDate = new HashMap<String, Date>();
		List<XPlan> plans = xPlanDao.getXPlanList();
		for (XPlan plan : plans) {
			LOG.debug("Update sort column value for plan with id {}", plan.getId());
			FeatureCollection featureCollection = xPlanDao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
			Date sortDate = sortPropertyReader.readSortDate(planType, version, featureCollection);
			planId2sortDate.put(plan.getId(), sortDate);
			sortPropertyDbUpdater.updateSortProperty(sortDate, plan);
		}
		return planId2sortDate;
	}

}
