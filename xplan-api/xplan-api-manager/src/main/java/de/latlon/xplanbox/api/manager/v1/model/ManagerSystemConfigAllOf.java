package de.latlon.xplanbox.api.manager.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class ManagerSystemConfigAllOf   {
  
  private @Valid String rasterCrs;
  private @Valid String rasterType;
  private @Valid Boolean skipSemantisch = false;
  private @Valid Boolean skipGeometrisch = false;
  private @Valid Boolean skipFlaechenschluss = false;
  private @Valid Boolean skipGeltungsbereich = false;

  /**
   * Konfiguriertes CRS für die Rasterdatenhaltung
   **/
  public ManagerSystemConfigAllOf rasterCrs(String rasterCrs) {
    this.rasterCrs = rasterCrs;
    return this;
  }

  

  
  @ApiModelProperty(example = "epsg:28352", value = "Konfiguriertes CRS für die Rasterdatenhaltung")
  @JsonProperty("rasterCrs")
  public String getRasterCrs() {
    return rasterCrs;
  }

  public void setRasterCrs(String rasterCrs) {
    this.rasterCrs = rasterCrs;
  }/**
   * Typ der Rasterdatenhaltung: gdal oder tiff
   **/
  public ManagerSystemConfigAllOf rasterType(String rasterType) {
    this.rasterType = rasterType;
    return this;
  }

  

  
  @ApiModelProperty(example = "gdal", value = "Typ der Rasterdatenhaltung: gdal oder tiff")
  @JsonProperty("rasterType")
  public String getRasterType() {
    return rasterType;
  }

  public void setRasterType(String rasterType) {
    this.rasterType = rasterType;
  }/**
   * Semantische Validierung bei Import ueberspringen
   **/
  public ManagerSystemConfigAllOf skipSemantisch(Boolean skipSemantisch) {
    this.skipSemantisch = skipSemantisch;
    return this;
  }

  

  
  @ApiModelProperty(value = "Semantische Validierung bei Import ueberspringen")
  @JsonProperty("skipSemantisch")
  public Boolean getSkipSemantisch() {
    return skipSemantisch;
  }

  public void setSkipSemantisch(Boolean skipSemantisch) {
    this.skipSemantisch = skipSemantisch;
  }/**
   * Geometrische Validierung bei Import ueberspringen
   **/
  public ManagerSystemConfigAllOf skipGeometrisch(Boolean skipGeometrisch) {
    this.skipGeometrisch = skipGeometrisch;
    return this;
  }

  

  
  @ApiModelProperty(value = "Geometrische Validierung bei Import ueberspringen")
  @JsonProperty("skipGeometrisch")
  public Boolean getSkipGeometrisch() {
    return skipGeometrisch;
  }

  public void setSkipGeometrisch(Boolean skipGeometrisch) {
    this.skipGeometrisch = skipGeometrisch;
  }/**
   * Ueberpruefung des Flaechenschluss bei Import ueberspringen
   **/
  public ManagerSystemConfigAllOf skipFlaechenschluss(Boolean skipFlaechenschluss) {
    this.skipFlaechenschluss = skipFlaechenschluss;
    return this;
  }

  

  
  @ApiModelProperty(value = "Ueberpruefung des Flaechenschluss bei Import ueberspringen")
  @JsonProperty("skipFlaechenschluss")
  public Boolean getSkipFlaechenschluss() {
    return skipFlaechenschluss;
  }

  public void setSkipFlaechenschluss(Boolean skipFlaechenschluss) {
    this.skipFlaechenschluss = skipFlaechenschluss;
  }/**
   * Ueberpruefung des Geltungsbereich bei Import ueberspringen
   **/
  public ManagerSystemConfigAllOf skipGeltungsbereich(Boolean skipGeltungsbereich) {
    this.skipGeltungsbereich = skipGeltungsbereich;
    return this;
  }

  

  
  @ApiModelProperty(value = "Ueberpruefung des Geltungsbereich bei Import ueberspringen")
  @JsonProperty("skipGeltungsbereich")
  public Boolean getSkipGeltungsbereich() {
    return skipGeltungsbereich;
  }

  public void setSkipGeltungsbereich(Boolean skipGeltungsbereich) {
    this.skipGeltungsbereich = skipGeltungsbereich;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ManagerSystemConfigAllOf managerSystemConfigAllOf = (ManagerSystemConfigAllOf) o;
    return Objects.equals(this.rasterCrs, managerSystemConfigAllOf.rasterCrs) &&
        Objects.equals(this.rasterType, managerSystemConfigAllOf.rasterType) &&
        Objects.equals(this.skipSemantisch, managerSystemConfigAllOf.skipSemantisch) &&
        Objects.equals(this.skipGeometrisch, managerSystemConfigAllOf.skipGeometrisch) &&
        Objects.equals(this.skipFlaechenschluss, managerSystemConfigAllOf.skipFlaechenschluss) &&
        Objects.equals(this.skipGeltungsbereich, managerSystemConfigAllOf.skipGeltungsbereich);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rasterCrs, rasterType, skipSemantisch, skipGeometrisch, skipFlaechenschluss, skipGeltungsbereich);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ManagerSystemConfigAllOf {\n");
    
    sb.append("    rasterCrs: ").append(toIndentedString(rasterCrs)).append("\n");
    sb.append("    rasterType: ").append(toIndentedString(rasterType)).append("\n");
    sb.append("    skipSemantisch: ").append(toIndentedString(skipSemantisch)).append("\n");
    sb.append("    skipGeometrisch: ").append(toIndentedString(skipGeometrisch)).append("\n");
    sb.append("    skipFlaechenschluss: ").append(toIndentedString(skipFlaechenschluss)).append("\n");
    sb.append("    skipGeltungsbereich: ").append(toIndentedString(skipGeltungsbereich)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

