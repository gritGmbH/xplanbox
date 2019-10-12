package de.latlon.xplan.validator.geometric.inspector;

import org.deegree.gml.feature.FeatureInspector;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface GeometricFeatureInspector extends FeatureInspector {

    List<String> checkGeometricRule();

}