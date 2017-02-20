package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;
import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import java.util.List;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.Reference;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.datetime.Date;
import org.deegree.commons.tom.gml.GMLReference;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.feature.types.property.SimplePropertyType;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;

/**
 * Expression for building the flat XPlan Syn version of XPlan 2.0 / 3.0 properties that are feature valued.
 * <p>
 * The following feature types are handled (both from XPlan 2 / 3):
 * <ul>
 * <li>XP_ExterneReferenz</li>
 * <li>XP_ExterneReferenzPlan</li>
 * <li>XP_GenerAttribut</li>
 * <li>XP_Verfahrensmerkmal</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: ionita $
 * @version $Revision: 1242 $, $Date: 2010-05-25 20:15:58 +0200 (Di, 25 Mai 2010) $
 */
public class XPlanFlattenFeature implements Expression {

    final static public String SYN_NS = XPLAN_SYN.getNamespace();

    private static final Logger LOG = LoggerFactory.getLogger( XPlanFlattenFeature.class );
    
    private final Expression exp;

    /**
     * @param exp
     *            an expression that targets a property node
     */
    public XPlanFlattenFeature( Expression exp ) {
        this.exp = exp;
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String description = null;
        try {
            TypedObjectNodeArray<TypedObjectNode> props = Expressions.castToArray( exp.evaluate( feature ) );
            if ( props != null ) {
                description = "";
                for ( TypedObjectNode o : props.getElements() ) {
                    if ( !( o instanceof Property ) ) {
                        throw new IllegalArgumentException( "Expression targets '" + o.getClass()
                                                            + "', but can only be evaluated for property nodes." );
                    }
                    description += flatten( (Property) o );
                }
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            String msg = "Error flattening property '" + exp + "' for feature '" + feature.getId() + " when "
                         + description + " : " + e.getMessage();
            throw new RuntimeException( msg, e );
        }
        return toPrimitiveValue( description );
    }

    private String flatten( Property prop ) {
        TypedObjectNode value = prop.getValue();
        if ( value == null ) {
            return "";
        } else if ( value instanceof Feature ) {
            Feature subFeature = (Feature) value;
            if ( subFeature instanceof Reference ) {
                subFeature = (Feature) ( (Reference<?>) subFeature ).getReferencedObject();
            }
            if ( subFeature != null ) {
                return flattenFeature( subFeature );
            }
        } /*
           * else if ( value instanceof ElementNode ) { // TODO: Flatten ElementNode }
           */else {
            throw new IllegalArgumentException( "Unexpected property value type: " + value.getClass() );
        }
        return "";
    }

    private String flattenFeature( Feature feature ) {

        String s = "";
        if ( !( feature instanceof GMLReference<?> ) || ( (GMLReference<?>) feature ).isLocal() ) {
            String ftName = feature.getName().getLocalPart();
            String ns = feature.getName().getNamespaceURI();
            s = flattenExterneReferenzPlan( feature );
            if ( "".equals( s ) ) {
                if ( isXP_GenerAttribut( feature ) ) {
                    TypedObjectNode name = getPropertyValue( feature, new QName( ns, "name" ) );
                    TypedObjectNode wert = getPropertyValue( feature, new QName( ns, "wert" ) );
                    s = "[\"" + name + "\"=\"" + wert + "\"]";
                } else if ( "XP_ExterneReferenz".equals( feature.getName().getLocalPart() ) ) {
                    s += createUrlForExterneReferenz( feature );
                } else if ( "XP_TextAbschnitt".equals( ftName ) ) {
                    s = XplanTextAbschnitte.toString( feature );
                } else if ( "XP_BegruendungAbschnitt".equals( ftName ) ) {
                    s = XplanBegruendungAbschnitte.toString( feature );
                } else if ( "XP_VerfahrensMerkmal".equals( ftName ) ) {
                    s = concatenateVerfahrensMerkmal( feature );
                } else if ( "XP_Hoehenangabe".equals( ftName ) ) {
                    s = "[";
                    // compress all properties of primitive type
                    List<Property> props = feature.getProperties();
                    for ( Property prop : props ) {
                        if ( prop.getType() instanceof SimplePropertyType ) {
                            String propLocal = prop.getName().getLocalPart();
                            TypedObjectNode value = XPlanSynthesizer.rules.get( ftName + "/" + propLocal ).evaluate( feature );
                            s += concatenateValues( propLocal, value ) + ";";
                        }
                    }
                    s += "]";
                } else {
                    s = "[" + feature.getId() + "]";
                }
            }
        } else {
            GMLReference<?> externalRef = (GMLReference<?>) feature;
            s = "[" + escape( externalRef.getURI() ) + "]";
        }
        return s;
    }

    private boolean isXP_GenerAttribut( Feature feature ) {
        XPlanVersion version = determineBaseVersion( feature.getName() );
        if ( version == XPLAN_40 || version == XPLAN_41 ) {
            return false;
        }
        FeatureType ft = feature.getType();
        AppSchema schema = ft.getSchema();
        String ns = feature.getName().getNamespaceURI();
        FeatureType generAttributFt = schema.getFeatureType( new QName( ns, "XP_GenerAttribut" ) );
        return schema.isSubType( generAttributFt, ft );
    }

    private String concatenateValues( String propName, TypedObjectNode value ) {
        String resultString = propName + "=";
        if ( value instanceof Property ) {
            value = ( (Property) value ).getValue();
        }
        if ( value instanceof PrimitiveValue ) {
            resultString += value.toString();
        }
        // if ( value instanceof Object[] ) {
        // Object[] values = (Object[]) value;
        // for ( Object val : values ) {
        // if ( val instanceof BigDecimal ) {
        // resultString += ( (BigDecimal) val ).toString();
        // } else if ( val instanceof BigInteger ) {
        // resultString += ( (BigInteger) val ).toString();
        // } else {
        // resultString += (String) val;
        // }
        // }
        // } else {
        // if ( value instanceof BigDecimal ) {
        // resultString += ( (BigDecimal) value ).toString();
        // } else if ( value instanceof BigInteger ) {
        // resultString += ( (BigInteger) value ).toString();
        // } else {
        // resultString += (String) value;
        // }
        // }
        return resultString;
    }

    private String flattenExterneReferenzPlan( Feature feature ) {
        StringBuilder sBuilder = new StringBuilder();

        FeatureType ft = feature.getType();
        AppSchema schema = ft.getSchema();
        String ns = feature.getName().getNamespaceURI();

        FeatureType rasterAenderungFt = schema.getFeatureType( new QName( ns, "XP_RasterplanAenderung" ) );
        FeatureType rasterBasisFt = schema.getFeatureType( new QName( ns, "XP_RasterplanBasis" ) );

        if ( schema.isSubType( rasterAenderungFt, ft ) ) {
            QName refName = new QName( ns, "refScan" );
            List<Property> props = feature.getProperties( refName );
            for ( Property prop : props ) {
                Object value = prop.getValue();
                Feature refFeature = ( (FeatureReference) value ).getReferencedObject(); // XP_ExterneReferenzPlan
                sBuilder.append( createURLForExterneReferenzPlan( refFeature ) );
            }

        } else if ( schema.isSubType( rasterBasisFt, ft ) ) {
            QName refName = new QName( ns, "refScan" );
            List<Property> props = feature.getProperties( refName );
            for ( Property prop : props ) {
                Object value = prop.getValue();
                if ( value != null ) {
                    if ( value instanceof FeatureReference ) {
                        Feature refFeature = ( (FeatureReference) value ).getReferencedObject(); // XP_ExterneReferenzPlan
                        sBuilder.append( createURLForExterneReferenzPlan( refFeature ) );
                    } else {
                        LOG.warn( "Flattening of ExterneReferenzPlan is not supported for class {}", value.getClass() );
                    }
                }
            }

        } else if ( "XP_ExterneReferenzPlan".equals( feature.getName().getLocalPart() ) ) {
            sBuilder.append( createURLForExterneReferenzPlan( feature ) );
        }
        return sBuilder.toString();
    }

    private String createURLForExterneReferenzPlan( Feature feature ) {
        String s = "";

        String ns = feature.getName().getNamespaceURI();
        QName referenzURLName = new QName( ns, "referenzURL" );
        TypedObjectNode referenzUrl = getPropertyValue( feature, referenzURLName );
        if ( referenzUrl != null ) {
            String referenzUrlString = referenzUrl.toString();
            if ( referenzUrlString != null ) {
                if ( referenzUrlString.contains( "/:" ) ) {
                    // absolute URL
                    s += "[" + escape( referenzUrlString ) + "]";
                } else {
                    s += "[/getAttachment?featureID=" + feature.getId() + "&filename=" + escape( referenzUrlString )
                         + "]";
                }
            }
        }

        QName georefURLName = new QName( ns, "georefURL" );
        TypedObjectNode georefURL = getPropertyValue( feature, georefURLName );
        if ( georefURL != null ) {
            String georefUrlString = georefURL.toString();
            if ( georefUrlString.contains( "/:" ) ) {
                // absolute URL
                s += "[" + escape( georefUrlString ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + feature.getId() + "&filename=" + escape( georefUrlString ) + "]";
            }
        }
        return s;
    }

    static String createUrlForExterneReferenz( Feature feature ) {
        String s = "";
        String ns = feature.getName().getNamespaceURI();
        QName referenzURLName = new QName( ns, "referenzURL" );
        String referenzURL = getPropertyValue( feature, referenzURLName ).toString();
        if ( referenzURL != null ) {
            if ( referenzURL.contains( ":/" ) ) {
                // absolute URL
                s += "[" + escape( referenzURL ) + "]";
            } else {
                s += "[/getAttachment?featureID=" + feature.getId() + "&filename=" + escape( referenzURL ) + "]";
            }
        }
        return s;
    }

    static String createUrlForExterneReferenz( ElementNode xpExterneReferenz, String featureId ) {
        String ns = xpExterneReferenz.getName().getNamespaceURI();
        QName referenzURLName = new QName( ns, "referenzURL" );
        for ( TypedObjectNode child : xpExterneReferenz.getChildren() ) {
            if ( child instanceof ElementNode ) {
                ElementNode childEl = (ElementNode) child;
                if ( childEl.getName().equals( referenzURLName ) && !childEl.getChildren().isEmpty() ) {
                    String referenzURL = ( "" + childEl.getChildren().get( 0 ) ).trim();
                    if ( referenzURL.contains( ":/" ) ) {
                        return "[" + escape( referenzURL ) + "]";
                    }
                    return "[/getAttachment?featureID=" + featureId + "&filename=" + escape( referenzURL ) + "]";
                }
            }
        }
        return "";
    }

    private String concatenateVerfahrensMerkmal( Feature feature ) {
        String s = "[";
        String ns = feature.getName().getNamespaceURI();
        Date datum = (Date) Features.getPropertyValue( feature, new QName( ns, "datum" ) );
        String vermerk = Features.getPropertyValue( feature, new QName( ns, "vermerk" ) ).toString();
        String signatur = Features.getPropertyValue( feature, new QName( ns, "signatur" ) ).toString();
        String signiert = Features.getPropertyValue( feature, new QName( ns, "signiert" ) ).toString();
        s += "";
        if ( datum != null ) {
            s += datum + ": ";
        }
        if ( vermerk != null ) {
            s += "\"" + vermerk + "\"";
        }
        s += "(";
        if ( signatur != null && !signatur.isEmpty() ) {
            s += signatur + ", ";
        }
        if ( "true".equalsIgnoreCase( signiert ) ) {
            s += "signiert";
        } else if ( "false".equalsIgnoreCase( signiert ) ) {
            s += "nicht signiert";
        }
        s += ")]";
        return s;
    }

    private static String escape( String desc ) {
        String result = desc;
        if ( result.startsWith( "[" ) && result.endsWith( "]" ) ) {
            result = result.substring( 1, result.length() - 1 );
        }
        result = result.replace( "][", "][][" );
        return result;
    }

    // private String concatenateExterneReferenzPlan( Feature feature ) {
    // String s = "";
    // String ns = feature.getName().getNamespaceURI();
    // String informationssystemURL = (String) feature.getPropertyValue( new QName( ns, "informationssystemURL" ) );
    // String beschreibung = (String) feature.getPropertyValue( new QName( ns, "beschreibung" ) );
    // String referenzName = (String) feature.getPropertyValue( new QName( ns, "referenzName" ) );
    // String referenzURL = (String) feature.getPropertyValue( new QName( ns, "referenzURL" ) );
    // String referenzMimeType = (String) feature.getPropertyValue( new QName( ns, "referenzMimeType" ) );
    // String georefURL = (String) feature.getPropertyValue( new QName( ns, "georefURL" ) );
    // String georefMimeType = (String) feature.getPropertyValue( new QName( ns, "georefMimeType" ) );
    //
    // if ( referenzName != null ) {
    // s += "[\"" + referenzName + "\"=";
    // } else {
    // s += "[keine referenz ";
    // }
    // if ( beschreibung != null ) {
    // s += "\"" + beschreibung + "\" ";
    // } else {
    // s += " keine beschreibung ";
    // }
    // if ( georefURL != null ) {
    // s += "(" + georefURL + ", ";
    // } else {
    // s += "(keine geoRefURL, ";
    // }
    // if ( referenzURL != null ) {
    // s += "(" + referenzURL + " ";
    // } else {
    // s += "(keine referenzURL ";
    // }
    //
    // if ( informationssystemURL != null ) {
    // s += "in " + informationssystemURL + ", ";
    // } else {
    // s += " keine informationssystemURL, ";
    // }
    //
    // if ( referenzMimeType != null ) {
    // s += referenzMimeType + ", ";
    // } else {
    // s += "keine referenzMimeType, ";
    // }
    // if ( georefMimeType != null ) {
    // s += georefMimeType + ")]";
    // } else {
    // s += "keine geoRefMimeType)]";
    // }
    //
    // return s;
    // }
    //
    // private String concatenateExterneReferenz( Feature feature ) {
    // String s = "";
    // String ns = feature.getName().getNamespaceURI();
    // String informationssystemURL = (String) feature.getPropertyValue( new QName( ns, "informationssystemURL" ) );
    // String referenzName = (String) feature.getPropertyValue( new QName( ns, "referenzName" ) );
    // String referenzURL = (String) feature.getPropertyValue( new QName( ns, "referenzURL" ) );
    // String referenzMimeType = (String) feature.getPropertyValue( new QName( ns, "referenzMimeType" ) );
    //
    // if ( referenzName != null ) {
    // s += "[\"" + referenzName + "\" ";
    // } else {
    // s += "[keine referenz ";
    // }
    //
    // if ( referenzURL != null ) {
    // s += "(" + referenzURL + " ";
    // } else {
    // s += "(keine referenzURL ";
    // }
    //
    // if ( informationssystemURL != null ) {
    // s += "in " + informationssystemURL + ", ";
    // } else {
    // s += " keine informationssystemURL, ";
    // }
    //
    // if ( referenzMimeType != null ) {
    // s += referenzMimeType + ")]";
    // } else {
    // s += "keine referenzMimeType)]";
    // }
    // return s;
    // }
}
