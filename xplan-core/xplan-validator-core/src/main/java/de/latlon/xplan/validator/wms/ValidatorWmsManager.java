/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import org.apache.commons.io.IOUtils;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.memory.jaxb.MemoryFeatureStoreConfig;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_32;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManager {

    private static final Logger LOG = LoggerFactory.getLogger( ValidatorWmsManager.class );

    private static final String RELATIVE_PATH_TO_DATE_DIR = "data";

    private static int PLANID = 1;

    private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

    private final Path pathToDataDirectory;

    private final XPlanSynthesizer synthesizer;

    /**
     * @param synthesizer
     *                         used to synthesize the XPlan GML
     * @param workspaceLocation
     *                         path to the workspace xplan-validator-wms-workspace, the directory data is created if required
     * @throws IOException
     *                         if the directory data could not be required
     * @throws IllegalArgumentException
     *                         if the workspace location or file datasources/feature/xplansyn.xml does not exixt
     */
    public ValidatorWmsManager( XPlanSynthesizer synthesizer, Path workspaceLocation )
                            throws IOException {
        this.synthesizer = synthesizer;
        if ( workspaceLocation == null )
            throw new IllegalArgumentException( "Workspace does not exist" );
        this.pathToDataDirectory = workspaceLocation.resolve( RELATIVE_PATH_TO_DATE_DIR );
        if ( !Files.exists( pathToDataDirectory ) )
            Files.createDirectory( pathToDataDirectory );
    }

    /**
     * @param featureCollection
     *                         feature collection to append to the {@link MemoryFeatureStoreConfig}, never <code>null</code>
     * @throws MapPreviewCreationException
     *                         if the configuration could not be writtem
     * @return
     */
    public int insert( XPlanFeatureCollection featureCollection )
                            throws MapPreviewCreationException {
        try {
            int managerId = PLANID++;
            AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema( XPLAN_SYN, null );
            FeatureCollection fc = synthesizer.synthesize( featureCollection );
            featureCollectionManipulator.addPlanIdToFeatures( fc, synSchema, managerId );
            writeSynFeatureCollectionAsGml( fc, synSchema );
            return managerId;
        } catch ( Exception e ) {
            LOG.warn( "Could not add featureCollection", e );
            throw new MapPreviewCreationException( e );
        }
    }

    private void writeSynFeatureCollectionAsGml( FeatureCollection synthesizedFeatureCollection, AppSchema synSchema )
                            throws IOException, TransformationException, XMLStreamException, UnknownCRSException {
        String fileName = UUID.randomUUID().toString() + ".gml";
        Path pathToFile = pathToDataDirectory.resolve( fileName );
        LOG.info( "Write to file {}", pathToFile );
        OutputStream output = null;
        GMLStreamWriter gmlWriter = null;
        try {
            output = Files.newOutputStream( pathToFile );
            gmlWriter = createGmlWriter( output );
            Map<String, String> nsBindings = synSchema.getNamespaceBindings();
            nsBindings.put( "gml", GML_32.getNamespace() );
            gmlWriter.setNamespaceBindings( nsBindings );
            gmlWriter.write( synthesizedFeatureCollection );
        } finally {
            closeQuietly( gmlWriter );
            IOUtils.closeQuietly( output );
        }
    }

    private GMLStreamWriter createGmlWriter( OutputStream output )
                            throws XMLStreamException {
        XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter( output );
        xmlWriter = new IndentingXMLStreamWriter( xmlWriter );
        return GMLOutputFactory.createGMLStreamWriter( GML_32, xmlWriter );
    }

    private void closeQuietly( GMLStreamWriter gmlWriter ) {
        try {
            if ( gmlWriter != null )
                gmlWriter.close();
        } catch ( XMLStreamException e ) {
        }
    }

}
