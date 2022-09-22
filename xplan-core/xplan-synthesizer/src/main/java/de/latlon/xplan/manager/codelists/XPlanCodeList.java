/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.codelists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Encapsulates a single code list with id and a list of {@link XPlanCodeEntry}.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanCodeList {

	private final String codeListId;

	private final List<XPlanCodeEntry> codeEntries = new ArrayList<>();

	/**
	 * @param codeListId the id of the code list, never <code>null</code>
	 */
	public XPlanCodeList(String codeListId) {
		this.codeListId = codeListId;
	}

	/**
	 * @return the id of this code list, never <code>null</code>
	 */
	public String getCodelistId() {
		return codeListId;
	}

	/**
	 * @param code of the codeEntry to add, never <code>null</code>
	 * @param name of the codeEntry to add, never <code>null</code>
	 * @param lesbarerName of the codeEntry, may be <code>null</code>
	 * @param kuerzel of the codeEntry, may be <code>null</code>
	 * @throws IllegalArgumentException if a codeEntry with the passed code or name
	 * already exists
	 */
	public void addNewCode(String code, String name, String lesbarerName, String kuerzel) {
		checkExistingCodes(code, name);
		XPlanCodeEntry newCode = new XPlanCodeEntry(code, name, lesbarerName, kuerzel);
		codeEntries.add(newCode);
	}

	/**
	 * @param code of the codeEntry to return, never <code>null</code>
	 * @return the codeEntry with the passed code, if no entry exists an
	 * {@link IllegalArgumentException} is thrown
	 * @throws IllegalArgumentException if no codeEntry with the passed code exists
	 */
	public XPlanCodeEntry getCodeEntry(String code) {
		Optional<XPlanCodeEntry> codeEntryWithCode = codeEntries.stream()
				.filter(codeEntry -> codeEntry.getCode().equals(code)).findFirst();
		if (!codeEntryWithCode.isPresent()) {
			throw new IllegalArgumentException("Unbekannter Code '" + code + "'. CodeList '" + codeListId
					+ "' enthält keinen entsprechenden Eintrag.");

		}
		return codeEntryWithCode.get();
	}

	/**
	 * @return all code entries, may be <code>empty</code> but never <code></code>
	 */
	public List<XPlanCodeEntry> getCodeEntries() {
		return codeEntries;
	}

	private void checkExistingCodes(String code, String name) {
		Optional<XPlanCodeEntry> codeEntryWithCode = codeEntries.stream()
				.filter(codeEntry -> codeEntry.getCode().equals(code)).findAny();
		if (codeEntryWithCode.isPresent()) {
			String msg = "Cannot add code '" + code + "' to code list '" + codeListId + "' -- code is already defined.";
			throw new IllegalArgumentException(msg);
		}
		Optional<XPlanCodeEntry> codeEntryWithName = codeEntries.stream()
				.filter(codeEntry -> codeEntry.getName().equals(name)).findAny();
		if (codeEntryWithName.isPresent()) {
			String msg = "Cannot add code '" + code + "' with name '" + name + "' to code list '" + codeListId
					+ "' -- list already defines name '" + name + "' for this code.";
			throw new IllegalArgumentException(msg);
		}
	}

	@Override
	public String toString() {
		return "XPlanCodeList{" +
				"codeListId='" + codeListId + '\'' +
				", codeEntries=" + codeEntries +
				'}';
	}
}
