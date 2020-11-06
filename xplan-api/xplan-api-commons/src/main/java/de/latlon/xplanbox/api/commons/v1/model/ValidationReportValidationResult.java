package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04\n"
                                                                                                            + "@XmlRootElement\n"
                                                                                                            + "@XmlAccessorType(XmlAccessType.FIELD).497+02:00[Europe/Berlin]")
public class ValidationReportValidationResult {

    private @Valid ValidationReportValidationResultSemantisch semantisch;

    private @Valid ValidationReportValidationResultGeometrisch geometrisch;

    private @Valid ValidationReportValidationResultSyntaktisch syntaktisch;

    /**
     *
     **/
    public ValidationReportValidationResult semantisch( ValidationReportValidationResultSemantisch semantisch ) {
        this.semantisch = semantisch;
        return this;
    }

    @Schema
    @JsonProperty("semantisch")
    public ValidationReportValidationResultSemantisch getSemantisch() {
        return semantisch;
    }

    public void setSemantisch( ValidationReportValidationResultSemantisch semantisch ) {
        this.semantisch = semantisch;
    }

    /**
     *
     **/
    public ValidationReportValidationResult geometrisch( ValidationReportValidationResultGeometrisch geometrisch ) {
        this.geometrisch = geometrisch;
        return this;
    }

    @Schema
    @JsonProperty("geometrisch")
    public ValidationReportValidationResultGeometrisch getGeometrisch() {
        return geometrisch;
    }

    public void setGeometrisch( ValidationReportValidationResultGeometrisch geometrisch ) {
        this.geometrisch = geometrisch;
    }

    /**
     *
     **/
    public ValidationReportValidationResult syntaktisch( ValidationReportValidationResultSyntaktisch syntaktisch ) {
        this.syntaktisch = syntaktisch;
        return this;
    }

    @Schema
    @JsonProperty("syntaktisch")
    public ValidationReportValidationResultSyntaktisch getSyntaktisch() {
        return syntaktisch;
    }

    public void setSyntaktisch( ValidationReportValidationResultSyntaktisch syntaktisch ) {
        this.syntaktisch = syntaktisch;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ValidationReportValidationResult validationReportValidationResult = (ValidationReportValidationResult) o;
        return Objects.equals( this.semantisch, validationReportValidationResult.semantisch ) && Objects.equals(
                                this.geometrisch, validationReportValidationResult.geometrisch ) && Objects.equals(
                                this.syntaktisch, validationReportValidationResult.syntaktisch );
    }

    @Override
    public int hashCode() {
        return Objects.hash( semantisch, geometrisch, syntaktisch );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ValidationReportValidationResult {\n" );

        sb.append( "    semantisch: " ).append( toIndentedString( semantisch ) ).append( "\n" );
        sb.append( "    geometrisch: " ).append( toIndentedString( geometrisch ) ).append( "\n" );
        sb.append( "    syntaktisch: " ).append( toIndentedString( syntaktisch ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( java.lang.Object o ) {
        if ( o == null ) {
            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}

