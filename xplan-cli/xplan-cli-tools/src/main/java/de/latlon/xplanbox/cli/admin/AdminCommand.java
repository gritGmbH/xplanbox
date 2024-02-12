package de.latlon.xplanbox.cli.admin;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "admin", description = "Administrate xPlanBox",
		subcommands = { CommandLine.HelpCommand.class, DistrictUpdateSubcommand.class,
				EvaluationSchemaSynchronizerSubcommand.class, ReSynthesizerSubcommand.class,
				SortdateUpdateSubcommand.class })
public class AdminCommand implements Runnable {

	@Override
	public void run() {
	}

}
