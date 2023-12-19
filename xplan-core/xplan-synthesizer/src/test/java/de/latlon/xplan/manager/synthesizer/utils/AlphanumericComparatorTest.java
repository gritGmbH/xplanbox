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
package de.latlon.xplan.manager.synthesizer.utils;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class AlphanumericComparatorTest {

	private final AlphanumericComparator comparator = new AlphanumericComparator();

	@Test
	void testCompare_BothNull() {
		int compare = comparator.compare(null, null);
		assertEquals(0, compare);
	}

	@Test
	void testCompare_FirstNull() {
		int compare = comparator.compare(null, "§1 Nr.2");
		assertEquals(1, compare);
	}

	@Test
	void testCompare_SecondNull() {
		int compare = comparator.compare("§1 Nr.15", null);
		assertEquals(-1, compare);
	}

	@Test
	void testCompare_FirstString() {
		int compare = comparator.compare("ABC", "§1 Nr.2");
		assertThat(compare).isGreaterThan(0);
	}

	@Test
	void testCompare_SecondString() {
		int compare = comparator.compare("§1 Nr.15", "ABC");
		assertEquals(-1, compare);
	}

	@Test
	void testCompare_Strings() {
		int compare = comparator.compare("ABC", "DEF");
		assertThat(compare).isLessThan(0);
	}

	@Test
	void testCompare_Integer() {
		int compare = comparator.compare("10", "2");
		assertEquals(1, compare);
	}

	@Test
	void testCompare_ParagrahAndNr() {
		int compare = comparator.compare("§1 Nr.15", "§1 Nr.2");
		assertEquals(1, compare);
	}

	@Test
	void testCompare_Nr() {
		int compare = comparator.compare("1.2", "1.3");
		assertEquals(-1, compare);
	}

	@Test
	void testCompare_NrDifferentLength() {
		int compare = comparator.compare("1.1", "1.2.9");
		assertEquals(-1, compare);
	}

	@Test
	void testCompare_StringWithPipeAndParagraph() {
		int compare = comparator.compare("§1 Nr.15", "Ende | 1");
		assertEquals(-1, compare);
	}

	@Test
	void testCompare_StringAndParagraph() {
		int compare = comparator.compare("§3", "SiebzehnPunktVier");
		assertEquals(-1, compare);
	}

	@Test
	void testSortList_paragraphsAndNr() {
		List<String> list = asList("§1 Nr.15", "§1", "§1 Nr.2", "Ende | 1", "§3", "SiebzehnPunktVier");
		Collections.sort(list, comparator);
		assertThat(list).containsExactly("§1", "§1 Nr.2", "§1 Nr.15", "§3", "Ende | 1", "SiebzehnPunktVier");
	}

	@Test
	void testSortList_numbersOnly() {
		List<String> list = asList("2.1 text", "1.2 text", "2.2 text", "1.1 text");
		Collections.sort(list, comparator);
		assertThat(list).containsExactly("1.1 text", "1.2 text", "2.1 text", "2.2 text");
	}

	@Test
	void testSortList_charactersOnly() {
		List<String> list = asList("b) text", "d) text", "c) text", "a) text");
		Collections.sort(list, comparator);
		assertThat(list).containsExactly("a) text", "b) text", "c) text", "d) text");
	}

	@Test
	void testSortList_charactersAndNumbersMixed() {
		List<String> list = asList("b) text", "2.1 text", "d) text", "1.1 text", "c) text", "a) text", "1.2 text",
				"2.2 text");
		Collections.sort(list, comparator);

		assertThat(list).containsExactly("1.1 text", "1.2 text", "2.1 text", "2.2 text", "a) text", "b) text",
				"c) text", "d) text");
	}

	@Test
	void testSortList_charactersAndNumbers() {
		List<String> list = asList("B text", "A.1.1 text", "A text", "B.1.1 text", "A.1.2 text", "B.1.2 text");
		Collections.sort(list, comparator);
		assertThat(list).containsExactly("A text", "A.1.1 text", "A.1.2 text", "B text", "B.1.1 text", "B.1.2 text");
	}

	@Test
	void testSortList_NoAndStrings() {
		List<String> list = asList("[1. | Mit]", "[2. | Im]", "[3. | Im]", "[4. | In]", "[5. | Im]", "[6. | Die]",
				"[7. | Auf]", "[8. | Auf]", "[9. | Auf]", "[ | Ueber die Festsetzungen nach § 9 Abs. 1 Nr. 25b]",
				"[10. | Im]", "[11. | Bei]", "[12. | Garagen]", "[13. | Das]",
				"[ | Rechtliche Grundlagen: Baugesetzbuch (BauGB) Baunutzungsverordnung (BauNVO) in der Fassung vom 23. Januar 1990 ]");
		Collections.sort(list, comparator);
		assertThat(list.get(0)).isEqualTo("[1. | Mit]");
		assertThat(list.get(1)).isEqualTo("[2. | Im]");
		assertThat(list.get(2)).isEqualTo("[3. | Im]");
		assertThat(list.get(3)).isEqualTo("[4. | In]");
		assertThat(list.get(4)).isEqualTo("[5. | Im]");
		assertThat(list.get(5)).isEqualTo("[6. | Die]");
		assertThat(list.get(6)).isEqualTo("[7. | Auf]");
		assertThat(list.get(7)).isEqualTo("[8. | Auf]");
		assertThat(list.get(8)).isEqualTo("[9. | Auf]");
		assertThat(list.get(9)).isEqualTo("[10. | Im]");
		assertThat(list.get(10)).isEqualTo("[11. | Bei]");
		assertThat(list.get(11)).isEqualTo("[12. | Garagen]");
		assertThat(list.get(12)).isEqualTo("[13. | Das]");
		assertThat(list.get(13)).isIn(
				"[ | Rechtliche Grundlagen: Baugesetzbuch (BauGB) Baunutzungsverordnung (BauNVO) in der Fassung vom 23. Januar 1990 ]",
				"[ | Ueber die Festsetzungen nach § 9 Abs. 1 Nr. 25b]");
		assertThat(list.get(14)).isIn(
				"[ | Rechtliche Grundlagen: Baugesetzbuch (BauGB) Baunutzungsverordnung (BauNVO) in der Fassung vom 23. Januar 1990 ]",
				"[ | Ueber die Festsetzungen nach § 9 Abs. 1 Nr. 25b]");
	}

}
