/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.config;

import de.latlon.xplan.job.validator.workspace.ValidatorWorkspaceWrapper;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
public class TestContext {

	@Primary
	@Bean
	public ValidatorWorkspaceWrapper validatorWorkspaceWrapper() throws Exception {
		ValidatorWorkspaceWrapper validatorWorkspaceWrapper = mock(ValidatorWorkspaceWrapper.class);
		FeatureStoreTransaction transaction = mock(FeatureStoreTransaction.class);
		when(transaction.performInsert(any(FeatureCollection.class), any(IDGenMode.class)))
			.thenReturn(Collections.singletonList("1"));
		FeatureStore sqlFeatureStore = mock(FeatureStore.class);
		when(sqlFeatureStore.acquireTransaction()).thenReturn(transaction);
		when(validatorWorkspaceWrapper.lookupFeatureStore()).thenReturn(sqlFeatureStore);
		Connection conn = mock(Connection.class);
		PreparedStatement stmt = mock(PreparedStatement.class);
		when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
		when(validatorWorkspaceWrapper.openConnection()).thenReturn(conn);
		return validatorWorkspaceWrapper;
	}

}
