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

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Provides a {@link StringListConverter} for all parameters of type List<String>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Provider
public class StringListConverterProvider implements ParamConverterProvider {

	@SuppressWarnings("unchecked")
	@Override
	public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
		if (isListWithStrings(aClass, type)) {
			return (ParamConverter<T>) new StringListConverter();
		}
		return null;
	}

	private <T> boolean isListWithStrings(Class<T> aClass, Type type) {
		if (List.class.isAssignableFrom(aClass) && type instanceof ParameterizedType) {
			Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
			if (actualTypeArguments.length == 1) {
				Type actualTypeArgument = actualTypeArguments[0];
				if (actualTypeArgument instanceof Class
						&& ((Class<?>) actualTypeArgument).isAssignableFrom(String.class)) {
					return true;
				}
			}
		}
		return false;
	}

}
