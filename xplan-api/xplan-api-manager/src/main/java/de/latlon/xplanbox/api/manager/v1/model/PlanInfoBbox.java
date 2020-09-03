package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanInfoBbox {

    private @Valid Double minX;

    private @Valid Double minY;

    private @Valid Double maxX;

    private @Valid Double maxY;

    private @Valid String crs;

    /**
     *
     **/
    public PlanInfoBbox minX( Double minX ) {
        this.minX = minX;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("minX")
    public Double getMinX() {
        return minX;
    }

    public void setMinX( Double minX ) {
        this.minX = minX;
    }

    /**
     *
     **/
    public PlanInfoBbox minY( Double minY ) {
        this.minY = minY;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("minY")
    public Double getMinY() {
        return minY;
    }

    public void setMinY( Double minY ) {
        this.minY = minY;
    }

    /**
     *
     **/
    public PlanInfoBbox maxX( Double maxX ) {
        this.maxX = maxX;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("maxX")
    public Double getMaxX() {
        return maxX;
    }

    public void setMaxX( Double maxX ) {
        this.maxX = maxX;
    }

    /**
     *
     **/
    public PlanInfoBbox maxY( Double maxY ) {
        this.maxY = maxY;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("maxY")
    public Double getMaxY() {
        return maxY;
    }

    public void setMaxY( Double maxY ) {
        this.maxY = maxY;
    }

    /**
     *
     **/
    public PlanInfoBbox crs( String crs ) {
        this.crs = crs;
        return this;
    }

    @ApiModelProperty(example = "epsg:4326", value = "")
    @JsonProperty("crs")
    public String getCrs() {
        return crs;
    }

    public void setCrs( String crs ) {
        this.crs = crs;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PlanInfoBbox planInfoBbox = (PlanInfoBbox) o;
        return Objects.equals( this.minX, planInfoBbox.minX ) && Objects.equals( this.minY, planInfoBbox.minY )
               && Objects.equals( this.maxX, planInfoBbox.maxX ) && Objects.equals( this.maxY, planInfoBbox.maxY )
               && Objects.equals( this.crs, planInfoBbox.crs );
    }

    @Override
    public int hashCode() {
        return Objects.hash( minX, minY, maxX, maxY, crs );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class PlanInfoBbox {\n" );

        sb.append( "    minX: " ).append( toIndentedString( minX ) ).append( "\n" );
        sb.append( "    minY: " ).append( toIndentedString( minY ) ).append( "\n" );
        sb.append( "    maxX: " ).append( toIndentedString( maxX ) ).append( "\n" );
        sb.append( "    maxY: " ).append( toIndentedString( maxY ) ).append( "\n" );
        sb.append( "    crs: " ).append( toIndentedString( crs ) ).append( "\n" );
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

