/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.server.service;

import static org.apache.commons.io.IOUtils.write;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.ValidationException;

/**
 * Access to plan archive from session and filesystem
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ManagerPlanArchiveManager {

    private static final File UPLOAD_FOLDER = new File( System.getProperty( "java.io.tmpdir" ), "xplanmanager" );

    private static final String SESSION_ATTRIBUTE_LOCAL_PLAN = "localPlan";

    private static final String SESSION_ATTRIBUTE_ARTEFACTS_FOLDER = "artefactsFolder";

    public ManagerPlanArchiveManager() {
        if ( !UPLOAD_FOLDER.exists() )
            UPLOAD_FOLDER.mkdir();
    }

    public File getUploadFolder() {
        return UPLOAD_FOLDER;
    }

    public File readArchiveFromFilesystem( XPlan plan )
                    throws IOException {
        String fileToBeValidated = plan.getId() + ".zip";
        return new File( getUploadFolder(), fileToBeValidated );
    }

    public void savePlanInSession( HttpSession session, XPlan plan ) {
        session.setAttribute( SESSION_ATTRIBUTE_LOCAL_PLAN, plan );
    }

    public void clearPlanInSession( HttpSession session ) {
        session.removeAttribute( SESSION_ATTRIBUTE_LOCAL_PLAN );
    }

    public XPlan retrievePlanFromSession( HttpSession session ) {
        return (XPlan) session.getAttribute( SESSION_ATTRIBUTE_LOCAL_PLAN );
    }

    public XPlan retrieveRequiredPlanFromSession( HttpSession session )
                    throws ValidationException {
        XPlan planFromSession = retrievePlanFromSession( session );
        if ( planFromSession != null )
            return planFromSession;
        throw new ValidationException( "Could not find a plan to validate!" );
    }

    public void saveArtefactInFilesystem( HttpSession session, String fileName, byte[] artefact )
                    throws FileNotFoundException, IOException {
        checkAndSetSessionAttributeIfRequired( session );
        File artefactFolder = (File) session.getAttribute( SESSION_ATTRIBUTE_ARTEFACTS_FOLDER );
        File artefactFile = new File( artefactFolder, fileName );
        try ( FileOutputStream localOutput = new FileOutputStream( artefactFile ) ) {
            write( artefact, localOutput );
        }
    }

    public List<File> retrieveUploadedArtefacts( HttpSession session ) {
        Object artefactFolder = session.getAttribute( SESSION_ATTRIBUTE_ARTEFACTS_FOLDER );
        if ( artefactFolder != null )
            return Arrays.asList( ( (File) artefactFolder ).listFiles() );
        return Collections.emptyList();
    }

    public File createReportDirectory( String planUuid ) {
        File reportDirectory = new File( UPLOAD_FOLDER, planUuid );
        reportDirectory.mkdir();
        return reportDirectory;
    }

    private void checkAndSetSessionAttributeIfRequired( HttpSession session ) {
        Object sessionAttribute = session.getAttribute( SESSION_ATTRIBUTE_ARTEFACTS_FOLDER );
        if ( sessionAttribute == null ) {
            String uuid = UUID.randomUUID().toString();
            File artefactDir = new File( UPLOAD_FOLDER, uuid );
            artefactDir.mkdir();
            session.setAttribute( SESSION_ATTRIBUTE_ARTEFACTS_FOLDER, artefactDir );
        }
    }

}
