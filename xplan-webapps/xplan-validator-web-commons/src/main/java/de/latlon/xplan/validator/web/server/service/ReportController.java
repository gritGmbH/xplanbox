package de.latlon.xplan.validator.web.server.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import de.latlon.xplan.validator.web.shared.ArtifactType;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

/**
 * REST-Interface providing report artefacts
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
@Controller
@RequestMapping(value = "/report")
public class ReportController {

    private static final Logger LOG = LoggerFactory.getLogger( ReportController.class );

    @Autowired
    private ReportProvider reportProvider;

    @RequestMapping(value = "/html/{uuid}", params = { "validationName" }, method = RequestMethod.GET, produces = TEXT_HTML_VALUE)
    @ResponseBody
    public void getHtmlReport( HttpServletResponse response, 
                               @PathVariable String uuid, 
                               @RequestParam(value = "validationName", required = true) String validationName )
                            throws IOException {
        response.addHeader( "Content-Type", TEXT_HTML_VALUE );
        LOG.debug( "HTML-Report for '{}' and validationName '{}' requested.", uuid, validationName );
        reportProvider.writeHtmlReport( response, uuid, validationName );
        response.setContentType( "text/html" );
    }

    @RequestMapping(value = "/zip/{uuid}", params = { "validationName", "artifacts" }, method = RequestMethod.GET)
    @ResponseBody
    public void getZippedReport( HttpServletResponse response, 
                                 @PathVariable String uuid, 
                                 @RequestParam(value = "validationName", required = true) String validationName, 
                                 @RequestParam(value = "artifacts", required = true) List<ArtifactType> artifacts )
                            throws IOException {
        LOG.debug( "ZIP-Report for '{}' with artifacts {} requested.", uuid, artifacts );
        response.setContentType( "application/zip" );
        response.setHeader( "Content-Disposition", "attachment; filename=\"" + validationName + "-Report.zip\"" );

        reportProvider.writeZipReport( response, uuid, validationName, artifacts );
    }

}