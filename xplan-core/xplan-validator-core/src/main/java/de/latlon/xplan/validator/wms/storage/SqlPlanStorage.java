package de.latlon.xplan.validator.wms.storage;

import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.workspace.ValidatorWorkspaceWrapper;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.feature.persistence.sql.SQLFeatureStore;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Implementation of a {@link PlanStorage}, writing the feature collection to a database
 * using the deegree SQLFeatureStore in xplan-valdiator-workspace.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SqlPlanStorage implements PlanStorage {

	private static final Logger LOG = LoggerFactory.getLogger(SqlPlanStorage.class);

	private ValidatorWorkspaceWrapper validatorWorkspaceWrapper;

	/**
	 * @param validatorWorkspaceWrapper wrapping the required xplan-validator-workspace,
	 * never <code>null</code>
	 */
	public SqlPlanStorage(ValidatorWorkspaceWrapper validatorWorkspaceWrapper) {
		this.validatorWorkspaceWrapper = validatorWorkspaceWrapper;
	}

	@Override
	public void storeSynFeatureCollection(FeatureCollection synFeatureCollection) throws MapPreviewCreationException {
		SQLFeatureStore featureStore = validatorWorkspaceWrapper.lookupSqlFeatureStore();
		FeatureStoreTransaction transaction = null;
		try {
			transaction = featureStore.acquireTransaction();
			List<String> insertedIds = transaction.performInsert(synFeatureCollection, IDGenMode.GENERATE_NEW);
			LOG.info("Inserted {} features.", insertedIds.size());
			transaction.commit();
		}
		catch (FeatureStoreException e) {
			rollback(transaction, e);
			throw new MapPreviewCreationException("Insert of feature collection in XPlanValidatorWMS failed.", e);
		}

	}

	private static void rollback(FeatureStoreTransaction transaction, FeatureStoreException e) {
		try {
			if (transaction != null)
				transaction.rollback();
		}
		catch (FeatureStoreException ex) {
			LOG.error("Rollback failed", e);
		}
	}

}
