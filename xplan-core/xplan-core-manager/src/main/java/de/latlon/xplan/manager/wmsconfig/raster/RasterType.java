package de.latlon.xplan.manager.wmsconfig.raster;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public enum RasterType {

	TIFF("image/tiff"), PNG("image/png"), UNKNOWN;

	private final String mediaType;

	RasterType(String mediaType) {
		this.mediaType = mediaType;
	}

	RasterType() {
		this(null);
	}

	public static RasterType fromMediaType(String mediaType) {
		for (RasterType rasterType : values()) {
			if (rasterType.mediaType != null && rasterType.mediaType.equals(mediaType)) {
				return rasterType;
			}
		}
		return UNKNOWN;
	}

}
