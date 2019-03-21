package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.cli.Operation;
import de.latlon.xplan.commons.cli.SynchronizationException;
import de.latlon.xplan.commons.cli.Synchronizer;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.util.XPlanFeatureCollectionUtils;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.transform.cli.result.FileTransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformingValidationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Connection;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformationSynchronizer implements Synchronizer {

    private static final Logger LOG = LoggerFactory.getLogger( TransformationSynchronizer.class );

    private final XPlanDao xPlanDao;

    private final TransformingValidator transformingValidator;

    private Path outDir;

    public TransformationSynchronizer( XPlanDao xPlanDao, TransformingValidator transformingValidator, Path outDir ) {
        this.xPlanDao = xPlanDao;
        this.transformingValidator = transformingValidator;
        this.outDir = outDir;
    }

    @Override
    public void synchronize( Connection conn, int xPlanManagerId, String planVersion, String oldPlanStatus,
                             String newPlanStatus, Operation operation )
                    throws SynchronizationException {
        if ( !XPLAN_41.equals( XPlanVersion.valueOf( planVersion ) ) ) {
            return;
        }
        LOG.info( "Synchronize plan with id {}, operation {}", xPlanManagerId, operation );
        switch ( operation ) {
        case INSERT:
            insert( xPlanManagerId, newPlanStatus );
            break;
        case UPDATE:
            delete( xPlanManagerId, oldPlanStatus );
            insert( xPlanManagerId, newPlanStatus );
            break;
        case DELETE:
            delete( xPlanManagerId, oldPlanStatus );
            break;
        default:
            LOG.warn( "Unsupported operation: {}", operation );
        }
    }

    private void insert( int xPlanManagerId, String newPlanStatus )
                    throws SynchronizationException {
        try {
            XPlan xPlan = xPlanDao.getXPlanById( xPlanManagerId );

            TransformationResultWriter resultTransformationWriter = new FileTransformationResultWriter( outDir );
            TransformingValidationResult validationResult = transformingValidator.validate( xPlan,
                                                                                            resultTransformationWriter );
            if ( validationResult != null ) {
                String id = xPlan.getId();
                SyntacticValidatorResult validatorResult = validationResult.getValidatorResult();
                if ( validatorResult.isValid() ) {
                    LOG.info( "Plan with id {} is valid.", id );
                    XPlanType type = XPlanType.valueOf( xPlan.getType() );
                    PlanStatus planStatus = PlanStatus.findByMessage( newPlanStatus );
                    XPlanAde ade = xPlan.getAde() != null ? XPlanAde.valueOf( xPlan.getAde() ) : null;
                    XPlanFeatureCollection transformedXPlanFc = createXPlanFeatureCollection(
                                    validationResult.getTransformationResult(), type, ade );
                    xPlanDao.insertXPlanFeatureCollection( transformedXPlanFc, planStatus );
                } else {
                    LOG.warn( "Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML: {}",
                              id, validatorResult );
                    throw new SynchronizationException(
                                    "Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML" );
                }
            }
        } catch ( SynchronizationException e ) {
            throw e;
        } catch ( Exception e ) {
            throw new SynchronizationException( e );
        }
    }

    private void delete( int xPlanManagerId, String oldPlanStatus )
                    throws SynchronizationException {
        try {
            XPlan xPlan = xPlanDao.getXPlanById( xPlanManagerId );
            XPlanAde ade = xPlan.getAde() != null ? XPlanAde.valueOf( xPlan.getAde() ) : null;
            PlanStatus planStatus = PlanStatus.findByMessage( oldPlanStatus );
            xPlanDao.deleteXPlanFeatureCollection( xPlanManagerId, XPlanVersion.XPLAN_51, ade, planStatus );
        } catch ( Exception e ) {
            throw new SynchronizationException( e );
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

}