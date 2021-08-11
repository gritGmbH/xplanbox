/*-
 * #%L
 * xplan-validator-web-commons - Modul zur Gruppierung aller Webapps
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.web.client.report;

import java.util.List;

import com.google.gwt.core.client.GWT;

import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * Contains useful methods to create report urls.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
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
