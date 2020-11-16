/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.i18n;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * @author erben
 */
public interface XPlanWebMessages extends Messages {

    String loadingConfigurationFailed();

    String loadingAuthorizationInfoFailed();

    String addPlan();

    String uploadButtonTitle();

    String uploadingFile();

    String uploadFailed();

    String nameColumn();

    String idColumn();

    String numberColumn();

    String versionColumn();

    String planArt();

    String sonstPlanArt();

    String load();

    String loadSuccessful();

    String loadFailed();

    String loadNotPossible();

    String validate();

    String validated();

    String validationNoteNotValidated();

    String validationNoteValid();

    String validationNoteInvalid();

    String validationNoteMultipleXPlanElements();

    String validationTitle();

    String mapPreview();

    String actions();

    String reallyDiscardPlan( String name );

    String help();

    String helpContent();

    String close();

    String fileNameMustEndWithZip();

    String fileNameInvalidCharacters();

    String getPlansFailed();

    String downloadPlan();

    String downloadPlanFailed();

    String deletePlan();

    String deleteSuccessful();

    String deleteFailed();

    String editPlanFailed();

    String editPlanAbortedAsNoPlanMatchedId();

    String getPlanToEditFailed();

    String getPlanToEditAbortedAsNoPlanMatchedId();

    String mapPreviewDialogTitle( String planName );

    String errorTitle();

    String urlButton();

    String capabilitiesButton();

    String closeButton();

    String cancelButton();

    String nextButton();

    String configurationException( String message );

    String missingFileName();

    String loadedPlan( String p0, String p1 );

    String reportCloseButtonTitle();

    String reportNextButtonTitle();

    String loadingFile();

    String deletingPlan();

    String editingStarted();

    String editingUploading();

    String editingSaving();

    String retrieveMatchingInternalIdsFailed();

    String noMatchingInternalIdFound();

    String noInternalIdSelected();

    String internalIdDialogBoxTitle();

    String checkingIfCrsIsSetFailed();

    String crsDialogHeader();

    String crsDialogDescription();

    String crsDialogDefaultCrs();

    String crsDialogSelectCrs();

    String crsDialogNoCrsChosen();

    String legislationStatusDialogTitle();

    String legislationStatusDialogButtonText();

    String legislationStatusDialogText( String translatedLegislationStatus );

    String legislationStatusDialogTextWithoutLegislationStatus();

    String legislationStatusDialogFestgestelltOption();

    String legislationStatusDialogFestgestelltSelectedOption();

    String legislationStatusDialogInAufstellungOption();

    String legislationStatusDialogInAufstellungSelectedOption();

    String legislationStatusDialogArchiviertOption();

    String legislationStatusDialogArchiviertSelectedOption();

    String validityPeriodDialogTitle();

    String validityPeriodDialogDescription();

    String validityPeriodDialogTimeTooltip();

    String validityPeriodDialogStartDateTime();

    String validityPeriodDialogEndDateTime();

    String validityPeriodDialogTypeStart();

    String validityPeriodDialogTypeEnd();

    String validityPeriodDialogStartNotBeforeEnd();

    String validityPeriodDialogValidationFailures();

    String legislationStatus();

    String releaseDate();

    String importDate();

    String ade();

    String planStatus();

    String validityStatus();

    String validityTooltip( String startDateTime, String endDateTime );

    String validityTooltipLimitByStartDate( String startDateTime );

    String validityTooltipLimitByEndDate( String startDateTime );

    String validityTooltipUnlimited();

    String filterPlanStatusLabel();

    String filterPlanStatusTooltip();

    String filterPlanStatusSelectionAll();

    String categoryTab();

    String searchTab();

    String searchButton();

    String searchLabel();

    String filterFreeTextTooltip();

    String searchOnAllColumns();

    SafeHtml filterReset();

    String erroneousRasterDataHeader();

    String erroneousRasterDataQuestionForCriticalErrors1();

    String erroneousRasterDataQuestionForCriticalErrors2();

    String erroneousRasterDataQuestionForCrsNotSetErrors1();

    String erroneousRasterDataQuestionForCrsNotSetErrors2( String rasterConfigurationCrs );

    String erroneousRasterDataQuestionForCrsNotSetErrors3();

