package de.latlon.xplan.manager.codelists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles the translation of XPlan 2 code list names and codes to their XPlan Syn counterparts.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: schneider $
 * @version $Revision: 703 $, $Date: 2010-01-20 12:26:07 +0100 (Mi, 20 Jan 2010) $
 */
public class XPlanCodeConverter {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanCodeConverter.class );

    // key: name of internal XPlan 2 code list, value: name of corresponding internal XPlan Syn code list
    private static final Map<String, String> xp2ToSynCodeList =
          buildCodeListMapping( XPlanCodeListsFactory.getXPlan2(),
                                "/codelists/xp2codelists.rules" );

    // key: name of external XPlan 2 code list, value: name of corresponding external XPlan Syn code list
    private static final Map<String, String> xp2ToSynCodeListExt =
          buildCodeListMapping( XPlanCodeListsFactory.getXPlan2Ext(),
                                "/codelists/xp2codelists_external.rules" );

    private static Map<String, Map<String, XPlan2CodeTranslation>> xp2CodeListToRules = buildCodeMapping();

    private static Map<String, String> buildCodeListMapping( XPlanCodeLists codeLists, String rulesFile ) {
        Map<String, String> xp2ToSynCodeList = new HashMap<String, String>();

        try ( InputStream is = XPlanCodeConverter.class.getResourceAsStream( rulesFile );
              InputStreamReader in = new InputStreamReader( is, "UTF-8" );
              BufferedReader ruleReader = new BufferedReader( in ) ) {
            
            String line;
            while ( ( line = ruleReader.readLine() ) != null ) {
                line = line.trim();
                if ( !line.isEmpty() && !line.contains( "->" ) && line.contains( "=" ) ) {
                    String[] parts = line.split( "=" );
                    String xplan2 = parts[0];
                    String xplanSyn = parts[1];
                    xp2ToSynCodeList.put( xplan2, xplanSyn );
                    LOG.debug( xplan2 + " -> " + xplanSyn );
                }
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new RuntimeException( "Error loading code list mapping rules: " + e.getMessage() );
        }

        // augment with mappings based on equal names
        for ( String xplan2CodeList : codeLists.getCodesToDescriptions().keySet() ) {
            if ( xp2ToSynCodeList.containsKey( xplan2CodeList ) ) {
                LOG.debug( "Skipping: " + xplan2CodeList );
            } else {
                LOG.debug( xplan2CodeList + " -> " + xplan2CodeList );
                xp2ToSynCodeList.put( xplan2CodeList, xplan2CodeList );
            }
        }
        return xp2ToSynCodeList;
    }

    private static Map<String, Map<String, XPlan2CodeTranslation>> buildCodeMapping() {

        Map<String, Map<String, XPlan2CodeTranslation>> xp2CodeListToRules = new HashMap<String, Map<String, XPlan2CodeTranslation>>();

        // build translation rules from xp2codes.rules
        try {
            LOG.debug( "Lese Spezialregeln für das Mapping der XPlan2 Codes..." );
            InputStream is = XPlanCodeConverter.class.getResourceAsStream( "/codelists/xp2codelists.rules" );
            BufferedReader ruleReader = new BufferedReader( new InputStreamReader( is, "UTF-8" ) );
            String line;
            String xp2CodeListId = null;
            String synCodeListId = null;
            while ( ( line = ruleReader.readLine() ) != null ) {
                line = line.trim();
                if ( !line.isEmpty() ) {
                    if ( !line.contains( "->" ) ) {
                        if ( line.contains( "=" ) ) {
                            String[] parts = line.split( "=" );
                            xp2CodeListId = parts[0];
                            synCodeListId = parts[1];
                        } else {
                            xp2CodeListId = line;
                            synCodeListId = line;
                        }
                    } else {
                        String[] parts = line.split( "->" );
                        String xplan2Desc = parts[0];
                        String xplan2Code = XPlanCodeListsFactory.getXPlan2().getCode( xp2CodeListId, xplan2Desc );
                        String[] xplanSynDesc = parts[1].split( "," );
                        String xplanSynCode;
                        if ( xplanSynDesc[0].startsWith( "XP2_" ) ) {
                            xplanSynCode = xplanSynDesc[0];
                            xplanSynDesc[0] = xplan2Desc;
                        } else {
                            xplanSynCode = XPlanCodeListsFactory.getXPlanSyn().getCode( synCodeListId, xplanSynDesc[0] );
                        }
                        XPlan2CodeTranslation translation;
                        if ( xplanSynDesc.length == 1 ) {
                            translation = new XPlan2CodeTranslation( xp2CodeListId, xplan2Code, synCodeListId,
                                                                     xplanSynCode );
                        } else if ( xplanSynDesc.length == 2 ) {
                            String xplanSynExtRule = xplanSynDesc[1];
                            String[] extRuleTokens = xplanSynExtRule.split( "=" );
                            if ( extRuleTokens.length != 2 ) {
                                // extended rule not valid
                                throw new RuntimeException();
                            }
                            String xplanSynExtCodeList = extRuleTokens[0];
                            String xplanSynExtDesc = extRuleTokens[1];
                            String xplanSynExtCode = XPlanCodeListsFactory.getXPlanSyn().getCode( xplanSynExtCodeList,
                                                                                           xplanSynExtDesc );

                            translation = new XPlan2CodeTranslation( xp2CodeListId, xplan2Code, synCodeListId,
                                                                     xplanSynCode, xplanSynExtCodeList,
                                                                     xplanSynExtCode );
                        } else {
                            // rule not valid
                            throw new RuntimeException();
                        }
                        Map<String, XPlan2CodeTranslation> mapping = xp2CodeListToRules.get( xp2CodeListId );
                        if ( mapping == null ) {
                            mapping = new HashMap<String, XPlan2CodeTranslation>();
                            xp2CodeListToRules.put( xp2CodeListId, mapping );
                        }
                        mapping.put( xplan2Code, translation );
                    }
                }
            }
            ruleReader.close();
        } catch ( Exception e ) {
            e.printStackTrace();
            throw new RuntimeException( "Error reading code rules: " + e.getMessage() );
        }

        // add default translation rules
        for ( String xp2CodeList : xp2ToSynCodeList.keySet() ) {
            Map<String, XPlan2CodeTranslation> mapping = xp2CodeListToRules.get( xp2CodeList );
            if ( mapping == null ) {
                mapping = new HashMap<String, XPlan2CodeTranslation>();
                xp2CodeListToRules.put( xp2CodeList, mapping );
            }

            Map<String, String> xp2CodesAndDesc = XPlanCodeListsFactory.getXPlan2().getCodesToDescriptions()
                  .get( xp2CodeList );
            for ( Entry<String, String> xplan2CodeAndDesc : xp2CodesAndDesc.entrySet() ) {
                String xplan2Code = xplan2CodeAndDesc.getKey();
                String xplan2Desc = xplan2CodeAndDesc.getValue();
                // only add if no special rule applies
                if ( !mapping.containsKey( xplan2Code ) ) {
                    String synCodeListId = xp2ToSynCodeList.get( xp2CodeList );
                    String xplanSynCode = XPlanCodeListsFactory.getXPlanSyn().getCode( synCodeListId, xplan2Desc );
                    String xplanSynDesc = XPlanCodeListsFactory.getXPlanSyn().getDescription( synCodeListId, xplanSynCode );
                    LOG.debug( xplan2Desc + ": " + xplanSynDesc );
                    XPlan2CodeTranslation translation = new XPlan2CodeTranslation( xp2CodeList, xplan2Code,
                                                                                   synCodeListId,
                                                                                   xplanSynCode, null, null );
                    mapping.put( xplan2Code, translation );
                }
            }
        }
        return xp2CodeListToRules;
    }

    /**
     * Returns the corresponding XPlan Syn codes.
     *
     * @param xp2Code
     * @param xp2CodeList
     * @return the corresponding XPlan Syn code, never <code>null</code>
     */
    public static XPlan2CodeTranslation xplan2ToSynCode( String xp2Code, String xp2CodeList ) {
        Map<String, XPlan2CodeTranslation> translationList = xp2CodeListToRules.get( xp2CodeList );
        if ( translationList == null ) {
            throw new IllegalArgumentException( "No XPlan2 code list with id '" + xp2CodeList + "' known." );
        }
        XPlan2CodeTranslation translation = translationList.get( xp2Code );
        if ( translation == null ) {
            throw new IllegalArgumentException( "No XPlan2 code  '" + xp2Code + "' defined in code list '"
                                                + xp2CodeList + "'." );
        }
        return translation;
    }

    /**
     * Returns the name of the corresponding XPlan Syn codelist.
     *
     * @param xp2CodeList
     * @return the corresponding XPlan Syn code, never <code>null</code>
     */
    public static String xplan2ToSynCodeList( String xp2CodeList ) {
        String synCodeList = xp2ToSynCodeList.get( xp2CodeList );
        if ( synCodeList == null ) {
            throw new IllegalArgumentException( "No XPlan2 code list with id '" + xp2CodeList + "' known." );
        }
        return synCodeList;
    }

    /**
     * Returns the name of the corresponding external XPlan Syn codelist.
     *
     * @param xp2CodeList
     * @return the corresponding XPlan Syn code list , never <code>null</code>
     */
    public static String xplan2ToSynCodeListExt( String xp2CodeList ) {
        String synCodeList = xp2ToSynCodeListExt.get( xp2CodeList );
        if ( synCodeList == null ) {
            synCodeList = xp2CodeList;
            throw new IllegalArgumentException( "No external XPlan2 code list with id '" + xp2CodeList + "' known." );
        }
        return synCodeList;
    }
}