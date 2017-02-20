package de.latlon.xplan.validator.syntactic;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.xni.parser.XMLParseException;
import org.deegree.commons.xml.schema.SchemaValidationEvent;
import org.deegree.commons.xml.schema.SchemaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class SyntacticValidatorImpl implements SyntacticValidator {

    private static final Logger LOG = LoggerFactory.getLogger( SyntacticValidatorImpl.class );

    private static final String INVALID_HINT = "Das Instanzobjekt 'xplan.gml' ist nicht konform zum XPlanGML Schema. Eine \u00DCberpr\u00FCfung ist erforderlich.";

    @Override
    public ValidatorResult validateSyntax( XPlanArchive archive ) {
        List<String> resultMessages = new ArrayList<>();
        try ( InputStream is = archive.getMainFileInputStream() ) {
            XPlanVersion version = archive.getVersion();
            String schemaUrl = findSchemaUrl( archive, version );
            List<SchemaValidationEvent> schemaValidationEvents = SchemaValidator.validate( is, schemaUrl );
            appendResultMessages( resultMessages, schemaValidationEvents );
        } catch ( IOException e ) {
            LOG.error( "Syntaktische Valdierung wurde aufgrund eines Fehlers abgebrochen: {}", e.getMessage() );
            LOG.trace( "Syntactically validation failed!", e );
        }
        boolean isValid = resultMessages.isEmpty();
        ValidatorDetail detailsHint = createDetail( isValid );
        return new SyntacticValidatorResult( resultMessages, isValid, detailsHint );
    }

    @Override
    public void validateReferences( XPlanArchive archive, ExternalReferenceInfo externalReferenceInfo, boolean force )
                    throws ValidatorException {
        for ( ExternalReference ref : externalReferenceInfo.getExternalRefs() ) {
            String referenceUrl = ref.getReferenzUrl();
            if ( referenceUrl != null && !referenceUrl.contains( ":/" ) && archive.getEntry( referenceUrl ) == null ) {
                sendErrorReference( referenceUrl, force );
            }
            String geoRefUrl = ref.getGeoRefUrl();
            if ( geoRefUrl != null && !geoRefUrl.contains( ":/" ) && archive.getEntry( geoRefUrl ) == null ) {
                sendErrorReference( geoRefUrl, force );
            }
        }
    }

    private void sendErrorReference( String referenceURL, boolean force )
                    throws ValidatorException {
        LOG.info( "Referenz-Fehler: Das Hauptdokument enth\u00e4lt die relative URL '{}', "
                  + "aber die Datei ist nicht im Archiv enthalten.", referenceURL );
        if ( !force ) {
            LOG.error( "Aufgrund von Referenz-Fehlern wurde die Validierung abgebrochen" );
            throw new ValidatorException( "Validierung wurde aufgrund von Referenz-Fehlern abgebrochen" );
        } else {
            LOG.info( "Fortsetzung trotz Referenz-Fehlern." );
        }
    }

    private void appendResultMessages( List<String> resultMessages,
                                       List<SchemaValidationEvent> schemaValidationEvents ) {
        for ( SchemaValidationEvent schemaValidationEvent : schemaValidationEvents ) {
            XMLParseException exception = schemaValidationEvent.getException();
            int line = exception.getLineNumber();
            int column = exception.getColumnNumber();
            String exceptionMessage = exception.getMessage();
            String resultMessage = format( "%s Zeile: %s, Spalte %s", exceptionMessage, line, column );
            resultMessages.add( resultMessage );
        }
    }

    private String findSchemaUrl( XPlanArchive archive, XPlanVersion version ) {
        String schemaUrl;
        if ( archive.getAde() != null )
            schemaUrl = archive.getAde().getSchemaUrl().toString();
        else
            schemaUrl = version.getSchemaUrl().toString();
        return schemaUrl;
    }

    private ValidatorDetail createDetail( boolean isValid ) {
        ValidatorDetail detail = null;
        if ( !isValid )
            detail = new ValidatorDetail( INVALID_HINT );
        return detail;
    }

}