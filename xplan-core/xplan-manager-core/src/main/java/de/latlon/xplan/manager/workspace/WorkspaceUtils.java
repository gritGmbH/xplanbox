/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.manager.workspace;

import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.deegree.commons.config.DeegreeWorkspace.getInstance;
import static org.deegree.commons.config.DeegreeWorkspace.isWorkspace;

/**
 * Contains convenience methods to instantiate deegree workspaces.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceUtils {

	private static final Logger LOG = LoggerFactory.getLogger(WorkspaceUtils.class);

	public static final String DEFAULT_XPLANSYN_WMS_WORKSPACE = "xplansyn-wms-workspace";

	public static final String DEFAULT_XPLAN_MANAGER_WORKSPACE = "xplan-manager-workspace";

	private WorkspaceUtils() {
	}

	/**
	 * @param workspaceName name of the workspace to instantiates, never <code>null</code>
	 * @param workspaceDir directory to use as workspace, used if the default directory
	 * does not contain a workspace with the given name, may be <code>null</code>
	 * @return the instantiated workspace, if a workspace with the given name or in the
	 * directory exist, never <code>null</code>
	 * @throws Exception - an exception occurred during instantiation or a workspace with
	 * the given name does not exist
	 */
	static DeegreeWorkspace instantiateWorkspace(String workspaceName, File workspaceDir) throws WorkspaceException {
		if (workspaceDir != null && workspaceName != null)
			return instantiateWorkspaceByNameAndDir(workspaceDir, workspaceName);
		else if (workspaceDir != null)
			return instantiateWorkspace(workspaceDir);
		else
			return instantiateWorkspace(workspaceName);
	}

	/**
	 * @param workspaceDir directory to use as workspace, used if the default directory
	 * does not contain a workspace with the given name, may be <code>null</code>
	 * @return the instantiated workspace, if a workspace on the given path exist, never
	 * <code>null</code>
	 * @throws Exception - an exception occurred during instantiation or a workspace with
	 * the given name does not exist
	 */
	static DeegreeWorkspace instantiateWorkspace(File workspaceDir) throws WorkspaceException {
		return instantiateWorkspaceByNameAndDir(workspaceDir, null);
	}

	/**
	 * @param workspaceName name of the workspace to instantiates, never <code>null</code>
	 * given name, may be <code>null</code>
	 * @return the instantiated workspace, if a workspace with the given name or in the
	 * directory exist, never <code>null</code>
	 * @throws Exception - an exception occurred during instantiation or a workspace with
	 * the given name does not exist
	 */
	public static DeegreeWorkspace instantiateWorkspace(String workspaceName) throws WorkspaceException {
		LOG.info("Get workspace instance '{}' from deegree workspace root '{}'", workspaceName,
				DeegreeWorkspace.getWorkspaceRoot());
		if (!isWorkspace(workspaceName)) {
			throw new WorkspaceException("Fehler: Workspace '" + workspaceName + "' existiert nicht.");
		}
		try {
			DeegreeWorkspace workspace = getInstance(workspaceName);
			workspace.initAll();
			return workspace;
		}
		catch (Exception e) {
			throw new WorkspaceException(
					"Fehler: Workspace '" + workspaceName + "' konnte nicht initialisiert werden: " + e.getMessage(),
					e);
		}
	}

	/**
	 * @param workspaceDir directory to use as workspace, may be <code>null</code>
	 * @return the location of the workspace (passed directory or the workspace with
	 * default name ('xplansyn-wms-workspace') in the deegree workspace root directory) if
	 * exist, never <code>null</code>
	 * @throws Exception - if a workspace with the given name does not exist
	 */
	public static File findWorkspaceDirectory(File workspaceDir) throws Exception {
		if (workspaceDir != null)
			return findWorkspaceDirectoryByDir(workspaceDir);
		else
			return findWorkspaceDirectoryByName(DEFAULT_XPLANSYN_WMS_WORKSPACE);
	}

	private static DeegreeWorkspace instantiateWorkspaceByNameAndDir(File workspaceDir, String workspaceName)
			throws WorkspaceException {
		LOG.info("Get workspace instance '{}' from deegree workspace root '{}' or '{}' ", workspaceName,
				DeegreeWorkspace.getWorkspaceRoot(), workspaceDir);
		DeegreeWorkspace workspace;
		try {
			workspace = getInstance(workspaceName, workspaceDir);
		}
		catch (Exception e) {
			throw new WorkspaceException(
					"Fehler: Workspace '" + workspaceName + "' konnte nicht gefunden werden: " + e.getMessage(), e);
		}
		if (!workspace.getLocation().exists())
			throw new WorkspaceException("Fehler: Workspace '" + workspaceName + "' existiert nicht.");
		try {
			workspace.initAll();
		}
		catch (Exception e) {
			throw new WorkspaceException(
					"Fehler: Workspace '" + workspaceName + "' konnte nicht initialisiert werden: " + e.getMessage(),
					e);
		}
		return workspace;
	}

	private static File findWorkspaceDirectoryByDir(File workspaceDir) throws WorkspaceException {
		LOG.info("Get workspace instance from directory '{}'", workspaceDir);
		DeegreeWorkspace workspace;
		try {
			workspace = getInstance(null, workspaceDir);
		}
		catch (IOException e) {
			throw new WorkspaceException(
					"Fehler: Workspace at '" + workspaceDir + "' konnte nicht gefunden werden: " + e.getMessage(), e);
		}
		if (!workspace.getLocation().exists())
			throw new WorkspaceException("Fehler: Workspace '" + workspaceDir + "' existiert nicht.");
		return workspace.getLocation();
	}

	private static File findWorkspaceDirectoryByName(String workspaceName) throws WorkspaceException {
		LOG.info("Get workspace instance '{}' from deegree workspace root '{}'", workspaceName,
				DeegreeWorkspace.getWorkspaceRoot());
		DeegreeWorkspace workspace = getInstance(workspaceName);
		File workspaceLocation = workspace.getLocation();
		if (!workspaceLocation.exists())
			throw new WorkspaceException("Fehler: Workspace '" + workspaceName + "' existiert nicht.");
		return workspaceLocation;
	}

}
