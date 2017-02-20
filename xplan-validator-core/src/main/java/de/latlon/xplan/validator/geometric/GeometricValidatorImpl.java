package de.latlon.xplan.validator.geometric;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.deegree.commons.tom.ReferenceResolvingException;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * Validates <link>XPlanArchives</link> geometrically
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class GeometricValidatorImpl implements GeometricValidator {

    private static final Logger LOG = LoggerFactory.getLogger( GeometricValidatorImpl.class );

    // Maximum distance for gaps that are to be closed
    private static final double EPSILON = 0.10;

    @Override
    public ValidatorResult validateGeometry( XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
                                             List<ValidationOption> voOptions )
                                                             throws ValidatorException {
        List<String> errors = new ArrayList<>();
        List<String> warnings = new ArrayList<>();
        List<BadGeometry> badGeometries = new ArrayList<>();

        retrieveXPlanFeatureCollectionAndReturnErrors( warnings, errors, badGeometries, archive, crs, force, schema,
                                                       voOptions );

        ValidatorResult geometricValidatorResult = new GeometricValidatorResult( warnings, errors, badGeometries, crs,
                        ( errors.isEmpty() ) );

        return geometricValidatorResult;
    }

    @Override
    public XPlanFeatureCollection retrieveGeometricallyValidXPlanFeatures( XPlanArchive archive, ICRS crs,
                                                                           AppSchema schema, boolean force,
                                                                           String internalId )
                                                                                           throws XMLStreamException,
                                                                                           UnknownCRSException,
                                                                                           ValidatorException {

        return new XPlanFeatureCollection( retrieveFeatureCollection( new ArrayList<String>(), new ArrayList<String>(),
                                                                      new ArrayList<BadGeometry>(), archive, crs, force,
                                                                      schema, null, internalId ),
                        archive.getType() );
    }

    private void resolveAndValidateXlinks( GMLStreamReader gmlStream, List<String> errors ) {
        long begin = System.currentTimeMillis();
        LOG.info( "- Überprüfung der XLink-Integrität..." );
        try {
            gmlStream.getIdContext().resolveLocalRefs();
            // now check for remote feature references
            List<GMLReference<?>> gmlRefs = gmlStream.getIdContext().getReferences();
            for ( GMLReference<?> gmlReference : gmlRefs )
                if ( gmlReference instanceof FeatureReference )
                    if ( !gmlReference.isLocal() ) {
                        String msg = format( "Fehler: Dokument enthält eine externe Feature-Referenz ('%s'). "
                                             + "Nur Dokument-lokale xlinks werden unterstützt.",
                                             gmlReference.getURI() );
                        LOG.info( msg );
                        errors.add( msg );
                    }
        } catch ( ReferenceResolvingException e ) {
            LOG.trace( "Resolving XLinks failed!", e );
            String errorMessage = format( "Die XLink-Integrität konnte nicht sichergestellt werden: %s",
                                          e.getMessage() );
            LOG.info( errorMessage );
            errors.add( errorMessage );
        }
        long elapsed = System.currentTimeMillis() - begin;
        LOG.info( "OK [{} ms]", elapsed );
    }

    private void retrieveXPlanFeatureCollectionAndReturnErrors( List<String> warnings, List<String> errors,
                                                                List<BadGeometry> badGeometries, XPlanArchive archive,
                                                                ICRS crs, boolean force, AppSchema schema,
                                                                List<ValidationOption> voOptions )
                                                                                throws ValidatorException {
        try {
            retrieveFeatureCollection( warnings, errors, badGeometries, archive, crs, force, schema, voOptions, null );
        } catch ( XMLStreamException | UnknownCRSException e ) {
            LOG.trace( "Geomteric validation failed!", e );
            throw new ValidatorException(
                            "Geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen", e );
        }
    }

    private FeatureCollection retrieveFeatureCollection( List<String> warnings, List<String> errors,
                                                         List<BadGeometry> badGeometries, XPlanArchive archive,
                                                         ICRS crs, boolean force, AppSchema schema,
                                                         List<ValidationOption> voOptions, String internalId )
                                                                         throws XMLStreamException,
                                                                         UnknownCRSException {

        PerformReadGmlStream performReadGmlStream = new PerformReadGmlStream( archive, crs, schema, voOptions,
                        internalId );

        if ( performReadGmlStream.getErrors() != null )
            errors.addAll( performReadGmlStream.getErrors() );
        if ( performReadGmlStream.getWarnings() != null )
            warnings.addAll( performReadGmlStream.getWarnings() );
        if ( performReadGmlStream.getBadList() != null )
            badGeometries.addAll( performReadGmlStream.getBadList() );
        long elapsed = performReadGmlStream.getElapsed();
        FeatureCollection xplanFeatures = performReadGmlStream.getXplanFeatures();
        GMLStreamReader gmlStream = performReadGmlStream.getGmlStream();

        if ( !errors.isEmpty() )
            printErrorMessages( errors, force, warnings );
        else
            printSuccessMessages( warnings, elapsed, xplanFeatures );
        resolveAndValidateXlinks( gmlStream, errors );
        return xplanFeatures;
    }

    private void printSuccessMessages( List<String> warnings, long elapsed, FeatureCollection xplanFeatures ) {
        LOG.info( "OK [{} ms]: {} Features", elapsed, xplanFeatures.size() );
        if ( !warnings.isEmpty() ) {
            LOG.info( "Geometrie-Warnungen: {}", warnings.size() );
            for ( String warning : warnings )
                LOG.info( " - {}", warning );
        }
    }

    private void printErrorMessages( List<String> errors, boolean force, List<String> warnings ) {
        if ( !warnings.isEmpty() ) {
            LOG.info( "Geometrie-Warnungen: {}", warnings.size() );
            for ( String warning : warnings )
                LOG.info( " - {}", warning );
        }
        LOG.info( "Geometrie-Fehler: {}", errors.size() );
        for ( String error : errors )
            LOG.info( " - {}", error );
        if ( !force ) {
            LOG.info( "{} Geometrie-Fehler, {} Geometrie-Warnung(en). Hinweis: Sie k\u00f6nnen das "
                      + "Importieren des Plans mit der Kommandozeilen-Option --force erzwingen.", errors.size(),
                      warnings.size() );
            throw new IllegalArgumentException( "Der Plan kann aufgrund von " + errors.size()
                                                + " Geometrie-Fehler(n) und " + warnings.size()
                                                + " Geometrie-Warnung(en) nicht verarbeitet werden." );
        } else
            LOG.info( "Fortsetzung trotz Geometrie-Fehlern (--force)." );
    }

    /**
     * Method object encapsulating the construction and read from the gml stream
     * 
     * @author erben
     */
    private class PerformReadGmlStream {

        private final FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();

        private List<String> errors = new ArrayList<>();

        private List<String> warnings = new ArrayList<>();

        private List<BadGeometry> badList = new ArrayList<>();

        private GMLStreamReader gmlStream;

        private FeatureCollection xplanFeatures;

        private long elapsed;

        PerformReadGmlStream( XPlanArchive archive, ICRS crs, AppSchema schema, List<ValidationOption> voOptions,
                              String internalId ) throws XMLStreamException, UnknownCRSException {
            XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper( archive.getMainFileXmlReader(), null );
            long begin = System.currentTimeMillis();
            LOG.info( "- Einlesen der Features (+ Geometrievalidierung)..." );
            GeometryFactory geomFac = new GeometryFactory();
            XPlanGeometryInspector inspector = new XPlanGeometryInspector( xmlStream, EPSILON, voOptions );
            geomFac.addInspector( inspector );
            GMLVersion gmlVersion = archive.getVersion().getGmlVersion();
            gmlStream = GMLInputFactory.createGMLStreamReader( gmlVersion, xmlStream );
            gmlStream.setDefaultCRS( crs );
            gmlStream.setGeometryFactory( geomFac );
            gmlStream.setApplicationSchema( schema );
            try {
                xplanFeatures = (FeatureCollection) gmlStream.readFeature( true );
                if ( internalId != null )
                    featureCollectionManipulator.addInternalId( xplanFeatures, schema, internalId );
                elapsed = System.currentTimeMillis() - begin;
                errors = inspector.getErrors();
                List<String> brokenGeometryErrors = extendMessagesOfBrokenGeometryErrors();
                errors.addAll( brokenGeometryErrors );
                warnings = inspector.getWarnings();
                badList = inspector.getBadGeometries();
            } catch ( XMLParsingException e ) {
                String msg = "Die geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen. "
                             + "Das XPlanGML-Dokument (xplan.gml) entspricht nicht dem GML-Schema.";
                errors.add( msg );
            }
        }

        List<String> getErrors() {
            return errors;
        }

        public List<BadGeometry> getBadList() {
            return badList;
        }

        GMLStreamReader getGmlStream() {
            return gmlStream;
        }

        FeatureCollection getXplanFeatures() {
            return xplanFeatures;
        }

        long getElapsed() {
            return elapsed;
        }

        List<String> getWarnings() {

            return warnings;
        }

        private List<String> extendMessagesOfBrokenGeometryErrors() {
            ArrayList<String> extendedBrokenGeometryErrors = new ArrayList<>();
            List<String> brokenGeometryErrors = gmlStream.getSkippedBrokenGeometryErrors();
            for ( String brokenGeometryError : brokenGeometryErrors ) {
                extendedBrokenGeometryErrors.add( brokenGeometryError + " - Achtung: Die Geometrie ist so stark "
                                                  + "besch\u00e4digt, dass sie nicht f\u00fcr die Shapefile- und Bildgenerierung verwendet "
                                                  + "werden kann." );
            }
            return extendedBrokenGeometryErrors;
        }

    }

}