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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.manager.synthesizer.expression.Ausrichtung;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import de.latlon.xplan.manager.synthesizer.expression.LatestDate;
import de.latlon.xplan.manager.synthesizer.expression.StringConstant;
import de.latlon.xplan.manager.synthesizer.expression.XPlanExternalCodeLookup;
import de.latlon.xplan.manager.synthesizer.expression.XPlanGeometry;
import de.latlon.xplan.manager.synthesizer.expression.XPlanGmlDescription;
import de.latlon.xplan.manager.synthesizer.expression.XPlanName;
import de.latlon.xplan.manager.synthesizer.expression.XPlanType;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanBaugebietFlaechenteile;
import de.latlon.xplan.manager.synthesizer.expression.XplanCodeLookup;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import de.latlon.xplan.manager.synthesizer.expression.XplanGmlName;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.SchriftinhaltLookup;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.SkalierungLookup;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.StylesheetIdLookup;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>RuleParser</code> class parses the Syn rules into corresponding objects.
 * These will be used for evalution in the context of a feature.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @version 1.0, Date: 2010-05-25
 */
class RuleParser {

	private final String xplanType;

	private final String xplanName;

	private final XPlanSynthesizer xPlanSynthesizer;

	/**
	 * @param xplanType the type of xplan document. See {@link XPlanType}
	 * @param xplanName the name of xplan document, i.e. the name of the
	 * XP_Plan-descendant feature in the document
	 */
	public RuleParser(String xplanType, String xplanName, XPlanSynthesizer xPlanSynthesizer) {
		this.xplanType = xplanType;
		this.xplanName = xplanName;
		this.xPlanSynthesizer = xPlanSynthesizer;
	}

	private String trimString(String s) {
		String result = s.trim();
		if (result.startsWith("'") && result.endsWith("'")) {
			result = result.substring(1, result.length() - 1);
		}
		return result;
	}

	private boolean asBoolean(String s) {
		String asString = trimString(s);
		return Boolean.parseBoolean(asString);
	}

	private Object parseDefaultValue(String s) {
		s = s.replace("\"", "");
		try {
			return Integer.parseInt(s);
		}
		catch (IllegalArgumentException ei) {
			try {
				return Double.parseDouble(s);
			}
			catch (IllegalArgumentException ed) {
			}
		}
		return s;
	}

	private Xpath parseXPath(List<String> args) {
		if (args.size() > 1)
			return new Xpath(trimString(args.get(0)), parseDefaultValue(args.get(1)));
		return new Xpath(trimString(args.get(0)));
	}

	private Expression parseXPlanFlattenFeature(List<String> args) {
		if (args.size() > 1) {
			return new XplanFlattenProperty(parse(args.get(0)), asBoolean(args.get(1)));
		}
		return new XplanFlattenProperty(parse(args.get(0)));
	}

	private Expression parseXPlanCodeLookup(List<String> args) {
		return new XplanCodeLookup(parse(args.get(0)), trimString(args.get(1)));
	}

	private Expression parseXPlanGeometry(List<String> args) {
		return new XPlanGeometry(parseXPath(args));
	}

	private Expression parseXPlanExternalCodeLookup(List<String> args) {
		Expression expression = parse(args.get(0));
		String codeListFile = trimString(args.get(1));
		String codeListName = args.size() > 2 ? trimString(args.get(2)) : null;
		return new XPlanExternalCodeLookup(expression, codeListFile, codeListName,
				xPlanSynthesizer.getExternalConfigurationFile());
	}

	private Expression parseAusrichtungLookup(List<String> args) {
		return new Ausrichtung(parse(args.get(0)));
	}

	private Expression parseStylesheetIdLookup() {
		return new StylesheetIdLookup();
	}

	private Expression parseSchriftinhaltLookup() {
		return new SchriftinhaltLookup();
	}

	private Expression parseSkalierungLookup() {
		return new SkalierungLookup();
	}

	private Expression parseLatest(List<String> args) {
		return new LatestDate(parse(args.get(0)));
	}

	private Expression parseFunction(String functionName, List<String> args) {
		switch (functionName) {
			case "xpath":
				return parseXPath(args);
			case "xplanGmlName":
				return new XplanGmlName();
			case "xplanGmlDescription":
				return new XPlanGmlDescription();
			case "xplanFlatten":
				return parseXPlanFlattenFeature(args);
			case "xplanCodeLookup":
				return parseXPlanCodeLookup(args);
			case "xplanGeometry":
				return parseXPlanGeometry(args);
			case "xplanAggregateFlaechenteil":
				return parseXPlanAggregateFlaechenteil();
			case "xplanType":
				return new XPlanType(xplanType);
			case "xplanName":
				return new XPlanName(xplanName);
			case "xplanExternalCodeLookup":
				// Required to resolve codelist from external files
				return parseXPlanExternalCodeLookup(args);
			case "ausrichtungLookup":
				return parseAusrichtungLookup(args);
			case "stylesheetIdLookup":
				return parseStylesheetIdLookup();
			case "schriftinhaltLookup":
				return parseSchriftinhaltLookup();
			case "skalierungLookup":
				return parseSkalierungLookup();
			case "latest":
				return parseLatest(args);
			default:
				throw new RuntimeException(String.format("Expression %s is not expected.", functionName));
		}
	}

	private Expression parseXPlanAggregateFlaechenteil() {
		return new XplanBaugebietFlaechenteile();
	}

	/**
	 * @param expr
	 * @return
	 */
	public Expression parse(String expr) {
		int firstP = expr.indexOf("(");
		int firstQ = expr.indexOf("\'");
		if (firstP == -1 || (firstQ != -1 && firstQ < firstP)) {
			// if there are no parantheses or the paranthesis should be taken as text
			return new StringConstant(trimString(expr));
		}
		String functionName = expr.substring(0, firstP);
		List<String> args = new ArrayList<String>();
		int cursor = firstP + 1;
		int parantBalance = 1;
		StringBuilder currentArg = new StringBuilder();
		boolean notValidSeparator = false; // commas that are strings parts of the concat
											// function should not be
		// treated as separators
		while (parantBalance > 0) {
			if (cursor >= expr.length()) {
				throw new RuntimeException("cursor surpassed length of expr: " + expr);
			}
			if (expr.charAt(cursor) == '\'') {
				notValidSeparator = !notValidSeparator;
			}
			if (!notValidSeparator && expr.charAt(cursor) == ',' && parantBalance == 1) {
				args.add(currentArg.toString());
				currentArg = new StringBuilder();
			}
			else if (!notValidSeparator && expr.charAt(cursor) == '(') {
				currentArg.append(expr.charAt(cursor));
				parantBalance++;
			}
			else if (!notValidSeparator && expr.charAt(cursor) == ')') {
				parantBalance--;
				if (parantBalance != 0) {
					currentArg.append(expr.charAt(cursor));
				}
			}
			else {
				currentArg.append(expr.charAt(cursor));
			}
			cursor++;
		}
		args.add(currentArg.toString());
		return parseFunction(functionName.trim(), args);
	}

}
