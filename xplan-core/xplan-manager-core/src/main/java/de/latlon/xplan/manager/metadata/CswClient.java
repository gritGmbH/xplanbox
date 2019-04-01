package de.latlon.xplan.manager.metadata;

import org.deegree.metadata.MetadataRecord;
import org.deegree.protocol.csw.client.CSWClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CswClient {

    private static final Logger LOG = LoggerFactory.getLogger( CswClient.class );

    private String cswUrl;

    private String metadataResourceUrlTemplate;

    public CswClient( String cswUrl ) {
        this.cswUrl = cswUrl;
    }

    public CoupledResource requestMetadataRecord()
                    throws DataServiceCouplingException {
        try {
            CSWClient cswClient = new CSWClient( new URL( cswUrl ) );
            MetadataRecord isoRecordById = cswClient.getIsoRecordById( "CC9E9E0D-07AD-4C77-ADAB-AFDA37585633" );
            String identifier = isoRecordById.getIdentifier();
            String url = metadataResourceUrlTemplate.replace( "${METADATA_RECORD_IDENTIFIER}", identifier );
            return new CoupledResource( identifier, url );
        } catch ( Exception e ) {
            LOG.error( "Could not request metadata record", e );
            throw new DataServiceCouplingException( e );
        }
    }

}