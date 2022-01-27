package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.coordinatesystems.ICRS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ArchiveMetadata {

	private final XPlanVersion version;

	private final XPlanType type;

	private final XPlanAde ade;

	private final ICRS crs;

	private final String district;

	private final boolean hasMultipleXPlanElements;

	public ArchiveMetadata(XPlanVersion version, XPlanType type, XPlanAde ade, ICRS crs, String district,
			boolean hasMultipleXPlanElements) {
		this.version = version;
		this.type = type;
		this.ade = ade;
		this.crs = crs;
		this.district = district;
		this.hasMultipleXPlanElements = hasMultipleXPlanElements;
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
