package de.latlon.xplan.manager.wmsconfig.raster;

import static de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper.supportedTypes;
import static java.lang.Boolean.TRUE;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.deegree.commons.metadata.description.jaxb.LanguageStringType;
import org.deegree.theme.persistence.standard.jaxb.ThemeType;
import org.deegree.theme.persistence.standard.jaxb.ThemeType.Identifier;
import org.deegree.theme.persistence.standard.jaxb.ThemeType.Layer;
import org.deegree.theme.persistence.standard.jaxb.Themes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsThemesConfig;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;

/**
 * Modifies raster theme configuration files in the WMS workspace.
 * 
 * @author <a href="mailto:schmitz@occamlabs.de">Andreas Schmitz</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class WorkspaceRasterThemeManager {

    private static final Logger LOG = LoggerFactory.getLogger( WorkspaceRasterThemeManager.class );

    private final RasterConfigurationSorter rasterConfigurationSorter = new RasterConfigurationSorter(); 
    
    private final WmsWorkspaceWrapper wmsWorkspaceWrapper;

    /**
     * @param wmsWorkspaceWrapper
     *            the wrapper of the workspace containing the wms configuration to manipulate, never <code>null</code>
     * @throws IllegalArgumentException
     *             - if the wmsWorkspace is <code>null</code>
     */
    public WorkspaceRasterThemeManager( WmsWorkspaceWrapper wmsWorkspaceWrapper ) {
        if ( wmsWorkspaceWrapper == null )
            throw new IllegalArgumentException();
        this.wmsWorkspaceWrapper = wmsWorkspaceWrapper;
    }

    /**
     * Inserts the specified raster layers at the correct position in the "sorted theme" (which is sorted by plan date).
     * 
     * @param rasterLayerIds
     *            ids of the new raster layers, must not be <code>null</code>
     * @param succeedingPlanId
     *            id of the raster plan that succeeds the new layers, can be <code>null</code> (new layers are most
     *            recent, inserted at the end)
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     * @throws ConfigurationException
     */
    public void insertLayersRightBefore( String type, String crs, List<String> rasterLayerIds, String succeedingPlanId )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type, crs );

        Themes themes = themesConfig.getThemes();
        LOG.info( "Succeeding plan id: {}", succeedingPlanId );
        for ( String rasterLayerId : rasterLayerIds ) {
            themes.getLayerStoreId().add( rasterLayerId );
        }
        ThemeType sortedTheme = themes.getTheme().getTheme().get( 0 );
        List<Layer> layers = sortedTheme.getLayer();
        List<Layer> newRasterLayers = createNewRasterLayers( rasterLayerIds );

        int index = findIndex( layers, succeedingPlanId );
        layers.addAll( index, newRasterLayers );

        for ( Layer layer : layers ) {
            LOG.debug( layer.getLayerStore() );
        }
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Inserts the specified raster layers at the beginning.
     * 
     * @param rasterLayerIds
     *            ids of the new raster layers, must not be <code>null</code>
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void insertLayersAtBeginning( String type, String crs, List<String> rasterLayerIds )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type, crs );

        File config = themesConfig.getConfig();
        Themes themes = themesConfig.getThemes();

        for ( String rasterLayerId : rasterLayerIds ) {
            themes.getLayerStoreId().add( rasterLayerId );
        }
        ThemeType sortedTheme = themes.getTheme().getTheme().get( 0 );
        List<Layer> layers = sortedTheme.getLayer();
        List<Layer> newRasterLayers = createNewRasterLayers( rasterLayerIds );
        layers.addAll( 0, newRasterLayers );
        persist( themes, config );
    }

    /**
     * Adds the specified layer to a user-defined category using a custom name and title.
     * 
     * @param layerId
     *            id of the layer, must not be <code>null</code> (and already exist in themes file)
     * @param name
     *            name of the layer in the category, must not be <code>null</code>
     * @param title
     *            title of the layer in the category, must not be <code>null</code>
     * @param category
     *            category to place the new layer, must not be <code>null</code> (and already exist)
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void addUserLayer( String type, String layerId, String name, String title, String category )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();

        Set<String> themeIds = retrieveAllThemeIds( themes );

        if ( themeIds.contains( name ) ) {
            throw new IllegalArgumentException( "Eine Kategorie/Layer mit der id '" + name + "' existiert bereits." );
        }
        if ( !themes.getLayerStoreId().contains( layerId ) ) {
            throw new IllegalArgumentException(
                            "Kein automatisch verwalteter Rasterlayer mit id '" + layerId + " vorhanden." );
        }
        ThemeType categoryTheme = findUserCategory( themes, category );
        ThemeType layerTheme = new ThemeType();
        layerTheme.setIdentifier( createIdentifier( name ) );
        LanguageStringType languageTitle = new LanguageStringType();
        languageTitle.setValue( title );
        layerTheme.getTitle().add( languageTitle );
        categoryTheme.getTheme().add( layerTheme );
        Layer layer = new Layer();
        layer.setLayerStore( layerId );
        layer.setValue( layerId );
        layerTheme.getLayer().add( layer );
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Removes the specified user-defined layer.
     * 
     * @param layerId
     *            id of the layer, must not be <code>null</code>
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void removeUserLayer( String type, String layerId )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        checkIfGroupLayer( type, layerId );

        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();
        ThemeType removedLayer = removeUserLayer( layerId, themes.getTheme() );
        if ( removedLayer == null )
            throw new IllegalArgumentException( "Es wurde kein Layer mit der id '" + layerId + "' gefunden." );
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Removes all references to raster layers that belong to the specified plan.
     * 
     * @param planId
     *            id of the plan, must not be <code>null</code>
     * @throws JAXBException
     * @throws IOException
     */
    public void removeLayersForPlan( String type, String planId )
                    throws JAXBException, IOException, ConfigurationException {
        final String prefix = planId + "_";
        IdMatcher idMatcher = new IdMatcher() {
            @Override
            public boolean matches( String toMatch ) {
                return toMatch.startsWith( prefix );
            }
        };
        removeLayers( type, idMatcher );
    }

    /**
     * Removes all references to raster layers that belong to the specified plan with the specified rasterLayerId.
     * 
     * @param type
     *            of the plan (bplan, rplan, fplan, lplan), never <code>null</code>
     * @param planId
     *            id of the plan, must not be <code>null</code>
     * @param rasterId
     *            the id of the raster (layer id without id suffix) to remove, never <code>null</code>
     * 
     * @throws JAXBException
     * @throws IOException
     */
    public void removeLayersForPlan( String type, String planId, String rasterId )
                    throws JAXBException, IOException, ConfigurationException {
        final String layerId = planId + "_" + rasterId;
        IdMatcher idMatcher = new IdMatcher() {
            @Override
            public boolean matches( String toMatch ) {
                return toMatch.equals( layerId );
            }
        };
        removeLayers( type, idMatcher );
    }

    /**
     * Moves the specified user-defined layer to a different user-defined category.
     * 
     * @param layerId
     *            id of the layer, must not be <code>null</code>
     * @param category
     *            user-defined category, can be <code>null</code> (root category)
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void moveLayer( String type, String layerId, String category )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        checkIfGroupLayer( type, layerId );

        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();

        ThemeType layer = removeUserLayer( layerId, themes.getTheme() );
        if ( layer == null ) {
            throw new IllegalArgumentException( "Kein benutzer-definierter Layer mit id '" + layerId + "' vorhanden." );
        }
        ThemeType userCategory = findUserCategory( themes, category );
        userCategory.getTheme().add( layer );
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Moves all raster configurations from one config to another. If sourceType and targetType are equal, nothing is
     * moved.
     * 
     * @param sourceType
     *            the type of the plan to move from, never <code>null</code>
     * @param targetType
     *            the type of the plan to move to, never <code>null</code>
     * @param planId
     *            the id of the plan to move, never <code>null</code>
     * @throws JAXBException
     * @throws IOException
     * @throws ConfigurationException
     */
    public void moveLayers( String sourceType, String targetType, String planId )
                    throws JAXBException, IOException, ConfigurationException {
        if ( sourceType.equals( targetType ) )
            return;
        checkIfTypeIsSupported( sourceType );
        checkIfTypeIsSupported( targetType );

        WmsThemesConfig sourceThemesConfig = wmsWorkspaceWrapper.retrieveThemesForType( sourceType );
        Themes sourceThemes = sourceThemesConfig.getThemes();

        List<Layer> removedLayers = removeUserLayers( planId, sourceThemes.getTheme() );

        WmsThemesConfig targetThemesConfig = wmsWorkspaceWrapper.retrieveThemesForType( targetType );
        Themes targetThemes = targetThemesConfig.getThemes();

        ThemeType sortedTheme = targetThemes.getTheme().getTheme().get( 0 );
        List<Layer> layers = sortedTheme.getLayer();
        layers.addAll( removedLayers );
        for ( Layer removedLayer : removedLayers ) {
            String layerStore = removedLayer.getLayerStore();
            sourceThemes.getLayerStoreId().remove( layerStore );
            targetThemes.getLayerStoreId().add( layerStore );
        }
        persist( sourceThemes, sourceThemesConfig.getConfig() );
        persist( targetThemes, targetThemesConfig.getConfig() );
    }

    /**
     * Adds a new user-defined category layer.
     * 
     * @param name
     *            id of the category layer, must not be <code>null</code>
     * @param title
     *            title of the category layer, must not be <code>null</code>
     * @param uppercategory
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void addCategory( String type, String name, String title, String uppercategory )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();
        Set<String> themeIds = retrieveAllThemeIds( themes );

        if ( themeIds.contains( name ) ) {
            throw new IllegalArgumentException( "Eine Kategorie/Layer mit der id " + name + " exisitiert bereits." );
        }
        if ( uppercategory != null && !themeIds.contains( uppercategory ) ) {
            throw new IllegalArgumentException( "Es existiert keine Kategorie mit der id " + uppercategory + "." );
        }
        ThemeType theme = new ThemeType();
        theme.setIdentifier( createIdentifier( name ) );
        LanguageStringType titl = new LanguageStringType();
        titl.setValue( title );
        theme.getTitle().add( titl );
        findParentTheme( themes, uppercategory ).getTheme().add( theme );
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Removes a user-defined category layer.
     * 
     * @param name
     *            id of the category layer, must not be <code>null</code>
     * @throws JAXBException
     *             if the changes could not be persisted
     * @throws IOException
     */
    public void removeCategory( String type, String name )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();

        ThemeType upperTheme = themes.getTheme();
        boolean isRemoved = removeTheme( name, upperTheme );
        if ( !isRemoved )
            throw new IllegalArgumentException( "Keine benutzerdefinierte Kategorie '" + name + "' vorhanden." );
        persist( themes, themesConfig.getConfig() );
    }

    /**
     * Reorders the wms layer configuraton by the passed sort dates.
     * 
     * @param planId2sortDate
     *            plan ids mapped to the new sort date, never <code>null</code>
     * @throws ConfigurationException
     * @throws IOException
     * @throws JAXBException
     */
    public void reorderWmsLayers( Map<String, Date> planId2sortDate, String crs )
                    throws JAXBException, IOException, ConfigurationException {
        List<String> sortedByDate = rasterConfigurationSorter.sortByDateInDeegreeOrder( planId2sortDate );
        for ( String supportedType : supportedTypes ) {
            reorderWmsLayers( sortedByDate, crs, supportedType );
        }
    }

    private void reorderWmsLayers( List<String> sortedByDate, String crs, String supportedType )
                    throws JAXBException, IOException, ConfigurationException {
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( supportedType, crs );
        
        File config = themesConfig.getConfig();
        Themes themes = themesConfig.getThemes();

        ThemeType sortedTheme = themes.getTheme().getTheme().get( 0 );
        List<Layer> layers = sortedTheme.getLayer();
        rasterConfigurationSorter.sortLayers( layers, sortedByDate );
        persist( themes, config );
    }

    private void persist( Themes themes, File config )
                    throws JAXBException {
        JAXBContext ctx = JAXBContext.newInstance( "org.deegree.theme.persistence.standard.jaxb" );
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty( JAXB_FORMATTED_OUTPUT, TRUE );
        marshaller.marshal( themes, config );
    }

    private ThemeType findParentTheme( Themes themes, String uppercategory ) {
        if ( uppercategory == null ) {
            return themes.getTheme();
        }
        List<ThemeType> subthemes = themes.getTheme().getTheme();
        ThemeType parentTheme = findThemeByIdentifier( uppercategory, subthemes );
        if ( parentTheme != null )
            return parentTheme;
        throw new IllegalArgumentException( "Es existiert keine Kategorie mit der id '" + uppercategory + "'." );
    }

    private ThemeType findThemeByIdentifier( String identfier, List<ThemeType> subthemes ) {
        for ( ThemeType themeType : subthemes ) {
            if ( identfier.equals( themeType.getIdentifier().getValue() ) )
                return themeType;
            ThemeType subtheme = findThemeByIdentifier( identfier, themeType.getTheme() );
            if ( subtheme != null )
                return subtheme;
        }
        return null;
    }

    private int findIndex( List<Layer> layers, String succedingPlanId ) {
        if ( succedingPlanId != null ) {
            String prefix = succedingPlanId + "_";
            for ( int i = 0; i < layers.size(); i++ ) {
                if ( layers.get( i ).getLayerStore().startsWith( prefix ) ) {
                    return i;
                }
            }
        }
        return layers.size();
    }

    private void checkIfTypeIsSupported( String type ) {
        if ( !supportedTypes.contains( type ) ) {
            throw new IllegalArgumentException( "First parameter must be bplan, fplan, rplan, lplan or soplan." );
        }
    }

    private void checkIfGroupLayer( String type, String layerId ) {
        if ( layerId.equals( type + "raster_sortiert" ) )
            throw new IllegalArgumentException(
                            "Der automatisch verwaltete Gruppierungslayer kann nicht entfernt/verschoben werden." );
    }

    private Set<String> retrieveAllThemeIds( Themes themes ) {
        Set<String> themeIds = new HashSet<String>();
        addThemeIdsRecursively( themeIds, themes, themes.getTheme() );
        return themeIds;
    }

    private void addThemeIdsRecursively( Set<String> themeIds, Themes themes, ThemeType theme ) {
        Identifier identifier = theme.getIdentifier();
        if ( identifier != null ) {
            themeIds.add( identifier.getValue() );
        }
        for ( ThemeType subTheme : theme.getTheme() ) {
            addThemeIdsRecursively( themeIds, themes, subTheme );
        }
    }

    private void removeLayers( String type, IdMatcher idMatcher )
                    throws JAXBException, IOException, ConfigurationException {
        checkIfTypeIsSupported( type );
        WmsThemesConfig themesConfig = wmsWorkspaceWrapper.retrieveThemesForType( type );
        Themes themes = themesConfig.getThemes();

        removeLayerStoresWithPrefix( themes, idMatcher );
        removeLayersWithPrefixFromAutoTheme( themes, idMatcher );
        removeUserLayersFromRootTheme( themes, idMatcher );
        removeUserLayersFromUserCategories( themes, idMatcher );
        persist( themes, themesConfig.getConfig() );
    }

    private void removeLayerStoresWithPrefix( Themes themes, IdMatcher matcher ) {
        List<String> removeIds = new ArrayList<String>();
        for ( String layerStoreId : themes.getLayerStoreId() ) {
            if ( matcher.matches( layerStoreId ) ) {
                removeIds.add( layerStoreId );
            }
        }
        themes.getLayerStoreId().removeAll( removeIds );
    }

    private void removeLayersWithPrefixFromAutoTheme( Themes themes, IdMatcher matcher ) {
        ThemeType theme = themes.getTheme().getTheme().get( 0 );
        List<Layer> removeLayers = new ArrayList<Layer>();
        for ( Layer layer : theme.getLayer() ) {
            if ( matcher.matches( layer.getLayerStore() ) ) {
                removeLayers.add( layer );
            }
        }
        theme.getLayer().removeAll( removeLayers );
    }

    private void removeUserLayersFromRootTheme( Themes themes, IdMatcher matcher ) {
        List<ThemeType> removeThemes = new ArrayList<ThemeType>();
        for ( int i = 1; i < themes.getTheme().getTheme().size(); i++ ) {
            ThemeType theme = themes.getTheme().getTheme().get( i );
            if ( theme.getLayer().size() == 1 ) {
                if ( matcher.matches( theme.getLayer().get( 0 ).getValue() ) ) {
                    removeThemes.add( theme );
                }
            }
        }
        themes.getTheme().getTheme().removeAll( removeThemes );
    }

    private void removeUserLayersFromUserCategories( Themes themes, IdMatcher matcher ) {
        for ( int i = 1; i < themes.getTheme().getTheme().size(); i++ ) {
            ThemeType theme = themes.getTheme().getTheme().get( i );
            if ( !theme.getTheme().isEmpty() ) {
                List<ThemeType> userLayers = new ArrayList<ThemeType>();
                for ( ThemeType userLayer : theme.getTheme() ) {
                    if ( userLayer.getLayer().size() == 1 ) {
                        if ( matcher.matches( userLayer.getLayer().get( 0 ).getValue() ) ) {
                            userLayers.add( userLayer );
                        }
                    }
                }
                theme.getTheme().removeAll( userLayers );
            }
        }
    }

    private List<Layer> createNewRasterLayers( List<String> rasterLayerIds ) {
        List<Layer> newRasterLayers = new ArrayList<Layer>();
        for ( String rasterLayerId : rasterLayerIds ) {
            Layer layer = new Layer();
            layer.setLayerStore( rasterLayerId );
            layer.setValue( rasterLayerId );
            newRasterLayers.add( layer );
        }
        return newRasterLayers;
    }

    private ThemeType findUserCategory( Themes themes, String category ) {
        ThemeType categoryTheme = themes.getTheme();
        if ( category != null ) {
            ThemeType categoryThemeType = findThemeByIdentifier( category, categoryTheme.getTheme() );
            if ( categoryThemeType == null ) {
                throw new IllegalArgumentException(
                                "Benutzerdefinierte Kategorie '" + category + "' existiert nicht." );
            }
            if ( !categoryThemeType.getLayer().isEmpty() ) {
                throw new IllegalArgumentException( "'" + category + "' ist keine benutzerdefinierter Kategorie." );
            }
            return categoryThemeType;
        }
        return categoryTheme;
    }

    private ThemeType removeUserLayer( String themeId, ThemeType theme ) {
        for ( ThemeType subTheme : theme.getTheme() ) {
            if ( themeId.equals( subTheme.getIdentifier().getValue() ) ) {
                if ( !subTheme.getTheme().isEmpty() ) {
                    throw new IllegalArgumentException(
                                    "Kein benutzer-definierter Layer mit id '" + themeId + "' vorhanden." );
                }
                theme.getTheme().remove( subTheme );
                return subTheme;
            }
            ThemeType removedLayer = removeUserLayer( themeId, subTheme );
            if ( removedLayer != null )
                return removedLayer;
        }
        return null;
    }

    private List<Layer> removeUserLayers( String planId, ThemeType theme ) {
        String prefix = planId + "_";
        List<Layer> removedLayers = new ArrayList<>();
        for ( ThemeType subTheme : theme.getTheme() ) {
            List<Layer> removedLayersFromSubtheme = new ArrayList<>();
            List<Layer> layers = subTheme.getLayer();
            for ( Layer layer : layers ) {
                String value = layer.getValue();
                if ( value.startsWith( prefix ) ) {
                    removedLayersFromSubtheme.add( layer );
                }
            }
            subTheme.getLayer().removeAll( removedLayersFromSubtheme );
            removedLayers.addAll( removedLayersFromSubtheme );
            removedLayers.addAll( removeUserLayers( planId, subTheme ) );
        }
        return removedLayers;
    }

    private boolean removeTheme( String name, ThemeType upperTheme ) {
        for ( ThemeType theme : upperTheme.getTheme() ) {
            if ( name.equals( theme.getIdentifier().getValue() ) ) {
                upperTheme.getTheme().remove( theme );
                return true;
            }
            boolean removeTheme = removeTheme( name, theme );
            if ( removeTheme )
                return true;
        }
        return false;
    }

    private Identifier createIdentifier( String name ) {
        Identifier identifier = new Identifier();
        identifier.setValue( name );
        return identifier;
    }

    private interface IdMatcher {
        boolean matches( String toMatch );
    }

}