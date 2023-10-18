/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public enum TextRechtscharacterType {

	BP_FESTSETZUNG(1000, "BP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	BP_NACHRICHTLICHEUEBERNAHME(2000, "BP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	BP_HINWEIS(3000, "BP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	BP_VERMERK(4000, "BP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	BP_KENNZEICHNUNG(5000, "BP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	BP_UNBEKANNT(9998, "BP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_DARSTELLLUNG(1000, "FP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_NACHRICHTLICHEUEBERNAHME(2000, "FP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_HINWEIS(3000, "FP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_VERMERK(4000, "FP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_KENNZEICHNUNG(5000, "FP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	FP_UNBEKANNT(9998, "FP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_FESTSETZUNG(1000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_GEPLANT(2000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_NACHRICHTLICHEUEBERNAHME(3000, "LP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_DARSTELLUNGKENNZEICHNUNG(4000, "LP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_FESTSETZUNGINBPLAN(5000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_UNBEKANNT(9998, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	LP_SONSTIGERSTATUS(9999, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_ZIELDERRAUMORDNUNG(1000, "RP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_GRUNDSATZDERRAUMORDNUNG(2000, "RP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_NACHRICHTLICHEUEBERNAHME(3000, "RP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_NACHRICHTLICHEUEBERNAHMEZIEL(4000, "RP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ(5000, "RP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_NURINFORMATIONSGEHALT(6000, "RP_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_TEXTLICHESZIEL(7000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_ZIELUNDGRUNDSATZ(8000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_VORSCHLAG(9000, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	RP_UNBEKANNT(9998, "LP_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_FESTSETZUNGBPLAN(1000, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_DARSTELLUNGFPLAN(1500, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_INHALTLPLAN(1800, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_NACHRICHTLICHEUEBERNAHME(2000, "SO_Plan",
			Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_HINWEIS(3000, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_VERMERK(4000, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_KENNZEICHNUNG(5000, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_UNBEKANNT(9998, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	SO_SONSTIGES(9999, "SO_Plan", Arrays.asList("XPLAN_50", "XPLAN_51", "XPLAN_52", "XPLAN_53", "XPLAN_54")),

	XP_FESTSETZUNGBPLAN(1000, "XPLAN_60"),

	XP_NACHRICHTLICHEUEBERNAHME(2000, "XPLAN_60"),

	XP_DARSTELLUNGFPLAN(3000, "XPLAN_60"),

	XP_ZIELDERRAUMORDNUNG(4000, "XPLAN_60"),

	XP_GRUNDSATZDERRAUMORDNUNG(4100, "XPLAN_60"),

	XP_NACHRICHTLICHEUEBERNAHMEZIEL(4200, "XPLAN_60"),

	XP_NACHRICHTLICHEUEBERNAHMEGRUNDSATZ(4300, "XPLAN_60"),

	XP_NURINFORMATIONSGEHALTRPLAN(4400, "XPLAN_60"),

	XP_TEXTLICHESZIELRAUMORDNUNG(4500, "XPLAN_60"),

	XP_ZIELUNDGRUNDSATZRAUMORDNUNG(4600, "XPLAN_60"),

	XP_VORSCHLAGRAUMORDNUNG(4700, "XPLAN_60"),

	XP_FESTSETZUNGIMLP(5000, "XPLAN_60"),

	XP_GEPLANTEFESTSETZUNGIMLP(5100, "XPLAN_60"),

	XP_DARSTELLUNGKENNZEICHNUNGIMLP(5200, "XPLAN_60"),

	XP_LANDSCHAFTSPLANUNGSINHALTZURBERUECKSICHTIGUNG(5300, "XPLAN_60"),

	XP_HINWEIS(6000, "XPLAN_60"),

	XP_KENNZEICHNUNG(7000, "XPLAN_60"),

	XP_VERMERK(8000, "XPLAN_60"),

	XP_UNBEKANNT(9998, "XPLAN_60"),

	XP_SONSTIGES(9999, "XPLAN_60");

	private final int code;

	private String type;

	private final List<String> supportedXPlanVersions = new ArrayList<>();

	TextRechtscharacterType(int code, String supportedXPlanVersion) {
		this.code = code;
		this.supportedXPlanVersions.add(supportedXPlanVersion);
	}

	TextRechtscharacterType(int code, String type, List<String> supportedXPlanVersions) {
		this.code = code;
		this.type = type;
		this.supportedXPlanVersions.addAll(supportedXPlanVersions);
	}

	public static TextRechtscharacterType fromCode(int code, String version, String type) {
		for (TextRechtscharacterType rechtscharacterType : values()) {
			if (rechtscharacterType.code == code && rechtscharacterType.supportedXPlanVersions.contains(version)
					&& (type.equals(rechtscharacterType.type)))
				return rechtscharacterType;
		}
		for (TextRechtscharacterType rechtscharacterType : values()) {
			if (rechtscharacterType.code == code && rechtscharacterType.supportedXPlanVersions.contains(version)
					&& (rechtscharacterType.type == null))
				return rechtscharacterType;
		}
		throw new IllegalArgumentException("Could not find rechtscharacter with code " + code);
	}

	public int getCode() {
		return code;
	}

	public boolean isCodeFor(String version, String type) {
		return this.supportedXPlanVersions.contains(version) && (this.type == null || this.type.equals(type));
	}

}
