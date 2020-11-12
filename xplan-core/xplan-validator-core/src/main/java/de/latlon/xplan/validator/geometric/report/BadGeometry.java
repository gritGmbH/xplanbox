/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.report;

import java.util.ArrayList;
import java.util.List;

import org.deegree.geometry.Geometry;

/**
 * contains a defect geometry and its error-Strings
 *
 * @author bingel
 */

public class BadGeometry {

    Geometry geometry;

    private final List<String> errors = new ArrayList<>();

    public BadGeometry() {
    }

    public BadGeometry( Geometry geometry ) {
        this.geometry = geometry;
    }

    public BadGeometry( Geometry geometry, String error ) {
        this.geometry = geometry;
        addError( error );
    }

    public void setGeometry( Geometry geometry ) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorsSingleString() {
        StringBuilder allErrors = new StringBuilder();
        for ( String error : errors ) {
            allErrors.append( error );
            allErrors.append( "; " );
        }
        return allErrors.toString();
    }

    public void addError( String err ) {
        errors.add( err );
    }
}
