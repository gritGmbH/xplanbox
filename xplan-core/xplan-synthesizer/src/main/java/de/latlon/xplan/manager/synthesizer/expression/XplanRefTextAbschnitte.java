//$HeadURL: svn+ssh://goerke@criador/srv/svn/lkee_xplanung2/trunk/src/de/latlon/
// lkee_xplanung2/rules/expressions/XPlanRefTextAbschnitte.java $
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2009 by:
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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.expression.flatten.XpExterneReferenzFlattener;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.List;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

/**
 * Returns a textual representation of the "XP_TextAbschnitt" features that use external references and are referenced
 * via the "texte" property of an "XP_Plan" feature.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 *
 * @since 1.0
 */
public class XplanRefTextAbschnitte implements Expression {

    private static final Logger LOG = LoggerFactory.getLogger( XplanRefTextAbschnitte.class );

	@Override
	public PrimitiveValue evaluate(Feature feature) {
		StringBuilder s = new StringBuilder();
		QName textePropName = new QName(feature.getName().getNamespaceURI(), "texte");
		List<Property> props = feature.getProperties(textePropName);
		for (Property prop : props) {
			FeatureReference ref = (FeatureReference) prop.getValue();
			if (ref != null) {
				append(s, ref.getReferencedObject());
			}
		}
        if ( s.toString().isEmpty() )
            return null;
        return new PrimitiveValue( s.toString() );
	}

	private void append(StringBuilder s, Feature textAbschnitt) {
		QName propName = new QName(textAbschnitt.getName().getNamespaceURI(), "refText");
		TypedObjectNode refTextValue = getPropertyValue(textAbschnitt, propName);
		if (refTextValue != null) {
			if (refTextValue instanceof FeatureReference) {
				Feature extRef = ((FeatureReference) refTextValue).getReferencedObject();
				s.append( new XpExterneReferenzFlattener(extRef).flatten(extRef));
			} else if (refTextValue instanceof ElementNode) {
				ElementNode extRef = (ElementNode) refTextValue;
				s.append( new XpExterneReferenzFlattener(textAbschnitt).flatten(extRef));
            } else {
                LOG.warn( "Creating a String value from class {} is not supported.", refTextValue.getClass() );
			}
		}
	}
}
