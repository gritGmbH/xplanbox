/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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