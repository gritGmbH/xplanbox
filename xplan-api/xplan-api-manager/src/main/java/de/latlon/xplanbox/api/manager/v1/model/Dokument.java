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
public class Dokument {

    private @Valid String georefURL;

    private @Valid String georefMimeType;

    private @Valid String art;

    private @Valid String informationssystemURL;

    private @Valid String referenzName;

    private @Valid String referenzURL;

    private @Valid String referenzMimeType;

    private @Valid String beschreibung;

    private @Valid String datum;

    private @Valid String typ;

    /**
     *
     **/
    public Dokument georefURL( String georefURL ) {
        this.georefURL = georefURL;
        return this;
    }

    @JsonProperty("georefURL")
    public String getGeorefURL() {
        return georefURL;
    }

    public void setGeorefURL( String georefURL ) {
        this.georefURL = georefURL;
    }

    /**
     *
     **/
    public Dokument georefMimeType( String georefMimeType ) {
        this.georefMimeType = georefMimeType;
        return this;
    }

    @JsonProperty("georefMimeType")
    public String getGeorefMimeType() {
        return georefMimeType;
    }

    public void setGeorefMimeType( String georefMimeType ) {
        this.georefMimeType = georefMimeType;
    }

    /**
     *
     **/
    public Dokument art( String art ) {
        this.art = art;
        return this;
    }

    @JsonProperty("art")
    public String getArt() {
        return art;
    }

    public void setArt( String art ) {
        this.art = art;
    }

    /**
     *
     **/
    public Dokument informationssystemURL( String informationssystemURL ) {
        this.informationssystemURL = informationssystemURL;
        return this;
    }

    @JsonProperty("informationssystemURL")
    public String getInformationssystemURL() {
        return informationssystemURL;
    }

    public void setInformationssystemURL( String informationssystemURL ) {
        this.informationssystemURL = informationssystemURL;
    }

    /**
     *
     **/
    public Dokument referenzName( String referenzName ) {
        this.referenzName = referenzName;
        return this;
    }

    @JsonProperty("referenzName")
    public String getReferenzName() {
        return referenzName;
    }

    public void setReferenzName( String referenzName ) {
        this.referenzName = referenzName;
    }

    /**
     *
     **/
    public Dokument referenzURL( String referenzURL ) {
        this.referenzURL = referenzURL;
        return this;
    }

    @JsonProperty("referenzURL")
    public String getReferenzURL() {
        return referenzURL;
    }

    public void setReferenzURL( String referenzURL ) {
        this.referenzURL = referenzURL;
    }

    /**
     *
     **/
    public Dokument referenzMimeType( String referenzMimeType ) {
        this.referenzMimeType = referenzMimeType;
        return this;
    }

    @JsonProperty("referenzMimeType")
    public String getReferenzMimeType() {
        return referenzMimeType;
    }

    public void setReferenzMimeType( String referenzMimeType ) {
        this.referenzMimeType = referenzMimeType;
    }

    /**
     *
     **/
    public Dokument beschreibung( String beschreibung ) {
        this.beschreibung = beschreibung;
        return this;
    }

    @JsonProperty("beschreibung")
    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung( String beschreibung ) {
        this.beschreibung = beschreibung;
    }

    /**
     *
     **/
    public Dokument datum( String datum ) {
        this.datum = datum;
        return this;
    }

    @JsonProperty("datum")
    public String getDatum() {
        return datum;
    }

    public void setDatum( String datum ) {
        this.datum = datum;
    }

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
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Dokument dokument = (Dokument) o;
        return Objects.equals( this.georefURL, dokument.georefURL ) &&
               Objects.equals( this.georefMimeType, dokument.georefMimeType ) &&
               Objects.equals( this.art, dokument.art ) &&
               Objects.equals( this.informationssystemURL, dokument.informationssystemURL ) &&
               Objects.equals( this.referenzName, dokument.referenzName ) &&
               Objects.equals( this.referenzURL, dokument.referenzURL ) &&
               Objects.equals( this.referenzMimeType, dokument.referenzMimeType ) &&
               Objects.equals( this.beschreibung, dokument.beschreibung ) &&
               Objects.equals( this.datum, dokument.datum ) &&
               Objects.equals( this.typ, dokument.typ );
    }

    @Override
    public int hashCode() {
        return Objects.hash( georefURL, georefMimeType, art, informationssystemURL, referenzName, referenzURL,
                             referenzMimeType, beschreibung, datum, typ );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Dokument {\n" );

        sb.append( "    georefURL: " ).append( toIndentedString( georefURL ) ).append( "\n" );
        sb.append( "    georefMimeType: " ).append( toIndentedString( georefMimeType ) ).append( "\n" );
        sb.append( "    art: " ).append( toIndentedString( art ) ).append( "\n" );
        sb.append( "    informationssystemURL: " ).append( toIndentedString( informationssystemURL ) ).append( "\n" );
        sb.append( "    referenzName: " ).append( toIndentedString( referenzName ) ).append( "\n" );
        sb.append( "    referenzURL: " ).append( toIndentedString( referenzURL ) ).append( "\n" );
        sb.append( "    referenzMimeType: " ).append( toIndentedString( referenzMimeType ) ).append( "\n" );
        sb.append( "    beschreibung: " ).append( toIndentedString( beschreibung ) ).append( "\n" );
        sb.append( "    datum: " ).append( toIndentedString( datum ) ).append( "\n" );
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

