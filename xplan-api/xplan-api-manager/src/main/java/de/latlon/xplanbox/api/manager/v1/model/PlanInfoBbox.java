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
public class PlanInfoBbox   {
  
  private @Valid Integer minX;
  private @Valid Integer minY;
  private @Valid Integer maxX;
  private @Valid Integer maxY;
  private @Valid String crs;

  /**
   **/
  public PlanInfoBbox minX(Integer minX) {
    this.minX = minX;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("minX")
  public Integer getMinX() {
    return minX;
  }

  public void setMinX(Integer minX) {
    this.minX = minX;
  }/**
   **/
  public PlanInfoBbox minY(Integer minY) {
    this.minY = minY;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("minY")
  public Integer getMinY() {
    return minY;
  }

  public void setMinY(Integer minY) {
    this.minY = minY;
  }/**
   **/
  public PlanInfoBbox maxX(Integer maxX) {
    this.maxX = maxX;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("maxX")
  public Integer getMaxX() {
    return maxX;
  }

  public void setMaxX(Integer maxX) {
    this.maxX = maxX;
  }/**
   **/
  public PlanInfoBbox maxY(Integer maxY) {
    this.maxY = maxY;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("maxY")
  public Integer getMaxY() {
    return maxY;
  }

  public void setMaxY(Integer maxY) {
    this.maxY = maxY;
  }/**
   **/
  public PlanInfoBbox crs(String crs) {
    this.crs = crs;
    return this;
  }

  

  
  @ApiModelProperty(example = "epsg:4326", value = "")
  @JsonProperty("crs")
  public String getCrs() {
    return crs;
  }

  public void setCrs(String crs) {
    this.crs = crs;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlanInfoBbox planInfoBbox = (PlanInfoBbox) o;
    return Objects.equals(this.minX, planInfoBbox.minX) &&
        Objects.equals(this.minY, planInfoBbox.minY) &&
        Objects.equals(this.maxX, planInfoBbox.maxX) &&
        Objects.equals(this.maxY, planInfoBbox.maxY) &&
        Objects.equals(this.crs, planInfoBbox.crs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(minX, minY, maxX, maxY, crs);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlanInfoBbox {\n");
    
    sb.append("    minX: ").append(toIndentedString(minX)).append("\n");
    sb.append("    minY: ").append(toIndentedString(minY)).append("\n");
    sb.append("    maxX: ").append(toIndentedString(maxX)).append("\n");
    sb.append("    maxY: ").append(toIndentedString(maxY)).append("\n");
    sb.append("    crs: ").append(toIndentedString(crs)).append("\n");
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

