package de.latlon.xplan.validator.geometric.inspector;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.gml.feature.FeatureInspector;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface GeometricFeatureInspector extends FeatureInspector {

    /**
     * @return <code>true</code> if no errors was found, <code>false</code> otherwise
     */
    boolean checkGeometricRule();

    /**
     * @return all errors found, max be empty but never <code>null</code>
     */
    List<String> getErrors();

    /**
     * @return the BadGeoemtries, max be empty but never <code>null</code>
     */
    List<BadGeometry> getBadGeometries();

}