    String erroneousRasterDataTableName();

    String erroneousRasterDataTableCrs();

    String erroneousRasterDataTableImageFormat();

    String erroneousRasterDataButtonForceImport();

    String erroneousRasterDataButtonImportWithoutRaster();

    String rasterTooltipCrsNotSet();

    String rasterTooltipIsConfiguredCrs( String rasterConfigurationCrs );

    String rasterTooltipNotConfiguredCrs( String rasterCrs, String rasterConfigurationCrs );

    String rasterTooltipIsSupportedImageFormat();

    String rasterTooltipNotSupportedImageFormat();

    String rasterNameAndGeoreferencNameNotSame();

    String rasterAndGeoreferencNotChanged();

    String editButtonTooltip();

    String editButtonTooltipPermissionDenied();

    String editButtonTooltipIncorrectPlanType();

    String editButtonTooltipIncorrectVersion();

    String editSaveButton();

    String editCancelButton();

    String editInvalidInput();

    String editInputRequired();

    String editInvalidDate();

    String editCaptionBasedata();

    String editCaptionBasedataName();

    String editCaptionBasedataDescription();

    String editCaptionBasedataCreationDate();

    String editCaptionBasedataLossDate();

    String editCaptionBasedataPlanType();

    String editCaptionBasedataOtherPlanType();

    String editCaptionBasedataMethod();

    String editCaptionBasedataLegislationStatus();

    String editCaptionBasedataRegulationDate();

    String editCaptionValidityPeriod();

    String editCaptionChanges();

    String editCaptionChangesText();

    String editCaptionChangesPlanName();

    String editCaptionChangesLegalNature();

    String editCaptionChangesNumber();

    String editCaptionChangesType();

    String editCaptionNewChange();

    String editCaptionTexts();

    String editCaptionTextsKey();

    String editCaptionTextsBasis();

    String editCaptionTextsText();

    String editCaptionTextsReference();

    String editCaptionTextsRechtscharakter();

    String editCaptionTextsGeoReference();

    String editCaptionNewText();

    String editCaptionReferences();

    String editCaptionReferencesReference();

    String editCaptionReferencesGeoReference();

    String editCaptionReferencesType();

    String editCaptionNewReference();

    String editCaptionRasterBasis();

    String editCaptionRasterBasisReference();

    String editCaptionRasterBasisGeoReference();

    String editCaptionRasterBasisType();

    String editCaptionRasterBasisGeorefMimeType();

    String editCaptionRasterBasisArt();

    String  editCaptionRasterBasisInformationssystemURL();

    String editCaptionRasterBasisReferenzName();

    String editCaptionRasterBasisReferenzMimeType();

    String editCaptionRasterBasisBeschreibung();

    String editCaptionRasterBasisDatum();

    String editCaptionRasterBasisInvalid();

    String editCaptionNewRasterBasis();

    String editCaptionRasterBasisReferenceNameOrUrl();

    String editCaptionRasterBasisGeoReferenceNotAllowed();

    String editCaptionRasterBasisGeoReferenceMimeTypeNotAllowed();

    String editCaptionChangesDialogEdit();

    String editCaptionChangesDialogNew();

    String editCaptionTextsDialogEdit();

    String editCaptionTextsDialogNew();

    String editCaptionReferencesDialogNew();

    String editCaptionReferencesGreenStructursReferenceAlreadyExists();

    String editCaptionReferencesReasonReferenceAlreadyExists();
    
    String editCaptionRasterBasisDialogEdit();

    String editCaptionRasterBasisDialogNew();

    String editCaptionReferencesCurrentFile();

    String editCaptionReferencesRemoveFileTooltip();

    String editUnsupportedPropertyRefType();

    String publishPlu();

    String publishingPlu();

    String publishingPluSuccessful();

    String publishingPluFailed();

    String publishingPluButtonTooltip();

    String publishingPluButtonTooltipPermissionDenied();

    String publishingPluButtonTooltipIncorrectPlanType();

    String publishingPluButtonTooltipIncorrectVersion();

    String publishingPluButtonTooltipAlreadyPublished();

    String patternMissmatch( String pattern );

    String planNameMismatch();

    String planNameAndStatusDialogHeader();

    String duplicatePlanName( String planName, String planStatus );

}
