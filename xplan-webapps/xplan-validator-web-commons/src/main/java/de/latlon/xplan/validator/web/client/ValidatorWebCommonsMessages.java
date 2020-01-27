package de.latlon.xplan.validator.web.client;

import com.google.gwt.i18n.client.Messages;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
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

    String fieldLabelRunName();

    String defaultRunName();

    String selectionValidationTypeLabel();

    String selectionValidationTypeSem();

    String selectionValidationTypeSyn();

    String selectionValidationTypeGeom();

    String ignoreSelfIntersection();

    String ignoreOrientation();

    String skipFlaechenschluss();

    String skipGeltungsbereich();

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

    String mapPreviewDialogTitle( String planName );

}