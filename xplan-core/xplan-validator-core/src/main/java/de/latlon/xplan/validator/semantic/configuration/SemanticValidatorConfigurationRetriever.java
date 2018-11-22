package de.latlon.xplan.validator.semantic.configuration;

import net.sf.saxon.trans.XPathException;

import java.io.IOException;

/**
 * Create <link>SemanticValidatorConfiguration</link> from arbitrary sources
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public interface SemanticValidatorConfigurationRetriever {

    /**
     * Retrieves the configuration created by this retriever
     *
     * @return the ready configuration
     * @throws IOException
     * @throws XPathException
     */
    SemanticValidatorConfiguration retrieveConfiguration() throws IOException, XPathException;
}
