/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons.converter;

import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.ext.ParamConverter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts coma separated string values as List.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class StringListConverter implements ParamConverter<List<String>> {

	@Override
	public List<String> fromString(final String value) {
		if (StringUtils.isBlank(value))
			return null;
		return Stream.of(value.split(",")).map(String::new).collect(Collectors.toList());
	}

	@Override
	public String toString(final List<String> value) {
		if (value == null || value.isEmpty())
			return null;
		return value.stream().collect(Collectors.joining(","));
	}

}
