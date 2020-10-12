package de.latlon.xplan.validator.report;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

import java.util.Date;

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

    private ExternalReferenceReport externalReferenceReport;

    private String validationName;

    private String planName;

    private Date date;

    private XPlanVersion xPlanVersion;

    public ValidatorReport() {
    }

    private boolean hasMultipleXPlanElements;

    /**
     * @return result of geometric validator, maybe <code>null</code>.
     */
    public GeometricValidatorResult getGeometricValidatorResult() {
        return geometricValidatorResult;
    }

    /**
     * sets result of geometric validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setGeometricValidatorResult( GeometricValidatorResult result ) {
        geometricValidatorResult = result;
    }

    /**
     * @return result of syntactic validator, maybe <code>null</code>.
     */
    public SyntacticValidatorResult getSyntacticValidatorResult() {
        return syntacticValidatorResult;
    }

    /**
     * sets result of syntactic validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setSyntacticValidatorResult( SyntacticValidatorResult result ) {
        syntacticValidatorResult = result;
    }

    /**
     * @return result of semantic validator, maybe <code>null</code>.
     */
    public SemanticValidatorResult getSemanticValidatorResult() {
        return semanticValidatorResult;
    }

    /**
     * sets result of semantic validator.
     * @param result to set, maybe <code>null</code>.
     */
    public void setSemanticValidatorResult( SemanticValidatorResult result ) {
        semanticValidatorResult = result;
    }

    /**
     * @return ExternalReferenceReport, maybe <code>null</code>.
     */
    public ExternalReferenceReport getExternalReferenceReport() {
        return this.externalReferenceReport;
    }

    /**
     * sets ExternalReferenceReport.
     * @param externalReferenceReport to set, maybe <code>null</code>.
     */
    public void setExternalReferenceReport( ExternalReferenceReport externalReferenceReport ) {
        this.externalReferenceReport = externalReferenceReport;
    }

    /**
     * @param validationName name of the validation run
     */
    public void setValidationName( String validationName ) {
        this.validationName = validationName;
    }

    /**
     * @return name of the validation run
     */
    public String getValidationName() {
        return validationName;
    }

    /**
     * @param planName of teh archive
     */
    public void setPlanName( String planName ) {
        this.planName = planName;
    }

    /**
     * @return name of teh archive
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * @param date date of the validation run
     */
    public void setDate( Date date ) {
        this.date = date;
    }

    /**
     * @return date of the validation run
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the version of the validated XPlanGML, may be <code>null</code> if not known
     */
    public XPlanVersion getXPlanVersion() {
        return xPlanVersion;
    }

    /**
     * @param xPlanVersion the version of the validated XPlanGML, may be <code>null</code> if not known
     */
    public void setXPlanVersion( XPlanVersion xPlanVersion ) {
        this.xPlanVersion = xPlanVersion;
    }
    /**
     * @param hasMultipleXPlanElements
     *                         <code>true</code> if the XPLanArchive contains multiple XPlanElements, <code>false</code> otherwise
     */
    public void setHasMultipleXPlanElements( boolean hasMultipleXPlanElements ) {
        this.hasMultipleXPlanElements = hasMultipleXPlanElements;
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

    /**
     * @return <code>true</code> if the XPLanArchive contains multiple XPlanElements, <code>false</code> otherwise
     */
    public boolean hasMultipleXPlanElements() {
        return this.hasMultipleXPlanElements;
    }

}
