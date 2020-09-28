package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.inspector.FlaechenschlussInspector;
import de.latlon.xplan.validator.geometric.inspector.GeltungsbereichInspector;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.commons.tom.ReferenceResolvingException;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.xml.XMLParsingException;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;

/**
 * Validates <link>XPlanArchives</link> geometrically
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 */
public class GeometricValidatorImpl implements GeometricValidator {

    private static final Logger LOG = LoggerFactory.getLogger( GeometricValidatorImpl.class );

    // Maximum distance for gaps that are to be closed
    private static final double EPSILON = 0.10;

    public static final String SKIP_FLAECHENSCHLUSS_OPTION = "skip-flaechenschluss";

    public static final String SKIP_GELTUNGSBEREICH_OPTION = "skip-geltungsbereich";

    public static final ValidationOption SKIP_FLAECHENSCHLUSS = new ValidationOption( SKIP_FLAECHENSCHLUSS_OPTION,
                                                                                      Boolean.toString( true ) );

    public static final ValidationOption SKIP_GELTUNGSBEREICH = new ValidationOption( SKIP_GELTUNGSBEREICH_OPTION,
                                                                                      Boolean.toString( true ) );

    public static final ArrayList<ValidationOption> SKIP_OPTIONS = new ArrayList<>();

    static  {
        SKIP_OPTIONS.add( SKIP_FLAECHENSCHLUSS );
        SKIP_OPTIONS.add( SKIP_GELTUNGSBEREICH );
    }

    @Override
    public GemetricValidatorParsingResult validateGeometry( XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
                                             List<ValidationOption> voOptions )
                            throws ValidatorException {
        try {
            ParserAndValidatorResult result = retrieveFeatureCollection( archive, crs, force, schema, voOptions );
            GeometricValidatorResult validationResult = new GeometricValidatorResult( result.warnings,
                                                                                              result.errors,
                                                                                              result.badGeometries, crs,
                                                                                              result.isValid() );
            XPlanFeatureCollection features = new XPlanFeatureCollectionBuilder( result.xPlanFeatures,
                                                                              archive.getType() ).build();
            return new GemetricValidatorParsingResult(features, validationResult);
        } catch ( XMLStreamException e ) {
            LOG.trace( "Geometric validation failed!", e );
            throw new ValidatorException(
                                    "Geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen",
                                    e );
        }
    }

    @Override
    public XPlanFeatureCollection retrieveGeometricallyValidXPlanFeatures( XPlanArchive archive, ICRS crs,
                                                                           AppSchema schema, boolean force,
                                                                           String internalId )
                            throws XMLStreamException, UnknownCRSException {
        ParserAndValidatorResult result = retrieveFeatureCollection( archive, crs, force, schema, SKIP_OPTIONS );
        return new XPlanFeatureCollectionBuilder( result.xPlanFeatures, archive.getType() ).build();
    }

    private ParserAndValidatorResult retrieveFeatureCollection( XPlanArchive archive, ICRS crs, boolean force,
                                                                AppSchema schema, List<ValidationOption> voOptions )
                            throws XMLStreamException {
        ParserAndValidatorResult result = readAndValidateArchive( archive, crs, schema, voOptions );
        logResult( force, result );
        return result;
    }

    private ParserAndValidatorResult readAndValidateArchive( XPlanArchive archive, ICRS crs, AppSchema schema,
                                                             List<ValidationOption> voOptions )
                            throws XMLStreamException {
        ParserAndValidatorResult result = new ParserAndValidatorResult();
        XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper( archive.getMainFileXmlReader(), null );
        long begin = System.currentTimeMillis();
        LOG.info( "- Einlesen der Features (+ Geometrievalidierung)..." );
        XPlanGeometryInspector geometryInspector = new XPlanGeometryInspector( xmlStream );
        List<GeometricFeatureInspector> featureInspectors = createInspectors( voOptions );
        GMLStreamReader gmlStream = createGmlStreamReader( archive, crs, schema, xmlStream, geometryInspector,
                                                           featureInspectors );
        try {
            FeatureCollection xPlanFeatures = (FeatureCollection) gmlStream.readFeature();
            result.setXplanFeatures( xPlanFeatures );
            result.elapsed = System.currentTimeMillis() - begin;
            result.addErrors( geometryInspector.getErrors() );
            List<String> brokenGeometryErrors = extendMessagesOfBrokenGeometryErrors( gmlStream );
            result.addErrors( brokenGeometryErrors );
            result.addWarnings( geometryInspector.getWarnings() );
            result.addBadGeometries( geometryInspector.getBadGeometries() );
            featureInspectors.stream().forEach( fi -> checkAndAddRules( fi, result ) );


            resolveAndValidateXlinks( gmlStream, result );
        } catch ( XMLParsingException e ) {
            String msg = "Die geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen. "
                         + "Das XPlanGML-Dokument (xplan.gml) entspricht nicht dem GML-Schema.";
            result.addError( msg );
            LOG.info( "Unexpected failure by geometry validation ", e );
        } catch ( Exception e ) {
            String msg = "Die geometrische Validierung wurde aufgrund von schwerwiegenden Fehlern abgebrochen. "
                         + "Das XPlanGML-Dokument (xplan.gml) entspricht nicht dem GML-Schema.";
            result.addError( msg );
            LOG.info( "Unexpected failure by geometry validation ", e );
        }
        return result;
    }

    private void checkAndAddRules( GeometricFeatureInspector fi, ParserAndValidatorResult result ) {
        List<BadGeometry> errors = fi.checkGeometricRule();
        errors.stream().forEach( error -> {
            result.addErrors( error.getErrors() );
        } );
        result.addBadGeometries( errors );
    }

