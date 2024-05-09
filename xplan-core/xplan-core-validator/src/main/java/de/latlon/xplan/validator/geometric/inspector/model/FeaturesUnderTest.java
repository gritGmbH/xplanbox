/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.model;

import org.deegree.geometry.Geometry;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FeaturesUnderTest {

	private final Geometry flaechenschlussUnion;

	private final List<FeatureUnderTest> featuresUnderTest;

	/**
	 * @param flaechenschlussUnion the union of the geometries of all featuresUnderTest,
	 * never <code>null</code>
	 * @param featuresUnderTest the featuresUnderTest, never <code>null</code>
	 */
	public FeaturesUnderTest(Geometry flaechenschlussUnion, List<FeatureUnderTest> featuresUnderTest) {
		this.flaechenschlussUnion = flaechenschlussUnion;
		this.featuresUnderTest = featuresUnderTest;
	}

	/**
	 * @return the union of the geometries of all featuresUnderTest, never
	 * <code>null</code>
	 */
	public Geometry getFlaechenschlussUnion() {
		return flaechenschlussUnion;
	}

	/**
	 * @return the featuresUnderTest, never <code>null</code>
	 */
	public List<FeatureUnderTest> getFeaturesUnderTest() {
		return featuresUnderTest;
	}

}
