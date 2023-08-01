/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.core.manager.db.repository;

import de.latlon.xplan.core.manager.db.config.HsqlJpaContext;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.config.PostgisJpaContext;
import de.latlon.xplan.core.manager.db.model.Plan;
import de.latlon.xplan.core.manager.db.model.PlanwerkWmsMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test-hsql")
@ContextConfiguration(classes = { JpaContext.class, HsqlJpaContext.class, PostgisJpaContext.class })
@Transactional
public class PlanwerkWmsMetadataRepositoryTest {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Test
	@Commit
	public void verify_saveAndFindById() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = new Plan().importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false);
		planRepository.save(plan);

		PlanwerkWmsMetadata planwerkWmsMetadata = new PlanwerkWmsMetadata().plan(plan.getId())
			.title("title")
			.resourceidentifier("resourceidentifier")
			.servicemetadataurl("http:/test.de/servicemetadataurl")
			.datametadataurl("http:/test.de/datametadataurl");

		planwerkWmsMetadataRepository.save(planwerkWmsMetadata);

		assertTrue(planwerkWmsMetadata.getPlanId() == plan.getId());
	}

}