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
package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

import de.latlon.xplan.manager.synthesizer.expression.flatten.AbstractFlattener;
import org.apache.xerces.xs.XSElementDeclaration;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpGenerAttributFlattener extends AbstractFlattener {

	@Override
	public boolean accepts(TypedObjectNode node) {
		if (node instanceof ElementNode) {
			XSElementDeclaration elDecl = ((ElementNode) node).getXSType();
			return elDecl != null && isGenerAttribut(elDecl);
		}
		return false;
	}

	private boolean isGenerAttribut(XSElementDeclaration elDecl) {
		if ("XP_GenerAttribut".equals(elDecl.getName())) {
			return true;
		}
		return elDecl.getSubstitutionGroupAffiliation() != null
				&& isGenerAttribut(elDecl.getSubstitutionGroupAffiliation());
	}

	@Override
	public String flatten(TypedObjectNode xpGenerAttribut, boolean keepCodes) {
		TypedObjectNode name = getPropertyValue(xpGenerAttribut, "name");
		TypedObjectNode wert = getPropertyValue(xpGenerAttribut, "wert");
		return "[\"" + name + "\"=\"" + wert + "\"]";
	}

}
