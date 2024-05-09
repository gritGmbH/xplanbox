/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HeaderAndFooterPageEventHelper extends PdfPageEventHelper {

	private static final String LABEL_TITLE = "Validierungsbericht";

	private final String validationName;

	private final Date date;

	public HeaderAndFooterPageEventHelper(String validationName, Date date) {
		this.validationName = validationName;
		this.date = date;
	}

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(2);
		table.setTotalWidth(document.getPageSize().getWidth() - 68);
		table.setWidths(new int[] { 75, 25 });
		table.setSpacingBefore(5);
		table.setSpacingAfter(5);
		table.getDefaultCell().setPaddingBottom(5);
		table.getDefaultCell().setBorder(Rectangle.BOTTOM);

		Paragraph title = new Paragraph(LABEL_TITLE, new Font(Font.HELVETICA, 20, Font.BOLD));
		PdfPCell titleCell = new PdfPCell(title);
		titleCell.setPaddingBottom(7);
		titleCell.setColspan(2);
		titleCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		titleCell.setBorder(Rectangle.NO_BORDER);

		Font cellFont = new Font(Font.HELVETICA, 10, Font.BOLD);
		String dateAsString = date != null ? DateFormatUtils.format(date, "dd.MM.yyyy HH:mm:ss") : "-";
		Paragraph dateParagraph = new Paragraph(dateAsString, cellFont);
		PdfPCell dateCell = new PdfPCell(dateParagraph);
		dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		dateCell.setBorder(Rectangle.BOTTOM);

		table.addCell(titleCell);
		table.addCell(new Paragraph(validationName, cellFont));
		table.addCell(dateCell);

		table.writeSelectedRows(0, -1, 34, 828, writer.getDirectContent());
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		PdfPTable table = new PdfPTable(1);
		table.setTotalWidth(document.getPageSize().getWidth() - 68);
		table.setWidths(new int[] { 100 });
		table.setSpacingBefore(5);
		table.setSpacingAfter(5);
		table.getDefaultCell().setBorder(Rectangle.TOP);

		Paragraph pageNumberText = new Paragraph("Seite " + document.getPageNumber(), new Font(Font.HELVETICA, 10));
		PdfPCell pageNumberCell = new PdfPCell(pageNumberText);
		pageNumberCell.setPaddingTop(4);
		pageNumberCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pageNumberCell.setBorder(Rectangle.TOP);
		table.addCell(pageNumberCell);

		table.writeSelectedRows(0, -1, 34, 36, writer.getDirectContent());
	}

}
