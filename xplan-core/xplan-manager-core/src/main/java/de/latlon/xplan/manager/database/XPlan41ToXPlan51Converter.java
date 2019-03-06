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
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlan41ToXPlan51Converter {

    private static final Logger LOG = LoggerFactory.getLogger( XPlan41ToXPlan51Converter.class );

    private final XPlanDao xPlanDao;

    private final XPlanGmlTransformer xPlanGmlTransformer;

    public XPlan41ToXPlan51Converter( XPlanDao xPlanDao, XPlanGmlTransformer xPlanGmlTransformer ) {
        this.xPlanDao = xPlanDao;
        this.xPlanGmlTransformer = xPlanGmlTransformer;
    }

    public void convertXPlan41ToXPlan51()
                    throws Exception {
        List<XPlan> plans = xPlanDao.getXPlanList( false );
        for ( XPlan plan : plans ) {
            XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
            if ( XPLAN_41.equals( version ) ) {
                convert( plan, version );
            }
        }
    }

    private void convert( XPlan plan, XPlanVersion version ) {
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
                ValidatorResult validatorResult = validateSyntactically( transformationResult, ade );
                if ( validatorResult.isValid() ) {
                    XPlanFeatureCollection transformedXPlanFc = createXPlanFeatureCollection( transformationResult,
                                                                                              type, ade );
                    xPlanDao.updateXPlanFeatureCollection( id, version, ade, planStatus, transformedXPlanFc );
                } else {
                    LOG.warn( "Transformation of the XPlanGML 4.1 plan with id {} to XPlanGml 5.1 results in syntactically invalid GML: {}",
                              id, validatorResult );
                }
            }
        } catch ( Exception e ) {
            LOG.warn( "Plan with id {} could not be converted: {}", e.getMessage() );
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

    private ValidatorResult validateSyntactically( TransformationResult transformationResult, XPlanAde ade )
                    throws IOException {
        byte[] transformedPlan = transformationResult.getTransformationResult();
        try ( InputStream is = new ByteArrayInputStream( transformedPlan ) ) {
            XPlanVersion version = transformationResult.getVersionOfTheResult();
            return new SyntacticValidatorImpl().validateSyntax( is, version, ade );
        }
    }

}