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

import static org.deegree.gml.GMLVersion.GML_30;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.dictionary.Definition;
import org.deegree.gml.dictionary.Dictionary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encapsulates the internal or external code lists for one XPlan schema.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: rubach $
 * @version $Revision: 940 $, $Date: 2010-02-11 15:24:52 +0100 (Do, 11 Feb 2010) $
 */
public class XPlanCodeLists {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanCodeLists.class );

    // for each code list: key: code, value: description
    private Map<String, Map<String, String>> codeListIdToMapping = new TreeMap<String, Map<String, String>>();

    // for each code list: key: description, value: code
    private Map<String, Map<String, String>> codeListIdToReverseMapping = new TreeMap<String, Map<String, String>>();


    /**
     * @param codeListIdToMapping
     * @param codeListIdToReverseMapping
     */
    XPlanCodeLists( Map<String, Map<String, String>> codeListIdToMapping,
                    Map<String, Map<String, String>> codeListIdToReverseMapping ) {
        this.codeListIdToMapping = codeListIdToMapping;
        this.codeListIdToReverseMapping = codeListIdToReverseMapping;
    }

    // public void checkConsistency( ApplicationSchema schema ) {
    //
    // Set<String> internalCodeTypes = new TreeSet<String>();
    // Set<String> externalCodeTypes = new TreeSet<String>();
    //
    // for ( FeatureType ft : schema.getFeatureTypes() ) {
    // for ( PropertyType<?> prop : ft.getPropertyDeclarations() ) {
    // if ( prop instanceof SimplePropertyType<?> ) {
    // SimplePropertyType<?> simpleProp = (SimplePropertyType<?>) prop;
    // if ( simpleProp.getCodeList() != null ) {
    // if ( !simpleProp.getCodeList().startsWith( "xplan:" ) ) {
    // throw new RuntimeException( "Code list reference '" + simpleProp.getCodeList()
    // + " does not start with 'xplan:'." );
    // }
    // externalCodeTypes.add( simpleProp.getCodeList().substring( 6 ) );
    // } else {
    // QName xsdSimpleType = simpleProp.getXSDTypeName();
    // if ( !"http://www.w3.org/2001/XMLSchema".equals( xsdSimpleType.getNamespaceURI() ) ) {
    // String simpleType = xsdSimpleType.getLocalPart();
    // if ( !simpleType.endsWith( "Type" ) ) {
    // throw new RuntimeException( "Simple type '" + simpleType + " does not end on 'Type'." );
    // }
    // internalCodeTypes.add( simpleType.substring( 0, simpleType.length() - 4 ) );
    // }
    // }
    // }
    // }
    // }
    // System.out.println( "Internal code lists: " + internalCodeTypes.size() );
    // for ( String codeType : internalCodeTypes ) {
    // Dictionary codeList = (Dictionary) codeListsIdContext.getObject( codeType );
    // if ( codeList == null ) {
    // System.out.println( "Keine interne CodeList für code type " + codeType + " gefunden." );
    // throw new RuntimeException();
    // }
    // }
    // System.out.println( "External code lists: " + externalCodeTypes.size() );
    // for ( String codeType : externalCodeTypes ) {
    // Dictionary codeList = (Dictionary) extDictIdContext.getObject( codeType );
    // if ( codeList == null ) {
    // System.out.println( "Keine externe CodeList für code type " + codeType + " gefunden." );
    // throw new RuntimeException();
    // }
    // }
    //
    // for ( String codeListId : codeListIdToCodeList.keySet() ) {
    // if ( !( externalCodeTypes.contains( codeListId ) || internalCodeTypes.contains( codeListId ) ) ) {
    // System.out.println( "Warnung: Externe CodeList " + codeListId + " wird nicht im Schema verwendet." );
    // }
    // }
    // }

    /**
     * @param codeListId
     * @param code
     * @return
     */
    public String getDescription( String codeListId, String code ) {
        String codeListIdToUse = checkCodeListIdForNull( codeListId );
        Map<String, String> codeToDesc = codeListIdToMapping.get( codeListIdToUse );
        if ( codeToDesc == null ) {
            throw new IllegalArgumentException( "Unbekannte CodeList '" + codeListId + "'." );
        }
        String translation = codeToDesc.get( code );
        if ( translation == null ) {
            throw new IllegalArgumentException( "Unbekannter Code '" + code + "'. CodeList '" + codeListId
                                                + "' enthält keinen entsprechenden Eintrag." );
        }
        return translation;
    }

    /**
     * @param codeListId
     * @param description
     * @return
     */
    public String getCode( String codeListId, String description ) {
        String codeListIdToUse = checkCodeListIdForNull( codeListId );
        Map<String, String> codeToDesc = codeListIdToReverseMapping.get( codeListIdToUse );
        if ( codeToDesc == null ) {
            throw new IllegalArgumentException( "Unbekannte CodeList '" + codeListId + "'." );
        }
        String code = codeToDesc.get( description );
        if ( code == null ) {
            throw new IllegalArgumentException( "Unbekannte Description '" + description + "'. CodeList '" + codeListId
                                                + "' enthält keinen entsprechenden Eintrag." );
        }
        return code;
    }

    /**
     * @return key: code list id, value: (key: code, value: description)
     */
    public Map<String, Map<String, String>> getCodesToDescriptions() {
        return codeListIdToMapping;
    }

    /**
     * @return key: code list id, value: (key: description, value: code)
     */
    public Map<String, Map<String, String>> getDescriptionsToCodes() {
        return codeListIdToReverseMapping;
    }

    private String checkCodeListIdForNull( String codeListId ) {
        if ( codeListId != null ) {
            return codeListId;
        }
        if ( codeListIdToMapping.size() == 0 )
            LOG.warn( "Code list is empty!" );
        else if ( codeListIdToMapping.size() == 1 )
            return codeListIdToMapping.keySet().iterator().next();
        else
            LOG.warn( "XPlanCodeLists contains multiple codelists!" );
        return null;
    }

}
