package de.latlon.xplan.validator.web.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.latlon.xplan.validator.web.shared.MapPreviewException;
import de.latlon.xplan.validator.web.shared.MapPreviewMetadata;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RemoteServiceRelativePath("mappreviewconfig")
public interface MapPreviewConfigService extends RemoteService {


    boolean isMapPreviewAvaialable()
                            throws MapPreviewException;

    MapPreviewMetadata createMapPreviewConfig()
                            throws MapPreviewException;

}