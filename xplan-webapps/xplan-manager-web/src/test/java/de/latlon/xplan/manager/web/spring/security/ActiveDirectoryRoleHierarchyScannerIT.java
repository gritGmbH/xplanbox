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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.spring.security;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ActiveDirectoryRoleHierarchyScannerIT {

    private static final String PROVIDERURL = "ldap://adserver:389";

    private static final String DOMAIN = "adserver.lat-lon";

    private static final String USERNAME = "ll-technical";

    private static final String PASSWORD = "ADServer!";

    private static final String SEARCHNODE = "OU=lgvxplanisk,DC=adserver,DC=lat-lon";

    @Test
    public void testRetrieveRoleHierarchy()
                    throws Exception {
        ActiveDirectoryRoleHierarchyScanner scanner = initRoleHierarchyScanner();
        String hierarchy = scanner.retrieveRoleHierarchy();

        assertThat( hierarchy, containsString( "B-Plan > ALTONA" ) );
        assertThat( hierarchy, containsString( "G11 > SUPER" ) );
        assertThat( hierarchy, containsString( "EDITOR" ) );
        assertThat( hierarchy, containsString( "HARBURG" ) );
        assertThat( hierarchy, containsString( "HAMBURGNORD" ) );
    }

    private static ActiveDirectoryRoleHierarchyScanner initRoleHierarchyScanner()
                    throws NamingException {
        List<String> editorGroups = asList( "ALTONA", "EDITOR" );
        List<String> superGroups = asList( "SUPER" );
        Map<String, List<String>> districtMap = new HashMap<>();
        districtMap.put( "ALTONA", asList( "Altona" ) );
        districtMap.put( "HARBURG", asList( "Harburg" ) );
        districtMap.put( "HAMBURGNORD", asList( "Hamburg-Nord" ) );
        return new ActiveDirectoryRoleHierarchyScanner( PROVIDERURL, DOMAIN, USERNAME, PASSWORD, SEARCHNODE,
                        editorGroups, superGroups, districtMap );
    }

}
