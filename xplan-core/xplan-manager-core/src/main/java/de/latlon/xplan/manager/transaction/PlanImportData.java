package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.FeatureCollection;

import java.util.Date;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class PlanImportData {

	private final XPlanArchive xPlanArchive;

	private final PlanStatus planStatus;

	private final AdditionalPlanData xPlanMetadata;

	private final Date sortDate;

	private final ICRS crs;

	private final FeatureCollection synFc;

	private final XPlanFeatureCollection xPlanFC;

	private int planId;

	public PlanImportData(XPlanArchive xPlanArchive, PlanStatus planStatus, AdditionalPlanData xPlanMetadata,
			Date sortDate, ICRS crs, FeatureCollection synFc, XPlanFeatureCollection xPlanFc) {
		this.xPlanArchive = xPlanArchive;
		this.planStatus = planStatus;
		this.xPlanMetadata = xPlanMetadata;
		this.sortDate = sortDate;
		this.crs = crs;
		this.synFc = synFc;
		this.xPlanFC = xPlanFc;
	}

	public XPlanArchive getxPlanArchive() {
		return xPlanArchive;
	}

	public PlanStatus getPlanStatus() {
		return planStatus;
	}

	public AdditionalPlanData getxPlanMetadata() {
		return xPlanMetadata;
	}

	public Date getSortDate() {
		return sortDate;
	}

	public ICRS getCrs() {
		return crs;
	}

	public FeatureCollection getSynFc() {
		return synFc;
	}

	public XPlanFeatureCollection getxPlanFC() {
		return xPlanFC;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

}
