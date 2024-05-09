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
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;

import java.io.OutputStream;

/**
 * Creates a pdf from a {@link ValidatorReport}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PdfReportGenerator {

	private final ReportBuilder reportBuilder = new ReportBuilder();

	/**
	 * Writes the {@link ValidatorReport} to an OutputStream in PDF-Format
	 * @param report the validation report to serialize, never <code>null</code>
	 * @param os the OutputStream where the PDF-Content is written into, never
	 * <code>null</code>
	 * @throws ReportGenerationException if an exception occurred during writing the
	 * report
	 * @throws IllegalArgumentException if the passed report or outputstream are
	 * <code>null</code>
	 */
	public void createPdfReport(ValidatorReport report, OutputStream os) throws ReportGenerationException {
		checkParameters(report, os);
		Document document = new Document(PageSize.A4, 36, 36, 65, 36);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, os);
			writer.setCloseStream(false);
			writer.setPageEvent(new HeaderAndFooterPageEventHelper(report.getValidationName(), report.getDate()));
			document.open();
			reportBuilder.writeReport(report, document);
		}
		catch (Throwable e) {
			throw new ReportGenerationException(e);
		}
		finally {
			document.close();
		}
	}

	private void checkParameters(ValidatorReport report, OutputStream os) {
		if (report == null)
			throw new IllegalArgumentException("Report must not be null!");
		if (os == null)
			throw new IllegalArgumentException("OutputStream must not be null!");
	}

}
