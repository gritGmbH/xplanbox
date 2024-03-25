/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.validator.geometric.inspector.model;

import org.deegree.feature.Feature;

import java.util.ArrayList;
import java.util.List;

/**
 * A feature which should be part of the geltungsbereich.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FeatureUnderTest extends AbstractGeltungsbereichFeature {

	private static final List<String> GEHOERT_ZU_BEREICH_PROPNAMES = new ArrayList<>();

	private final InspectorContext inspectorContext;

	static {
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuBereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuBP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuLP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuFP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuRP_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertZuSO_Bereich");
		GEHOERT_ZU_BEREICH_PROPNAMES.add("gehoertNachrichtlichZuBereich");
	}

	public FeatureUnderTest(Feature feature, InspectorContext inspectorContext) {
		super(feature);
		this.inspectorContext = inspectorContext;
	}

	/**
	 * @return the name of the FeatureType, never <code>null</code>
	 */
	public String getFeatureType() {
		return feature.getName().getLocalPart();
	}

	/**
	 * @return The Plan or Bereich feature of this FeatureUnderTest, may be
	 * <code>null</code> if no Plan or Bereich feature is assigned
	 */
	public GeltungsbereichFeature getGeltungsbereichFeature() {
		String bereichId = getGehortZuBereichId();
		if (bereichId == null) {
			return getSingleGeltungsbereichFeature();
		}
		BereichFeature bereichFeature = inspectorContext.getBereichFeatures().get(bereichId);
		if (bereichFeature.hasGeometry())
			return bereichFeature;
		String planId = bereichFeature.getPlanId();
		return inspectorContext.getPlanFeatures().get(planId);
	}

	/**
	 * @return The Plan feature of this FeatureUnderTest, may be * <code>null</code> if no
	 * Plan feature is assigned
	 */
	public PlanFeature getPlanFeature() {
		BereichFeature bereichFeature = getBereichFeature();
		if (bereichFeature == null) {
			if (inspectorContext.getPlanFeatures().size() == 1) {
				return inspectorContext.getPlanFeatures().values().stream().findFirst().get();
			}
			return null;
		}
		String planId = bereichFeature.getPlanId();
		return inspectorContext.getPlanFeatures().get(planId);
	}

	/**
	 * @return The Bereich feature of this FeatureUnderTest, may be * <code>null</code> if
	 * no Bereich feature is assigned
	 */
	public BereichFeature getBereichFeature() {
		String bereichId = getGehortZuBereichId();
		if (bereichId == null) {
			if (inspectorContext.getBereichFeatures().size() > 1) {
				return null;
			}
			if (inspectorContext.getBereichFeatures().size() == 1) {
				BereichFeature singleBereichFeature = inspectorContext.getBereichFeatures()
					.values()
					.stream()
					.findFirst()
					.get();
				return singleBereichFeature;
			}
		}
		return inspectorContext.getBereichFeatures().get(bereichId);
	}

	/**
	 * @return the id of the bereich this feature belongs to, <code>null</code> is not
	 * assigned to a bereich
	 */
	private String getGehortZuBereichId() {
		for (String propName : GEHOERT_ZU_BEREICH_PROPNAMES) {
			String gehortZuBereichId = getPropertyValue(propName);
			if (gehortZuBereichId != null)
				return gehortZuBereichId;
		}
		return null;
	}

	private GeltungsbereichFeature getSingleGeltungsbereichFeature() {
		if (inspectorContext.getBereichFeatures().size() > 1) {
			return null;
		}
		if (inspectorContext.getBereichFeatures().size() == 1) {
			BereichFeature singleBereichFeature = inspectorContext.getBereichFeatures()
				.values()
				.stream()
				.findFirst()
				.get();
			if (singleBereichFeature.hasGeometry())
				return singleBereichFeature;
		}
		if (inspectorContext.getPlanFeatures().size() == 1) {
			return inspectorContext.getPlanFeatures().values().stream().findFirst().get();
		}
		return null;
	}

}
