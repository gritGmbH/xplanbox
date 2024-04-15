/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumenteAPI
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

import de.latlon.xplanbox.api.dokumente.handler.DocumentHandler;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Controller class for handling access to the validate resource.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @since 7.0
 */
@Path("/dokument")
public class DokumentApi {

	@Autowired
	private DocumentHandler documentHandler;

	@GET
	@Path("/{planId}")
	@Produces(APPLICATION_JSON)
	@Operation(summary = "List documents", description = "List all documents of the plan", tags = { "download" },
			responses = { @ApiResponse(responseCode = "200", description = "Documents"),
					@ApiResponse(responseCode = "400", description = "Invalid input"),
					@ApiResponse(responseCode = "404", description = "Invalid planID") })
	public Response listDocuments(@PathParam("planId") @Parameter(description = "ID of the plan to return document",
			example = "123") String planId) throws Exception {
		List<Document> documents = documentHandler.listDocuments(planId);
		return Response.ok(documents).build();
	}

	@HEAD
	@Path("/{planId}/{fileName}")
	@Operation(summary = "Show download header", description = "Download a document", tags = { "download" },
			responses = { @ApiResponse(responseCode = "200", description = "Document"),
					@ApiResponse(responseCode = "400", description = "Invalid input"), @ApiResponse(
							responseCode = "404", description = "Invalid planID or fileName, document not found") })
	public Response headDocument(
			@PathParam("planId") @Parameter(description = "ID of the plan to return document",
					example = "123") String planId,
			@PathParam("fileName") @Parameter(description = "Name of the document", example = "123") String fileName)
			throws Exception {
		DocumentHeader document = documentHandler.headDocument(planId, fileName);
		return Response.ok()
			.header("Content-Length", document.getFileSize())
			.header("Content-Type", document.getMediaType())
			.build();
	}

	@GET
	@Path("/{planId}/{fileName}")
	@Operation(summary = "Download document", description = "Download a document", tags = { "download" }, responses = {
			@ApiResponse(responseCode = "200", description = "Document"),
			@ApiResponse(responseCode = "400", description = "Invalid input"),
			@ApiResponse(responseCode = "404", description = "Invalid planID or fileName, document not found") })
	public Response getDocument(
			@PathParam("planId") @Parameter(description = "ID of the plan to return document",
					example = "123") String planId,
			@PathParam("fileName") @Parameter(description = "Name of the document", example = "123") String fileName)
			throws Exception {
		DocumentHeaderWithStream document = documentHandler.getDocument(planId, fileName);
		return Response.ok(document.getStreamingOutput())
			.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
			.header("Content-Length", document.getFileSize())
			.header("Content-Type", document.getMediaType())
			.build();
	}

}
