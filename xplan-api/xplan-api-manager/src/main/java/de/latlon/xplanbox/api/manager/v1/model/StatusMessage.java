package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2020-09-22T07:19:41.345+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StatusMessage {

    private @Valid String message;

    /**
     *
     **/
    public StatusMessage message( String message ) {
        this.message = message;
        return this;
    }

    @Schema(example = "Plan mit ID 123 entfernt")
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        StatusMessage statusMessage = (StatusMessage) o;
        return Objects.equals( this.message, statusMessage.message );
    }

    @Override
    public int hashCode() {
        return Objects.hash( message );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class StatusMessage {\n" );

        sb.append( "    message: " ).append( toIndentedString( message ) ).append( "\n" );
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

