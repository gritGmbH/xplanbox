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
package de.latlon.xplanbox.cli.admin;

import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplanbox.cli.XPlanCliSubcommand;
import de.latlon.xplanbox.cli.admin.config.SortdateUpdateContext;
import de.latlon.xplanbox.cli.admin.sortdate.SortDateUpdater;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * Updates the sort property.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Component
@CommandLine.Command(name = "sortdate-update", description = "Update sort date.",
		subcommands = { CommandLine.HelpCommand.class })
public class SortdateUpdateSubcommand extends XPlanCliSubcommand {

	@Override
	protected void register(AnnotationConfigApplicationContext applicationContext) {
		applicationContext.register(SortdateUpdateContext.class);
	}

	/**
	 * Retrieves all plans from the manager store, parses the date from the plan with the
	 * help of the {@link SortPropertyReader} and updates the sort property in the syn
	 * schema data and reorders the wms rasterlayers.
	 */
	@Override
	protected Integer callSubcommand(ApplicationContext applicationContext) throws Exception {
		SortDateUpdater sortDateUpdater = applicationContext.getBean(SortDateUpdater.class);
		sortDateUpdater.sortDates();
		return 0;
	}

}
