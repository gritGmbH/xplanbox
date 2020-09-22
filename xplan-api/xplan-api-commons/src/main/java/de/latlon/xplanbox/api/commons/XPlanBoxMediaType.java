package de.latlon.xplanbox.api.commons;

import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanBoxMediaType {

    public static final String APPLICATION_ZIP = "application/zip";

    public static final MediaType APPLICATION_ZIP_TYPE = new MediaType( "application", "zip" );

    public static final String APPLICATION_X_ZIP = "application/x-zip";

    public static final MediaType APPLICATION_X_ZIP_TYPE = new MediaType( "application", "x-zip" );

    public static final String APPLICATION_X_ZIP_COMPRESSED = "application/x-zip-compressed";

    public static final MediaType APPLICATION_X_ZIP_COMPRESSED_TYPE = new MediaType( "application", "x-zip-compressed" );

    public static final String APPLICATION_PDF = "application/pdf";

    public static final MediaType APPLICATION_PDF_TYPE = new MediaType( "application", "pdf" );

}
