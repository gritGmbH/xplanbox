package de.latlon.xplan.manager.metadata;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResource {

    private final String id;

    private final String url;

    public CoupledResource( String id, String url ) {

        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}