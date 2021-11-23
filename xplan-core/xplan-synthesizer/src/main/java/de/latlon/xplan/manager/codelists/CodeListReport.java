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
package de.latlon.xplan.manager.codelists;

import static org.apache.xerces.xs.XSConstants.TYPE_DEFINITION;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.xerces.xs.StringList;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.deegree.feature.types.AppSchema;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanVersion;

class CodeListReport {

	public static void main(String[] args)
			throws ClassCastException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		// System.out.print( "Lese XPlan 2 schema..." );
		// appSchema = XPlanSchemas.getAppSchema( XPLAN_2 );
		// schemas.add( appSchema );
		// System.out.println( "ok: " + appSchema.getFeatureTypes( null, false, false
		// ).size() + " feature types." );
		//
		// System.out.print( "Lese XPlan 3 schema..." );
		// appSchema = XPlanSchemas.getAppSchema( XPLAN_3 );
		// schemas.add( appSchema );
		// System.out.println( "ok: " + appSchema.getFeatureTypes( null, false, false
		// ).size() + " feature types." );
		//
		// System.out.print( "Lese XPlan 4.0 schema..." );
		// appSchema = XPlanSchemas.getAppSchema( XPLAN_40 );
		// schemas.add( appSchema );
		// System.out.println( "ok: " + appSchema.getFeatureTypes( null, false, false
		// ).size() + " feature types." );

		CodeListReport report = new CodeListReport();
		report.printReport(XPlanVersion.XPLAN_3);
	}

	private void printReport(XPlanVersion version) {
		System.out.print("Lese Schema...");
		AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema(version, null);
		System.out.println("OK");

		XSModel xsModel = appSchema.getGMLSchema().getXSModel();
		XSNamedMap elementDecls = xsModel.getComponentsByNamespace(TYPE_DEFINITION, version.getNamespace());
		@SuppressWarnings("unchecked")
		List<QName> orderedNames = new ArrayList<QName>(elementDecls.keySet());
		Collections.sort(orderedNames, new Comparator<QName>() {
			@Override
			public int compare(QName o1, QName o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		for (QName name : orderedNames) {
			XSTypeDefinition typeDef = (XSTypeDefinition) elementDecls.get(name);
			if (typeDef instanceof XSSimpleTypeDefinition) {
				print((XSSimpleTypeDefinition) typeDef);
			}
		}
	}

	private void print(XSSimpleTypeDefinition typeDef) {
		System.out.println(typeDef.getName());
		StringList enumeration = typeDef.getLexicalEnumeration();
		for (int i = 0; i < enumeration.getLength(); i++) {
			String value = (String) enumeration.get(i);
			System.out.println(value);
		}
	}

}
