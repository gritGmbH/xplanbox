package de.latlon.xplanbox.internal.hale;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Classification {

    public enum CLASSIFICATIONTYPE {MAIN, SPECIFIC}

    private final String xplanungKlasse;

    private String xplanungAttribut;

    private List<String> defaultClassification;

    private List<String> defaultSpecificClassification;

    private Map<Integer, List<String>> classification = new TreeMap<>();

    private Map<Integer, List<String>> specificClassification = new TreeMap<>();

    public Classification( String xplanungKlasse ) {
        this.xplanungKlasse = xplanungKlasse;
    }

    public String getXplanungKlasse() {
        return xplanungKlasse;
    }

    public String getXplanungAttribut() {
        return xplanungAttribut;
    }

    public void setXplanungAttribut( String xplanungAttribut ) {
        this.xplanungAttribut = xplanungAttribut;
    }

    public void addClass( double code, String value, String specific ) {
        int intCode = (int) code;
        if ( intCode == 0 ) {
            defaultClassification = parseMultiple( value );
            defaultSpecificClassification = parseMultiple( specific );
        } else {
            classification.put( intCode, parseMultiple( value ) );
            specificClassification.put( intCode, parseMultiple( specific ) );
        }

    }

    public List<String> getDefaultClassification( CLASSIFICATIONTYPE type ) {
        if ( CLASSIFICATIONTYPE.SPECIFIC.equals( type ) )
            return defaultSpecificClassification;
        return defaultClassification;
    }

    public Map<Integer, List<String>> getClassification( CLASSIFICATIONTYPE type ) {
        if ( CLASSIFICATIONTYPE.SPECIFIC.equals( type ) )
            return specificClassification;
        return classification;
    }

    private List<String> parseMultiple( String value ) {
        List<String> values = asList( value.split( "&&" ) );
        return values.stream().map( s -> s.replaceAll( "[^a-zA-Z0-9_]", "" ) ).collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return "Classification{" + "xplanungKlasse='" + xplanungKlasse + '\'' + ", xplanungAttribut='"
               + xplanungAttribut + '\'' + ", classification=" + classification + ", specificClassification="
               + specificClassification + '}';
    }
}