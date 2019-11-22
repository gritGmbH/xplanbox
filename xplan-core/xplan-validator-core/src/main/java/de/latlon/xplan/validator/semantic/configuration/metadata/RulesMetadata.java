package de.latlon.xplan.validator.semantic.configuration.metadata;

import org.apache.commons.lang.StringUtils;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesMetadata {

    private static final String UNKNOWN = "unbekannt";

    private final String version;

    private final String source;

    public RulesMetadata() {
        this( null, null );
    }

    /**
     * @param version
     *                         the version of the rules, may be <code>null</code> if not known
     * @param source
     *                         the source of the rules, may be <code>null</code> if not known
     */
    public RulesMetadata( String version, String source ) {
        this.version = StringUtils.isEmpty( version ) ? UNKNOWN : version;
        this.source = StringUtils.isEmpty( source ) ? UNKNOWN : source;
    }

    /**
     * @return the version of the rules, 'unbekannt' if not known, never <code>null</code>
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the source of the rules, 'unbekannt' if not known, never <code>null</code>
     */
    public String getSource() {
        return source;
    }

}