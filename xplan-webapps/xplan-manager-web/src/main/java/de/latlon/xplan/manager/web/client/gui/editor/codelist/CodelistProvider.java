/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.i18n.CodelistMessages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_50;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_52;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_53;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_54;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_60;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_PlanArt;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Rechtsstand;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.BP_Verfahren;
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
	 * @param codelistType the type of the codelist, may be <code>null</code>
	 * @return a list of {@link Code}s or an empty list. of no codelist is available for
	 * the passed version/codelistType, never <code>null</code>
	 */
	public List<Code> retrieveItems(EditVersion version, CodelistType codelistType) {
		CodelistKey codelistKey = new CodelistKey(version, codelistType);
		if (CODELISTS.containsKey(codelistKey))
			return CODELISTS.get(codelistKey);
		return Collections.emptyList();
	}

	/**
	 * @param version the version of the codelist, may be <code>null</code>
	 * @param codelistType the type of the codelist, may be <code>null</code>
	 * @param codeToTranslate the code to translate
	 * @return the item value of the code, the code as string if no item could be found,
	 * never <code>null</code>
	 */
	public String translate(EditVersion version, CodelistType codelistType, int codeToTranslate) {
		String codeToTranslateAsString = Integer.toString(codeToTranslate);
		List<Code> codes = retrieveItems(version, codelistType);
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
		add_50_XP_RechtscharakterPlanaenderung(codeLists);
		add_51_BP_PlanArt(codeLists);
		add_51_BP_Rechtsstand(codeLists);
		add_51_BP_Verfahren(codeLists);
		add_51_XP_RechtscharakterPlanaenderung(codeLists);
		add_52_BP_PlanArt(codeLists);
		add_52_BP_Rechtsstand(codeLists);
		add_52_BP_Verfahren(codeLists);
		add_52_XP_RechtscharakterPlanaenderung(codeLists);
		add_53_BP_PlanArt(codeLists);
		add_53_BP_Rechtsstand(codeLists);
		add_53_BP_Verfahren(codeLists);
		add_53_XP_RechtscharakterPlanaenderung(codeLists);
		add_54_BP_PlanArt(codeLists);
		add_54_BP_Rechtsstand(codeLists);
		add_54_BP_Verfahren(codeLists);
		add_54_XP_RechtscharakterPlanaenderung(codeLists);
		add_60_BP_PlanArt(codeLists);
		add_60_BP_Rechtsstand(codeLists);
		add_60_BP_Verfahren(codeLists);
		add_60_XP_RechtscharakterPlanaenderung(codeLists);
		return codeLists;
	}

	private static void add_41_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_41, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_41, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_41, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_41_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_41_BP_Verfahren_3000_Parag13a()));
		codeLists.put(key, codes);
	}

	private static void add_41_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_41, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_41_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_50_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_50, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_50, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_50_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_50_BP_Verfahren_3000_Parag13a()));
		codeLists.put(key, codes);
	}

	private static void add_50_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_50, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_50_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_51_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_51, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_51, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_51_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_51_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_51_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_51_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_51, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_51_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_52_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_52, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_52, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_52_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_52_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_52_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_52_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_52, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_52_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codeLists.put(key, codes);
	}

	private static void add_53_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_53, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_53, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_53_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_53_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_53_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_53_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_53, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codes.add(new Code(20000, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_20000_Aufhebungsverfahren()));
		codes.add(new Code(20001, MESSAGES.XPLAN_53_XP_RechtscharakterPlanaenderung_20001_Ueberplanung()));
		codeLists.put(key, codes);
	}

	private static void add_54_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_54, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_54, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_54_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_54_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_54_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_54_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_54, XP_RechtscharakterPlanaenderung);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_1000_Aenderung()));
		codes.add(new Code(1100, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_1100_Ergaenzung()));
		codes.add(new Code(2000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_2000_Aufhebung()));
		codes.add(new Code(20000, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_20000_Aufhebungsverfahren()));
		codes.add(new Code(20001, MESSAGES.XPLAN_54_XP_RechtscharakterPlanaenderung_20001_Ueberplanung()));
		codeLists.put(key, codes);
	}

	private static void add_60_BP_PlanArt(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, BP_PlanArt);
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
		CodelistKey key = new CodelistKey(XPLAN_60, BP_Rechtsstand);
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
		CodelistKey key = new CodelistKey(XPLAN_60, BP_Verfahren);
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(1000, MESSAGES.XPLAN_60_BP_Verfahren_1000_Normal()));
		codes.add(new Code(2000, MESSAGES.XPLAN_60_BP_Verfahren_2000_Parag13()));
		codes.add(new Code(3000, MESSAGES.XPLAN_60_BP_Verfahren_3000_Parag13a()));
		codes.add(new Code(4000, MESSAGES.XPLAN_60_BP_Verfahren_4000_Parag13b()));
		codeLists.put(key, codes);
	}

	private static void add_60_XP_RechtscharakterPlanaenderung(HashMap<CodelistKey, List<Code>> codeLists) {
		CodelistKey key = new CodelistKey(XPLAN_60, XP_RechtscharakterPlanaenderung);
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
