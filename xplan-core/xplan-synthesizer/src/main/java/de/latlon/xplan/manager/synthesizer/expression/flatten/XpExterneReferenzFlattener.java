/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

public class XpExterneReferenzFlattener extends AbstractFlattener {

    private final String fid;

    public XpExterneReferenzFlattener( Feature contextFeature ) {
        fid = contextFeature.getId();
    }

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        } else if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_ExterneReferenz".equals( elName ) || "XP_ExterneReferenzPlan".equals( elName ) || "XP_SpezExterneReferenz".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpExterneReferenz ) {
        String s = "";
        TypedObjectNode referenzUrl = getPropertyValue( xpExterneReferenz, "referenzURL" );
        if ( referenzUrl != null ) {
            String refUrlString = referenzUrl.toString();
            if ( refUrlString.contains( ":/" ) ) {
                s += "[" + escape( refUrlString ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + fid + "&filename=" + escape( refUrlString ) + "]";
            }
        }
        TypedObjectNode georefURL = getPropertyValue( xpExterneReferenz, "georefURL" );
        if ( georefURL != null ) {
            String georefUrlString = georefURL.toString();
            if ( georefUrlString.contains( "/:" ) ) {
                s += "[" + escape( georefUrlString ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + fid + "&filename=" + escape( georefUrlString ) + "]";
            }
        }
        return s;
    }
}
