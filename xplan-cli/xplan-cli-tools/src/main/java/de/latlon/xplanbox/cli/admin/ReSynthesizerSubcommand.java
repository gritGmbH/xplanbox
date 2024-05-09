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

import de.latlon.xplanbox.cli.XPlanCliSubcommand;
import de.latlon.xplanbox.cli.admin.config.ReSynthesizerContext;
import de.latlon.xplanbox.cli.admin.resynthesize.ReSynthesizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Re-synthesizes single or all available plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "resynthesize",
		description = "Reads the XPlanGML data and updates the re-synthesized data in the xplansyn schema.",
		subcommands = { CommandLine.HelpCommand.class })
public class ReSynthesizerSubcommand extends XPlanCliSubcommand {

	@CommandLine.Option(names = { "-i", "--id" }, split = ",",
			description = "The ID of a plan in the XPlanManager of the plan to re-synthesize. If missing all plans are re-synthesized.")
	private String[] ids;

	@Override
	protected void register(AnnotationConfigApplicationContext applicationContext) {
		applicationContext.register(ReSynthesizerContext.class);
	}

	@Override
	protected Integer callSubcommand(ApplicationContext applicationContext) throws Exception {
		ReSynthesizer reSynthesizer = applicationContext.getBean(ReSynthesizer.class);
		List<String> idList = ids != null ? Arrays.asList(ids) : Collections.emptyList();
		return reSynthesizer.resynthesize(idList);
	}

}
