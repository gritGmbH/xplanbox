/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.metadata.csw;

import de.latlon.xplan.manager.metadata.DataServiceCouplingException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CswClientTestManual {

	private static final String CSW_GET_CAPABILITIESURL = "https://metaver.de/csw?SERVICE=CSW&REQUEST=GetCapabilities";

	@Test
	public void testRequestMetadataRecord() throws DataServiceCouplingException {
		CswClient cswClient = new CswClient(CSW_GET_CAPABILITIESURL);
		PlanRecordMetadata planRecordMetadata = cswClient.requestMetadataRecord("Alsterdorf20");

		assertThat(planRecordMetadata.getRecordId(), is("CC9E9E0D-07AD-4C77-ADAB-AFDA37585633"));
		assertThat(planRecordMetadata.getResourceIdentifier(),
				is("https://registry.gdi-de.org/id/de.hh/1ad52a0e-9e8b-4a91-b4b3-cf60703cb91b"));
	}

	@Test
	public void testRequestMetadataRecord_Unknown() throws DataServiceCouplingException {
		CswClient cswClient = new CswClient(CSW_GET_CAPABILITIESURL);
		PlanRecordMetadata planRecordMetadata = cswClient.requestMetadataRecord("GIBT ES NICHT");

		assertThat(planRecordMetadata, is(CoreMatchers.nullValue()));
	}

	@Test
	public void testCreateGetRecordByIdRequest() throws DataServiceCouplingException {
		CswClient cswClient = new CswClient(CSW_GET_CAPABILITIESURL);
		String recordId = "CC9E9E0D-07AD-4C77-ADAB-AFDA37585633";
		String getRecordByIdRequest = cswClient.createGetRecordByIdRequest(recordId);

		assertThat(getRecordByIdRequest, containsString("ID=" + recordId));
	}

}
