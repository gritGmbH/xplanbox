package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichFeature {

	private final Feature feature;

	private AbstractDefaultGeometry originalGeometry;

	private org.locationtech.jts.geom.Geometry jtsGeometry;

	private org.locationtech.jts.geom.Geometry geometryWithBuffer;

	private GeltungsbereichFeatureAnalyser featureAnalyser;

	private final double toleranceMetre;

	/**
	 * @param feature never <code>null</code>
	 * @param featureAnalyser used to analyse the feature, never <code>null</code>
	 */
	public GeltungsbereichFeature(Feature feature, GeltungsbereichFeatureAnalyser featureAnalyser,
			double toleranceMetre) {
		this.feature = feature;
		this.featureAnalyser = featureAnalyser;
		this.toleranceMetre = toleranceMetre;
		this.originalGeometry = featureAnalyser.getOriginalGeometry(feature);
	}

	/**
	 * @return the ID of the feature
	 */
	public String getFeatureId() {
		return feature.getId();
	}

	/**
	 * @return the original geometry of the feature
	 */
	public AbstractDefaultGeometry getOriginalGeometry() {
		return originalGeometry;
	}

	/**
	 * @return the JTS geometry of the feature
	 */
	public org.locationtech.jts.geom.Geometry getJtsGeometry() {
		if (jtsGeometry == null)
			jtsGeometry = featureAnalyser.getJtsGeometry(feature);
		return jtsGeometry;
	}

	/**
	 * @return the buffered JTS geometry of the feature
	 */
	public org.locationtech.jts.geom.Geometry getBufferedGeometry() {
		if (geometryWithBuffer == null && getJtsGeometry() != null)
			geometryWithBuffer = getJtsGeometry().buffer(toleranceMetre);
		return geometryWithBuffer;
	}

	/**
	 * @return <code>true</code> if the geometry is not empty and valid,
	 * <code>false</code> otherwise
	 */
	public boolean isGeometryValid() {
		org.locationtech.jts.geom.Geometry jtsGeometry = getJtsGeometry();
		if (jtsGeometry != null)
			return jtsGeometry.isValid();
		return false;
	}

}