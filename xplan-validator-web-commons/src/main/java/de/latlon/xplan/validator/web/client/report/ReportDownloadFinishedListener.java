package de.latlon.xplan.validator.web.client.report;

/**
 * Indicates that the user finished the report download.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public interface ReportDownloadFinishedListener {

    public enum FinishStatus {
        NEXT, CLOSE
    }

    /**
     * Invoked when the user finished the download via close or next.
     * 
     * @param finishStatus
     *            the status, never <code>null</code>
     */
    void downloadFinished( FinishStatus finishStatus );

}