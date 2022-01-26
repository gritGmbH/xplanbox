package de.latlon.xplan.manager.synthesizer.utils;

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
		System.out.println(list);
		assertThat(list.get(0), is("$1"));
		assertThat(list.get(1), is("$1 Nr.2"));
		assertThat(list.get(2), is("$1 Nr.15"));
		assertThat(list.get(3), is("$3"));
		assertThat(list.get(4), is("Ende | 1"));
		assertThat(list.get(5), is("SiebzehnPunktVier"));
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
