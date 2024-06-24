/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.evaluation;

import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.util.XmlUtils;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.wmsconfig.raster.RasterType;
import it.geosolutions.imageioimpl.plugins.tiff.TIFFImageReader;
import org.deegree.commons.xml.stax.XMLStreamUtils;
import org.deegree.coverage.raster.io.imageio.geotiff.GeoTiffIIOMetadataAdapter;
import org.deegree.cs.configuration.wkt.WKTParser;
import org.deegree.cs.coordinatesystems.CRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.detectRasterType;
import static javax.imageio.ImageIO.getImageReadersBySuffix;

/**
 * Evaluates rasterfiles to be stored in mapserver rastersource.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapserverRasterEvaluation implements RasterEvaluation {

	private static final Logger LOG = LoggerFactory.getLogger(MapserverRasterEvaluation.class);

	private final String configuredRasterCrs;

	private final String configuredRasterCrsEpsgCode;

	public MapserverRasterEvaluation(String configuredRasterCrs) {
		this.configuredRasterCrs = configuredRasterCrs;
		this.configuredRasterCrsEpsgCode = asEpsgCode(configuredRasterCrs);
	}

	@Override
	public RasterEvaluationResult evaluate(XPlanArchiveContentAccess archive, ExternalReference rasterplanZipEntry)
			throws IOException {
		String referenzEntryName = rasterplanZipEntry.getReferenzUrl();

		RasterType rasterType = detectRasterType(archive, referenzEntryName);
		LOG.info("Rasterdatei mit Namen {} und Typ {} gefunden.", referenzEntryName, rasterType);
		switch (rasterType) {
			case TIFF:
				String rasterCrsEpsgCodeTiff = detectEpsgCodeFromTiff(archive, referenzEntryName);
				return compareRasterCrsWithConfiguredCrs(referenzEntryName, rasterCrsEpsgCodeTiff);
			case PNG:
				String rasterCrsEpsgCodePng = detectEpsgCodeFromPng(archive, rasterplanZipEntry.getGeoRefUrl());
				return compareRasterCrsWithConfiguredCrs(referenzEntryName, rasterCrsEpsgCodePng);
		}
		return new RasterEvaluationResult(referenzEntryName, null, configuredRasterCrs, false, false, false);
	}

	@Override
	public boolean isSupportedFile(String fileName) {
		return true;
	}

	private static String detectEpsgCodeFromTiff(XPlanArchiveContentAccess archive, String entryName) {
		ImageReader reader = null;
		Iterator<ImageReader> readers = getImageReadersBySuffix("tiff");
		while (readers.hasNext() && !(reader instanceof TIFFImageReader)) {
			reader = readers.next();
		}

		try (InputStream is = archive.retrieveInputStreamFor(entryName);
				ImageInputStream iis = ImageIO.createImageInputStream(is)) {
			reader.setInput(iis, false, true);
			IIOMetadata md = reader.getImageMetadata(0);
			GeoTiffIIOMetadataAdapter geoTIFFMetaData = new GeoTiffIIOMetadataAdapter(md);

			int modelType = Integer.valueOf(geoTIFFMetaData.getGeoKey(1024));
			if (modelType == 1) {
				return geoTIFFMetaData.getGeoKey(3072);
			}
			else if (modelType == 2) {
				return geoTIFFMetaData.getGeoKey(2048);
			}
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Rasterscan-Datei '" + entryName
					+ "' ist nicht im Archiv vorhanden oder konnte nicht gelesen werden.");
		}
		catch (UnsupportedOperationException e) {
			// Raster is a TIFF, not a GeoTIFF, CRS can not be detected.
		}
		return null;
	}

	private String detectEpsgCodeFromPng(XPlanArchiveContentAccess archive, String georeferenzEntryName) {
		if (georeferenzEntryName != null && georeferenzEntryName.endsWith("aux.xml")) {
			try (InputStream is = archive.retrieveInputStreamFor(georeferenzEntryName)) {
				XMLStreamReader xmlReader = XmlUtils.createXMLInputFactory().createXMLStreamReader(is);
				XMLStreamUtils.skipStartDocument(xmlReader);
				XMLStreamUtils.moveReaderToFirstMatch(xmlReader, new QName("SRS"));
				String srs = XMLStreamUtils.getText(xmlReader, new QName("SRS"), null, false);
				LOG.debug("Found srs {} from georeference", srs);
				CRS crs = WKTParser.parse(srs);
				if (crs != null)
					return crs.getCode().getCode();
			}
			catch (IOException e) {
				throw new IllegalArgumentException("Datei-Datei '" + georeferenzEntryName
						+ "' ist nicht im Archiv vorhanden oder konnte nicht gelesen werden.");
			}
			catch (XMLStreamException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	private RasterEvaluationResult compareRasterCrsWithConfiguredCrs(String referenzEntryName,
			String rasterCrsEpsgCode) {
		if (rasterCrsEpsgCode != null) {
			String rasterCrsAuthority = "EPSG:" + rasterCrsEpsgCode;
			if (configuredRasterCrsEpsgCode == null) {
				LOG.warn("The evaluation of the raster crs is skipped (rasterConfigurationCrs is not configured).");
				return new RasterEvaluationResult(referenzEntryName, rasterCrsAuthority, configuredRasterCrs, true,
						false, true);
			}
			else {
				LOG.info("Koordinatensystem des Rasters: {}", rasterCrsEpsgCode);
				boolean isCrsSet = rasterCrsEpsgCode != null && !rasterCrsEpsgCode.isEmpty();
				boolean isConfiguredCrs = rasterCrsEpsgCode.equals(configuredRasterCrsEpsgCode);
				return new RasterEvaluationResult(referenzEntryName, rasterCrsAuthority, configuredRasterCrs, isCrsSet,
						isConfiguredCrs, true);
			}
		}
		return new RasterEvaluationResult(referenzEntryName, null, configuredRasterCrs, false, false, true);
	}

	private String asEpsgCode(String rasterCrs) {
		if (rasterCrs == null) {
			LOG.warn("rasterConfigurationCrs is not configured! The evaluation of the raster crs is skipped.");
			return null;
		}
		return rasterCrs.substring(rasterCrs.indexOf(":") + 1);
	}

}
