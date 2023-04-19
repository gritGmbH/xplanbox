package de.latlon.xplan.core.manager.db.repository;

import de.latlon.xplan.core.manager.db.config.HsqlJpaContext;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.config.PostgisJpaContext;
import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.model.ArtefactType;
import de.latlon.xplan.core.manager.db.model.Bereich;
import de.latlon.xplan.core.manager.db.model.Feature;
import de.latlon.xplan.core.manager.db.model.Plan;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
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
import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles("test-hsql")
@ContextConfiguration(classes = { JpaContext.class, HsqlJpaContext.class, PostgisJpaContext.class })
@Transactional
public class PlanRepositoryTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PlanRepository planRepository;

	@Test
	public void verify_injectedComponentsAreNotNull() {
		assertThat(dataSource, is(notNullValue()));
		assertThat(entityManager, is(notNullValue()));
		assertThat(planRepository, is(notNullValue()));
	}

	@Test
	@Commit
	public void verify_saveAndFindById() throws ParseException {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		Plan plan = new Plan().importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false)
				.bereiche(Collections.singleton(bereich)).features(Collections.singleton(feature))
				.artefacts(Collections.singleton(artefact));
		// Not running with HSQL DB (@ActiveProfiles("test-hsql"))
		// setBbox(plan);
		assertNull(plan.getId());
		Plan savedPlan = planRepository.save(plan);
		assertNotNull(savedPlan.getId());
		Optional<Plan> optionalFoundPlan = planRepository.findById(savedPlan.getId());
		assertTrue(optionalFoundPlan.isPresent());
		Plan foundPlan = optionalFoundPlan.get();
		assertNotNull(foundPlan);
		assertNotNull(foundPlan.getId());
	}

	@Test
	@Commit
	public void verify_findByName() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		String name = "saveAndFindByName";

		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		Plan plan = new Plan().name(name).importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false)
				.bereiche(Collections.singleton(bereich)).features(Collections.singleton(feature))
				.artefacts(Collections.singleton(artefact));
		planRepository.save(plan);

		List<Plan> existingPlan = planRepository.findByName(name);
		assertFalse(existingPlan.isEmpty());

		List<Plan> nonExistingPlan = planRepository.findByName("unknown");
		assertTrue(nonExistingPlan.isEmpty());
	}

	@Test
	@Commit
	public void verify_findByNameLike() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		String name = "saveAndFindByLikeName";
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		Plan plan = new Plan().name(name).importDate(new Date()).version(XPLAN_51).type(BP_Plan).hasRaster(false)
				.bereiche(Collections.singleton(bereich)).features(Collections.singleton(feature))
				.artefacts(Collections.singleton(artefact));
		planRepository.save(plan);
		List<Plan> existingPlan = planRepository.findByNameLike("iKEnAme");
		assertFalse(existingPlan.isEmpty());

		List<Plan> nonExistingPlan = planRepository.findByNameLike("unknown");
		assertTrue(nonExistingPlan.isEmpty());
	}

	@Test
	@Commit
	public void verify_findByPlanWithMoreRecentRasterPlan() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		Date wmsSortDate = new Date();
		Plan plan = new Plan().name("saveAndFindPlanWithMoreRecentRasterPlan").importDate(new Date()).version(XPLAN_51)
				.type(BP_Plan).hasRaster(true).wmssortdate(wmsSortDate).bereiche(Collections.singleton(bereich))
				.features(Collections.singleton(feature)).artefacts(Collections.singleton(artefact));
		planRepository.save(plan);

		Date tomorrow = new Date(wmsSortDate.getTime() - (1000 * 60 * 60 * 24));
		List<Plan> existingPlan = planRepository.findByPlanWithMoreRecentRasterPlan(tomorrow);
		assertFalse(existingPlan.isEmpty());

		Date yesterday = new Date(wmsSortDate.getTime() + (1000 * 60 * 60 * 24));
		List<Plan> nonExistingPlan = planRepository.findByPlanWithMoreRecentRasterPlan(yesterday);
		assertTrue(nonExistingPlan.isEmpty());
	}

	@Test
	@Commit
	public void verify_existsPlanByNameAndPlanstatus() {
		assertFalse(TestTransaction.isFlaggedForRollback());
		String name = "existsPlanByNameAndPlanstatus";
		String planstatus = PlanStatus.FESTGESTELLT.name();
		Bereich bereich = new Bereich().nummer("0").name("test");
		Feature feature = new Feature().num(1).fid("123");
		Artefact artefact = new Artefact().num(1).artefacttype(ArtefactType.XPLANGML).mimetype("text/xml")
				.data("test".getBytes(UTF_8)).filename("test.xml");
		Date wmsSortDate = new Date();
		Plan plan = new Plan().name(name).importDate(new Date()).version(XPLAN_51).type(BP_Plan).planstatus(planstatus)
				.hasRaster(true).wmssortdate(wmsSortDate).bereiche(Collections.singleton(bereich))
				.features(Collections.singleton(feature)).artefacts(Collections.singleton(artefact));
		planRepository.save(plan);

		boolean planExists = planRepository.existsPlanByNameAndPlanstatus(name, planstatus);
		assertTrue(planExists);

		boolean notPlanExistsName = planRepository.existsPlanByNameAndPlanstatus("unknown", planstatus);
		assertFalse(notPlanExistsName);

		boolean notPlanExistsPlanstatus = planRepository.existsPlanByNameAndPlanstatus(name, IN_AUFSTELLUNG.name());
		assertFalse(notPlanExistsPlanstatus);
	}

	private static void setBbox(Plan plan) throws ParseException {
		Geometry bbox = new WKTReader().read("POLYGON((10 53, 11 53, 11 54, 10 54, 10 53))");
		plan.bbox(bbox);
	}

}