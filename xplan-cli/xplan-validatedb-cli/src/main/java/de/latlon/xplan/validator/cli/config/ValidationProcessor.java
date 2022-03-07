package de.latlon.xplan.validator.cli.config;

import de.latlon.xplan.validator.cli.domain.ValidationResultSummary;
import de.latlon.xplan.validator.cli.domain.XPlanWithFeatureCollection;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import org.springframework.batch.item.ItemProcessor;

import static java.util.Collections.EMPTY_LIST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidationProcessor implements ItemProcessor<XPlanWithFeatureCollection, ValidationResultSummary> {

    private SemanticValidator validator;

    public ValidationProcessor( SemanticValidator validator ) {
        this.validator = validator;
    }

    @Override
    public ValidationResultSummary process( XPlanWithFeatureCollection xPlanWithFeatureCollection ) {
        try {
            System.out.println( "Validate xplan with id " + xPlanWithFeatureCollection.getId() );
            ValidatorResult validatorReport = validator.validateSemantic( xPlanWithFeatureCollection, EMPTY_LIST );
            return new ValidationResultSummary( xPlanWithFeatureCollection.getId(),
                                                xPlanWithFeatureCollection.getXp_version(),
                                                xPlanWithFeatureCollection.getName(),
                                                xPlanWithFeatureCollection.getDistrict(), validatorReport );
        } catch ( Exception e ) {
            e.printStackTrace();
            throw e;
        }
    }

}
