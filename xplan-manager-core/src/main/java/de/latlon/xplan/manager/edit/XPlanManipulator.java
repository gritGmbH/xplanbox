//$HeadURL$
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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.RasterWithReferences;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.gml.property.PropertyType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.deegree.feature.property.SimpleProperty;
import org.deegree.feature.types.AppSchema;
import org.deegree.feature.types.FeatureType;
import org.deegree.feature.types.property.CustomPropertyType;
import org.deegree.feature.types.property.FeaturePropertyType;
import org.deegree.feature.types.property.SimplePropertyType;
import org.deegree.gml.reference.FeatureReference;
import org.deegree.gml.reference.GmlDocumentIdContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GREEN_STRUCTURES_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.LEGISLATION_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.REASON;

/**
 * Modifies the {@link FeatureCollection} representing an XPlanGML.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XPlanManipulator {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanManipulator.class );

    private static final QName XLINK_HREF_ATTRIBUTE = new QName( "http://www.w3.org/1999/xlink", "href" );

    private static final Map<String, RasterReferenceType> PROPNAME_TO_RASTERREFERENCE = initRasterReferenceToPropName();

    private static final List<String> EXTERNAL_PLAN_PROPERTIES = initExternalPlanProperties();

    private static final Map<ReferenceType, String> REF_TYPE_TO_CODE = initRefTypeToCode();

    /**
     * Modifies the {@link FeatureCollection} representing an XPlanGML, by the changes described in {@link XPlanToEdit}.
     *
     * @param planToEdit
     *                 the {@link FeatureCollection} to edit, never <code>null</code>
     * @param planWithChanges
     *                 containing the changes, never <code>null</code>
     * @param version
     *                 of the plan, never <code>null</code>
     * @param type
     *                 of the plan, never <code>null</code>
     * @param schema
     *                 of the plan, never <code>null</code>
     */
    public void modifyXPlan( FeatureCollection planToEdit, XPlanToEdit planWithChanges, XPlanVersion version,
                             XPlanType type, AppSchema schema ) {
        checkVersionAndType( version, type );

        GmlDocumentIdContext context = new GmlDocumentIdContext( version.getGmlVersion() );
        context.setApplicationSchema( schema );

        List<String> previouslyReferencedTextFeatureIds = parseReferencedTextFeatureIds( planToEdit );

        List<Feature> featuresToAdd = new ArrayList<Feature>();
        List<Feature> featuresToRemove = new ArrayList<Feature>();
        List<String> referencesToRemove = new ArrayList<String>();
        for ( Feature feature : planToEdit ) {
            QName featureName = feature.getName();
            if ( isBPlan( featureName ) )
                modifyBPlan( context, version, planToEdit, feature, planWithChanges, schema, featuresToAdd,
                             featuresToRemove, referencesToRemove, previouslyReferencedTextFeatureIds );
            else if ( isXPRasterplanBasis( featureName ) )
                modifyXPRasterBasis( context, version, feature, planWithChanges, schema );
            else if ( isBPRasterplanAenderung( featureName ) )
                modifyBPRasterplanAenderung( context, version, feature, planWithChanges, schema );
        }
        removeAllPropertiesWithReferences( planToEdit, referencesToRemove );
        planToEdit.removeAll( featuresToRemove );
        planToEdit.addAll( featuresToAdd );
    }

    private void modifyBPlan( GmlDocumentIdContext context, XPlanVersion version, FeatureCollection planToEdit,
                              Feature feature, XPlanToEdit changes, AppSchema schema, List<Feature> featuresToAdd,
                              List<Feature> featuresToRemove, List<String> referencesToRemove,
                              List<String> previouslyReferencedTextFeatureIds ) {
        modify( version, feature, "name", changes.getBaseData().getPlanName() );
        modify( version, feature, "beschreibung", changes.getBaseData().getDescription() );
        modify( version, feature, "technHerstellDatum", changes.getBaseData().getCreationDate() );
        modify( version, feature, "untergangsDatum", changes.getBaseData().getLossDate() );
        modify( version, feature, "rechtsverordnungsDatum", changes.getBaseData().getRegulationDate() );
        modifyCode( version, feature, "rechtsstand", changes.getBaseData().getLegislationStatusCode() );
        modifyCode( version, feature, "sonstPlanArt", changes.getBaseData().getOtherPlanTypeCode() );
        modifyCode( version, feature, "planArt", changes.getBaseData().getPlanTypeCode() );
        modifyChanges( version, feature, schema, "wurdeGeaendertVon", changes.getChanges(), CHANGED_BY );
        modifyChanges( version, feature, schema, "aendert", changes.getChanges(), CHANGES );
        modifyTexts( context, version, planToEdit, feature, schema, changes.getTexts(), featuresToAdd, featuresToRemove,
                     referencesToRemove, previouslyReferencedTextFeatureIds );
        if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) )
            modifyCode( version, feature, "verfahren", changes.getBaseData().getMethodCode() );
        modifyReferences( context, version, feature, changes, schema, featuresToAdd );
    }

    private void modifyXPRasterBasis( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                      XPlanToEdit planWithChanges, AppSchema schema ) {
        RasterWithReferences rasterBasis = planWithChanges.getRasterBasis();
        if ( rasterBasis != null )
            modifyRasterWithReference( version, feature, rasterBasis );
    }

    private void modifyBPRasterplanAenderung( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                              XPlanToEdit planWithChanges, AppSchema schema ) {
        List<RasterWithReferences> rasterPlanChanges = planWithChanges.getRasterPlanChanges();
        RasterWithReferences rasterPlanChange = findRasterPlanChangeByFeatureId( feature, rasterPlanChanges );
        if ( rasterPlanChange != null )
            modifyRasterWithReference( version, feature, rasterPlanChange );
        else
            LOG.warn( "No raster plan change with the id of the current BP_RasterplanAenderung found ({}), "
                      + "element be skipped.", feature.getId() );
    }

    private void modifyRasterWithReference( XPlanVersion version, Feature feature,
                                            RasterWithReferences rasterWithReference ) {
        List<Property> properties = feature.getProperties();
        List<RasterReference> rasterReferences = rasterWithReference.getRasterReferences();
        int rasterReferenceIndex = 0;
        for ( Property property : properties ) {
            RasterReferenceType rasterReferenceType = PROPNAME_TO_RASTERREFERENCE.get(
                            property.getName().getLocalPart() );
            if ( rasterReferenceType != null ) {
                boolean wasUpdated = modifyRasterReference( version, rasterReferences, rasterReferenceIndex, property,
                                                            rasterReferenceType );
                if ( wasUpdated )
                    rasterReferenceIndex++;
            }
        }
    }

    private boolean modifyRasterReference( XPlanVersion version, List<RasterReference> rasterReferences,
                                           int rasterReferenceIndex, Property property,
                                           RasterReferenceType expectedRasterReferenceType ) {
        if ( rasterReferenceIndex < rasterReferences.size() ) {
            return updateReference( version, rasterReferences, rasterReferenceIndex, property,
                                    expectedRasterReferenceType );
        } else {
            LOG.warn( "More properties in XPlan-GML than passed raster references, following will be skipped." );
        }
        return false;
    }

    private boolean updateReference( XPlanVersion version, List<RasterReference> rasterReferences,
                                     int rasterReferenceIndex, Property property,
                                     RasterReferenceType expectedRasterReferenceType ) {
        RasterReference currentRasterReference = rasterReferences.get( rasterReferenceIndex );
        if ( !expectedRasterReferenceType.equals( currentRasterReference.getType() ) ) {
            LOG.warn( "Current raster reference type ({}) does not match the expected{}.",
                      currentRasterReference.getType(), expectedRasterReferenceType );
            return false;
        }
        List<TypedObjectNode> children = property.getChildren();
        if ( children.size() == 1 ) {
            TypedObjectNode firstChild = children.get( 0 );
            if ( firstChild instanceof GenericXMLElement ) {
                GenericXMLElement externalReference = (GenericXMLElement) firstChild;
                String namespaceUri = externalReference.getName().getNamespaceURI();
                GenericProperty georefProp = createGeoReferenzProperty( namespaceUri,
                                                                        currentRasterReference.getGeoReference() );
                updateRasterReferenceProperty( version, externalReference, "georefURL", georefProp );
                GenericProperty refProp = createReferenzProperty( namespaceUri, currentRasterReference.getReference() );
                updateRasterReferenceProperty( version, externalReference, "referenzURL", refProp );
            } else if ( firstChild instanceof FeatureReference ) {
                FeatureReference externalReference = (FeatureReference) firstChild;
                Feature externalReferenceFeature = externalReference.getReferencedObject();
                modify( version, externalReferenceFeature, "georefURL", currentRasterReference.getGeoReference() );
                modify( version, externalReferenceFeature, "referenzURL", currentRasterReference.getReference() );
            }
        }
        return true;
    }

    private void updateRasterReferenceProperty( XPlanVersion version, GenericXMLElement externalReference,
                                                String propertyName, GenericProperty property ) {
        QName propName = new QName( externalReference.getName().getNamespaceURI(), propertyName );
        List<TypedObjectNode> externalReferenceChilds = externalReference.getChildren();
        List<TypedObjectNode> childsToRemove = new ArrayList<TypedObjectNode>();
        for ( TypedObjectNode externalReferenceChild : externalReferenceChilds ) {
            if ( externalReferenceChild instanceof GenericXMLElement
                 && ( (GenericXMLElement) externalReferenceChild ).getName().equals( propName ) )
                childsToRemove.add( externalReferenceChild );
        }
        externalReferenceChilds.removeAll( childsToRemove );
        if ( property != null ) {
            int index = findIndex( version, externalReferenceChilds, propName, EXTERNAL_PLAN_PROPERTIES );
            externalReferenceChilds.add( index, property );
        }
        externalReference.setChildren( externalReferenceChilds );
    }

    private RasterWithReferences findRasterPlanChangeByFeatureId( Feature feature,
                                                                  List<RasterWithReferences> rasterPlanChanges ) {
        for ( RasterWithReferences rasterPlanChange : rasterPlanChanges ) {
            if ( rasterPlanChange.getFeatureId() != null && rasterPlanChange.getFeatureId().equals( feature.getId() ) )
                return rasterPlanChange;
        }
        return null;
    }

    private void modifyCode( XPlanVersion version, Feature feature, String propertyName, int newCodeValue ) {
        QName propName = new QName( feature.getName().getNamespaceURI(), propertyName );
        if ( newCodeValue > 0 ) {
            PropertyType propertyDeclaration = feature.getType().getPropertyDeclaration( propName );
            if ( propertyDeclaration instanceof SimplePropertyType ) {
                Property prop = createSimpleProperty( feature, propName, Integer.toString( newCodeValue ) );
                addOrReplaceProperty( version, feature, propName, prop );
            } else {
                LOG.warn( "Editing of " + propertyDeclaration.getClass() + " (PropertyName: " + propertyName
                          + ") is not supported yet." );
            }
        } else {
            removeProperties( feature, propName );
        }
    }

    private void modifyChanges( XPlanVersion version, Feature feature, AppSchema schema, String propertyName,
                                List<Change> changes, ChangeType changedType ) {
        QName propName = new QName( feature.getName().getNamespaceURI(), propertyName );
        List<Property> properties = new ArrayList<Property>();
        for ( Change change : changes ) {
            if ( changedType.equals( change.getType() ) ) {
                if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) ) {
                    addProperty( properties, createVerbundenerPlanProperty( feature, schema, change, propName ) );
                } else if ( XPLAN_3.equals( version ) ) {
                    addProperty( properties, createSimpleProperty( feature, propName, change.getPlanName() ) );
                }
            }
        }
        addOrReplaceProperties( version, feature, propName, properties );
    }

    private void modifyTexts( GmlDocumentIdContext context, XPlanVersion version, FeatureCollection planToEdit,
                              Feature feature, AppSchema schema, List<Text> texts, List<Feature> featuresToAdd,
                              List<Feature> featuresToRemove, List<String> referencesToRemove,
                              List<String> previouslyReferencedTextFeatureIds ) {
        String namespaceUri = feature.getName().getNamespaceURI();
        QName propName = new QName( namespaceUri, "texte" );
        List<Property> properties = new ArrayList<Property>();
        for ( Text text : texts ) {
            String gmlid = text.getFeatureId();
            if ( gmlid != null ) {
                QName textFeatureTypeName = getTextAbschnittName( version, namespaceUri );
                Feature oldTextFeature = detectFeatureById( planToEdit, textFeatureTypeName, gmlid );
                if ( oldTextFeature != null )
                    featuresToRemove.add( oldTextFeature );
            } else {
                gmlid = generateGmlId( propName );
            }
            Property linkProp = createPropertyWithHrefAttribute( context, feature.getType(), propName, gmlid );
            addProperty( properties, linkProp );
            createAndAddTextFeature( context, version, schema, namespaceUri, text, gmlid, featuresToAdd );
        }
        for ( String previouslyReferencedTextFeatureId : previouslyReferencedTextFeatureIds ) {
            if ( isNotLongerReferenced( texts, previouslyReferencedTextFeatureId ) ) {
                QName textFeatureTypeName = getTextAbschnittName( version, namespaceUri );
                Feature oldTextFeature = detectFeatureById( planToEdit, textFeatureTypeName,
                                                            previouslyReferencedTextFeatureId );
                if ( oldTextFeature != null ) {
                    featuresToRemove.add( oldTextFeature );
                    referencesToRemove.add( previouslyReferencedTextFeatureId );
                }
            }
        }
        addOrReplaceProperties( version, feature, propName, properties );
    }

    private boolean isNotLongerReferenced( List<Text> texts, String previouslyReferencedTextFeatureId ) {
        for ( Text text : texts ) {
            if ( previouslyReferencedTextFeatureId.equals( text.getFeatureId() ) )
                return false;
        }
        return true;
    }

    private Feature detectFeatureById( FeatureCollection planToEdit, QName featureTypeName, String featureId ) {
        for ( Feature feature : planToEdit ) {
            if ( featureTypeName.equals( feature.getName() ) && featureId.equals( feature.getId() ) )
                return feature;
        }
        return null;
    }

    private void modifyReferences( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                   XPlanToEdit changes, AppSchema schema, List<Feature> featuresToAdd ) {
        if ( XPLAN_41.equals( version ) || XPLAN_3.equals( version ) ) {
            modifyReferences( context, version, feature, schema, "refRechtsplan", changes.getReferences(),
                              LEGISLATION_PLAN, featuresToAdd );
            modifyReferences( context, version, feature, schema, "refBegruendung", changes.getReferences(), REASON,
                              featuresToAdd );
            if ( XPLAN_41.equals( version ) )
                modifyReferences( context, version, feature, schema, "refGruenordnungsplan", changes.getReferences(),
                                  GREEN_STRUCTURES_PLAN, featuresToAdd );
        } else if ( XPLAN_50.equals( version ) ) {
            modifyReferences_XPlan50( context, version, feature, changes, schema, featuresToAdd );
        }
    }

    private void modifyReferences_XPlan50( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                           XPlanToEdit changes, AppSchema schema, List<Feature> featuresToAdd ) {
        String namespaceUri = feature.getName().getNamespaceURI();
        QName propName = new QName( namespaceUri, "externeReferenz" );
        List<Property> properties = new ArrayList<>();
        FeatureType featureType = feature.getType();
        for ( Reference reference : changes.getReferences() ) {
            String spezExterneReferenzTyp = REF_TYPE_TO_CODE.get( reference.getType() );
            Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, reference,
                                                              featureType, properties, propName,
                                                              "XP_SpezExterneReferenz", spezExterneReferenzTyp );
            if ( refFeature != null )
                featuresToAdd.add( refFeature );
        }
        addOrReplaceProperties( version, feature, propName, properties );
    }

    private void modifyReferences( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                   AppSchema schema, String propertyName, List<Reference> references,
                                   ReferenceType refType, List<Feature> featuresToAdd ) {
        String namespaceUri = feature.getName().getNamespaceURI();
        QName propName = new QName( namespaceUri, propertyName );
        List<Property> properties = new ArrayList<>();
        FeatureType featureType = feature.getType();
        for ( Reference reference : references ) {
            if ( refType.equals( reference.getType() ) ) {
                Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, reference,
                                                                  featureType, properties, propName,
                                                                  "XP_ExterneReferenz", null );
                if ( refFeature != null )
                    featuresToAdd.add( refFeature );
            }
        }
        addOrReplaceProperties( version, feature, propName, properties );
    }

    private void modify( XPlanVersion version, Feature feature, String propertyName, String newValue ) {
        QName propName = new QName( feature.getName().getNamespaceURI(), propertyName );
        if ( newValue != null && newValue.length() > 0 ) {
            Property prop = createSimpleProperty( feature, propName, newValue );
            addOrReplaceProperty( version, feature, propName, prop );
        } else {
            removeProperties( feature, propName );
        }
    }

    private void modify( XPlanVersion version, Feature feature, String propertyName, Date newValue ) {
        QName propName = new QName( feature.getName().getNamespaceURI(), propertyName );
        if ( newValue != null ) {
            SimplePropertyType propType = (SimplePropertyType) feature.getType().getPropertyDeclaration( propName );
            org.deegree.commons.tom.datetime.Date date = new org.deegree.commons.tom.datetime.Date( newValue, null );
            Property prop = new GenericProperty( propType, new PrimitiveValue( date ) );
            addOrReplaceProperty( version, feature, propName, prop );
        } else {
            removeProperties( feature, propName );
        }
    }

    private void removeAllPropertiesWithReferences( FeatureCollection planToEdit, List<String> featuresToRemove ) {
        for ( Feature feature : planToEdit ) {
            List<Property> propertiesToRemove = new ArrayList<>();
            for ( Property property : feature.getProperties() ) {
                if ( !isPropertySecured( feature, property ) ) {
                    PrimitiveValue hrefValue = property.getAttributes().get( XLINK_HREF_ATTRIBUTE );
                    if ( hrefValue != null && featuresToRemove.contains( hrefValue.toString().substring( 1 ) ) )
                        propertiesToRemove.add( property );
                }
            }
            feature.getProperties().removeAll( propertiesToRemove );
        }
    }

    private boolean isPropertySecured( Feature feature, Property property ) {
        QName featureName = feature.getName();
        if ( isBPlan( featureName ) && new QName( featureName.getNamespaceURI(), "texte" ).equals(
                        property.getName() ) )
            return true;
        return false;
    }

    private void createAndAddTextFeature( GmlDocumentIdContext context, XPlanVersion version, AppSchema schema,
                                          String namespaceUri, Text text, String gmlid, List<Feature> featuresToAdd ) {
        QName textFeatureTypeName = getTextAbschnittName( version, namespaceUri );
        FeatureType textFeatureType = schema.getFeatureType( textFeatureTypeName );
        List<Property> props = new ArrayList<Property>();
        addProperty( props, createKeyProperty( namespaceUri, text.getKey() ) );
        addProperty( props, createBasisProperty( namespaceUri, text.getBasis() ) );
        addProperty( props, createTextProperty( namespaceUri, text.getText() ) );

        QName refPropName = new QName( namespaceUri, "refText" );
        Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, text, textFeatureType,
                                                          props, refPropName, "XP_ExterneReferenz", null );

        if ( XPLAN_50.equals( version ) ) {
            addProperty( props, createLegalNatureProperty( namespaceUri, text.getLegalNatureCode() ) );
        }
        if ( props.isEmpty() )
            return;
        featuresToAdd.add( textFeatureType.newFeature( gmlid, props, null ) );
        if ( refFeature != null )
            featuresToAdd.add( refFeature );
    }

    private Feature createAndAddExterneReferenz( GmlDocumentIdContext context, XPlanVersion version, AppSchema schema,
                                                 String namespaceUri, AbstractReference text, FeatureType featureType,
                                                 List<Property> props, QName refPropName,
                                                 String externeReferenzElementName, String spezExterneReferenzTyp ) {
        if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) ) {
            GenericProperty refProperty = createExterneReferenzProperty_XPlan41_XPlan50( schema, featureType,
                                                                                         refPropName, text,
                                                                                         externeReferenzElementName,
                                                                                         spezExterneReferenzTyp );
            addProperty( props, refProperty );
        } else if ( XPLAN_3.equals( version ) ) {
            String refGmlid = generateGmlId( refPropName );
            Property refLinkProperty = createPropertyWithHrefAttribute( context, featureType, refPropName, refGmlid );
            boolean added = addProperty( props, refLinkProperty );
            if ( added )
                return createExterneReferenzFeature_XPlan3( version, schema, namespaceUri, text, refGmlid );
        }
        return null;
    }

    private Feature createExterneReferenzFeature_XPlan3( XPlanVersion version, AppSchema schema, String namespaceUri,
                                                         AbstractReference reference, String gmlid ) {
        QName refFeatureTypeName = new QName( namespaceUri, "XP_ExterneReferenz" );
        FeatureType refFeatureType = schema.getFeatureType( refFeatureTypeName );
        List<Property> props = new ArrayList<Property>();
        if ( !XPLAN_3.equals( version ) )
            addProperty( props, createGeoReferenzProperty( namespaceUri, reference.getGeoReference() ) );
        addProperty( props, createReferenzProperty( namespaceUri, reference.getReference() ) );
        if ( props.isEmpty() )
            return null;
        return refFeatureType.newFeature( gmlid, props, null );
    }

    private GenericProperty createExterneReferenzProperty_XPlan41_XPlan50( AppSchema schema,
                                                                           FeatureType textFeatureType, QName propName,
                                                                           AbstractReference reference,
                                                                           String externeReferenzElementName,
                                                                           String spezExterneReferenzTyp ) {
        String namespaceUri = textFeatureType.getName().getNamespaceURI();
        PropertyType propType = textFeatureType.getPropertyDeclaration( propName );
        GenericProperty newProperty = new GenericProperty( propType, null );

        List<TypedObjectNode> subElementChilds = new ArrayList<>();
        add( subElementChilds, createGeoReferenzProperty( namespaceUri, reference.getGeoReference() ) );
        add( subElementChilds, createReferenzProperty( namespaceUri, reference.getReference() ) );
        if ( subElementChilds.isEmpty() )
            return null;
        if ( spezExterneReferenzTyp != null ) {
            add( subElementChilds, createReferenzTypProperty( namespaceUri, spezExterneReferenzTyp ) );
        }

        QName subElementName = new QName( namespaceUri, externeReferenzElementName );
        XSElementDeclaration subElementType = schema.getGMLSchema().getElementDecl( subElementName );
        GenericXMLElement gxe = new GenericXMLElement( subElementName, subElementType, null, subElementChilds );

        newProperty.setChildren( asList( gxe ) );
        return newProperty;
    }

    private GenericProperty createVerbundenerPlanProperty( Feature feature, AppSchema schema, Change change,
                                                           QName propName ) {
        String namespaceUri = feature.getName().getNamespaceURI();
        PropertyType propType = feature.getType().getPropertyDeclaration( propName );
        GenericProperty newProperty = new GenericProperty( propType, null );

        List<TypedObjectNode> subElementChilds = new ArrayList<TypedObjectNode>();
        add( subElementChilds, createPlanNameProperty( namespaceUri, change.getPlanName() ) );
        add( subElementChilds, createLegalNatureProperty( namespaceUri, change.getLegalNatureCode() ) );
        add( subElementChilds, createNumberProperty( namespaceUri, change.getNumber() ) );
        if ( subElementChilds.isEmpty() )
            return null;

        QName subElementName = new QName( namespaceUri, "XP_VerbundenerPlan" );
        XSElementDeclaration subElementType = schema.getGMLSchema().getElementDecl( subElementName );
        GenericXMLElement gxe = new GenericXMLElement( subElementName, subElementType, null, subElementChilds );

        newProperty.setChildren( asList( gxe ) );
        return newProperty;
    }

    private GenericProperty createPlanNameProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "planName" );
        return createProperty( propName, value, 1, 1 );
    }

    private GenericProperty createLegalNatureProperty( String namespaceUri, int value ) {
        QName propName = new QName( namespaceUri, "rechtscharakter" );
        return createProperty( propName, value, 1, 1 );
    }

    private GenericProperty createNumberProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "nummer" );
        return createProperty( propName, value, 1, 1 );
    }

    private GenericProperty createReferenzProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "referenzURL" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createGeoReferenzProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "georefURL" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createKeyProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "schluessel" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createBasisProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "gesetzlicheGrundlage" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createTextProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "text" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createReferenzTypProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "typ" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createProperty( QName propName, String value, int minOccurs, int maxOccurs ) {
        if ( value == null || "".equals( value ) )
            return null;
        CustomPropertyType planNameType = new CustomPropertyType( propName, minOccurs, maxOccurs, null, null );
        return new GenericProperty( planNameType, new PrimitiveValue( value ) );
    }

    private GenericProperty createProperty( QName propName, int value, int minOccurs, int maxOccurs ) {
        if ( value < 0 )
            return null;
        CustomPropertyType planNameType = new CustomPropertyType( propName, minOccurs, maxOccurs, null, null );
        return new GenericProperty( planNameType, new PrimitiveValue( Integer.toString( value ) ) );
    }

    private Property createSimpleProperty( Feature feature, QName propName, String newValue ) {
        SimplePropertyType propType = (SimplePropertyType) feature.getType().getPropertyDeclaration( propName );
        return new SimpleProperty( propType, newValue );
    }

    private Property createPropertyWithHrefAttribute( GmlDocumentIdContext context, FeatureType featureType,
                                                      QName propName, String attributeValue ) {
        FeaturePropertyType propType = (FeaturePropertyType) featureType.getPropertyDeclaration( propName );
        Map<QName, PrimitiveValue> attributes = new HashMap<QName, PrimitiveValue>();
        FeatureReference featureReference = new FeatureReference( context, "#" + attributeValue, null );
        attributes.put( XLINK_HREF_ATTRIBUTE, new PrimitiveValue( "#" + attributeValue ) );
        List<TypedObjectNode> childs = new ArrayList<TypedObjectNode>();
        childs.add( featureReference );
        return new GenericProperty( propType, null, null, attributes, childs );
    }

    private List<String> parseReferencedTextFeatureIds( FeatureCollection planToEdit ) {
        List<String> previouslyReferencedTextsFeatureIds = new ArrayList<>();
        for ( Feature feature : planToEdit ) {
            QName featureName = feature.getName();
            if ( isBPlan( featureName ) ) {
                QName propName = new QName( featureName.getNamespaceURI(), "texte" );
                List<Property> properties = feature.getProperties( propName );
                for ( Property property : properties ) {
                    PrimitiveValue hrefValue = property.getAttributes().get( XLINK_HREF_ATTRIBUTE );
                    if ( hrefValue != null )
                        previouslyReferencedTextsFeatureIds.add( hrefValue.toString().substring( 1 ) );
                }
            }
        }
        return previouslyReferencedTextsFeatureIds;
    }

    private void add( List<TypedObjectNode> subElementChilds, Property propertyToAdd ) {
        if ( propertyToAdd != null )
            subElementChilds.add( propertyToAdd );
    }

    private boolean addProperty( List<Property> properties, Property propertyToAdd ) {
        if ( propertyToAdd != null ) {
            properties.add( propertyToAdd );
            return true;
        }
        return false;
    }

    private void addOrReplaceProperty( XPlanVersion version, Feature feature, QName propName, Property property ) {
        removeProperties( feature, propName );
        if ( property != null ) {
            int index = findIndex( version, feature, propName );
            feature.getProperties().add( index, property );
        }
    }

    private void addOrReplaceProperties( XPlanVersion version, Feature feature, QName propName,
                                         List<Property> properties ) {
        removeProperties( feature, propName );
        if ( properties != null && !properties.isEmpty() ) {
            int index = findIndex( version, feature, propName );
            for ( Property property : properties ) {
                feature.getProperties().add( index, property );
            }
        }
    }

    private void removeProperties( Feature feature, QName propName ) {
        List<Property> properties = feature.getProperties( propName );
        feature.getProperties().removeAll( properties );
    }

    private int findIndex( XPlanVersion version, Feature feature, QName propNameToFindIndexFor ) {
        List<Property> properties = feature.getProperties();
        List<PropertyType> propertyTypes = feature.getType().getPropertyDeclarations();
        int index = calculateNumberOfPropertiesToSkip( properties, version );
        for ( ; index < properties.size(); index++ ) {
            Property actualProp = properties.get( index );
            boolean actualPropertyAfter = isActualPropertyAfterPropertyToFindIndexFor( propertyTypes, actualProp,
                                                                                       propNameToFindIndexFor );
            if ( actualPropertyAfter )
                return index;
        }
        return index;
    }

    private int findIndex( XPlanVersion version, List<TypedObjectNode> properties, QName propNameToFindIndexFor,
                           List<String> propertyNamesInOrder ) {
        int index = calculateNumberOfPropertiesToSkipOfTypedObjectNode( properties, version );
        for ( ; index < properties.size(); index++ ) {
            TypedObjectNode actualProp = properties.get( index );
            boolean actualPropertyAfter = isActualPropertyAfterPropertyToFindIndexFor( properties, actualProp,
                                                                                       propNameToFindIndexFor,
                                                                                       propertyNamesInOrder );
            if ( actualPropertyAfter )
                return index;
        }
        return index;
    }

    private QName getTextAbschnittName( XPlanVersion version, String namespaceUri ) {
        if ( XPLAN_50.equals( version ) )
            return new QName( namespaceUri, "BP_TextAbschnitt" );
        return new QName( namespaceUri, "XP_TextAbschnitt" );
    }

    private QName getExterneReferenzPropertyName( XPlanVersion version, String namespaceUri ) {
        if ( XPLAN_50.equals( version ) )
            return new QName( namespaceUri, "XP_SpezExterneReferenz" );
        return new QName( namespaceUri, "XP_ExterneReferenz" );
    }

    private String generateGmlId( QName propName ) {
        String prefix = "XPLAN_" + propName.getLocalPart() + "_";
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid;
    }

    private List<TypedObjectNode> asList( GenericXMLElement child ) {
        List<TypedObjectNode> childs = new ArrayList<TypedObjectNode>();
        childs.add( child );
        return childs;
    }

    private boolean isActualPropertyAfterPropertyToFindIndexFor( List<PropertyType> propertyTypes, Property actualProp,
                                                                 QName propNameToFindIndexFor ) {
        boolean foundActual = false;
        boolean foundPropToFindIndexFor = false;
        for ( PropertyType propertyType : propertyTypes ) {
            if ( propertyType.getName().equals( actualProp.getName() ) )
                foundActual = true;
            if ( propertyType.getName().equals( propNameToFindIndexFor ) )
                foundPropToFindIndexFor = true;
            if ( foundPropToFindIndexFor && !foundActual )
                return true;
        }
        return false;
    }

    private boolean isActualPropertyAfterPropertyToFindIndexFor( List<TypedObjectNode> propertyTypes,
                                                                 TypedObjectNode actualProp,
                                                                 QName propNameToFindIndexFor,
                                                                 List<String> properties ) {
        boolean foundActual = false;
        boolean foundPropToFindIndexFor = false;
        for ( String propertyType : properties ) {
            if ( actualProp instanceof GenericXMLElement ) {
                String actualPropName = ( (GenericXMLElement) actualProp ).getName().getLocalPart();
                if ( propertyType.equals( actualPropName ) )
                    foundActual = true;
                if ( propertyType.equals( propNameToFindIndexFor.getLocalPart() ) )
                    foundPropToFindIndexFor = true;
                if ( foundPropToFindIndexFor && !foundActual )
                    return true;
            }
        }
        return false;
    }

    private int calculateNumberOfPropertiesToSkip( List<Property> properties, XPlanVersion version ) {
        int numberOfPropertiesToSkip = 0;
        for ( Property property : properties ) {
            if ( property.getName().getNamespaceURI().equals( version.getGmlVersion().getNamespace() ) )
                numberOfPropertiesToSkip++;
        }
        return numberOfPropertiesToSkip;
    }

    private int calculateNumberOfPropertiesToSkipOfTypedObjectNode( List<TypedObjectNode> properties,
                                                                    XPlanVersion version ) {
        int numberOfPropertiesToSkip = 0;
        for ( TypedObjectNode property : properties ) {
            if ( property instanceof GenericXMLElement ) {
                GenericXMLElement prop = (GenericXMLElement) property;
                if ( prop.getName().getNamespaceURI().equals( version.getGmlVersion().getNamespace() ) )
                    numberOfPropertiesToSkip++;
            }
        }
        return numberOfPropertiesToSkip;
    }

    private boolean isBPlan( QName featureName ) {
        return "BP_Plan".equals( featureName.getLocalPart() );
    }

    private boolean isXPRasterplanBasis( QName featureName ) {
        return "XP_RasterplanBasis".equals( featureName.getLocalPart() );
    }

    private boolean isBPRasterplanAenderung( QName featureName ) {
        return "BP_RasterplanAenderung".equals( featureName.getLocalPart() );
    }

    private void checkVersionAndType( XPlanVersion version, XPlanType type ) {
        if ( !XPLAN_3.equals( version ) && !XPLAN_41.equals( version ) && !XPLAN_50.equals( version ) )
            throw new IllegalArgumentException( "Unsupported Version: " + version );
        if ( !XPlanType.BP_Plan.equals( type ) )
            throw new IllegalArgumentException( "Unsupported Plan, only BP_Plan is supported yet." );
    }

    private static Map<ReferenceType, String> initRefTypeToCode() {
        Map<ReferenceType, String> refTypeToCode = new HashMap<>();
        refTypeToCode.put( GREEN_STRUCTURES_PLAN, "2300" );
        refTypeToCode.put( LEGISLATION_PLAN, "1030" );
        refTypeToCode.put( REASON, "1010" );
        return refTypeToCode;
    }

    private static Map<String, RasterReferenceType> initRasterReferenceToPropName() {
        Map<String, RasterReferenceType> rasterReferenceToPropName = new HashMap<>();
        rasterReferenceToPropName.put( "refLegende", LEGEND );
        rasterReferenceToPropName.put( "refScan", SCAN );
        rasterReferenceToPropName.put( "refText", TEXT );
        return rasterReferenceToPropName;
    }

    private static List<String> initExternalPlanProperties() {
        List<String> externalPlanProperties = new ArrayList<String>();
        externalPlanProperties.add( "georefURL" );
        externalPlanProperties.add( "georefMimeType" );
        externalPlanProperties.add( "art" );
        externalPlanProperties.add( "informationssystemURL" );
        externalPlanProperties.add( "referenzName" );
        externalPlanProperties.add( "referenzURL" );
        externalPlanProperties.add( "referenzMimeType" );
        externalPlanProperties.add( "beschreibung" );
        externalPlanProperties.add( "datum" );
        return externalPlanProperties;
    }

}