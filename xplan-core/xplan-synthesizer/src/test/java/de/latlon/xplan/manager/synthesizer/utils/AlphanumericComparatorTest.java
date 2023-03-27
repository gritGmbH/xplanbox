package de.latlon.xplan.manager.synthesizer.utils;

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

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AlphanumericComparatorTest {

	private final AlphanumericComparator comparator = new AlphanumericComparator();

	@Test
	public void testCompare() {
		int compare = Integer.valueOf(4).compareTo(Integer.valueOf(2));
		assertThat(compare, is(1));
	}

	@Test
	public void testCompare_BothNull() {
		int compare = comparator.compare(null, null);
		assertThat(compare, is(0));
	}

	@Test
	public void testCompare_FirstNull() {
		int compare = comparator.compare(null, "$1 Nr.2");
		assertThat(compare, is(1));
	}

	@Test
	public void testCompare_SecondNull() {
		int compare = comparator.compare("$1 Nr.15", null);
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_FirstString() {
		int compare = comparator.compare("ABC", "$1 Nr.2");
		assertThat(compare, is(greaterThan(0)));
	}

	@Test
	public void testCompare_SecondString() {
		int compare = comparator.compare("$1 Nr.15", "ABC");
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_Strings() {
		int compare = comparator.compare("ABC", "DEF");
		assertThat(compare, is(lessThan(0)));
	}

	@Test
	public void testCompare_Integer() {
		int compare = comparator.compare("10", "2");
		assertThat(compare, is(1));
	}

	@Test
	public void testCompare_ParagrahAndNr() {
		int compare = comparator.compare("$1 Nr.15", "$1 Nr.2");
		assertThat(compare, is(1));
	}

	@Test
	public void testCompare_Nr() {
		int compare = comparator.compare("1.1", "1.2");
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_NrDifferentLength() {
		int compare = comparator.compare("1.1", "1.2.9");
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_StringWithPipeAndParagraph() {
		int compare = comparator.compare("$1 Nr.15", "Ende | 1");
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_StringAndParagraph() {
		int compare = comparator.compare("$3", "SiebzehnPunktVier");
		assertThat(compare, is(-1));
	}

	@Test
	public void testSortList() {
		List<String> list = asList("$1 Nr.15", "$1", "$1 Nr.2", "Ende | 1", "$3", "SiebzehnPunktVier");
		Collections.sort(list, comparator);
		assertThat(list.get(0), is("$1"));
		assertThat(list.get(1), is("$1 Nr.2"));
		assertThat(list.get(2), is("$1 Nr.15"));
		assertThat(list.get(3), is("$3"));
		assertThat(list.get(4), is("Ende | 1"));
		assertThat(list.get(5), is("SiebzehnPunktVier"));
	}

	@Test
	public void testSortList_NoAndStrings() {
		List<String> list = asList("[1. | Mit]", "[2. | Im]", "[3. | Im]", "[4. | In]", "[5. | Im]", "[6. | Die]",
				"[7. | Auf]", "[8. | Auf]", "[9. | Auf]", "[ | Ueber die Festsetzungen nach § 9 Abs. 1 Nr. 25b]",
				"[10. | Im]", "[11. | Bei]", "[12. | Garagen]", "[13. | Das]",
				"[ | Rechtliche Grundlagen: Baugesetzbuch (BauGB) Baunutzungsverordnung (BauNVO) in der Fassung vom 23. Januar 1990 ]");
		Collections.sort(list, comparator);
		assertThat(list.get(0), is("[1. | Mit]"));
		assertThat(list.get(1), is("[2. | Im]"));
		assertThat(list.get(2), is("[3. | Im]"));
		assertThat(list.get(3), is("[4. | In]"));
		assertThat(list.get(4), is("[5. | Im]"));
		assertThat(list.get(5), is("[6. | Die]"));
		assertThat(list.get(6), is("[7. | Auf]"));
		assertThat(list.get(7), is("[8. | Auf]"));
		assertThat(list.get(8), is("[9. | Auf]"));
		assertThat(list.get(9), is("[10. | Im]"));
		assertThat(list.get(10), is("[11. | Bei]"));
		assertThat(list.get(11), is("[12. | Garagen]"));
		assertThat(list.get(12), is("[13. | Das]"));
		assertThat(list.get(13), is(
				"[ | Rechtliche Grundlagen: Baugesetzbuch (BauGB) Baunutzungsverordnung (BauNVO) in der Fassung vom 23. Januar 1990 ]"));
		assertThat(list.get(14), is("[ | Ueber die Festsetzungen nach § 9 Abs. 1 Nr. 25b]"));
	}

	private BaseMatcher<Integer> lessThan(int i) {
		return new BaseMatcher<Integer>() {
			@Override
			public boolean matches(Object o) {
				int toCompare = (int) o;
				return toCompare < i;
			}

			@Override
			public void describeTo(Description description) {

			}
		};
	}

	private BaseMatcher<Integer> greaterThan(int i) {
		return new BaseMatcher<Integer>() {
			@Override
			public boolean matches(Object o) {
				int toCompare = (int) o;
				return toCompare > i;
			}

			@Override
			public void describeTo(Description description) {

			}
		};
	}

}
