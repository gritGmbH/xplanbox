package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PlanStatusEnum {

    FESTGESTELLT( String.valueOf( "FESTGESTELLT" ) ), IN_AUFSTELLUNG( String.valueOf( "IN_AUFSTELLUNG" ) ), ARCHIVIERT(
                            String.valueOf( "ARCHIVIERT" ) );

    private String value;

    PlanStatusEnum( String v ) {
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
    public static PlanStatusEnum fromValue( String value ) {
        for ( PlanStatusEnum b : PlanStatusEnum.values() ) {
            if ( b.value.equals( value ) ) {
                return b;
            }
        }
        throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
    }
}