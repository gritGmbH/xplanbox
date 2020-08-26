package de.latlon.xplanbox.api.validator.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class ValidationReport {

    private @Valid String filename;

    private @Valid String name;

    private @Valid Date date;

    private @Valid Boolean valid;

    private @Valid List<String> externalReferences = new ArrayList<String>();

    private @Valid URI wmsUrl;

    private @Valid RulesMetadata rulesMetadata;

    private @Valid ValidationReportValidationResult validationResult;

    /**
     *
     **/
    public ValidationReport filename( String filename ) {
        this.filename = filename;
        return this;
    }

    @ApiModelProperty(example = "xplan52-test.gml", value = "")
    @JsonProperty("filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename( String filename ) {
        this.filename = filename;
    }

    /**
     *
     **/
    public ValidationReport name( String name ) {
        this.name = name;
        return this;
    }

    @ApiModelProperty(example = "xplan52-test", value = "")
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
    public ValidationReport date( Date date ) {
        this.date = date;
        return this;
    }

    @ApiModelProperty(example = "2020-08-24T15:06:36.662Z", value = "")
    @JsonProperty("date")
    public Date getDate() {
        return date;
    }

    public void setDate( Date date ) {
        this.date = date;
    }

    /**
     *
     **/
    public ValidationReport valid( Boolean valid ) {
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
    public ValidationReport externalReferences( List<String> externalReferences ) {
        this.externalReferences = externalReferences;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("externalReferences")
    public List<String> getExternalReferences() {
        return externalReferences;
    }

    public void setExternalReferences( List<String> externalReferences ) {
        this.externalReferences = externalReferences;
    }

    /**
     *
     **/
    public ValidationReport wmsUrl( URI wmsUrl ) {
        this.wmsUrl = wmsUrl;
        return this;
    }

    @ApiModelProperty(example = "https://xplanbox.lat-lon.de/xplan-validator-wms/services/wms?PLANWERK_MANAGERID=13", value = "")
    @JsonProperty("wmsUrl")
    public URI getWmsUrl() {
        return wmsUrl;
    }

    public void setWmsUrl( URI wmsUrl ) {
        this.wmsUrl = wmsUrl;
    }

    /**
     *
     **/
    public ValidationReport rulesMetadata( RulesMetadata rulesMetadata ) {
        this.rulesMetadata = rulesMetadata;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("rulesMetadata")
    public RulesMetadata getRulesMetadata() {
        return rulesMetadata;
    }

    public void setRulesMetadata( RulesMetadata rulesMetadata ) {
        this.rulesMetadata = rulesMetadata;
    }

    /**
     *
     **/
    public ValidationReport validationResult( ValidationReportValidationResult validationResult ) {
        this.validationResult = validationResult;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("validationResult")
    public ValidationReportValidationResult getValidationResult() {
        return validationResult;
    }

    public void setValidationResult( ValidationReportValidationResult validationResult ) {
        this.validationResult = validationResult;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ValidationReport validationReport = (ValidationReport) o;
        return Objects.equals( this.filename, validationReport.filename ) && Objects.equals( this.name,
                                                                                             validationReport.name )
               && Objects.equals( this.date, validationReport.date ) && Objects.equals( this.valid,
                                                                                        validationReport.valid )
               && Objects.equals( this.externalReferences, validationReport.externalReferences ) && Objects.equals(
                                this.wmsUrl, validationReport.wmsUrl ) && Objects.equals( this.rulesMetadata,
                                                                                          validationReport.rulesMetadata )
               && Objects.equals( this.validationResult, validationReport.validationResult );
    }

    @Override
    public int hashCode() {
        return Objects.hash( filename, name, date, valid, externalReferences, wmsUrl, rulesMetadata, validationResult );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ValidationReport {\n" );

        sb.append( "    filename: " ).append( toIndentedString( filename ) ).append( "\n" );
        sb.append( "    name: " ).append( toIndentedString( name ) ).append( "\n" );
        sb.append( "    date: " ).append( toIndentedString( date ) ).append( "\n" );
        sb.append( "    valid: " ).append( toIndentedString( valid ) ).append( "\n" );
        sb.append( "    externalReferences: " ).append( toIndentedString( externalReferences ) ).append( "\n" );
        sb.append( "    wmsUrl: " ).append( toIndentedString( wmsUrl ) ).append( "\n" );
        sb.append( "    rulesMetadata: " ).append( toIndentedString( rulesMetadata ) ).append( "\n" );
        sb.append( "    validationResult: " ).append( toIndentedString( validationResult ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {
        if ( o == null ) {
            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}

