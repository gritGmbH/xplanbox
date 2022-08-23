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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Encapsulates the enumerations for one XPlan version.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanCodeLists {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanCodeLists.class);

	private final List<XPlanCodeList> codeLists = new ArrayList<>();

	/**
	 * @param codeListId the id of the codeList to create, never <code>null</code>
	 * @return the created {@link XPlanCodeList}, never <code>null</code>
	 */
	public XPlanCodeList addNewCodeList(String codeListId) {
		XPlanCodeList codeList = new XPlanCodeList(codeListId);
		codeLists.add(codeList);
		return codeList;
	}

	/**
	 * @param codeListId the id of the codeList to add the new codeEntry to, never
	 * <code>null</code>
	 * @param code the code of the entry to add, never <code>null</code>
	 * @param name the name of the entry to add, never <code>null</code>
	 */
	public void addNewCodeEntry(String codeListId, String code, String name) {
		addNewCodeEntry(codeListId, code, name, null, null);
	}

	/**
	 * @param codeListId the id of the codeList to add the new codeEntry to, never
	 * <code>null</code>
	 * @param code the code of the entry to add, never <code>null</code>
	 * @param name the name of the entry to add, never <code>null</code>
	 * @param lesbarerName of the codeEntry, may be <code>null</code>
	 * @param kuerzel of the codeEntry, may be <code>null</code>
	 */
	public void addNewCodeEntry(String codeListId, String code, String name, String lesbarerName, String kuerzel) {
		Optional<XPlanCodeList> codeListWithId = codeLists.stream()
				.filter(codeList -> codeList.getCodelistId().equals(codeListId)).findFirst();
		XPlanCodeList codeList;
		if (!codeListWithId.isPresent()) {
			codeList = addNewCodeList(codeListId);
		}
		else {
			codeList = codeListWithId.get();
		}
		codeList.addNewCode(code, name, lesbarerName, kuerzel);
	}

	/**
	 * @param codeListId the id of the codeList to return, never <code>null</code>
	 * @return the codeList with the passed codeListId, never <code>null</code>, if no
	 * codeList with the passed codeListId exists, an {@link IllegalArgumentException} is
	 * thrown
	 * @throws IllegalArgumentException if no codeList with the passed codeListId exists
	 */
	public XPlanCodeList getCodeList(String codeListId) {
		Optional<XPlanCodeList> codeListWithId = codeLists.stream()
				.filter(codeList -> codeList.getCodelistId().equals(codeListId)).findFirst();
		if (!codeListWithId.isPresent()) {
			throw new IllegalArgumentException("Unbekannte CodeList '" + codeListId + "'.");
		}
		return codeListWithId.get();
	}

	/**
	 * @return al codeLists, mey be <code>empty</code> but never <code>null</code>
	 */
	public List<XPlanCodeList> getCodeLists() {
		return codeLists;
	}

	/**
	 * @param codeListId the id of the codeList to check, never <code>null</code>
	 * @return <code>true</code> if a codeList with the passed codeListId exists,
	 * <code>false</code> otherwise
	 */
	public boolean hasCodeList(String codeListId) {
		return codeLists.stream().anyMatch(codeList -> codeList.getCodelistId().equals(codeListId));
	}

	/**
	 * @param codeListId the id of the codeList to return the name, never
	 * <code>null</code>
	 * @param code the code of the codeEntry to return the name, never <code>null</code>
	 * @return the name of the codeEntry with the passed code of the codeList with the
	 * passed codeListId, never <code>null</code>
	 * @throws IllegalArgumentException if no codeList or codeEntry with the passed
	 * codeListId exists
	 */
	public String getTranslation(String codeListId, String code) {
		String codeListIdOrFirst = checkCodeListIdForNull(codeListId);
		XPlanCodeList codeList = getCodeList(codeListIdOrFirst);
		XPlanCodeEntry codeEntry = codeList.getCodeEntry(code);
		String lesbarerName = codeEntry.getLesbarerName();
		if (lesbarerName != null && !lesbarerName.isEmpty()) {
			return lesbarerName;
		}
		return codeEntry.getName();
	}

	private String checkCodeListIdForNull(String codeListId) {
		if (codeListId != null) {
			return codeListId;
		}
		if (codeLists.size() == 0)
			LOG.warn("Code list is empty!");
		else if (codeLists.size() == 1)
			return codeLists.stream().findFirst().get().getCodelistId();
		else
			LOG.warn("XPlanCodeLists contains multiple codelists!");
		return null;
	}

}
