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
package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanVersion;

import org.assertj.core.api.AbstractAssert;
import org.deegree.commons.xml.NamespaceBindings;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.util.Iterator;
import java.util.function.Predicate;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XPlanVersionUtilsTest {

	@Test
	void testDetermineBaseVersionFor40() {
		QName element = new QName(XPLAN_40.getNamespace(), "element");
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(element);
		assertThat(version).isEqualTo(XPLAN_40);
	}

	@Test
	void testDetermineBaseVersionFor41() {
		QName element = new QName(XPLAN_41.getNamespace(), "element");
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(element);
		assertThat(version).isEqualTo(XPLAN_41);
	}

	@Test
	void testDetermineBaseVersionFor50() {
		QName element = new QName(XPLAN_50.getNamespace(), "element");
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(element);
		assertThat(version).isEqualTo(XPLAN_50);
	}

	@Test
	void testDetermineBaseVersionFor51() {
		QName element = new QName(XPLAN_51.getNamespace(), "element");
		XPlanVersion version = XPlanVersionUtils.determineBaseVersion(element);
		assertThat(version).isEqualTo(XPLAN_51);
	}

	@Test
	void testDetermineBaseVersionForUnknownNamespaceShouldFail() {
		QName element = new QName("http://unknown.namespaceuri.de", "element");
		assertThrows(IllegalArgumentException.class, () -> XPlanVersionUtils.determineBaseVersion(element));
	}

	@Test
	void testRetrieveNamespaceBindingsFor40() {
		QName element = new QName(XPLAN_40.getNamespace(), "element");
		NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings(element);
		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_40.getNamespace(), "xplan");
		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_40.getGmlVersion().getNamespace(), "gml");
	}

	@Test
	void testRetrieveNamespaceBindingsFor41() {
		QName element = new QName(XPLAN_41.getNamespace(), "element");
		NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings(element);
		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_41.getNamespace(), "xplan");
		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_41.getGmlVersion().getNamespace(), "gml");
	}

	@Test
	void testRetrieveNamespaceBindingsFor50() {
		QName element = new QName(XPLAN_50.getNamespace(), "element");
		NamespaceBindings namespaceBindings = XPlanVersionUtils.retrieveNamespaceBindings(element);
		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_50.getNamespace(), "xplan");

		assertThatNamespaceBindings(namespaceBindings).hasNamespace(XPLAN_50.getGmlVersion().getNamespace(), "gml");
	}

	@Test
	void testRetrieveNamespaceBindingsForUnknownNamespace() {
		QName element = new QName("http://unknown.namespaceuri.de", "element");
		assertThrows(IllegalArgumentException.class, () -> XPlanVersionUtils.retrieveNamespaceBindings(element));
	}

	static class NamespaceBindingsAssert extends AbstractAssert<NamespaceBindingsAssert, NamespaceBindings> {

		protected NamespaceBindingsAssert(NamespaceBindings actual) {
			super(actual, NamespaceBindingsAssert.class);
		}

		NamespaceBindingsAssert hasNamespace(final String namespace, final String prefix) {
			Iterator<String> prefixes = actual.getPrefixes();
			while (prefixes.hasNext()) {
				if (prefix.equals(prefixes.next())) {
					String namespaceURI = actual.getNamespaceURI(prefix);
					if (namespace.equals(namespaceURI))
						return this;
				}
			}

			throw failure("NamespaceBindings must contain namespace " + namespace + " with prefix " + prefix);
		}

	}

	static NamespaceBindingsAssert assertThatNamespaceBindings(NamespaceBindings actual) {
		return new NamespaceBindingsAssert(actual);
	}

}
