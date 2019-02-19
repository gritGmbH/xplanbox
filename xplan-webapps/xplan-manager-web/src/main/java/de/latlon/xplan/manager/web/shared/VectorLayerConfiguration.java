package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;

/**
 * Encapsulates a map preview configuration
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class VectorLayerConfiguration implements Serializable {

    private static final long serialVersionUID = -6690846114049627141L;

    private String vectorWmsName;

    private String bpVectorLayer;

    private String fpVectorLayer;

    private String lpVectorLayer;

    private String rpVectorLayer;

    private String soVectorLayer;

    public VectorLayerConfiguration() {
    }

    /**
     * @param vectorWmsName
     *            displayed name of the vector maps of the wms, may be <code>null</code>
     * @param bpLayer
     *            the bp_* Layer to display in map preview, never <code>null</code>
     * @param fpLayer
     *            the fp_* Layer to display in map preview, never <code>null</code>
     * @param lpLayer
     *            the lp_* Layer to display in map preview, never <code>null</code>
     * @param rpLayer
     *            the rp_* Layer to display in map preview, never <code>null</code>
     * @param soLayer
     *            the so_* Layer to display in map preview, never <code>null</code>
     */
    public VectorLayerConfiguration( String vectorWmsName, String bpLayer, String fpLayer, String lpLayer,
                                     String rpLayer, String soLayer  ) {
        this.vectorWmsName = vectorWmsName;
        this.bpVectorLayer = bpLayer;
        this.fpVectorLayer = fpLayer;
        this.lpVectorLayer = lpLayer;
        this.rpVectorLayer = rpLayer;
        this.soVectorLayer = soLayer;
    }

    public String getVectorWmsName() {
        return vectorWmsName;
    }

    public String getBpVectorLayer() {
        return bpVectorLayer;
    }

    public String getFpVectorLayer() {
        return fpVectorLayer;
    }

    public String getLpVectorLayer() {
        return lpVectorLayer;
    }

    public String getRpVectorLayer() {
        return rpVectorLayer;
    }

    public String getSoVectorLayer() {
        return soVectorLayer;
    }

    @Override
    public int hashCode() {
        int result = vectorWmsName.hashCode();
        result = 31 * result + bpVectorLayer.hashCode();
        result = 31 * result + fpVectorLayer.hashCode();
        result = 31 * result + lpVectorLayer.hashCode();
        result = 31 * result + rpVectorLayer.hashCode();
        result = 31 * result + soVectorLayer.hashCode();
        return result;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o )
            return true;
        if ( o == null || getClass() != o.getClass() )
            return false;

        VectorLayerConfiguration that = (VectorLayerConfiguration) o;

        if ( !vectorWmsName.equals( that.vectorWmsName ) )
            return false;
        if ( bpVectorLayer != null ? !bpVectorLayer.equals( that.bpVectorLayer ) : that.bpVectorLayer != null )
            return false;
        if ( fpVectorLayer != null ? !fpVectorLayer.equals( that.fpVectorLayer ) : that.fpVectorLayer != null )
            return false;
        if ( lpVectorLayer != null ? !lpVectorLayer.equals( that.lpVectorLayer ) : that.lpVectorLayer != null )
            return false;
        if ( rpVectorLayer != null ? !rpVectorLayer.equals( that.rpVectorLayer ) : that.rpVectorLayer != null )
            return false;
        if ( soVectorLayer != null ? !soVectorLayer.equals( that.soVectorLayer ) : that.soVectorLayer != null )
            return false;

        return true;
    }

}
