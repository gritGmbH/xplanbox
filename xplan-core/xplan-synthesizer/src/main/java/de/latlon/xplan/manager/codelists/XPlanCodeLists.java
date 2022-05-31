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

import java.util.Map;

/**
 * Encapsulates the internal or external code lists for one XPlan schema.
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version 1.0, Date: 2010-02-11
 */
public class XPlanCodeLists {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanCodeLists.class);

	// for each code list: key: code, value: description
	private final Map<String, Map<String, String>> codeListIdToMapping;

	// for each code list: key: description, value: code
	private final Map<String, Map<String, String>> codeListIdToReverseMapping;

	/**
	 * @param codeListIdToMapping
	 * @param codeListIdToReverseMapping
	 */
	XPlanCodeLists(Map<String, Map<String, String>> codeListIdToMapping,
			Map<String, Map<String, String>> codeListIdToReverseMapping) {
		this.codeListIdToMapping = codeListIdToMapping;
		this.codeListIdToReverseMapping = codeListIdToReverseMapping;
	}

	/**
	 * @param codeListId
	 * @param code
	 * @return
	 */
	public String getDescription(String codeListId, String code) {
		String codeListIdToUse = checkCodeListIdForNull(codeListId);
		Map<String, String> codeToDesc = codeListIdToMapping.get(codeListIdToUse);
		if (codeToDesc == null) {
			throw new IllegalArgumentException("Unbekannte CodeList '" + codeListId + "'.");
		}
		String translation = codeToDesc.get(code);
		if (translation == null) {
			throw new IllegalArgumentException("Unbekannter Code '" + code + "'. CodeList '" + codeListId
					+ "' enthält keinen entsprechenden Eintrag.");
		}
		return translation;
	}

	/**
	 * @param codeListId
	 * @param description
	 * @return
	 */
	public String getCode(String codeListId, String description) {
		String codeListIdToUse = checkCodeListIdForNull(codeListId);
		Map<String, String> codeToDesc = codeListIdToReverseMapping.get(codeListIdToUse);
		if (codeToDesc == null) {
			throw new IllegalArgumentException("Unbekannte CodeList '" + codeListId + "'.");
		}
		String code = codeToDesc.get(description);
		if (code == null) {
			throw new IllegalArgumentException("Unbekannte Description '" + description + "'. CodeList '" + codeListId
					+ "' enthält keinen entsprechenden Eintrag.");
		}
		return code;
	}

	/**
	 * @return key: code list id, value: (key: code, value: description)
	 */
	public Map<String, Map<String, String>> getCodesToDescriptions() {
		return codeListIdToMapping;
	}

	/**
	 * @return key: code list id, value: (key: description, value: code)
	 */
	public Map<String, Map<String, String>> getDescriptionsToCodes() {
		return codeListIdToReverseMapping;
	}

	private String checkCodeListIdForNull(String codeListId) {
		if (codeListId != null) {
			return codeListId;
		}
		if (codeListIdToMapping.size() == 0)
			LOG.warn("Code list is empty!");
		else if (codeListIdToMapping.size() == 1)
			return codeListIdToMapping.keySet().iterator().next();
		else
			LOG.warn("XPlanCodeLists contains multiple codelists!");
		return null;
	}

}
