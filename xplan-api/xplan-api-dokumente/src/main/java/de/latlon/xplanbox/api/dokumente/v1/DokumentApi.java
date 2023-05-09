/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.v1;

import de.latlon.xplanbox.api.dokumente.handler.DokumentHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Controller class for handling access to the validate resource.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 6.1
 */
@Path("/dokument")
public class DokumentApi {

	@Autowired
	private DokumentHandler dokumentHandler;

	@GET
	@Path("/{planId}/{fileName}")
	@Operation(summary = "Download document", description = "Download a document", tags = { "dokument" }, responses = {
			@ApiResponse(responseCode = "200", description = "Dokument"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "404", description = "Invalid planID or fileName, dokument not found") })
	public Response dokument(
			@PathParam("planId") @Parameter(description = "ID of the plan to return document",
					example = "123") String planId,
			@PathParam("fileName") @Parameter(description = "Name of the document", example = "123") String fileName) {
		return Response.ok().entity("ok").build();
	}

}
