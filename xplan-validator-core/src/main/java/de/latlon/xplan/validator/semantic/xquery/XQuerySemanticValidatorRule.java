package de.latlon.xplan.validator.semantic.xquery;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static java.lang.Boolean.parseBoolean;
import static java.lang.String.format;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.stream.StreamResult;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.pull.PullSource;
import net.sf.saxon.pull.StaxBridge;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;

/**
 * Semantically validation rule, based on XQuery.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorRule implements SemanticValidatorRule {

    private static final Logger LOG = LoggerFactory.getLogger( XQuerySemanticValidatorRule.class );

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
    public boolean validate( XPlanArchive archive )
                            throws ValidatorException {
        final Properties props = createProperties();
        boolean validationResult;
        try (Writer writer = new StringWriter()) {
            final DynamicQueryContext dynamicContext = createDynamicQueryContext( archive );
            expression.run( dynamicContext, new StreamResult( writer ), props );
            final SequenceIterator<?> iterator = expression.iterator( dynamicContext );
            List<String> resultList = getResultFromIterator( iterator );
            validationResult = evaluateXQueryResult( resultList );
        } catch ( XPathException | IOException e ) {
            LOG.warn( format( "Could not validate rule %s, reason:%s", this.getName(), e.getMessage() ) );
            LOG.debug( "Exception: ", e );
            throw new ValidatorException( "Rule could not be validated!", e );
        }
        return validationResult;
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

    private boolean evaluateXQueryResult( List<String> resultList )
                            throws ValidatorException {
        if ( resultList.size() > 1 )
            throw new ValidatorException( "XPath Queries resulting in lists with more than one item are not supported!" );
        return parseBoolean( resultList.get( 0 ) );
    }

    private Properties createProperties() {
        final Properties props = new Properties();
        props.setProperty( OutputKeys.METHOD, "text" );
        return props;
    }

    private DynamicQueryContext createDynamicQueryContext( XPlanArchive archive )
                            throws XPathException {
        final Item<NodeInfo> item = configuration.buildDocument( getSource( archive.getMainFileXmlReader() ) );
        final DynamicQueryContext dynamicContext = new DynamicQueryContext( configuration );
        dynamicContext.setContextItem( item );
        return dynamicContext;
    }

    private PullSource getSource( XMLStreamReader resourceToValidate ) {
        StaxBridge bridge = new StaxBridge();
        bridge.setXMLStreamReader( resourceToValidate );
        return new PullSource( bridge );
    }

    private List<String> getResultFromIterator( SequenceIterator<?> iterator )
                            throws XPathException {
        Item<?> next;
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