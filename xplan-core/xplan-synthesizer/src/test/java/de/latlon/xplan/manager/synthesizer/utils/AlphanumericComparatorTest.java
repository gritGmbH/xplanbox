package de.latlon.xplan.manager.synthesizer.utils;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AlphanumericComparatorTest {

	private final AlphanumericComparator comparator = new AlphanumericComparator();

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
		assertThat(compare, is(1));
	}

	@Test
	public void testCompare_SecondString() {
		int compare = comparator.compare("$1 Nr.15", "ABC");
		assertThat(compare, is(-1));
	}

	@Test
	public void testCompare_Strings() {
		int compare = comparator.compare("ABC", "DEF");
		assertThat(compare, is(-3));
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
	public void testSortList() {
		List<String> list = asList("$1 Nr.15", "$1", "$1 Nr.2", "Ende");
		Collections.sort(list, comparator);
		System.out.println(list);
		assertThat(list.get(0), is("$1"));
		assertThat(list.get(1), is("$1 Nr.2"));
		assertThat(list.get(2), is("$1 Nr.15"));
		assertThat(list.get(3), is("Ende"));
	}

}
