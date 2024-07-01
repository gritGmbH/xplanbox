/*-
 * #%L
 * xplan-webservices-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.services.synwfs;

import de.latlon.xplan.core.test.WarStructureVerifier;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 8.0
 */
class XPlanServicesSynWfsWarStructureIT extends WarStructureVerifier {

	@Test
	void verifyLibsInWarFile() throws Exception {
		verifyLibsInWarFile("xplan-services-wfs-syn-.*\\.war");
	}

}
