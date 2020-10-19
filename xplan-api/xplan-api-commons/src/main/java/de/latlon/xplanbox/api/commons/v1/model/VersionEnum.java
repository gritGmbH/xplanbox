package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import de.latlon.xplan.commons.XPlanVersion;

public enum VersionEnum {

    XPLAN_3( String.valueOf( "XPLAN_3" ) ),

    XPLAN_40( String.valueOf( "XPLAN_40" ) ),

    XPLAN_41( String.valueOf( "XPLAN_41" ) ),

    XPLAN_50( String.valueOf( "XPLAN_50" ) ),

    XPLAN_51( String.valueOf( "XPLAN_51" ) ),

    XPLAN_52( String.valueOf( "XPLAN_52" ) );

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

    /**
     * @param xPlanVersion
     *                         may be <code>null</code>
     * @return the XPlanVersion as VersionEnum, <code>null</code> if xPlanVersion was null
     * @throws IllegalArgumentException
     *                         if the passed xPlanVersion could not be converted to a VersionEnum
     */
    public static VersionEnum fromXPlanVersion( XPlanVersion xPlanVersion ) {
        if ( xPlanVersion == null )
            return null;
        for ( VersionEnum b : VersionEnum.values() ) {
            if ( b.value.equals( xPlanVersion.name() ) ) {
                return b;
            }
        }
        throw new IllegalArgumentException( "Unexpected XPlanVersion '" + xPlanVersion + "'" );
    }
}