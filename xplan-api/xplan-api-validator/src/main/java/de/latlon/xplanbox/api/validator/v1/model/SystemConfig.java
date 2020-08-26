package de.latlon.xplanbox.api.validator.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-08-26T09:59:16.298+02:00[Europe/Berlin]")
public class SystemConfig {

    private @Valid String version;

    private @Valid RulesMetadata rulesMetadata;

    /**
     * Version der xPlanBox
     **/
    public SystemConfig version( String version ) {
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
    public SystemConfig rulesMetadata( RulesMetadata rulesMetadata ) {
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

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        SystemConfig systemConfig = (SystemConfig) o;
        return Objects.equals( this.version, systemConfig.version ) && Objects.equals( this.rulesMetadata,
                                                                                       systemConfig.rulesMetadata );
    }

    @Override
    public int hashCode() {
        return Objects.hash( version, rulesMetadata );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class SystemConfig {\n" );

        sb.append( "    version: " ).append( toIndentedString( version ) ).append( "\n" );
        sb.append( "    rulesMetadata: " ).append( toIndentedString( rulesMetadata ) ).append( "\n" );
        sb.append( "}" );
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString( Object o ) {
        if ( o == null ) {
            return "null";
        }
        return o.toString().replace( "\n", "\n    " );
    }

}

