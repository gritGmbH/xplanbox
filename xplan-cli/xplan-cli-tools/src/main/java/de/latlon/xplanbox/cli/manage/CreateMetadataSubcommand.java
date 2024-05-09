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
package de.latlon.xplanbox.cli.manage;

import de.latlon.xplanbox.cli.XPlanCliSubcommand;
import de.latlon.xplanbox.cli.manage.config.ManageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "create-metadata", description = "Create service metadata records.",
		subcommands = { CommandLine.HelpCommand.class })
public class CreateMetadataSubcommand extends XPlanCliSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(CreateMetadataSubcommand.class);

	@CommandLine.Option(names = { "-i", "--id" }, split = ",",
			description = "Die ID des Plans zu dem der Service-Metadatensatz generiert werden soll.", required = true)
	private Integer[] ids;

	@Override
	protected void register(AnnotationConfigApplicationContext applicationContext) {
		applicationContext.register(ManageContext.class);
	}

	@Override
	protected Integer callSubcommand(ApplicationContext applicationContext) {
		ServiceMetadataRecordCreator serviceMetadataRecordCreator = applicationContext
			.getBean(ServiceMetadataRecordCreator.class);

		if (serviceMetadataRecordCreator == null) {
			LOG.error("ServiceMetadataRecordCreator is not available Check configuration.");
			return 1;
		}
		List<Integer> planIds = Arrays.asList(ids);
		if (planIds == null || planIds.isEmpty()) {
			try {
				serviceMetadataRecordCreator.createServiceMetadataRecords();
			}
			catch (Exception e) {
				LOG.error("Bei der Erstellung des Service Metadatensatz fuer alle Plaene ist ein Fehler aufgetreten: "
						+ e.getMessage(), e);
				return 1;
			}
		}
		else {
			for (Integer planId : planIds) {
				try {
					serviceMetadataRecordCreator.createServiceMetadataRecords(planId);
				}
				catch (Exception e) {
					LOG.error("Bei der Erstellung des Service Metadatensatz fuer den Plan mit der ID " + planId
							+ " ist ein Fehler aufgetreten: " + e.getMessage(), e);
					return 1;
				}
			}
		}
		return 0;
	}

}
