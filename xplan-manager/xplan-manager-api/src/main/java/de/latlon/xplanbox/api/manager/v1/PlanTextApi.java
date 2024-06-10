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
import de.latlon.xplanbox.api.manager.handler.EditTextHandler;
import de.latlon.xplanbox.api.manager.v1.model.Text;
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

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Controller class for handling access to the text resource of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 4.4
 */
@Path("/plan/{planId}/text")
public class PlanTextApi {

	@Autowired
	private EditTextHandler editTextHandler;

	@GET
	@Produces({ "application/json" })
	@Operation(operationId = "getTexte", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(array = @ArraySchema(schema = @Schema(implementation = Text.class)))),
					@ApiResponse(responseCode = "400",
							description = "Unsupported plan version or planID is not a valid int value"),
					@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
					@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public List<Text> getTexte(@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
			example = "123") String planId) throws Exception {
		return editTextHandler.retrieveTexte(planId);
	}

	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "addText", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Text.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan version or textmodel is missing or planID is not a valid int value"),
			@ApiResponse(responseCode = "404", description = "Invalid planID, plan not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Text addText(
			@PathParam("planId") @Parameter(description = "ID of the plan to add text", example = "123") String planId,
			@Parameter(schema = @Schema(implementation = Text.class),
					required = true) @Valid @ModelValidator(Text.class) @FormDataParam("textmodel") FormDataBodyPart textmodel,
			@Parameter(schema = @Schema(type = "string", format = "binary")) @FormDataParam("datei") InputStream datei,
			@Parameter(hidden = true) @FormDataParam("datei") FormDataContentDisposition dateiMeta) throws Exception {
		if (textmodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'textmodel' is missing.");
		}
		Text text = textmodel.getValueAs(Text.class);
		File file = editTextHandler.storeAsFile(datei, dateiMeta);
		return editTextHandler.addText(planId, text, file);
	}

	@GET
	@Path("/{id}")
	@Produces({ "application/json" })
	@Operation(operationId = "getTextById", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Text.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan version or planID is not a valid int value"),
			@ApiResponse(responseCode = "404", description = "Invalid planID or text ID, plan or text not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available") })
	public Text getTextById(
			@PathParam("planId") @Parameter(description = "ID of the plan to be returned",
					example = "123") String planId,
			@PathParam("id") @Parameter(description = "ID of the text to be returned (GML-Id of the feature)",
					example = "GML_ID_123") String id)
			throws Exception {
		return editTextHandler.retrieveText(planId, id);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceTextById", tags = { "edit" }, responses = {
			@ApiResponse(responseCode = "200", description = "successful operation",
					content = @Content(schema = @Schema(implementation = Text.class))),
			@ApiResponse(responseCode = "400",
					description = "Unsupported plan version or textmodel is missing or planID is not a valid int value"),
			@ApiResponse(responseCode = "404", description = "Invalid planID or text ID, plan or Text not found"),
			@ApiResponse(responseCode = "406", description = "Requested format is not available"),
			@ApiResponse(responseCode = "415", description = "Unsupported media type"),
			@ApiResponse(responseCode = "422", description = "Request body contains invalid content") })
	public Text replaceTextById(
			@PathParam("planId") @Parameter(description = "ID of the plan to be updated",
					example = "123") String planId,
			@PathParam("id") @Parameter(description = "ID of the text to be updated (GML-Id of the feature)",
					example = "GML_ID_123") String id,
			@Parameter(schema = @Schema(implementation = Text.class),
					required = true) @Valid @ModelValidator(Text.class) @FormDataParam("textmodel") FormDataBodyPart textmodel,
			@Parameter(schema = @Schema(type = "string", format = "binary")) @FormDataParam("datei") InputStream datei,
			@Parameter(hidden = true) @FormDataParam("datei") FormDataContentDisposition dateiMeta) throws Exception {
		if (textmodel == null) {
			throw new MissingRequestEntity("Multipart attachment 'textmodel' is missing.");
		}
		Text text = textmodel.getValueAs(Text.class);
		File file = editTextHandler.storeAsFile(datei, dateiMeta);
		return editTextHandler.replaceText(planId, id, text, file);
	}

}
