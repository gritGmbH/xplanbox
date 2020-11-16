/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.syntactic;

import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.latlon.xplan.commons.XPlanAde;
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
        try ( InputStream is = archive.getMainFileInputStream() ) {
            return validateSyntax( is, archive.getVersion(), archive.getAde() );
        } catch ( IOException e ) {
            LOG.error( "Syntaktische Valdierung wurde aufgrund eines Fehlers abgebrochen: {}", e.getMessage() );
            LOG.trace( "Syntactically validation failed!", e );
        }
        return new SyntacticValidatorResult( Collections.emptyList(), true, createDetail( true ) );
    }

    @Override
    public ValidatorResult validateSyntax( InputStream is, XPlanVersion version, XPlanAde ade ) {
        List<String> resultMessages = new ArrayList<>();
        String schemaUrl = findSchemaUrl( ade, version );
        List<SchemaValidationEvent> schemaValidationEvents = SchemaValidator.validate( is, schemaUrl );
        appendResultMessages( resultMessages, schemaValidationEvents );
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

    private String findSchemaUrl( XPlanAde ade, XPlanVersion version ) {
        String schemaUrl;
        if ( ade != null )
            schemaUrl = ade.getSchemaUrl().toString();
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
