package de.latlon.xplan.planwerkwms;

import org.apache.commons.fileupload.FileItem;
import org.deegree.services.OWS;
import org.deegree.services.authentication.SecurityException;
import org.deegree.services.controller.AbstractOWS;
import org.deegree.services.controller.utils.HttpResponseBuffer;
import org.deegree.services.jaxb.controller.DeegreeServiceControllerType;
import org.deegree.services.jaxb.metadata.DeegreeServicesMetadataType;
import org.deegree.workspace.ResourceMetadata;
import org.deegree.workspace.Workspace;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * An {@link AbstractOWS} for the PlanwerkWms
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkWmsController extends AbstractOWS {

    protected PlanwerkWmsController( ResourceMetadata<OWS> metadata, Workspace workspace, Object jaxbConfig ) {
        super( metadata, workspace, jaxbConfig );
    }

    @Override
    protected void init( DeegreeServicesMetadataType mainMetadataConf, DeegreeServiceControllerType mainControllerConf,
                         Object controllerConfig ) {

    }

    @Override
    public void doKVP( Map<String, String> normalizedKVPParams, HttpServletRequest request, HttpResponseBuffer response,
                       List<FileItem> multiParts )
                    throws ServletException, IOException, SecurityException {

        PrintWriter writer = response.getWriter();
        writer.write( "TODO: list available Planwerke" );

    }

    @Override
    public void doXML( XMLStreamReader xmlStream, HttpServletRequest request, HttpResponseBuffer response,
                       List<FileItem> multiParts )
                    throws ServletException, IOException, SecurityException {

    }

    @Override
    public void destroy() {

    }

}
