package de.latlon.xplanbox.cli.admin.districtupdate;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
public class DistrictUpdater {

	private static final Logger LOG = LoggerFactory.getLogger(DistrictUpdater.class);

	@Autowired
	@Lazy
	private XPlanDao dao;

	@Transactional(rollbackOn = Exception.class)
	public void updateDistricts() throws Exception {
		List<XPlan> plans = dao.getXPlanList();
		for (XPlan plan : plans) {
			LOG.info("Update district of plan with id {}", plan.getId());
			FeatureCollection featureCollection = dao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			String district = retrieveDistrict(featureCollection, planType);
			dao.updateDistrict(plan, district);
		}
	}

}
