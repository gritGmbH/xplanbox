/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.*;
import static java.nio.file.Paths.get;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for <link>XQuerySemanticValidatorConfigurationRetriever</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorConfigurationRetrieverTest {

    @Test
    public void testRetrieveConfigurationShouldReturnCorrectNumberOfRules()
        throws Exception {
        Path rulesPath =
            get( XQuerySemanticValidatorConfigurationRetriever.class.getResource( "rules" ).toURI() );
        XQuerySemanticValidatorConfigurationRetriever configurationRetriever =
            new XQuerySemanticValidatorConfigurationRetriever(
                rulesPath );
        SemanticValidatorConfiguration configuration = configurationRetriever.retrieveConfiguration();
        List<SemanticValidatorRule> rules = configuration.getAllRules();

        assertThat( rules.size(), is( 11 ) );
    }

    @Test
    public void testRetrieveConfigurationShouldRetrieveAllFilesRecursively()
        throws Exception {
        Path rulesPath = get( XQuerySemanticValidatorConfigurationRetriever.class.getResource( "rules" ).toURI() );
        XQuerySemanticValidatorConfigurationRetriever retriever =
            new XQuerySemanticValidatorConfigurationRetriever( rulesPath );
        SemanticValidatorConfiguration configuration = retriever.retrieveConfiguration();

        assertThat( configuration.getRules( singletonList( IGNORE_XP ) ).size(), is( 9 ) );
        assertThat( configuration.getRules( singletonList( IGNORE_SO ) ).size(), is( 10 ) );
        assertThat( configuration.getRules( XPLAN_40, singletonList( NONE ) ).size(), is( 8 ) );
    }

}
