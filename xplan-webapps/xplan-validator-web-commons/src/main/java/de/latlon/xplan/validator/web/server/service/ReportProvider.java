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
package de.latlon.xplan.validator.web.server.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import de.latlon.xplan.validator.web.shared.ArtifactType;

/**
 * Provides report artefacts
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public interface ReportProvider {

    /**
     * @param response
     *            the response to write the html report in, never <code>null</code>
     * @param planUuid
     *            the uuid of the plan to write the validation result, never <code>null</code>
     * @param validationName
     *            the name of the validation run to write, never <code>null</code>
     * @throws IOException
     */
    void writeHtmlReport( HttpServletResponse response, String planUuid, String validationName )
                            throws IOException;

    /**
     * @param response
     *            the response to write the html report in, never <code>null</code>
     * @param planUuid
     *            the uuid of the plan to write the validation result, never <code>null</code>
     * @param validationName
     *            the name of the validation run to write, never <code>null</code>
     * @param artifacts
     *            a list of artifacts to put in the zip archive, never <code>null</code>
     * @throws IOException
     */
    void writeZipReport( HttpServletResponse response, String planUuid, String validationName,
                         List<ArtifactType> artifacts )
                            throws IOException;
}
