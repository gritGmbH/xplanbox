package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Zeitraum {

    private @Valid String start;

    private @Valid String ende;

    /**
     *
     **/
    public Zeitraum start( String start ) {
        this.start = start;
        return this;
    }

    @JsonProperty("start")
    public String getStart() {
        return start;
    }

    public void setStart( String start ) {
        this.start = start;
    }

    /**
     *
     **/
    public Zeitraum ende( String ende ) {
        this.ende = ende;
        return this;
    }

    @JsonProperty("ende")
    public String getEnde() {
        return ende;
    }

    public void setEnde( String ende ) {
        this.ende = ende;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Zeitraum zeitraum = (Zeitraum) o;
        return Objects.equals( this.start, zeitraum.start ) &&
               Objects.equals( this.ende, zeitraum.ende );
    }

    @Override
    public int hashCode() {
        return Objects.hash( start, ende );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Zeitraum {\n" );

        sb.append( "    start: " ).append( toIndentedString( start ) ).append( "\n" );
        sb.append( "    ende: " ).append( toIndentedString( ende ) ).append( "\n" );
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

