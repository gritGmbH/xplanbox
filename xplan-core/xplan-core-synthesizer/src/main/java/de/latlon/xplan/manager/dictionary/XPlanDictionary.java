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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Encapsulates a single dictionary with id and a list of {@link XPlanDictionaryEntry}.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanDictionary {

	private final String dictionaryId;

	private final List<XPlanDictionaryEntry> dictionaryEntries = new ArrayList<>();

	/**
	 * @param dictionaryId the id of the dictionary, never <code>null</code>
	 */
	public XPlanDictionary(String dictionaryId) {
		this.dictionaryId = dictionaryId;
	}

	/**
	 * @return the id of this dictionary, never <code>null</code>
	 */
	public String getDictionaryId() {
		return dictionaryId;
	}

	/**
	 * @param code of the dictionaryEntry to add, never <code>null</code>
	 * @param name of the dictionaryEntry to add, never <code>null</code>
	 * @param lesbarerName of the dictionaryEntry, may be <code>null</code>
	 * @param kuerzel of the dictionaryEntry, may be <code>null</code>
	 * @throws IllegalArgumentException if a dictionaryEntry with the passed code or name
	 * already exists
	 */
	public void addDictionaryEntry(String code, String name, String lesbarerName, String kuerzel) {
		checkExistingCodes(code, name);
		XPlanDictionaryEntry newDictionaryEntry = new XPlanDictionaryEntry(code, name, lesbarerName, kuerzel);
		dictionaryEntries.add(newDictionaryEntry);
	}

	/**
	 * @param code of the dictionaryEntry to return, never <code>null</code>
	 * @return the dictionaryEntry with the passed code, if no entry exists an
	 * {@link IllegalArgumentException} is thrown
	 * @throws IllegalArgumentException if no dictionaryEntry with the passed code exists
	 */
	public XPlanDictionaryEntry getDictionaryEntry(String code) {
		Optional<XPlanDictionaryEntry> dictionaryEntryWithCode = dictionaryEntries.stream()
			.filter(dictionaryEntry -> dictionaryEntry.getCode().equals(code))
			.findFirst();
		if (!dictionaryEntryWithCode.isPresent()) {
			throw new IllegalArgumentException("Unbekannter Code '" + code + "'. Dictionary '" + dictionaryId
					+ "' enthält keinen entsprechenden Eintrag.");

		}
		return dictionaryEntryWithCode.get();
	}

	/**
	 * @return all dictionaryEntries, may be <code>empty</code> but never <code></code>
	 */
	public List<XPlanDictionaryEntry> getDictionaryEntries() {
		return dictionaryEntries;
	}

	private void checkExistingCodes(String code, String name) {
		Optional<XPlanDictionaryEntry> dictionaryEntryWithCode = dictionaryEntries.stream()
			.filter(dictionaryEntry -> dictionaryEntry.getCode().equals(code))
			.findAny();
		if (dictionaryEntryWithCode.isPresent()) {
			String msg = "Cannot add code '" + code + "' to dictionary '" + dictionaryId
					+ "' -- code is already defined.";
			throw new IllegalArgumentException(msg);
		}
		Optional<XPlanDictionaryEntry> dictionaryEntryWithName = dictionaryEntries.stream()
			.filter(dictionaryEntry -> dictionaryEntry.getName().equals(name))
			.findAny();
		if (dictionaryEntryWithName.isPresent()) {
			String msg = "Cannot add code '" + code + "' with name '" + name + "' to dictionary '" + dictionaryId
					+ "' -- list already defines name '" + name + "' for this code.";
			throw new IllegalArgumentException(msg);
		}
	}

	@Override
	public String toString() {
		return "XPlanDictionary{" + "dictionaryId='" + dictionaryId + '\'' + ", dictionaryEntries=" + dictionaryEntries
				+ '}';
	}

}
