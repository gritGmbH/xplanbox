package de.latlon.xplan.manager.web.server.service;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.apache.commons.io.IOUtils.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import de.latlon.xplan.validator.web.shared.ArtifactType;

public class ManagerReportProvider implements ReportProvider {

    private final ManagerPlanArchiveManager planArchiveManager = new ManagerPlanArchiveManager();

    private final ReportWriter reportWriter;

    public ManagerReportProvider( ReportWriter reportWriter ) {
        this.reportWriter = reportWriter;
    }

    @Override
    public void writeHtmlReport( HttpServletResponse response, String planUuid, String validationName )
                            throws IOException {
        File planDirectory = planArchiveManager.createReportDirectory( planUuid );
        File htmlReport = reportWriter.retrieveReport( HTML, validationName, planDirectory );
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