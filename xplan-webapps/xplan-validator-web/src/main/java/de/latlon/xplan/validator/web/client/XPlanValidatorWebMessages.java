/*-
 * #%L
 * xplan-validator-web - Modul zur Gruppierung aller Webapps
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
package de.latlon.xplan.validator.web.client;

import com.google.gwt.i18n.client.Messages;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
interface XPlanValidatorWebMessages extends Messages {

	String validationPopupCancel();

	String validationPopupSave();

	String validationOptionsOpen();

	String validationSettingsTitle();

	String uploadLabel();

	String uploadHint();

	String errorTitle();

	String fileNameMustEndWithZip();

	String fileNameInvalidCharacters();

	String uploadingFile();

	String uploadFailed();

	String uploadSecurityException();

	String uploadSuccessTitle();

	String fieldLabelRunName();

	String defaultRunName();

	String selectionValidationTypeHint();

	String selectionValidationTypeSem();

	String selectionValidationTypeSyn();

	String selectionValidationTypeGeom();

	String moreOptions();

	String ignorePresentation();

	String ignoreOther();

	String nodeTolerance();

	String nodeToleranceUnit();

	String startValidationButton();

	String uploadFinishedNextButton();

	String uploadFinishedCancelButton();

	String loadedPlan();

	String planIsValid();

	String planIsNotValid();

	String correctInputText();

	String correctValidationType();

	String openUserManual();

}
