/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

/**
 * {@link Expression} for translating codes from external codelists to their textual representation.
 * 
 * TODO actually take dictionaries into account that are referenced by the main document
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XplanCodeLookupExt implements Expression {

    private final Expression exp;

    private final String codeList;

    public XplanCodeLookupExt( Expression exp, String codeList ) {
        this.exp = exp;
        this.codeList = codeList;
    }

    @Override
    public PrimitiveValue evaluate( Feature feature, FeatureCollection features ) {
        String descriptions = null;
        TypedObjectNodeArray<TypedObjectNode> props = Expressions.castToArray( exp.evaluate( feature, features ) );
        if ( props != null ) {
            for ( TypedObjectNode node : props.getElements() ) {
                if ( node instanceof Property ) {
                    Property prop = (Property) node;
                    TypedObjectNode value = prop.getValue();
                    String desc = toString( value );
                    if ( desc != null && !desc.isEmpty() ) {
                        if ( descriptions == null ) {
                            descriptions = desc;
                        } else {
                            descriptions += ";" + desc;
                        }
                    }
                }
            }
        }
        return toPrimitiveValue( descriptions );
    }

    private String toString( TypedObjectNode value ) {
        if ( value == null ) {
            return null;
        }
        if ( value instanceof ElementNode ) {
            ElementNode el = (ElementNode) value;
            String s = "";
            PrimitiveValue codeSpace = el.getAttributes().get( new QName( "codeSpace" ) );
            if ( codeSpace != null ) {
                s = "{" + codeSpace + "}";
            }
            for ( TypedObjectNode child : el.getChildren() ) {
                s += child;
            }
            return s;
        }
        return value.toString();
    }
}
