/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.gui;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.HasRpcToken;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.client.rpc.XsrfTokenService;
import com.google.gwt.user.client.rpc.XsrfTokenServiceAsync;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.CellPreviewEvent.Handler;
import com.google.gwt.view.client.ListDataProvider;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.client.service.ManagerService;
import de.latlon.xplan.manager.web.client.service.ManagerServiceAsync;
import de.latlon.xplan.manager.web.client.utils.AlertFailureCallback;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplanbox.core.gwt.commons.client.ValidatorOptionsDialog;
import de.latlon.xplanbox.core.gwt.commons.client.report.ReportDownloadFinishedListener;
import de.latlon.xplanbox.core.gwt.commons.shared.ValidationConfig;
import de.latlon.xplanbox.core.gwt.commons.web.DisengageableButtonCell;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplanbox.core.gwt.commons.client.report.ReportDownloadFinishedListener.FinishStatus.NEXT;

/**
 * Files system panel of the xplan manager web gui.
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class UploadPanel extends DecoratorPanel {

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	private final CellTable<XPlan> uploadedPlanTable = new CellTable<XPlan>();

	private final ListDataProvider<XPlan> dataProviderFileSystem = new ListDataProvider<XPlan>();

	private final ImportWizardCreator importWizardCreator;

	private DialogBox uploading;

	private final FileUpload upload = new FileUpload();

	private ValidationConfig validationConfig;

	public UploadPanel(ManagerWebConfiguration configuration, ValidationConfig validationConfig,
			PlanListPanel planListPanel) {
		this.validationConfig = validationConfig;
		this.importWizardCreator = new ImportWizardCreator(configuration, planListPanel);
		createUi();
	}

	private void createUi() {
		HorizontalPanel uploadPanel = createUploadPanel();
		initUploadedPlanTable();
		FlexTable layout = createLayout(uploadPanel);
		this.setWidget(layout);
	}

	private HorizontalPanel createUploadPanel() {
		HorizontalPanel xPlanTableFileSystemHeader = new HorizontalPanel();
		xPlanTableFileSystemHeader.setSpacing(10);
		xPlanTableFileSystemHeader.add(createUploadWidget());
		xPlanTableFileSystemHeader.add(createHelpButton());
		return xPlanTableFileSystemHeader;
	}

	private FlexTable createLayout(HorizontalPanel uploadPanel) {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_CENTER);
		formatter.setHorizontalAlignment(2, 1, ALIGN_CENTER);
		layout.setWidget(1, 1, uploadPanel);
		layout.setWidget(2, 1, uploadedPlanTable);
		return layout;
	}

	private void initUploadedPlanTable() {
		uploadedPlanTable.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
		uploadedPlanTable.setPageSize(1);
		initUploadedPlanTableColumns();
		dataProviderFileSystem.addDataDisplay(uploadedPlanTable);
		uploadedPlanTable.setVisible(false);
	}

	private void initUploadedPlanTableColumns() {
		addNameColumn(uploadedPlanTable);
		addValidationStatusColumn(uploadedPlanTable);
		addValidationNoteColumn(uploadedPlanTable);
		addValidationColumn(uploadedPlanTable);
		addImportColumn(uploadedPlanTable);
		addRemoveColumn(uploadedPlanTable);
	}

	private void addNameColumn(CellTable<XPlan> xPlanTable) {
		TextColumn<XPlan> nameColumn = new TextColumn<XPlan>() {
			@Override
			public String getValue(XPlan object) {
				return object.getName();
			}
		};
		nameColumn.setSortable(true);
		xPlanTable.addColumn(nameColumn, messages.nameColumn());
	}

	private void addValidationColumn(CellTable<XPlan> xPlanTable) {
		ButtonCell validatedButtonCell = new ButtonCell();
		Column<XPlan, String> validatedColumn = new Column<XPlan, String>(validatedButtonCell) {
			@Override
			public String getValue(XPlan object) {
				return messages.validate();
			}
		};
		validatedColumn.setFieldUpdater(new FieldUpdater<XPlan, String>() {
			public void update(int index, XPlan object, String value) {
				final DialogBox dialog = new DialogBox();
				dialog.setText(messages.validationTitle());
				ValidatorOptionsDialog validatorOptions = createValidatorOptions(dialog);
				dialog.add(validatorOptions);
				dialog.show();
			}

		});
		validatedColumn.setSortable(true);
		xPlanTable.addColumn(validatedColumn);
	}

	private void addValidationStatusColumn(CellTable<XPlan> xPlanTable) {
		TextCell validatedButtonCell = new TextCell();
		Column<XPlan, String> validatedColumn = new Column<XPlan, String>(validatedButtonCell) {
			@Override
			public String getValue(XPlan object) {
				return " ";
			}

			@Override
			public String getCellStyleNames(Cell.Context context, XPlan object) {
				if (object.isValidated())
					return "cellButton " + (object.isValid() && !object.isHasUnresolvedReferences() ? "buttonValid"
							: "buttonNotValid");
				else
					return "cellButton buttonNotValidated";
			}
		};
		xPlanTable.addColumn(validatedColumn, messages.validated());
	}

	private void addValidationNoteColumn(CellTable<XPlan> xPlanTable) {
		TextCell validatedNoteCell = new TextCell();
		Column<XPlan, String> validatedColumn = new Column<XPlan, String>(validatedNoteCell) {
			@Override
			public String getValue(XPlan object) {
				if (!object.isValidated())
					return messages.validationNoteNotValidated();
				else if (!object.isValid())
					if (object.isHasUnresolvedReferences())
						return messages.validationNoteInvalidAndUnresolvedReferences();
					else
						return messages.validationNoteInvalid();
				if (object.isHasUnresolvedReferences())
					return messages.validationNoteUnresolvedReferences();
				return messages.validationNoteValid();
			}
		};
		xPlanTable.addColumn(validatedColumn);
	}

	private void addImportColumn(CellTable<XPlan> xPlanTable) {
		final DisengageableButtonCell importButtonCell = new DisengageableButtonCell();
		importButtonCell.setDisabled();
		Column<XPlan, String> importButtonColumn = new Column<XPlan, String>(importButtonCell) {
			@Override
			public String getValue(XPlan object) {
				if (object.isValid() && !object.isHasUnresolvedReferences())
					importButtonCell.setEnabled();
				else
					importButtonCell.setDisabled();
				return messages.load();
			}
		};
		importButtonColumn.setFieldUpdater(new FieldUpdater<XPlan, String>() {
			public void update(int index, XPlan object, String value) {
				if (object.isValid() && !object.isHasUnresolvedReferences()) {
					importWizardCreator.importPlan(object.getId(), object.isHasMultipleXPlanElements());
				}
				else {
					Window.alert(messages.loadNotPossible());
				}
			}
		});
		xPlanTable.addColumn(importButtonColumn);
	}

	private void addRemoveColumn(final CellTable<XPlan> xPlanTable) {
		ButtonCell removeButtonCell = new ButtonCell();
		final Column<XPlan, String> removeButtonColumn = new Column<XPlan, String>(removeButtonCell) {
			@Override
			public String getValue(XPlan object) {
				return "";
			}
		};
		removeButtonColumn.setFieldUpdater(new FieldUpdater<XPlan, String>() {
			public void update(int index, XPlan object, String value) {
				if (Window.confirm(messages.reallyDiscardPlan(object.getName())))
					removePlan(object.getId());
			}
		});
		removeButtonColumn.setCellStyleNames("removeButtonColumn");
		xPlanTable.addCellPreviewHandler(new Handler<XPlan>() {
			@Override
			public void onCellPreview(CellPreviewEvent<XPlan> event) {
				int columnIndex = event.getColumn();
				if (xPlanTable.getColumnIndex(removeButtonColumn) == columnIndex
						&& "mouseover".equals(event.getNativeEvent().getType())) {
					int index = event.getIndex();
					xPlanTable.getRowElement(index).getCells().getItem(columnIndex).setTitle(messages.deletePlan());
				}
			}
		});
		xPlanTable.addColumn(removeButtonColumn);
	}

	private ValidatorOptionsDialog createValidatorOptions(final DialogBox dialog) {
		ReportDownloadFinishedListener reportDownloadFinishedListener = new ReportDownloadFinishedListener() {
			@Override
			public void downloadFinished(FinishStatus finishStatus) {
				reload();
				if (NEXT.equals(finishStatus))
					dialog.hide();
			}
		};
		ClickHandler cancelHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				dialog.hide();
			}
		};
		return new ValidatorOptionsDialog(validationConfig, reportDownloadFinishedListener,
				messages.reportCloseButtonTitle(), messages.reportNextButtonTitle(), getFilename(upload), cancelHandler,
				false);
	}

	private Widget createUploadWidget() {
		HorizontalPanel vPanel = new HorizontalPanel();
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		vPanel.getElement().setId("fileUploadPanel");
		final FormPanel form = new FormPanel();
		form.setAction(GWT.getHostPageBaseURL() + GWT.getModuleName() + "/rest/manager/plan");
		form.setEncoding(FormPanel.ENCODING_MULTIPART);
		form.setMethod(FormPanel.METHOD_POST);

		form.setWidget(vPanel);
		Label l = new Label(messages.addPlan());
		l.setStylePrimaryName("stdFont");
		vPanel.add(l);

		upload.setName("planZipFile");
		upload.setStylePrimaryName("stdFont");
		vPanel.add(upload);

		Button uploadButton = createUploadButton(form, upload);
		uploadButton.setStylePrimaryName("stdFont");
		vPanel.add(uploadButton);

		form.addSubmitHandler(new FormPanel.SubmitHandler() {
			@Override
			public void onSubmit(FormPanel.SubmitEvent event) {
				if (upload.getFilename().length() == 0) {
					uploading.hide();
					Window.alert(messages.uploadFailed());
					event.cancel();
				}
			}
		});
		form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
			@Override
			public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
				uploading.hide();
				reload();
				if (event.getResults().contains("Content type"))
					Window.alert(messages.uploadSecurityException());
				else
					Window.alert(event.getResults());
			}
		});

		return form;
	}

	private Button createUploadButton(final FormPanel form, final FileUpload upload) {
		return new Button(messages.uploadButtonTitle(), new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (!isSupportedType(upload))
					showInvalidFileDialog(messages.fileNameMustEndWithZip());
				else if (!isValidFileName(upload))
					showInvalidFileDialog(messages.fileNameInvalidCharacters());
				else {
					form.submit();
					showUploadDialogBox();
				}
			}

			private boolean isSupportedType(final FileUpload upload) {
				String filename = upload.getFilename().toLowerCase();
				return filename.endsWith(".zip") || filename.endsWith(".xml") || filename.endsWith(".gml");
			}

			private boolean isValidFileName(final FileUpload upload) {
				String fileName = getFilename(upload);
				return fileName.matches("[a-zA-Z0-9.()_-]*");
			}

			private void showInvalidFileDialog(String message) {
				final DialogBox errorUpload = new DialogBox(false, true);
				Button closeButton = new Button(messages.close(), new ClickHandler() {
					public void onClick(ClickEvent event) {
						errorUpload.hide();
					}
				});
				errorUpload.setText(message);
				errorUpload.add(closeButton);
				errorUpload.center();
				errorUpload.show();
			}

			private void showUploadDialogBox() {
				uploading = new DialogBox(false, true);
				uploading.setText(messages.uploadingFile());
				uploading.center();
				uploading.show();
			}
		});
	}

	private Button createHelpButton() {
		Button help = new Button(messages.help());
		help.setStylePrimaryName("stdFont");
		help.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				final DialogBox helpDialog = new DialogBox(false, true);
				Button closeButton = new Button(messages.close(), new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						helpDialog.hide();
					}
				});
				VerticalPanel vPanel = new VerticalPanel();
				HTML html = new HTML(messages.helpContent());
				vPanel.add(html);
				vPanel.add(closeButton);
				helpDialog.add(vPanel);
				helpDialog.setWidth("400px");
				helpDialog.center();
				helpDialog.show();
			}
		});
		return help;
	}

	private void removePlan(String id) {
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = GWT.create(ManagerService.class);
				((HasRpcToken) managerService).setRpcToken(token);
				managerService.removePlanFromFileSystem(id, new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						reload();
						Window.alert(caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						reload();
						if (result) {
							Window.alert(messages.deleteSuccessful());
						}
					}
				});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private void reload() {
		XsrfTokenServiceAsync xsrf = (XsrfTokenServiceAsync) GWT.create(XsrfTokenService.class);
		((ServiceDefTarget) xsrf).setServiceEntryPoint(GWT.getModuleBaseURL() + "xsrf");
		xsrf.getNewXsrfToken(new AsyncCallback<XsrfToken>() {
			public void onSuccess(XsrfToken token) {
				ManagerServiceAsync managerService = GWT.create(ManagerService.class);
				((HasRpcToken) managerService).setRpcToken(token);
				managerService.getPlanFromLocal(new AlertFailureCallback<XPlan>() {

					@Override
					public void onSuccess(XPlan plan) {
						updatePlanTable(plan);
					}

					private void updatePlanTable(XPlan plan) {
						List<XPlan> list = dataProviderFileSystem.getList();
						list.clear();
						int newRowCount = plan != null ? 1 : 0;
						uploadedPlanTable.setRowCount(newRowCount, true);
						if (plan != null) {
							list.add(plan);
							uploadedPlanTable.setVisible(true);
						}
						else {
							uploadedPlanTable.setVisible(false);
						}
						ColumnSortEvent.fire(uploadedPlanTable, uploadedPlanTable.getColumnSortList());
					}
				});
			}

			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}
		});
	}

	private String getFilename(FileUpload upload) {
		if (upload != null) {
			try {
				String filename = upload.getFilename();
				int indexOfSep = filename.lastIndexOf("\\") + 1;
				filename = filename.substring(indexOfSep);
				int indexOfPref = filename.lastIndexOf(".");
				filename = filename.substring(0, indexOfPref);
				return filename;
			}
			catch (Exception e) {
			}
		}
		return null;
	}

}
