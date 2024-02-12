package de.latlon.xplanbox.cli.validate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidateFileSubcommandTest {

	@TempDir
	private static Path tempDir;

	private static Path testPlan;

	@BeforeAll
	public static void copyTestplan() throws IOException {
		String testPlanName = "BPlan005_6-0.zip";
		testPlan = tempDir.resolve(testPlanName);
		Files.copy(ValidateFileSubcommandTest.class.getResourceAsStream(testPlanName), testPlan);
	}

	@Test
	public void test_validate_help() {
		ValidateFileSubcommand app = new ValidateFileSubcommand();
		CommandLine cmd = new CommandLine(app);

		int exitCode = cmd.execute("help");
		assertEquals(0, exitCode);
	}

	@Test
	public void test_validate_file() {
		ValidateFileSubcommand app = new ValidateFileSubcommand();
		CommandLine cmd = new CommandLine(app);

		int exitCode = cmd.execute("-f=" + testPlan);
		assertEquals(0, exitCode);
	}

}
