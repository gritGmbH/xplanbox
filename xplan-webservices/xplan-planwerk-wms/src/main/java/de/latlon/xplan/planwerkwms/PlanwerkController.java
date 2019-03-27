package de.latlon.xplan.planwerkwms;

import org.apache.commons.fileupload.FileItem;
import org.deegree.services.controller.utils.HttpResponseBuffer;
import org.deegree.services.wms.controller.WMSController;
import org.deegree.workspace.Workspace;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkController extends WMSController {

    private static final String PARAM_NAME_MANAGERID = "PLANWERK_MANAGERID";

    private final List<Integer> planwerkManagerIds;

    public PlanwerkController( PlanwerkMetadata metadata, Workspace workspace, List<Integer> planwerkManagerIds ) {
        super( metadata, workspace, metadata.getDeegreeWmsConfig() );
        this.planwerkManagerIds = planwerkManagerIds;
    }

    @Override
    public void doKVP( Map<String, String> map, HttpServletRequest request, HttpResponseBuffer response,
                       List<FileItem> multiParts )
                    throws ServletException, IOException {
        addManagerIdParameter( map );
        super.doKVP( map, request, response, multiParts );
    }

    private void addManagerIdParameter( Map<String, String> map ) {
        String managerIds = planwerkManagerIds.stream().map( managerId -> managerId.toString() ).collect(
                        joining( "," ) );
        map.put( PARAM_NAME_MANAGERID, managerIds );
    }

}