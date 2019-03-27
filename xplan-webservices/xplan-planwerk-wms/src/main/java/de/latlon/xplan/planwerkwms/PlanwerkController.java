package de.latlon.xplan.planwerkwms;

import org.apache.commons.fileupload.FileItem;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.metadata.SpatialMetadata;
import org.deegree.layer.metadata.LayerMetadata;
import org.deegree.services.controller.utils.HttpResponseBuffer;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.services.wms.controller.capabilities.theme.DefaultMetadataMerger;
import org.deegree.services.wms.controller.capabilities.theme.MetadataMerger;
import org.deegree.theme.Theme;
import org.deegree.workspace.Workspace;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkController extends WMSController {

    private static final String PARAM_NAME_MANAGERID = "PLANWERK_MANAGERID";

    private final Planwerk planwerk;

    public PlanwerkController( PlanwerkMetadata metadata, Workspace workspace, Planwerk planwerk ) {
        super( metadata, workspace, metadata.getDeegreeWmsConfig() );
        this.planwerk = planwerk;
    }

    @Override
    public void doKVP( Map<String, String> map, HttpServletRequest request, HttpResponseBuffer response,
                       List<FileItem> multiParts )
                    throws ServletException, IOException {
        addManagerIdParameter( map );
        super.doKVP( map, request, response, multiParts );
    }

    @Override
    public MetadataMerger getMetadataMerger() {
        return new MetadataMerger() {
            @Override
            public SpatialMetadata mergeSpatialMetadata( List<Theme> themes ) {
                return createPlanwerkSpatialMetadata( themes );
            }

            @Override
            public LayerMetadata mergeLayerMetadata( Theme theme ) {
                DefaultMetadataMerger defaultMetadataMerger = new DefaultMetadataMerger();
                LayerMetadata layerMetadata = defaultMetadataMerger.mergeLayerMetadata( theme );
                layerMetadata.setSpatialMetadata( createPlanwerkSpatialMetadata( theme ) );
                return layerMetadata;
            }

            private SpatialMetadata createPlanwerkSpatialMetadata( Theme theme ) {
                List<ICRS> coordinateSystems = theme.getLayerMetadata().getSpatialMetadata().getCoordinateSystems();
                return new SpatialMetadata( planwerk.getBbox(), coordinateSystems );
            }

            private SpatialMetadata createPlanwerkSpatialMetadata( List<Theme> themes ) {
                List<ICRS> coordinateSystems = getCrs( themes );
                return new SpatialMetadata( planwerk.getBbox(), coordinateSystems );
            }

            private List<ICRS> getCrs( List<Theme> themes ) {
                if ( themes.isEmpty() )
                    return Collections.emptyList();
                Theme firstTheme = themes.get( 0 );
                return firstTheme.getLayerMetadata().getSpatialMetadata().getCoordinateSystems();
            }
        };
    }

    private void addManagerIdParameter( Map<String, String> map ) {
        List<Integer> managerIds = planwerk.getManagerIds();
        String ids = managerIds.stream().map( managerId -> managerId.toString() ).collect( joining( "," ) );
        map.put( PARAM_NAME_MANAGERID, ids );
    }

}