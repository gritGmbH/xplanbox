/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager;

import de.latlon.xplan.commons.archive.LocalCenterToDistrictMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Maps a part to the category.
 *
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Deprecated
public class CategoryMapper implements LocalCenterToDistrictMapper {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryMapper.class);

	private final Map<String, List<String>> categoryMapping;

	/**
	 * @param managerConfiguration provides access to the mapping, never <code>null</code>
	 * @throws NullPointerException - managerConfiguration is <code>null</code>
	 */
	public CategoryMapper(ManagerConfiguration managerConfiguration) {
		this.categoryMapping = managerConfiguration.getCategoryMapping();
	}

	/**
	 * Does not check if a part is assigned to more than one category (first one is
	 * returned)!
	 * @param part may be <code>null</code>
	 * @return the category the part is assigned to, code>null</code> if no category could
	 * be found or part is <code>null</code>
	 * @throws NullPointerException - part is <code>null</code>
	 */
	public String mapToCategory(String part) {
		LOG.debug("Map part {} to category.", part);
		if (part != null)
			for (Entry<String, List<String>> category : categoryMapping.entrySet()) {
				for (String categoryPart : category.getValue()) {
					if (part.equals(categoryPart)) {
						LOG.info("Found category: {}", category.getKey());
						return category.getKey();
					}
				}
			}
		LOG.debug("No category found.");
		return null;
	}

	@Override
	public String mapToDistrict(String localCenter) {
		return mapToCategory(localCenter);
	}

}
