package de.latlon.xplanbox.cli.manage;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "manage", description = "Manage plans",
		subcommands = { CommandLine.HelpCommand.class, ListSubcommand.class, ImportSubcommand.class,
				ExportSubcommand.class, DeleteSubcommand.class, CreateMetadataSubcommand.class })
public class ManageCommand implements Runnable {

	@Override
	public void run() {
	}

}
