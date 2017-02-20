package de.latlon.xplan.manager.codelists;

import de.latlon.xplan.commons.XPlanVersion;
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
 * Encapsulates the internal or external code lists for one XPlan schema.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: rubach $
 * @version $Revision: 940 $, $Date: 2010-02-11 15:24:52 +0100 (Do, 11 Feb 2010) $
 */
public class XPlanCodeLists {

    private static final String XPLAN_2_CODE_LISTS = "/appschemas/XPlanGML_2_0/XPlanGml_CodeLists.xml";

    private static final String XPLAN_2_EXT_CODE_LISTS = "/appschemas/XPlanGML_2_0/XPlanGml_ExternalCodeLists.xml";

    private static final String XPLAN_3_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_CodeLists.xml";

    private static final String XPLAN_40_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_0.xml";

    private static final String XPLAN_41_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_1.xml";

    private static final String XPLAN_3_EXT_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_ExternalCodeLists.xml";

    private static final String XPLAN_SYN_CODE_LISTS = "/appschemas/XPlanGML_Syn/XPlanSyn_CodeLists.xml";

    private static final String XPLAN_SYN_EXT_CODE_LISTS_XP3 =
          "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP3.xml";

    private static final String XPLAN_SYN_EXT_CODE_LISTS_XP2 =
          "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP2.xml";

    private static XPlanCodeLists xplan2CodeLists;

    private static XPlanCodeLists xplan2ExtCodeLists;

    private static XPlanCodeLists xplan3CodeLists;

    private static XPlanCodeLists xplan3ExtCodeLists;

    private static XPlanCodeLists xplanSynCodeLists;

    private static XPlanCodeLists xplanSynExtCodeLists;

    private static XPlanCodeLists xplan40CodeLists;

    private static XPlanCodeLists xplan41CodeLists;

    private Dictionary codeLists;

    // for each code list: key: code, value: description
    private Map<String, Map<String, String>> codeListIdToMapping = new TreeMap<String, Map<String, String>>();

    // for each code list: key: description, value: code
    private Map<String, Map<String, String>> codeListIdToReverseMapping = new TreeMap<String, Map<String, String>>();

    /**
     * Erzeugt eine neue {@link XPlanCodeLists} Instanz ohne Einträge.
     */
    public XPlanCodeLists() {
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
    public XPlanCodeLists( URL codeListUrl ) throws XMLStreamException, FactoryConfigurationError, IOException {

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

    public void addCodeAndDescription( String codeListId, String code, String description ) {
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
    public XPlanCodeLists( Map<String, Map<String, String>> codeListIdToMapping,
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
        Map<String, String> codeToDesc = codeListIdToMapping.get( codeListId );
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
        Map<String, String> codeToDesc = codeListIdToReverseMapping.get( codeListId );
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

    @SuppressWarnings("incomplete-switch")
    public static XPlanCodeLists get( XPlanVersion version ) {
        switch ( version ) {
        case XPLAN_2:
            return getXPlan2();
        case XPLAN_3:
            return getXPlan3();
        case XPLAN_40:
            return getXPlan40();
        case XPLAN_41:
            return getXPlan41();
        }
        throw new IllegalArgumentException();
    }

    public static synchronized XPlanCodeLists getXPlan2() {
        if ( xplan2CodeLists == null ) {
            try {
                xplan2CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_2_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan2CodeLists;
    }

    public static synchronized XPlanCodeLists getXPlan3() {
        if ( xplan3CodeLists == null ) {
            try {
                xplan3CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_3_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan3CodeLists;
    }

    public static synchronized XPlanCodeLists getXPlan40() {
        if ( xplan40CodeLists == null ) {
            try {
                xplan40CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_40_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan40CodeLists;
    }

    public static synchronized XPlanCodeLists getXPlan41() {
        if ( xplan41CodeLists == null ) {
            try {
                xplan41CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_41_CODE_LISTS ) );
            } catch ( Exception e ) {
                e.printStackTrace();
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan41CodeLists;
    }

    public static synchronized XPlanCodeLists getXPlanSyn() {
        if ( xplanSynCodeLists == null ) {
            try {
                xplanSynCodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_SYN_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplanSynCodeLists;
    }

    public static synchronized XPlanCodeLists getXPlan2Ext() {
        if ( xplan2ExtCodeLists == null ) {
            try {
                xplan2ExtCodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_2_EXT_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan2ExtCodeLists;
    }

    public static synchronized XPlanCodeLists getXPlan3Ext() {
        if ( xplan3ExtCodeLists == null ) {
            try {
                xplan3ExtCodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_3_EXT_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplan3ExtCodeLists;
    }

    public static synchronized XPlanCodeLists getXPlanSynExt() {
        if ( xplanSynExtCodeLists == null ) {
            try {
                xplanSynExtCodeLists = mergeCodeLists(
                      new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_SYN_EXT_CODE_LISTS_XP3 ) ),
                      new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_SYN_EXT_CODE_LISTS_XP2 ) ) );
            } catch ( Exception e ) {
                String msg = "Fehler in Codelists Datei: " + e.getMessage();
                throw new RuntimeException( msg );
            }
        }
        return xplanSynExtCodeLists;
    }

    private static XPlanCodeLists mergeCodeLists( XPlanCodeLists xplanCodeLists1, XPlanCodeLists xplanCodeLists2 ) {
        Map<String, Map<String, String>> codesToDesc1 = xplanCodeLists1.getCodesToDescriptions();
        Map<String, Map<String, String>> descToCodes1 = xplanCodeLists1.getDescriptionsToCodes();

        Map<String, Map<String, String>> codesToDesc2 = xplanCodeLists2.getCodesToDescriptions();
        Map<String, Map<String, String>> descToCodes2 = xplanCodeLists2.getDescriptionsToCodes();

        Map<String, Map<String, String>> synCodesToDesc = new HashMap<String, Map<String, String>>();
        Map<String, Map<String, String>> synDescToCodes = new HashMap<String, Map<String, String>>();
        for ( String codelistId : codesToDesc1.keySet() ) {
            synCodesToDesc.put( codelistId, codesToDesc1.get( codelistId ) );
            synDescToCodes.put( codelistId, descToCodes1.get( codelistId ) );
        }
        for ( String codelistId : codesToDesc2.keySet() ) {
            synCodesToDesc.put( codelistId, codesToDesc2.get( codelistId ) );
            synDescToCodes.put( codelistId, descToCodes2.get( codelistId ) );
        }

        return new XPlanCodeLists( synCodesToDesc, synDescToCodes );
    }
}
