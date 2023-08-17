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

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;

import java.io.File;

import org.deegree.commons.config.DeegreeWorkspace;

/**
 * Encapsulates a {@link DeegreeWorkspace}. The workspace is instantiated at first access
 * via {@link #getWorkspaceInstance()}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class DeegreeWorkspaceWrapper {

	private final String workspaceName;

	private final File workspaceDir;

	private DeegreeWorkspace deegreeWorkspace;

	/**
	 * Returns the workspace with the given name.
	 * @param workspaceName the name of the workspace located at deegree workspace root,
	 * never <code>null</code>
	 * @throws IllegalArgumentException - if workspaceName is <code>null</code>
	 */
	public DeegreeWorkspaceWrapper(String workspaceName) {
		if (workspaceName == null)
			throw new IllegalArgumentException("workspaceName must not be null!");
		this.workspaceName = workspaceName;
		this.workspaceDir = null;
	}

	/**
	 *
	 * Returns the workspace for the given directory.
	 * @param workspaceDir the directory of the workspace, never <code>null</code>
	 * @throws IllegalArgumentException - if workspaceDir is <code>null</code>
	 */
	public DeegreeWorkspaceWrapper(File workspaceDir) {
		if (workspaceDir == null)
			throw new IllegalArgumentException("workspaceDir must not be null!");
		this.workspaceName = null;
		this.workspaceDir = workspaceDir;
	}

	/**
	 * Returns the workspace with the given name or the workspace for the given directory
	 * if the former does not exist if both parameters (workspaceName and workspaceDir)
	 * are not null.
	 * @param workspaceName the name of the workspace located at deegree workspace root,
	 * can be <code>null</code> (implies default workspace)
	 * @param workspaceDir the directory to use as workspace if the named workspace does
	 * not exist, can be null <code>null</code>
	 * @throws IllegalArgumentException - if both, workspaceName and workspaceDir are
	 * <code>null</code>
	 */
	public DeegreeWorkspaceWrapper(String workspaceName, File workspaceDir) {
		if (workspaceName == null && workspaceDir == null)
			throw new IllegalArgumentException("workspaceName or workspaceName must not be null!");
		this.workspaceName = workspaceName;
		this.workspaceDir = workspaceDir;
	}

	/**
	 * Returns the instantiated workspace.
	 * @return the instantiated workspace, never <code>null</code>
	 * @throws WorkspaceException - if the workspace does not exists or the instantiation
	 * failed
	 */
	public synchronized DeegreeWorkspace getWorkspaceInstance() throws WorkspaceException {
		if (deegreeWorkspace == null)
			deegreeWorkspace = instantiateWorkspace(workspaceName, workspaceDir);
		return deegreeWorkspace;
	}

}
