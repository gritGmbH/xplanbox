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
package de.latlon.xplan.manager.web.client.gui.dialog;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;

import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;

/**
 * Wizard for import of raster plans.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class RasterDialog extends DialogBox {

	private static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	private List<RasterHandler> rasterHandlers = new ArrayList<RasterHandler>();

	private Button cancelButton;

	public RasterDialog(List<RasterEvaluationResult> rasterResults) {
		cancelButton = createCancelButton();
		createLayout(rasterResults);
	}

	/**
	 * Add a {@link RasterHandler}.
	 * @param handler never <code>null</code>
	 */
	public void addRasterHandler(RasterHandler handler) {
		rasterHandlers.add(handler);
	}

	/**
	 * @param clickHandler clickHandler to add to the cancel button, never
	 * <code>null</code>
	 */
	public void addCancelClickedHandler(ClickHandler clickHandler) {
		cancelButton.addClickHandler(clickHandler);
	}

	private void createLayout(List<RasterEvaluationResult> rasterResults) {
		setText(MESSAGES.erroneousRasterDataHeader());
		VerticalPanel mainPanel = createMainPanel(rasterResults);
		setWidget(mainPanel);
		center();
		show();
	}

	private VerticalPanel createMainPanel(List<RasterEvaluationResult> rasterResults) {
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setSpacing(20);
		mainPanel.setWidth("100%");
		mainPanel.setHorizontalAlignment(ALIGN_CENTER);
		mainPanel.add(createResultTable(rasterResults));
		if (checkIfJustCrsNotSet(rasterResults)) {
			createCrsNotSetErrorsDialog(mainPanel, rasterResults);
		}
		else {
			createCriticalErrorsDialog(mainPanel);
		}
		return mainPanel;
	}

	private void createCrsNotSetErrorsDialog(VerticalPanel mainPanel, List<RasterEvaluationResult> rasterResults) {
		mainPanel.add(new Label(MESSAGES.erroneousRasterDataQuestionForCrsNotSetErrors1()));
		String firstRasterConfigurationCrs = rasterResults.get(0).getRasterConfigurationCrs();
		mainPanel.add(new Label(MESSAGES.erroneousRasterDataQuestionForCrsNotSetErrors2(firstRasterConfigurationCrs)));
		mainPanel.add(createButtonPanel(createForceImportButton()));
		mainPanel.add(new Label(MESSAGES.erroneousRasterDataQuestionForCrsNotSetErrors3()));
		mainPanel.add(createButtonPanel(cancelButton, createImportWithoutRasterButton()));
	}

	private void createCriticalErrorsDialog(VerticalPanel mainPanel) {
		mainPanel.add(new Label(MESSAGES.erroneousRasterDataQuestionForCriticalErrors1()));
		mainPanel.add(new Label(MESSAGES.erroneousRasterDataQuestionForCriticalErrors2()));
		mainPanel.add(createButtonPanel(cancelButton, createImportWithoutRasterButton()));
	}

	private HorizontalPanel createButtonPanel(Button... buttonList) {
		HorizontalPanel buttonPanel = new HorizontalPanel();
		buttonPanel.setSpacing(10);
		for (Button button : buttonList)
			buttonPanel.add(button);
		return buttonPanel;
	}

	private CellTable<RasterEvaluationResult> createResultTable(List<RasterEvaluationResult> rasterResults) {
		CellTable<RasterEvaluationResult> resultTable = new CellTable<RasterEvaluationResult>();
		addNameColumn(resultTable);
		addCrsColumn(resultTable);
		addImageFormatColumn(resultTable);
		resultTable.setRowData(0, rasterResults);
		return resultTable;
	}

	private void addNameColumn(CellTable<RasterEvaluationResult> resultTable) {
		TextColumn<RasterEvaluationResult> nameColumn = new TextColumn<RasterEvaluationResult>() {
			@Override
			public String getValue(RasterEvaluationResult object) {
				return object.getRasterName();
			}
		};
		resultTable.addColumn(nameColumn, MESSAGES.erroneousRasterDataTableName());
	}

	private void addCrsColumn(final CellTable<RasterEvaluationResult> resultTable) {
		TextCell crsCell = new TextCell();
		Column<RasterEvaluationResult, String> crsColumn = new Column<RasterEvaluationResult, String>(crsCell) {
			@Override
			public String getValue(RasterEvaluationResult object) {
				return " ";
			}

			@Override
			public String getCellStyleNames(Cell.Context context, RasterEvaluationResult object) {
				return "cellButton " + (object.isConfiguredCrs() ? "buttonValid" : "buttonNotValid");
			}
		};
		resultTable.addCellPreviewHandler(new CellPreviewEvent.Handler<RasterEvaluationResult>() {
			@Override
			public void onCellPreview(CellPreviewEvent<RasterEvaluationResult> event) {
				if ("mouseover".equals(event.getNativeEvent().getType())) {
					int index = event.getIndex();
					String tooltip = createTooltip(event);
					resultTable.getRowElement(index).getCells().getItem(1).setTitle(tooltip);
				}
			}

			private String createTooltip(CellPreviewEvent<RasterEvaluationResult> event) {
				RasterEvaluationResult evaluationResult = event.getValue();
				if (evaluationResult.isCrsSet()) {
					if (evaluationResult.isConfiguredCrs())
						return MESSAGES.rasterTooltipIsConfiguredCrs(evaluationResult.getRasterConfigurationCrs());
					return MESSAGES.rasterTooltipNotConfiguredCrs(evaluationResult.getRasterCrs(),
							evaluationResult.getRasterConfigurationCrs());
				}
				return MESSAGES.rasterTooltipCrsNotSet();
			}
		});
		resultTable.addColumn(crsColumn, MESSAGES.erroneousRasterDataTableCrs());
	}

	private void addImageFormatColumn(final CellTable<RasterEvaluationResult> resultTable) {
		TextCell imageFormatCell = new TextCell();
		Column<RasterEvaluationResult, String> imageFormatColumn = new Column<RasterEvaluationResult, String>(
				imageFormatCell) {
			@Override
			public String getValue(RasterEvaluationResult object) {
				return " ";
			}

			@Override
			public String getCellStyleNames(Cell.Context context, RasterEvaluationResult object) {
				return "cellButton " + (object.isSupportedImageFormat() ? "buttonValid" : "buttonNotValid");
			}
		};
		resultTable.addCellPreviewHandler(new CellPreviewEvent.Handler<RasterEvaluationResult>() {
			@Override
			public void onCellPreview(CellPreviewEvent<RasterEvaluationResult> event) {
				if ("mouseover".equals(event.getNativeEvent().getType())) {
					int index = event.getIndex();
					String tooltip = createTooltip(event);
					resultTable.getRowElement(index).getCells().getItem(2).setTitle(tooltip);
				}
			}

			private String createTooltip(CellPreviewEvent<RasterEvaluationResult> event) {
				RasterEvaluationResult evaluationResult = event.getValue();
				if (evaluationResult.isSupportedImageFormat())
					return MESSAGES.rasterTooltipIsSupportedImageFormat();
				return MESSAGES.rasterTooltipNotSupportedImageFormat();
			}
		});
		resultTable.addColumn(imageFormatColumn, MESSAGES.erroneousRasterDataTableImageFormat());
	}

	private Button createForceImportButton() {
		Button forceImportButton = new Button();
		forceImportButton.setText(MESSAGES.erroneousRasterDataButtonForceImport());
		forceImportButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
				informRasterHandlersWithForce();
			}
		});
		return forceImportButton;
	}

	private Button createImportWithoutRasterButton() {
		Button importWithoutRasterButton = new Button();
		importWithoutRasterButton.setText(MESSAGES.erroneousRasterDataButtonImportWithoutRaster());
		importWithoutRasterButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
				informRasterHandlers();
			}
		});
		return importWithoutRasterButton;
	}

	private Button createCancelButton() {
		Button cancelButton = new Button();
		cancelButton.setText(MESSAGES.cancelButton());
		cancelButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		return cancelButton;
	}

	private boolean checkIfJustCrsNotSet(List<RasterEvaluationResult> results) {
		for (RasterEvaluationResult result : results) {
			if ((result.isCrsSet() && !result.isConfiguredCrs()) || !result.isSupportedImageFormat())
				return false;
		}
		return true;
	}

	private void informRasterHandlers() {
		for (RasterHandler handler : rasterHandlers)
			handler.onConfirmImport();
	}

	private void informRasterHandlersWithForce() {
		for (RasterHandler handler : rasterHandlers)
			handler.onConfirmForceImport();
	}

}
