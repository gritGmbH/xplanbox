/*-
 * #%L
 * xplan-webservices-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.core.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:guillemot@lat-lon.de">Marc Guillemot</a>
 * @since 8.0
 */
public class WarStructureVerifier {

	public void verifyLibsInWarFile(String warFileName) throws Exception {
		List<String> expectedLibs = getExpectedLibs();

		Path warPath = getWarFile(warFileName);
		List<String> actualLibs = getWarLibs(warPath);
		Files.write(Paths.get("target/actual-libs.txt"), actualLibs, StandardCharsets.UTF_8);

		assertThat(actualLibs).containsExactlyElementsOf(expectedLibs);
	}

	private List<String> getExpectedLibs() throws IOException, URISyntaxException {
		return Files
			.readAllLines(Paths.get(getClass().getResource("libs.expected.txt").toURI()), StandardCharsets.UTF_8)
			.stream()
			.filter(name -> !name.startsWith("xplan-"))
			.sorted()
			.collect(Collectors.toUnmodifiableList());
	}

	private Path getWarFile(String warPattern) throws IOException {
		Path targetFolder = Paths.get("target");
		List<Path> warFiles = Files
			.find(targetFolder, 1, (p, basicFileAttributes) -> p.getFileName().toString().matches(warPattern))
			.toList();
		assertThat(warFiles).describedAs("Incorrect number of war files found").hasSize(1);
		return warFiles.get(0);
	}

	private List<String> getWarLibs(Path warPath) throws IOException {
		URI uriWarFile = URI.create("jar:file:" + warPath.toAbsolutePath().toString());

		Map<String, ?> env = new HashMap<>();
		try (FileSystem zipfs = FileSystems.newFileSystem(uriWarFile, env)) {
			Path libFolder = zipfs.getPath("/WEB-INF/lib");
			return Files.list(libFolder)
				.map(Path::getFileName)
				.map(Path::toString)
				.filter(name -> !name.startsWith("xplan-"))
				.sorted()
				.toList();
		}
	}

}
