/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.STAEDTEBAULICHERVERTRAG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.UMWELTBERICHT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.UMWELTBEZOGENESTELLUNGNAHMEN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.VERORDNUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.VORHABENUNDERSCHLIESSUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.ZUSAMMENFASSENDEERKLAERUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FESTSETZUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.VERMERK;

/**
 * Provides access to type code (mapping between code and value)
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class TypeCodelistProvider {

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
		codes.add(new Code(FESTSETZUNG.name(), MESSAGES.TextAbschnitt_Festsetzung()));
		codes.add(new Code(HINWEIS.name(), MESSAGES.TextAbschnitt_Hinweis()));
		codes.add(new Code(KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_Kennzeichnung()));
		codes.add(new Code(NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_NachrichtlicheUebernahme()));
		codes.add(new Code(VERMERK.name(), MESSAGES.TextAbschnitt_Vermerk()));
		codes.add(new Code(UNBEKANNT.name(), MESSAGES.TextAbschnitt_Unbekannt()));
		typeCode.put(TextRechtscharacterType.class, codes);
	}

}
