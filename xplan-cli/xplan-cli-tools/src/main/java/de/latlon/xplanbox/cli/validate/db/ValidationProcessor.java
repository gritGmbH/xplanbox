/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.validate.db;

import de.latlon.xplanbox.cli.validate.db.domain.ValidationResultSummary;
import de.latlon.xplanbox.cli.validate.db.domain.XPlanWithFeatureCollection;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.Collections;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
public class ValidationProcessor implements ItemProcessor<XPlanWithFeatureCollection, ValidationResultSummary> {

	private static final Logger LOG = LoggerFactory.getLogger(ValidationProcessor.class);

	private SemanticValidator validator;

	public ValidationProcessor(SemanticValidator validator) {
		this.validator = validator;
	}

	@Override
	public ValidationResultSummary process(XPlanWithFeatureCollection xPlanWithFeatureCollection) {
		try {
			LOG.info("Validate xplan with id {}", xPlanWithFeatureCollection.getId());
			ValidatorResult validatorReport = validator.validateSemantic(xPlanWithFeatureCollection,
					Collections.emptyList());
			return new ValidationResultSummary(xPlanWithFeatureCollection.getId(),
					xPlanWithFeatureCollection.getXp_version(), xPlanWithFeatureCollection.getName(),
					xPlanWithFeatureCollection.getDistrict(), validatorReport);
		}
		catch (Exception e) {
			LOG.error("Plan could not be validated", e);
			throw e;
		}
	}

}
