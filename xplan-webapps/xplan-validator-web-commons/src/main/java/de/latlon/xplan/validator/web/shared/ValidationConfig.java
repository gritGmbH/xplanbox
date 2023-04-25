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
package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the validation configuration.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ValidationConfig implements Serializable {

	private static final long serialVersionUID = 6626853282136320186L;

	private List<ValidationProfile> profiles = new ArrayList<ValidationProfile>();

	public ValidationConfig() {
	}

	public List<ValidationProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<ValidationProfile> profiles) {
		if (profiles != null) {
			this.profiles = profiles;
		}
	}

	public void addProfile(ValidationProfile profile) {
		if (profiles == null)
			profiles = new ArrayList<ValidationProfile>();
		profiles.add(profile);
	}

	@Override
	public String toString() {
		return "ValidationConfig{" + "profiles=" + profiles + '}';
	}

}
