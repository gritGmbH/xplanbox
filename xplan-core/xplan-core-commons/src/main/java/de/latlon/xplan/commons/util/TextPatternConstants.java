/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.util;

/**
 * Contains constants with patterns for text fields/parameter.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class TextPatternConstants {

	private TextPatternConstants() {
	}

	public final static String SIMPLE_NAME_PATTERN = "^[A-Za-z0-9.()_\\-]*$";

	public final static String INTERNALID_PATTERN = "^[A-Za-z0-9\\-_]*$";

	public final static String URL_PATTERN = "^[A-Za-z0-9.@:%_\\+.~#?&//=\\-]*$";

	/**
	 * <pre>
	 *   \u0030-\u0039 -> !, ", #, $, %, &
	 *   \u0021-\u0026 -> (, ), *, +, comma, -, ., /
	 *   \u0028-\u002F -> s. u.
	 *   \u003A-\u0084 -> s. u.
	 *   \u0086-\u009F -> s. u.
	 *   \u00A1-\u167F -> s. u.
	 *   \u1681-\u1FFF -> s. u.
	 *   \u200B-\u2027 -> s. u.
	 *   \u202A-\u202E -> s. u.
	 *   \u2030-\u205E -> s. u.
	 *   \u2060-\u2FFF -> s. u.
	 *   \u3001-\uD7FF -> :,;,<,=,>,?,@,A-Z,[,\,],^,_,`,a-z,0-9,ae,ue,oe,Ae,Ue,Oe,ss
	 *   \s -> Whitespace
	 * </pre>
	 */
	public final static String TEXT_PATTERN = "^[\\u0030-\\u0039\\u0021-\\u0026\\u0028-\\u002F\\u003A-\\u0084\\u0086-\\u009F\\u00A1-\\u167F\\u1681-\\u1FFF\\u200B-\\u2027\\u202A-\\u202E\\u2030-\\u205E\\u2060-\\u2FFF\\u3001-\\uD7FF\\s]*$";

	public final static int XS_LENGTH = 50;

	public final static int S_LENGTH = 100;

	public final static int M_LENGTH = 250;

	public final static int L_LENGTH = 1000;

	public final static int XL_LENGTH = 10000;

}
