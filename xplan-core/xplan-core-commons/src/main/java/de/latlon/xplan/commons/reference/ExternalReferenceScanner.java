/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.reference;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

/**
 * Scans an XPlan {@link FeatureCollection} for
 * <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code> objects as well as
 * their usage inside <code>XP_RasterplanBasis</code>/<code>XP_RasterplanAenderung</code>
 * features.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class ExternalReferenceScanner {

	/**
	 * Scans the given XPlan {@link FeatureCollection}.
	 * @param fc feature collection, must not be <code>null</code>
	 * @return reference information, never <code>null</code>
	 */
	public ExternalReferenceInfo scan(FeatureCollection fc) {
		XPlanVersion version = determineBaseVersion(fc.getName());
		return scanFc(fc, version);
	}

	public ExternalReferenceInfo scan(FeatureCollection fc, XPlanVersion version) {
		return scanFc(fc, version);
	}

	private ExternalReferenceInfo scanFc(FeatureCollection fc, XPlanVersion version) {
		switch (version) {
			case XPLAN_40:
			case XPLAN_41:
				return scanXplan4(fc);
			case XPLAN_50:
			case XPLAN_51:
			case XPLAN_52:
			case XPLAN_53:
			case XPLAN_54:
			case XPLAN_60:
				return scanXplan5or6(fc);
			default:
				throw new IllegalArgumentException("Unsupported XPlanGML Version: " + version);
		}
	}

	private ExternalReferenceInfo scanXplan4(FeatureCollection fc) {
		ExternalReferenceInfoBuilder externalReferenceInfoBuilder = new ExternalReferenceInfoBuilder();
		for (Feature feature : fc) {
			String name = feature.getName().getLocalPart();
			if ("XP_RasterplanBasis".equals(name)) {
				for (Property prop : feature.getProperties()) {
					if ("refScan".equals(prop.getName().getLocalPart())) {
						externalReferenceInfoBuilder.addRasterPlanBaseScans(scanXplan4or5(prop));
					}
					else {
						externalReferenceInfoBuilder.withNonRasterReferences(scanXplan4or5(prop));
					}
				}
			}
			else if (isRasterplanAenderungFeature(name)) {
				for (Property prop : feature.getProperties()) {
					if ("refScan".equals(prop.getName().getLocalPart())) {
						externalReferenceInfoBuilder.addRasterPlanUpdateScans(scanXplan4or5(prop));
					}
					else {
						externalReferenceInfoBuilder.withNonRasterReferences(scanXplan4or5(prop));
					}
				}
			}
			else {
				for (Property prop : feature.getProperties()) {
					externalReferenceInfoBuilder.withNonRasterReferences(scanXplan4or5(prop));
				}
			}
		}
		return externalReferenceInfoBuilder.build();
	}

	private ExternalReferenceInfo scanXplan5or6(FeatureCollection fc) {
		ExternalReferenceInfoBuilder externalReferenceInfoBuilder = new ExternalReferenceInfoBuilder();
		for (Feature feature : fc) {
			String name = feature.getName().getLocalPart();
			if ("XP_Rasterdarstellung".equals(name) || name.matches("(BP|LP|RP|FP|SO)_Bereich")) {
				for (Property prop : feature.getProperties()) {
					if ("refScan".equals(prop.getName().getLocalPart())) {
						externalReferenceInfoBuilder.addRasterPlanBaseScans(scanXplan4or5(prop));
					}
					else {
						externalReferenceInfoBuilder.withNonRasterReferences(scanXplan4or5(prop));
					}
				}
			}
			else {
				for (Property prop : feature.getProperties()) {
					externalReferenceInfoBuilder.withNonRasterReferences(scanXplan4or5(prop));
				}
			}
		}
		return externalReferenceInfoBuilder.build();
	}

	private boolean isRasterplanAenderungFeature(String name) {
		return "P_RasterplanAenderung".equals(name.substring(1));
	}

	private List<ExternalReference> scanXplan4or5(ElementNode elNode) {
		List<ExternalReference> refs = new ArrayList<>();
		String name = elNode.getName().getLocalPart();
		if ("XP_ExterneReferenz".equals(name)) {
			ExternalReference externalRef = createExternalReferenceXplan4or5(elNode);
			refs.add(externalRef);
		}
		else if ("XP_SpezExterneReferenz".equals(name)) {
			ExternalReference externalRef = createExternalReferenceXplan4or5(elNode);
			refs.add(externalRef);
		}
		else {
			for (TypedObjectNode childNode : elNode.getChildren()) {
				if (childNode instanceof ElementNode) {
					refs.addAll(scanXplan4or5((ElementNode) childNode));
				}
			}
		}
		return refs;
	}

	private ExternalReference createExternalReferenceXplan4or5(ElementNode elNode) {
		String referenzName = null;
		String referenzUrl = null;
		String referenzMimeTypeCode = null;
		String geoRefUrl = null;
		String geoRefMimeTypeCode = null;
		boolean isPlan = false;
		for (TypedObjectNode childNode : elNode.getChildren()) {
			if (childNode instanceof ElementNode) {
				ElementNode childEl = (ElementNode) childNode;
				String name = childEl.getName().getLocalPart();
				if ("georefURL".equals(name)) {
					geoRefUrl = getStringValue(childEl);
				}
				else if ("georefMimeType".equals(name)) {
					geoRefMimeTypeCode = getStringValue(childEl);
				}
				else if ("art".equals(name)) {
					String art = getStringValue(childEl);
					if ("PlanMitGeoreferenz".equals(art.trim())) {
						isPlan = true;
					}
				}
				else if ("referenzName".equals(name)) {
					referenzName = getStringValue(childEl);
				}
				else if ("referenzURL".equals(name)) {
					referenzUrl = getStringValue(childEl);
				}
				else if ("referenzMimeType".equals(name)) {
					referenzMimeTypeCode = getStringValue(childEl);
				}
			}
		}
		return new ExternalReference(geoRefUrl, geoRefMimeTypeCode, referenzUrl, referenzName, referenzMimeTypeCode,
				isPlan);
	}

	private String getStringValue(ElementNode childEl) {
		for (TypedObjectNode node : childEl.getChildren()) {
			if (node instanceof PrimitiveValue) {
				return ((PrimitiveValue) node).getAsText().trim();
			}
		}
		return "";
	}

}
