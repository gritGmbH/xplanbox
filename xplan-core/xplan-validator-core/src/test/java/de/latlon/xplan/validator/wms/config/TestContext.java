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
