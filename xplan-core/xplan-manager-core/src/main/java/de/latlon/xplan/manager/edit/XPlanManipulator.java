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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
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
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.RECHTSPLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;

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
        String previouslyReferencedRasterBasisFeatureId = parseReferencedRasterBasisFeatureId( planToEdit );

        List<Feature> featuresToAdd = new ArrayList<>();
        List<Feature> featuresToRemove = new ArrayList<>();
        List<String> referencesToRemove = new ArrayList<>();
        for ( Feature feature : planToEdit ) {
            QName featureName = feature.getName();
            if ( isBPlan( featureName ) )
                modifyBPlan( context, version, planToEdit, feature, planWithChanges, schema, featuresToAdd,
                             featuresToRemove, referencesToRemove, previouslyReferencedTextFeatureIds );
            if ( isBPBereich( featureName ) )
                modifyBPBereich( context, version, planToEdit, feature, planWithChanges, schema, featuresToAdd,
                                 featuresToRemove, referencesToRemove, previouslyReferencedRasterBasisFeatureId );
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
        if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) )
            modifyCode( version, feature, "verfahren", changes.getBaseData().getMethodCode() );
        modifyReferences( context, version, feature, changes, schema, featuresToAdd );
    }

    private void modifyBPBereich( GmlDocumentIdContext context, XPlanVersion version, FeatureCollection planToEdit,
                                  Feature feature, XPlanToEdit changes, AppSchema schema, List<Feature> featuresToAdd,
                                  List<Feature> featuresToRemove, List<String> referencesToRemove,
                                  String previouslyReferencedRasterBasisFeatureId ) {
        modifyRasterBasis( context, version, planToEdit, feature, schema, changes.getRasterBasis(), featuresToAdd,
                           featuresToRemove, referencesToRemove, previouslyReferencedRasterBasisFeatureId );
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
        List<Property> properties = new ArrayList<>();
        for ( Change change : changes ) {
            if ( changedType.equals( change.getType() ) ) {
                if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) ) {
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
            Feature oldTextFeature = null;
            if ( gmlid != null ) {
                oldTextFeature = detectFeatureById( planToEdit, gmlid );
                if ( oldTextFeature != null )
                    featuresToRemove.add( oldTextFeature );
            } else {
                gmlid = generateGmlId( propName );
            }
            Property linkProp = createPropertyWithHrefAttribute( context, feature.getType(), propName, gmlid );
            addProperty( properties, linkProp );
            createAndAddTextFeature( context, version, schema, namespaceUri, text, gmlid, featuresToAdd, oldTextFeature );
        }
        for ( String previouslyReferencedTextFeatureId : previouslyReferencedTextFeatureIds ) {
            if ( isNotLongerReferenced( texts, previouslyReferencedTextFeatureId ) ) {
                Feature oldTextFeature = detectFeatureById( planToEdit, previouslyReferencedTextFeatureId );
                if ( oldTextFeature != null ) {
                    featuresToRemove.add( oldTextFeature );
                    referencesToRemove.add( previouslyReferencedTextFeatureId );
                }
            }
        }
        addOrReplaceProperties( version, feature, propName, properties );
    }

    private void modifyRasterBasis( GmlDocumentIdContext context, XPlanVersion version, FeatureCollection planToEdit,
                                    Feature feature, AppSchema schema, RasterBasis rasterBasis,
                                    List<Feature> featuresToAdd, List<Feature> featuresToRemove,
                                    List<String> referencesToRemove, String previouslyReferencedRasterBasisFeatureId ) {
        String namespaceUri = feature.getName().getNamespaceURI();
        QName propName = new QName( namespaceUri, "rasterBasis" );

        List<Property> properties = new ArrayList<>();
        if ( rasterBasis == null ) {
            QName rasterBasisElementName = getRasterBasisElementName( version, namespaceUri );
            Feature oldRasterBasisFeature = detectFeatureById( planToEdit, rasterBasisElementName,
                                                               previouslyReferencedRasterBasisFeatureId );
            if ( oldRasterBasisFeature != null ) {
                featuresToRemove.add( oldRasterBasisFeature );
                referencesToRemove.add( previouslyReferencedRasterBasisFeatureId );
            }
            removeProperties( feature, new QName( namespaceUri, "refScan" ) );
        } else {
            String gmlid = rasterBasis.getFeatureId();
            if ( gmlid != null ) {
                QName rasterBasisElementName = getRasterBasisElementName( version, namespaceUri );
                Feature oldRasterBasisFeature = detectFeatureById( planToEdit, rasterBasisElementName, gmlid );
                if ( oldRasterBasisFeature != null )
                    featuresToRemove.add( oldRasterBasisFeature );
            } else {
                gmlid = generateGmlId( propName );
            }
            Property linkProp = createPropertyWithHrefAttribute( context, feature.getType(), propName, gmlid );
            addProperty( properties, linkProp );
            createAndAddRasterBasisFeature( context, version, planToEdit, schema, namespaceUri, rasterBasis, gmlid,
                                            featuresToAdd, featuresToRemove );
            addOrReplaceProperties( version, feature, propName, properties );
            removeProperties( feature, new QName( namespaceUri, "refScan" ) );
        }
    }

    private boolean isNotLongerReferenced( List<Text> texts, String previouslyReferencedTextFeatureId ) {
        for ( Text text : texts ) {
            if ( previouslyReferencedTextFeatureId.equals( text.getFeatureId() ) )
                return false;
        }
        return true;
    }

    private Feature detectFeatureById( FeatureCollection planToEdit, String featureId ) {
        return detectFeatureById( planToEdit, null, featureId );
    }

    private Feature detectFeatureById( FeatureCollection planToEdit, QName featureTypeName, String featureId ) {
        if ( featureId == null )
            return null;
        for ( Feature feature : planToEdit ) {
            if ( featureId.equals( feature.getId() ) && ( featureTypeName == null || featureTypeName.equals(
                                    feature.getName() ) ) )
                return feature;
        }
        return null;
    }

    private void modifyReferences( GmlDocumentIdContext context, XPlanVersion version, Feature feature,
                                   XPlanToEdit changes, AppSchema schema, List<Feature> featuresToAdd ) {
        if ( XPLAN_41.equals( version ) || XPLAN_3.equals( version ) ) {
            modifyReferences( context, version, feature, schema, "refRechtsplan", changes.getReferences(), RECHTSPLAN, featuresToAdd );
            modifyReferences( context, version, feature, schema, "refBegruendung", changes.getReferences(), BEGRUENDUNG,
                              featuresToAdd );
            if ( XPLAN_41.equals( version ) )
                modifyReferences( context, version, feature, schema, "refGruenordnungsplan", changes.getReferences(),
                                  GRUENORDNUNGSPLAN, featuresToAdd );
        } else if ( XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) ) {
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
            int spezExterneReferenzTyp = reference.getType().getSpezExterneReferenceType();
            Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, reference,
                                                              featureType, properties, propName,
                                                              "XP_SpezExterneReferenz",
                                                              Integer.toString( spezExterneReferenzTyp ) );
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
                                          String namespaceUri, Text text, String gmlid, List<Feature> featuresToAdd,
                                          Feature oldTextFeature ) {
        QName textFeatureTypeName = getTextAbschnittName( version, namespaceUri, oldTextFeature );
        FeatureType textFeatureType = schema.getFeatureType( textFeatureTypeName );
        List<Property> props = new ArrayList<Property>();
        addProperty( props, createKeyProperty( namespaceUri, text.getKey() ) );
        addProperty( props, createBasisProperty( namespaceUri, text.getBasis() ) );
        addProperty( props, createTextProperty( namespaceUri, text.getText() ) );

        QName refPropName = new QName( namespaceUri, "refText" );
        Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, text, textFeatureType,
                                                          props, refPropName, "XP_ExterneReferenz", null );

        if ( text.getRechtscharakter() != null && ( XPLAN_50.equals( version ) || XPLAN_51.equals( version )
                                                    || XPLAN_52.equals( version ) ) ) {
            addProperty( props, createRechtscharakterProperty( namespaceUri, text.getRechtscharakter().getCode() ) );
        }
        if ( props.isEmpty() )
            return;
        featuresToAdd.add( textFeatureType.newFeature( gmlid, props, null ) );
        if ( refFeature != null )
            featuresToAdd.add( refFeature );
    }

    private void createAndAddRasterBasisFeature( GmlDocumentIdContext context, XPlanVersion version,
                                                 FeatureCollection planToEdit, AppSchema schema, String namespaceUri,
                                                 RasterBasis rasterBasis, String gmlid, List<Feature> featuresToAdd,
                                                 List<Feature> featuresToRemove ) {
        QName rasterBasisFeatureTypeName = getRasterBasisElementName( version, namespaceUri );
        FeatureType rasterBasisFeatureType = schema.getFeatureType( rasterBasisFeatureTypeName );
        List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
        List<RasterReference> scans = collectRasterReferencesByType( rasterReferences, SCAN );
        List<RasterReference> texts = collectRasterReferencesByType( rasterReferences, TEXT );
        List<RasterReference> legends = collectRasterReferencesByType( rasterReferences, LEGEND );

        List<Property> props = new ArrayList<>();

        if ( !scans.isEmpty() ) {
            for ( RasterReference scan : scans ) {
                QName refPropName = new QName( namespaceUri, "refScan" );
                String externeReferenzFeatureTypeName = XPLAN_3.equals( version ) ?
                                                        "XP_ExterneReferenzPlan" :
                                                        "XP_ExterneReferenz";
                Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, scan,
                                                                  rasterBasisFeatureType, props, refPropName,
                                                                  externeReferenzFeatureTypeName, null );
                if ( scan.getFeatureId() != null ) {
                    QName externeReferenz = new QName( namespaceUri, externeReferenzFeatureTypeName );
                    Feature oldReferenceFeature = detectFeatureById( planToEdit, externeReferenz, scan.getFeatureId() );
                    featuresToRemove.add( oldReferenceFeature );
                }
                if ( refFeature != null ) {
                    featuresToAdd.add( refFeature );
                }
            }
        }
        if ( !texts.isEmpty() ) {
            QName refPropName = new QName( namespaceUri, "refText" );
            RasterReference text = texts.get( 0 );
            Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, text,
                                                              rasterBasisFeatureType, props, refPropName,
                                                              "XP_ExterneReferenz", null );
            if ( text.getFeatureId() != null ) {
                QName externeReferenz = new QName( namespaceUri, "XP_ExterneReferenz" );
                Feature oldReferenceFeature = detectFeatureById( planToEdit, externeReferenz, text.getFeatureId() );
                featuresToRemove.add( oldReferenceFeature );
            }
            if ( refFeature != null )
                featuresToAdd.add( refFeature );
        }

        if ( !legends.isEmpty() ) {
            for ( RasterReference legend : legends ) {
                QName refPropName = new QName( namespaceUri, "refLegende" );
                Feature refFeature = createAndAddExterneReferenz( context, version, schema, namespaceUri, legend,
                                                                  rasterBasisFeatureType, props, refPropName,
                                                                  "XP_ExterneReferenz", null );
                if ( legend.getFeatureId() != null ) {
                    QName externeReferenz = new QName( namespaceUri, "XP_ExterneReferenz" );
                    Feature oldReferenceFeature = detectFeatureById( planToEdit, externeReferenz,
                                                                     legend.getFeatureId() );
                    featuresToRemove.add( oldReferenceFeature );
                }
                if ( refFeature != null )
                    featuresToAdd.add( refFeature );
            }
        }
        if ( !props.isEmpty() )
            featuresToAdd.add( rasterBasisFeatureType.newFeature( gmlid, props, null ) );
    }

    private Feature createAndAddExterneReferenz( GmlDocumentIdContext context, XPlanVersion version, AppSchema schema,
                                                 String namespaceUri, AbstractReference reference,
                                                 FeatureType featureType, List<Property> props, QName refPropName,
                                                 String externeReferenzElementName, String spezExterneReferenzTyp ) {
        if ( XPLAN_41.equals( version ) || XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) ) {
            GenericProperty refProperty = createExterneReferenzProperty_XPlan41_XPlan50( schema, featureType,
                                                                                         refPropName, reference,
                                                                                         externeReferenzElementName,
                                                                                         spezExterneReferenzTyp );
            addProperty( props, refProperty );
        } else if ( XPLAN_3.equals( version ) ) {
            String refGmlid = generateGmlId( refPropName );
            Property refLinkProperty = createPropertyWithHrefAttribute( context, featureType, refPropName, refGmlid );
            boolean added = addProperty( props, refLinkProperty );
            if ( added ) {
                return createExterneReferenzFeature_XPlan3( schema, namespaceUri, reference, externeReferenzElementName,
                                                            refGmlid );
            }
        }
        return null;
    }

    private Feature createExterneReferenzFeature_XPlan3( AppSchema schema, String namespaceUri,
                                                         AbstractReference reference, String externeReferenzElementName,
                                                         String gmlid ) {
        QName refFeatureTypeName = new QName( namespaceUri, externeReferenzElementName );
        FeatureType refFeatureType = schema.getFeatureType( refFeatureTypeName );
        List<Property> props = new ArrayList<>();
        addProperty( props, createInformationssystemURLProperty( namespaceUri, reference.getInformationssystemURL() ) );
        addProperty( props, createReferenzNameProperty( namespaceUri, reference.getReferenzName() ) );
        addProperty( props, createReferenzProperty( namespaceUri, reference.getReference() ) );
        addProperty( props, createReferenzMimeTypeProperty( namespaceUri, reference.getReferenzMimeType() ) );
        if ( "XP_ExterneReferenzPlan".equals( externeReferenzElementName ) ) {
            addProperty( props, createGeoReferenzProperty( namespaceUri, reference.getGeoReference() ) );
            addProperty( props, createGeorefMimeTypeProperty( namespaceUri, reference.getGeorefMimeType() ) );
        }
        addProperty( props, createBeschreibungProperty( namespaceUri, reference.getBeschreibung() ) );
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
        add( subElementChilds, createGeorefMimeTypeProperty( namespaceUri, reference.getGeorefMimeType() ) );
        add( subElementChilds, createArtProperty( namespaceUri, reference.getArt() ) );
        add( subElementChilds,
             createInformationssystemURLProperty( namespaceUri, reference.getInformationssystemURL() ) );
        add( subElementChilds, createReferenzNameProperty( namespaceUri, reference.getReferenzName() ) );
        add( subElementChilds, createReferenzProperty( namespaceUri, reference.getReference() ) );
        add( subElementChilds, createReferenzMimeTypeProperty( namespaceUri, reference.getReferenzMimeType() ) );
        add( subElementChilds, createBeschreibungProperty( namespaceUri, reference.getBeschreibung() ) );
        add( subElementChilds, createDatumProperty( namespaceUri, reference.getDatum() ) );
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
        add( subElementChilds, createRechtscharakterProperty( namespaceUri, change.getLegalNatureCode() ) );
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

    private GenericProperty createRechtscharakterProperty( String namespaceUri, int value ) {
        QName propName = new QName( namespaceUri, "rechtscharakter" );
        return createProperty( propName, value, 1, 1 );
    }

    private GenericProperty createNumberProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "nummer" );
        return createProperty( propName, value, 1, 1 );
    }

    private GenericProperty createArtProperty( String namespaceUri, ExterneReferenzArt value ) {
        if ( value != null ) {
            QName propName = new QName( namespaceUri, "art" );
            return createProperty( propName, value.getCode(), 0, 1 );
        }
        return null;
    }

    private GenericProperty createInformationssystemURLProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "informationssystemURL" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createReferenzNameProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "referenzName" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createReferenzProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "referenzURL" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createReferenzMimeTypeProperty( String namespaceUri, MimeTypes value ) {
        if ( value != null ) {
            QName propName = new QName( namespaceUri, "referenzMimeType" );
            return createProperty( propName, value.getCode(), 0, 1 );
        }
        return null;
    }

    private GenericProperty createGeoReferenzProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "georefURL" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createGeorefMimeTypeProperty( String namespaceUri, MimeTypes value ) {
        if ( value != null ) {
            QName propName = new QName( namespaceUri, "georefMimeType" );
            return createProperty( propName, value.getCode(), 0, 1 );
        }
        return null;
    }

    private GenericProperty createBeschreibungProperty( String namespaceUri, String value ) {
        QName propName = new QName( namespaceUri, "beschreibung" );
        return createProperty( propName, value, 0, 1 );
    }

    private GenericProperty createDatumProperty( String namespaceUri, Date value ) {
        QName propName = new QName( namespaceUri, "datum" );
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

    private GenericProperty createProperty( QName propName, Date value, int minOccurs, int maxOccurs ) {
        if ( value == null || "".equals( value ) )
            return null;
        org.deegree.commons.tom.datetime.Date date = new org.deegree.commons.tom.datetime.Date( value, null );
        CustomPropertyType type = new CustomPropertyType( propName, minOccurs, maxOccurs, null, null );
        return new GenericProperty( type, new PrimitiveValue( date ) );
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

    private String parseReferencedRasterBasisFeatureId( FeatureCollection planToEdit ) {
        for ( Feature feature : planToEdit ) {
            QName featureName = feature.getName();
            if ( isBPBereich( featureName ) ) {
                QName propName = new QName( featureName.getNamespaceURI(), "rasterBasis" );
                List<Property> properties = feature.getProperties( propName );
                for ( Property property : properties ) {
                    PrimitiveValue hrefValue = property.getAttributes().get( XLINK_HREF_ATTRIBUTE );
                    if ( hrefValue != null )
                        return hrefValue.toString().substring( 1 );
                }
            }
        }
        return null;
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

    private QName getTextAbschnittName( XPlanVersion version, String namespaceUri, Feature oldTextFeature ) {
        if ( oldTextFeature != null )
            return oldTextFeature.getName();
        if ( XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) )
            return new QName( namespaceUri, "BP_TextAbschnitt" );
        return new QName( namespaceUri, "XP_TextAbschnitt" );
    }

    private QName getRasterBasisElementName( XPlanVersion version, String namespaceUri ) {
        if ( XPLAN_50.equals( version ) || XPLAN_51.equals( version ) || XPLAN_52.equals( version ) || XPLAN_52.equals( version ) )
            return new QName( namespaceUri, "XP_Rasterdarstellung" );
        return new QName( namespaceUri, "XP_RasterplanBasis" );
    }

    private String generateGmlId( QName propName ) {
        String prefix = "XPLAN_" + propName.getLocalPart() + "_";
        String uuid = UUID.randomUUID().toString();
        return prefix + uuid;
    }

    private List<TypedObjectNode> asList( GenericXMLElement child ) {
        List<TypedObjectNode> childs = new ArrayList<>();
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

    private boolean isBPBereich( QName featureName ) {
        return "BP_Bereich".equals( featureName.getLocalPart() );
    }

    private List<RasterReference> collectRasterReferencesByType( List<RasterReference> rasterReferences,
                                                                 RasterReferenceType type ) {
        return rasterReferences.stream().filter( rasterReference -> type.equals( rasterReference.getType() ) ).collect(
                        Collectors.toList() );
    }

    private void checkVersionAndType( XPlanVersion version, XPlanType type ) {
        if ( !XPLAN_3.equals( version ) && !XPLAN_41.equals( version ) && !XPLAN_50.equals( version )
             && !XPLAN_51.equals( version ) && !XPLAN_52.equals( version ) )
            throw new IllegalArgumentException( "Unsupported Version: " + version );
        if ( !XPlanType.BP_Plan.equals( type ) )
            throw new IllegalArgumentException( "Unsupported Plan, only BP_Plan is supported yet." );
    }

}
