/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager.dictionary;

/**
 * A single codeEntry with all relevant information.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanDictionaryEntry {

	private final String code;

	private final String name;

	private final String lesbarerName;

	private final String kuerzel;

	/**
	 * @param code of the codeEntry, e.g "1000", never <code>null</code>
	 * @param name of the codeEntry, e.g "absolutNHN", never <code>null</code>
	 * @param lesbarerName of the codeEntry, e.g "Absolut NHN", may be <code>null</code>
	 * @param kuerzel of the codeEntry, e.g "NHN", may be <code>null</code>
	 */
	public XPlanDictionaryEntry(String code, String name, String lesbarerName, String kuerzel) {
		this.code = code;
		this.name = name;
		this.lesbarerName = lesbarerName;
		this.kuerzel = kuerzel;
	}

	/**
	 * @return code of the codeEntry, e.g "1000", never <code>null</code>
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return name of the codeEntry, e.g "absolutNHN", never <code>null</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return lesbarerName of the codeEntry, e.g "Absolut NHN", may be <code>null</code>
	 */
	public String getLesbarerName() {
		return lesbarerName;
	}

	/**
	 * @return kuerzel of the codeEntry, e.g "NHN", may be <code>null</code>
	 */
	public String getKuerzel() {
		return kuerzel;
	}

	@Override
	public String toString() {
		return "XPlanCodeEntry{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", lesbarerName='" + lesbarerName
				+ '\'' + ", kuerzel='" + kuerzel + '\'' + '}';
	}

}
