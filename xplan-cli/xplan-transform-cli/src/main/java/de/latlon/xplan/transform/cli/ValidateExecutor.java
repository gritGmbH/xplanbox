/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.transform.cli.result.CsvTransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformationResultWriter;

import java.nio.file.Path;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidateExecutor {

	private XPlanDao xPlanDao;

	private final TransformingValidator validator;

	public ValidateExecutor(XPlanDao xPlanDao, TransformingValidator validator) {
		this.xPlanDao = xPlanDao;
		this.validator = validator;
	}

	/**
	 * Transforms all available plans, validates each of them and writes a validation
	 * report.
	 * @param outDir the directory to store the validation reports (must exist)
	 * @throws Exception
	 */
	public void validateAll(Path outDir) throws Exception {
		try (TransformationResultWriter transformationResultWriter = new CsvTransformationResultWriter(outDir)) {
			List<XPlan> plans = xPlanDao.getXPlanList(false);
			for (XPlan plan : plans) {
				XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
				if (XPLAN_41.equals(version)) {
					validator.validate(plan, transformationResultWriter);
				}
			}
		}
	}

}
