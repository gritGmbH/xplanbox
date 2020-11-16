/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.BaseData;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;
import de.latlon.xplan.manager.web.shared.edit.ValidityPeriod;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.datetime.Temporal;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.BaseType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.getByCode;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;

/**
 * Factory to parse {@link XPlanToEdit}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XPlanToEditFactory {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanToEditFactory.class );

    /**
     * Parses an {@link XPlanToEdit} from the passed {@link FeatureCollection}.
     *
     * @param xPlan
     *                         used to extract some metadata, may be <code>null</code>
     * @param featureCollection
     *                         to parse the editable values from, never <code>null</code>
     * @return the xPlanToEdit, never <code>null</code>
     */
    public XPlanToEdit createXPlanToEdit( XPlan xPlan, FeatureCollection featureCollection ) {
        Iterator<Feature> iterator = featureCollection.iterator();
        XPlanToEdit xPlanToEdit = new XPlanToEdit();
        while ( iterator.hasNext() ) {
            Feature feature = iterator.next();
            String nameOfFeature = feature.getName().getLocalPart();
            if ( "BP_Plan".equals( nameOfFeature ) ) {
                parseBPPlan( feature, xPlanToEdit );
            } else if ( "BP_Bereich".equals( nameOfFeature ) ) {
                parseBPBereich( feature, xPlanToEdit );
            }
        }
        setValidityPeriod( xPlan, xPlanToEdit );
        return xPlanToEdit;
    }

    private void setValidityPeriod( XPlan xPlan, XPlanToEdit xPlanToEdit ) {
        if ( xPlan != null && xPlan.getXplanMetadata() != null ) {
            AdditionalPlanData xplanMetadata = xPlan.getXplanMetadata();
            xplanMetadata.getStartDateTime();
            ValidityPeriod validityPeriod = xPlanToEdit.getValidityPeriod();
            validityPeriod.setStart( xplanMetadata.getStartDateTime() );
            validityPeriod.setEnd( xplanMetadata.getEndDateTime() );
        }
    }

    private void parseBPPlan( Feature feature, XPlanToEdit xPlanToEdit ) {
        LOG.debug( "Parse properties from BP_Plan" );
        BaseData baseData = xPlanToEdit.getBaseData();
        for ( Property property : feature.getProperties() ) {
            String propertyName = property.getName().getLocalPart();
            TypedObjectNode propertyValue = property.getValue();
            if ( "name".equals( propertyName ) ) {
                baseData.setPlanName( asString( propertyValue ) );
            } else if ( "beschreibung".equals( propertyName ) ) {
                baseData.setDescription( asString( propertyValue ) );
            } else if ( "technHerstellDatum".equals( propertyName ) ) {
                baseData.setCreationDate( asDate( propertyValue ) );
            } else if ( "untergangsDatum".equals( propertyName ) ) {
                baseData.setLossDate( asDate( propertyValue ) );
            } else if ( "planArt".equals( propertyName ) ) {
                baseData.setPlanTypeCode( asInteger( propertyValue ) );
            } else if ( "sonstPlanArt".equals( propertyName ) ) {
                baseData.setOtherPlanTypeCode( asInteger( propertyValue ) );
            } else if ( "verfahren".equals( propertyName ) ) {
                baseData.setMethodCode( asInteger( propertyValue ) );
            } else if ( "rechtsstand".equals( propertyName ) ) {
                baseData.setLegislationStatusCode( asInteger( propertyValue ) );
            } else if ( "rechtsverordnungsDatum".equals( propertyName ) ) {
                baseData.setRegulationDate( asDate( propertyValue ) );
            } else if ( "aendert".equals( propertyName ) ) {
                parseChange( property, xPlanToEdit, CHANGES );
            } else if ( "wurdeGeaendertVon".equals( propertyName ) ) {
                parseChange( property, xPlanToEdit, CHANGED_BY );
            } else if ( "refBegruendung".equals( propertyName ) ) {
                parseReference( property, xPlanToEdit, BEGRUENDUNG );
            } else if ( "refRechtsplan".equals( propertyName ) ) {
                parseReference( property, xPlanToEdit, RECHTSPLAN );
            } else if ( "refGruenordnungsplan".equals( propertyName ) ) {
                parseReference( property, xPlanToEdit, GRUENORDNUNGSPLAN );
            } else if ( "externeReferenz".equals( propertyName ) ) {
                parseExterneReference( property, xPlanToEdit );
            } else if ( "texte".equals( propertyName ) ) {
                parseTextReference( property, xPlanToEdit );
            }
        }
    }

    private void parseBPBereich( Feature feature, XPlanToEdit xPlanToEdit ) {
        LOG.debug( "Parse properties from BP_Bereich" );
        for ( Property property : feature.getProperties() ) {
            String propertyName = property.getName().getLocalPart();
            if ( "rasterBasis".equals( propertyName ) ) {
                parseRasterBasis( property, xPlanToEdit );
            } else if ( "refScan".equals( propertyName ) ) {
                parseRasterBasisRefScan( xPlanToEdit, property );
            }
        }
    }

    private void parseRasterBasisRefScan( XPlanToEdit xPlanToEdit, Property property ) {
        RasterBasis rasterBasis = new RasterBasis();
        RasterReference rasterReference = parseRasterReference( property, SCAN );
        rasterBasis.addRasterReference( rasterReference );
        xPlanToEdit.setRasterBasis( rasterBasis );
    }

    private void parseRasterBasis( Property property, XPlanToEdit xPlanToEdit ) {
        TypedObjectNode propertyValue = property.getValue();
        if ( propertyValue instanceof FeatureReference ) {
            RasterBasis rasterBasis = parseRasterWithReferences( propertyValue );
            xPlanToEdit.setRasterBasis( rasterBasis );
        }
    }

    private RasterBasis parseRasterWithReferences( TypedObjectNode propertyValue ) {
        Feature referencedObject = ( (FeatureReference) propertyValue ).getReferencedObject();
        String featureId = referencedObject.getId();
        RasterBasis rasterPlanChange = new RasterBasis( featureId );
        for ( Property prop : referencedObject.getProperties() ) {
            String propName = prop.getName().getLocalPart();
            if ( "refLegende".equals( propName ) ) {
                RasterReference rasterReference = parseRasterReference( prop, LEGEND );
                rasterPlanChange.addRasterReference( rasterReference );
            } else if ( "refScan".equals( propName ) ) {
                RasterReference rasterReference = parseRasterReference( prop, SCAN );
                rasterPlanChange.addRasterReference( rasterReference );
            } else if ( "refText".equals( propName ) ) {
                RasterReference rasterReference = parseRasterReference( prop, TEXT );
                rasterPlanChange.addRasterReference( rasterReference );
            }
        }
        return rasterPlanChange;
    }

    private RasterReference parseRasterReference( Property prop, RasterReferenceType rasterReferenceType ) {
        RasterReference rasterReference = new RasterReference();
        rasterReference.setType( rasterReferenceType );
        List<TypedObjectNode> children = prop.getChildren();
        String featureId = parseReference( children, rasterReference );
        rasterReference.setFeatureId( featureId );
        if ( children.get( 0 ) instanceof GenericXMLElement ) {
            GenericXMLElement genericXmlElement = (GenericXMLElement) children.get( 0 );
            parseRasterReference( rasterReference, genericXmlElement.getChildren() );
        } else if ( children.get( 0 ) instanceof FeatureReference ) {
            Feature referencedObject = ( (FeatureReference) children.get( 0 ) ).getReferencedObject();
            parseRasterReference( rasterReference, referencedObject.getProperties() );
        }
        return rasterReference;
    }

    private void parseRasterReference( RasterReference rasterReference, List<? extends TypedObjectNode> children ) {
        for ( TypedObjectNode child : children ) {
            if ( child instanceof SimpleProperty ) {
                SimpleProperty childProperty = (SimpleProperty) child;
                String propName = childProperty.getName().getLocalPart();
                PrimitiveValue value = childProperty.getValue();
                parseRasterReference( rasterReference, propName, value );
            } else if ( child instanceof GenericXMLElement ) {
                GenericXMLElement childProperty = (GenericXMLElement) child;
                String propName = childProperty.getName().getLocalPart();
                PrimitiveValue value = childProperty.getValue();
                parseRasterReference( rasterReference, propName, value );
            }
        }
    }

    private void parseRasterReference( RasterReference rasterReference, String propName, PrimitiveValue value ) {
        if ( "art".equals( propName ) ) {
            rasterReference.setArt( ExterneReferenzArt.getByCode( asString( value ) ) );
        } else if ( "informationssystemURL".equals( propName ) ) {
            rasterReference.setInformationssystemURL( asString( value ) );
        } else if ( "referenzName".equals( propName ) ) {
            rasterReference.setReferenzName( asString( value ) );
        } else if ( "beschreibung".equals( propName ) ) {
            rasterReference.setBeschreibung( asString( value ) );
        } else if ( "datum".equals( propName ) ) {
            rasterReference.setDatum( asDate( value ) );
        } else if ( "referenzMimeType".equals( propName ) ) {
            rasterReference.setReferenzMimeType( getByCode( asString( value ) ) );
        } else if ( "georefMimeType".equals( propName ) ) {
            rasterReference.setGeorefMimeType( getByCode( asString( value ) ) );
        }
    }

    private void parseTextReference( Property property, XPlanToEdit xPlanToEdit ) {
        TypedObjectNode propertyValue = property.getValue();
        if ( propertyValue instanceof FeatureReference ) {
            Feature referencedObject = ( (FeatureReference) propertyValue ).getReferencedObject();
            String featureId = referencedObject.getId();
            Text text = new Text( featureId );
            for ( Property prop : referencedObject.getProperties() ) {
                String propName = prop.getName().getLocalPart();
                TypedObjectNode propValue = prop.getValue();
                if ( "schluessel".equals( propName ) ) {
                    text.setKey( asString( propValue ) );
                } else if ( "gesetzlicheGrundlage".equals( propName ) ) {
                    text.setBasis( asString( propValue ) );
                } else if ( "text".equals( propName ) ) {
                    text.setText( asString( propValue ) );
                } else if ( "refText".equals( propName ) ) {
                    parseReference( prop.getChildren(), text );
                } else if ( "rechtscharakter".equals( propName ) ) {
                    text.setRechtscharakter( TextRechtscharacterType.fromCode( asInteger( propValue ) ) );
                }
            }
            xPlanToEdit.addText( text );
        }
    }

    private void parseChange( Property property, XPlanToEdit xPlanToEdit, ChangeType changeType ) {
        if ( property instanceof GenericProperty ) {
            List<TypedObjectNode> children = property.getChildren();
            if ( children.size() == 1 && children.get( 0 ) instanceof GenericXMLElement ) {
                Change change = new Change();
                change.setType( changeType );
                GenericXMLElement genericXmlElement = (GenericXMLElement) children.get( 0 );
                for ( TypedObjectNode child : genericXmlElement.getChildren() ) {
                    if ( child instanceof GenericXMLElement ) {
                        GenericXMLElement childProperty = (GenericXMLElement) child;
                        if ( "planName".equals( childProperty.getName().getLocalPart() ) ) {
                            change.setPlanName( asString( childProperty.getValue() ) );
                        } else if ( "rechtscharakter".equals( childProperty.getName().getLocalPart() ) ) {
                            change.setLegalNatureCode( asInteger( childProperty.getValue() ) );
                        } else if ( "nummer".equals( childProperty.getName().getLocalPart() ) ) {
                            change.setNumber( asString( childProperty.getValue() ) );
                        }
                    }
                }
                xPlanToEdit.addChange( change );
            } else {
                LOG.warn( "Could not parse property " + property );
            }
        } else if ( property instanceof SimpleProperty ) {
            Change change = new Change();
            change.setType( changeType );
            change.setPlanName( asString( property.getValue() ) );
            xPlanToEdit.addChange( change );
        } else {
            LOG.warn( "Could not parse property " + property );
        }
    }

    private void parseExterneReference( Property property, XPlanToEdit xPlanToEdit ) {
        ReferenceType referenceType = detectType( property );
        if ( referenceType != null )
            parseReference( property, xPlanToEdit, referenceType );
    }

    private void parseReference( Property property, XPlanToEdit xPlanToEdit, ReferenceType referenceType ) {
        List<TypedObjectNode> children = property.getChildren();
        if ( children.size() == 1 && children.get( 0 ) instanceof GenericXMLElement ) {
            Reference reference = new Reference();
            reference.setType( referenceType );
            parseReference( children, reference );
            xPlanToEdit.addReference( reference );
        } else if ( children.size() == 1 && children.get( 0 ) instanceof FeatureReference ) {
            Feature referencedObject = ( (FeatureReference) children.get( 0 ) ).getReferencedObject();
            Reference reference = new Reference();
            reference.setType( referenceType );
            parseReferenceProperties( referencedObject.getProperties(), reference );
            xPlanToEdit.addReference( reference );
        } else {
            LOG.warn( "Could not parse property " + property );
        }
    }

    private String parseReference( List<TypedObjectNode> children, AbstractReference reference ) {
        if ( children.get( 0 ) instanceof GenericXMLElement ) {
            GenericXMLElement genericXmlElement = (GenericXMLElement) children.get( 0 );
            parseReferenceProperties( genericXmlElement.getChildren(), reference );
        } else if ( children.get( 0 ) instanceof FeatureReference ) {
            Feature referencedObject = ( (FeatureReference) children.get( 0 ) ).getReferencedObject();
            parseReferenceProperties( referencedObject.getProperties(), reference );
            return referencedObject.getId();
        }
        return null;
    }

    private void parseReferenceProperties( List<? extends TypedObjectNode> children, AbstractReference reference ) {
        for ( TypedObjectNode child : children ) {
            if ( child instanceof GenericXMLElement ) {
                GenericXMLElement childProperty = (GenericXMLElement) child;
                parseReferenceProperty( reference, childProperty.getName(), childProperty.getValue() );
            } else if ( child instanceof SimpleProperty ) {
                SimpleProperty childProperty = (SimpleProperty) child;
                parseReferenceProperty( reference, childProperty.getName(), childProperty.getValue() );
            }
        }
    }

    private void parseReferenceProperty( AbstractReference reference, QName name, PrimitiveValue value ) {
        if ( "georefURL".equals( name.getLocalPart() ) ) {
            reference.setGeoReference( asString( value ) );
        } else if ( "georefMimeType".equals( name.getLocalPart() ) ) {
            reference.setGeorefMimeType( getByCode( asString( value ) ) );
        } else if ( "art".equals( name.getLocalPart() ) ) {
            reference.setArt( ExterneReferenzArt.getByCode( asString( value ) ) );
        } else if ( "informationssystemURL".equals( name.getLocalPart() ) ) {
            reference.setInformationssystemURL( asString( value ) );
        } else if ( "referenzName".equals( name.getLocalPart() ) ) {
            reference.setReferenzName( asString( value ) );
        } else if ( "referenzURL".equals( name.getLocalPart() ) ) {
            reference.setReference( asString( value ) );
        } else if ( "referenzMimeType".equals( name.getLocalPart() ) ) {
            reference.setReferenzMimeType( getByCode( asString( value ) ) );
        } else if ( "beschreibung".equals( name.getLocalPart() ) ) {
            reference.setBeschreibung( asString( value ) );
        } else if ( "datum".equals( name.getLocalPart() ) ) {
            reference.setDatum( asDate( value ) );
        }
    }

    private ReferenceType detectType( Property property ) {
        List<TypedObjectNode> children = property.getChildren();
        if ( children.size() == 1 && children.get( 0 ) instanceof GenericXMLElement ) {
            GenericXMLElement genericXmlElement = (GenericXMLElement) children.get( 0 );
            return detectType( genericXmlElement.getChildren() );
        } else if ( children.size() == 1 && children.get( 0 ) instanceof FeatureReference ) {
            Feature referencedObject = ( (FeatureReference) children.get( 0 ) ).getReferencedObject();
            return detectType( referencedObject.getProperties() );
        }
        return null;
    }

    private ReferenceType detectType( List<? extends TypedObjectNode> children ) {
        for ( TypedObjectNode child : children ) {
            if ( child instanceof GenericXMLElement ) {
                GenericXMLElement childProperty = (GenericXMLElement) child;
                if ( "typ".equals( childProperty.getName().getLocalPart() ) ) {
                    String type = asString( childProperty.getValue() );
                    return ReferenceType.getBySpezExterneReferenceType( type );
                }
            }
        }
        return null;
    }

    private String asString( TypedObjectNode value ) {
        if ( value instanceof PrimitiveValue ) {
            return ( (PrimitiveValue) value ).getAsText().trim();
        }
        return null;
    }

    private int asInteger( TypedObjectNode value ) {
        if ( value instanceof PrimitiveValue ) {
            if ( BaseType.INTEGER.equals( ( (PrimitiveValue) value ).getType().getBaseType() ) )
                return (int) ( (PrimitiveValue) value ).getValue();
            String valueAsText = ( (PrimitiveValue) value ).getAsText();
            try {
                return Integer.parseInt( valueAsText );
            } catch ( NumberFormatException e ) {
                LOG.warn( "Could not parse {} as integer.", valueAsText );
            }
        }
        return -1;
    }

    private Date asDate( TypedObjectNode value ) {
        if ( value instanceof PrimitiveValue ) {
            BaseType baseType = ( (PrimitiveValue) value ).getType().getBaseType();
            if ( BaseType.TIME.equals( baseType ) || BaseType.DATE.equals( baseType ) || BaseType.DATE_TIME.equals(
                                    baseType ) )
                return ( (Temporal) ( (PrimitiveValue) value ).getValue() ).getDate();
        }
        return null;
    }

}
