/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import de.latlon.xplan.validator.semantic.report.ValidationResultType;
import net.sf.saxon.Configuration;
import net.sf.saxon.ma.arrays.SimpleArrayItem;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.Sequence;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.om.TreeInfo;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.WARNING;
import static java.lang.String.format;

/**
 * Semantically validation rule, based on XQuery.
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorRule implements SemanticValidatorRule {

	private static final Logger LOG = LoggerFactory.getLogger(XQuerySemanticValidatorRule.class);

	private final Configuration configuration = new Configuration();

	private final XQueryExpression expression;

	private final String name;

	private String message;

	private XPlanVersion version;

	private SemanticValidationOptions ignoredOption;

	public XQuerySemanticValidatorRule(InputStream statementStream, String name, XPlanVersion version,
			SemanticValidationOptions validationOption, String message) throws IOException, XPathException {
		this.version = version;
		this.ignoredOption = validationOption;
		this.expression = compileStatement(statementStream);
		this.name = name;
		this.message = message;
	}

	@Override
	public List<InvalidFeaturesResult> validate(SemanticValidableXPlanArchive archive) throws ValidatorException {
		final Properties props = createProperties();
		try (Writer writer = new StringWriter()) {
			final DynamicQueryContext dynamicContext = createDynamicQueryContext(archive);
			expression.run(dynamicContext, new StreamResult(writer), props);
			final SequenceIterator iterator = expression.iterator(dynamicContext);
			return evaluateXQueryResult(iterator);
		}
		catch (XPathException | IOException e) {
			LOG.warn(format("Could not validate rule %s, reason:%s", this.getName(), e.getMessage()));
			LOG.debug("Exception: ", e);
			throw new ValidatorException("Rule could not be validated!", e);
		}
	}

	@Override
	public XPlanVersion getXPlanVersion() {
		return version;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean isIgnoredByOption(SemanticValidationOptions option) {
		if (option == null || NONE.equals(option))
			return false;
		return ignoredOption != null && ignoredOption.equals(option);
	}

	@Override
	public String getMessage() {
		return message;
	}

	private Properties createProperties() {
		final Properties props = new Properties();
		props.setProperty(OutputKeys.METHOD, "text");
		return props;
	}

	private DynamicQueryContext createDynamicQueryContext(SemanticValidableXPlanArchive archive) throws XPathException {
		Source source = getSource(archive);
		TreeInfo treeInfo = configuration.buildDocumentTree(source);
		Item item = treeInfo.getRootNode();
		DynamicQueryContext dynamicContext = new DynamicQueryContext(configuration);
		dynamicContext.setContextItem(item);
		return dynamicContext;
	}

	private Source getSource(SemanticValidableXPlanArchive archive) {
		InputStream mainFileInputStream = archive.getMainFileInputStream();
		return new StreamSource(mainFileInputStream);
	}

	private List<InvalidFeaturesResult> evaluateXQueryResult(SequenceIterator iterator)
			throws XPathException, ValidatorException {
		Item next;
		MultiKeyMap<String, InvalidFeaturesResult> results = new MultiKeyMap<>();
		while ((next = iterator.next()) != null) {
			if (next instanceof SimpleArrayItem) {
				evaluateWarningErrorResult((SimpleArrayItem) next, results);
			}
			else {
				String resultOrGmlId = next.getStringValue();
				if (resultOrGmlId.equalsIgnoreCase("true"))
					return Collections.emptyList();
				if (resultOrGmlId.equalsIgnoreCase("false"))
					return Collections.singletonList(new InvalidFeaturesResult(message));
				evaluateInvalidGmlIdResult(resultOrGmlId, results);
			}
		}
		return results.values().stream().collect(Collectors.toList());
	}

	private void evaluateInvalidGmlIdResult(String gmlId, MultiKeyMap<String, InvalidFeaturesResult> results) {
		if (results.containsKey(ERROR.name(), message)) {
			results.get(ERROR.name(), message).addGmlId(gmlId);
		}
		else {
			InvalidFeaturesResult invalidRuleResult = new InvalidFeaturesResult(gmlId, message);
			results.put(ERROR.name(), message, invalidRuleResult);
		}
	}

	private void evaluateWarningErrorResult(SimpleArrayItem next, MultiKeyMap<String, InvalidFeaturesResult> results)
			throws ValidatorException, XPathException {
		SimpleArrayItem arrayItem = next;
		if (arrayItem.arrayLength() == 3) {
			String gmlId = asString(arrayItem.get(0));
			ValidationResultType validationResultType = asValidationResultType(arrayItem.get(1));
			String message = asString(arrayItem.get(2));

			if (results.containsKey(validationResultType.name(), message)) {
				results.get(validationResultType.name(), message).addGmlId(gmlId);
			}
			else {
				InvalidFeaturesResult invalidRuleResult = new InvalidFeaturesResult(gmlId, validationResultType,
						message);
				results.put(validationResultType.name(), message, invalidRuleResult);
			}
		}
		else {
			throw new ValidatorException(
					"Semantic validation result array must have exact 3 items. Result array is: " + arrayItem);
		}
	}

	private ValidationResultType asValidationResultType(Sequence sequence) {
		String s = asString(sequence);
		switch (s) {
			case "W":
				return WARNING;
			default:
				return ERROR;
		}
	}

	private String asString(Sequence sequence) {
		if (sequence instanceof Item)
			return ((Item) sequence).getStringValue();
		return sequence.toString();
	}

	private XQueryExpression compileStatement(InputStream statementStream) throws IOException, XPathException {
		final StaticQueryContext staticQueryContext = configuration.newStaticQueryContext();
		return staticQueryContext.compileQuery(statementStream, "UTF-8");
	}

}
