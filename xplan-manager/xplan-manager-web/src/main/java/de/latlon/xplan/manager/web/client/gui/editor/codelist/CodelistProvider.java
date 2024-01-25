/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.web.client.gui.editor.codelist;

import com.google.gwt.core.client.GWT;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.i18n.CodelistMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.BP_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.FP_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.LP_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.RP_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditPlanType.SO_Plan;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_50;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_52;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_53;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_54;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.PlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.Rechtsstand;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.Verfahren;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.XP_RechtscharakterPlanaenderung;

/**
 * Provides access to code list items (mapping between code and value)
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class CodelistProvider {

	private static final CodelistMessages MESSAGES = GWT.create(CodelistMessages.class);

	private static final Map<CodelistKey, List<Code>> CODELISTS = initCodeLists();

	/**
	 * @param version the version of the codelist, may be <code>null</code>
	 * @param planType the plan type of the codelist, may be <code>null</code>
	 * @param codelistType the type of the codelist, may be <code>null</code>
	 * @return a list of {@link Code}s or an empty list. of no codelist is available for
	 * the passed version/codelistType, never <code>null</code>
	 */
	public List<Code> retrieveItems(EditVersion version, EditPlanType planType, CodelistType codelistType) {
		CodelistKey codelistKey = new CodelistKey(version, planType, codelistType);
		if (CODELISTS.containsKey(codelistKey))
			return CODELISTS.get(codelistKey);
		return Collections.emptyList();
	}

	/**
	 * @param version the version of the codelist, may be <code>null</code>
	 * @param planType the plan type of the codelist, may be <code>null</code>
	 * @param codelistType the type of the codelist, may be <code>null</code>
	 * @param codeToTranslate the code to translate
	 * @return the item value of the code, the code as string if no item could be found,
	 * never <code>null</code>
	 */
	public String translate(EditVersion version, EditPlanType planType, CodelistType codelistType,
			int codeToTranslate) {
		String codeToTranslateAsString = Integer.toString(codeToTranslate);
		List<Code> codes = retrieveItems(version, planType, codelistType);
		for (Code code : codes) {
			if (code.getCode().equals(codeToTranslateAsString))
				return code.getItem();
		}
		return codeToTranslateAsString;
	}

	private static HashMap<CodelistKey, List<Code>> initCodeLists() {
		HashMap<CodelistKey, List<Code>> codeLists = new HashMap<CodelistKey, List<Code>>();
		add_41_BP_PlanArt(codeLists);
		add_41_BP_Rechtsstand(codeLists);
		add_41_BP_Verfahren(codeLists);
		add_41_XP_RechtscharakterPlanaenderung(codeLists);
		add_50_BP_PlanArt(codeLists);
		add_50_BP_Rechtsstand(codeLists);
		add_50_BP_Verfahren(codeLists);
		add_50_FP_PlanArt(codeLists);
		add_50_FP_Rechtsstand(codeLists);
		add_50_FP_Verfahren(codeLists);
		add_50_RP_PlanArt(codeLists);
		add_50_RP_Rechtsstand(codeLists);
		add_50_RP_Verfahren(codeLists);
		add_50_XP_RechtscharakterPlanaenderung(codeLists);
		add_51_BP_PlanArt(codeLists);
		add_51_BP_Rechtsstand(codeLists);
		add_51_BP_Verfahren(codeLists);
		add_51_FP_PlanArt(codeLists);
		add_51_FP_Rechtsstand(codeLists);
		add_51_FP_Verfahren(codeLists);
		add_51_RP_PlanArt(codeLists);
		add_51_RP_Rechtsstand(codeLists);
		add_51_RP_Verfahren(codeLists);
		add_51_XP_RechtscharakterPlanaenderung(codeLists);
		add_52_BP_PlanArt(codeLists);
		add_52_BP_Rechtsstand(codeLists);
		add_52_BP_Verfahren(codeLists);
		add_52_FP_PlanArt(codeLists);
		add_52_FP_Rechtsstand(codeLists);
		add_52_FP_Verfahren(codeLists);
		add_52_RP_PlanArt(codeLists);
		add_52_RP_Rechtsstand(codeLists);
		add_52_RP_Verfahren(codeLists);
		add_52_XP_RechtscharakterPlanaenderung(codeLists);
		add_53_BP_PlanArt(codeLists);
		add_53_BP_Rechtsstand(codeLists);
		add_53_BP_Verfahren(codeLists);
		add_53_FP_PlanArt(codeLists);
		add_53_FP_Rechtsstand(codeLists);
		add_53_FP_Verfahren(codeLists);
		add_53_RP_PlanArt(codeLists);
		add_53_RP_Rechtsstand(codeLists);
		add_53_RP_Verfahren(codeLists);
		add_53_XP_RechtscharakterPlanaenderung(codeLists);
		add_54_BP_PlanArt(codeLists);
		add_54_BP_Rechtsstand(codeLists);
		add_54_BP_Verfahren(codeLists);
		add_54_FP_PlanArt(codeLists);
		add_54_FP_Rechtsstand(codeLists);
		add_54_FP_Verfahren(codeLists);
		add_54_RP_PlanArt(codeLists);
		add_54_RP_Rechtsstand(codeLists);
		add_54_RP_Verfahren(codeLists);
		add_54_XP_RechtscharakterPlanaenderung(codeLists);
		add_60_BP_PlanArt(codeLists);
		add_60_BP_Rechtsstand(codeLists);
		add_60_BP_Verfahren(codeLists);
		add_60_FP_PlanArt(codeLists);
		add_60_FP_Rechtsstand(codeLists);
		add_60_FP_Verfahren(codeLists);
		add_60_RP_PlanArt(codeLists);
		add_60_RP_Rechtsstand(codeLists);
		add_60_RP_Verfahren(codeLists);
		add_60_LP_PlanArt(codeLists);
		add_60_LP_Rechtsstand(codeLists);
		add_60_XP_RechtscharakterPlanaenderung(codeLists);
		return codeLists;
	}

	private static void add_41_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_41, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_41_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_41_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_41_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_41_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_41_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_41_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_41_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_41_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_41_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_41_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_41_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_41, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_41_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_41_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_41_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_41_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_41_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_41_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_41_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_41_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_41_BP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_41_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_41, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_41_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_41_BP_Verfahren_3000_Parag13a()));
		codeLists.put(key, codes);
	}

	private static void add_41_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_41_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_41_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_41_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_41_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_41_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_41_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_41, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_50_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_50_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_50_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_50_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_50_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_50_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_50_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_50_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_50_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_50_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_50_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_50_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_50_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_50_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_50_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_50_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_50_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_50_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_50_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_50_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_50_BP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_50_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_50_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_50_BP_Verfahren_3000_Parag13a()));
		codeLists.put(key, codes);
	}

	private static void add_50_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_50_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_50_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_50_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_50_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_50_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codeLists.put(key, codes);
	}

	private static void add_50_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_50_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_50_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_50_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_50_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_50_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_50_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_50, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_51_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_51_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_51_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_51_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_51_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_51_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_51_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_51_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_51_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_51_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_51_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_51_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_51_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_51_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_51_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_51_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_51_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_51_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_51_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_51_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_51_BP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_51_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_51_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_51_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_51_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_51_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_51_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_51_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_51_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_51_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_51_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codeLists.put(key, codes);
	}

	private static void add_51_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_51_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_51_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_51_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_51_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_51_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_51_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_51, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_52_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_52_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_52_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_52_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_52_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_52_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_52_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_52_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_52_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_52_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_52_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_52_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_52_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_52_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_52_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_52_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_52_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_52_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_52_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_52_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_52_BP_Rechtsstand_5000_Untergegangen()));
		codeLists.put(key, codes);
	}

	private static void add_52_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_52_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_52_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_52_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_52_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_52_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_FP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_FP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_52_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_52_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_52_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_52_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Verfahren_6000_Neubekanntmachung()));
		codeLists.put(key, codes);
	}

	private static void add_52_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_52_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_52_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_52_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_52_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_52_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_52_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_52, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_53_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_53_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_53_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_53_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(3100, MESSAGES.XPLAN_53_BP_PlanArt_3001_VorhabenUndErschliessungsplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_53_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_53_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_53_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_53_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_53_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_53_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_53_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_53_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_53_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_53_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_53_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_53_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_53_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_53_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_53_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_53_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_53_BP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_53_BP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_53_BP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_53_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_53_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_53_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_53_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_53_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_53_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_FP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_FP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_53_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_53_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_53_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_53_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Verfahren_6000_Neubekanntmachung()));
		codeLists.put(key, codes);
	}

	private static void add_53_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_53_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_53_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_53_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_53_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_53_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_53_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_53, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codes.add(new Code(20000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_20000_Aufhebungsverfahren()));
		codes.add(new Code(20001, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_20001_Ueberplanung()));
		codeLists.put(key, codes);
	}

	private static void add_54_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_54_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_54_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(10002, MESSAGES.XPLAN_54_BP_PlanArt_10002_BebauungsplanZurWohnraumversorgung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_54_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(3100, MESSAGES.XPLAN_54_BP_PlanArt_3001_VorhabenUndErschliessungsplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_54_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_54_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_54_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_54_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_54_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_54_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_54_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_54_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_54_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_54_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_54_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_54_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_54_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_54_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_54_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_54_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(5000, MESSAGES.XPLAN_54_BP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_54_BP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_54_BP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_54_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_54_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_54_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_54_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_54_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_54_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2300, MESSAGES.XPLAN_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_FP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_FP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_54_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_54_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_54_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_54_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Verfahren_6000_Neubekanntmachung()));
		codeLists.put(key, codes);
	}

	private static void add_54_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_54_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_54_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_54_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_54_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_54_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_54_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_54, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codes.add(new Code(20000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_20000_Aufhebungsverfahren()));
		codes.add(new Code(20001, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_20001_Ueberplanung()));
		codeLists.put(key, codes);
	}

	private static void add_60_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, BP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_BP_PlanArt_1000_BPlan()));
		codes.add(new Code(10000, MESSAGES.XPLAN_60_BP_PlanArt_10000_EinfacherBPlan()));
		codes.add(new Code(10001, MESSAGES.XPLAN_60_BP_PlanArt_10001_QualifizierterBPlan()));
		codes.add(new Code(10002, MESSAGES.XPLAN_60_BP_PlanArt_10002_BebauungsplanZurWohnraumversorgung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_BP_PlanArt_3000_VorhabenbezogenerBPlan()));
		codes.add(new Code(3100, MESSAGES.XPLAN_60_BP_PlanArt_3001_VorhabenUndErschliessungsplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_60_BP_PlanArt_4000_InnenbereichsSatzung()));
		codes.add(new Code(40000, MESSAGES.XPLAN_60_BP_PlanArt_40000_KlarstellungsSatzung()));
		codes.add(new Code(40001, MESSAGES.XPLAN_60_BP_PlanArt_40001_EntwicklungsSatzung()));
		codes.add(new Code(40002, MESSAGES.XPLAN_60_BP_PlanArt_40002_ErgaenzungsSatzung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_60_BP_PlanArt_5000_AussenbereichsSatzung()));
		codes.add(new Code(7000, MESSAGES.XPLAN_60_BP_PlanArt_7000_OertlicheBauvorschrift()));
		codes.add(new Code(9999, MESSAGES.XPLAN_60_BP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_60_BP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, BP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_BP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_60_BP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2100, MESSAGES.XPLAN_60_BP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_60_BP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2250, MESSAGES.XPLAN_60_BP_Rechtsstand_2250_Entwurfsbeschluss()));
		codes.add(new Code(2300, MESSAGES.XPLAN_60_BP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_60_BP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_BP_Rechtsstand_3000_Satzung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_60_BP_Rechtsstand_4000_InkraftGetreten()));
		codes.add(new Code(4500, MESSAGES.XPLAN_60_BP_Rechtsstand_4500_TeilweiseUntergegangen()));
		codes.add(new Code(45000, MESSAGES.XPLAN_60_BP_Rechtsstand_45000_TeilweiseAufgehoben()));
		codes.add(new Code(45001, MESSAGES.XPLAN_60_BP_Rechtsstand_45001_TeilweiseAusserKraft()));
		codes.add(new Code(5000, MESSAGES.XPLAN_60_BP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_60_BP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_60_BP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_60_BP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, BP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_60_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_60_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_60_FP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, FP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_PlanArt_1000_FPlan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_PlanArt_2000_GemeinsamerFPlan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_FP_PlanArt_3000_RegFPlan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_FP_PlanArt_4000_FPlanRegPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_FP_PlanArt_5000_SachlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_FP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_60_FP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, FP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_FP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_60_FP_Rechtsstand_2000_ImVerfahren()));
		codes.add(new Code(2100, MESSAGES.XPLAN_60_FP_Rechtsstand_2100_FruehzeitigeBehoerdenBeteiligung()));
		codes.add(new Code(2200, MESSAGES.XPLAN_60_FP_Rechtsstand_2200_FruehzeitigeOeffentlichkeitsBeteiligung()));
		codes.add(new Code(2250, MESSAGES.XPLAN_60_FP_Rechtsstand_2250_Entwurfsbeschluss()));
		codes.add(new Code(2300, MESSAGES.XPLAN_60_FP_Rechtsstand_2300_BehoerdenBeteiligung()));
		codes.add(new Code(2400, MESSAGES.XPLAN_60_FP_Rechtsstand_2400_OeffentlicheAuslegung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_FP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_60_FP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_60_FP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(50000, MESSAGES.XPLAN_60_FP_Rechtsstand_50000_Aufgehoben()));
		codes.add(new Code(50001, MESSAGES.XPLAN_60_FP_Rechtsstand_50001_AusserKraft()));
		codeLists.put(key, codes);
	}

	private static void add_60_FP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, FP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_FP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_FP_Verfahren_2000_Parag13()));
		codeLists.put(key, codes);
	}

	private static void add_60_RP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, RP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_PlanArt_1000_Regionalplan()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_PlanArt_2000_SachlicherTeilplanRegionalebene()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_PlanArt_2001_SachlicherTeilplanLandesebene()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_PlanArt_3000_Braunkohlenplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_PlanArt_4000_LandesweiterRaumordnungsplan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_StandortkonzeptBund()));
		codes.add(new Code(5001, MESSAGES.XPLAN_RP_PlanArt_5001_AWZPlan()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_PlanArt_5000_RaeumlicherTeilplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_RP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_60_RP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, RP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(2001, MESSAGES.XPLAN_RP_Rechtsstand_2001_EntwurfGenehmigt()));
		codes.add(new Code(2002, MESSAGES.XPLAN_RP_Rechtsstand_2002_EntwurfGeaendert()));
		codes.add(new Code(2003, MESSAGES.XPLAN_RP_Rechtsstand_2003_EntwurfAufgegeben()));
		codes.add(new Code(2004, MESSAGES.XPLAN_RP_Rechtsstand_2004_EntwurfRuht()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Rechtsstand_4000_Inkraftgetreten()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Rechtsstand_5000_AllgemeinePlanungsabsicht()));
		codes.add(new Code(5500, MESSAGES.XPLAN_RP_Rechtsstand_5500_TeilweiseAusserKraft()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Rechtsstand_6000_AusserKraft()));
		codes.add(new Code(7000, MESSAGES.XPLAN_RP_Rechtsstand_7000_PlanUngueltig()));
		codeLists.put(key, codes);
	}

	private static void add_60_RP_Verfahren(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, RP_Plan, Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_RP_Verfahren_1000_Aenderung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_RP_Verfahren_2000_Teilfortschreibung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_RP_Verfahren_3000_Neuaufstellung()));
		codes.add(new Code(4000, MESSAGES.XPLAN_RP_Verfahren_4000_Gesamtfortschreibung()));
		codes.add(new Code(5000, MESSAGES.XPLAN_RP_Verfahren_5000_Aktualisierung()));
		codes.add(new Code(6000, MESSAGES.XPLAN_RP_Verfahren_6000_Neubekanntmachung()));
		codeLists.put(key, codes);
	}

	private static void add_60_LP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, LP_Plan, PlanArt);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_LP_PlanArt_1000_Landschaftsprogramm()));
		codes.add(new Code(2000, MESSAGES.XPLAN_LP_PlanArt_2000_Landschaftsrahmenplan()));
		codes.add(new Code(3000, MESSAGES.XPLAN_LP_PlanArt_3000_Landschaftsplan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_LP_PlanArt_4000_Gruenordnungsplan()));
		codes.add(new Code(9999, MESSAGES.XPLAN_LP_PlanArt_9999_Sonstiges()));
		codeLists.put(key, codes);
	}

	private static void add_60_LP_Rechtsstand(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, LP_Plan, Rechtsstand);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_LP_Rechtsstand_1000_Aufstellungsbeschluss()));
		codes.add(new Code(2000, MESSAGES.XPLAN_LP_Rechtsstand_2000_Entwurf()));
		codes.add(new Code(3000, MESSAGES.XPLAN_LP_Rechtsstand_3000_Plan()));
		codes.add(new Code(4000, MESSAGES.XPLAN_LP_Rechtsstand_4000_Wirksamkeit()));
		codes.add(new Code(5000, MESSAGES.XPLAN_LP_Rechtsstand_5000_Untergegangen()));
		codes.add(new Code(6000, MESSAGES.XPLAN_LP_Rechtsstand_6000_InFortschreibung()));
		codeLists.put(key, codes);
	}

	private static void add_60_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		add_60_XP_RechtscharakterPlanaenderung(codeLists, BP_Plan);
		add_60_XP_RechtscharakterPlanaenderung(codeLists, LP_Plan);
		add_60_XP_RechtscharakterPlanaenderung(codeLists, FP_Plan);
		add_60_XP_RechtscharakterPlanaenderung(codeLists, RP_Plan);
		add_60_XP_RechtscharakterPlanaenderung(codeLists, SO_Plan);
	}

	private static void add_60_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists,
			EditPlanType planType) {
		CodelistKey key = new CodelistKey(XPLAN_60, planType, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(10000, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_10000_Ersetzung()));
		codes.add(new Code(10001, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_10001_Ergaenzung()));
		codes.add(new Code(10002, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_10002_Streichung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_XP_RechtscharakterPlanaenderung_3000_Ueberplanung()));
		codeLists.put(key, codes);
	}

}
