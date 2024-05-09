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

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.cs.coordinatesystems.ICRS;

import java.util.Date;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class PlanImportData {

	private final XPlanArchive xPlanArchive;

	private final PlanStatus planStatus;

	private final AdditionalPlanData xPlanMetadata;

	private final Date sortDate;

	private final ICRS crs;

	private final XPlanFeatureCollection xPlanFC;

	private final String internalId;

	private int planId;

	public PlanImportData(XPlanArchive xPlanArchive, PlanStatus planStatus, AdditionalPlanData xPlanMetadata,
			Date sortDate, ICRS crs, XPlanFeatureCollection xPlanFc) {
		this(xPlanArchive, planStatus, xPlanMetadata, sortDate, crs, xPlanFc, null);
	}

	public PlanImportData(XPlanArchive xPlanArchive, PlanStatus planStatus, AdditionalPlanData xPlanMetadata,
			Date sortDate, ICRS crs, XPlanFeatureCollection xPlanFc, String internalId) {
		this.xPlanArchive = xPlanArchive;
		this.planStatus = planStatus;
		this.xPlanMetadata = xPlanMetadata;
		this.sortDate = sortDate;
		this.crs = crs;
		this.xPlanFC = xPlanFc;
		this.internalId = internalId;
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

	public XPlanFeatureCollection getxPlanFC() {
		return xPlanFC;
	}

	public String getInternalId() {
		return internalId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

}
