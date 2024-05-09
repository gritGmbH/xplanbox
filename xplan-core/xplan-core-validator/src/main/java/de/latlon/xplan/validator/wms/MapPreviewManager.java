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
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.GeometryTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewManager {

	private static final Logger LOG = LoggerFactory.getLogger(MapPreviewManager.class);

	private final XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();

	private final ValidatorWmsManager validatorWmsManager;

	private final MasterportalConfigWriter configWriter;

	private final GeometricValidator geometricValidator;

	private final XPlanSchemas schemas;

	/**
	 * @param validatorWmsManager used to create wms configuration, never
	 * <code>null</code>
	 * @param geometricValidator used to parse the gml, never <code>null</code>
	 * @param validatorWmsEndpoint the base URL of the XPlanValidatorWMS, never
	 * <code>null</code>
	 * @throws MapPreviewCreationException if instantiation failed
	 */
	public MapPreviewManager(ValidatorWmsManager validatorWmsManager, GeometricValidator geometricValidator,
			String validatorWmsEndpoint) throws MapPreviewCreationException {
		this.geometricValidator = geometricValidator;
		this.validatorWmsManager = validatorWmsManager;
		this.configWriter = new MasterportalConfigWriter(validatorWmsEndpoint);
		this.schemas = XPlanSchemas.getInstance();

	}

	public MapPreviewMetadata createConfigurations(File xPlan) throws MapPreviewCreationException {
		try {
			XPlanArchive archive = archiveCreator.createXPlanArchive(xPlan);
			XPlanFeatureCollection featureCollection = XPlanGmlParserBuilder.newBuilder()
				.withSkipResolveReferences(true)
				.build()
				.parseXPlanFeatureCollection(archive);
			int managerId = this.validatorWmsManager.insert(featureCollection);
			String configFileName = this.configWriter.createMasterportalConfig(managerId, archive.getType());

			Envelope envelope = transformBboxTo25832(featureCollection.getBboxIn4326());
			XPlanEnvelope xPlanEnvelope = new XPlanEnvelope(envelope.getMin().get0(), envelope.getMin().get1(),
					envelope.getMax().get0(), envelope.getMax().get1(), "EPSG:4326");
			return new MapPreviewMetadata(configFileName, featureCollection.getPlanName(), xPlanEnvelope);
		}
		catch (XMLStreamException | UnknownCRSException e) {
			LOG.error("Plan could not be parsed. Reason {}", e.getMessage(), e);
			throw new MapPreviewCreationException(e.getMessage());
		}
		catch (Exception e) {
			LOG.error("An exception occurred during creation of the map preview configuration", e);
			throw new MapPreviewCreationException(e.getMessage());
		}
	}

	// TODO
	private Envelope transformBboxTo25832(Envelope envelopeIn4326) {
		try {
			if (envelopeIn4326 != null) {
				ICRS targetCrs = CRSManager.lookup("EPSG:25832");
				GeometryTransformer geometryTransformer = new GeometryTransformer(targetCrs);
				return geometryTransformer.transform(envelopeIn4326);
			}
		}
		catch (IllegalArgumentException | UnknownCRSException | TransformationException e) {
			LOG.error("Could not transform envelope: " + e.getMessage());
		}
		return envelopeIn4326;
	}

}
