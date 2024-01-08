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
import de.latlon.xplan.manager.web.client.i18n.CodelistMessages;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEKANNTMACHUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BESCHLUSS;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BESCHREIBUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.DURCHFUEHRUNGSVERTRAG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.ERLAEUTERUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.ERSCHLIESSUNGSVERTRAG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GENEHMIGUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUNDSTUECKSVERZEICHNIS;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.INFORMELL;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.KARTE;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.KOORDINATENLISTE;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.LEGENDE;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.METADATENPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.PFLANZLISTE;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.PLANGRUNDLAGE;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSVERBINDLICH;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.SATZUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.SCHUTZGEBIETSVERORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.STAEDTEBAULICHERVERTRAG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.UMWELTBERICHT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.UMWELTBEZOGENESTELLUNGNAHMEN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.VERORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.VORHABENUNDERSCHLIESSUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.ZUSAMMENFASSENDEERKLAERUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_FESTSETZUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.BP_VERMERK;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_DARSTELLLUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FP_VERMERK;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_DARSTELLUNGKENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_FESTSETZUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_FESTSETZUNGINBPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_GEPLANT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_SONSTIGERSTATUS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.LP_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_GRUNDSATZDERRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_NACHRICHTLICHEUEBERNAHMEZIEL;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_NURINFORMATIONSGEHALT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_TEXTLICHESZIEL;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_VORSCHLAG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_ZIELDERRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.RP_ZIELUNDGRUNDSATZ;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_DARSTELLUNGFPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_FESTSETZUNGBPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_INHALTLPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_SONSTIGES;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.SO_VERMERK;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_DARSTELLUNGFPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_DARSTELLUNGKENNZEICHNUNGIMLP;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_FESTSETZUNGBPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_FESTSETZUNGIMLP;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_GEPLANTEFESTSETZUNGIMLP;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_GRUNDSATZDERRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_LANDSCHAFTSPLANUNGSINHALTZURBERUECKSICHTIGUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_NACHRICHTLICHEUEBERNAHMEZIEL;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_NURINFORMATIONSGEHALTRPLAN;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_SONSTIGES;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_TEXTLICHESZIELRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_VERMERK;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_VORSCHLAGRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_ZIELDERRAUMORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.XP_ZIELUNDGRUNDSATZRAUMORDNUNG;

