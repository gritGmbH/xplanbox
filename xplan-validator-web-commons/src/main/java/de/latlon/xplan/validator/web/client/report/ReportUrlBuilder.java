package de.latlon.xplan.validator.web.client.report;

import java.util.List;

import com.google.gwt.core.client.GWT;

import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * Contains useful methods to create report urls.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ReportUrlBuilder {

    String createHtmlUrl( ValidationSummary validationSummary ) {
        String url = createBaseUrl( "rest/report/html/" + validationSummary.getPlanUuid() );
        url = appendValidationName( validationSummary, url );
        return url;
    }

    String createZipUrl( ValidationSummary validationSummary, List<ArtifactType> artifacts ) {
        String url = createBaseUrl( "rest/report/zip/" + validationSummary.getPlanUuid() );
        url = appendArtifacts( artifacts, url );
        url = appendValidationName( validationSummary, url );
        return url;
    }

    private String createBaseUrl( String path ) {
        return GWT.getHostPageBaseURL() + GWT.getModuleName() + "/" + path + "?";
    }

    private String appendValidationName( ValidationSummary validationSummary, String url ) {
        return url + "validationName" + "=" + validationSummary.getValidationName();
    }

    private String appendArtifacts( List<ArtifactType> artifacts, String url ) {
        return url + "artifacts" + "=" + createArtifactsAsString( artifacts ) + "&";
    }

    private String createArtifactsAsString( List<ArtifactType> artifacts ) {
        StringBuilder artifactsAsString = new StringBuilder();
        for ( ArtifactType artifact : artifacts ) {
            if ( artifactsAsString.length() > 0 )
                artifactsAsString.append( "," );
            artifactsAsString.append( artifact );
        }
        return artifactsAsString.toString();
    }

}