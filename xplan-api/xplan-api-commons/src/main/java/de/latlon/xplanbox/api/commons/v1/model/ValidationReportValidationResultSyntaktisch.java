package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-27T12:32:04.497+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ValidationReportValidationResultSyntaktisch {

    private @Valid Boolean valid;

    private @Valid List<String> messages = new ArrayList<String>();

    /**
     *
     **/
    public ValidationReportValidationResultSyntaktisch valid( Boolean valid ) {
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
    public ValidationReportValidationResultSyntaktisch messages( List<String> messages ) {
        this.messages = messages;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("messages")
    public List<String> getMessages() {
        return messages;
    }

    public void setMessages( List<String> messages ) {
        this.messages = messages;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ValidationReportValidationResultSyntaktisch validationReportValidationResultSyntaktisch = (ValidationReportValidationResultSyntaktisch) o;
        return Objects.equals( this.valid, validationReportValidationResultSyntaktisch.valid ) && Objects.equals(
                                this.messages, validationReportValidationResultSyntaktisch.messages );
    }

    @Override
    public int hashCode() {
        return Objects.hash( valid, messages );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ValidationReportValidationResultSyntaktisch {\n" );

        sb.append( "    valid: " ).append( toIndentedString( valid ) ).append( "\n" );
        sb.append( "    messages: " ).append( toIndentedString( messages ) ).append( "\n" );
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

