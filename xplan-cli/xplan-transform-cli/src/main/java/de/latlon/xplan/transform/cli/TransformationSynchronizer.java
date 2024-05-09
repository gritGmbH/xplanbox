/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.cli.DatabaseUtils;
import de.latlon.xplan.commons.cli.Operation;
import de.latlon.xplan.commons.cli.SynchronizationException;
import de.latlon.xplan.commons.cli.Synchronizer;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.transform.cli.result.FileTransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformingValidationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.transform.cli.TransformApplicationRunner.LOG_TABLE_NAME;

/**
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Deprecated
public class TransformationSynchronizer implements Synchronizer {

	private static final Logger LOG = LoggerFactory.getLogger(TransformationSynchronizer.class);

	private final XPlanDao xPlanDao;

	private final TransformingValidator transformingValidator;

	private Path outDir;

	public TransformationSynchronizer(XPlanDao xPlanDao, TransformingValidator transformingValidator, Path outDir) {
		this.xPlanDao = xPlanDao;
		this.transformingValidator = transformingValidator;
		this.outDir = outDir;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void synchronize(Connection conn, int oldid, int newid, int xPlanManagerId, String planVersion,
			String oldPlanStatus, String newPlanStatus, Operation operation) throws SynchronizationException {
		if (!XPLAN_41.equals(XPlanVersion.valueOf(planVersion))) {
			return;
		}
		LOG.info("Synchronize plan with id {}, operation {}", xPlanManagerId, operation);
		switch (operation) {
			case INSERT:
				insert(xPlanManagerId, newPlanStatus);
				break;
			case UPDATE:
				delete(conn, oldid, xPlanManagerId, oldPlanStatus);
				insert(xPlanManagerId, newPlanStatus);
				break;
			case DELETE:
				delete(conn, oldid, xPlanManagerId, oldPlanStatus);
				break;
			default:
				LOG.warn("Unsupported operation: {}", operation);
		}
	}

	private void insert(int xPlanManagerId, String newPlanStatus) throws SynchronizationException {
		try {
			XPlan xPlan = xPlanDao.getXPlanById(xPlanManagerId);

			TransformationResultWriter resultTransformationWriter = new FileTransformationResultWriter(outDir);
			TransformingValidationResult validationResult = transformingValidator.validate(xPlan,
					resultTransformationWriter);
			if (validationResult != null) {
				String id = xPlan.getId();
				SyntacticValidatorResult validatorResult = validationResult.getValidatorResult();
				if (validatorResult.isValid()) {
					LOG.info("Plan with id {} is valid.", id);
					XPlanType type = XPlanType.valueOf(xPlan.getType());
					PlanStatus planStatus = PlanStatus.findByMessage(newPlanStatus);
					XPlanFeatureCollection transformedXPlanFc = createXPlanFeatureCollection(
							validationResult.getTransformationResult(), type);
					xPlanDao.insertXPlanFeatureCollection(transformedXPlanFc, planStatus);
				}
				else {
					LOG.warn(
							"Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML: {}",
							id, validatorResult);
					throw new SynchronizationException(
							"Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML");
				}
			}
		}
		catch (SynchronizationException e) {
			throw e;
		}
		catch (Exception e) {
			throw new SynchronizationException(e);
		}
	}

	private void delete(Connection conn, int logTableId, int xPlanManagerId, String oldPlanStatus)
			throws SynchronizationException {
		try {
			Set<String> fids = selectIds(conn, logTableId);
			PlanStatus planStatus = PlanStatus.findByMessage(oldPlanStatus);
			xPlanDao.deleteXPlanFeatureCollection(xPlanManagerId, XPlanVersion.XPLAN_51, planStatus, fids);
		}
		catch (Exception e) {
			throw new SynchronizationException(e);
		}
	}

	private Set<String> selectIds(Connection conn, int logTableId) throws SynchronizationException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT fids FROM ").append(LOG_TABLE_NAME).append(" WHERE id = ? ");
			ps = conn.prepareStatement(sb.toString());
			ps.setInt(1, logTableId);
			LOG.debug("Execute select fids to delete: {}", ps);

			rs = ps.executeQuery();
			if (rs.next()) {
				Array array = rs.getArray(1);
				String[] fids = (String[]) array.getArray();
				return new HashSet<>(Arrays.asList(fids));
			}
		}
		catch (SQLException e) {
			throw new SynchronizationException("Could not select fids from " + LOG_TABLE_NAME, e);
		}
		finally {
			DatabaseUtils.closeQuietly(ps, rs);
		}
		return Collections.emptySet();
	}

	private XPlanFeatureCollection createXPlanFeatureCollection(TransformationResult transformationResult,
			XPlanType type) throws Exception {
		byte[] resultAsBytes = transformationResult.getTransformationResult();
		XPlanVersion resultVersion = transformationResult.getVersionOfTheResult();
		try (InputStream inputStream = new ByteArrayInputStream(resultAsBytes)) {
			return XPlanGmlParserBuilder.newBuilder()
				.build()
				.parseXPlanFeatureCollection(inputStream, resultVersion, type);
		}
	}

}
