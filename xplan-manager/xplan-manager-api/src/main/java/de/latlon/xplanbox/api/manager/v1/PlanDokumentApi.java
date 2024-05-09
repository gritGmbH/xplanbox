/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.exception.MissingRequestEntity;
import de.latlon.xplanbox.api.manager.handler.EditDokumentHandler;
import de.latlon.xplanbox.api.manager.v1.model.Dokument;
import de.latlon.xplanbox.api.manager.validation.ModelValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Controller class for handling access to the dokument resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/dokument")
public class PlanDokumentApi {

	@Autowired
	private EditDokumentHandler editDokumentHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getDokumente", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(
									array = @ArraySchema(schema = @Schema(implementation = Dokument.class)))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public List<Dokument> getDokumente(@PathParam("planId") @Parameter(
			description = "ID of the plan to return dokumente", example = "123") String planId) throws Exception {
		return editDokumentHandler.retrieveDokumente(planId);
	}

	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "addDokument", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Dokument.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan version or dokumentmodel is missing or planID is not a valid int value"),
			@ApiResponse(responseCode = "404",
					description = "Invalid planID or dokument ID, plan or dokument not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Dokument addDokument(
			@PathParam("planId") @Parameter(description = "ID of the plan to add a dokument",
					example = "123") String planId,
			@Parameter(schema = @Schema(implementation = Dokument.class),
					required = true) @Valid @ModelValidator(Dokument.class) @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
			@Parameter(schema = @Schema(type = "string", format = "binary")) @FormDataParam("datei") InputStream datei,
			@Parameter(hidden = true) @FormDataParam("datei") FormDataContentDisposition dateiMeta) throws Exception {
		if (dokumentmodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'dokumentmodel' is missing.");
		}
		Dokument dokument = dokumentmodel.getValueAs(Dokument.class);
		File file = editDokumentHandler.storeAsFile(datei, dateiMeta);
		return editDokumentHandler.addDokument(planId, dokument, file);
	}

	@GET
	@Path("/{id}")
	@Produces({ "application/json" })
	@Operation(operationId = "getDokumentById", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Dokument.class))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404",
							description = "Invalid planID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Dokument getDokumentById(
			@PathParam("planId") @Parameter(description = "ID of the plan to get dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the dokument to be returned (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id)
			throws Exception {
		return editDokumentHandler.retrieveDokument(planId, id);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceDokumentById", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Dokument.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan version or dokumentmodel is missing or planID is not a valid int value"),
			@ApiResponse(responseCode = "404",
					description = "Invalid planID or dokument ID, plan or dokument not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Dokument replaceDokumentById(
			@PathParam("planId") @Parameter(description = "ID of the plan to replace dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the dokument to be updated (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id,
			@Parameter(schema = @Schema(implementation = Dokument.class),
					required = true) @Valid @ModelValidator(Dokument.class) @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
			@Parameter(schema = @Schema(type = "string", format = "binary")) @FormDataParam("datei") InputStream datei,
			@Parameter(hidden = true) @FormDataParam("datei") FormDataContentDisposition dateiMeta) throws Exception {
		if (dokumentmodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'dokumentmodel' is missing.");
		}
		Dokument dokument = dokumentmodel.getValueAs(Dokument.class);
		File file = editDokumentHandler.storeAsFile(datei, dateiMeta);
		return editDokumentHandler.replaceDokument(planId, id, dokument, file);
	}

	@DELETE
	@Path("/{id}")
	@Produces({ "application/json" })
	@Operation(operationId = "deleteDokumentById", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Dokument.class))),
					@ApiResponse(responseCode = "404",
							description = "Invalid planID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Dokument deleteDokumentById(
			@PathParam("planId") @Parameter(description = "ID of the plan to delete dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the dokument to be deleted (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id)
			throws Exception {
		return editDokumentHandler.deleteDokument(planId, id);
	}

}
