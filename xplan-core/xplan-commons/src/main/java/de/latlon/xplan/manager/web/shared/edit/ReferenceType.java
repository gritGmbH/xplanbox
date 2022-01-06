/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Used to discriminate references types.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public enum ReferenceType {

	BESCHREIBUNG(1000, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	BEGRUENDUNG(1010, "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	LEGENDE(1020, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	RECHTSPLAN(1030, "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	PLANGRUNDLAGE(1040, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	UMWELTBERICHT(1050, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	SATZUNG(1060, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	VERORDNUNG(1065, "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	KARTE(1070, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	ERLAEUTERUNG(1080, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	ZUSAMMENFASSENDEERKLAERUNG(1090, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	KOORDINATENLISTE(2000, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	GRUNDSTUECKSVERZEICHNIS(2100, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	PFLANZLISTE(2200, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	GRUENORDNUNGSPLAN(2300, "XPLAN_3", "XPLAN_41", "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	ERSCHLIESSUNGSVERTRAG(2400, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	DURCHFUEHRUNGSVERTRAG(2500, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	STAEDTEBAULICHERVERTRAG(2600, "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	UMWELTBEZOGENESTELLUNGNAHMEN(2700, "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	BESCHLUSS(2800, "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	VORHABENUNDERSCHLIESSUNGSPLAN(2900, "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	METADATENPLAN(3000, "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	STAEDTEBAULENTWICKLUNGSKONZEPTINNENENTWICKLUNG(3100, "XPLAN_52", "XPLAN_53"),

	GENEHMIGUNG(4000, "XPLAN_53", "XPLAN_54"),

	BEKANNTMACHUNG(5000, "XPLAN_53", "XPLAN_54"),

	RECHTSVERBINDLICH(9998, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54"),

	INFORMELL(9999, "XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54");

	private int spezExterneRefType;

	private List<String> supportedXPlanVersions = new ArrayList<String>();

	ReferenceType(int spezExterneRefType, String... supportedXPlanVersions) {
		this.spezExterneRefType = spezExterneRefType;
		this.supportedXPlanVersions.addAll(Arrays.asList(supportedXPlanVersions));
	}

	public static ReferenceType getBySpezExterneReferenceType(String type) {
		if (type == null)
			return null;
		int typeAsInt = Integer.parseInt(type.trim());
		return getBySpezExterneReferenceType(typeAsInt);
	}

	public static ReferenceType getBySpezExterneReferenceType(int typeAsInt) {
		for (ReferenceType referenceType : values()) {
			if (referenceType.spezExterneRefType == typeAsInt)
				return referenceType;
		}
		return null;
	}

	public int getSpezExterneReferenceType() {
		return spezExterneRefType;
	}

	public boolean isXPlanVersionSupported(String xPlanVersion) {
		return this.supportedXPlanVersions.contains(xPlanVersion);
	}

}
