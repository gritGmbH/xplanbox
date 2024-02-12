package de.latlon.xplanbox.cli.validate;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "validate", description = "Validate a plan or all plans in a database", subcommands = {
		CommandLine.HelpCommand.class, ValidateFileSubcommand.class, ValidateFromDatabaseSubcommand.class })
public class ValidateCommand implements Runnable {

	@Override
	public void run() {
	}

}