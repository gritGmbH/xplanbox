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

    private Dictionary codeLists;

    // for each code list: key: code, value: description
    private Map<String, Map<String, String>> codeListIdToMapping = new TreeMap<String, Map<String, String>>();

    // for each code list: key: description, value: code
    private Map<String, Map<String, String>> codeListIdToReverseMapping = new TreeMap<String, Map<String, String>>();

    /**
     * Erzeugt eine neue {@link XPlanCodeLists} Instanz ohne Einträge.
     */
    XPlanCodeLists() {
        // nothing to do
    }

    /**
     * Erzeugt eine neue {@link XPlanCodeLists} Instanz, die durch das Einlesen des spezifizierten GML Dictionaries
     * initialisiert wird.
     *
     * @param codeListUrl
     * @throws XMLStreamException
     * @throws FactoryConfigurationError
     * @throws IOException
     */
    XPlanCodeLists( URL codeListUrl ) throws XMLStreamException, FactoryConfigurationError, IOException {

        GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader( GML_30, codeListUrl );
        codeLists = gmlStream.readDictionary();
        gmlStream.close();

        for ( Definition codeListDef : codeLists ) {
            Dictionary codeListDict = (Dictionary) codeListDef;
            String codeListId = codeListDict.getId();
            if ( codeListIdToMapping.get( codeListId ) != null ) {
                String msg = "CodeList '" + codeListId + "' ist in Dictionary '" + codeListUrl + "' doppelt vorhanden.";
                throw new RuntimeException( msg );
            }

            if ( codeListDict.isEmpty() ) {
                codeListIdToMapping.put( codeListId, new HashMap<String, String>() );
                codeListIdToReverseMapping.put( codeListId, new HashMap<String, String>() );
            }

            for ( Definition def : codeListDict ) {
                if ( def.getNames().length == 1 ) {
                    if ( def.getDescription() != null ) {
                        String description = def.getDescription().getString();
                        if ( def.getNames() == null || def.getNames().length != 1 ) {
                            String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
                                         + "' definiert mehrerere Codes für '" + description + "'.";
                            throw new RuntimeException( msg );
                        }
                        addCodeAndDescription( codeListId, def.getNames()[0].getCode(), description );
                    } else {
                        // no description -> treat
                        addCodeAndDescription( codeListId, def.getNames()[0].getCode(), def.getNames()[0].getCode() );
                    }
                } else {
                    String msg = "CodeList '" + codeListId + "' in Dictionary '" + codeListUrl
                                 + "' enthält Einträge mit keinem oder mehreren Codes.";
                    throw new RuntimeException( msg );
                }
            }
        }
    }

    void addCodeAndDescription( String codeListId, String code, String description ) {
        Map<String, String> codeToDesc = codeListIdToMapping.get( codeListId );
        Map<String, String> descToCode = codeListIdToReverseMapping.get( codeListId );
        if ( codeToDesc == null ) {
            codeToDesc = new HashMap<String, String>();
            codeListIdToMapping.put( codeListId, codeToDesc );
            descToCode = new HashMap<String, String>();
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
