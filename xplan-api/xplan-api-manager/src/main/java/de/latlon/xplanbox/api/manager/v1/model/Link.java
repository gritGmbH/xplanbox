package de.latlon.xplanbox.api.manager.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.net.URI;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.*;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Link to a resource related to the resource such as XPlanWerkWMS or the resource itself
 **/
@ApiModel(description = "Link to a resource related to the resource such as XPlanWerkWMS or the resource itself")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
public class Link   {
  
  private @Valid URI href;

public enum RelEnum {

    SELF(String.valueOf("self")), ALTERNATE(String.valueOf("alternate")), PLANWERKWMS(String.valueOf("planwerkwms"));


    private String value;

    RelEnum (String v) {
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
    public static RelEnum fromValue(String value) {
        for (RelEnum b : RelEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

  private @Valid RelEnum rel;
  private @Valid String type;
  private @Valid String hreflang;
  private @Valid String title;
  private @Valid Integer length;

  /**
   **/
  public Link href(URI href) {
    this.href = href;
    return this;
  }

  

  
  @ApiModelProperty(example = "https://xplanbox.lat-lon.de/xmanager/api/v1/plan/123", required = true, value = "")
  @JsonProperty("href")
  @NotNull
  public URI getHref() {
    return href;
  }

  public void setHref(URI href) {
    this.href = href;
  }/**
   **/
  public Link rel(RelEnum rel) {
    this.rel = rel;
    return this;
  }

  

  
  @ApiModelProperty(example = "self", value = "")
  @JsonProperty("rel")
  public RelEnum getRel() {
    return rel;
  }

  public void setRel(RelEnum rel) {
    this.rel = rel;
  }/**
   **/
  public Link type(String type) {
    this.type = type;
    return this;
  }

  

  
  @ApiModelProperty(example = "application/json", value = "")
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }/**
   **/
  public Link hreflang(String hreflang) {
    this.hreflang = hreflang;
    return this;
  }

  

  
  @ApiModelProperty(example = "en", value = "")
  @JsonProperty("hreflang")
  public String getHreflang() {
    return hreflang;
  }

  public void setHreflang(String hreflang) {
    this.hreflang = hreflang;
  }/**
   **/
  public Link title(String title) {
    this.title = title;
    return this;
  }

  

  
  @ApiModelProperty(example = "Othmarschen 3, Hamburg", value = "")
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }/**
   **/
  public Link length(Integer length) {
    this.length = length;
    return this;
  }

  

  
  @ApiModelProperty(value = "")
  @JsonProperty("length")
  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return Objects.equals(this.href, link.href) &&
        Objects.equals(this.rel, link.rel) &&
        Objects.equals(this.type, link.type) &&
        Objects.equals(this.hreflang, link.hreflang) &&
        Objects.equals(this.title, link.title) &&
        Objects.equals(this.length, link.length);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, rel, type, hreflang, title, length);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Link {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    rel: ").append(toIndentedString(rel)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    hreflang: ").append(toIndentedString(hreflang)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    length: ").append(toIndentedString(length)).append("\n");
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

