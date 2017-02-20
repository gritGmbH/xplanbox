package de.latlon.xplan.manager.web.shared;

/**
 * Container for legislation status. Can be used on server and client side.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class LegislationStatus {

    private int codeNumber;

    private String translatedCode;

    /**
     * An empty constructor is mandatory for GWT applications.
     */
    public LegislationStatus() {
    }

    /**
     * @param codeNumber
     *            code number
     */
    public LegislationStatus( int codeNumber ) {
        this( codeNumber, null );
    }

    /**
     * @param codeNumber
     *            code number
     * @param translatedCode
     *            may be <code>null</code>
     */
    public LegislationStatus( int codeNumber, String translatedCode ) {
        this.codeNumber = codeNumber;
        this.translatedCode = translatedCode;
    }

    /**
     * 
     * @return code number
     */
    public int getCodeNumber() {
        return codeNumber;
    }

    /**
     * 
     * @param codeNumber
     *            code number
     */
    public void setCodeNumber( int codeNumber ) {
        this.codeNumber = codeNumber;
    }

    /**
     * 
     * @return translated code, may be <code>null</code>
     */
    public String getTranslatedCode() {
        return translatedCode;
    }

    /**
     * 
     * @param translatedCode
     *            may be <code>null</code>
     */
    public void setTranslatedCode( String translatedCode ) {
        this.translatedCode = translatedCode;
    }

}