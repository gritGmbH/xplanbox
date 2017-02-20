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
package de.latlon.xplan;

import static org.deegree.commons.tom.primitive.BaseType.DATE_TIME;
import static org.deegree.filter.function.ParameterType.ANYTYPE;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveType;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.filter.Expression;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.expression.Function;
import org.deegree.filter.function.FunctionProvider;
import org.deegree.filter.function.ParameterType;
import org.deegree.workspace.ResourceInitException;
import org.deegree.workspace.Workspace;

/**
 * Provides a simple function retuning the current date and time.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class Now implements FunctionProvider {

    static final String NAME = "Now";

    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss.SSS" );

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public List<ParameterType> getArgs() {
        return Collections.emptyList();
    }

    @Override
    public Function create( List<Expression> params ) {
        return new Function( NAME, params ) {
            @Override
            public TypedObjectNode[] evaluate( List<TypedObjectNode[]> args )
                            throws FilterEvaluationException {
                Date now = new Date();
                TypedObjectNode date = new PrimitiveValue( DATE_FORMAT.format( now ), new PrimitiveType( DATE_TIME ) );
                return new TypedObjectNode[] { date };
            }
        };
    }

    @Override
    public ParameterType getReturnType() {
        return ANYTYPE;
    }

    @Override
    public void init( Workspace arg0 )
                    throws ResourceInitException {
        // nothing to do
    }

    @Override
    public void destroy() {
        // nothing to do
    }

}