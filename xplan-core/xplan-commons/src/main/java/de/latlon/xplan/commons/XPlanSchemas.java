/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.deegree.feature.types.AppSchema;
import org.deegree.gml.schema.GMLAppSchemaReader;

public class XPlanSchemas {

	private static XPlanSchemas INSTANCE = new XPlanSchemas();

	private XPlanSchemas() {
	}

	public static XPlanSchemas getInstance() {
		return INSTANCE;
	}

	private Map<VersionWithAde, AppSchema> xplanVersionToSchema = new HashMap<VersionWithAde, AppSchema>();

	public synchronized AppSchema getAppSchema(XPlanVersion version, XPlanAde ade) {
		VersionWithAde versionWithAde = new VersionWithAde(version, ade);
		AppSchema schema = xplanVersionToSchema.get(versionWithAde);
		if (schema == null) {
			URL gmlSchemaUrl;
			if (ade != null)
				gmlSchemaUrl = ade.getSchemaUrl();
			else
				gmlSchemaUrl = version.getSchemaUrl();
			GMLAppSchemaReader appSchemaReader;
			try {
				appSchemaReader = new GMLAppSchemaReader(null, null, gmlSchemaUrl.toString());
			}
			catch (Exception e) {
				throw new RuntimeException("Fehler beim Lesen des XPlan GML-Schemas: " + e.getMessage());
			}
			schema = appSchemaReader.extractAppSchema();
			xplanVersionToSchema.put(versionWithAde, schema);
		}
		return schema;
	}

	private class VersionWithAde {

		private final XPlanVersion version;

		private final XPlanAde ade;

		public VersionWithAde(XPlanVersion version, XPlanAde ade) {
			this.version = version;
			this.ade = ade;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			VersionWithAde that = (VersionWithAde) o;

			if (ade != that.ade) {
				return false;
			}
			if (version != that.version) {
				return false;
			}

			return true;
		}

		@Override
		public int hashCode() {
			int result = version.hashCode();
			result = 31 * result + (ade != null ? ade.hashCode() : 0);
			return result;
		}

	}

}
