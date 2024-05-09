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
public class CrsUtils {

	public static ICRS determineActiveCrs(ICRS defaultCRS, XPlanArchive archive, Logger log) throws Exception {
		log.info("- Überprüfung des räumlichen Bezugssystems...");
		return determineCrs(defaultCRS, archive, log);
	}

	private static ICRS determineCrs(ICRS defaultCRS, XPlanArchive archive, Logger log) throws Exception {
		ICRS crs = defaultCRS;
		if (archive.getCrs() != null) {
			crs = archive.getCrs();
			try {
				// called to force an UnknownCRSException
				CRSManager.lookup(crs.getName());
				log.info("OK, " + crs.getAlias());
				crs = archive.getCrs();
			}
			catch (UnknownCRSException e) {
				if (defaultCRS != null) {
					log.info("OK");
					log.info("Das im Dokument verwendete CRS '" + archive.getCrs().getName()
							+ "' ist unbekannt. Verwende benutzerspezifiziertes CRS '" + crs.getName() + "'.");
				}
				else {
					throw new Exception("Fehler: Das im Dokument verwendete CRS '" + archive.getCrs().getName()
							+ "' ist unbekannt. Hinweis: Sie können das CRS als weiteren "
							+ "Kommandozeilen-Parameter übergeben.");
				}
			}
		}
		else {
			if (defaultCRS == null) {
				throw new Exception(
						"Fehler: Das Dokument enthält keine CRS-Informationen. Hinweis: Sie können das CRS als weiteren "
								+ "Kommandozeilen-Parameter übergeben.");
			}
			else {
				log.info("OK. Keine CRS-Informationen, verwende " + defaultCRS.getName());
			}
		}
		return crs;
	}

}
