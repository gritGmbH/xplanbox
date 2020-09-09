package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

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

    private @Valid List<VersionEnum> supportedXPlanGmlVersions = new ArrayList<VersionEnum>();

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

    @Schema(example = "v3.4.0", description = "Version der xPlanBox")
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

    @Schema(description = "")
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
    public ManagerSystemConfig supportedXPlanGmlVersions( List<VersionEnum> supportedXPlanGmlVersions ) {
        this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
        return this;
    }

    @Schema(description = "")
    @JsonProperty("supportedXPlanGmlVersions")
    public List<VersionEnum> getSupportedXPlanGmlVersions() {
        return supportedXPlanGmlVersions;
    }

    public void setSupportedXPlanGmlVersions( List<VersionEnum> supportedXPlanGmlVersions ) {
        this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
    }

    /**
     * Konfiguriertes CRS für die Rasterdatenhaltung
     **/
    public ManagerSystemConfig rasterCrs( String rasterCrs ) {
        this.rasterCrs = rasterCrs;
        return this;
    }

    @Schema(example = "epsg:28352", description = "Konfiguriertes CRS für die Rasterdatenhaltung")
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

    @Schema(example = "gdal", description = "Typ der Rasterdatenhaltung: gdal oder tiff")
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

    @Schema(description = "Semantische Validierung bei Import ueberspringen")
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

    @Schema(description = "Geometrische Validierung bei Import ueberspringen")
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

    @Schema(description = "Ueberpruefung des Flaechenschluss bei Import ueberspringen")
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

    @Schema(description = "Ueberpruefung des Geltungsbereich bei Import ueberspringen")
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