    private List<GeometricFeatureInspector> createInspectors( List<ValidationOption> voOptions ) {
        List<GeometricFeatureInspector> inspectors = new ArrayList<>();
        if ( !isSkipped( voOptions, SKIP_FLAECHENSCHLUSS_OPTION ) )
            inspectors.add( new FlaechenschlussInspector() );
        if ( !isSkipped( voOptions, SKIP_GELTUNGSBEREICH_OPTION ) )
            inspectors.add( new GeltungsbereichInspector() );
        return inspectors;
    }

    private GMLStreamReader createGmlStreamReader( XPlanArchive archive, ICRS crs, AppSchema schema,
                                                   XMLStreamReaderWrapper xmlStream, XPlanGeometryInspector inspector,
                                                   List<GeometricFeatureInspector> featureInspectors )
                            throws XMLStreamException {
        GMLVersion gmlVersion = archive.getVersion().getGmlVersion();
        GeometryFactory geomFac = new GeometryFactory();
        geomFac.addInspector( inspector );
        GMLStreamReader gmlStream = createGMLStreamReader( gmlVersion, xmlStream );
        gmlStream.setDefaultCRS( crs );
        gmlStream.setGeometryFactory( geomFac );
        gmlStream.setApplicationSchema( schema );
        gmlStream.setSkipBrokenGeometries( true );
        for ( GeometricFeatureInspector featureInspector : featureInspectors )
            gmlStream.addInspector( featureInspector );
        return gmlStream;
    }

    private void resolveAndValidateXlinks( GMLStreamReader gmlStream, ParserAndValidatorResult result ) {
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
                        result.addError( msg );
                    }
        } catch ( ReferenceResolvingException e ) {
            LOG.trace( "Resolving XLinks failed!", e );
            String errorMessage = format( "Die XLink-Integrität konnte nicht sichergestellt werden: %s",
                                          e.getMessage() );
            LOG.info( errorMessage );
            result.addError( errorMessage );
        }
        result.elapsed = System.currentTimeMillis() - begin;
    }

    private boolean isSkipped( List<ValidationOption> voOptions, String optionName ) {
        if ( voOptions == null )
            return false;
        for ( ValidationOption voOption : voOptions ) {
            if ( optionName.equals( voOption.getName() ) ) {
                if ( voOption.getArgument() != null )
                    return Boolean.valueOf( voOption.getArgument() );
            }
        }
        return false;
    }

    private void logResult( boolean force, ParserAndValidatorResult result ) {
        if ( result.isValid() )
            logSuccessMessages( result );
        else
            logErrorMessages( force, result );
    }

    private void logSuccessMessages( ParserAndValidatorResult result ) {
        LOG.info( "OK [{} ms]: {} Features", result.elapsed, result.xPlanFeatures.size() );
        if ( !result.warnings.isEmpty() ) {
            LOG.info( "Geometrie-Warnungen: {}", result.warnings.size() );
            for ( String warning : result.warnings )
                LOG.info( " - {}", warning );
        }
    }

    private void logErrorMessages( boolean force, ParserAndValidatorResult result ) {
        if ( !result.warnings.isEmpty() ) {
            LOG.info( "Geometrie-Warnungen: {}", result.warnings.size() );
            for ( String warning : result.warnings )
                LOG.info( " - {}", warning );
        }
        LOG.info( "Geometrie-Fehler: {}", result.errors.size() );
        for ( String error : result.errors )
            LOG.info( " - {}", error );
        if ( !force ) {
            LOG.info( "{} Geometrie-Fehler, {} Geometrie-Warnung(en). Hinweis: Sie k\u00f6nnen das "
                      + "Importieren des Plans mit der Kommandozeilen-Option --force erzwingen.", result.errors.size(),
                      result.warnings.size() );
            throw new IllegalArgumentException(
                                    "Der Plan kann aufgrund von " + result.errors.size() + " Geometrie-Fehler(n) und "
                                    + result.warnings.size() + " Geometrie-Warnung(en) nicht verarbeitet werden." );
        } else
            LOG.info( "Fortsetzung trotz Geometrie-Fehlern (--force)." );
    }

    private List<String> extendMessagesOfBrokenGeometryErrors( GMLStreamReader gmlStream ) {
        ArrayList<String> extendedBrokenGeometryErrors = new ArrayList<>();
        List<String> brokenGeometryErrors = gmlStream.getSkippedBrokenGeometryErrors();
        for ( String brokenGeometryError : brokenGeometryErrors ) {
            extendedBrokenGeometryErrors.add( brokenGeometryError + " - Achtung: Die Geometrie ist so stark "
                                              + "besch\u00e4digt, dass sie nicht f\u00fcr die Shapefile- und Bildgenerierung verwendet "
                                              + "werden kann." );
        }
        return extendedBrokenGeometryErrors;
    }

    private class ParserAndValidatorResult {

        private List<String> errors = new ArrayList<>();

        private List<String> warnings = new ArrayList<>();

        private List<BadGeometry> badGeometries = new ArrayList<>();

        private long elapsed;

        private FeatureCollection xPlanFeatures;

        private void addError( String errorToAdd ) {
            this.errors.add( errorToAdd );
        }

        private void addErrors( List<String> errorsToAdd ) {
            this.errors.addAll( errorsToAdd );
        }

        private void addWarnings( List<String> warningsToAdd ) {
            this.warnings.addAll( warningsToAdd );
        }

        private void addBadGeometries( List<BadGeometry> badGeometries ) {
            this.badGeometries.addAll( badGeometries );
        }

        private void setXplanFeatures( FeatureCollection xPlanFeatures ) {
            this.xPlanFeatures = xPlanFeatures;
        }

        public boolean isValid() {
            return errors.isEmpty();
        }
    }

}