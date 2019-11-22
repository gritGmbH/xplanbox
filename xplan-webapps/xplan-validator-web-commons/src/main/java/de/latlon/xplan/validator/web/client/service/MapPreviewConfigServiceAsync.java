package de.latlon.xplan.validator.web.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface MapPreviewConfigServiceAsync {

    void createMapPreviewConfig( AsyncCallback<MapPreviewMetadata> callback );

}
