package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import java.util.Objects;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Aenderung {

    private @Valid String planName;

    private @Valid String rechtscharakter;

    private @Valid String nummer;

    /**
     *
     **/
    public Aenderung planName( String planName ) {
        this.planName = planName;
        return this;
    }

    @JsonProperty("planName")
    public String getPlanName() {
        return planName;
    }

    public void setPlanName( String planName ) {
        this.planName = planName;
    }

    /**
     *
     **/
    public Aenderung rechtscharakter( String rechtscharakter ) {
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

    /**
     *
     **/
    public Aenderung nummer( String nummer ) {
        this.nummer = nummer;
        return this;
    }

    @JsonProperty("nummer")
    public String getNummer() {
        return nummer;
    }

    public void setNummer( String nummer ) {
        this.nummer = nummer;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Aenderung aenderung = (Aenderung) o;
        return Objects.equals( this.planName, aenderung.planName ) &&
               Objects.equals( this.rechtscharakter, aenderung.rechtscharakter ) &&
               Objects.equals( this.nummer, aenderung.nummer );
    }

    @Override
    public int hashCode() {
        return Objects.hash( planName, rechtscharakter, nummer );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Aenderung {\n" );

        sb.append( "    planName: " ).append( toIndentedString( planName ) ).append( "\n" );
        sb.append( "    rechtscharakter: " ).append( toIndentedString( rechtscharakter ) ).append( "\n" );
        sb.append( "    nummer: " ).append( toIndentedString( nummer ) ).append( "\n" );
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

