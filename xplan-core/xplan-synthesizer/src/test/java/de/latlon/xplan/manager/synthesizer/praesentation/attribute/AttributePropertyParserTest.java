/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.synthesizer.praesentation.attribute;

import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributeProperty;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyParser;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.CODE_OR_ENUM;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.OTHER;
import static de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute.AttributePropertyType.STRING;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttributePropertyParserTest {

	private static FeatureCollection features;

	private AttributePropertyParser attributePropertyParser = new AttributePropertyParser();

	@BeforeClass
	public static void initTestFeatures() throws Exception {
		features = getTestFeatures(XPLAN_54, "/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
	}

	@Test
	public void testParseAttributeProperties_singleStep_enum() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("planArt[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("planArt"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(CODE_OR_ENUM));
		assertThat(attributeProperties.get(0).getValue(), is("1000"));
	}

	@Test
	public void testParseAttributeProperties_singleStep_code() {
		Feature feature = getTestFeature(features, "GML_c8fa8ddd-d5de-4111-9489-03967eec715e");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("sonstGebietsArt[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("sonstGebietsArt"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(CODE_OR_ENUM));
		assertThat(attributeProperties.get(0).getValue(), is("4242"));
	}

	@Test
	public void testParseAttributeProperties_singleStep_string() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("beschreibung[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("beschreibung"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(STRING));
		assertThat(attributeProperties.get(0).getValue(), is("Testdaten"));
	}

	@Test
	public void testParseAttributeProperties_singleStep_boolean() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("veraenderungssperre[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("veraenderungssperre"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(OTHER));
		assertThat(attributeProperties.get(0).getValue(), is("false"));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_gemeindeName() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/XP_Gemeinde/gemeindeName[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("gemeindeName"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(STRING));
		assertThat(attributeProperties.get(0).getValue(), is("Freie und Hansestadt Hamburg"));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_withNamespacePrefix() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt(
				"xplan:gemeinde[0]/xplan:XP_Gemeinde/xplan:gemeindeName[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("gemeindeName"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(STRING));
		assertThat(attributeProperties.get(0).getValue(), is("Freie und Hansestadt Hamburg"));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_withInvalidNamespacePrefix() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt(
				"xplan:gemeinde[0]/invalid:XP_Gemeinde/xplan:gemeindeName[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("gemeindeName"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(STRING));
		assertThat(attributeProperties.get(0).getValue(), is("Freie und Hansestadt Hamburg"));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_ags() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/XP_Gemeinde/ags[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(1));
		assertThat(attributeProperties.get(0).getAttribute(), is("ags"));
		assertThat(attributeProperties.get(0).getAttributePropertyType(), is(STRING));
		assertThat(attributeProperties.get(0).getValue(), is("02000000"));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_withMissingStep_gemeindeName() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/gemeindeName[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_withMissingStep_ags() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/ags[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_multipleArtProperties_withMissingSteps() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/ags[0]", "gemeinde[0]/gemeindeName[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_positionIsNotAnInteger() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("planArt[o]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_positionOutOfNumberOfElements() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("planArt[2]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_positionIsNotAnInteger() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/ags[o]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_positionOutOfNumberOfElements() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/ags[2]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_singleStep_unknownProperty() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("unknown[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	@Test
	public void testParseAttributeProperties_multipleSteps_unknownProperty() {
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");

		TypedObjectNodeArray<TypedObjectNode> artNodes = mockArt("gemeinde[0]/unknown[0]");
		List<AttributeProperty> attributeProperties = attributePropertyParser.parseAttributeProperties(feature,
				artNodes);
		assertThat(attributeProperties.size(), is(0));
	}

	private TypedObjectNodeArray<TypedObjectNode> mockArt(String... artNodes) {
		List<SimpleProperty> properties = Arrays.stream(artNodes).map(artNode -> {
			SimpleProperty property = Mockito.mock(SimpleProperty.class);
			PrimitiveValue primitiveValue = new PrimitiveValue(artNode);
			Mockito.when(property.getValue()).thenReturn(primitiveValue);
			return property;
		}).collect(Collectors.toList());
		return new TypedObjectNodeArray<>(properties.toArray(new SimpleProperty[0]));
	}

}
