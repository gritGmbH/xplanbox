package de.latlon.xplan.validator.report.xml;

import de.latlon.xplan.validator.report.ObjectFactory;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidationReportType;
import de.latlon.xplan.validator.report.ValidatorReport;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;

/**
 * Creates XML reports from a {@link ValidatorReport}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XmlReportGenerator {

    private final JaxbConverter jaxbConverter = new JaxbConverter();

    /**
     * write the complete Report to an OutputStream in XML-Format ( according to validationReport.xsd ) using JAXB
     *
     * @param report         the validation report to serialize, never <code>null</code>
     * @param validationName the Name of the Validation, never <code>null</code>
     * @param planName       the Name of the validated plan, never <code>null</code>
     * @param os             the OutputStream where the XML-Content is written into, never <code>null</code>
     * @throws ReportGenerationException if the generation of the XML report failed
     * @throws IllegalArgumentException  if on of the parameters is <code>null</code>
     */
    public void generateXmlReport( ValidatorReport report, String validationName, String planName, OutputStream os )
          throws ReportGenerationException {
        checkParameters( report, validationName, planName, os );

        ObjectFactory objectFactory = new ObjectFactory();
        ValidationReportType jaxbValidationReport = jaxbConverter.convertValidationReport( report, validationName,
                                                                                           planName );
        JAXBElement<ValidationReportType> jaxbElement = objectFactory.createValidationReport( jaxbValidationReport );
        marshal( os, jaxbElement );
    }

    private void marshal( OutputStream os, JAXBElement<ValidationReportType> jaxbElement )
          throws ReportGenerationException {
        try {
            JAXBContext jc = JAXBContext.newInstance( "de.latlon.xplan.validator.report" );
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            marshaller.marshal( jaxbElement, os );
        } catch ( JAXBException e ) {
            throw new ReportGenerationException( "XML-Report generation failed!", e );
        }
    }

    private void checkParameters( ValidatorReport report, String validationName, String planName, OutputStream os ) {
        if ( report == null )
            throw new IllegalArgumentException( "ValidationReport must not be null" );
        if ( validationName == null )
            throw new IllegalArgumentException( "ValidationName must not be null" );
        if ( planName == null )
            throw new IllegalArgumentException( "PlanName must not be null" );
        if ( os == null )
            throw new IllegalArgumentException( "OutputStream must not be null" );
    }

}