/*-
 * #%L
 * xplan-core-manager-db - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.core.manager.db.repository;

import de.latlon.xplan.core.manager.db.config.HsqlJpaContext;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.config.PostgisJpaContext;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactId;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Plan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.core.manager.db.model.ArtefactType.XPLANGML;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test-hsql")
@ContextConfiguration(classes = { JpaContext.class, HsqlJpaContext.class, PostgisJpaContext.class })
@Transactional
class ArtefactRepositoryTest {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private ArtefactRepository artefactRepository;

	@Test
	@Commit
	void verify_findByPlanIdAndFilename() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = createPlan();
		planRepository.save(plan);

		Optional<Artefact> artefact = artefactRepository.findByPlanAndFilename(plan.getId(), "image.png");
		assertEquals(RASTERBASIS, artefact.get().getArtefacttype());
	}

	@Test
	@Commit
	void verify_findXPlanGmlByPlan() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = createPlan();
		planRepository.save(plan);

		Optional<Artefact> xPlanGmlByPlan = artefactRepository.findXPlanGmlByPlan(plan.getId());
		assertTrue(xPlanGmlByPlan.isPresent());
	}

	@Test
	@Commit
	void verify_findAllByPlanId() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = createPlan();
		planRepository.save(plan);

		Stream<Artefact> artefacts = artefactRepository.findAllByPlanId(plan.getId());
		assertTrue(artefacts.count() == plan.getArtefacts().size());
	}

	@Test
	@Commit
	void verify_findAllFileNamesByPlanId() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = createPlan();
		planRepository.save(plan);

		List<String> fileNames = artefactRepository.findAllFileNamesByPlanId(plan.getId());
		assertTrue(fileNames.size() == plan.getArtefacts().size());
	}

	private static Plan createPlan() {
		Plan plan = new Plan().importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false);
		Set<Artefact> artefacts = new HashSet<>();
		artefacts.add(createArtefact(plan, "xplan.gml", XPLANGML));
		artefacts.add(createArtefact(plan, "image.png", RASTERBASIS));
		plan.artefacts(artefacts);
		return plan;
	}

	private static Artefact createArtefact(Plan plan, String image, ArtefactType artefactType) {
		ArtefactId artefactId = new ArtefactId().plan(plan).filename(image);
		byte[] bytes = "test".getBytes(UTF_8);
		return new Artefact().id(artefactId)
			.num(1)
			.artefacttype(artefactType)
			.mimetype("text/xml")
			.length(Long.valueOf(bytes.length))
			.data(bytes);
	}

}
