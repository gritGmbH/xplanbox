/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.feature;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.GeometryInspector;
import org.deegree.gml.feature.FeatureInspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Builder to create new {@link XPlanGmlParser}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlParserBuilder {

	private ICRS defaultCrs;

	private boolean fixOrientation = false;

	private boolean skipBrokenGeometries = false;

	private List<FeatureInspector> featureInspectors = new ArrayList<>();

	private List<GeometryInspector> geometryInspectors = new ArrayList<>();

	private boolean skipResolveReferences = false;

	private XPlanGmlParserBuilder() {
	}

	/**
	 * @return a new instance of a {@link XPlanGmlParserBuilder }, never <code>null</code>
	 */
	public static XPlanGmlParserBuilder newBuilder() {
		return new XPlanGmlParserBuilder();
	}

	/**
	 * @param defaultCrs the default crs used to parse geometries, may be
	 * <code>null</code> (default)
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withDefaultCrs(ICRS defaultCrs) {
		this.defaultCrs = defaultCrs;
		return this;
	}

	/**
	 * @param fixOrientation <code>true</code> if the orientation should be fixed during
	 * parsing, <code>false</code> otherwise (default)
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withFixOrientation(boolean fixOrientation) {
		this.fixOrientation = fixOrientation;
		return this;
	}

	/**
	 * @param skipBrokenGeometries <code>true</code> if broken geometries should be
	 * skipped during parsing (no execptions are thrown) useful if geometries are
	 * validated, <code>false</code> otherwise (default)
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withSkipBrokenGeometries(boolean skipBrokenGeometries) {
		this.skipBrokenGeometries = skipBrokenGeometries;
		return this;
	}

	/**
	 * @param featureInspectors feature inspectors to add, never <code>null</code>
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withFeatureInspector(FeatureInspector... featureInspectors) {
		if (featureInspectors.length > 0) {
			this.featureInspectors = Arrays.asList(featureInspectors);
		}
		return this;
	}

	/**
	 * @param featureInspectors feature inspectors to add, never <code>null</code>
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withFeatureInspectors(List<FeatureInspector> featureInspectors) {
		if (!featureInspectors.isEmpty()) {
			this.featureInspectors = featureInspectors;
		}
		return this;
	}

	/**
	 * @param geometryInspectors geometry inspectors to add, never <code>null</code>
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withGeometryInspectors(GeometryInspector... geometryInspectors) {
		if (geometryInspectors.length > 0) {
			this.geometryInspectors = Arrays.asList(geometryInspectors);
		}
		return this;
	}

	/**
	 * @param skipResolveReferences <code>true</code> if resolving of references should be
	 * skipped, <code>false</code> otherwise
	 * @return this instance of the {@link XPlanGmlParserBuilder}
	 */
	public XPlanGmlParserBuilder withSkipResolveReferences(boolean skipResolveReferences) {
		this.skipResolveReferences = skipResolveReferences;
		return this;
	}

	/**
	 * @return a new instance of a {@link XPlanGmlParser}
	 */
	public XPlanGmlParser build() {
		return new XPlanGmlParser(defaultCrs, fixOrientation, skipBrokenGeometries, featureInspectors,
				geometryInspectors, skipResolveReferences);
	}

}
