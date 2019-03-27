package de.latlon.xplan.planwerkwms;

import org.deegree.geometry.Envelope;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Planwerk {

    private final String name;

    private final List<Integer> managerIds;

    private final Envelope bbox;

    public Planwerk( String name, List<Integer> managerIds, Envelope bbox ) {
        this.name = name;
        this.managerIds = managerIds;
        this.bbox = bbox;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getManagerIds() {
        return managerIds;
    }

    public Envelope getBbox() {
        return bbox;
    }

}