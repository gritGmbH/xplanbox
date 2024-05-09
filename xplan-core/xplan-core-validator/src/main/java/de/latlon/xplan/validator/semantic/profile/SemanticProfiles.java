/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.profile;

import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SemanticProfiles {

	private final List<RulesMetadata> profilesMetadata = new ArrayList<>();

	private final List<SemanticProfileValidator> semanticProfileValidators = new ArrayList<>();

	/**
	 * @return all RulesMetadata of the configured profiles, may be <code>empty</code> but
	 * never <code>null</code>
	 */
	public List<RulesMetadata> getProfileMetadata() {
		return profilesMetadata;
	}

	/**
	 * @return all SemanticProfileValidator of the configured profiles, may be
	 * <code>empty</code> but never <code>null</code>
	 */
	public List<SemanticProfileValidator> getProfileValidators() {
		return semanticProfileValidators;
	}

	/**
	 * @param profileMetadata to add, should not be <code>null</code>
	 * @param semanticProfileValidator to add, should not be <code>null</code>
	 * @return this SemanticProfiles instance
	 */
	public SemanticProfiles add(RulesMetadata profileMetadata, SemanticProfileValidator semanticProfileValidator) {
		if (profileMetadata != null && semanticProfileValidator != null) {
			this.profilesMetadata.add(profileMetadata);
			this.semanticProfileValidators.add(semanticProfileValidator);
		}
		return this;
	}

}
