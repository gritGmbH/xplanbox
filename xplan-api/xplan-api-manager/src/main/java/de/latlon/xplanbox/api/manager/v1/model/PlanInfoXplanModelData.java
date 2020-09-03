package de.latlon.xplanbox.api.manager.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * attributes derived from XPlanGML data model
 **/
@ApiModel(description = "attributes derived from XPlanGML data model")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class PlanInfoXplanModelData   {
  
  private @Valid String name;
  private @Valid String nummer;
  private @Valid String internalId;
  private @Valid String ags;
  private @Valid String gemeindeName;
  private @Valid String rechtsstand;
  private @Valid Date inkrafttretensDatum;

  /**
   **/
  public PlanInfoXplanModelData name(String name) {
    this.name = name;
    return this;
  }

  

  
  @ApiModelProperty(example = "Othmarschen3", value = "")
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }/**
   **/
  public PlanInfoXplanModelData nummer(String nummer) {
    this.nummer = nummer;
    return this;
  }

  

  
  @ApiModelProperty(example = "-", value = "")
  @JsonProperty("nummer")
  public String getNummer() {
    return nummer;
  }

  public void setNummer(String nummer) {
    this.nummer = nummer;
  }/**
   **/
  public PlanInfoXplanModelData internalId(String internalId) {
    this.internalId = internalId;
    return this;
  }

  

  
  @ApiModelProperty(example = "12341", value = "")
  @JsonProperty("internalId")
  public String getInternalId() {
    return internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }/**
   **/
  public PlanInfoXplanModelData ags(String ags) {
    this.ags = ags;
    return this;
  }

  

  
  @ApiModelProperty(example = "02000000", value = "")
  @JsonProperty("ags")
  public String getAgs() {
    return ags;
  }

  public void setAgs(String ags) {
    this.ags = ags;
  }/**
   **/
  public PlanInfoXplanModelData gemeindeName(String gemeindeName) {
    this.gemeindeName = gemeindeName;
    return this;
  }

  

  
  @ApiModelProperty(example = "Gemeindename", value = "")
  @JsonProperty("gemeindeName")
  public String getGemeindeName() {
    return gemeindeName;
  }

  public void setGemeindeName(String gemeindeName) {
    this.gemeindeName = gemeindeName;
  }/**
   * translation of code list value of xplan:rechtsstand
   **/
  public PlanInfoXplanModelData rechtsstand(String rechtsstand) {
    this.rechtsstand = rechtsstand;
    return this;
  }

  

  
  @ApiModelProperty(example = "Satzung", value = "translation of code list value of xplan:rechtsstand")
  @JsonProperty("rechtsstand")
  public String getRechtsstand() {
    return rechtsstand;
  }

  public void setRechtsstand(String rechtsstand) {
    this.rechtsstand = rechtsstand;
  }/**
   **/
  public PlanInfoXplanModelData inkrafttretensDatum(Date inkrafttretensDatum) {
    this.inkrafttretensDatum = inkrafttretensDatum;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("inkrafttretensDatum")
  public Date getInkrafttretensDatum() {
    return inkrafttretensDatum;
  }

  public void setInkrafttretensDatum(Date inkrafttretensDatum) {
    this.inkrafttretensDatum = inkrafttretensDatum;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlanInfoXplanModelData planInfoXplanModelData = (PlanInfoXplanModelData) o;
    return Objects.equals(this.name, planInfoXplanModelData.name) &&
        Objects.equals(this.nummer, planInfoXplanModelData.nummer) &&
        Objects.equals(this.internalId, planInfoXplanModelData.internalId) &&
        Objects.equals(this.ags, planInfoXplanModelData.ags) &&
        Objects.equals(this.gemeindeName, planInfoXplanModelData.gemeindeName) &&
        Objects.equals(this.rechtsstand, planInfoXplanModelData.rechtsstand) &&
        Objects.equals(this.inkrafttretensDatum, planInfoXplanModelData.inkrafttretensDatum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, nummer, internalId, ags, gemeindeName, rechtsstand, inkrafttretensDatum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlanInfoXplanModelData {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nummer: ").append(toIndentedString(nummer)).append("\n");
    sb.append("    internalId: ").append(toIndentedString(internalId)).append("\n");
    sb.append("    ags: ").append(toIndentedString(ags)).append("\n");
    sb.append("    gemeindeName: ").append(toIndentedString(gemeindeName)).append("\n");
    sb.append("    rechtsstand: ").append(toIndentedString(rechtsstand)).append("\n");
    sb.append("    inkrafttretensDatum: ").append(toIndentedString(inkrafttretensDatum)).append("\n");
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

