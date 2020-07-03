package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.coordinatesystems.ICRS;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MainZipEntry implements ZipEntryWithContent {

    private final String name;

    private final XPlanVersion version;

    private final XPlanType type;

    private final XPlanAde ade;

    private final ICRS crs;

    private final String district;

    private final boolean hasMultipleXPlanElements;

    private final byte[] content;

    public MainZipEntry( byte[] content, String name, XPlanVersion version, XPlanType type, XPlanAde ade, ICRS crs,
                         String district, boolean hasMultipleXPlanElements ) {
        this.content = content;
        this.name = name;
        this.version = version;
        this.type = type;
        this.ade = ade;
        this.crs = crs;
        this.district = district;
        this.hasMultipleXPlanElements = hasMultipleXPlanElements;
    }

    @Override
    public byte[] getContent() {
        return content;
    }

    @Override
    public InputStream retrieveContentAsStream() {
        return new ByteArrayInputStream( content );
    }

    @Override
    public String getName() {
        return name;
    }

    public ICRS getCrs() {
        return crs;
    }

    public XPlanType getType() {
        return type;
    }

    public XPlanAde getAde() {
        return ade;
    }

    public String getDistrict() {
        return district;
    }

    public XPlanVersion getVersion() {
        return version;
    }

    public boolean hasMultipleXPlanElements() {
        return hasMultipleXPlanElements;
    }
}