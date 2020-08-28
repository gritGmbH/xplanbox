package de.latlon.xplanbox.api.manager.v1.model;

import de.latlon.xplanbox.api.manager.v1.model.RulesMetadata;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class SystemConfig   {
  
  private @Valid String version;
  private @Valid RulesMetadata rulesMetadata;
 
public enum SupportedXPlanGmlVersionsEnum {

    _3(String.valueOf("XPLAN_3")), _40(String.valueOf("XPLAN_40")), _41(String.valueOf("XPLAN_41")), _50(String.valueOf("XPLAN_50")), _51(String.valueOf("XPLAN_51")), _52(String.valueOf("XPLAN_52"));


    private String value;

    SupportedXPlanGmlVersionsEnum (String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static SupportedXPlanGmlVersionsEnum fromValue(String value) {
        for (SupportedXPlanGmlVersionsEnum b : SupportedXPlanGmlVersionsEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions = new ArrayList<SupportedXPlanGmlVersionsEnum>();

  /**
   * Version der xPlanBox
   **/
  public SystemConfig version(String version) {
    this.version = version;
    return this;
  }

  

  
  @ApiModelProperty(example = "v3.4.0", value = "Version der xPlanBox")
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }/**
   **/
  public SystemConfig rulesMetadata(RulesMetadata rulesMetadata) {
    this.rulesMetadata = rulesMetadata;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("rulesMetadata")
  public RulesMetadata getRulesMetadata() {
    return rulesMetadata;
  }

  public void setRulesMetadata(RulesMetadata rulesMetadata) {
    this.rulesMetadata = rulesMetadata;
  }/**
   **/
  public SystemConfig supportedXPlanGmlVersions(List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions) {
    this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("supportedXPlanGmlVersions")
  public List<SupportedXPlanGmlVersionsEnum> getSupportedXPlanGmlVersions() {
    return supportedXPlanGmlVersions;
  }

  public void setSupportedXPlanGmlVersions(List<SupportedXPlanGmlVersionsEnum> supportedXPlanGmlVersions) {
    this.supportedXPlanGmlVersions = supportedXPlanGmlVersions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SystemConfig systemConfig = (SystemConfig) o;
    return Objects.equals(this.version, systemConfig.version) &&
        Objects.equals(this.rulesMetadata, systemConfig.rulesMetadata) &&
        Objects.equals(this.supportedXPlanGmlVersions, systemConfig.supportedXPlanGmlVersions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, rulesMetadata, supportedXPlanGmlVersions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SystemConfig {\n");
    
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    rulesMetadata: ").append(toIndentedString(rulesMetadata)).append("\n");
    sb.append("    supportedXPlanGmlVersions: ").append(toIndentedString(supportedXPlanGmlVersions)).append("\n");
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

