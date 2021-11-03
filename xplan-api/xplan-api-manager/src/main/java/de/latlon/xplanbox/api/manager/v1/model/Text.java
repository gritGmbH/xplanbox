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
public class Text {

    private @Valid String schluessel;

    private @Valid String gesetzlicheGrundlage;

    private @Valid String text;

    private @Valid Referenz refText;

    private @Valid String rechtscharakter;

    /**
     *
     **/
    public Text schluessel( String schluessel ) {
        this.schluessel = schluessel;
        return this;
    }

    @JsonProperty("schluessel")
    public String getSchluessel() {
        return schluessel;
    }

    public void setSchluessel( String schluessel ) {
        this.schluessel = schluessel;
    }

    /**
     *
     **/
    public Text gesetzlicheGrundlage( String gesetzlicheGrundlage ) {
        this.gesetzlicheGrundlage = gesetzlicheGrundlage;
        return this;
    }

    @JsonProperty("gesetzlicheGrundlage")
    public String getGesetzlicheGrundlage() {
        return gesetzlicheGrundlage;
    }

    public void setGesetzlicheGrundlage( String gesetzlicheGrundlage ) {
        this.gesetzlicheGrundlage = gesetzlicheGrundlage;
    }

    /**
     *
     **/
    public Text text( String text ) {
        this.text = text;
        return this;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    /**
     *
     **/
    public Text refText( Referenz refText ) {
        this.refText = refText;
        return this;
    }

    @JsonProperty("refText")
    public Referenz getRefText() {
        return refText;
    }

    public void setRefText( Referenz refText ) {
        this.refText = refText;
    }

    /**
     *
     **/
    public Text rechtscharakter( String rechtscharakter ) {
        this.rechtscharakter = rechtscharakter;
        return this;
    }

    @JsonProperty("rechtscharakter")
    public String getRechtscharakter() {
        return rechtscharakter;
    }

    public void setRechtscharakter( String rechtscharakter ) {
        this.rechtscharakter = rechtscharakter;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Text text = (Text) o;
        return Objects.equals( this.schluessel, text.schluessel ) &&
               Objects.equals( this.gesetzlicheGrundlage, text.gesetzlicheGrundlage ) &&
               Objects.equals( this.text, text.text ) &&
               Objects.equals( this.refText, text.refText ) &&
               Objects.equals( this.rechtscharakter, text.rechtscharakter );
    }

    @Override
    public int hashCode() {
        return Objects.hash( schluessel, gesetzlicheGrundlage, text, refText, rechtscharakter );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Text {\n" );

        sb.append( "    schluessel: " ).append( toIndentedString( schluessel ) ).append( "\n" );
        sb.append( "    gesetzlicheGrundlage: " ).append( toIndentedString( gesetzlicheGrundlage ) ).append( "\n" );
        sb.append( "    text: " ).append( toIndentedString( text ) ).append( "\n" );
        sb.append( "    refText: " ).append( toIndentedString( refText ) ).append( "\n" );
        sb.append( "    rechtscharakter: " ).append( toIndentedString( rechtscharakter ) ).append( "\n" );
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

