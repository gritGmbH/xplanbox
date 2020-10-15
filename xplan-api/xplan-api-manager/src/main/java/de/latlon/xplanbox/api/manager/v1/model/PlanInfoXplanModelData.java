package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

/**
 * attributes derived from XPlanGML data model
 **/
@Schema(description = "attributes derived from XPlanGML data model")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PlanInfoXplanModelData {

    private @Valid String name;

    private @Valid String nummer;

    private @Valid String internalId;

    private @Valid String ags;

    private @Valid String gemeindeName;

    private @Valid String rechtsstand;

    private @Valid Date inkrafttretensDatum;

    /**
     *
     **/
    public PlanInfoXplanModelData name( String name ) {
        this.name = name;
        return this;
    }

    @Schema(example = "Othmarschen3")
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
    public PlanInfoXplanModelData nummer( String nummer ) {
        this.nummer = nummer;
        return this;
    }

    @Schema(example = "-")
    @JsonProperty("nummer")
    public String getNummer() {
        return nummer;
    }

    public void setNummer( String nummer ) {
        this.nummer = nummer;
    }

    /**
     *
     **/
    public PlanInfoXplanModelData internalId( String internalId ) {
        this.internalId = internalId;
        return this;
    }

    @Schema(example = "12341")
    @JsonProperty("internalId")
    public String getInternalId() {
        return internalId;
    }

    public void setInternalId( String internalId ) {
        this.internalId = internalId;
    }

    /**
     *
     **/
    public PlanInfoXplanModelData ags( String ags ) {
        this.ags = ags;
        return this;
    }

    @Schema(example = "02000000")
    @JsonProperty("ags")
    public String getAgs() {
        return ags;
    }

    public void setAgs( String ags ) {
        this.ags = ags;
    }

    /**
     *
     **/
    public PlanInfoXplanModelData gemeindeName( String gemeindeName ) {
        this.gemeindeName = gemeindeName;
        return this;
    }

    @Schema(example = "Gemeindename")
    @JsonProperty("gemeindeName")
    public String getGemeindeName() {
        return gemeindeName;
    }

    public void setGemeindeName( String gemeindeName ) {
        this.gemeindeName = gemeindeName;
    }

    /**
     * translation of code list value of xplan:rechtsstand
     **/
    public PlanInfoXplanModelData rechtsstand( String rechtsstand ) {
        this.rechtsstand = rechtsstand;
        return this;
    }

    @Schema(example = "Satzung", description = "translation of code list value of xplan:rechtsstand")
    @JsonProperty("rechtsstand")
    public String getRechtsstand() {
        return rechtsstand;
    }

    public void setRechtsstand( String rechtsstand ) {
        this.rechtsstand = rechtsstand;
    }

    /**
     *
     **/
    public PlanInfoXplanModelData inkrafttretensDatum( Date inkrafttretensDatum ) {
        this.inkrafttretensDatum = inkrafttretensDatum;
        return this;
    }

    @Schema
    @JsonProperty("inkrafttretensDatum")
    public Date getInkrafttretensDatum() {
        return inkrafttretensDatum;
    }

    public void setInkrafttretensDatum( Date inkrafttretensDatum ) {
        this.inkrafttretensDatum = inkrafttretensDatum;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PlanInfoXplanModelData planInfoXplanModelData = (PlanInfoXplanModelData) o;
        return Objects.equals( this.name, planInfoXplanModelData.name ) && Objects.equals( this.nummer,
                                                                                           planInfoXplanModelData.nummer )
               && Objects.equals( this.internalId, planInfoXplanModelData.internalId ) && Objects.equals( this.ags,
                                                                                                          planInfoXplanModelData.ags )
               && Objects.equals( this.gemeindeName, planInfoXplanModelData.gemeindeName ) && Objects.equals(
                                this.rechtsstand, planInfoXplanModelData.rechtsstand ) && Objects.equals(
                                this.inkrafttretensDatum, planInfoXplanModelData.inkrafttretensDatum );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, nummer, internalId, ags, gemeindeName, rechtsstand, inkrafttretensDatum );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class PlanInfoXplanModelData {\n" );

        sb.append( "    name: " ).append( toIndentedString( name ) ).append( "\n" );
        sb.append( "    nummer: " ).append( toIndentedString( nummer ) ).append( "\n" );
        sb.append( "    internalId: " ).append( toIndentedString( internalId ) ).append( "\n" );
        sb.append( "    ags: " ).append( toIndentedString( ags ) ).append( "\n" );
        sb.append( "    gemeindeName: " ).append( toIndentedString( gemeindeName ) ).append( "\n" );
        sb.append( "    rechtsstand: " ).append( toIndentedString( rechtsstand ) ).append( "\n" );
        sb.append( "    inkrafttretensDatum: " ).append( toIndentedString( inkrafttretensDatum ) ).append( "\n" );
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

