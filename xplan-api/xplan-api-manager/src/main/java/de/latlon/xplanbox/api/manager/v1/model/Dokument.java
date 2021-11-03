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
public class Dokument extends Referenz {

    private @Valid String typ;

    /**
     *
     **/
    public Dokument typ( String typ ) {
        this.typ = typ;
        return this;
    }

    @JsonProperty("typ")
    public String getTyp() {
        return typ;
    }

    public void setTyp( String typ ) {
        this.typ = typ;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;
        if ( !super.equals( o ) )
            return false;
        Dokument dokument = (Dokument) o;
        return Objects.equals( typ, dokument.typ );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode(), typ );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Dokument {\n" );
        sb.append( "    georefURL: " ).append( toIndentedString( getGeorefURL() ) ).append( "\n" );
        sb.append( "    georefMimeType: " ).append( toIndentedString( getGeorefMimeType() ) ).append( "\n" );
        sb.append( "    art: " ).append( toIndentedString( getArt() ) ).append( "\n" );
        sb.append( "    informationssystemURL: " ).append( toIndentedString( getInformationssystemURL() ) ).append(
                        "\n" );
        sb.append( "    referenzName: " ).append( toIndentedString( getReferenzName() ) ).append( "\n" );
        sb.append( "    referenzURL: " ).append( toIndentedString( getReferenzURL() ) ).append( "\n" );
        sb.append( "    referenzMimeType: " ).append( toIndentedString( getReferenzMimeType() ) ).append( "\n" );
        sb.append( "    beschreibung: " ).append( toIndentedString( getBeschreibung() ) ).append( "\n" );
        sb.append( "    datum: " ).append( toIndentedString( getDatum() ) ).append( "\n" );
        sb.append( "    typ: " ).append( toIndentedString( typ ) ).append( "\n" );
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

