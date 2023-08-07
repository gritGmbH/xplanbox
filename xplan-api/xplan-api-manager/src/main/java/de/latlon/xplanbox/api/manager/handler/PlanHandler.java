/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.manager.database.PlanNotFoundException;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanId;
import de.latlon.xplanbox.api.commons.exception.InvalidPlanIdSyntax;
import de.latlon.xplanbox.api.commons.exception.UnsupportedParameterValue;
import de.latlon.xplanbox.api.manager.exception.InvalidPlan;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstand;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static de.latlon.xplan.manager.web.shared.PlanStatus.findByLegislationStatusCode;
import static java.lang.Integer.parseInt;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class PlanHandler {

	private static final Logger LOG = getLogger(PlanHandler.class);

	private static final String DELETE_MSG = "Plan mit ID %s entfernt.";

	@Autowired
	private XPlanArchiveCreator archiveCreator;

	@Autowired
	private XPlanValidator xPlanValidator;

	@Autowired
	private XPlanInsertManager xPlanInsertManager;

	@Autowired
	private XPlanDeleteManager xPlanDeleteManager;

	@Autowired
	private XPlanDao xPlanDao;

	@Autowired
	private XPlanExporter xPlanExporter;

	public List<XPlan> importPlan(XPlanArchive xPlanArchive, String xFileName, ValidationSettings validationSettings,
			String internalId, String planStatus) throws Exception {
		LOG.info("Importing plan using validation settings '{}'", validationSettings);
		ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport(validationSettings, xPlanArchive,
				xFileName);
		if (!validatorReport.isReportValid()) {
			throw new InvalidPlan(validatorReport, xFileName);
		}
		LOG.info("Plan is valid. Importing plan into storage for '{}'", planStatus);
		AdditionalPlanData metadata = createAdditionalPlanData(xPlanArchive, planStatus);
		List<Integer> planIds = xPlanInsertManager.importPlan(xPlanArchive, null, false, true, internalId, metadata);
		List<XPlan> plansById = findPlansById(planIds);
		LOG.info("Plan successfully imported. Ids: {}",
				plansById.stream().map(plan -> plan.getId()).collect(Collectors.joining(",")));
		return plansById;
	}

	public StatusMessage deletePlan(String planId) throws Exception {
		LOG.info("Deleting plan with Id {}", planId);
		xPlanDeleteManager.delete(planId);
		return new StatusMessage().message(String.format(DELETE_MSG, planId));
	}

	public StreamingOutput exportPlan(String planId) throws Exception {
		try {
			int planIdAsInt = checkIdAndConvertIdToInt(planId);
			LOG.info("Exporting plan with Id '{}'", planId);
			if (!xPlanDao.existsPlan(planIdAsInt)) {
				throw new InvalidPlanId(planId);
			}
			List<Artefact> artefacts = xPlanDao.retrieveAllXPlanArtefacts(planId);
			return outputStream -> xPlanExporter.export(outputStream, artefacts);
		}
		catch (PlanNotFoundException e) {
			throw new InvalidPlanId(planId);
		}
	}

	public XPlan findPlanById(String planId) throws InvalidPlanIdSyntax, InvalidPlanId {
		LOG.info("Finding plan by Id '{}'", planId);
		int id = checkIdAndConvertIdToInt(planId);
		return findPlanById(id);
	}

	public List<XPlan> findPlansByName(String planName) {
		LOG.info("Finding plan by name '{}'", planName);
		return xPlanDao.getXPlanByName(planName);
	}

	public List<XPlan> findPlans(String planName) throws Exception {
		LOG.info("Searching plan by name '{}'", planName);
		if (planName != null)
			return xPlanDao.getXPlansLikeName(planName);
		return xPlanDao.getXPlanList();
	}

	public List<XPlan> findPlansById(List<Integer> planIds) throws Exception {
		LOG.info("Finding plan by IDs '{}'", planIds);
		List<XPlan> plans = new ArrayList<>();
		for (int planId : planIds) {
			XPlan planById = findPlanById(planId);
			plans.add(planById);
		}
		return plans;
	}

	private XPlan findPlanById(int id) throws InvalidPlanId {
		XPlan xPlanById = xPlanDao.getXPlanById(id);
		if (xPlanById == null) {
			throw new InvalidPlanId(id);
		}
		return xPlanById;
	}

	private AdditionalPlanData createAdditionalPlanData(XPlanArchive xPlanArchive, String requestedPlanStatus)
			throws UnsupportedParameterValue, XMLStreamException, UnknownCRSException {
		AdditionalPlanData metadata = new AdditionalPlanData();
		PlanStatus planStatus;
		if (requestedPlanStatus == null) {
			PlanStatus planStatusFromPlan = determinePlanStatusByRechtsstand(xPlanArchive);
			planStatus = PlanStatus.valueOf(planStatusFromPlan.name());
		}
		else {
			try {
				planStatus = PlanStatus.valueOf(requestedPlanStatus);
			}
			catch (IllegalArgumentException e) {
				throw new UnsupportedParameterValue("planStatus", requestedPlanStatus);
			}
		}
		metadata.setPlanStatus(planStatus);
		return metadata;
	}

	private PlanStatus determinePlanStatusByRechtsstand(XPlanArchive xPlanArchive)
			throws XMLStreamException, UnknownCRSException {
		XPlanFeatureCollection fc = XPlanGmlParserBuilder.newBuilder()
			.withSkipResolveReferences(true)
			.build()
			.parseXPlanFeatureCollection(xPlanArchive);
		String legislationStatus = retrieveRechtsstand(fc.getFeatures(), fc.getType());
		if (legislationStatus != null && !legislationStatus.isEmpty()) {
			try {
				int rechtsstand = parseInt(legislationStatus);
				return findByLegislationStatusCode(xPlanArchive.getType().name(), rechtsstand);
			}
			catch (NumberFormatException e) {
				LOG.info("Rechtsstand '{}' could not be parsed as integer.", legislationStatus);
			}
		}
		return IN_AUFSTELLUNG;
	}

	private int checkIdAndConvertIdToInt(String planId) throws InvalidPlanIdSyntax {
		try {
			return Integer.parseInt(planId);
		}
		catch (NumberFormatException e) {
			throw new InvalidPlanIdSyntax(planId);
		}
	}

}
