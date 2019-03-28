package de.latlon.xplan.planwerkwms;

import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.DefaultResourceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static de.latlon.xplan.planwerkwms.PlanwerkProvider.NAMESPACE;
import static org.deegree.commons.xml.XMLAdapter.writeElement;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger( PlanwerkFilter.class );

    private PlanwerkReader planwerkReader;

    private String wmsId;

    private String planStatus;

    @Override
    public void init( FilterConfig filterConfig )
                    throws ServletException {
        String jdbcConnId = filterConfig.getInitParameter( "JdbcConnId" );
        wmsId = filterConfig.getInitParameter( "WmsId" );
        planStatus = filterConfig.getInitParameter( "PlanstatusDB" );
        planwerkReader = new PlanwerkReader( jdbcConnId );
    }

    @Override
    public void doFilter( ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain )
                    throws IOException, ServletException {
        tryToAddIfUnavailable( servletRequest );
        filterChain.doFilter( servletRequest, servletResponse );
    }

    @Override
    public void destroy() {
    }

    private void tryToAddIfUnavailable( ServletRequest servletRequest ) {
        try {
            if ( servletRequest instanceof HttpServletRequest ) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                String pathInfo = httpServletRequest.getPathInfo();
                if ( pathInfo != null ) {
                    String serviceId = pathInfo.substring( 1 );
                    Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
                    OWS resource = workspace.getResource( OWSProvider.class, serviceId );
                    if ( resource == null ) {
                        tryToAdd( workspace, serviceId );
                    }
                }
            }
        } catch ( Exception e ) {
            LOG.error( "Requested plan could not be added", e );
        }
    }

    private void tryToAdd( Workspace workspace, String serviceId )
                    throws XMLStreamException, IOException {
        String planName = serviceId.substring( serviceId.lastIndexOf( "/" ) + 1, serviceId.length() );
        Plan plan = planwerkReader.retrieveAvailablePlanwerke( workspace, planName, planStatus );
        if ( plan == null ) {
            LOG.info( "Plan with name is not available from database" );
            return;
        }

        byte[] planConfig = writePlanwerkConfig( planName, plan, wmsId );
        DefaultResourceIdentifier<OWS> identifier = new DefaultResourceIdentifier<>( OWSProvider.class, serviceId );
        WmsMetadata wmsMetadata = (WmsMetadata) workspace.getResourceMetadata( OWSProvider.class, wmsId );
        PlanwerkResourceLocation resourceLocation = new PlanwerkResourceLocation( planConfig, identifier, wmsMetadata );

        workspace.add( resourceLocation );
        workspace.init( identifier, null );
    }

    private byte[] writePlanwerkConfig( String planname, Plan plan, String wmsId )
                    throws XMLStreamException, IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter( bos );
        writer.writeStartDocument();
        writer.setDefaultNamespace( NAMESPACE );
        writer.writeStartElement( NAMESPACE, "Planwerk" );
        writer.writeDefaultNamespace( NAMESPACE );
        writeElement( writer, NAMESPACE, "PlanwerkWms", wmsId );
        writeElement( writer, NAMESPACE, "Name", planname );
        writeElement( writer, NAMESPACE, "Envelope", plan.getBbox() );
        for ( Integer managerId : plan.getManagerIds() ) {
            writeElement( writer, NAMESPACE, "ManagerId", managerId.toString() );
        }
        writer.writeEndElement();
        writer.close();
        bos.close();
        return bos.toByteArray();
    }

}