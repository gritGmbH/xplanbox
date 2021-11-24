/*-
 * #%L
 * xplan-validator-web - Modul zur Gruppierung aller Webapps
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
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

	String uploadSucessTitle();

	String fieldLabelRunName();

	String defaultRunName();

	String selectionValidationTypeHint();

	String selectionValidationTypeSem();

	String selectionValidationTypeSyn();

	String selectionValidationTypeGeom();

	String moreOptions();

	String extendedOptionsDialogTitle();

	String ignoreSelfIntersection();

	String ignoreOrientation();

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
