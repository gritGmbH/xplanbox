package de.latlon.xplan.validator.web.client;

import com.google.gwt.i18n.client.Messages;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
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
}