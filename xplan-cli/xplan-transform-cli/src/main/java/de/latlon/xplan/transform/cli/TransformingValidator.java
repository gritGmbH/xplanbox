package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.cli.SynchronizationException;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.transform.cli.result.TransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformingValidationResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformingValidator {

    private static final Logger LOG = LoggerFactory.getLogger( TransformingValidator.class );

    private final XPlanDao xPlanDao;

    private final XPlanGmlTransformer xPlanGmlTransformer;

    public TransformingValidator( XPlanDao xPlanDao, XPlanGmlTransformer xPlanGmlTransformer ) {
        this.xPlanDao = xPlanDao;
        this.xPlanGmlTransformer = xPlanGmlTransformer;
    }

    public TransformingValidationResult validate( XPlan plan, TransformationResultWriter transformationResultWriter )
                    throws SynchronizationException {
        String id = plan.getId();
        LOG.info( "Transform plan with id {}", id );
        XPlanType type = XPlanType.valueOf( plan.getType() );
        XPlanAde ade = plan.getAde() != null ? XPlanAde.valueOf( plan.getAde() ) : null;
        try {
            FeatureCollection features = xPlanDao.retrieveFeatureCollection( plan );
            if ( features.isEmpty() ) {
                throw new SynchronizationException( "FeatureCollection retrieved from database is empty" );
            }
            XPlanFeatureCollection xPlanFeatureCollection = new XPlanFeatureCollectionBuilder( features, type ).build();

            TransformationResult transformationResult = xPlanGmlTransformer.transform( xPlanFeatureCollection );
            if ( transformationResult != null ) {
                SyntacticValidatorResult validatorResult = validateSyntactically( transformationResult, ade );
                if ( validatorResult.isValid() ) {
                    LOG.info( "Plan with id {} is valid.", id );
                } else {
                    LOG.warn( "Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML: {}",
                              id, validatorResult );
                }
                transformationResultWriter.writeResult( id, plan.getName(), validatorResult, transformationResult );
                return new TransformingValidationResult( plan, transformationResult, validatorResult );
            }
        } catch ( SynchronizationException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new SynchronizationException( "Plan with id " + id + " could not be converted", e );
        }
        return null;
    }

    private SyntacticValidatorResult validateSyntactically( TransformationResult transformationResult, XPlanAde ade )
                    throws IOException {
        byte[] transformedPlan = transformationResult.getTransformationResult();
        try ( InputStream is = new ByteArrayInputStream( transformedPlan ) ) {
            XPlanVersion version = transformationResult.getVersionOfTheResult();
            return (SyntacticValidatorResult) new SyntacticValidatorImpl().validateSyntax( is, version, ade );
        }
    }

}