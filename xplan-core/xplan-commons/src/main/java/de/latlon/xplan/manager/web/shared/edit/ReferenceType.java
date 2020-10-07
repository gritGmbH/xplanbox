package de.latlon.xplan.manager.web.shared.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to discriminate references types.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public enum ReferenceType {

    BESCHREIBUNG( 1000, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    BEGRUENDUNG( 1010, "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    LEGENDE( 1020, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    RECHTSPLAN( 1030, "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    PLANGRUNDLAGE( 1040, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    UMWELTBERICHT( 1050, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    SATZUNG( 1060, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    VERORDNUNG( 1065, "XPLAN_51", "XPLAN_52" ),

    KARTE( 1070, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    ERLAEUTERUNG( 1080, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    ZUSAMMENFASSENDEERKLAERUNG( 1090, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    KOORDINATENLISTE( 2000, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    GRUNDSTUECKSVERZEICHNIS( 2100, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    PFLANZLISTE( 2200, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    GRUENORDNUNGSPLAN( 2300, "XPLAN_3", "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    ERSCHLIESSUNGSVERTRAG( 2400, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    DURCHFUEHRUNGSVERTRAG( 2500, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    STAEDTEBAULICHERVERTRAG( 2600, "XPLAN_51", "XPLAN_52" ),

    UMWELTBEZOGENESTELLUNGNAHMEN( 2700, "XPLAN_51", "XPLAN_52" ),

    BESCHLUSS( 2800, "XPLAN_51", "XPLAN_52" ),

    VORHABENUNDERSCHLIESSUNGSPLAN( 2900, "XPLAN_52" ),

    METADATENPLAN( 3000, "XPLAN_52" ),

    RECHTSVERBINDLICH( 9998, "XPLAN_50", "XPLAN_51", "XPLAN_52" ),

    INFORMELL( 9999, "XPLAN_50", "XPLAN_51", "XPLAN_52" );

    private int spezExterneRefType;

    private List<String> supportedXPlanVersions = new ArrayList<String>();

    ReferenceType( int spezExterneRefType, String... supportedXPlanVersions ) {
        this.spezExterneRefType = spezExterneRefType;
        this.supportedXPlanVersions.addAll( Arrays.asList( supportedXPlanVersions ) );
    }

    public static ReferenceType getBySpezExterneReferenceType( String type ) {
        if ( type == null )
            return null;
        int typeAsInt = Integer.parseInt( type.trim() );
        for ( ReferenceType referenceType : values() ) {
            if ( referenceType.spezExterneRefType == typeAsInt )
                return referenceType;
        }
        return null;
    }

    public int getSpezExterneReferenceType() {
        return spezExterneRefType;
    }

    public boolean isXPlanVersionSupported( String xPlanVersion ) {
        return this.supportedXPlanVersions.contains( xPlanVersion );
    }

}