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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.xmlmatchers.transform.XmlConverters;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class XplanSynthesizerXplan50Test extends AbstractXplanSynthesizerTest {

    // Contains broken geometrieS: "xplan50/FPlan.zip",
    @Parameters({ "xplan50/BP2070.zip", "xplan50/BP2135.zip", "xplan50/LA22.zip", "xplan50/LA67.zip" })
    @Test
    public void testCreateSynFeatures( String archiveName )
                    throws Exception {
        XPlanArchive archive = getTestArchive( archiveName );
        XPlanFeatureCollection originalFeatureCollection = readFeatures( archive );
        FeatureCollection synFeatureCollection = createSynFeatures( archive.getVersion(), originalFeatureCollection );

        int numberOfOriginalFeatures = originalFeatureCollection.getFeatures().size();
        int numberOfSynFeatures = synFeatureCollection.size();

        assertThat( numberOfSynFeatures, is( numberOfOriginalFeatures ) );
        String synGml = writeSynFeatureCollection( synFeatureCollection);

        assertThat( the( synGml ),
                    hasXPath( "count(//xplansyn:rechtscharakter[text() = ''])", nsContext(), returningANumber(),
                              is( 0d ) ) );
    }

    @Override
    XPlanVersion getXPlanVersion() {
        return XPLAN_50;
    }

}
