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
package de.latlon.xplan.manager.synthesizer.expression.dictionary;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.dictionary.XPlanEnumerationFactory;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.deegree.feature.Feature;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

/**
 * {@link Expression} for translating codes from internal codelists (aka enumerations) to
 * their textual representation.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanEnumerationLookup extends AbstractXPlanDictionaryLookup {

	public XPlanEnumerationLookup(Expression exp, String codeListName) {
		super(exp, codeListName);
	}

	@Override
	public XPlanDictionaries getXPlanDictionaries(Feature feature) {
		XPlanVersion version = determineBaseVersion(feature.getName());
		return XPlanEnumerationFactory.get(version);
	}

}
