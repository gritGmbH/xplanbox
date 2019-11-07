package de.latlon.xplan.validator.report.reference;

import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ExternalReferenceReport {

    private SkipCode skipCode;

    private List<String> references;

    public ExternalReferenceReport( SkipCode skipCode ) {
        this( skipCode, Collections.emptyList() );
    }

    public ExternalReferenceReport( List<String> references ) {
        this( null, references );
    }

    private ExternalReferenceReport( SkipCode skipCode, List<String> references ) {
        this.skipCode = skipCode;
        this.references = references;
    }

    public SkipCode getSkipCode() {
        return skipCode;
    }

    public List<String> getReferences() {
        return references;
    }
}
