/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.admin.evaluation;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class UpdateUtils {

	/**
	 * @param planstatus the status of the plan, may be null <code>null</code>
	 * @return the syn database schema where plans with the passed planstatus are stored,
	 * <code>null</code> if the passsed planstatus is <code>null</code>
	 * @throws IllegalArgumentException if the planstatus is not uspported
	 */
	public static String detectSynSchemaByPlanStatus(String planstatus) {
		if (planstatus == null)
			return null;
		switch (planstatus) {
			case "In Aufstellung":
				return "xplansynpre";
			case "Festgestellt":
				return "xplansyn";
			case "Archiviert":
				return "xplansynarchive";
		}
		throw new IllegalArgumentException("Unsupported planstatus: " + planstatus);
	}

	/**
	 * @param version the version of the plan, never <code>null</code>
	 * @return the blob database schema plans with the passed version are stored, never
	 * <code>null</code>
	 * @throws IllegalArgumentException if the version is not supported
	 */
	public static String detectBlobSchemaByVersion(String version) {
		switch (version) {
			case "XPLAN_40":
				return "xplan40";
			case "XPLAN_41":
				return "xplan41";
			case "XPLAN_50":
				return "xplan50";
			case "XPLAN_51":
				return "xplan51";
			case "XPLAN_52":
				return "xplan52";
			case "XPLAN_53":
				return "xplan53";
			case "XPLAN_54":
				return "xplan54";
			case "XPLAN_60":
				return "xplan60";
		}
		throw new IllegalArgumentException("Unsupported version: " + version);
	}

	/**
	 * @param columnname the name of the geometry column, never <code>null</code>
	 * @return the XPath expression to select the geometry, never <code>null</code>
	 * @throws IllegalArgumentException if the columnname is not uspported
	 */
	public static String detectXPath(String columnname) {
		switch (columnname) {
			case "xplan_position":
				return "//xplan:position/*";
			case "xplan_geltungsbereich":
				return "//xplan:geltungsbereich/*";
			case "xplan_raeumlichergeltungsbereich":
				return "//xplan:raeumlicherGeltungsbereich/*";
			case "xplan_symbolposition":
				return "//xplan:symbolPosition/*";
			case "xplan_geltungsbereichaenderung":
				return "//xplan:geltungsbereichAenderung/*";
		}
		throw new IllegalArgumentException("Unsupported column: " + columnname);
	}

	/**
	 * @param schemaname the name of the blob database schema, never <code>null</code>
	 * @return the namespace of the XPlanGML stored in this database schema, never
	 * <code>null</code>
	 * @throws IllegalArgumentException if the schemaname is not uspported
	 */
	public static String detectNamespace(String schemaname) {
		switch (schemaname) {
			case "xplan40":
				return "http://www.xplanung.de/xplangml/4";
			case "xplan41":
				return "http://www.xplanung.de/xplangml/4/1";
			case "xplan50":
				return "http://www.xplanung.de/xplangml/5";
			case "xplan51":
				return "http://www.xplanung.de/xplangml/5/1";
			case "xplan52":
				return "http://www.xplanung.de/xplangml/5/2";
			case "xplan53":
				return "http://www.xplanung.de/xplangml/5/3";
			case "xplan54":
				return "http://www.xplanung.de/xplangml/5/4";
			case "xplan60":
				return "http://www.xplanung.de/xplangml/6/0";
		}
		throw new IllegalArgumentException("Unsupported schemaname: " + schemaname);
	}

}
