/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.report;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.deegree.geometry.Envelope;

import java.util.Date;

/**
 * A validation report contains all ValidationResults of one Validation
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
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

    private Envelope bboxIn4326;

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
     * @param bboxIn4326 bbox of the plan in EPSG:4326, may be <code>null</code>
     */
    public void setBBoxIn4326( Envelope bboxIn4326 ) {
        this.bboxIn4326 = bboxIn4326;
    }

    /**
     * @return the bbox of the plan in EPSG:4326, may be <code>null</code> if unknown
     */
    public Envelope getBBoxIn4326() {
        return this.bboxIn4326;
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
