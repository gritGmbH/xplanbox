package de.latlon.xplan.validator.report;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * A validation report contains all ValidationResults of one Validation
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class ValidatorReport {

    private GeometricValidatorResult geometricValidatorResult;

    private SyntacticValidatorResult syntacticValidatorResult;

    private SemanticValidatorResult semanticValidatorResult;

    public ValidatorReport() {
    }

    /**
     *
     * @return result of geometric validator, maybe <code>null</code>.
     */
    public GeometricValidatorResult getGeometricValidatorResult() {
        return geometricValidatorResult;
    }

    /**
     *
     * @return result of syntactic validator, maybe <code>null</code>.
     */
    public SyntacticValidatorResult getSyntacticValidatorResult() {
        return syntacticValidatorResult;
    }

    /**
     *
     * @return result of semantic validator, maybe <code>null</code>.
     */
    public SemanticValidatorResult getSemanticValidatorResult() {
        return semanticValidatorResult;
    }

    /**
     * sets result of geometric validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setGeometricValidatorResult( GeometricValidatorResult result ) {
        geometricValidatorResult = result;
    }

    /**
     * sets result of syntactic validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setSyntacticValidatorResult( SyntacticValidatorResult result ) {
        syntacticValidatorResult = result;
    }

    /**
     * sets result of semantic validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setSemanticValidatorResult( SemanticValidatorResult result ) {
        semanticValidatorResult = result;
    }

    /**
     * sets all validator results.
     * @param synResult syntactic result to set, maybe <code>null</code>,
     * @param geomResult geometric result to set, maybe <code>null</code>,
     * @param semResult semantic result to set, maybe <code>null</code>,
     */
    public void setValidatorResults( SyntacticValidatorResult synResult, GeometricValidatorResult geomResult,
                                SemanticValidatorResult semResult ) {
        this.syntacticValidatorResult = synResult;
        this.geometricValidatorResult = geomResult;
        this.semanticValidatorResult = semResult;
    }

    /**
     *
     * @return <code>true</code>, if all validator results are either <code>null</code> or valid.
     */
    public boolean isReportValid() {

        boolean finalResult = true;

        if ( geometricValidatorResult != null )
            finalResult = geometricValidatorResult.isValid();
        if ( semanticValidatorResult != null )
            finalResult = finalResult && semanticValidatorResult.isValid();
        if ( syntacticValidatorResult != null )
            finalResult = finalResult && syntacticValidatorResult.isValid();

        return finalResult;
    }

}
