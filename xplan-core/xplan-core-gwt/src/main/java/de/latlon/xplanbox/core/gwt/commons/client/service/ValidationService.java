/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationException;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationSummary;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplanbox.core.gwt.commons.shared.InvalidParameterException;
import de.latlon.xplan.validator.web.shared.ValidationSettings;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@RemoteServiceRelativePath("validation")
public interface ValidationService extends RemoteService {

	/**
	 * Start a validation run with the plan from the session.
	 * @param validationSettings used for the validation, never <code>null</code>
	 * @return the uuid of the plan
	 * @throws ValidatorException if an exception occurred during validation
	 */
	ValidationSummary validate(ValidationSettings validationSettings)
			throws ValidationException, IllegalArgumentException, InvalidParameterException;

	boolean poll();

}
