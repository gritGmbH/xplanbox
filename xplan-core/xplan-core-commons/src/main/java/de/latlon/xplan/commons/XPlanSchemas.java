/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.commons;

import org.deegree.feature.types.AppSchema;
import org.deegree.gml.schema.GMLAppSchemaReader;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanSchemas {

	private static XPlanSchemas INSTANCE = new XPlanSchemas();

	private XPlanSchemas() {
	}

	public static XPlanSchemas getInstance() {
		return INSTANCE;
	}

	private Map<XPlanVersion, AppSchema> xplanVersionToSchema = new HashMap<>();

	public synchronized AppSchema getAppSchema(XPlanVersion version) {
		AppSchema schema = xplanVersionToSchema.get(version);
		if (schema == null) {
			URL gmlSchemaUrl = version.getSchemaUrl();
			GMLAppSchemaReader appSchemaReader;
			try {
				appSchemaReader = new GMLAppSchemaReader(null, null, gmlSchemaUrl.toString());
			}
			catch (Exception e) {
				throw new RuntimeException("Fehler beim Lesen des XPlan GML-Schemas: " + e.getMessage());
			}
			schema = appSchemaReader.extractAppSchema();
			xplanVersionToSchema.put(version, schema);
		}
		return schema;
	}

}
