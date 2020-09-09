package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.Objects;

/**
 * Status
 **/
@Schema(description = "Status")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Status {

    private @Valid Integer planId;

    private @Valid URI link;

    private @Valid ValidationReport validationReport;

    /**
     *
     **/
    public Status planId( Integer planId ) {
        this.planId = planId;
        return this;
    }

    @Schema(format = "int32", example = "123")
    @JsonProperty("planId")
    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId( Integer planId ) {
        this.planId = planId;
    }

    /**
     *
     **/
    public Status link( URI link ) {
        this.link = link;
        return this;
    }

    @Schema(example = "https://xplanbox.lat-lon.de/xmanager/api/v1/plan/123")
    @JsonProperty("link")
    public URI getLink() {
        return link;
    }

    public void setLink( URI link ) {
        this.link = link;
    }

    /**
     *
     **/
    public Status validationReport( ValidationReport validationReport ) {
        this.validationReport = validationReport;
        return this;
    }

    @Schema
    @JsonProperty("validationReport")
    public ValidationReport getValidationReport() {
        return validationReport;
    }

    public void setValidationReport( ValidationReport validationReport ) {
        this.validationReport = validationReport;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Status status = (Status) o;
        return Objects.equals( this.planId, status.planId ) && Objects.equals( this.link, status.link )
               && Objects.equals( this.validationReport, status.validationReport );
    }

    @Override
    public int hashCode() {
        return Objects.hash( planId, link, validationReport );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Status {\n" );

        sb.append( "    planId: " ).append( toIndentedString( planId ) ).append( "\n" );
        sb.append( "    link: " ).append( toIndentedString( link ) ).append( "\n" );
        sb.append( "    validationReport: " ).append( toIndentedString( validationReport ) ).append( "\n" );
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

