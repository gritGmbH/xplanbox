package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;

/**
 * Encapsulates a map preview configuration
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class RasterLayerConfiguration implements Serializable {

    private static final long serialVersionUID = -6690846114049627142L;

    private String rasterWmsName;

    private String bpRasterLayer;

    private String fpRasterLayer;

    private String lpRasterLayer;

    private String rpRasterLayer;

    public RasterLayerConfiguration() {
    }

    /**
     * @param rasterWmsName
     *            displayed name of the raster maps of the wms, may be <code>null</code>
     * @param bpRasterLayer
     *            the fp_* Layer to display in map preview, never <code>null</code>
     * @param fpRasterLayer
     *            the fp_* Layer to display in map preview, never <code>null</code>
     * @param lpRasterLayer
     *            the fp_* Layer to display in map preview, never <code>null</code>
     * @param rpRasterLayer
     *            the fp_* Layer to display in map preview, never <code>null</code>
     */
    public RasterLayerConfiguration( String rasterWmsName, String bpRasterLayer, String fpRasterLayer,
                                     String lpRasterLayer, String rpRasterLayer ) {
        this.rasterWmsName = rasterWmsName;
        this.bpRasterLayer = bpRasterLayer;
        this.fpRasterLayer = fpRasterLayer;
        this.lpRasterLayer = lpRasterLayer;
        this.rpRasterLayer = rpRasterLayer;
    }

    public String getRasterWmsName() {
        return rasterWmsName;
    }

    public String getBpRasterLayer() {
        return bpRasterLayer;
    }

    public String getFpRasterLayer() {
        return fpRasterLayer;
    }

    public String getLpRasterLayer() {
        return lpRasterLayer;
    }

    public String getRpRasterLayer() {
        return rpRasterLayer;
    }

    @Override
    public int hashCode() {
        int result = rasterWmsName.hashCode();
        result = 31 * result + bpRasterLayer.hashCode();
        result = 31 * result + fpRasterLayer.hashCode();
        result = 31 * result + lpRasterLayer.hashCode();
        result = 31 * result + rpRasterLayer.hashCode();
        return result;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        RasterLayerConfiguration that = (RasterLayerConfiguration) o;

        if ( !rasterWmsName.equals( that.rasterWmsName ) )
            return false;
        if ( bpRasterLayer != null ? !bpRasterLayer.equals( that.bpRasterLayer ) : that.bpRasterLayer != null )
            return false;
        if ( fpRasterLayer != null ? !fpRasterLayer.equals( that.fpRasterLayer ) : that.fpRasterLayer != null )
            return false;
        if ( lpRasterLayer != null ? !lpRasterLayer.equals( that.lpRasterLayer ) : that.lpRasterLayer != null )
            return false;
        if ( rpRasterLayer != null ? !rpRasterLayer.equals( that.rpRasterLayer ) : that.rpRasterLayer != null )
            return false;

        return true;
    }

}
