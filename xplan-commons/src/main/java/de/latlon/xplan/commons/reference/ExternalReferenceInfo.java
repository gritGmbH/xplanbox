package de.latlon.xplan.commons.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Information on <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code> objects as well as their usage
 * inside <code>XP_RasterplanBasis</code>/<code>XP_RasterplanAenderung</code> features.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class ExternalReferenceInfo {

    private final List<ExternalReference> externalRefs;

    private final List<ExternalReference> rasterPlanBaseScans;

    private final List<ExternalReference> rasterPlanUpdateScans;

    public ExternalReferenceInfo() {
        this( new ArrayList<ExternalReference>(), new ArrayList<ExternalReference>(),
              new ArrayList<ExternalReference>() );
    }

    public ExternalReferenceInfo( List<ExternalReference> externalRefs, List<ExternalReference> rasterPlanBaseScans,
                                  List<ExternalReference> rasterPlanUpdateScans ) {
        this.externalRefs = externalRefs;
        this.rasterPlanBaseScans = rasterPlanBaseScans;
        this.rasterPlanUpdateScans = rasterPlanUpdateScans;
    }

    public List<ExternalReference> getExternalRefs() {
        return externalRefs;
    }

    public void addExternalRefs( List<ExternalReference> externalRefs ) {
        this.externalRefs.addAll( externalRefs );
    }

    public List<ExternalReference> getRasterPlanBaseScans() {
        return rasterPlanBaseScans;
    }

    public void addRasterPlanBaseScan( ExternalReference rasterPlanBaseScan ) {
        this.rasterPlanBaseScans.add( rasterPlanBaseScan );
    }

    public void addRasterPlanBaseScans( List<ExternalReference> rasterPlanBaseScans ) {
        this.rasterPlanBaseScans.addAll( rasterPlanBaseScans );
    }

    public List<ExternalReference> getRasterPlanUpdateScans() {
        return rasterPlanUpdateScans;
    }

    public void addRasterPlanUpdateScan( ExternalReference rasterPlanUpdateScan ) {
        this.rasterPlanUpdateScans.add( rasterPlanUpdateScan );
    }

    public void addRasterPlanUpdateScans( List<ExternalReference> rasterPlanUpdateScans ) {
        this.rasterPlanUpdateScans.addAll( rasterPlanUpdateScans );
    }

    public List<ExternalReference> getRasterPlanBaseAndUpdateScans() {
        List<ExternalReference> rasterPlanBaseAndUpdateScans = new ArrayList<>();
        rasterPlanBaseAndUpdateScans.addAll( rasterPlanBaseScans );
        rasterPlanBaseAndUpdateScans.addAll( rasterPlanUpdateScans );
        return rasterPlanBaseAndUpdateScans;
    }

    @Override
    public String toString() {
        return "ExternalReferenceInfo [externalRefs=" + externalRefs + ", rasterPlanBaseScans=" + rasterPlanBaseScans
               + ", rasterPlanUpdateScans=" + rasterPlanUpdateScans + "]";
    }

}