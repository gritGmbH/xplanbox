package de.latlon.xplanbox.api.manager.config;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DefaultValidationConfiguration {

    private final boolean skipSemantisch;

    private final boolean skipGeometrisch;

    private final boolean skipFlaechenschluss;

    private final boolean skipGeltungsbereich;

    /**
     * Default validation configuration. Nothing is skipped.
     */
    public DefaultValidationConfiguration() {
        this( false, false, false, false );
    }

    /**
     * @param skipSemantisch
     *                         <code>true</code> if the semantic validation should be skipped, <code>false</code> otherwise
     * @param skipGeometrisch
     *                         <code>true</code> if the geometric validation should be skipped, <code>false</code> otherwise
     * @param skipFlaechenschluss
     *                         <code>true</code> if the flaechenschluss validation should be skipped, <code>false</code> otherwise
     * @param skipGeltungsbereich
     *                         <code>true</code> if the geltungsbereich validation should be skipped, <code>false</code> otherwise
     */
    public DefaultValidationConfiguration( boolean skipSemantisch, boolean skipGeometrisch, boolean skipFlaechenschluss,
                                           boolean skipGeltungsbereich ) {
        this.skipSemantisch = skipSemantisch;
        this.skipGeometrisch = skipGeometrisch;
        this.skipFlaechenschluss = skipFlaechenschluss;
        this.skipGeltungsbereich = skipGeltungsbereich;
    }

    /**
     * @return <code>true</code> if the semantic validation should be skipped, <code>false</code> otherwise
     */
    public boolean isSkipSemantisch() {
        return skipSemantisch;
    }

    /**
     * @return <code>true</code> if the geometric validation should be skipped, <code>false</code> otherwise
     */
    public boolean isSkipGeometrisch() {
        return skipGeometrisch;
    }

    /**
     * @return <code>true</code> if the flaechenschluss validation should be skipped, <code>false</code> otherwise
     */
    public boolean isSkipFlaechenschluss() {
        return skipFlaechenschluss;
    }

    /**
     * @return <code>true</code> if the geltungsbereich validation should be skipped, <code>false</code> otherwise
     */
    public boolean isSkipGeltungsbereich() {
        return skipGeltungsbereich;
    }
}