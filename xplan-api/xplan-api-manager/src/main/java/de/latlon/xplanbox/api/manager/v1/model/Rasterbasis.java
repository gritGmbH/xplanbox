package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;

/**
 * Datatype for Rasterbasis.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Rasterbasis extends Referenz {

    private String id;

    public static Rasterbasis fromRasterReference( String rasterbasisId, RasterReference rasterReference ) {
        Rasterbasis rasterbasis = new Rasterbasis().id( rasterbasisId );
        rasterbasis.art( rasterReference.getArt() != null ? rasterReference.getArt().getCode() : null ).beschreibung(
                        rasterReference.getBeschreibung() ).datum( rasterReference.getDatum() ).georefMimeType(
                        rasterReference.getGeorefMimeType() != null ?
                        rasterReference.getGeorefMimeType().getCode() :
                        null ).georefURL( rasterReference.getGeoReference() ).informationssystemURL(
                        rasterReference.getInformationssystemURL() ).referenzMimeType(
                        rasterReference.getReferenzMimeType() != null ?
                        rasterReference.getReferenzMimeType().getCode() :
                        null ).referenzURL( rasterReference.getReference() ).referenzName(
                        rasterReference.getReferenzName() );
        return rasterbasis;
    }

    public RasterReference toRasterReference() {
        RasterReference rasterReference = new RasterReference();
        rasterReference.setType( SCAN );
        rasterReference.setReference( getReferenzURL() );
        rasterReference.setReferenzMimeType( MimeTypes.getByCode( getReferenzMimeType() ) );
        rasterReference.setGeoReference( getGeorefURL() );
        rasterReference.setGeorefMimeType( MimeTypes.getByCode( getGeorefMimeType() ) );
        rasterReference.setArt( ExterneReferenzArt.getByCode( getArt() ) );
        rasterReference.setBeschreibung( getBeschreibung() );
        rasterReference.setDatum( getDatum() );
        rasterReference.setInformationssystemURL( getInformationssystemURL() );
        return rasterReference;
    }

    public Rasterbasis id( String id ) {
        this.id = id;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        return super.equals( o );
    }

    @Override
    public int hashCode() {
        return Objects.hash( super.hashCode() );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Rasterbasis {\n" );
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

