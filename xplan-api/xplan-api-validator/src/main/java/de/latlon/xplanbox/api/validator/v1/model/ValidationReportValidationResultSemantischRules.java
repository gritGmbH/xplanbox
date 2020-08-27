package de.latlon.xplanbox.api.validator.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class ValidationReportValidationResultSemantischRules {

    private @Valid String name;

    private @Valid Boolean isValid;

    private @Valid String message;

    private @Valid List<String> invalidFeatures = new ArrayList<String>();

    /**
     *
     **/
    public ValidationReportValidationResultSemantischRules name( String name ) {
        this.name = name;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    /**
     *
     **/
    public ValidationReportValidationResultSemantischRules isValid( Boolean isValid ) {
        this.isValid = isValid;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("isValid")
    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid( Boolean isValid ) {
        this.isValid = isValid;
    }

    /**
     *
     **/
    public ValidationReportValidationResultSemantischRules message( String message ) {
        this.message = message;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    /**
     *
     **/
    public ValidationReportValidationResultSemantischRules invalidFeatures( List<String> invalidFeatures ) {
        this.invalidFeatures = invalidFeatures;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("invalidFeatures")
    public List<String> getInvalidFeatures() {
        return invalidFeatures;
    }

    public void setInvalidFeatures( List<String> invalidFeatures ) {
        this.invalidFeatures = invalidFeatures;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ValidationReportValidationResultSemantischRules validationReportValidationResultSemantischRules = (ValidationReportValidationResultSemantischRules) o;
        return Objects.equals( this.name, validationReportValidationResultSemantischRules.name ) && Objects.equals(
                                this.isValid, validationReportValidationResultSemantischRules.isValid )
               && Objects.equals( this.message, validationReportValidationResultSemantischRules.message )
               && Objects.equals( this.invalidFeatures,
                                  validationReportValidationResultSemantischRules.invalidFeatures );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, isValid, message, invalidFeatures );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ValidationReportValidationResultSemantischRules {\n" );

        sb.append( "    name: " ).append( toIndentedString( name ) ).append( "\n" );
        sb.append( "    isValid: " ).append( toIndentedString( isValid ) ).append( "\n" );
        sb.append( "    message: " ).append( toIndentedString( message ) ).append( "\n" );
        sb.append( "    invalidFeatures: " ).append( toIndentedString( invalidFeatures ) ).append( "\n" );
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

