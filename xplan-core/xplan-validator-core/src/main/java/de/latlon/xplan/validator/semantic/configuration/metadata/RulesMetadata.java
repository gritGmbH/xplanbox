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
package de.latlon.xplan.validator.semantic.configuration.metadata;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesMetadata {

    private static final String UNKNOWN = "unbekannt";

    private final String version;

    private final String source;

    public RulesMetadata() {
        this( null, null );
    }

    /**
     * @param version
     *                         the version of the rules, may be <code>null</code> if not known
     * @param source
     *                         the source of the rules, may be <code>null</code> if not known
     */
    public RulesMetadata( String version, String source ) {
        this.version = StringUtils.isEmpty( version ) ? UNKNOWN : version;
        this.source = StringUtils.isEmpty( source ) ? UNKNOWN : source;
    }

    /**
     * @return the version of the rules, 'unbekannt' if not known, never <code>null</code>
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the source of the rules, 'unbekannt' if not known, never <code>null</code>
     */
    public String getSource() {
        return source;
    }

}