/**
 * Provides access to type code (mapping between code and value)
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public final class TypeCodelistProvider {

	private static final CodelistMessages MESSAGES = GWT.create(CodelistMessages.class);

	private static final Map<Class<?>, List<Code>> TYPECODES = initCodeLists();

	/**
	 * @param enumClass the enumeration the value is part of, never <code>null</code>
	 * @param enumValue the value to retrieve the item for, may be <code>null</code>
	 * @return the item value of the {@link Code} where the code value equals the
	 * enumValue, if no codelist and/or code could be found enumValue.name() is returned
	 */
	public <T extends Enum<T>> String translate(Class<T> enumClass, T enumValue) {
		if (enumValue == null)
			return null;
		List<Code> codes = TYPECODES.get(enumClass);
		if (codes != null) {
			for (Code code : codes) {
				if (code.getCode().equals(enumValue.name()))
					return code.getItem();
			}
		}
		return enumValue.name();
	}

	/**
	 * @param enumClass the enumeration to retrieve as {@link Code}s
	 * @return a list of {@link Code}s or an empty list. of no codelist is available for
	 * the passed enumeration, never <code>null</code>
	 */
	public <T extends Enum<T>> List<Code> retrieveItems(Class<T> enumClass) {
		if (TYPECODES.containsKey(enumClass))
			return TYPECODES.get(enumClass);
		return Collections.emptyList();
	}

	private static Map<Class<?>, List<Code>> initCodeLists() {
		HashMap<Class<?>, List<Code>> typeCode = new HashMap<Class<?>, List<Code>>();
		addChangeType(typeCode);
		addReferenceType(typeCode);
		addRasterReferenceType(typeCode);
		addExterneReferenzArtType(typeCode);
		addMimeTypesType(typeCode);
		addTextRechtscharakterType(typeCode);
		return typeCode;
	}

	private static void addChangeType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(CHANGED_BY.name(), MESSAGES.ChangeType_CHANGED_BY()));
		codes.add(new Code(CHANGES.name(), MESSAGES.ChangeType_CHANGES()));
		typeCode.put(ChangeType.class, codes);
	}

	private static void addReferenceType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(BESCHREIBUNG.name(), MESSAGES.ReferenceType_BESCHREIBUNG()));
		codes.add(new Code(BEGRUENDUNG.name(), MESSAGES.ReferenceType_BEGRUENDUNG()));
		codes.add(new Code(LEGENDE.name(), MESSAGES.ReferenceType_LEGENDE()));
		codes.add(new Code(RECHTSPLAN.name(), MESSAGES.ReferenceType_RECHTSPLAN()));
		codes.add(new Code(PLANGRUNDLAGE.name(), MESSAGES.ReferenceType_PLANGRUNDLAGE()));
		codes.add(new Code(UMWELTBERICHT.name(), MESSAGES.ReferenceType_UMWELTBERICHT()));
		codes.add(new Code(SATZUNG.name(), MESSAGES.ReferenceType_SATZUNG()));
		codes.add(new Code(VERORDNUNG.name(), MESSAGES.ReferenceType_VERORDNUNG()));
		codes.add(new Code(KARTE.name(), MESSAGES.ReferenceType_KARTE()));
		codes.add(new Code(ERLAEUTERUNG.name(), MESSAGES.ReferenceType_ERLAEUTERUNG()));
		codes.add(new Code(ZUSAMMENFASSENDEERKLAERUNG.name(), MESSAGES.ReferenceType_ZUSAMMENFASSENDEERKLAERUNG()));
		codes.add(new Code(KOORDINATENLISTE.name(), MESSAGES.ReferenceType_KOORDINATENLISTE()));
		codes.add(new Code(GRUNDSTUECKSVERZEICHNIS.name(), MESSAGES.ReferenceType_GRUNDSTUECKSVERZEICHNIS()));
		codes.add(new Code(PFLANZLISTE.name(), MESSAGES.ReferenceType_PFLANZLISTE()));
		codes.add(new Code(GRUENORDNUNGSPLAN.name(), MESSAGES.ReferenceType_GRUENORDNUNGSPLAN()));
		codes.add(new Code(ERSCHLIESSUNGSVERTRAG.name(), MESSAGES.ReferenceType_ERSCHLIESSUNGSVERTRAG()));
		codes.add(new Code(DURCHFUEHRUNGSVERTRAG.name(), MESSAGES.ReferenceType_DURCHFUEHRUNGSVERTRAG()));
		codes.add(new Code(STAEDTEBAULICHERVERTRAG.name(), MESSAGES.ReferenceType_STAEDTEBAULICHERVERTRAG()));
		codes.add(new Code(UMWELTBEZOGENESTELLUNGNAHMEN.name(), MESSAGES.ReferenceType_UMWELTBEZOGENESTELLUNGNAHMEN()));
		codes.add(new Code(BESCHLUSS.name(), MESSAGES.ReferenceType_BESCHLUSS()));
		codes.add(
				new Code(VORHABENUNDERSCHLIESSUNGSPLAN.name(), MESSAGES.ReferenceType_VORHABENUNDERSCHLIESSUNGSPLAN()));
		codes.add(new Code(METADATENPLAN.name(), MESSAGES.ReferenceType_METADATENPLAN()));
		codes.add(new Code(GENEHMIGUNG.name(), MESSAGES.ReferenceType_GENEHMIGUNG()));
		codes.add(new Code(BEKANNTMACHUNG.name(), MESSAGES.ReferenceType_BEKANNTMACHUNG()));
		codes.add(new Code(SCHUTZGEBIETSVERORDNUNG.name(), MESSAGES.ReferenceType_SCHUTZGEBIETSVERORDNUNG()));
		codes.add(new Code(RECHTSVERBINDLICH.name(), MESSAGES.ReferenceType_RECHTSVERBINDLICH()));
		codes.add(new Code(INFORMELL.name(), MESSAGES.ReferenceType_INFORMELL()));
		typeCode.put(ReferenceType.class, codes);
	}

	private static void addRasterReferenceType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(LEGEND.name(), MESSAGES.RasterReferenceType_LEGEND()));
		codes.add(new Code(SCAN.name(), MESSAGES.RasterReferenceType_SCAN()));
		codes.add(new Code(TEXT.name(), MESSAGES.RasterReferenceType_TEXT()));
		typeCode.put(RasterReferenceType.class, codes);
	}

	private static void addMimeTypesType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		for (MimeTypes mimeTypeType : MimeTypes.values()) {
			codes.add(new Code(mimeTypeType.name(), mimeTypeType.getCode()));
		}
		typeCode.put(MimeTypes.class, codes);
	}

	private static void addExterneReferenzArtType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(DOKUMENT.name(), MESSAGES.XP_ExterneReferenzArt_Dokument()));
		codes.add(new Code(PLANMITGEOREFERENZ.name(), MESSAGES.XP_ExterneReferenzArt_PlanMitGeoreferenz()));
		typeCode.put(ExterneReferenzArt.class, codes);
	}

	private static void addTextRechtscharakterType(HashMap<Class<?>, List<Code>> typeCode) {
		List<Code> codes = new ArrayList<Code>();
		codes.add(new Code(BP_FESTSETZUNG.name(), MESSAGES.TextAbschnitt_BP_Festsetzung()));
		codes.add(new Code(BP_HINWEIS.name(), MESSAGES.TextAbschnitt_BP_Hinweis()));
		codes.add(new Code(BP_KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_BP_Kennzeichnung()));
		codes.add(new Code(BP_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_BP_NachrichtlicheUebernahme()));
		codes.add(new Code(BP_VERMERK.name(), MESSAGES.TextAbschnitt_BP_Vermerk()));
		codes.add(new Code(BP_UNBEKANNT.name(), MESSAGES.TextAbschnitt_BP_Unbekannt()));

		codes.add(new Code(FP_DARSTELLLUNG.name(), MESSAGES.TextAbschnitt_FP_Darstellung()));
		codes.add(new Code(FP_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_FP_NachrichtlicheUebernahme()));
		codes.add(new Code(FP_HINWEIS.name(), MESSAGES.TextAbschnitt_FP_Hinweis()));
		codes.add(new Code(FP_VERMERK.name(), MESSAGES.TextAbschnitt_FP_Vermerk()));
		codes.add(new Code(FP_KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_FP_Kennzeichnung()));
		codes.add(new Code(FP_UNBEKANNT.name(), MESSAGES.TextAbschnitt_FP_Unbekannt()));

		codes.add(new Code(LP_FESTSETZUNG.name(), MESSAGES.TextAbschnitt_LP_Festsetzung()));
		codes.add(new Code(LP_GEPLANT.name(), MESSAGES.TextAbschnitt_LP_Geplant()));
		codes.add(new Code(LP_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_LP_NachrichtlicheUebernahme()));
		codes.add(new Code(LP_DARSTELLUNGKENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_LP_DarstellungKennzeichnung()));
		codes.add(new Code(LP_FESTSETZUNGINBPLAN.name(), MESSAGES.TextAbschnitt_LP_FestsetzungInBPlan()));
		codes.add(new Code(LP_UNBEKANNT.name(), MESSAGES.TextAbschnitt_LP_Unbekannt()));
		codes.add(new Code(LP_SONSTIGERSTATUS.name(), MESSAGES.TextAbschnitt_LP_SonstigerStatus()));

		codes.add(new Code(RP_ZIELDERRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_RP_ZielDerRaumordnung()));
		codes.add(new Code(RP_GRUNDSATZDERRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_RP_GrundsatzDerRaumordnung()));
		codes.add(new Code(RP_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_RP_NachrichtlicheUebernahme()));
		codes.add(new Code(RP_NACHRICHTLICHEUEBERNAHMEZIEL.name(),
				MESSAGES.TextAbschnitt_RP_NachrichtlicheUebernahmeZiel()));
		codes.add(new Code(RP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ.name(),
				MESSAGES.TextAbschnitt_RP_NachrichtlicheUebernahmeGrundsatz()));
		codes.add(new Code(RP_NURINFORMATIONSGEHALT.name(), MESSAGES.TextAbschnitt_RP_NurInformatinsGehalt()));
		codes.add(new Code(RP_TEXTLICHESZIEL.name(), MESSAGES.TextAbschnitt_RP_TextlichesZiel()));
		codes.add(new Code(RP_ZIELUNDGRUNDSATZ.name(), MESSAGES.TextAbschnitt_RP_ZielUndGrundsatz()));
		codes.add(new Code(RP_VORSCHLAG.name(), MESSAGES.TextAbschnitt_RP_Vorschlag()));
		codes.add(new Code(RP_UNBEKANNT.name(), MESSAGES.TextAbschnitt_RP_Unbekannt()));

		codes.add(new Code(SO_FESTSETZUNGBPLAN.name(), MESSAGES.TextAbschnitt_SO_FestsetzungBPlan()));
		codes.add(new Code(SO_DARSTELLUNGFPLAN.name(), MESSAGES.TextAbschnitt_SO_DarstellungFPlan()));
		codes.add(new Code(SO_INHALTLPLAN.name(), MESSAGES.TextAbschnitt_SO_InhaltLPlan()));
		codes.add(new Code(SO_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_SO_NachrichtlicheUebernahme()));
		codes.add(new Code(SO_HINWEIS.name(), MESSAGES.TextAbschnitt_SO_Hinweis()));
		codes.add(new Code(SO_VERMERK.name(), MESSAGES.TextAbschnitt_SO_Vermerk()));
		codes.add(new Code(SO_KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_SO_Kennzeichnung()));
		codes.add(new Code(SO_UNBEKANNT.name(), MESSAGES.TextAbschnitt_SO_Unbekannt()));
		codes.add(new Code(SO_SONSTIGES.name(), MESSAGES.TextAbschnitt_SO_Sonstiges()));

		codes.add(new Code(XP_FESTSETZUNGBPLAN.name(), MESSAGES.TextAbschnitt_XP_FestsetzungBPlan()));
		codes.add(new Code(XP_NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_XP_NachrichtlicheUebernahme()));
		codes.add(new Code(XP_DARSTELLUNGFPLAN.name(), MESSAGES.TextAbschnitt_XP_DarstellungFPlan()));
		codes.add(new Code(XP_ZIELDERRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_XP_ZielDerRaumordnung()));
		codes.add(new Code(XP_GRUNDSATZDERRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_XP_GrundsatzDerRaumordnung()));
		codes.add(new Code(XP_NACHRICHTLICHEUEBERNAHMEZIEL.name(),
				MESSAGES.TextAbschnitt_XP_NachrichtlicheUebernahmeZiel()));
		codes.add(new Code(XP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ.name(),
				MESSAGES.TextAbschnitt_XP_NachrichtlicheUebernahmeGrundsatz()));
		codes.add(new Code(XP_NURINFORMATIONSGEHALTRPLAN.name(), MESSAGES.TextAbschnitt_XP_NurInformatinsGehalt()));
		codes.add(new Code(XP_TEXTLICHESZIELRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_XP_TextlichesZielRaumordnung()));
		codes.add(new Code(XP_ZIELUNDGRUNDSATZRAUMORDNUNG.name(),
				MESSAGES.TextAbschnitt_XP_ZielUndGrundsatzDerRaumordnung()));
		codes.add(new Code(XP_VORSCHLAGRAUMORDNUNG.name(), MESSAGES.TextAbschnitt_XP_VorschlagRaumordnung()));
		codes.add(new Code(XP_FESTSETZUNGIMLP.name(), MESSAGES.TextAbschnitt_XP_FestsetzungImLP()));
		codes.add(new Code(XP_GEPLANTEFESTSETZUNGIMLP.name(), MESSAGES.TextAbschnitt_XP_GeplanteFestsetzungImLP()));
		codes.add(new Code(XP_DARSTELLUNGKENNZEICHNUNGIMLP.name(),
				MESSAGES.TextAbschnitt_XP_DarstellungKennzeichnungImLP()));
		codes.add(new Code(XP_LANDSCHAFTSPLANUNGSINHALTZURBERUECKSICHTIGUNG.name(),
				MESSAGES.TextAbschnitt_XP_LandschaftsplanungsInhaltZurBeruecksichtigung()));
		codes.add(new Code(XP_HINWEIS.name(), MESSAGES.TextAbschnitt_XP_Hinweis()));
		codes.add(new Code(XP_KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_XP_Kennzeichnung()));
		codes.add(new Code(XP_VERMERK.name(), MESSAGES.TextAbschnitt_XP_Vermerk()));
		codes.add(new Code(XP_UNBEKANNT.name(), MESSAGES.TextAbschnitt_XP_Unbekannt()));
		codes.add(new Code(XP_SONSTIGES.name(), MESSAGES.TextAbschnitt_XP_Sonstiges()));

		typeCode.put(TextRechtscharacterType.class, codes);
	}

}
