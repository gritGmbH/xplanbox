/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.shapefile;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.geotools.geometry.jts.Geometries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Creates shape files containing the bad geometries from a {@link ValidatorReport}
 *
 * @author Florian Bingel
 * @version $Revision: $, $Date: $
 */
public class ShapefileGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(ShapefileGenerator.class);

	/**
	 * Checks if the report contains at least one bad geometry
	 * @param report to check, never <code>null</code>
	 * @return <code>true</code> if the report contains at least one bad geometry,
	 * <code>false</code> otherwise
	 */
	public boolean hasBadGeometry(ValidatorReport report) {
		GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
		if (geometricValidatorResult != null) {
			return !geometricValidatorResult.getBadGeometries().isEmpty();
		}
		return false;
	}

	public void generateReport(ValidatorReport report, String validationName, Path directoryToCreateShapes)
			throws ReportGenerationException {
		checkParameters(report, validationName, directoryToCreateShapes);
		GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
		if (geometricValidatorResult != null) {
			writeShapefiles(validationName, directoryToCreateShapes, geometricValidatorResult.getBadGeometries(),
					geometricValidatorResult.getCrs());
		}
	}

	/**
	 * for every Geometry-Type (POINT, MULTIPOINT, LINESTRING, MULTILINESTRING, POLYGON,
	 * MULTIPOLYGON) one Shapefile is created.
	 * @param shapefileName the name of the Shapefiles, the Type of the Geometry will be
	 * appended to the name, never <code>null</code>
	 * @param directoryToCreateShapes the path to the Shapefiles, never <code>null</code>
	 * @param badGeometries List of the bad geometries
	 * @throws ReportGenerationException if the generation of the shapefile failed
	 */
	void writeShapefiles(String shapefileName, Path directoryToCreateShapes, List<BadGeometry> badGeometries, ICRS crs)
			throws ReportGenerationException {
		try {
			Map<Geometries, ShapefileBuilder> geomType2ShapefileBuilders = createShapefileBuilder(crs);
			addGeometriesToShapefileBuilder(badGeometries, geomType2ShapefileBuilders);
			writeShapefiles(shapefileName, directoryToCreateShapes, geomType2ShapefileBuilders);
		}
		catch (Exception e) {
			throw new ReportGenerationException("Shapefile could not be created!", e);
		}
	}

	private Map<Geometries, ShapefileBuilder> createShapefileBuilder(ICRS crs) throws ReportGenerationException {
		Map<Geometries, ShapefileBuilder> geomType2Builders = new HashMap<Geometries, ShapefileBuilder>();
		geomType2Builders.put(Geometries.POINT, new ShapefileBuilder(crs, Geometries.POINT));
		geomType2Builders.put(Geometries.LINESTRING, new ShapefileBuilder(crs, Geometries.LINESTRING));
		geomType2Builders.put(Geometries.POLYGON, new ShapefileBuilder(crs, Geometries.POLYGON));
		geomType2Builders.put(Geometries.MULTIPOINT, new ShapefileBuilder(crs, Geometries.MULTIPOINT));
		geomType2Builders.put(Geometries.MULTILINESTRING, new ShapefileBuilder(crs, Geometries.MULTILINESTRING));
		geomType2Builders.put(Geometries.MULTIPOLYGON, new ShapefileBuilder(crs, Geometries.MULTIPOLYGON));
		return geomType2Builders;
	}

	private void addGeometriesToShapefileBuilder(List<BadGeometry> badGeometries,
			Map<Geometries, ShapefileBuilder> geomType2Builders) {
		for (BadGeometry badGeometry : badGeometries) {
			Geometry geom = badGeometry.getOriginalGeometry();
			addGeometryToShapefileBuilder(geomType2Builders, badGeometry.getErrorsSingleString(), geom);
			badGeometry.getMarkerGeometries()
				.entrySet()
				.forEach(g -> addGeometryToShapefileBuilder(geomType2Builders, g.getKey(), g.getValue()));
		}
	}

	private void addGeometryToShapefileBuilder(Map<Geometries, ShapefileBuilder> geomType2Builders, String message,
			Geometry geom) {
		if (geom instanceof AbstractDefaultGeometry) {
			try {
				AbstractDefaultGeometry defaultGeometry = (AbstractDefaultGeometry) geom;
				org.locationtech.jts.geom.Geometry jtsGeom = defaultGeometry.getJTSGeometry();

				Geometries geomType = Geometries.get(jtsGeom);
				if (geomType2Builders.containsKey(geomType)) {
					geomType2Builders.get(geomType).addGeometry(jtsGeom, defaultGeometry.getId(), message);
				}
				else {
					LOG.warn("Geometry type " + geomType + " is not supported to be rendered in shp.");
				}
			}
			catch (Exception e) {
				LOG.warn("Geometry is broken (could not be written in shapefile): " + e.getMessage());
				LOG.trace("Geometry is broken (could not be written in shapefile).", e);
			}
		}
		else {
			LOG.info("Geometrie '" + geom.getId() + "' kann nicht in Shapefile geschrieben werden.");
		}
	}

	private void writeShapefiles(String shapefileName, Path directoryToCreateShapes,
			Map<Geometries, ShapefileBuilder> geomType2ShapefileBuilders) {
		for (Entry<Geometries, ShapefileBuilder> geomType2Builder : geomType2ShapefileBuilders.entrySet()) {
			writeFile(geomType2Builder.getValue(), directoryToCreateShapes, shapefileName, geomType2Builder.getKey());
		}
	}

	private void writeFile(ShapefileBuilder creator, Path directoryToCreateShapes, String validationName,
			Geometries geom) {
		if (creator.hasGeometry()) {
			String shpFileName = validationName + "_" + geom.getName();
			try {
				creator.writeToShapefile(directoryToCreateShapes, shpFileName);
			}
			catch (Exception e) {
				LOG.error("Beim Erzeugen des Shapefiles '{}' ist ein Fehler aufgetreten: {}", shpFileName,
						e.getMessage());
				LOG.trace("Shapefile could not be created!", e);
			}
		}
	}

	private void checkParameters(ValidatorReport report, String validationName, Path directoryToCreateShapes) {
		if (report == null)
			throw new IllegalArgumentException("ValidationReport must not be null");
		if (validationName == null)
			throw new IllegalArgumentException("ValidationName must not be null");
		if (directoryToCreateShapes == null)
			throw new IllegalArgumentException("DirectoryToCreateShapes must not be null");
	}

}
