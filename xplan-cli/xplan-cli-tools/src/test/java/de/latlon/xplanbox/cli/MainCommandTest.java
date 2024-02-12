package de.latlon.xplanbox.cli;

import de.latlon.xplanbox.cli.main.MainCommand;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */

public class MainCommandTest {

	@Test
	public void test_xpb_help() {
		MainCommand app = new MainCommand();
		CommandLine cmd = new CommandLine(app);

		int exitCode = cmd.execute("help");
		assertEquals(0, exitCode);
	}

}
