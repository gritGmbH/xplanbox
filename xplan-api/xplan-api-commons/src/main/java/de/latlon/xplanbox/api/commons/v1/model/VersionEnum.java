package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum VersionEnum {

    _3( String.valueOf( "XPLAN_3" ) ), _40( String.valueOf( "XPLAN_40" ) ), _41( String.valueOf( "XPLAN_41" ) ), _50(
                            String.valueOf( "XPLAN_50" ) ), _51( String.valueOf( "XPLAN_51" ) ), _52(
                            String.valueOf( "XPLAN_52" ) );

    private String value;

    VersionEnum( String v ) {
        value = v;
    }

    public String value() {
        return value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf( value );
    }

    @JsonCreator
    public static VersionEnum fromValue( String value ) {
        for ( VersionEnum b : VersionEnum.values() ) {
            if ( b.value.equals( value ) ) {
                return b;
            }
        }
        throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
    }
}