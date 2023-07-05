package de.latlon.xplan.manager.transaction;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer;
import org.deegree.feature.Feature;

import java.util.UUID;

import static de.latlon.xplan.manager.synthesizer.FeatureTypeNameSynthesizer.SYN_FEATURETYPE_PREFIX;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public final class TransactionUtils {

	private TransactionUtils() {
	}

	private static final FeatureTypeNameSynthesizer featureTypeNameSynthesizer = new FeatureTypeNameSynthesizer();

	public static void reassignFids(XPlanFeatureCollection fc) {
		for (Feature f : fc.getFeatures()) {
			String synFeatureTypeName = featureTypeNameSynthesizer.detectSynFeatureTypeName(f.getName());
			String prefix = SYN_FEATURETYPE_PREFIX + synFeatureTypeName.toUpperCase() + "_";
			String uuid = UUID.randomUUID().toString();
			f.setId(prefix + uuid);
		}
	}

}
