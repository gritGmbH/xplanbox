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
package de.latlon.xplan.manager.web.client.gui.editor.codelist;

import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_PlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Rechtsstand;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Verfahren;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.XP_RechtscharakterPlanaenderung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.i18n.CodelistMessages;

/**
 * Provides access to code list items (mapping between code and value)
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class CodelistProvider {

    private static final CodelistMessages MESSAGES = GWT.create( CodelistMessages.class );

    private static final Map<CodelistKey, List<Code>> CODELISTS = initCodeLists();

    /**
     * @param version
     *            the version of the codelist, may be <code>null</code>
     * @param codelistType
     *            the type of the codelist, may be <code>null</code>
     * @return a list of {@link Code}s or an empty list. of no codelist is available for the passed
     *         version/codelistType, never <code>null</code>
     */
    public List<Code> retrieveItems( EditVersion version, CodelistType codelistType ) {
        CodelistKey codelistKey = new CodelistKey( version, codelistType );
        if ( CODELISTS.containsKey( codelistKey ) )
            return CODELISTS.get( codelistKey );
        return Collections.emptyList();
    }

    /**
     * @param version
     *            the version of the codelist, may be <code>null</code>
     * @param codelistType
     *            the type of the codelist, may be <code>null</code>
     * @param codeToTranslate
     *            the code to translate
     * @return the item value of the code, the code as string if no item could be found, never <code>null</code>
     */
    public String translate( EditVersion version, CodelistType codelistType, int codeToTranslate ) {
        String codeToTranslateAsString = Integer.toString( codeToTranslate );
        List<Code> codes = retrieveItems( version, codelistType );
        for ( Code code : codes ) {
            if ( code.getCode().equals( codeToTranslateAsString ) )
                return code.getItem();
        }
        return codeToTranslateAsString;
    }

    private static HashMap<CodelistKey, List<Code>> initCodeLists() {
        HashMap<CodelistKey, List<Code>> codeLists = new HashMap<CodelistKey, List<Code>>();
        add_30_BP_PlanArt( codeLists );
        add_30_BP_Rechtsstand( codeLists );
        add_41_BP_PlanArt( codeLists );
        add_41_BP_Rechtsstand( codeLists );
        add_41_BP_Verfahren( codeLists );
        add_41_XP_RechtscharakterPlanaenderung( codeLists );
        return codeLists;
    }

    private static void add_30_BP_PlanArt( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_3, BP_PlanArt );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_3_BP_PlanArt_1000_BPlan() ) );
        codes.add( new Code( 2000, MESSAGES.XPLAN_3_BP_PlanArt_2000_BPlanNachParag13() ) );
        codes.add( new Code( 3000, MESSAGES.XPLAN_3_BP_PlanArt_3000_VorhabenbezogenerBPlan() ) );
        codes.add( new Code( 4000, MESSAGES.XPLAN_3_BP_PlanArt_4000_InnenbereichsSatzung() ) );
        codes.add( new Code( 40000, MESSAGES.XPLAN_3_BP_PlanArt_40000_KlarstellungsSatzung() ) );
        codes.add( new Code( 40001, MESSAGES.XPLAN_3_BP_PlanArt_40001_EntwicklungsSatzung() ) );
        codes.add( new Code( 40002, MESSAGES.XPLAN_3_BP_PlanArt_40002_ErgaenzungsSatzung() ) );
        codes.add( new Code( 5000, MESSAGES.XPLAN_3_BP_PlanArt_5000_AussenbereichsSatzung() ) );
        codes.add( new Code( 6000, MESSAGES.XPLAN_3_BP_PlanArt_6000_BPlan_Innenentwicklung() ) );
        codes.add( new Code( 7000, MESSAGES.XPLAN_3_BP_PlanArt_7000_OertlicheBauvorschrift() ) );
        codes.add( new Code( 9999, MESSAGES.XPLAN_3_BP_PlanArt_9999_Sonstiges() ) );
        codeLists.put( key, codes );
    }

    private static void add_30_BP_Rechtsstand( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_3, BP_Rechtsstand );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_3_BP_Rechtsstand_1000_Aufstellungsbeschluss() ) );
        codes.add( new Code( 2000, MESSAGES.XPLAN_3_BP_Rechtsstand_2000_Entwurf() ) );
        codes.add( new Code( 3000, MESSAGES.XPLAN_3_BP_Rechtsstand_3000_Satzung() ) );
        codes.add( new Code( 4000, MESSAGES.XPLAN_3_BP_Rechtsstand_4000_Rechtskraft() ) );
        codes.add( new Code( 5000, MESSAGES.XPLAN_3_BP_Rechtsstand_5000_Untergegangen() ) );
        codeLists.put( key, codes );
    }

    private static void add_41_BP_PlanArt( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_41, BP_PlanArt );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_41_BP_PlanArt_1000_BPlan() ) );
        codes.add( new Code( 10000, MESSAGES.XPLAN_41_BP_PlanArt_10000_EinfacherBPlan() ) );
        codes.add( new Code( 10001, MESSAGES.XPLAN_41_BP_PlanArt_10001_QualifizierterBPlan() ) );
        codes.add( new Code( 3000, MESSAGES.XPLAN_41_BP_PlanArt_3000_VorhabenbezogenerBPlan() ) );
        codes.add( new Code( 4000, MESSAGES.XPLAN_41_BP_PlanArt_4000_InnenbereichsSatzung() ) );
        codes.add( new Code( 40000, MESSAGES.XPLAN_41_BP_PlanArt_40000_KlarstellungsSatzung() ) );
        codes.add( new Code( 40001, MESSAGES.XPLAN_41_BP_PlanArt_40001_EntwicklungsSatzung() ) );
        codes.add( new Code( 40002, MESSAGES.XPLAN_41_BP_PlanArt_40002_ErgaenzungsSatzung() ) );
        codes.add( new Code( 5000, MESSAGES.XPLAN_41_BP_PlanArt_5000_AussenbereichsSatzung() ) );
        codes.add( new Code( 7000, MESSAGES.XPLAN_41_BP_PlanArt_7000_OertlicheBauvorschrift() ) );
        codes.add( new Code( 9999, MESSAGES.XPLAN_41_BP_PlanArt_9999_Sonstiges() ) );
        codeLists.put( key, codes );
    }

    private static void add_41_BP_Rechtsstand( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_41, BP_Rechtsstand );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_41_BP_Rechtsstand_1000_Aufstellungsbeschluss() ) );
        codes.add( new Code( 2000, MESSAGES.XPLAN_41_BP_Rechtsstand_2000_Entwurf() ) );
        codes.add( new Code( 2100, MESSAGES.XPLAN_41_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung() ) );
        codes.add( new Code( 2200, MESSAGES.XPLAN_41_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung() ) );
        codes.add( new Code( 2300, MESSAGES.XPLAN_41_BP_Rechtsstand_2300_BehoerdenBeteiligung() ) );
        codes.add( new Code( 2400, MESSAGES.XPLAN_41_BP_Rechtsstand_2400_OeffentlicheAuslegung() ) );
        codes.add( new Code( 3000, MESSAGES.XPLAN_41_BP_Rechtsstand_3000_Satzung() ) );
        codes.add( new Code( 4000, MESSAGES.XPLAN_41_BP_Rechtsstand_4000_InkraftGetreten() ) );
        codes.add( new Code( 4500, MESSAGES.XPLAN_41_BP_Rechtsstand_4500_TeilweiseUntergegangen() ) );
        codes.add( new Code( 5000, MESSAGES.XPLAN_41_BP_Rechtsstand_5000_Untergegangen() ) );
        codeLists.put( key, codes );
    }

    private static void add_41_BP_Verfahren( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_41, BP_Verfahren );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_41_BP_Verfahren_1000_Normal() ) );
        codes.add( new Code( 2000, MESSAGES.XPLAN_41_BP_Verfahren_2000_Parag13() ) );
        codes.add( new Code( 3000, MESSAGES.XPLAN_41_BP_Verfahren_3000_Parag13a() ) );
        codeLists.put( key, codes );
    }

    private static void add_41_XP_RechtscharakterPlanaenderung( HashMap<CodelistKey, List<Code>> codeLists ) {
        CodelistKey key = new CodelistKey( XPLAN_41, XP_RechtscharakterPlanaenderung );
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( 1000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1000_Aenderung() ) );
        codes.add( new Code( 1100, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1100_Ergaenzung() ) );
        codes.add( new Code( 2000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_2000_Aufhebung() ) );
        codeLists.put( key, codes );
    }

}