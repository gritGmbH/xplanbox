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
package de.latlon.xplan.manager.codelists;

import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.dictionary.Definition;
import org.deegree.gml.dictionary.Dictionary;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.deegree.gml.GMLVersion.GML_30;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodeListsParser {

    /**
     * Erzeugt eine neue {@link XPlanCodeLists} Instanz, die durch das Einlesen des spezifizierten GML Dictionaries
     * initialisiert wird. Die Codeliste muss in GML 3.0 vorliegen.
     *
     * @param codeListUrl
     * @throws XMLStreamException
     * @throws FactoryConfigurationError
     * @throws IOException
     */
    public XPlanCodeLists parseCodelists( URL codeListUrl )
                            throws XMLStreamException, FactoryConfigurationError, IOException {
        GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader( GML_30, codeListUrl );
        Dictionary codeLists = gmlStream.readDictionary();
        gmlStream.close();

        Map<String, Map<String, String>> codeListIdToMapping = new TreeMap<>();
        Map<String, Map<String, String>> codeListIdToReverseMapping = new TreeMap<>();
        if ( !codeLists.isEmpty() ) {
            Definition firstDefinition = codeLists.get( 0 );
            if ( firstDefinition instanceof Dictionary ) {
                for ( Definition codeListDef : codeLists ) {
                    parseDictionary( codeListUrl, (Dictionary) codeListDef, codeListIdToMapping,
                                     codeListIdToReverseMapping );
                }
            } else {
                parseDictionary( codeListUrl, codeLists, codeListIdToMapping, codeListIdToReverseMapping );
            }
        }
        return new XPlanCodeLists( codeListIdToMapping, codeListIdToReverseMapping );
    }

    private void parseDictionary( URL codeListUrl, Dictionary codeListDef,
                                  Map<String, Map<String, String>> codeListIdToMapping,
                                  Map<String, Map<String, String>> codeListIdToReverseMapping ) {
        Dictionary codeListDict = codeListDef;
        String codeListId = codeListDict.getId();
        if ( codeListIdToMapping.get( codeListId ) != null ) {
            String msg = "CodeList '" + codeListId + "' ist in Dictionary '" + codeListUrl + "' doppelt vorhanden.";
            throw new RuntimeException( msg );
        }

        if ( codeListDict.isEmpty() ) {
            codeListIdToMapping.put( codeListId, new HashMap<>() );
            codeListIdToReverseMapping.put( codeListId, new HashMap<>() );
        }

        for ( Definition def : codeListDict ) {
            parseDefinition( codeListUrl, codeListId, def, codeListIdToMapping, codeListIdToReverseMapping );
        }
    }

    private void parseDefinition( URL codeListUrl, String codeListId, Definition def,
                                  Map<String, Map<String, String>> codeListIdToMapping,
                                  Map<String, Map<String, String>> codeListIdToReverseMapping ) {
        if ( def.getNames().length == 1 ) {
            if ( def.getDescription() != null ) {
                String description = def.getDescription().getString();
                if ( def.getNames() == null || def.getNames().length != 1 ) {
                    String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
                                 + "' definiert mehrerere Codes für '" + description + "'.";
                    throw new RuntimeException( msg );
                }
                addCodeAndDescription( codeListId, def.getNames()[0].getCode(), description, codeListIdToMapping,
                                       codeListIdToReverseMapping );
            } else {
                // no description -> treat
                addCodeAndDescription( codeListId, def.getNames()[0].getCode(), def.getNames()[0].getCode(),
                                       codeListIdToMapping, codeListIdToReverseMapping );
            }
        } else {
            String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
                         + "' enthält Einträge mit keinem oder mehreren Codes.";
            throw new RuntimeException( msg );
        }
    }

    void addCodeAndDescription( String codeListId, String code, String description,
                                Map<String, Map<String, String>> codeListIdToMapping,
                                Map<String, Map<String, String>> codeListIdToReverseMapping ) {
        Map<String, String> codeToDesc = codeListIdToMapping.get( codeListId );
        Map<String, String> descToCode = codeListIdToReverseMapping.get( codeListId );
        if ( codeToDesc == null ) {
            codeToDesc = new HashMap<>();
            codeListIdToMapping.put( codeListId, codeToDesc );
            descToCode = new HashMap<>();
            codeListIdToReverseMapping.put( codeListId, descToCode );
        }
        if ( codeToDesc.get( code ) != null && !description.equals( codeToDesc.get( code ) ) ) {
            String msg = "Cannot add code '" + code + "' with description '" + description + "' to code list '"
                         + codeListId + "' -- list already defines description '" + codeToDesc.get( code )
                         + "' for this code.";
            throw new IllegalArgumentException( msg );
        }
        codeToDesc.put( code, description );
        descToCode.put( description, code );
    }

}
