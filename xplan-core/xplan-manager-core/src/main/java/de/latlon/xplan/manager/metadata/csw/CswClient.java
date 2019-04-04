package de.latlon.xplan.manager.metadata.csw;

import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import org.deegree.commons.tom.ows.Version;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.commons.xml.XMLAdapter;
import org.deegree.commons.xml.XPath;
import org.deegree.filter.Expression;
import org.deegree.filter.Filter;
import org.deegree.filter.MatchAction;
import org.deegree.filter.OperatorFilter;
import org.deegree.filter.comparison.PropertyIsLike;
import org.deegree.filter.expression.Literal;
import org.deegree.filter.expression.ValueReference;
import org.deegree.metadata.MetadataRecord;
import org.deegree.protocol.csw.CSWConstants;
import org.deegree.protocol.csw.client.CSWClient;
import org.deegree.protocol.csw.client.getrecords.GetRecords;
import org.deegree.protocol.csw.client.getrecords.GetRecordsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CswClient {

    private static final Logger LOG = LoggerFactory.getLogger( CswClient.class );

    public static final String RESOURCE_IDENTIFIER_XPATH = "//gmd:MD_Metadata/gmd:identificationInfo[1]/gmd:MD_DataIdentification/gmd:citation/gmd:CI_Citation/gmd:identifier/gmd:MD_Identifier/gmd:code/gco:CharacterString";

    public static final QName TYPE_NAME = new QName( "http://www.isotc211.org/2005/gmd", "MD_Metadata", "gmd" );

    public static final String OUTPUT_FORMAT = "gmd:MD_Metadata";

    public static final String OUTPUT_SCHEMA = "http://www.isotc211.org/2005/gmd";

    public static final QName TITLE = new QName( "http://www.opengis.net/cat/csw/apiso/1.0", "title", "apiso" );

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

    public PlanRecordMetadata requestMetadataRecord( String planName )
                    throws DataServiceCouplingException {
        try {
            GetRecords getRecords = createGetRecordsRecordsRequest( planName );
            GetRecordsResponse records = cswClient.getRecords( getRecords );
            int numberOfRecordsMatched = records.getNumberOfRecordsMatched();
            if ( numberOfRecordsMatched == 0 )
                return null;
            if ( numberOfRecordsMatched > 1 )
                LOG.warn( "Found more than one matching record for plan with name {}. The first returned is used.",
                          planName );
            MetadataRecord metadataRecord = records.getRecords().next();
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
        sb.append( "OUTPUTSCHEMA=" ).append( OUTPUT_SCHEMA ).append( "&" );
        sb.append( "ID=" ).append( recordId );
        return sb.toString();
    }

    private GetRecords createGetRecordsRecordsRequest( String planName ) {
        Version version = new Version( 2, 0, 2 );
        int startPosition = 1;
        int maxRecords = 1;
        List<QName> typeNames = Collections.singletonList( TYPE_NAME );
        CSWConstants.ResultType resultType = CSWConstants.ResultType.results;
        CSWConstants.ReturnableElement elementSetName = CSWConstants.ReturnableElement.full;
        Filter constraint = createFilterForPlanName( planName );

        return new GetRecords( version, startPosition, maxRecords, OUTPUT_FORMAT, OUTPUT_SCHEMA, typeNames, resultType,
                               elementSetName, constraint );
    }

    private Filter createFilterForPlanName( String planName ) {
        ValueReference valueReference = new ValueReference( TITLE );
        Expression pattern = new Literal<>( "%" + planName + "%" );
        String wildCard = "%";
        String singleChar = "_";
        String escapeChar = "\\";
        Boolean matchCase = false;
        MatchAction matchAction = MatchAction.ANY;
        PropertyIsLike propertyIsLike = new PropertyIsLike( valueReference, pattern, wildCard, singleChar, escapeChar,
                                                            matchCase, matchAction );
        return new OperatorFilter( propertyIsLike );
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