/*-
 * #%L
 * xplan-update-data-cli - update of database
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
package de.latlon.xplanbox.cli.admin;

import de.latlon.xplanbox.cli.XPlanCliSubcommand;
import de.latlon.xplanbox.cli.admin.config.DistrictUpdateContext;
import de.latlon.xplanbox.cli.admin.districtupdate.DistrictUpdater;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * Update tool to update the district of all plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "district-update", description = "Update column district of table xplanmgr.plans.",
		subcommands = { CommandLine.HelpCommand.class })
public class DistrictUpdateSubcommand extends XPlanCliSubcommand {

	@Override
	protected void register(AnnotationConfigApplicationContext applicationContext) {
		applicationContext.register(DistrictUpdateContext.class);
	}

	@Override
	protected Integer callSubcommand(ApplicationContext applicationContext) throws Exception {
		DistrictUpdater districtUpdater = applicationContext.getBean(DistrictUpdater.class);
		districtUpdater.updateDistricts();
		return 0;
	}

}
