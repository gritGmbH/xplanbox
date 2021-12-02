package de.latlon.xplanbox.api.manager.v1;

import de.latlon.xplanbox.api.manager.exception.MissingRequestEntity;
import de.latlon.xplanbox.api.manager.handler.EditDokumentHandler;
import de.latlon.xplanbox.api.manager.v1.model.Dokument;
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
					@ApiResponse(responseCode = "404", description = "Invalid plan ID, plan not found"),
					@ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
	public List<Dokument> getDokumente(@PathParam("planId") @Parameter(
			description = "planId of the plan to return dokumente", example = "123") String planId) throws Exception {
		return editDokumentHandler.retrieveDokumente(planId);
	}

	@POST
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "addDokument", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Dokument.class))),
					@ApiResponse(responseCode = "404",
							description = "Invalid plan ID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "400",
							description = "Unsupported Plan type or version or dokumentmodel is missing") })
	public Dokument addDokument(
			@PathParam("planId") @Parameter(description = "ID of the plan to add dokumente",
					example = "123") String planId,
			@Parameter(schema = @Schema(implementation = Dokument.class),
					required = true) @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
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
					@ApiResponse(responseCode = "404",
							description = "Invalid plan ID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
	public Dokument getDokumentById(
			@PathParam("planId") @Parameter(description = "planId of the plan to get dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "id of the Dokument to be returned (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id)
			throws Exception {
		return editDokumentHandler.retrieveDokument(planId, id);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ "multipart/form-data" })
	@Produces({ "application/json" })
	@Operation(operationId = "replaceDokumentById", tags = { "edit" },
			responses = {
					@ApiResponse(responseCode = "200", description = "successful operation",
							content = @Content(schema = @Schema(implementation = Dokument.class))),
					@ApiResponse(responseCode = "404",
							description = "Invalid plan ID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "400",
							description = "Unsupported Plan type or version or dokumentmodel is missing") })
	public Dokument replaceDokumentById(
			@PathParam("planId") @Parameter(description = "planId of the plan to replace dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "id of the Dokument to be updated (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id,
			@Parameter(schema = @Schema(implementation = Dokument.class),
					required = true) @FormDataParam("dokumentmodel") FormDataBodyPart dokumentmodel,
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
							description = "Invalid plan ID or dokument ID, plan or dokument not found"),
					@ApiResponse(responseCode = "400", description = "Unsupported Plan type or version") })
	public Dokument deleteDokumentById(
			@PathParam("planId") @Parameter(description = "planId of the plan to delete dokument",
					example = "123") String planId,
			@PathParam("id") @Parameter(
					description = "id of the Dokument to be deleted (Pattern of the ID: referenzName-referenzURL, other characters than [a-z,A-Z,0-9,_,-] are removed)",
					example = "Legende123-") String id)
			throws Exception {
		return editDokumentHandler.deleteDokument(planId, id);
	}

}