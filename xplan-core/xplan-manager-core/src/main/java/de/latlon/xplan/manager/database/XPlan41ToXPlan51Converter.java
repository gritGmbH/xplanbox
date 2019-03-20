package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static java.nio.file.Files.newOutputStream;
import static org.apache.commons.io.IOUtils.write;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlan41ToXPlan51Converter {

    private static final Logger LOG = LoggerFactory.getLogger( XPlan41ToXPlan51Converter.class );

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd-HH-mm" );

    private final XPlanDao xPlanDao;

    private final XPlanGmlTransformer xPlanGmlTransformer;

    public XPlan41ToXPlan51Converter( XPlanDao xPlanDao, XPlanGmlTransformer xPlanGmlTransformer ) {
        this.xPlanDao = xPlanDao;
        this.xPlanGmlTransformer = xPlanGmlTransformer;
    }

    /**
     * Transforms all available plans, validates each of them and writes a validation report. If a plan is valid the plan will be moved to the XPlan 5.1 datastore (and removed from 4.1).
     *
     * @param outDir
     *                 the directory to store the validation reports (must exist)
     * @throws Exception
     */
    public void convertXPlan41ToXPlan51( Path outDir )
                    throws Exception {
        processAll( outDir, ( i, v, a, p, t ) -> {
            try {
                xPlanDao.updateXPlanFeatureCollection( i, v, a, p, t );
            } catch ( Exception e ) {
                LOG.warn( "Plan with id {} could not be updated: {}", e.getMessage() );
            }
        } );
    }

    /**
     * Transforms all available plans, validates each of them and writes a validation report.
     *
     * @param outDir
     *                 the directory to store the validation reports (must exist)
     * @throws Exception
     */
    public void validate( Path outDir )
                    throws Exception {
        processAll( outDir, ( i, v, a, p, t ) -> {
        } );
    }

    private void processAll( Path outDir,
                             FiveConsumer<String, XPlanVersion, XPlanAde, PlanStatus, XPlanFeatureCollection> consumeValid )
                    throws Exception {
        String fileName = "validationResults_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".csv";
        try ( BufferedWriter writer = Files.newBufferedWriter( outDir.resolve( fileName ) ) ) {
            CSVPrinter resultWriter = new CSVPrinter( writer, CSVFormat.DEFAULT.withHeader( "ID", "Name", "IsValid",
                                                                                            "RefTranformedGML",
                                                                                            "ValidationResult" ) );
            List<XPlan> plans = xPlanDao.getXPlanList( false );
            for ( XPlan plan : plans ) {
                XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
                if ( XPLAN_41.equals( version ) ) {
                    validate( plan, version, resultWriter, outDir, consumeValid );
                }
            }
        }
    }

    private void validate( XPlan plan, XPlanVersion version, CSVPrinter resultWriter, Path outDir,
                           FiveConsumer<String, XPlanVersion, XPlanAde, PlanStatus, XPlanFeatureCollection> consumeValid ) {
        String id = plan.getId();
        LOG.info( "Convert plan with id {}", id );
        PlanStatus planStatus = plan.getXplanMetadata().getPlanStatus();
        XPlanType type = XPlanType.valueOf( plan.getType() );
        XPlanAde ade = plan.getAde() != null ? XPlanAde.valueOf( plan.getAde() ) : null;
        try {
            FeatureCollection features = xPlanDao.retrieveFeatureCollection( plan );
            XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder( features, type ).build();

            TransformationResult transformationResult = xPlanGmlTransformer.transform( xPlanFeatureCollection );
            if ( transformationResult != null ) {
                SyntacticValidatorResult validatorResult = validateSyntactically( transformationResult, ade );
                if ( validatorResult.isValid() ) {
                    LOG.info( "Plan with id {} is valid.", id );
                    XPlanFeatureCollection transformedXPlanFc = createXPlanFeatureCollection( transformationResult,
                                                                                              type, ade );
                    consumeValid.accept( id, version, ade, planStatus, transformedXPlanFc );
                } else {
                    LOG.warn( "Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML: {}",
                              id, validatorResult );
                }
                writeValidationResult( resultWriter, outDir, id, plan.getName(), validatorResult,
                                       transformationResult );
            }
        } catch ( Exception e ) {
            LOG.warn( "Plan with id {} could not be converted: {}", e.getMessage() );
        }
    }

    private void writeValidationResult( CSVPrinter resultWriter, Path outDir, String id, String name,
                                        SyntacticValidatorResult validatorResult,
                                        TransformationResult transformationResult ) {
        String fileName = id + "_transformedGml_" + dateTimeFormatter.format( LocalDateTime.now() ) + ".xml";
        Path gmlFile = outDir.resolve( fileName );
        try ( OutputStream gmlOutputStream = newOutputStream( gmlFile ) ) {
            write( transformationResult.getTransformationResult(), gmlOutputStream );
            String validationResult = validatorResult.getMessages().stream().collect( Collectors.joining( "," ) );
            resultWriter.printRecord( id, name, validatorResult.isValid(), gmlFile, validationResult );
        } catch ( IOException e ) {
            LOG.warn( "Could not write results to csv file" );
        }

    }

    private XPlanFeatureCollection createXPlanFeatureCollection( TransformationResult transformationResult,
                                                                 XPlanType type, XPlanAde ade )
                    throws Exception {
        byte[] resultAsBytes = transformationResult.getTransformationResult();
        XPlanVersion resultVersion = transformationResult.getVersionOfTheResult();
        try ( InputStream inputStream = new ByteArrayInputStream( resultAsBytes ) ) {
            AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema( resultVersion, ade );
            return XPlanFeatureCollectionUtils.parseXPlanFeatureCollection( inputStream, type, resultVersion,
                                                                            appSchema );
        }
    }

    private SyntacticValidatorResult validateSyntactically( TransformationResult transformationResult, XPlanAde ade )
                    throws IOException {
        byte[] transformedPlan = transformationResult.getTransformationResult();
        try ( InputStream is = new ByteArrayInputStream( transformedPlan ) ) {
            XPlanVersion version = transformationResult.getVersionOfTheResult();
            return (SyntacticValidatorResult) new SyntacticValidatorImpl().validateSyntax( is, version, ade );
        }
    }

    @FunctionalInterface
    public interface FiveConsumer<T, U, V, W, X> {
        void accept( T t, U u, V v, W w, X x );
    }

}