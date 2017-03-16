package de.latlon.xplan.manager.codelists;

import java.util.HashMap;
import java.util.Map;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Instantiates {@link XPlanCodeLists} for different XPlan GML versions.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodeListsFactory {

    private static final String XPLAN_2_CODE_LISTS = "/appschemas/XPlanGML_2_0/XPlanGml_CodeLists.xml";

    private static final String XPLAN_2_EXT_CODE_LISTS = "/appschemas/XPlanGML_2_0/XPlanGml_ExternalCodeLists.xml";

    private static final String XPLAN_3_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_CodeLists.xml";

    private static final String XPLAN_3_EXT_CODE_LISTS = "/appschemas/XPlanGML_3_0/XPlanGML_ExternalCodeLists.xml";

    private static final String XPLAN_40_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_0.xml";

    private static final String XPLAN_41_CODE_LISTS = "/codelists/XPlanGML_Enumerationen_4_1.xml";

    private static final String XPLAN_SYN_CODE_LISTS = "/appschemas/XPlanGML_Syn/XPlanSyn_CodeLists.xml";

    private static final String XPLAN_SYN_EXT_CODE_LISTS_XP2 =
                            "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP2.xml";

    private static final String XPLAN_SYN_EXT_CODE_LISTS_XP3 =
                            "/appschemas/XPlanGML_Syn/XPlanSyn_ExternalCodeLists_XP3.xml";

    private static XPlanCodeLists xplan2CodeLists;

    private static XPlanCodeLists xplan2ExtCodeLists;

    private static XPlanCodeLists xplan3CodeLists;

    private static XPlanCodeLists xplan3ExtCodeLists;

    private static XPlanCodeLists xplanSynCodeLists;

    private static XPlanCodeLists xplanSynExtCodeLists;

    private static XPlanCodeLists xplan40CodeLists;

    private static XPlanCodeLists xplan41CodeLists;

    /**
     * @param version
     *            the version of the XPlanGML, never <code>null</code>
     * @return the {@link XPlanCodeLists} for the specified version, never <code>null</code>
     * @throws IllegalArgumentException
     *             if the version is not supported
     */
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
        default:
            throw new IllegalArgumentException();
        }
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

    private static synchronized XPlanCodeLists getXPlan2() {
        if ( xplan2CodeLists == null ) {
            try {
                xplan2CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_2_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg, e );
            }
        }
        return xplan2CodeLists;
    }

    private static synchronized XPlanCodeLists getXPlan3() {
        if ( xplan3CodeLists == null ) {
            try {
                xplan3CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_3_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg, e );
            }
        }
        return xplan3CodeLists;
    }

    private static synchronized XPlanCodeLists getXPlan40() {
        if ( xplan40CodeLists == null ) {
            try {
                xplan40CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_40_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg, e );
            }
        }
        return xplan40CodeLists;
    }

    private static synchronized XPlanCodeLists getXPlan41() {
        if ( xplan41CodeLists == null ) {
            try {
                xplan41CodeLists = new XPlanCodeLists( XPlanCodeLists.class.getResource( XPLAN_41_CODE_LISTS ) );
            } catch ( Exception e ) {
                String msg = "Internal error reading code lists file: " + e.getMessage();
                throw new RuntimeException( msg, e );
            }
        }
        return xplan41CodeLists;
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