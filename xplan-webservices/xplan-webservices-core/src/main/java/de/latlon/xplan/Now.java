/*-
 * #%L
 * xplan-webservices-core - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
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
 * @version $Revision: $, $Date: $
 */
public class Now implements FunctionProvider {

	static final String NAME = "Now";

	static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<ParameterType> getArgs() {
		return Collections.emptyList();
	}

	@Override
	public Function create(List<Expression> params) {
		return new Function(NAME, params) {
			@Override
			public TypedObjectNode[] evaluate(List<TypedObjectNode[]> args) throws FilterEvaluationException {
				Date now = new Date();
				TypedObjectNode date = new PrimitiveValue(DATE_FORMAT.format(now), new PrimitiveType(DATE_TIME));
				return new TypedObjectNode[] { date };
			}
		};
	}

	@Override
	public ParameterType getReturnType() {
		return ANYTYPE;
	}

	@Override
	public void init(Workspace arg0) throws ResourceInitException {
		// nothing to do
	}

	@Override
	public void destroy() {
		// nothing to do
	}

}
