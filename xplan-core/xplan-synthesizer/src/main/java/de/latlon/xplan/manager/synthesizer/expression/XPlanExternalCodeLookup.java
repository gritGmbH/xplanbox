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
package de.latlon.xplan.manager.synthesizer.expression;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.stream.XMLStreamException;

import org.deegree.feature.Feature;
import org.deegree.gml.GMLVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * {@link AbstractXplanCodeLookup} for translating codes from external {@link XPlanCodeLists} to their textual
 * representation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanExternalCodeLookup extends AbstractXplanCodeLookup {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanExternalCodeLookup.class );

    private final String codeListFile;

    private final Path configurationFilePath;

    /**
     *
     * @param exp
     *            to the property to translate, never <code>null</code>
     * @param codeListFile
     *            relative path (from the rule configuration) to the file containing the code list, never
     *            <code>null</code>
     * @param codeListName
     *            the name of the code list to use for the translation, may be <code>null</code> if the codelists
     *            contains only one codelist
     * @param configurationDirectoryPath
     *            the absolute path to the directory containing the rules configuration, never <code>null</code>
     */
    public XPlanExternalCodeLookup( Expression exp, String codeListFile, String codeListName, Path configurationDirectoryPath ) {
        super( exp, codeListName );
        this.codeListFile = codeListFile;
        this.configurationFilePath = configurationDirectoryPath;
    }

    @Override
    protected XPlanCodeLists getXplanCodeLists( Feature feature ) {
        if ( configurationFilePath != null ) {
            Path codeList = configurationFilePath.resolve( codeListFile );
            LOG.info( "Use configured codelist  from {}.", codeList );
            try {
                return XPlanCodeListsFactory.getXPlanCodeLists( codeList.toUri().toURL(), GML_30 );
            } catch ( XMLStreamException e ) {
                LOG.error( "Could not parse code list " + codeList + ". Code will not be translated.", e );
            } catch ( IOException e ) {
                LOG.error( "Could not parse code list " + codeList + ". Code will not be translated.", e );
            }
        }
        return null;
    }

}
