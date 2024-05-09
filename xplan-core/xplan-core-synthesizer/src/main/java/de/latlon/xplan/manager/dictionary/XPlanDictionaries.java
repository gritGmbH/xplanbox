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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Encapsulates the enumerations for one XPlan version.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanDictionaries {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDictionaries.class);

	private final List<XPlanDictionary> dictionaries = new ArrayList<>();

	/**
	 * @param dictionaryId the id of the dictionary to create, never <code>null</code>
	 * @return the created {@link XPlanDictionary}, never <code>null</code>
	 */
	public XPlanDictionary addDictionary(String dictionaryId) {
		XPlanDictionary dictionary = new XPlanDictionary(dictionaryId);
		dictionaries.add(dictionary);
		return dictionary;
	}

	/**
	 * Adds all passed {@link XPlanDictionary} to this {@link XPlanDictionaries}
	 * @param xPlanDictionaries to add, never <code>null</code>
	 */
	public void addDictionaries(XPlanDictionaries xPlanDictionaries) {
		this.dictionaries.addAll(xPlanDictionaries.getDictionaries());
	}

	/**
	 * @param dictionaryId the id of the dictionary to add the new dictionaryEntry to,
	 * never <code>null</code>
	 * @param code the code of the entry to add, never <code>null</code>
	 * @param name the name of the entry to add, never <code>null</code>
	 */
	public void addDictionaryEntry(String dictionaryId, String code, String name) {
		addDictionaryEntry(dictionaryId, code, name, null, null);
	}

	/**
	 * @param dictionaryId the id of the dictionary to add the new dictionaryEntry to,
	 * never <code>null</code>
	 * @param code the code of the entry to add, never <code>null</code>
	 * @param name the name of the entry to add, never <code>null</code>
	 * @param lesbarerName of the codeEntry, may be <code>null</code>
	 * @param kuerzel of the codeEntry, may be <code>null</code>
	 */
	public void addDictionaryEntry(String dictionaryId, String code, String name, String lesbarerName, String kuerzel) {
		Optional<XPlanDictionary> dictionaryWithId = dictionaries.stream()
			.filter(dictionary -> dictionary.getDictionaryId().equals(dictionaryId))
			.findFirst();
		XPlanDictionary dictionary;
		if (!dictionaryWithId.isPresent()) {
			dictionary = addDictionary(dictionaryId);
		}
		else {
			dictionary = dictionaryWithId.get();
		}
		dictionary.addDictionaryEntry(code, name, lesbarerName, kuerzel);
	}

	/**
	 * @param dictionaryId the id of the dictionary to return, never <code>null</code>
	 * @return the codeList with the passed dictionaryId, never <code>null</code>, if no
	 * codeList with the passed dictionaryId exists, an {@link IllegalArgumentException}
	 * is thrown
	 * @throws IllegalArgumentException if no codeList with the passed dictionaryId exists
	 */
	public XPlanDictionary getDictionary(String dictionaryId) {
		Optional<XPlanDictionary> dictionaryWithId = dictionaries.stream()
			.filter(dictionary -> dictionary.getDictionaryId().equals(dictionaryId))
			.findFirst();
		if (!dictionaryWithId.isPresent()) {
			throw new IllegalArgumentException("Unbekanntes Dictionary '" + dictionaryId + "'.");
		}
		return dictionaryWithId.get();
	}

	/**
	 * @return all dictionaries, may be <code>empty</code> but never <code>null</code>
	 */
	public List<XPlanDictionary> getDictionaries() {
		return Collections.unmodifiableList(dictionaries);
	}

	/**
	 * @param dictionaryId the id of the dictionary to check, never <code>null</code>
	 * @return <code>true</code> if a dictionary with the passed dictionaryId exists,
	 * <code>false</code> otherwise
	 */
	public boolean hasDictionary(String dictionaryId) {
		return dictionaries.stream().anyMatch(dictionary -> dictionary.getDictionaryId().equals(dictionaryId));
	}

	/**
	 * @param dictionaryId the id of the dictionary to return the translation, never
	 * <code>null</code>
	 * @param code the code of the dictionaryEntry to return the translation, never
	 * <code>null</code>
	 * @return the name of the dictionaryEntry with the passed code of the dictionary with
	 * the passed dictionaryId, never <code>null</code>
	 * @throws IllegalArgumentException if no dictionary or dictionaryEntry with the
	 * passed dictionaryId exists
	 */
	public String getTranslation(String dictionaryId, String code) {
		String dictionaryIdOrFirst = checkDictionaryIdForNull(dictionaryId);
		XPlanDictionary dictionary = getDictionary(dictionaryIdOrFirst);
		XPlanDictionaryEntry dictionaryEntry = dictionary.getDictionaryEntry(code);
		String lesbarerName = dictionaryEntry.getLesbarerName();
		if (lesbarerName != null && !lesbarerName.isEmpty()) {
			return lesbarerName;
		}
		return dictionaryEntry.getName();
	}

	/**
	 * @param dictionaryId the id of the dictionary to return the kuerzel, never
	 * <code>null</code>
	 * @param code the code of the dictionaryEntry to return the kuerzel, never
	 * <code>null</code>
	 * @return the kuerzel of the dictionaryEntry with the passed code of the dictionary
	 * with the passed dictionaryId, may be <code>null</code> if not available
	 * @throws IllegalArgumentException if no dictionary or dictionaryEntry with the
	 * passed dictionaryId exists
	 */
	public String getKuerzel(String dictionaryId, String code) {
		String dictionaryIdOrFirst = checkDictionaryIdForNull(dictionaryId);
		XPlanDictionary dictionary = getDictionary(dictionaryIdOrFirst);
		XPlanDictionaryEntry dictionaryEntry = dictionary.getDictionaryEntry(code);
		return dictionaryEntry.getKuerzel();
	}

	private String checkDictionaryIdForNull(String dictionaryId) {
		if (dictionaryId != null) {
			return dictionaryId;
		}
		if (dictionaries.size() == 0)
			LOG.warn("Dictionary are empty!");
		else if (dictionaries.size() == 1)
			return dictionaries.stream().findFirst().get().getDictionaryId();
		else
			LOG.warn("XPlanDictionary contains multiple dictionaries!");
		return null;
	}

}
