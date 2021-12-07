package de.latlon.xplan.manager.synthesizer.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AlphanumericComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null && o2 != null)
			return 1;
		if (o1 != null && o2 == null)
			return -1;
		List<Integer> integersO1 = parseInts(o1);
		List<Integer> integersO2 = parseInts(o2);
		if (integersO1.isEmpty() && integersO2.isEmpty())
			return o1.compareTo(o2);
		if (integersO1.isEmpty())
			return 1;
		if (integersO2.isEmpty())
			return -1;
		return compare(integersO1, integersO2);
	}

	private int compare(List<Integer> integersO1, List<Integer> integersO2) {
		int maxSize = Math.max(integersO1.size(), integersO2.size());
		for (int index = 0; index < maxSize; index++) {
			if (index < integersO1.size() && index < integersO2.size()) {
				Integer i1 = integersO1.get(index);
				Integer i2 = integersO2.get(index);
				if (!i1.equals(i2))
					return i1.compareTo(i2);
			}
			else if (index >= integersO1.size()) {
				return -1;
			}
			else if (index >= integersO2.size()) {
				return 1;
			}
		}
		return 0;
	}

	private List<Integer> parseInts(String stringToParse) {
		if (stringToParse.contains("|")) {
			stringToParse = stringToParse.substring(0, stringToParse.indexOf("|"));
		}
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(stringToParse);
		List<Integer> integers = new ArrayList<>();
		while (m.find()) {
			integers.add(Integer.parseInt(m.group()));
		}
		return integers;
	}

}
