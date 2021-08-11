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
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import net.sf.saxon.Configuration;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.om.TreeInfo;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;
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
import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;

/**
 * Semantically validation rule, based on XQuery.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorRule implements SemanticValidatorRule {

    private static final Logger LOG = LoggerFactory.getLogger( XQuerySemanticValidatorRule.class );

    private static final String UNKNOWN_GML_ID = "unbekannt";

    private final Configuration configuration = new Configuration();

    private final XQueryExpression expression;

    private final String name;

    private XPlanVersion version;

    private SemanticValidationOptions ignoredOption;

    public XQuerySemanticValidatorRule( InputStream statementStream, String name, XPlanVersion version,
                                        SemanticValidationOptions validationOption ) throws IOException, XPathException {
        this.version = version;
        this.ignoredOption = validationOption;
        this.expression = compileStatement( statementStream );
        this.name = name;
    }

    @Override
    public List<String> validate( SemanticValidableXPlanArchive archive )
                            throws ValidatorException {
        final Properties props = createProperties();
        try (Writer writer = new StringWriter()) {
            final DynamicQueryContext dynamicContext = createDynamicQueryContext( archive );
            expression.run( dynamicContext, new StreamResult( writer ), props );
            final SequenceIterator iterator = expression.iterator( dynamicContext );
            List<String> resultList = getResultFromIterator( iterator );
            return evaluateXQueryResult( resultList );
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

    private List<String> evaluateXQueryResult( List<String> resultList ) {
        if ( resultList.size() == 1 ) {
            String result = resultList.get( 0 );
            if ( result.equalsIgnoreCase( "true" ) )
                return Collections.emptyList();
            if ( result.equalsIgnoreCase( "false" ) )
                return Collections.singletonList( UNKNOWN_GML_ID );
        }
        return resultList;
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
        return new StreamSource( mainFileInputStream  );
    }

    private List<String> getResultFromIterator( SequenceIterator iterator )
                            throws XPathException {
        Item next;
        List<String> res = new ArrayList<>();
        while ( ( next = iterator.next() ) != null )
            res.add( next.getStringValue() );
        return res;
    }

    private XQueryExpression compileStatement( InputStream statementStream )
                            throws IOException, XPathException {
        final StaticQueryContext staticQueryContext = configuration.newStaticQueryContext();
        return staticQueryContext.compileQuery( statementStream, "UTF-8" );
    }

}
