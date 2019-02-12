package de.latlon.xplan.validator.web.server.service;

import static org.apache.commons.io.IOUtils.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.web.shared.ArtifactType;

/**
 * Provides reports after validation
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidatorReportProvider implements ReportProvider {

    private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

    @Autowired
    private ReportWriter reportWriter;

    @Override
    public void writeHtmlReport( HttpServletResponse response, String planUuid, String validationName )
                            throws IOException {
        File planDirectory = planArchiveManager.createReportDirectory( planUuid );
        File htmlReport = reportWriter.retrieveHtmlReport( validationName, planDirectory );
        try (FileInputStream fileInputStream = new FileInputStream( htmlReport )) {
            copy( fileInputStream, response.getOutputStream() );
        }
    }

    @Override
    public void writeZipReport( HttpServletResponse response, String planUuid, String validationName,
                                List<ArtifactType> artifacts )
                            throws IOException {
        File planDirectory = planArchiveManager.createReportDirectory( planUuid );
        reportWriter.writeZipWithArtifacts( response.getOutputStream(), validationName, artifacts, planDirectory );
    }

}