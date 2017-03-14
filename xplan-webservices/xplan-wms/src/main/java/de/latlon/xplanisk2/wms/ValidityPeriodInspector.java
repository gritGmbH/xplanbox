package de.latlon.xplanisk2.wms;

import org.deegree.layer.metadata.LayerMetadata;
import org.deegree.services.wms.visibility.LayerVisibilityInspector;

/**
 * {@link LayerVisibilityInspector} to check if the plan a (raster) layer is part if currently valid according the validity period.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidityPeriodInspector implements LayerVisibilityInspector {

    @Override
    public boolean isVisible( LayerMetadata layerMetadata ) {
        return true;
    }

}