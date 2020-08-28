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
public class RulesMetadata   {
  
  private @Valid String version;
  private @Valid String source;

  /**
   **/
  public RulesMetadata version(String version) {
    this.version = version;
    return this;
  }

  

  
  @ApiModelProperty(example = "0.9.14", value = "")
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }/**
   **/
  public RulesMetadata source(String source) {
    this.source = source;
    return this;
  }

  

  
  @ApiModelProperty(example = "https://bitbucket.org/geowerkstatt-hamburg/xplanung/get/v0.9.14.zip", value = "")
  @JsonProperty("source")
  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RulesMetadata rulesMetadata = (RulesMetadata) o;
    return Objects.equals(this.version, rulesMetadata.version) &&
        Objects.equals(this.source, rulesMetadata.source);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, source);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RulesMetadata {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
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

