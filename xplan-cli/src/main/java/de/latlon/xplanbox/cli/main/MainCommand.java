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
package de.latlon.xplanbox.cli.main;

import de.latlon.xplanbox.cli.admin.AdminCommand;
import de.latlon.xplanbox.cli.manage.ManageCommand;
import de.latlon.xplanbox.cli.validate.ValidateCommand;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "xpb", description = "XPlanCLI",
		subcommands = { CommandLine.HelpCommand.class, ValidateCommand.class, ManageCommand.class, AdminCommand.class })
public class MainCommand implements Runnable {

	@Override
	public void run() {
		new CommandLine(new MainCommand()).usage(System.out);
	}

}
