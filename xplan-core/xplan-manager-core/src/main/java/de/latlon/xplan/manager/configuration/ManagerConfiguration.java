package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.Double.parseDouble;
import static org.deegree.cs.CRSUtils.EPSG_4326;

/**
 * Provides access to the manager configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ManagerConfiguration {

    static final String CATEGORIES_TO_PARTS_KEY = "categoriesToParts";

    static final String RASTER_CONFIG_CRS = "rasterConfigurationCrs";

    static final String RASTER_LAYER_SCALE_DENOMINATOR_MIN = "rasterLayerMinScaleDenominator";

    static final String RASTER_LAYER_SCALE_DENOMINATOR_MAX = "rasterLayerMaxScaleDenominator";

    static final String ACTIVATE_SEPARATED_DATAMANAGEMENT = "activateSeparatedDataManagement";

    static final String ACTIVATE_EXPORT_OF_REEXPORTED = "activateExportOfReexported";

    static final String RASTER_CONFIG_TYPE = "rasterConfigurationType";

    static final String DEFAULT_BBOX_IN_4326 = "defaultBboxIn4326";

    static final String WORKSPACE_RELOAD_URLS = "workspaceReloadUrls";

    static final String WORKSPACE_RELOAD_USER = "workspaceReloadUser";

    static final String WORKSPACE_RELOAD_PASSWORD = "workspaceReloadPassword";

    static final String PATH_TO_HALE_CLI = "pathToHaleCli";

    static final String ACTIVATE_PROVIDING_XPLAN41_AS_XPLAN51 = "activateProvidingXPlan41AsXPlan51";

    private static final Logger LOG = LoggerFactory.getLogger( ManagerConfiguration.class );

    private static final String MANAGER_CONFIGURATION = "managerConfiguration.properties";

    private final Map<String, List<String>> categoriesToParts = new HashMap<>();

    private final SortConfiguration sortConfiguration = new SortConfiguration();

    private String rasterConfigurationCrs;

    private RasterConfigurationType rasterConfigurationType;

    private double rasterLayerMinScaleDenominator = Double.NaN;

    private double rasterLayerMaxScaleDenominator = Double.NaN;

    private boolean isSeperatedDataManagementActived = false;

    private boolean isExportOfReexportedActive = false;

    private WorkspaceReloaderConfiguration workspaceReloaderConfiguration = new WorkspaceReloaderConfiguration();

    private Envelope defaultBboxIn4326 = null;

    private InternalIdRetrieverConfiguration internalIdRetrieverConfiguration = new InternalIdRetrieverConfiguration();

    private SemanticConformityLinkConfiguration semanticConformityLinkConfiguration = new SemanticConformityLinkConfiguration();

    private Path configDirectory;

    private String pathToHaleCli;

    private Path pathToHaleProjectDirectory;

    private boolean isProvidingXPlan41As51Active = false;

    public ManagerConfiguration( PropertiesLoader propertiesLoader )
                    throws ConfigurationException {
        loadProperties( propertiesLoader );
        verifyConfiguration();
        logConfiguration();
    }

    /**
     * Retrieves the category mappings (category assigned to a list of parts).
     *
     * @return the category mapping, may be empty but never <code>null</code>
     */
    public Map<String, List<String>> getCategoryMapping() {
        return categoriesToParts;
    }

    /**
     * @return the crs to use in the raster configuration, may be <code>null</code>
     */
    public String getRasterConfigurationCrs() {
        return rasterConfigurationCrs;
    }

    /**
     * @return the type to use in the raster configuration (gdal or geotiff), never <code>null</code>
     */
    public RasterConfigurationType getRasterConfigurationType() {
        return rasterConfigurationType;
    }

    /**
     * @return the max scale denominator the raster layer is visible (a value less then 0 means the visibility is not
     * limited)
     */
    public double getRasterLayerMaxScaleDenominator() {
        return rasterLayerMaxScaleDenominator;
    }

    /**
     * @return the min scale denominator the raster layer is visible (a value less then 0 means the visibility is not
     * limited)
     */
    public double getRasterLayerMinScaleDenominator() {
        return rasterLayerMinScaleDenominator;
    }

    /**
     * @return <code>true</code> if 'festgestellte' and 'in aufstellung befindliche' plans should be stored in two
     * separated database schemas, <code>false</code> otherwise
     */
    public boolean isSeperatedDataManagementActived() {
        return isSeperatedDataManagementActived;
    }

    /**
     * @return configuration for {@link de.latlon.xplan.manager.workspace.WorkspaceReloader}, never <code>null</code>
     */
    public WorkspaceReloaderConfiguration getWorkspaceReloaderConfiguration() {
        return workspaceReloaderConfiguration;
    }

    /**
     * @return default BBOX in EPSG:4326, may be <code>null</code>
     */
    public Envelope getDefaultBboxIn4326() {
        return defaultBboxIn4326;
    }

    /**
     * @return the {@link InternalIdRetrieverConfiguration}, never <code>null</code>
     */
    public InternalIdRetrieverConfiguration getInternalIdRetrieverConfiguration() {
        return internalIdRetrieverConfiguration;
    }

    /**
     * @return the {@link SemanticConformityLinkConfiguration}, never <code>null</code>
     */
    public SemanticConformityLinkConfiguration getSemanticConformityLinkConfiguration() {
        return semanticConformityLinkConfiguration;
    }

    /**
     * @return the {@link SortConfiguration}, never <code>null</code>
     */
    public SortConfiguration getSortConfiguration() {
        return sortConfiguration;
    }

    /**
     * @return <code>true</code> if the xplan-reexported.gml file should be exported, <code>false</code> otherwise
     */
    public boolean isExportOfReexportedActive() {
        return isExportOfReexportedActive;
    }

    /**
     * @return the directory containing the configuration, may be <code>null</code>
     */
    public Path getConfigurationDirectory() {
        return configDirectory;
    }

    /**
     * @return the absolute path to the hale cli
     */
    public String getPathToHaleCli() {
        return pathToHaleCli;
    }

    /**
     * @return the path to the hale project directory
     */
    public Path getPathToHaleProjectDirectory() {
        return pathToHaleProjectDirectory;
    }

    /**
     * @return <code>true</code> if XPlanGML 4.1 plans should be transformed to XPlanGML 5.1 and provided by the
     * XPlan WFS for 5.1 plans, <code>false</code> otherwise
     */
    public boolean isProvidingXPlan41As51Active() {
        return isProvidingXPlan41As51Active;
    }

    private void loadProperties( PropertiesLoader propertiesLoader )
                    throws ConfigurationException {
        if ( propertiesLoader != null ) {
            Properties loadProperties = propertiesLoader.loadProperties( MANAGER_CONFIGURATION );
            if ( loadProperties != null ) {
                String categoriesToPartsProperty = loadProperties.getProperty( CATEGORIES_TO_PARTS_KEY );
                if ( categoriesToPartsProperty != null && !"".equals( categoriesToPartsProperty ) ) {
                    String[] categoriesWithParts = categoriesToPartsProperty.split( ";" );
                    parseCategories( categoriesWithParts );
                }
                rasterConfigurationCrs = loadProperties.getProperty( RASTER_CONFIG_CRS );
                rasterConfigurationType = parseRasterConfigurationType( loadProperties );
                rasterLayerMinScaleDenominator = parseScaleDenominator( loadProperties,
                                                                        RASTER_LAYER_SCALE_DENOMINATOR_MIN );
                rasterLayerMaxScaleDenominator = parseScaleDenominator( loadProperties,
                                                                        RASTER_LAYER_SCALE_DENOMINATOR_MAX );
                isSeperatedDataManagementActived = parseBoolean( loadProperties, ACTIVATE_SEPARATED_DATAMANAGEMENT,
                                                                 false );
                isExportOfReexportedActive = parseBoolean( loadProperties, ACTIVATE_EXPORT_OF_REEXPORTED, false );
                workspaceReloaderConfiguration = parseWorkspaceReloaderConfiguration( loadProperties );
                defaultBboxIn4326 = parseDefaultBboxIn4326( loadProperties );
                internalIdRetrieverConfiguration = parseInternalIdRetrieverConfiguration( loadProperties );
                parseSortConfiguration( loadProperties );
                parseSemanticConformityLinkConfiguration( loadProperties );
                pathToHaleCli = loadProperties.getProperty( PATH_TO_HALE_CLI );
                pathToHaleProjectDirectory = parsePathToHaleProjectDirectory( propertiesLoader );
                isProvidingXPlan41As51Active = parseBoolean( loadProperties, ACTIVATE_PROVIDING_XPLAN41_AS_XPLAN51, false );
            }
            configDirectory = getConfigDirectory( propertiesLoader, "synthesizer" );
        }
    }

    private void verifyConfiguration() {
        if ( Double.isNaN( rasterLayerMinScaleDenominator ) && Double.isNaN( rasterLayerMaxScaleDenominator ) )
            return;
        if ( rasterLayerMinScaleDenominator < 0 )
            throw new IllegalArgumentException( "rasterLayerMinScaleDenominator should not be a negative value" );
        if ( rasterLayerMaxScaleDenominator < 0 )
            throw new IllegalArgumentException( "rasterLayerMaxScaleDenominator should not be a negative value" );
        if ( rasterLayerMinScaleDenominator >= rasterLayerMaxScaleDenominator )
            throw new IllegalArgumentException(
                            "rasterLayerMinScaleDenominator must be less than rasterLayerMaxScaleDenominator" );
    }

    private void logConfiguration() {
        LOG.info( "-------------------------------------------" );
        LOG.info( "Configuration of the XPlanManager:" );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  directory containing the configuration: {}", configDirectory );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  raster configuration" );
        LOG.info( "   - crs: {}", rasterConfigurationCrs );
        LOG.info( "   - type: {}", rasterConfigurationType );
        LOG.info( "   - min scale denominator: {}", rasterLayerMinScaleDenominator );
        LOG.info( "   - max scale denominator: {}", rasterLayerMaxScaleDenominator );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  separated data management" );
        LOG.info( "   - is activated: {}", isSeperatedDataManagementActived );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  export of xplan-reexported.gml" );
        LOG.info( "   - is activated: {}", isExportOfReexportedActive );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  workspace reloader configuration" );
        LOG.info( "   - urls of service to reload: {}", workspaceReloaderConfiguration.getUrls().toString() );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  alternative operating mode" );
        LOG.info( "   - default bbox: {}", defaultBboxIn4326 );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  InternalIdRetriever" );
        LOG.info( "   - workspace: {}", internalIdRetrieverConfiguration.getWorkspaceName() );
        LOG.info( "   - jdbc connection id: {}", internalIdRetrieverConfiguration.getJdbcConnectionId() );
        LOG.info( "   - internalId label: {}", internalIdRetrieverConfiguration.getInternalIdLabel() );
        LOG.info( "   - internalName label: {}", internalIdRetrieverConfiguration.getInternalNameLabel() );
        LOG.info( "   - SQL Matching Ids: {}", internalIdRetrieverConfiguration.getSelectMatchingIdsSql() );
        LOG.info( "   - SQL All: {}", internalIdRetrieverConfiguration.getSelectAllSql() );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  path to HALE CLI: {}", pathToHaleCli );
        LOG.info( "  path to HALE project: {}", pathToHaleProjectDirectory );
        LOG.info( "-------------------------------------------" );
        sortConfiguration.logConfiguration( LOG );
        LOG.info( "-------------------------------------------" );
        semanticConformityLinkConfiguration.logConfiguration( LOG );
        LOG.info( "-------------------------------------------" );
    }

    private void parseCategories( String[] categoriesWithParts )
                    throws ConfigurationException {
        for ( String categoryWithParts : categoriesWithParts ) {
            String categoryName = parseCategoryName( categoryWithParts );
            List<String> partsAsList = parseParts( categoryWithParts );
            categoriesToParts.put( categoryName, partsAsList );
        }
    }

    private String parseCategoryName( String categoryWithParts )
                    throws ConfigurationException {
        if ( categoryWithParts.contains( "(" ) ) {
            int indexOfCategoryEnd = categoryWithParts.indexOf( "(" );
            return categoryWithParts.substring( 0, indexOfCategoryEnd );
        }
        throw new ConfigurationException( "Categories was not correctly configured!" );
    }

    private RasterConfigurationType parseRasterConfigurationType( Properties loadProperties ) {
        String rasterConfigTypePropertyValue = loadProperties.getProperty( RASTER_CONFIG_TYPE );
        if ( rasterConfigTypePropertyValue != null ) {
            try {
                return RasterConfigurationType.valueOf( rasterConfigTypePropertyValue );
            } catch ( IllegalArgumentException e ) {
            }
        }
        return RasterConfigurationType.gdal;
    }

    private List<String> parseParts( String categoryWithParts ) {
        int indexOfPartsBegin = categoryWithParts.indexOf( "(" ) + 1;
        int indexOfPartsEnd = categoryWithParts.indexOf( ")" );
        String allParts = categoryWithParts.substring( indexOfPartsBegin, indexOfPartsEnd );
        String[] parts = allParts.split( "," );
        return cleanupParts( parts );
    }

    private List<String> cleanupParts( String[] parts ) {
        List<String> partsAsList = new ArrayList<>();
        for ( String part : parts ) {
            partsAsList.add( part.trim() );
        }
        return partsAsList;
    }

    private WorkspaceReloaderConfiguration parseWorkspaceReloaderConfiguration( Properties loadProperties ) {
        String urls = loadProperties.getProperty( WORKSPACE_RELOAD_URLS );
        String user = loadProperties.getProperty( WORKSPACE_RELOAD_USER );
        String password = loadProperties.getProperty( WORKSPACE_RELOAD_PASSWORD );
        if ( urls != null && user != null && password != null && !"".equals( urls ) ) {
            List<String> urlList = Arrays.asList( urls.split( "," ) );
            return new WorkspaceReloaderConfiguration( urlList, user, password );
        }
        return new WorkspaceReloaderConfiguration();
    }

    private Envelope parseDefaultBboxIn4326( Properties loadProperties ) {
        String defaultBbox = loadProperties.getProperty( DEFAULT_BBOX_IN_4326 );
        if ( defaultBbox == null || defaultBbox.isEmpty() )
            return null;
        String[] split = defaultBbox.split( "," );
        double minx = parseDouble( split[0].trim() );
        double miny = parseDouble( split[1].trim() );
        double maxx = parseDouble( split[2].trim() );
        double maxy = parseDouble( split[3].trim() );
        return new SimpleGeometryFactory().createEnvelope( minx, miny, maxx, maxy, EPSG_4326 );
    }

    private InternalIdRetrieverConfiguration parseInternalIdRetrieverConfiguration( Properties properties ) {
        InternalIdRetrieverConfiguration internalIdRetrieverConfiguration = new InternalIdRetrieverConfiguration();
        String workspaceName = properties.getProperty( "workspaceName" );
        if ( workspaceName != null )
            internalIdRetrieverConfiguration.setWorkspaceName( workspaceName );
        String jdbcConnectionId = properties.getProperty( "jdbcConnectionId" );
        if ( jdbcConnectionId != null )
            internalIdRetrieverConfiguration.setJdbcConnectionId( jdbcConnectionId );
        String internalIdLabel = properties.getProperty( "internalIdLabel" );
        if ( internalIdLabel != null )
            internalIdRetrieverConfiguration.setInternalIdLabel( internalIdLabel );
        String internalNameLabel = properties.getProperty( "internalNameLabel" );
        if ( internalNameLabel != null )
            internalIdRetrieverConfiguration.setInternalNameLabel( internalNameLabel );
        String selectMatchingIdsSql = properties.getProperty( "selectMatchingIdsSql" );
        if ( selectMatchingIdsSql != null )
            internalIdRetrieverConfiguration.setSelectMatchingIdsSql( selectMatchingIdsSql );
        String selectAllIdsSql = properties.getProperty( "selectAllIdsSql" );
        if ( selectAllIdsSql != null )
            internalIdRetrieverConfiguration.setSelectAllSql( selectAllIdsSql );
        return internalIdRetrieverConfiguration;
    }

    private void parseSortConfiguration( Properties properties ) {
        for ( XPlanType type : XPlanType.values() ) {
            for ( XPlanVersion version : XPlanVersion.values() ) {
                String key = "wmsSortDate_" + type + "_" + version;
                String property = properties.getProperty( key );
                if ( property != null && !property.isEmpty() ) {
                    String[] split = property.split( "," );
                    if ( split.length != 2 ) {
                        LOG.warn( "Property with key {} cannot be parsed as wmsSortDate-Configuration. "
                                  + "The property value must contain the FeatureType and PropertyName comma-seperated." );
                    } else {
                        sortConfiguration.addSortField( type, version, split[0], split[1] );
                    }
                }
            }
        }
    }

    private void parseSemanticConformityLinkConfiguration( Properties properties ) {
        for ( XPlanVersion version : XPlanVersion.values() ) {
            String key = "linkSemanticConformity_" + version;
            String property = properties.getProperty( key );
            if ( property != null && !property.isEmpty() ) {
                semanticConformityLinkConfiguration.addLink( version, property );
            }
        }
    }

    private Path parsePathToHaleProjectDirectory( PropertiesLoader propertiesLoader ) {
        Path haleProject = getConfigDirectory( propertiesLoader, "hale" );
        if ( haleProject != null && Files.exists( haleProject ) && Files.isDirectory( haleProject ) )
            return haleProject;
        return null;
    }

    private Path getConfigDirectory( PropertiesLoader propertiesLoader, String subdirectory ) {
        Path configDirectory = propertiesLoader.getConfigDirectory();
        if ( configDirectory != null )
            return configDirectory.resolve( subdirectory );
        return null;
    }

    private Double parseScaleDenominator( Properties properties, String propName ) {
        String propertyValue = properties.getProperty( propName );
        if ( propertyValue == null || "".equals( propertyValue ) )
            return Double.NaN;
        return Double.parseDouble( propertyValue );
    }

    private boolean parseBoolean( Properties loadProperties, String propName, boolean defaultValue ) {
        String property = loadProperties.getProperty( propName );
        if ( property == null || "".equals( property ) )
            return defaultValue;
        return Boolean.parseBoolean( property );
    }

}