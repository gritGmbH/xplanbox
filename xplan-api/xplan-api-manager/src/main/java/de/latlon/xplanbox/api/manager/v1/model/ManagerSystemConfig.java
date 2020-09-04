package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ManagerSystemConfig {

    private @Valid String version;

    private @Valid RulesMetadata rulesMetadata;

    public enum SupportedXPlanGmlVersionsEnum {

        _3( String.valueOf( "XPLAN_3" ) ), _40( String.valueOf( "XPLAN_40" ) ), _41(
                                String.valueOf( "XPLAN_41" ) ), _50( String.valueOf( "XPLAN_50" ) ), _51(
                                String.valueOf( "XPLAN_51" ) ), _52( String.valueOf( "XPLAN_52" ) );

        private String value;

        SupportedXPlanGmlVersionsEnum( String v ) {
            value = v;
        }

        public String value() {
            return value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf( value );
        }

        @JsonCreator
        public static SupportedXPlanGmlVersionsEnum fromValue( String value ) {
            for ( SupportedXPlanGmlVersionsEnum b : SupportedXPlanGmlVersionsEnum.values() ) {
                if ( b.value.equals( value ) ) {
                    return b;
                }
            }
            throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
        }
    }

    private @Valid List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions = new ArrayList<SupportedXPlanGmlVersionsEnum>();

    private @Valid String rasterCrs;

    private @Valid String rasterType;

    private @Valid Boolean skipSemantisch = false;

    private @Valid Boolean skipGeometrisch = false;

    private @Valid Boolean skipFlaechenschluss = false;

    private @Valid Boolean skipGeltungsbereich = false;

    /**
     * Version der xPlanBox
     **/
    public ManagerSystemConfig version( String version ) {
        this.version = version;
        return this;
    }

    @ApiModelProperty(example = "v3.4.0", value = "Version der xPlanBox")
    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    /**
     *
     **/
    public ManagerSystemConfig rulesMetadata( RulesMetadata rulesMetadata ) {
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
    public ManagerSystemConfig supportedXPlanGmlVersions(
                            List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions ) {
        this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
        return this;
    }

    @ApiModelProperty(value = "")
    @JsonProperty("supportedXPlanGmlVersions")
    public List<SupportedXPlanGmlVersionsEnum> getSupportedXPlanGmlVersions() {
        return supportedXPlanGmlVersions;
    }

    public void setSupportedXPlanGmlVersions( List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions ) {
        this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
    }

    /**
     * Konfiguriertes CRS für die Rasterdatenhaltung
     **/
    public ManagerSystemConfig rasterCrs( String rasterCrs ) {
        this.rasterCrs = rasterCrs;
        return this;
    }

    @ApiModelProperty(example = "epsg:28352", value = "Konfiguriertes CRS für die Rasterdatenhaltung")
    @JsonProperty("rasterCrs")
    public String getRasterCrs() {
        return rasterCrs;
    }

    public void setRasterCrs( String rasterCrs ) {
        this.rasterCrs = rasterCrs;
    }

    /**
     * Typ der Rasterdatenhaltung: gdal oder tiff
     **/
    public ManagerSystemConfig rasterType( String rasterType ) {
        this.rasterType = rasterType;
        return this;
    }

    @ApiModelProperty(example = "gdal", value = "Typ der Rasterdatenhaltung: gdal oder tiff")
    @JsonProperty("rasterType")
    public String getRasterType() {
        return rasterType;
    }

    public void setRasterType( String rasterType ) {
        this.rasterType = rasterType;
    }

    /**
     * Semantische Validierung bei Import ueberspringen
     **/
    public ManagerSystemConfig skipSemantisch( Boolean skipSemantisch ) {
        this.skipSemantisch = skipSemantisch;
        return this;
    }

    @ApiModelProperty(value = "Semantische Validierung bei Import ueberspringen")
    @JsonProperty("skipSemantisch")
    public Boolean getSkipSemantisch() {
        return skipSemantisch;
    }

    public void setSkipSemantisch( Boolean skipSemantisch ) {
        this.skipSemantisch = skipSemantisch;
    }

    /**
     * Geometrische Validierung bei Import ueberspringen
     **/
    public ManagerSystemConfig skipGeometrisch( Boolean skipGeometrisch ) {
        this.skipGeometrisch = skipGeometrisch;
        return this;
    }

    @ApiModelProperty(value = "Geometrische Validierung bei Import ueberspringen")
    @JsonProperty("skipGeometrisch")
    public Boolean getSkipGeometrisch() {
        return skipGeometrisch;
    }

    public void setSkipGeometrisch( Boolean skipGeometrisch ) {
        this.skipGeometrisch = skipGeometrisch;
    }

    /**
     * Ueberpruefung des Flaechenschluss bei Import ueberspringen
     **/
    public ManagerSystemConfig skipFlaechenschluss( Boolean skipFlaechenschluss ) {
        this.skipFlaechenschluss = skipFlaechenschluss;
        return this;
    }

    @ApiModelProperty(value = "Ueberpruefung des Flaechenschluss bei Import ueberspringen")
    @JsonProperty("skipFlaechenschluss")
    public Boolean getSkipFlaechenschluss() {
        return skipFlaechenschluss;
    }

    public void setSkipFlaechenschluss( Boolean skipFlaechenschluss ) {
        this.skipFlaechenschluss = skipFlaechenschluss;
    }

    /**
     * Ueberpruefung des Geltungsbereich bei Import ueberspringen
     **/
    public ManagerSystemConfig skipGeltungsbereich( Boolean skipGeltungsbereich ) {
        this.skipGeltungsbereich = skipGeltungsbereich;
        return this;
    }

    @ApiModelProperty(value = "Ueberpruefung des Geltungsbereich bei Import ueberspringen")
    @JsonProperty("skipGeltungsbereich")
    public Boolean getSkipGeltungsbereich() {
        return skipGeltungsbereich;
    }

    public void setSkipGeltungsbereich( Boolean skipGeltungsbereich ) {
        this.skipGeltungsbereich = skipGeltungsbereich;
    }

    @Override
    public boolean equals( java.lang.Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        ManagerSystemConfig managerSystemConfig = (ManagerSystemConfig) o;
        return Objects.equals( this.version, managerSystemConfig.version ) && Objects.equals( this.rulesMetadata,
                                                                                              managerSystemConfig.rulesMetadata )
               && Objects.equals( this.supportedXPlanGmlVersions, managerSystemConfig.supportedXPlanGmlVersions )
               && Objects.equals( this.rasterCrs, managerSystemConfig.rasterCrs ) && Objects.equals( this.rasterType,
                                                                                                     managerSystemConfig.rasterType )
               && Objects.equals( this.skipSemantisch, managerSystemConfig.skipSemantisch ) && Objects.equals(
                                this.skipGeometrisch, managerSystemConfig.skipGeometrisch ) && Objects.equals(
                                this.skipFlaechenschluss, managerSystemConfig.skipFlaechenschluss ) && Objects.equals(
                                this.skipGeltungsbereich, managerSystemConfig.skipGeltungsbereich );
    }

    @Override
    public int hashCode() {
        return Objects.hash( version, rulesMetadata, supportedXPlanGmlVersions, rasterCrs, rasterType, skipSemantisch,
                             skipGeometrisch, skipFlaechenschluss, skipGeltungsbereich );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class ManagerSystemConfig {\n" );

        sb.append( "    version: " ).append( toIndentedString( version ) ).append( "\n" );
        sb.append( "    rulesMetadata: " ).append( toIndentedString( rulesMetadata ) ).append( "\n" );
        sb.append( "    supportedXPlanGmlVersions: " ).append( toIndentedString( supportedXPlanGmlVersions ) ).append(
                                "\n" );
        sb.append( "    rasterCrs: " ).append( toIndentedString( rasterCrs ) ).append( "\n" );
        sb.append( "    rasterType: " ).append( toIndentedString( rasterType ) ).append( "\n" );
        sb.append( "    skipSemantisch: " ).append( toIndentedString( skipSemantisch ) ).append( "\n" );
        sb.append( "    skipGeometrisch: " ).append( toIndentedString( skipGeometrisch ) ).append( "\n" );
        sb.append( "    skipFlaechenschluss: " ).append( toIndentedString( skipFlaechenschluss ) ).append( "\n" );
        sb.append( "    skipGeltungsbereich: " ).append( toIndentedString( skipGeltungsbereich ) ).append( "\n" );
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

