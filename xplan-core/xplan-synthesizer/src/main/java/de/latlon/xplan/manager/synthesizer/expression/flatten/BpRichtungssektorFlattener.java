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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class BpRichtungssektorFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode element ) {
        String elName = null;
        if ( element instanceof ElementNode ) {
            elName = ( (ElementNode) element ).getName().getLocalPart();
        }
        return "BP_Richtungssektor".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode bpRichtungssektor ) {
        List<Pair<String, String>> properties = new ArrayList<>();
        append( "Startwinkel", bpRichtungssektor, "winkelAnfang", properties );
        append( "Endwinkel", bpRichtungssektor, "winkelEnde", properties );
        append( "Zusatzkontingent Tag", bpRichtungssektor, "zkWertTag", properties );
        append( "Zusatzkontingent Nacht", bpRichtungssektor, "zkWertNacht", properties );
        return encode( properties );
    }

}
