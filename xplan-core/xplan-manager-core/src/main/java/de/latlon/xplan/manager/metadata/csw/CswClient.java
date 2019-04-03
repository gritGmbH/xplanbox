package de.latlon.xplan.manager.metadata.csw;

import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.commons.xml.XMLAdapter;
import org.deegree.commons.xml.XPath;
import org.deegree.metadata.MetadataRecord;
import org.deegree.protocol.csw.client.CSWClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CswClient {

    private static final Logger LOG = LoggerFactory.getLogger( CswClient.class );

    public static final String RESOURCE_IDENTIFIER_XPATH = "//gmd:MD_Metadata/gmd:identificationInfo[1]/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:identifier/gmd:MD_Identifier/gmd:code/gco:CharacterString";

    private final NamespaceBindings nsContext = new NamespaceBindings();

    private final CSWClient cswClient;

    public CswClient( String cswUrl )
                    throws DataServiceCouplingException {
        try {
            cswClient = new CSWClient( new URL( cswUrl ) );
        } catch ( Exception e ) {
            throw new DataServiceCouplingException( "CswClient is not available", e );
        }
        nsContext.addNamespace( "srv", "http://www.isotc211.org/2005/srv" );
        nsContext.addNamespace( "gmd", "http://www.isotc211.org/2005/gmd" );
        nsContext.addNamespace( "gco", "http://www.isotc211.org/2005/gco" );
    }

    public PlanRecordMetadata requestMetadataRecord()
                    throws DataServiceCouplingException {
        try {
            MetadataRecord metadataRecord = cswClient.getIsoRecordById( "CC9E9E0D-07AD-4C77-ADAB-AFDA37585633" );
            String recordId = metadataRecord.getIdentifier();
            String resourceIdentifier = parseResourceIdentifier( metadataRecord );

            return new PlanRecordMetadata( recordId, resourceIdentifier );
        } catch ( Exception e ) {
            LOG.error( "Could not request metadata record", e );
            throw new DataServiceCouplingException( e );
        }
    }

    /**
     * Creates a GetRecordById request, with filter to the passed recordId.
     *
     * @param recordId
     *                 may be <code>null</code>
     * @return the created GetRecordById or <code>null</code> if the recordId is <code>null</code> or empty
     */
    public String createGetRecordByIdRequest( String recordId )
                    throws DataServiceCouplingException {
        if ( recordId == null || recordId.isEmpty() )
            return null;
        StringBuilder sb = new StringBuilder();
        String recordByIdUrl = getRecordByIdUrl();
        sb.append( recordByIdUrl );
        if ( !recordByIdUrl.endsWith( "?" ) )
            sb.append( "?" );
        sb.append( "REQUEST=GetRecordById&" );
        sb.append( "VERSION=2.0.2&" );
        sb.append( "SERVICE=CSW&" );
        sb.append( "OUTPUTSCHEMA=http://www.isotc211.org/2005/gmd&" );
        sb.append( "ID=" ).append( recordId );
        return sb.toString();
    }

    private String getRecordByIdUrl()
                    throws DataServiceCouplingException {
        if ( cswClient.getOperations() != null ) {
            List<URL> getRecordByIdUrls = cswClient.getOperations().getGetUrls( "GetRecordById" );
            if ( !getRecordByIdUrls.isEmpty() ) {
                return getRecordByIdUrls.get( 0 ).toExternalForm();
            }
        }
        throw new DataServiceCouplingException( "Could not find GetRecordById URL for KVP" );
    }

    private String parseResourceIdentifier( MetadataRecord metadataRecord ) {
        XMLAdapter adapter = new XMLAdapter( metadataRecord.getAsOMElement() );
        return adapter.getNodeAsString( adapter.getRootElement(), new XPath( RESOURCE_IDENTIFIER_XPATH, nsContext ),
                                        null );
    }

}