package de.latlon.xplan.validator.report;

/**
 * Indicates that an error occurred during creating a (pdf, html, ...)report
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReportGenerationException extends Exception {

    private static final long serialVersionUID = 1054070896312606284L;

    public ReportGenerationException( String message ) {
        super( message );
    }

    public ReportGenerationException( String message, Throwable reason ) {
        super( message, reason );
    }

    public ReportGenerationException( Throwable reason ) {
        super( reason );
    }

}