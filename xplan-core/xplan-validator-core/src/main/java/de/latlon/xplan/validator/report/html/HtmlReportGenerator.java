/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.html;

import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.xml.XmlReportGenerator;
import org.apache.commons.io.IOUtils;
import org.slf4j.LoggerFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Utility methods for generating reports
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class HtmlReportGenerator {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger( ValidatorReport.class );

    private static final String XSLT_FILE_PATH = "xslt/report.xslt";

    /**
     * Writes the complete report to an OutputStream in HTML-Format.
     *
     * @param report         the validation report to serialize, never <code>null</code>
     * @throws ReportGenerationException if the generation of the XML report failed
     * @throws IllegalArgumentException  if on of the parameters is <code>null</code>
     */
    public void generateHtmlReport( ValidatorReport report, OutputStream htmlOut )
          throws ReportGenerationException {
        checkParameters( report, htmlOut );
        ByteArrayOutputStream xmlOut = writeXmlToStream( report );

        try (InputStream xmlIn = new ByteArrayInputStream( xmlOut.toByteArray() ); InputStream xslStream = loadXslt()) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer( new StreamSource( xslStream ) );
            transformer.transform( new StreamSource( xmlIn ), new StreamResult( htmlOut ) );
        } catch ( TransformerException e ) {
            LOG.debug( "Could not perform transformation to HTML output. Reason: ", e );
            throw new ReportGenerationException( "HTML-Report generation failed!", e );
        } catch ( IOException e ) {
            throw new ReportGenerationException( "HTML-Report generation failed!", e );
        }
    }

    private ByteArrayOutputStream writeXmlToStream( ValidatorReport report )
          throws ReportGenerationException {
        ByteArrayOutputStream xmlOut = new ByteArrayOutputStream();
        try {
            XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
            xmlReportGenerator.generateXmlReport( report, xmlOut );
            return xmlOut;
        } finally {
            IOUtils.closeQuietly( xmlOut );
        }
    }

    private static InputStream loadXslt() {
        return HtmlReportGenerator.class.getClassLoader().getResourceAsStream( XSLT_FILE_PATH );
    }

    private void checkParameters( ValidatorReport report, OutputStream os ) {
        if ( report == null )
            throw new IllegalArgumentException( "ValidationReport must not be null" );
        if ( os == null )
            throw new IllegalArgumentException( "OutputStream must not be null" );
    }

}
