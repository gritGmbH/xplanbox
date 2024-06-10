/*-
 * #%L
 * xplan-cli - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.admin.config;

import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * Spring Application Context for initialising xplan-update-data-cli components.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Configuration
@Import(CommonContext.class)
@ComponentScan("de.latlon.xplanbox.cli.admin.resynthesize")
public class ReSynthesizerContext {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public XPlanManagerDao xPlanDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanDbAdapter xPlanDbAdapter,
			XPlanSynthesizer xPlanSynthesizer) {
		return new XPlanManagerDao(managerWorkspaceWrapper, xPlanDbAdapter, xPlanSynthesizer, null, null,
				applicationEventPublisher);
	}

	@Bean
	public XPlanSynthesizer XPlanSynthesizer(ManagerConfiguration managerConfiguration) {
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor(
				managerConfiguration.getSynthesizerConfigurationDirectory());
		return new XPlanSynthesizer(synRulesAccessor);

	}

	@Bean
	public SortPropertyReader sortPropertyReader(ManagerConfiguration managerConfiguration) {
		return new SortPropertyReader(managerConfiguration.getSortConfiguration());

	}

}
