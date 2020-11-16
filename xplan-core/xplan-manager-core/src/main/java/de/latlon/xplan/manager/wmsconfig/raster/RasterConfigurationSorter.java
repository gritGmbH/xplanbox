/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.wmsconfig.raster;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.deegree.theme.persistence.standard.jaxb.ThemeType.Layer;

/**
 * Sorts raster configurations.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class RasterConfigurationSorter {

    /**
     * Sorts the strings of the passed map by the dates by the following order: First null dates, then the dates in
     * ascending order. if dates are equal, the strings are sorted ascending.
     * 
     * @param unsortedMap
     *            the map with the strings to sort by the date, never <code>null</code>
     * @return the string sorted by the date, never <code>null</code>
     */
    public List<String> sortByDateInDeegreeOrder( Map<String, Date> unsortedMap ) {
        List<Map.Entry<String, Date>> list = new LinkedList<Map.Entry<String, Date>>( unsortedMap.entrySet() );

        sort( list, new IdByDateComparator() );

        List<String> sortedList = new ArrayList<>();
        for ( Entry<String, Date> entry : list ) {
            sortedList.add( entry.getKey() );
        }
        return sortedList;
    }

    /**
     * Sorts the passed layer in the same order as the order if the sortedPrefixes. If the sortedPrefixList does not
     * contain a matching entry for a layer, this layer is sorted in the beginning.
     * 
     * @param layers
     *            the layers list to sort, never <code>null</code>
     * @param sortedPrefixList
     *            the list with reference order, never <code>null</code>
     */
    public void sortLayers( List<Layer> layers, List<String> sortedPrefixList ) {
        LayerBySortedPrefixListComparator comparator = new LayerBySortedPrefixListComparator( sortedPrefixList );
        sort( layers, comparator );
    }

    private class IdByDateComparator implements Comparator<Map.Entry<String, Date>> {

        @Override
        public int compare( Map.Entry<String, Date> o1, Map.Entry<String, Date> o2 ) {
            Date value1 = o1.getValue();
            Date value2 = o2.getValue();
            if ( value1 == null ) {
                if ( value2 == null )
                    return o1.getKey().compareTo( o2.getKey() );
                else
                    return -1;
            }
            if ( value2 == null )
                return 1;
            int compareTo = value1.compareTo( value2 );
            if ( compareTo == 0 )
                return o1.getKey().compareTo( o2.getKey() );
            return compareTo;
        }

    }

    private class LayerBySortedPrefixListComparator implements Comparator<Layer> {

        private final List<String> sortedPrefixList;

        public LayerBySortedPrefixListComparator( List<String> sortedPrefixList ) {
            this.sortedPrefixList = sortedPrefixList;
        }

        @Override
        public int compare( Layer o1, Layer o2 ) {
            String value1 = o1.getValue();
            String value2 = o2.getValue();
            int index1 = findIndex( value1 );
            int index2 = findIndex( value2 );
            return Integer.compare( index1, index2   );
        }

        private int findIndex( String id ) {
            for ( String prefix : sortedPrefixList ) {
                if ( id.startsWith( prefix + "_" ) )
                return sortedPrefixList.indexOf( prefix );
            }
            return 1;
        }

    }

}
