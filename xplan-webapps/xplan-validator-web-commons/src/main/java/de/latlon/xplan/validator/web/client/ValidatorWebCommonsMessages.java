/*-
 * #%L
 * xplan-validator-web-commons - Modul zur Gruppierung aller Webapps
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
public interface ValidatorWebCommonsMessages extends Messages {

	String validationPopupCancel();

	String validationPopupSave();

	String validationPopupClose();

	String validationOptionsOpen();

	String validationSettingsTitle();

	String uploadLabel();

	String uploadHint();

	String errorTitle();

	String fileNameMustEndWithZip();

	String uploadingFile();

	String uploadFailed();

	String uploadSucessTitle();

	String validationOptionTitle();

	String fieldLabelRunName();

	String defaultRunName();

	String selectionValidationTypeLabel();

	String selectionValidationTypeSem();

	String selectionValidationTypeSyn();

	String selectionValidationTypeGeom();

	String skipFlaechenschluss();

	String skipGeltungsbereich();

	String skipLaufrichtung();

	String selectionProfileLabel();

	String startValidationButton();

	String cancelValidationButton();

	String uploadFinishedNextButton();

	String uploadFinishedCancelButton();

	String loadedPlan();

	String planIsValid();

	String planIsNotValid();

	String correctValidationName();

	String correctValidationType();

	String reportButtonCloseTitle();

	String reportButtonNextTitle();

	String reportDownloadBoxTitle();

	String reportDownloadHtml();

	String reportDownloadXml();

	String reportDownloadPdf();

	String reportDownloadShp();

	String reportDownloadPng();

	String reportDownloadGeometryErrors();

	String reportDownloadButtonTitle();

	String reportDialogTitle();

	String reportDownloadNoArtefactsSelected();

	String validatingStatus();

	String mapPreviewOpenButton();

	String mapPreviewNotAvailableButton();

	String mapPreviewDialogTitle(String planName);

}
