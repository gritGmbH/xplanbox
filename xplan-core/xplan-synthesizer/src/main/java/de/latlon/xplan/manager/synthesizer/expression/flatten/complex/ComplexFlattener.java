/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.flatten.complex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.util.XPlanVersionUtils;
import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.model.DataTypeFlattener;
import de.latlon.xplan.manager.synthesizer.expression.flatten.model.FlattenerProperty;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.utils.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ComplexFlattener extends AbstractFlattener {

	private static final Logger LOG = LoggerFactory.getLogger(ComplexFlattener.class);

	public static final String FLATTENER_RESOURCE = "/flattener/complexFlattener.yaml";

	private final Map<String, DataTypeFlattener> complexFlattener;

	public ComplexFlattener(XPlanCodelists xPlanCodelists) {
		super(xPlanCodelists);
		List<DataTypeFlattener> dataTypeFlatteners = loadDataTypeFlattener();
		this.complexFlattener = dataTypeFlatteners.stream()
			.collect(Collectors.toMap(DataTypeFlattener::getAcceptedClass, Function.identity()));
	}

	@Override
	public boolean accepts(TypedObjectNode element) {
		return acceptsElementNode(element, complexFlattener.keySet());
	}

	@Override
	public String flatten(TypedObjectNode element, boolean keepCodes) {
		QName elNodeName = ((ElementNode) element).getName();
		DataTypeFlattener dataTypeFlattener = complexFlattener.get(elNodeName.getLocalPart());
		if (dataTypeFlattener != null) {
			XPlanVersion version = XPlanVersionUtils.determineBaseVersion(elNodeName);
			List<FlattenerProperty> flattenerProperties = dataTypeFlattener.getProperties();
			List<Pair<String, String>> properties = new ArrayList<>();
			flattenerProperties.forEach(flattenerProperty -> {
				String label = flattenerProperty.getLabel();
				String propertyName = flattenerProperty.getPropertyName();
				if (flattenerProperty.getEnumerationName() != null) {
					appendEnum(label, element, propertyName, version, flattenerProperty.getEnumerationName(), keepCodes,
							properties);
				}
				else if (flattenerProperty.getCodeListName() != null) {
					appendCode(label, element, propertyName, version, flattenerProperty.getCodeListName(), keepCodes,
							properties);
				}
				else {
					append(label, element, propertyName, properties);
				}
			});
			return encode(properties);
		}
		return null;
	}

	private List<DataTypeFlattener> loadDataTypeFlattener() {
		try {
			InputStream resourceAsStream = DataTypeFlattener.class.getResourceAsStream(FLATTENER_RESOURCE);
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			CollectionType javaType = om.getTypeFactory().constructCollectionType(List.class, DataTypeFlattener.class);
			return om.readValue(resourceAsStream, javaType);
		}
		catch (IOException e) {
			LOG.error("Could not read " + FLATTENER_RESOURCE, e);
			throw new RuntimeException("Could not read " + FLATTENER_RESOURCE + " as DataTypeFlattener yaml file.");
		}
	}

}
