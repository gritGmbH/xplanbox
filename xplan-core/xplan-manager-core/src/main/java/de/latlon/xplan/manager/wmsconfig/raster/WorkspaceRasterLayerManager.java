package de.latlon.xplan.manager.wmsconfig.raster;

import static java.lang.Boolean.TRUE;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

import org.apache.commons.io.FileUtils;
import org.deegree.commons.gdal.jaxb.GDALSettings;
import org.deegree.commons.gdal.jaxb.GDALSettings.GDALOption;
import org.deegree.commons.metadata.description.jaxb.LanguageStringType;
import org.deegree.layer.persistence.base.jaxb.ScaleDenominatorsType;
import org.deegree.layer.persistence.tile.jaxb.TileLayerType;
import org.deegree.layer.persistence.tile.jaxb.TileLayers;
import org.deegree.tile.persistence.gdal.jaxb.GdalTileStoreJaxb;
import org.deegree.tile.persistence.geotiff.jaxb.GeoTIFFTileStoreJAXB;
import org.deegree.tile.tilematrixset.gdal.jaxb.GdalTileMatrixSetConfig;
import org.deegree.tile.tilematrixset.geotiff.jaxb.GeoTIFFTileMatrixSetConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates/deletes configuration and data files for raster layers in the WMS workspace.
 * 
 * @author <a href="mailto:schmitz@occamlabs.de">Andreas Schmitz</a>
 * @since 1.0
 */
public class WorkspaceRasterLayerManager {

    public static final String DEFAULT_RASTER_CRS = "EPSG:25833";

    private static final Logger LOG = LoggerFactory.getLogger( WorkspaceRasterLayerManager.class );

    private final File wmsWorkspace;

    private final RasterConfigurationType tileStoreType;

    private final String rasterConfigurationCrs;

    public enum RasterConfigurationType {
        gdal, geotiff
    }

    /**
     * Instantiates a {@link WorkspaceRasterLayerManager} with type RasterConfigurationType.gdal and crs EPSG:25833.
     * 
     * @param wmsWorkspace
     *            the location of the workspace the configuration should be stored, never <code>null</code>
     */
    public WorkspaceRasterLayerManager( File wmsWorkspace ) {
        this( wmsWorkspace, RasterConfigurationType.gdal, DEFAULT_RASTER_CRS );
    }

    /**
     * @param wmsWorkspace
     *            the location of the workspace the configuration should be stored, never <code>null</code>
     * @param tileStoreType
     *            the type of the tile store to create, never <code>null</code>
     * @param rasterConfigurationCrs
     *            the crs of the configuration, never <code>null</code>
     */
    public WorkspaceRasterLayerManager( File wmsWorkspace, RasterConfigurationType tileStoreType,
                                        String rasterConfigurationCrs ) {
        this.wmsWorkspace = wmsWorkspace;
        this.tileStoreType = tileStoreType;
        this.rasterConfigurationCrs = rasterConfigurationCrs;
    }

    public void createRasterConfigurations( String rasterId, String rasterFileName, double minScaleDenominator,
                                            double maxScaleDenominator )
                            throws JAXBException, IOException {
        switch ( tileStoreType ) {
        case geotiff:
            createGeotiffConfiguration( rasterId, rasterFileName, minScaleDenominator, maxScaleDenominator );
            break;
        default:
            createGdalConfiguration( rasterId, rasterFileName, minScaleDenominator, maxScaleDenominator );
            break;
        }
    }

    /**
     * Removes all configuration files for the specified plan:
     * <ul>
     * <li>data/&lt;planId&gt;_&lt;XXX&gt;</li>
     * <li>datasources/tile/&lt;planId&gt;_&lt;XXX&gt;</li>
     * <li>datasources/tile/tilematrixset/&lt;planId&gt;_&lt;XXX&gt;</li>
     * <li>layers/&lt;planId&gt;_&lt;XXX&gt;</li>
     * </ul>
     * 
     * @param planId
     *            the id of the plan to remove configuration for, never <code>null</code>
     */
    public void deleteDataFilesAndRasterConfigurations( String planId ) {
        final String prefix = planId + "_";
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept( File dir, String name ) {
                return name.startsWith( prefix );
            }
        };

