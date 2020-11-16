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
package de.latlon.xplan.validator.semantic.report;

import java.util.List;

/**
 * contains the validator result of the rules of the semantic validation
 *
 * @author bingel
 */
public class RuleResult implements Comparable {

    private final String name;

    private final boolean isValid;

    private final String message;

  private final List<String> invalidFeatures;

  protected RuleResult( String name, boolean isValid, String message, List<String> invalidFeatures ) {
    this.name = name;
    this.isValid = isValid;
    this.message = message;
    this.invalidFeatures = invalidFeatures;
  }

    public String getName() {
        return name;
    }

    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }

  public List<String> getInvalidFeatures() {
    return invalidFeatures;
  }

  @Override
  public String toString() {
    return "RuleResult{" +
           "name='" + name + '\'' +
           ", isValid=" + isValid +
           ", message='" + message + '\'' +
           '}';
  }

    @Override
    public int compareTo( Object o ) {
        try {
            String[] array1 = this.getName().split( "\\." );
            String[] array2 = ( (RuleResult) o ).getName().split( "\\." );
            for ( int i = 0; i < array1.length && i < array2.length; i++ ) {
                int result = Integer.valueOf( array1[i] ).compareTo( Integer.valueOf( array2[i] ) );
                if ( result != 0 )
                    return result;
            }
            return compareStrings( (RuleResult) o );
        } catch ( NumberFormatException ex ) {
            return compareStrings( (RuleResult) o );
        }
    }

    private int compareStrings( RuleResult o ) {
        return this.getName().compareTo( o.getName() );
    }

}
