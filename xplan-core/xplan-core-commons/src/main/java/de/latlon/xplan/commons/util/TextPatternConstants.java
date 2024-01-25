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

	public final static String NAME_PATTERN = "^[A-Za-z0-9.()_\\-äüöÄÜÖß\\s]*$";

	public final static String DESCRIPTION_PATTERN = "^[A-Za-z0-9.,;:\\[\\]()–_\\-äüöÄÜÖß§\\s]*$";

	public final static String URL_PATTERN = "^[A-Za-z0-9.@:%_\\+.~#?&//=\\-]*$";

	public final static String TEXT_KEY_PATTERN = "^[A-Za-z0-9.()_\\-§\\s]*$";

	public final static String TEXT_GESETZ_PATTERN = "^[A-Za-z0-9.()_\\-äüöÄÜÖß§\\s]*$";

	public final static String TEXT_PATTERN = "^[A-Za-z0-9.,;:\\[\\]()–_\\-äüöÄÜÖß§\"„“²°\\s]*$";

	public final static int XS_LENGTH = 50;

	public final static int S_LENGTH = 100;

	public final static int M_LENGTH = 250;

	public final static int L_LENGTH = 1000;

	public final static int XL_LENGTH = 10000;

}
