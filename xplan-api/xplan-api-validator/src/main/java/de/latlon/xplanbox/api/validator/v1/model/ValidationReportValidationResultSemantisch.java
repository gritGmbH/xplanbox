package de.latlon.xplanbox.api.validator.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
public class ValidationReportValidationResultSemantisch {

    private @Valid Boolean valid;

    private @Valid List<ValidationReportValidationResultSemantischRules> rules = new ArrayList<ValidationReportValidationResultSemantischRules>();

    /**
     *
     **/
    public ValidationReportValidationResultSemantisch valid( Boolean valid ) {
        this.valid = valid;
        return this;
    }

    @ApiModelProperty(example = "false", value = "")
    @JsonProperty("valid")
    public Boolean getValid() {
        return valid;
    }

    public void setValid( Boolean valid ) {
        this.valid = valid;
    }

    /**
     *
     **/
    public ValidationReportValidationResultSemantisch rules(
                            List<ValidationReportValidationResultSemantischRules> rules ) {
        this.rules = rules;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("rules")
    public List<ValidationReportValidationResultSemantischRules> getRules() {
        return rules;
    }

    public void setRules( List<ValidationReportValidationResultSemantischRules> rules ) {
        this.rules = rules;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ValidationReportValidationResultSemantisch validationReportValidationResultSemantisch = (ValidationReportValidationResultSemantisch) o;
        return Objects.equals( this.valid, validationReportValidationResultSemantisch.valid ) && Objects.equals(
                                this.rules, validationReportValidationResultSemantisch.rules );
    }

    @Override
    public int hashCode() {
        return Objects.hash( valid, rules );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ValidationReportValidationResultSemantisch {\n" );

        sb.append( "    valid: " ).append( toIndentedString( valid ) ).append( "\n" );
        sb.append( "    rules: " ).append( toIndentedString( rules ) ).append( "\n" );
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

