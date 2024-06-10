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
