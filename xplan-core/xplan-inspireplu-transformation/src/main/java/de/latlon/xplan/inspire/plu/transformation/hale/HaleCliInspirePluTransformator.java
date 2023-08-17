/*-
 * #%L
 * xplan-inspireplu-transformation - Transformation XPlanGML nach INSPIRE PLU
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.inspire.plu.transformation.hale;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.hale.HaleTransformer;
import de.latlon.xplan.commons.hale.TransformationException;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleCliInspirePluTransformator implements InspirePluTransformator {

	private final Path haleProjectDirectory;

	private final HaleTransformer haleTransformator;

	private static final Map<XPlanVersion, String> VERSION_TO_HALEPROJECT = Collections.unmodifiableMap(Stream
		.of(new SimpleEntry<>(XPLAN_41, "xplan41/xplanGml41-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_50, "xplan50/xplanGml50-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_51, "xplan51/xplanGml51-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_52, "xplan52/xplanGml52-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_53, "xplan53/xplanGml53-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_54, "xplan54/xplanGml54-inspirePlu.halex"),
				new SimpleEntry<>(XPLAN_60, "xplan60/xplanGml60-inspirePlu.halex"))
		.collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

	public HaleCliInspirePluTransformator(String haleCli, Path haleProjectDirectory) {
		this.haleProjectDirectory = haleProjectDirectory;
		this.haleTransformator = new HaleTransformer(haleCli);
	}

	@Override
	public Path transformToPlu(Path xPlanGml, XPlanVersion xPlanVersion) throws TransformationException {
		String haleProject = findHaleProject(xPlanVersion);
		Path targetFile = createTargetFile();
		haleTransformator.transform(haleProject, xPlanGml.toString(), targetFile.toString());
		return targetFile;
	}

	private Path createTargetFile() throws TransformationException {
		try {
			return Files.createTempFile("inspirePlu", ".xml");
		}
		catch (IOException e) {
			throw new TransformationException("Could not create target file", e);
		}
	}

	private String findHaleProject(XPlanVersion xPlanVersion) throws TransformationException {
		String pathToHaleProject = VERSION_TO_HALEPROJECT.get(xPlanVersion);
		if (pathToHaleProject != null)
			return resolvePathToHale(xPlanVersion, pathToHaleProject);
		throw new TransformationException(
				"Could not find HALE project from XPlan GML " + xPlanVersion.name() + " to INSPIRE PLU");
	}

	private String resolvePathToHale(XPlanVersion xPlanVersion, String haleProjectPath) throws TransformationException {
		Path haleProject = haleProjectDirectory.resolve(haleProjectPath);
		if (Files.exists(haleProject))
			return haleProject.toString();
		throw new TransformationException(
				"Could not find HALE project from XPlan GML " + xPlanVersion.name() + " to INSPIRE PLU");
	}

}