        deleteDataFilesAndRasterConfigurations( filenameFilter );
    }

    /**
     * Removes the configuration files of the specified rasterId for the specified plan:
     * <ul>
     * <li>data/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
     * <li>datasources/tile/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
     * <li>datasources/tile/tilematrixset/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
     * <li>layers/&lt;planId&gt;_&lt;rasterId&gt;.xml</li>
     * </ul>
     * 
     * @param planId
     *            the id of the plan to remove configuration for, never <code>null</code>
     * @param rasterId
     *            id of the raster to remove, never <code>null</code>
     */
    public void deleteDataFilesAndRasterConfigurations( String planId, String rasterId ) {
        final String rasterLayerFileNameWithoutEnding = planId + "_" + rasterId;
        FilenameFilter dataFilenameFilter = new FilenameFilter() {
            @Override
            public boolean accept( File dir, String name ) {
                String nameWithoutPrefix = name;
                int lastIndexOfDot = name.lastIndexOf( "." );
                if ( lastIndexOfDot > 0 )
                    nameWithoutPrefix = name.substring( 0, lastIndexOfDot );
                return nameWithoutPrefix.equals( rasterLayerFileNameWithoutEnding );
            }
        };
        FilenameFilter xmlFilenameFilter = new FilenameFilter() {
            @Override
            public boolean accept( File dir, String name ) {
                return name.equals( rasterLayerFileNameWithoutEnding + ".xml" );
            }
        };
        deleteDataFilesAndRasterConfigurations( dataFilenameFilter, xmlFilenameFilter );
    }

    private void deleteDataFilesAndRasterConfigurations( FilenameFilter filenameFilter ) {
        deleteDataFilesAndRasterConfigurations( filenameFilter, filenameFilter );
    }

    private void deleteDataFilesAndRasterConfigurations( FilenameFilter dataFilenameFilter,
                                                         FilenameFilter xmlFilenameFilter ) {
        deleteFilesWithPrefix( new File( wmsWorkspace, "data" ), dataFilenameFilter );
        deleteFilesWithPrefix( new File( wmsWorkspace, "datasources/tile" ), xmlFilenameFilter );
        deleteFilesWithPrefix( new File( wmsWorkspace, "datasources/tile/tilematrixset" ), xmlFilenameFilter );
        deleteFilesWithPrefix( new File( wmsWorkspace, "layers" ), xmlFilenameFilter );
    }

    private void deleteFilesWithPrefix( File dir, FilenameFilter filenameFilter ) {
        if ( !dir.exists() || !dir.isDirectory() ) {
            return;
        }
        String[] fileNames = dir.list( filenameFilter );
        for ( String fileName : fileNames ) {
            File file = new File( dir, fileName );
            LOG.info( "- Entferne Workspace-Datei '" + file + "'..." );
            try {
                boolean deleteSuccessful = file.delete();
                if ( deleteSuccessful )
                    LOG.info( "OK" );
                else
                    LOG.warn( "Fehler: Die Datei konnte aus unbekannten Gruenden nicht entfernt werden. " );
            } catch ( Exception e ) {
                LOG.error( "Fehler: " + e.getMessage() );
                LOG.debug( "Fehler: ", e );
            }
        }
    }

    private void createGdalConfiguration( String rasterId, String rasterFileName, double minScaleDenominator,
                                          double maxScaleDenominator )
                            throws JAXBException, IOException {
        createGdalConfiguration();
        createGdalTileMatrixSetConfig( rasterId, rasterFileName );
        createGdalTileStoreConfig( rasterId, rasterFileName );
        createTileLayerConfig( rasterId, minScaleDenominator, maxScaleDenominator );
    }

    private void createGeotiffConfiguration( String rasterId, String rasterFileName, double minScaleDenominator,
                                             double maxScaleDenominator )
                            throws JAXBException, PropertyException {
        createGeotiffTileMatrixSetConfig( rasterId, rasterFileName );
        createGeotiffTileStoreConfig( rasterId, rasterFileName );
        createTileLayerConfig( rasterId, minScaleDenominator, maxScaleDenominator );
    }

    private void createTileLayerConfig( String rasterId, double minScaleDenominator, double maxScaleDenominator )
                            throws JAXBException {
        TileLayers cfg = new TileLayers();
        cfg.setConfigVersion( "3.2.0" );
        TileLayerType lay = new TileLayerType();
        cfg.getTileLayer().add( lay );
        lay.setName( rasterId );
        lay.setCRS( rasterConfigurationCrs );
        setScaleDenominator( lay, minScaleDenominator, maxScaleDenominator );
        LanguageStringType title = new LanguageStringType();
        title.setValue( rasterId );
        lay.getTitle().add( title );
        org.deegree.layer.persistence.tile.jaxb.TileLayerType.TileDataSet tds;
        tds = new org.deegree.layer.persistence.tile.jaxb.TileLayerType.TileDataSet();
        lay.getTileDataSet().add( tds );
        tds.setTileStoreId( rasterId );
        tds.setValue( rasterId );
        File file = new File( wmsWorkspace, "layers/" + rasterId + ".xml" );
        file.getParentFile().mkdirs();
        marshallConfig( cfg, "org.deegree.layer.persistence.tile.jaxb", file );
    }

    private void setScaleDenominator( TileLayerType lay, double minScaleDenominator, double maxScaleDenominator ) {
        if ( Double.isNaN( minScaleDenominator ) && Double.isNaN( maxScaleDenominator ) )
            return;
        double minScaleDenominatorToSet = Double.isNaN( minScaleDenominator ) ? 0 : minScaleDenominator;
        double maxScaleDenominatorToSet = Double.isNaN( maxScaleDenominator ) ? Double.MAX_VALUE : maxScaleDenominator;
        ScaleDenominatorsType scaleDenominators = new ScaleDenominatorsType();
        scaleDenominators.setMin( minScaleDenominatorToSet );
        scaleDenominators.setMax( maxScaleDenominatorToSet );
        lay.setScaleDenominators( scaleDenominators );
    }

    private void createGeotiffTileMatrixSetConfig( String rasterId, String rasterFileName )
                            throws JAXBException {
        GeoTIFFTileMatrixSetConfig cfg = new GeoTIFFTileMatrixSetConfig();
        cfg.setConfigVersion( "3.2.0" );
        cfg.setStorageCRS( rasterConfigurationCrs );
        cfg.setFile( "../../../data/" + rasterFileName );
        File file = new File( wmsWorkspace, "datasources/tile/tilematrixset/" );
        file.mkdirs();
        file = new File( file, rasterId + ".xml" );
        marshallConfig( cfg, "org.deegree.tile.tilematrixset.geotiff.jaxb", file );
    }

    private void createGdalTileMatrixSetConfig( String rasterId, String rasterFileName )
                            throws JAXBException {
        GdalTileMatrixSetConfig cfg = new GdalTileMatrixSetConfig();
        cfg.setConfigVersion( "3.4.0" );
        cfg.setStorageCRS( rasterConfigurationCrs );
        cfg.setFile( "../../../data/" + rasterFileName );
        File file = new File( wmsWorkspace, "datasources/tile/tilematrixset/" );
        file.mkdirs();
        file = new File( file, rasterId + ".xml" );
        marshallConfig( cfg, "org.deegree.tile.tilematrixset.gdal.jaxb", file );
    }

    private void createGeotiffTileStoreConfig( String rasterId, String rasterFileName )
                            throws JAXBException, PropertyException {
        GeoTIFFTileStoreJAXB cfg = new GeoTIFFTileStoreJAXB();
        cfg.setConfigVersion( "3.2.0" );
        GeoTIFFTileStoreJAXB.TileDataSet tds = new GeoTIFFTileStoreJAXB.TileDataSet();
        tds.setIdentifier( rasterId );
        tds.setFile( "../../data/" + rasterFileName );
        tds.setTileMatrixSetId( rasterId );
        cfg.getTileDataSet().add( tds );
        File file = new File( wmsWorkspace, "datasources/tile/" + rasterId + ".xml" );
        marshallConfig( cfg, "org.deegree.tile.persistence.geotiff.jaxb", file );
    }

    private void createGdalTileStoreConfig( String rasterId, String rasterFileName )
                            throws JAXBException, PropertyException {
        GdalTileStoreJaxb cfg = new GdalTileStoreJaxb();
        cfg.setConfigVersion( "3.4.0" );
        GdalTileStoreJaxb.TileDataSet tds = new GdalTileStoreJaxb.TileDataSet();
        tds.setIdentifier( rasterId );
        tds.setFile( "../../data/" + rasterFileName );
        tds.setTileMatrixSetId( rasterId );
        cfg.getTileDataSet().add( tds );
        File file = new File( wmsWorkspace, "datasources/tile/" + rasterId + ".xml" );
        marshallConfig( cfg, "org.deegree.tile.persistence.gdal.jaxb", file );
    }

    private void createGdalConfiguration()
                            throws JAXBException, IOException {
        GDALSettings gdalSettings = new GDALSettings();
        gdalSettings.setConfigVersion( "3.4.0" );
        gdalSettings.setOpenDatasets( BigInteger.valueOf( 10 ) );
        GDALOption gdalOption = new GDALOption();
        gdalOption.setName( "gdalSettings.getGDALOption()" );
        gdalOption.setValue( "1000" );
        gdalSettings.getGDALOption().add( gdalOption );
        File file = new File( wmsWorkspace, "gdal.xml" );
        marshallConfig( gdalSettings, "org.deegree.commons.gdal.jaxb", file );
    }

    private void marshallConfig( Object cfg, String contextPath, File toWriteIn )
                            throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance( contextPath );
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( JAXB_FORMATTED_OUTPUT, TRUE );
        marshaller.marshal( cfg, toWriteIn );
    }

}