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
import de.latlon.xplanbox.api.manager.handler.EditRasterbasisHandler;
import de.latlon.xplanbox.api.manager.v1.model.Rasterbasis;
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
 * Controller class for handling access to the rasterbasis resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/rasterbasis")
public class PlanRasterbasisApi {

	@Autowired
	private EditRasterbasisHandler editRasterbasisHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getRasterBasis", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(
									array = @ArraySchema(schema = @Schema(implementation = Rasterbasis.class)))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public List<Rasterbasis> getRasterBasis(@PathParam("planId") @Parameter(
			description = "ID of the plan to be returned", example = "123") String planId) throws Exception {
		return editRasterbasisHandler.retrieveRasterbasis(planId);
	}

	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "addRasterBasis", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan type or version, missing bereich nummer or rasterbasismodel or planID is not a valid int value"),
			@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Rasterbasis addRasterBasis(
			@PathParam("planId") @Parameter(description = "ID of the plan to add rasterbasis",
					example = "123") String planId,
			@Parameter(schema = @Schema(implementation = Rasterbasis.class),
					required = true) @Valid @ModelValidator(Rasterbasis.class) @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
			@Parameter(schema = @Schema(type = "string",
					format = "binary")) @FormDataParam("rasterdatei") InputStream rasterdatei,
			@Parameter(hidden = true) @FormDataParam("rasterdatei") FormDataContentDisposition rasterdateiMeta,
			@Parameter(schema = @Schema(type = "string",
					format = "binary")) @FormDataParam("georeferenzdatei") InputStream georeferenzdatei,
			@Parameter(
					hidden = true) @FormDataParam("georeferenzdatei") FormDataContentDisposition georeferenzdateiMeta)
			throws Exception {
		if (rasterbasismodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'rasterbasismodel' is missing.");
		}
		Rasterbasis rasterbasis = rasterbasismodel.getValueAs(Rasterbasis.class);
		File rasterfile = editRasterbasisHandler.storeAsFile(rasterdatei, rasterdateiMeta);
		File georeferenzfile = editRasterbasisHandler.storeAsFile(georeferenzdatei, georeferenzdateiMeta);
		return editRasterbasisHandler.addRasterbasis(planId, rasterbasis, rasterfile, georeferenzfile);
	}

	@GET
	@Path("/{id}")
	@Produces({ "application/json" })
	@Operation(operationId = "getRasterbasisById", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404",
							description = "Invalid planID or rasterbasis ID, plan or rasterbasis not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Rasterbasis getRasterbasisById(
			@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the rasterbasis to be returned (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed",
					example = "Referenz123-") String id)
			throws Exception {
		return editRasterbasisHandler.retrieveRasterbasis(planId, id);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceRasterbasisById", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan type or version, missing bereich nummer or rasterbasismodel or planID is not a valid int value"),
			@ApiResponse(responseCode = "404",
					description = "Invalid planID or rasterbasis ID, plan or rasterbasis not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Rasterbasis replaceRasterbasisById(
			@PathParam("planId") @Parameter(description = "ID of the plan to be updated",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the rasterbasis to be updated (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed",
					example = "Referenz123-") String id,
			@Parameter(schema = @Schema(implementation = Rasterbasis.class),
					required = true) @Valid @ModelValidator(Rasterbasis.class) @FormDataParam("rasterbasismodel") FormDataBodyPart rasterbasismodel,
			@Parameter(schema = @Schema(type = "string",
					format = "binary")) @FormDataParam("rasterdatei") InputStream rasterdatei,
			@Parameter(hidden = true) @FormDataParam("rasterdatei") FormDataContentDisposition rasterdateiMeta,
			@Parameter(schema = @Schema(type = "string",
					format = "binary")) @FormDataParam("georeferenzdatei") InputStream georeferenzdatei,
			@Parameter(
					hidden = true) @FormDataParam("georeferenzdatei") FormDataContentDisposition georeferenzdateiMeta)
			throws Exception {
		if (rasterbasismodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'rasterbasismodel' is missing.");
		}
		Rasterbasis rasterbasis = rasterbasismodel.getValueAs(Rasterbasis.class);
		File rasterfile = editRasterbasisHandler.storeAsFile(rasterdatei, rasterdateiMeta);
		File georeferenzfile = editRasterbasisHandler.storeAsFile(georeferenzdatei, georeferenzdateiMeta);
		return editRasterbasisHandler.replaceRasterbasis(planId, id, rasterbasis, rasterfile, georeferenzfile);
	}

	@DELETE
	@Path("/{id}")
	@Produces({ "application/json" })
	@Operation(operationId = "deleteRasterbasisById", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Rasterbasis.class))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404",
							description = "Invalid planID or rasterbasis ID, plan or rasterbasis not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Rasterbasis deleteRasterbasisById(
			@PathParam("planId") @Parameter(description = "ID of the plan to be deleted",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "ID of the rasterbasis to be deleted (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed",
					example = "Referenz123-") String id)
			throws Exception {
		return editRasterbasisHandler.deleteRasterbasis(planId, id);
	}

}
