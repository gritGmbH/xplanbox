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

    String planArt();

    String sonstPlanArt();

    String load();

    String loadSuccessful();

    String loadFailed();

    String loadNotPossible();

    String validate();

    String validated();

    String validationTitle();

    String mapPreview();

    String actions();

    String reallyDiscardPlan( String name );

    String help();

    String helpContent();

    String close();

    String fileNameMustEndWithZip();

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

    String filterCategoryLabel();

    String filterCategoryTooltip();

    String filterCategorySelectionAll();

    String filterCategorySelectionOther();

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

    String unauthorizedImport();

    String unauthorizedDelete();

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

    String editCaptionRasterBasisEmptyHint();

    String editCaptionChangesDialogEdit();

    String editCaptionChangesDialogNew();

    String editCaptionTextsDialogEdit();

    String editCaptionTextsDialogNew();

    String editCaptionReferencesDialogNew();

    String editCaptionReferencesGreenStructursReferenceAlreadyExists();

    String editCaptionReferencesReasonReferenceAlreadyExists();
    
    String editCaptionRasterBasisDialogEdit();

    String editCaptionReferencesCurrentFile();

    String editCaptionReferencesRemoveFileTooltip();

    String publishPlu();

    String publishingPlu();

    String unauthorizedPublishingPlu();

    String publishingPluSuccessful();

    String publishingPluFailed();

    String publishingPluButtonTooltip();

    String publishingPluButtonTooltipPermissionDenied();

    String publishingPluButtonTooltipIncorrectPlanType();

    String publishingPluButtonTooltipIncorrectVersion();

    String publishingPluButtonTooltipAlreadyPublished();

}