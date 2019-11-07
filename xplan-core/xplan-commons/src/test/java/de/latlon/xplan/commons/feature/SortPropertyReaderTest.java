//$HeadURL$
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
package de.latlon.xplan.commons.feature;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static org.deegree.gml.GMLInputFactory.createGMLStreamReader;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLStreamReader;

import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLStreamReader;
import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortPropertyReaderTest {

    private static final XPlanType TYPE = BP_Plan;

    private static final XPlanVersion VERSION = XPLAN_2;

    @Test
    public void testReadSortDate()
                    throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( "BP_Plan", "technHerstellDatum" );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );
        Date readSortDate = sortPropertyReader.readSortDate( TYPE, VERSION, readFeatureCollection() );

        assertThat( readSortDate, is( asDate( "2001-08-06" ) ) );
    }

    @Test
    public void testReadSortDate_UnmatchingType()
                    throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( "BP_Plan", "technHerstellDatum" );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );
        Date readSortDate = sortPropertyReader.readSortDate( FP_Plan, VERSION, readFeatureCollection() );

        assertThat( readSortDate, nullValue() );
    }

    @Test
    public void testReadSortDate_UnmatchingVersion()
                    throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( "BP_Plan", "technHerstellDatum" );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );
        Date readSortDate = sortPropertyReader.readSortDate( TYPE, XPLAN_3, readFeatureCollection() );

        assertThat( readSortDate, nullValue() );
    }

    @Test
    public void testReadSortDate_UnavailableFeatureType()
                    throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( "BP_PlanNotThere", "technHerstellDatum" );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );
        Date readSortDate = sortPropertyReader.readSortDate( TYPE, XPLAN_3, readFeatureCollection() );

        assertThat( readSortDate, nullValue() );
    }

    @Test
    public void testReadSortDate_UnavailableProperty()
                    throws Exception {
        SortConfiguration sortConfiguration = createSortConfiguration( "BP_Plan", "notThereDatum" );
        SortPropertyReader sortPropertyReader = new SortPropertyReader( sortConfiguration );
        Date readSortDate = sortPropertyReader.readSortDate( TYPE, XPLAN_3, readFeatureCollection() );

        assertThat( readSortDate, nullValue() );
    }

    @Test(expected = NullPointerException.class)
    public void testReadSortDate_NullSortConfiguration()
                    throws Exception {
        new SortPropertyReader( null );
    }

    private SortConfiguration createSortConfiguration( String featureType, String propertyName ) {
        SortConfiguration mockedSortConfig = mock( SortConfiguration.class );
        when( mockedSortConfig.retrieveFeatureType( TYPE, VERSION ) ).thenReturn( featureType );
        when( mockedSortConfig.retrievePropertyName( TYPE, VERSION ) ).thenReturn( propertyName );
        return mockedSortConfig;
    }

    private FeatureCollection readFeatureCollection()
                    throws Exception {
        String name = "xplan2/BP2070.zip";
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
        XPlanVersion version = archive.getVersion();
        XPlanAde ade = archive.getAde();
        XMLStreamReader xmlReader = archive.getMainFileXmlReader();
        GMLStreamReader gmlReader = createGMLStreamReader( version.getGmlVersion(), xmlReader );
        gmlReader.setApplicationSchema( XPlanSchemas.getInstance().getAppSchema( version, ade ) );
        FeatureCollection fc = gmlReader.readFeatureCollection();
        gmlReader.getIdContext().resolveLocalRefs();
        gmlReader.close();
        xmlReader.close();
        return fc;
    }

    private Date asDate( String string )
                    throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
        return simpleDateFormat.parse( string );
    }
}