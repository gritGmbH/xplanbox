package de.latlon.xplan.update.config;

import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.update.artefacts.ArtefactsTableUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Import(ApplicationContext.class)
public class ArtefactsTableUpdaterApplicationApplicationContext {

	@Autowired
	private PlanRepository planRepository;

	@Bean
	public ArtefactsTableUpdater artefactsTableUpdater() {
		return new ArtefactsTableUpdater(planRepository);
	}

}
