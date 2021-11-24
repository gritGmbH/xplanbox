/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.database.PlanNotFoundException;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.exception.InvalidXPlanGmlOrArchive;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.exception.InvalidPlan;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanId;
import de.latlon.xplanbox.api.manager.exception.UnsupportedParameterValue;
import de.latlon.xplanbox.api.manager.v1.model.StatusMessage;
import org.apache.http.client.utils.URIBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.feature.FeatureCollectionManipulator.removeAllFeaturesExceptOfPlanFeature;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveLegislationStatus;
import static de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils.parseXPlanFeatureCollection;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
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

	public XPlan importPlan(File uploadedPlan, String xFileName, ValidationSettings validationSettings,
			String internalId, String planStatus) throws Exception {
		LOG.info("Importing plan using validation settings '{}'", validationSettings);
		String validationName = validationSettings.getValidationName();
		XPlanArchive xPlanArchive = createArchive(uploadedPlan);
		ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport(validationSettings, validationName,
				xPlanArchive);
		if (!validatorReport.isReportValid()) {
			throw new InvalidPlan(validatorReport, xFileName);
		}
		LOG.info("Plan is valid. Importing plan into storage for '{}'", planStatus);
		AdditionalPlanData metadata = createAdditionalPlanData(xPlanArchive, planStatus);
		int planId = xPlanInsertManager.importPlan(xPlanArchive, null, false, false, true, null, internalId, metadata);

		XPlan planById = findPlanById(planId);
		LOG.info("Plan with Id {} successfully imported", planById);
		return planById;
	}

	private XPlanArchive createArchive(File uploadedPlan) throws InvalidXPlanGmlOrArchive {
		try {
			return archiveCreator.createXPlanArchiveFromZip(uploadedPlan);
		}
		catch (Exception e) {
			throw new InvalidXPlanGmlOrArchive("Could not read attached file as XPlanArchive", e);
		}
	}

	public StatusMessage deletePlan(String planId) throws Exception {
		LOG.info("Deleting plan with Id {}", planId);
		xPlanDeleteManager.delete(planId);
		return new StatusMessage().message(String.format(DELETE_MSG, planId));
	}

	public StreamingOutput exportPlan(String planId) throws Exception {
		try {
			LOG.info("Exporting plan with Id '{}'", planId);
			XPlanArchiveContent archiveContent = xPlanDao.retrieveAllXPlanArtefacts(planId);
			return outputStream -> xPlanExporter.export(outputStream, archiveContent);
		}
		catch (PlanNotFoundException e) {
			throw new InvalidPlanId(planId);
		}
	}

	public XPlan findPlanById(String planId) throws Exception {
		LOG.info("Find plan by Id '{}'", planId);
		int id = Integer.parseInt(planId);
		return findPlanById(id);
	}

	public List<XPlan> findPlansByName(String planName) throws Exception {
		LOG.info("Find plan by name '{}'", planName);
		return xPlanDao.getXPlanByName(planName);
	}

	public List<XPlan> findPlans(String planName) throws Exception {
		LOG.info("Search plan by name '{}'", planName);
		if (planName != null)
			return xPlanDao.getXPlansLikeName(planName);
		return xPlanDao.getXPlanList(false);
	}

	private XPlan findPlanById(int id) throws Exception {
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
		XPlanFeatureCollection fc = parseXPlanFeatureCollection(xPlanArchive);
		removeAllFeaturesExceptOfPlanFeature(fc);

		String legislationStatus = retrieveLegislationStatus(fc.getFeatures(), fc.getType());
		if (legislationStatus != null && !legislationStatus.isEmpty()) {
			try {
				int rechtsstand = parseInt(legislationStatus);
				return PlanStatus.findByLegislationStatusCode(rechtsstand);
			}
			catch (NumberFormatException e) {
				LOG.info("Rechtsstand '{}' could not be parsed as integer.", legislationStatus);
			}
		}
		return IN_AUFSTELLUNG;
	}

}
