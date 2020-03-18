package de.latlon.xplan.commons.reference;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyStringValue;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

/**
 * Scans an XPlan {@link FeatureCollection} for <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code>
 * objects as well as their usage inside <code>XP_RasterplanBasis</code>/<code>XP_RasterplanAenderung</code> features.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class ExternalReferenceScanner {

    private final List<ExternalReference> externalRefs = new ArrayList<ExternalReference>();

    private final List<ExternalReference> rasterPlanBaseScans = new ArrayList<ExternalReference>();

    private final List<ExternalReference> rasterPlanUpdateScans = new ArrayList<ExternalReference>();

    /**
     * Scans the given XPlan {@link FeatureCollection}.
     * 
     * @param fc
     *            feature collection, must not be <code>null</code>
     * @return reference information, never <code>null</code>
     */
    public ExternalReferenceInfo scan( FeatureCollection fc ) {
        XPlanVersion version = determineBaseVersion( fc.getName() );
        scanFc( fc, version );
        return new ExternalReferenceInfo( externalRefs, rasterPlanBaseScans, rasterPlanUpdateScans );
    }

    public ExternalReferenceInfo scan( FeatureCollection fc, XPlanVersion version  ) {
        scanFc( fc, version );
        return new ExternalReferenceInfo( externalRefs, rasterPlanBaseScans, rasterPlanUpdateScans );
    }

    private void scanFc( FeatureCollection fc, XPlanVersion version ) {
        switch ( version ) {
        case XPLAN_3:
            Map<String, ExternalReference> fidToExternalRef = scanForExternalReferencesXplan2or3( fc );
            scanForRasterplanFeaturesXplan2or3( fidToExternalRef, fc );
            break;
        case XPLAN_40:
        case XPLAN_41:
            scanXplan4( fc );
            break;
        case XPLAN_50:
        case XPLAN_51:
        case XPLAN_52:
            scanXplan5( fc );
            break;
        default:
            throw new IllegalArgumentException( "Unsupported XPlanGML Version: " + version );
        }
    }

    private Map<String, ExternalReference> scanForExternalReferencesXplan2or3( FeatureCollection fc ) {
        Map<String, ExternalReference> fidToExternalReference = new HashMap<String, ExternalReference>();
        for ( Feature feature : fc ) {
            String name = feature.getName().getLocalPart();
            if ( "XP_ExterneReferenz".equals( name ) || "XP_ExterneReferenzPlan".equals( name ) ) {
                String ns = feature.getName().getNamespaceURI();
                String referenzName = getPropertyStringValue( feature, new QName( ns, "referenzName" ) );
                String referenzUrl = getPropertyStringValue( feature, new QName( ns, "referenzURL" ) );
                String referenzMimeTypeCode = getPropertyStringValue( feature, new QName( ns, "referenzMimeType" ) );
                String geoRefUrl = null;
                String geoRefMimeTypeCode = null;
                boolean isPlan = false;
                if ( "XP_ExterneReferenzPlan".equals( name ) ) {
                    geoRefUrl = getPropertyStringValue( feature, new QName( ns, "georefURL" ) );
                    geoRefMimeTypeCode = getPropertyStringValue( feature, new QName( ns, "georefMimeType" ) );
                    isPlan = true;
                }
                ExternalReference externalRef = new ExternalReference( geoRefUrl, geoRefMimeTypeCode, referenzUrl,
                                referenzName, referenzMimeTypeCode, isPlan );
                externalRefs.add( externalRef );
                fidToExternalReference.put( feature.getId(), externalRef );
            }
        }
        return fidToExternalReference;
    }

    private void scanForRasterplanFeaturesXplan2or3( Map<String, ExternalReference> fidToExternalRef,
                                                     FeatureCollection fc ) {
        for ( Feature feature : fc ) {
            String name = feature.getName().getLocalPart();
            if ( "XP_RasterplanBasis".equals( name ) ) {
                List<Property> refScanProps = feature.getProperties( new QName( feature.getName().getNamespaceURI(),
                                "refScan" ) );
                for ( Property property : refScanProps ) {
                    GMLReference<?> ref = (GMLReference<?>) property.getValue();
                    String gmlId = ref.getId();
                    ExternalReference externalRef = fidToExternalRef.get( gmlId );
                    rasterPlanBaseScans.add( externalRef );
                }
            } else if ( isRasterplanAenderungFeature( name ) ) {
                List<Property> refScanProps = feature.getProperties( new QName( feature.getName().getNamespaceURI(),
                                "refScan" ) );
                for ( Property property : refScanProps ) {
                    GMLReference<?> ref = (GMLReference<?>) property.getValue();
                    String gmlId = ref.getId();
                    ExternalReference externalRef = fidToExternalRef.get( gmlId );
                    rasterPlanUpdateScans.add( externalRef );
                }
            }
        }
    }

    private void scanXplan4( FeatureCollection fc ) {
        for ( Feature feature : fc ) {
            String name = feature.getName().getLocalPart();
            if ( "XP_RasterplanBasis".equals( name ) ) {
                for ( Property prop : feature.getProperties() ) {
                    if ( "refScan".equals( prop.getName().getLocalPart() ) ) {
                        List<ExternalReference> scanRefs = new ArrayList<ExternalReference>();
                        scanXplan4or5( prop, scanRefs );
                        externalRefs.addAll( scanRefs );
                        rasterPlanBaseScans.addAll( scanRefs );
                    } else {
                        scanXplan4or5( prop, externalRefs );
                    }
                }
            } else if ( isRasterplanAenderungFeature( name ) ) {
                for ( Property prop : feature.getProperties() ) {
                    if ( "refScan".equals( prop.getName().getLocalPart() ) ) {
                        List<ExternalReference> scanRefs = new ArrayList<ExternalReference>();
                        scanXplan4or5( prop, scanRefs );
                        externalRefs.addAll( scanRefs );
                        rasterPlanUpdateScans.addAll( scanRefs );
                    } else {
                        scanXplan4or5( prop, externalRefs );
                    }
                }
            } else {
                for ( Property prop : feature.getProperties() ) {
                    scanXplan4or5( prop, externalRefs );
                }
            }
        }
    }

    private void scanXplan5( FeatureCollection fc ) {
        for ( Feature feature : fc ) {
            String name = feature.getName().getLocalPart();
            if ( "XP_Rasterdarstellung".equals( name ) || name.matches( "(BP|LP|RP|FP|SO)_Bereich" ) ) {
                for ( Property prop : feature.getProperties() ) {
                    if ( "refScan".equals( prop.getName().getLocalPart() ) ) {
                        List<ExternalReference> scanRefs = new ArrayList<ExternalReference>();
                        scanXplan4or5( prop, scanRefs );
                        externalRefs.addAll( scanRefs );
                        rasterPlanBaseScans.addAll( scanRefs );
                    } else {
                        scanXplan4or5( prop, externalRefs );
                    }
                }
            } else {
                for ( Property prop : feature.getProperties() ) {
                    scanXplan4or5( prop, externalRefs );
                }
            }
        }
    }

    private boolean isRasterplanAenderungFeature( String name ) {
        return "P_RasterplanAenderung".equals( name.substring( 1 ) );
    }

    private void scanXplan4or5(ElementNode elNode, List<ExternalReference> refs ) {
        String name = elNode.getName().getLocalPart();
        if ( "XP_ExterneReferenz".equals( name ) ) {
            ExternalReference externalRef = createExternalReferenceXplan4or5( elNode );
            refs.add( externalRef );
        } else if ( "XP_SpezExterneReferenz".equals( name ) ) {
            ExternalReference externalRef = createExternalReferenceXplan4or5( elNode );
            refs.add( externalRef );
        } else {
            for ( TypedObjectNode childNode : elNode.getChildren() ) {
                if ( childNode instanceof ElementNode ) {
                    scanXplan4or5( (ElementNode) childNode, refs );
                }
            }
        }
    }

    private ExternalReference createExternalReferenceXplan4or5(ElementNode elNode ) {
        String referenzName = null;
        String referenzUrl = null;
        String referenzMimeTypeCode = null;
        String geoRefUrl = null;
        String geoRefMimeTypeCode = null;
        boolean isPlan = false;
        for ( TypedObjectNode childNode : elNode.getChildren() ) {
            if ( childNode instanceof ElementNode ) {
                ElementNode childEl = (ElementNode) childNode;
                String name = childEl.getName().getLocalPart();
                if ( "georefURL".equals( name ) ) {
                    geoRefUrl = getStringValue( childEl );
                } else if ( "georefMimeType".equals( name ) ) {
                    geoRefMimeTypeCode = getStringValue( childEl );
                } else if ( "art".equals( name ) ) {
                    String art = getStringValue( childEl );
                    if ( "PlanMitGeoreferenz".equals( art.trim() ) ) {
                        isPlan = true;
                    }
                } else if ( "referenzName".equals( name ) ) {
                    referenzName = getStringValue( childEl );
                } else if ( "referenzURL".equals( name ) ) {
                    referenzUrl = getStringValue( childEl );
                } else if ( "referenzMimeType".equals( name ) ) {
                    referenzMimeTypeCode = getStringValue( childEl );
                }
            }
        }
        return new ExternalReference( geoRefUrl, geoRefMimeTypeCode, referenzUrl, referenzName, referenzMimeTypeCode,
                        isPlan );
    }

    private String getStringValue( ElementNode childEl ) {
        for ( TypedObjectNode node : childEl.getChildren() ) {
            if ( node instanceof PrimitiveValue ) {
                return ( (PrimitiveValue) node ).getAsText().trim();
            }
        }
        return "";
    }
}
