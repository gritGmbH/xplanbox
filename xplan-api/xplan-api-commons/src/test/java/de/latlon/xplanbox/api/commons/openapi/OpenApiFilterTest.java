package de.latlon.xplanbox.api.commons.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OpenApiFilterTest {

    @Test
    public void verifyFilterOpenAPI_ThatPathItemKeyIsCorrected() {
        OpenApiFilter openApiFilter = new OpenApiFilter();
        OpenAPI openApi = new OpenAPI();
        openApi.setPaths( new Paths() );
        openApi.getPaths().addPathItem( "/xmanager/api/v1/plans", new PathItem() );
        openApiFilter.filterOpenAPI( openApi, null, null, null );

        assertThat( openApi.getPaths().get( "/plans" ), is( notNullValue() ) );
        assertThat( openApi.getPaths().get( "/" ), is( notNullValue() ) );
    }

}
