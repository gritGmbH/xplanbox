/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager;

import de.latlon.xplan.commons.archive.XPlanArchive;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public final class CrsUtils {

	private CrsUtils() {
	}

	public static ICRS determineActiveCrs(XPlanArchive archive, Logger log) throws Exception {
		log.info("- Überprüfung des räumlichen Bezugssystems...");
		ICRS archiveCrs = archive.getCrs();
		if (archiveCrs != null) {
			try {
				// called to force an UnknownCRSException
				CRSManager.lookup(archiveCrs.getName());
				log.info("OK, " + archiveCrs.getAlias());
				return archiveCrs;
			}
			catch (UnknownCRSException e) {
				throw new Exception(
						"Fehler: Das im Dokument verwendete CRS '" + archiveCrs.getName() + "' ist unbekannt.");
			}
		}
		else {
			throw new Exception("Fehler: Das Dokument enthält keine CRS-Informationen.");

		}
	}

}
