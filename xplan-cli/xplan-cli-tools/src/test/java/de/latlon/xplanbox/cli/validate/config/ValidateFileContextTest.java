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
package de.latlon.xplanbox.cli.validate.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Tests the application context - sees if it loads
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */

public class ValidateFileContextTest {

	@BeforeAll
	public static void createMissingDirectory() throws URISyntaxException {
		URL path = ValidateFileContextTest.class.getProtectionDomain().getCodeSource().getLocation();
		File parentFile = new File(path.toURI()).getParentFile();
		createSubDirectories(parentFile);
	}

	@Test
	public void testLoadApplicationContextAndInitializeBeans() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(ValidateFileContext.class);
		context.refresh();
		context.close();
	}

	private static void createSubDirectories(File parent) {
		File etc = createEtcDirectory(parent);
		createRulesDirectory(etc);
	}

	private static File createEtcDirectory(File parent) {
		File newEtc = new File(parent, "etc");
		if (!newEtc.exists())
			newEtc.mkdir();
		return newEtc;
	}

	private static void createRulesDirectory(File etc) {
		File rules = new File(etc, "rules");
		if (!rules.exists()) {
			rules.mkdir();
		}
	}

}
