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

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Deprecated
public class CategoryMapperTest {

	@Test
	public void testMapToCategoryFromSingletonCategoryShoudBeFound() throws Exception {
		CategoryMapper categoryMapper = new CategoryMapper(mockManagerConfiguration());
		String part = categoryMapper.mapToCategory("A F");

		assertThat(part, is("Cat1"));
	}

	@Test
	public void testMapToCategoryFromMultipleCategoryShoudBeFound() throws Exception {
		CategoryMapper categoryMapper = new CategoryMapper(mockManagerConfiguration());
		String part = categoryMapper.mapToCategory("1");

		assertThat(part, is("Cat2"));
	}

	@Test
	public void testMapToCategoryWithNullPartShoudReturnNull() throws Exception {
		CategoryMapper categoryMapper = new CategoryMapper(mockManagerConfiguration());
		String part = categoryMapper.mapToCategory(null);

		assertThat(part, is(nullValue()));
	}

	@Test
	public void testMapToCategoryWithUnknownPartShoudNotBeFound() throws Exception {
		CategoryMapper categoryMapper = new CategoryMapper(mockManagerConfiguration());
		String part = categoryMapper.mapToCategory("notKnown");

		assertThat(part, is(nullValue()));
	}

	private ManagerConfiguration mockManagerConfiguration() {
		ManagerConfiguration managerConfiguration = mock(ManagerConfiguration.class);
		Map<String, List<String>> mapping = new HashMap<String, List<String>>();
		mapping.put("Cat1", Collections.singletonList("A F"));
		List<String> cat2Parts = new ArrayList<String>();
		cat2Parts.add("1");
		cat2Parts.add("7");
		mapping.put("Cat2", cat2Parts);
		Mockito.when(managerConfiguration.getCategoryMapping()).thenReturn(mapping);

		return managerConfiguration;
	}

}
