package de.latlon.xplan.validator.web.server.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import de.latlon.xplan.validator.web.shared.ArtifactType;

/**
 * Provides report artefacts
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface ReportProvider {

    /**
     * @param response
     *            the response to write the html report in, never <code>null</code>
     * @param planUuid
     *            the uuid of the plan to write the validation result, never <code>null</code>
     * @param validationName
     *            the name of the validation run to write, never <code>null</code>
     * @throws IOException
     */
    void writeHtmlReport( HttpServletResponse response, String planUuid, String validationName )
                            throws IOException;

    /**
     * @param response
     *            the response to write the html report in, never <code>null</code>
     * @param planUuid
     *            the uuid of the plan to write the validation result, never <code>null</code>
     * @param validationName
     *            the name of the validation run to write, never <code>null</code>
     * @param artifacts
     *            a list of artifacts to put in the zip archive, never <code>null</code>
     * @throws IOException
     */
    void writeZipReport( HttpServletResponse response, String planUuid, String validationName,
                         List<ArtifactType> artifacts )
                            throws IOException;
}