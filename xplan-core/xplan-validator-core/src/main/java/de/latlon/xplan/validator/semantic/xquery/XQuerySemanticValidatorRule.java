/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.semantic.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.RulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.report.InvalidRuleResult;
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
import net.sf.saxon.value.StringValue;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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

    private static final Logger LOG = LoggerFactory.getLogger( XQuerySemanticValidatorRule.class );

    private final Configuration configuration = new Configuration();

    private final XQueryExpression expression;

    private final String name;

    private XPlanVersion version;

    private SemanticValidationOptions ignoredOption;

    private String defaultMessage;

    public XQuerySemanticValidatorRule( InputStream statementStream, String name, XPlanVersion version,
                                        SemanticValidationOptions validationOption )
                    throws IOException, XPathException {
        this.version = version;
        this.ignoredOption = validationOption;
        this.expression = compileStatement( statementStream );
        this.name = name;
        this.defaultMessage = RulesMessagesAccessor.retrieveMessageForRule( name, version );
    }

    @Override
    public List<InvalidRuleResult> validate( SemanticValidableXPlanArchive archive )
                    throws ValidatorException {
        final Properties props = createProperties();
        try ( Writer writer = new StringWriter() ) {
            final DynamicQueryContext dynamicContext = createDynamicQueryContext( archive );
            expression.run( dynamicContext, new StreamResult( writer ), props );
            final SequenceIterator iterator = expression.iterator( dynamicContext );
            return evaluateXQueryResult( iterator );
        } catch ( XPathException | IOException e ) {
            LOG.warn( format( "Could not validate rule %s, reason:%s", this.getName(), e.getMessage() ) );
            LOG.debug( "Exception: ", e );
            throw new ValidatorException( "Rule could not be validated!", e );
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
    public boolean isIgnoredByOption( SemanticValidationOptions option ) {
        if ( option == null || NONE.equals( option ) )
            return false;
        return ignoredOption != null && ignoredOption.equals( option );
    }

    private Properties createProperties() {
        final Properties props = new Properties();
        props.setProperty( OutputKeys.METHOD, "text" );
        return props;
    }

    private DynamicQueryContext createDynamicQueryContext( SemanticValidableXPlanArchive archive )
                    throws XPathException {
        Source source = getSource( archive );
        TreeInfo treeInfo = configuration.buildDocumentTree( source );
        Item item = treeInfo.getRootNode();
        DynamicQueryContext dynamicContext = new DynamicQueryContext( configuration );
        dynamicContext.setContextItem( item );
        return dynamicContext;
    }

    private Source getSource( SemanticValidableXPlanArchive archive ) {
        InputStream mainFileInputStream = archive.getMainFileInputStream();
        return new StreamSource( mainFileInputStream );
    }

    private List<InvalidRuleResult> evaluateXQueryResult( SequenceIterator iterator )
                    throws XPathException, ValidatorException {
        Item next;
        List<InvalidRuleResult> res = new ArrayList<>();
        while ( ( next = iterator.next() ) != null ) {
            if ( next instanceof SimpleArrayItem ) {
                InvalidRuleResult invalidRuleResult = evaluateWarningErrorResult( (SimpleArrayItem) next );
                res.add( invalidRuleResult );
            } else {
                String resultOrGmlId = next.getStringValue();
                if ( resultOrGmlId.equalsIgnoreCase( "true" ) )
                    return Collections.emptyList();
                if ( resultOrGmlId.equalsIgnoreCase( "false" ) )
                    return Collections.singletonList( new InvalidRuleResult( defaultMessage ) );
                res.add( new InvalidRuleResult( resultOrGmlId, defaultMessage ) );
            }
        }
        return res;
    }

    private InvalidRuleResult evaluateWarningErrorResult( SimpleArrayItem next )
                    throws ValidatorException, XPathException {
        SimpleArrayItem arrayItem = next;
        if ( arrayItem.arrayLength() == 3 ) {
            return new InvalidRuleResult( asString( arrayItem.get( 0 ) ),
                                          asValidationResultType( arrayItem.get( 1 ) ),
                                          asString( arrayItem.get( 2 ) ) );
        } else {
            throw new ValidatorException(
                            "Semantic validation result array must have exact 3 items. Result array is: "
                            + arrayItem );
        }
    }

    private ValidationResultType asValidationResultType( Sequence sequence ) {
        String s = asString( sequence );
        switch ( s ) {
        case "W":
            return WARNING;
        default:
            return ERROR;
        }
    }

    private String asString( Sequence sequence ) {
        if ( sequence instanceof Item )
            return ( (Item) sequence ).getStringValue();
        return sequence.toString();
    }

    private XQueryExpression compileStatement( InputStream statementStream )
                    throws IOException, XPathException {
        final StaticQueryContext staticQueryContext = configuration.newStaticQueryContext();
        return staticQueryContext.compileQuery( statementStream, "UTF-8" );
    }

}
