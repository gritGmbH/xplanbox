package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.latlon.xplan.manager.web.shared.edit.BaseData;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2021-11-03T09:34:00.218+01:00[Europe/Berlin]")
public class Basisdaten {

    private @Valid String name;

    private @Valid String beschreibung;

    private @Valid String planArt;

    private @Valid String sonstPlanArt;

    private @Valid String verfahren;

    private @Valid String rechtsstand;

    private @Valid Date rechtsverordnungsDatum;

    private @Valid Date technHerstellDatum;

    private @Valid Date untergangsDatum;

    public static Basisdaten fromBaseData( BaseData baseData ) {
        return new Basisdaten().name( baseData.getPlanName() ).beschreibung( baseData.getDescription() ).planArt(
                        baseData.getPlanTypeCode() > 0 ?
                        Integer.toString( baseData.getPlanTypeCode() ) :
                        null ).sonstPlanArt( baseData.getOtherPlanTypeCode() > 0 ?
                                             Integer.toString( baseData.getOtherPlanTypeCode() ) :
                                             null ).verfahren( baseData.getMethodCode() > 0 ?
                                                               Integer.toString( baseData.getMethodCode() ) :
                                                               null ).rechtsstand(
                        baseData.getLegislationStatusCode() > 0 ?
                        Integer.toString( baseData.getLegislationStatusCode() ) :
                        null ).rechtsverordnungsDatum( baseData.getRegulationDate() ).technHerstellDatum(
                        baseData.getCreationDate() ).untergangsDatum( baseData.getLossDate() );
    }

    public BaseData toBaseData() {
        return new BaseData( name, beschreibung, technHerstellDatum, untergangsDatum, asInt( planArt ),
                             asInt( sonstPlanArt ), asInt( verfahren ), asInt( rechtsstand ),
                             rechtsverordnungsDatum );
    }

    private int asInt( String code ) {
        if ( code == null )
            return -1;
        return Integer.valueOf( code );
    }

    /**
     *
     **/
    public Basisdaten name( String name ) {
        this.name = name;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    /**
     *
     **/
    public Basisdaten beschreibung( String beschreibung ) {
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
    public Basisdaten planArt( String planArt ) {
        this.planArt = planArt;
        return this;
    }

    @JsonProperty("planArt")
    public String getPlanArt() {
        return planArt;
    }

    public void setPlanArt( String planArt ) {
        this.planArt = planArt;
    }

    /**
     *
     **/
    public Basisdaten sonstPlanArt( String sonstPlanArt ) {
        this.sonstPlanArt = sonstPlanArt;
        return this;
    }

    @JsonProperty("sonstPlanArt")
    public String getSonstPlanArt() {
        return sonstPlanArt;
    }

    public void setSonstPlanArt( String sonstPlanArt ) {
        this.sonstPlanArt = sonstPlanArt;
    }

    /**
     *
     **/
    public Basisdaten verfahren( String verfahren ) {
        this.verfahren = verfahren;
        return this;
    }

    @JsonProperty("verfahren")
    public String getVerfahren() {
        return verfahren;
    }

    public void setVerfahren( String verfahren ) {
        this.verfahren = verfahren;
    }

    /**
     *
     **/
    public Basisdaten rechtsstand( String rechtsstand ) {
        this.rechtsstand = rechtsstand;
        return this;
    }

    @JsonProperty("rechtsstand")
    public String getRechtsstand() {
        return rechtsstand;
    }

    public void setRechtsstand( String rechtsstand ) {
        this.rechtsstand = rechtsstand;
    }

    /**
     *
     **/
    public Basisdaten rechtsverordnungsDatum( Date rechtsverordnungsDatum ) {
        this.rechtsverordnungsDatum = rechtsverordnungsDatum;
        return this;
    }

    @JsonProperty("rechtsverordnungsDatum")
    public Date getRechtsverordnungsDatum() {
        return rechtsverordnungsDatum;
    }

    public void setRechtsverordnungsDatum( Date rechtsverordnungsDatum ) {
        this.rechtsverordnungsDatum = rechtsverordnungsDatum;
    }

    /**
     *
     **/
    public Basisdaten technHerstellDatum( Date technHerstellDatum ) {
        this.technHerstellDatum = technHerstellDatum;
        return this;
    }

    @JsonProperty("technHerstellDatum")
    public Date getTechnHerstellDatum() {
        return technHerstellDatum;
    }

    public void setTechnHerstellDatum( Date technHerstellDatum ) {
        this.technHerstellDatum = technHerstellDatum;
    }

    /**
     *
     **/
    public Basisdaten untergangsDatum( Date untergangsDatum ) {
        this.untergangsDatum = untergangsDatum;
        return this;
    }

    @JsonProperty("untergangsDatum")
    public Date getUntergangsDatum() {
        return untergangsDatum;
    }

    public void setUntergangsDatum( Date untergangsDatum ) {
        this.untergangsDatum = untergangsDatum;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Basisdaten basisdaten = (Basisdaten) o;
        return Objects.equals( this.name, basisdaten.name ) &&
               Objects.equals( this.beschreibung, basisdaten.beschreibung ) &&
               Objects.equals( this.planArt, basisdaten.planArt ) &&
               Objects.equals( this.sonstPlanArt, basisdaten.sonstPlanArt ) &&
               Objects.equals( this.verfahren, basisdaten.verfahren ) &&
               Objects.equals( this.rechtsstand, basisdaten.rechtsstand ) &&
               Objects.equals( this.rechtsverordnungsDatum, basisdaten.rechtsverordnungsDatum ) &&
               Objects.equals( this.technHerstellDatum, basisdaten.technHerstellDatum ) &&
               Objects.equals( this.untergangsDatum, basisdaten.untergangsDatum );
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, beschreibung, planArt, sonstPlanArt, verfahren, rechtsstand, rechtsverordnungsDatum,
                             technHerstellDatum, untergangsDatum );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( "class Basisdaten {\n" );

        sb.append( "    name: " ).append( toIndentedString( name ) ).append( "\n" );
        sb.append( "    beschreibung: " ).append( toIndentedString( beschreibung ) ).append( "\n" );
        sb.append( "    planArt: " ).append( toIndentedString( planArt ) ).append( "\n" );
        sb.append( "    sonstPlanArt: " ).append( toIndentedString( sonstPlanArt ) ).append( "\n" );
        sb.append( "    verfahren: " ).append( toIndentedString( verfahren ) ).append( "\n" );
        sb.append( "    rechtsstand: " ).append( toIndentedString( rechtsstand ) ).append( "\n" );
        sb.append( "    rechtsverordnungsDatum: " ).append( toIndentedString( rechtsverordnungsDatum ) ).append( "\n" );
        sb.append( "    technHerstellDatum: " ).append( toIndentedString( technHerstellDatum ) ).append( "\n" );
        sb.append( "    untergangsDatum: " ).append( toIndentedString( untergangsDatum ) ).append( "\n" );
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

