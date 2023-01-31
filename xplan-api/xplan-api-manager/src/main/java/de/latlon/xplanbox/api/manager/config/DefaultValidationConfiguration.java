/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.config;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DefaultValidationConfiguration {

	private final boolean skipSemantisch;

	private final boolean skipGeometrisch;

	private final boolean skipFlaechenschluss;

	private final boolean skipGeltungsbereich;

	private final boolean skipLaufrichtung;

	/**
	 * Default validation configuration. Nothing is skipped.
	 */
	public DefaultValidationConfiguration() {
		this(false, false, false, false, false);
	}

	/**
	 * @param skipSemantisch <code>true</code> if the semantic validation should be
	 * skipped, <code>false</code> otherwise
	 * @param skipGeometrisch <code>true</code> if the geometric validation should be
	 * skipped, <code>false</code> otherwise
	 * @param skipFlaechenschluss <code>true</code> if the flaechenschluss validation
	 * should be skipped, <code>false</code> otherwise
	 * @param skipGeltungsbereich <code>true</code> if the geltungsbereich validation
	 * should be skipped, <code>false</code> otherwise
	 * @param skipLaufrichtung <code>true</code> if the laufrichtung validation should be
	 * skipped, <code>false</code> otherwise
	 */
	public DefaultValidationConfiguration(boolean skipSemantisch, boolean skipGeometrisch, boolean skipFlaechenschluss,
			boolean skipGeltungsbereich, boolean skipLaufrichtung) {
		this.skipSemantisch = skipSemantisch;
		this.skipGeometrisch = skipGeometrisch;
		this.skipFlaechenschluss = skipFlaechenschluss;
		this.skipGeltungsbereich = skipGeltungsbereich;
		this.skipLaufrichtung = skipLaufrichtung;
	}

	/**
	 * @return <code>true</code> if the semantic validation should be skipped,
	 * <code>false</code> otherwise
	 */
	public boolean isSkipSemantisch() {
		return skipSemantisch;
	}

	/**
	 * @return <code>true</code> if the geometric validation should be skipped,
	 * <code>false</code> otherwise
	 */
	public boolean isSkipGeometrisch() {
		return skipGeometrisch;
	}

	/**
	 * @return <code>true</code> if the flaechenschluss validation should be skipped,
	 * <code>false</code> otherwise
	 */
	public boolean isSkipFlaechenschluss() {
		return skipFlaechenschluss;
	}

	/**
	 * @return <code>true</code> if the geltungsbereich validation should be skipped,
	 * <code>false</code> otherwise
	 */
	public boolean isSkipGeltungsbereich() {
		return skipGeltungsbereich;
	}

	/**
	 * @return <code>true</code> if the laufrichtung validation should be skipped,
	 * <code>false</code> otherwise
	 */
	public boolean isSkipLaufrichtung() {
		return skipLaufrichtung;
	}

}
