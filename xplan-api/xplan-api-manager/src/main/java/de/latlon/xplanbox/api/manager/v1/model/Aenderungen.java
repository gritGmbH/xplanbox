package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplan.manager.web.shared.edit.Change;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;

/**
 * Datatype for Aenderungen.
 *
 * @since 4.4
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Aenderungen {

    private @Valid List<Aenderung> aendert = new ArrayList<Aenderung>();

    private @Valid List<Aenderung> wurdeGeaendertVon = new ArrayList<Aenderung>();

    public static Aenderungen fromChanges( List<Change> changes ) {
        Aenderungen aenderungen = new Aenderungen();
        aenderungen.aendert( changes.stream().filter( change -> CHANGES.equals( change.getType() ) ).map(
                        c -> new Aenderung().nummer( c.getNumber() ).planName( c.getPlanName() ).rechtscharakter(
                                        c.getLegalNatureCode() ) ).collect( Collectors.toList() ) );
        aenderungen.wurdeGeaendertVon( changes.stream().filter( change -> CHANGED_BY.equals( change.getType() ) ).map(
                        c -> new Aenderung() ).collect( Collectors.toList() ) );
        return aenderungen;
    }

    public List<Change> toChanges() {
        List<Change> changes = new ArrayList<>();
        changes.addAll( aendert.stream().map(
                        a -> new Change( a.getPlanName(), asInt( a.getRechtscharakter() ), a.getNummer(),
                                         CHANGES ) ).collect(
                        Collectors.toList() ) );
        changes.addAll( wurdeGeaendertVon.stream().map(
                        a -> new Change( a.getPlanName(), asInt( a.getRechtscharakter() ), a.getNummer(),
                                         CHANGED_BY ) ).collect(
                        Collectors.toList() ) );
        return changes;
    }

    private int asInt( Integer code ) {
        if ( code == null )
            return -1;
        return code;
    }

    /**
     *
     **/
    public Aenderungen aendert( List<Aenderung> aendert ) {
        this.aendert = aendert;
        return this;
    }

    @JsonProperty("aendert")
    public List<Aenderung> getAendert() {
        return aendert;
    }

    public void setAendert( List<Aenderung> aendert ) {
        this.aendert = aendert;
    }

    /**
     *
     **/
    public Aenderungen wurdeGeaendertVon( List<Aenderung> wurdeGeaendertVon ) {
        this.wurdeGeaendertVon = wurdeGeaendertVon;
        return this;
    }

    @JsonProperty("wurdeGeaendertVon")
    public List<Aenderung> getWurdeGeaendertVon() {
        return wurdeGeaendertVon;
    }

    public void setWurdeGeaendertVon( List<Aenderung> wurdeGeaendertVon ) {
        this.wurdeGeaendertVon = wurdeGeaendertVon;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Aenderungen aenderungen = (Aenderungen) o;
        return Objects.equals( this.aendert, aenderungen.aendert ) &&
               Objects.equals( this.wurdeGeaendertVon, aenderungen.wurdeGeaendertVon );
    }

    @Override
    public int hashCode() {
        return Objects.hash( aendert, wurdeGeaendertVon );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Aenderungen {\n" );

        sb.append( "    aendert: " ).append( toIndentedString( aendert ) ).append( "\n" );
        sb.append( "    wurdeGeaendertVon: " ).append( toIndentedString( wurdeGeaendertVon ) ).append( "\n" );
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

