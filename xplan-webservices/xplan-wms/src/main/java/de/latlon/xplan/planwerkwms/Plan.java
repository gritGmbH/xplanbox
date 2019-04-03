package de.latlon.xplan.planwerkwms;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Plan {

    private final String name;

    private final List<Integer> managerIds;

    private final String bbox;

    private final List<String> wmsTitles;

    private final List<String> resourceidentifiers;

    private final List<String> dataMetadataUrls;

    private final List<String> serviceMetadataUrls;

    public Plan( String name, List<Integer> managerIds, String bbox, List<String> wmsTitles,
                 List<String> resourceidentifiers, List<String> dataMetadataUrls, List<String> serviceMetadataUrls ) {
        this.name = name;
        this.managerIds = managerIds;
        this.bbox = bbox;
        this.wmsTitles = wmsTitles;
        this.resourceidentifiers = resourceidentifiers;
        this.dataMetadataUrls = dataMetadataUrls;
        this.serviceMetadataUrls = serviceMetadataUrls;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getManagerIds() {
        return managerIds;
    }

    public String getBbox() {
        return bbox;
    }

    public List<String> getWmsTitles() {
        return wmsTitles;
    }

    public List<String> getResourceidentifiers() {
        return resourceidentifiers;
    }

    public List<String> getDataMetadataUrls() {
        return dataMetadataUrls;
    }

    public List<String> getServiceMetadataUrls() {
        return serviceMetadataUrls;
    }
}