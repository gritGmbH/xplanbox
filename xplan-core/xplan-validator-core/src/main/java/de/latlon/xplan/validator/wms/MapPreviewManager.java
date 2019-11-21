package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewManager {

    private final ValidatorWmsManager validatorWmsManager;

    private final MasterportalConfigWriter configWriter;

    public MapPreviewManager( ValidatorWmsManager validatorWmsManager )
                            throws ValidatorWmsException {
        this.validatorWmsManager = validatorWmsManager;
        this.configWriter = new MasterportalConfigWriter();

    }

    public void createConfigurations( String planId, XPlanFeatureCollection xPlanFeatureCollection )
                            throws ValidatorWmsException {
        this.validatorWmsManager.insert( xPlanFeatureCollection );
        this.configWriter.createMasterportalConfig( planId );
    }

}