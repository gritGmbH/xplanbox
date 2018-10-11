package de.latlon.xplan.validator.web.server.service;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnServletContext;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.client.service.ValidationService;
import de.latlon.xplan.validator.web.shared.ValidationException;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * Executes validation runs
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class XPlanValidationServiceImpl extends RemoteServiceServlet implements ValidationService {

    private static final long serialVersionUID = -6841654850245762811L;

    private static final Logger LOG = LoggerFactory.getLogger( XPlanValidationServiceImpl.class );

    private final PlanArchiveManager planArchiveManager = new PlanArchiveManager();

    protected HttpSession session;

    @Autowired
    private XPlanValidator xPlanValidator;

    @Autowired
    private ReportWriter reportWriter;

    @Override
    public void init( ServletConfig config )
                    throws ServletException {
        super.init( config );
        processInjectionBasedOnServletContext( this, config.getServletContext() );
    }

    @Override
    public void service( final HttpServletRequest request, HttpServletResponse response )
                    throws ServletException, IOException {
        session = request.getSession( true );
        super.service( request, response );
    }

    @Override
    public ValidationSummary validate( ValidationSettings validationSettings )
                    throws ValidationException, IllegalArgumentException {
        try {
            XPlan planToVerify = planArchiveManager.readPlanFromSession( session );
            String planUuid = planToVerify.getId();
            File archive = planArchiveManager.retrieveXPlanArchiveFromFileSystem( planToVerify );
            ValidatorReport report = xPlanValidator.validateNotWriteReport( validationSettings, archive );

            File reportDirectory = planArchiveManager.createReportDirectory( planUuid );
            reportWriter.writeArtefacts( report, planToVerify.getName(), validationSettings.getValidationName(),
                                         reportDirectory );

            return new ValidationSummary( planUuid, validationSettings.getValidationName() );
        } catch ( ValidatorException | IOException e ) {
            LOG.error( "An exception occurred during validation", e );
            throw new ValidationException( e.getMessage() );
        }
    }

}