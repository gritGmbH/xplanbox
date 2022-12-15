package de.latlon.xplan.validator.wms.config;

import de.latlon.xplan.validator.wms.workspace.ValidatorWorkspaceWrapper;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.feature.persistence.sql.SQLFeatureStore;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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
		SQLFeatureStore sqlFeatureStore = mock(SQLFeatureStore.class);
		when(sqlFeatureStore.acquireTransaction()).thenReturn(transaction);
		when(validatorWorkspaceWrapper.lookupSqlFeatureStore()).thenReturn(sqlFeatureStore);
		return validatorWorkspaceWrapper;
	}

}
