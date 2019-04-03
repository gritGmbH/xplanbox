package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.transform.cli.result.CsvTransformationResultWriter;
import de.latlon.xplan.transform.cli.result.TransformationResultWriter;

import java.nio.file.Path;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidateExecutor {

    private XPlanDao xPlanDao;

    private final TransformingValidator validator;

    public ValidateExecutor( XPlanDao xPlanDao, TransformingValidator validator ) {
        this.xPlanDao = xPlanDao;
        this.validator = validator;
    }

    /**
     * Transforms all available plans, validates each of them and writes a validation report.
     *
     * @param outDir
     *                 the directory to store the validation reports (must exist)
     * @throws Exception
     */
    public void validateAll( Path outDir )
                    throws Exception {
        try ( TransformationResultWriter transformationResultWriter = new CsvTransformationResultWriter( outDir ) ) {
            List<XPlan> plans = xPlanDao.getXPlanList( false );
            for ( XPlan plan : plans ) {
                XPlanVersion version = XPlanVersion.valueOf( plan.getVersion() );
                if ( XPLAN_41.equals( version ) ) {
                    validator.validate( plan, transformationResultWriter );
                }
            }
        }
    }

}
