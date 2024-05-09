/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.shapefile;

import de.latlon.xplan.validator.report.ReportGenerationException;
import org.deegree.cs.coordinatesystems.ICRS;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.Geometries;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.geotools.data.DataUtilities.createType;

/**
 * Creates Shapefiles of different Types
 *
 * @deprecated will be removed in a future version.
 * @author Florian Bingel
 * @version $Revision: $, $Date: $
 */
@Deprecated
class ShapefileBuilder {

	private final SimpleFeatureType TYPE;

	private final SimpleFeatureBuilder featureBuilder;

	private final List<SimpleFeature> features = new ArrayList<>();

	private final Geometries GEOM_TYPE;

	/**
	 * @param crs the crs of the Geometries that will be added, never <code>null</code>
	 * @param GEOM_TYPE one of the types of org.geotools.geometry.jts.Geometries ( POINT,
	 * MULTIPOINT, LINESTRING, MULTILINESTRING, POLYGON, MULTIPOLYGON ), never
	 * <code>null</code>
	 * @throws ReportGenerationException if instantiation failed
	 */
	ShapefileBuilder(ICRS crs, Geometries GEOM_TYPE) throws ReportGenerationException {
		this.GEOM_TYPE = GEOM_TYPE;
		try {
			String location = "Location";
			String geom = "the_geom:" + GEOM_TYPE.toString();
			String crsCode = getCrsCode(crs);
			String srid = crsCode != null ? ":srid=" + crsCode : "";
			String tail = ",ID:String,Fehler:String";
			TYPE = createType(location, geom + srid + tail);
			featureBuilder = new SimpleFeatureBuilder(TYPE);
		}
		catch (SchemaException e) {
			throw new ReportGenerationException("ShapefileBuilder could not be instantiated!", e);
		}
	}

	private String getCrsCode(ICRS crs) {
		if (crs != null) {
			String crsCode = crs.getCode().getCode();
			if (crsCode != null && !crsCode.isEmpty())
				return crsCode;
		}
		return null;
	}

	/**
	 * Adds geometry to the internal List before it will be written to the shapefile
	 * @param jtsGeom The geometry to add
	 * @param id The id of the geometry
	 * @param errors A string describing the errors of the geometry
	 */
	void addGeometry(org.locationtech.jts.geom.Geometry jtsGeom, String id, String errors) {
		if (id == null)
			id = "NOTSET_" + UUID.randomUUID().toString();
		Geometries geomType = Geometries.get(jtsGeom);
		if (geomType == GEOM_TYPE) {
			featureBuilder.add(jtsGeom);
			featureBuilder.add(id);
			featureBuilder.add(errors);
			SimpleFeature feature = featureBuilder.buildFeature(id);
			features.add(feature);
		}
	}

	/**
	 * @return <code>true</code> if at least one feature is available to write to the
	 * shape, <code>false</code> otherwise
	 */
	public boolean hasGeometry() {
		return features.size() != 0;
	}

	/**
	 * Writes shapefiles containing the Geometry which was added by addGeometry()
	 * @param shapeFileDirectory An empty file with ending .shp which will become the
	 * shapefile
	 * @param shpName
	 * @throws ReportGenerationException if the generation of the shapefile failed
	 */
	void writeToShapefile(Path shapeFileDirectory, String shpName) throws ReportGenerationException {
		try {
			Path shapeFile = shapeFileDirectory.resolve(shpName + ".shp");
			ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
			Map<String, Serializable> params = new HashMap<>();
			params.put("url", shapeFile.toUri().toURL());
			params.put("create spatial index", Boolean.TRUE);
			ShapefileDataStore newDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
			newDataStore.setCharset(UTF_8);
			newDataStore.createSchema(TYPE);

			Transaction transaction = new DefaultTransaction("create");
			String typeName = newDataStore.getTypeNames()[0];
			SimpleFeatureSource featureSource = newDataStore.getFeatureSource(typeName);

			if (featureSource instanceof SimpleFeatureStore) {
				SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;

				SimpleFeatureCollection collection = new ListFeatureCollection(TYPE, features);
				featureStore.setTransaction(transaction);
				try {
					featureStore.addFeatures(collection);
					writeCpgFile(shapeFileDirectory, shpName);
					transaction.commit();
				}
				catch (Exception e) {
					transaction.rollback();
					throw new ReportGenerationException("Shapefile could not be written!", e);
				}
				finally {
					transaction.close();
				}
			}
			else {
				throw new ReportGenerationException(typeName + " does not support read/write access");
			}
		}
		catch (IOException e) {
			throw new ReportGenerationException("Shapefile could not be written!", e);
		}
	}

	private static void writeCpgFile(Path shapeFileDirectory, String shpName) throws IOException {
		Path cpgFile = shapeFileDirectory.resolve(shpName + ".cpg");
		Files.writeString(cpgFile, "UTF-8", UTF_8);
	}

}
