package de.latlon.xplan.core.manager.db.repository;

import de.latlon.xplan.core.manager.db.config.HsqlJpaConfig;
import de.latlon.xplan.core.manager.db.config.JpaConfig;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Bereich;
import de.latlon.xplan.core.manager.db.model.Feature;
import de.latlon.xplan.core.manager.db.model.Plan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { JpaConfig.class, HsqlJpaConfig.class })
@Transactional
public class PlanRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PlanRepository planRepository;

	// @Test
	public void injectedComponentsAreNotNull() {
		assertThat(dataSource, is(notNullValue()));
		assertThat(entityManager, is(notNullValue()));
		assertThat(planRepository, is(notNullValue()));
	}

	@Test
	@Commit
	public void saveAndFindById() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Plan plan = new Plan().importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false);
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		plan.bereiche(Collections.singletonList(bereich)).features(Collections.singletonList(feature))
				.artefacts(Collections.singletonList(artefact));
		assertNull(plan.getId());
		Plan savedPlan = planRepository.save(plan);
		assertNotNull(savedPlan.getId());
		Optional<Plan> optionalFoundPlan = planRepository.findById(savedPlan.getId());
		assertTrue(optionalFoundPlan.isPresent());
		Plan foundPlan = optionalFoundPlan.get();
		assertNotNull(foundPlan);
		assertNotNull(foundPlan.getId());
	}

}