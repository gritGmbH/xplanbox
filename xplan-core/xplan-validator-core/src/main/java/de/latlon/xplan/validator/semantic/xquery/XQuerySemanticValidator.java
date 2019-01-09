package de.latlon.xplan.validator.semantic.xquery;

import static java.lang.String.format;

import java.io.IOException;
import java.util.List;

import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.RulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import net.sf.saxon.trans.XPathException;

/**
 * Validates <link>XPlanArchives</link> semantically using XQuery The file path must be set and the validator be built
 * before usage
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidator implements SemanticValidator {

    private static final Logger LOG = LoggerFactory.getLogger( XQuerySemanticValidator.class );

    private static final String DETAILS_HINT = "Details zu den angewendeten Regeln können in folgenden Dokument nachgeschlagen werden: %s";

    private final SemanticValidatorConfiguration semanticValidatorConfiguration;

    private final SemanticConformityLinkConfiguration semanticConformityLinkConfiguration;

    /**
     * Builds the validator.
     * 
     * @param retriever
     *            retrieves the configuration for this validator
     * @throws ValidatorException
     */
    public XQuerySemanticValidator( XQuerySemanticValidatorConfigurationRetriever retriever ) throws ValidatorException {
        this( retriever, null );
    }

    /**
     * Builds the validator.
     * 
     * @param retriever
     *            retrieves the configuration for this validator
     * @param semanticConformityLinkConfiguration,
     *            encapsulates the configuration of the semantic validator links, may be <code>null</code>
     * @throws ValidatorException
     */
    public XQuerySemanticValidator( XQuerySemanticValidatorConfigurationRetriever retriever,
                                    SemanticConformityLinkConfiguration semanticConformityLinkConfiguration ) throws ValidatorException {
        this.semanticConformityLinkConfiguration = semanticConformityLinkConfiguration;
        try {
            semanticValidatorConfiguration = retriever.retrieveConfiguration();
        } catch ( XPathException | IOException e ) {
            LOG.error( format( "Could not instantiate semantic validator. Reason: %s", e.getMessage() ) );
            throw new ValidatorException( "Could not create validator.", e );
        }
    }

    @Override
    public ValidatorResult validateSemantic( SemanticValidableXPlanArchive archive,
                                             List<SemanticValidationOptions> semanticValidationOptions ) {
        checkParameters( archive, semanticValidationOptions );
        ValidatorDetail detail = createDetail( archive );
        SemanticValidatorResult validatorResult = new SemanticValidatorResult( detail );
        boolean isArchiveValid = true;
        List<SemanticValidatorRule> rulesToApply = retrieveRulesToApply( archive, semanticValidationOptions );
        LOG.info( "Number of rules to apply: {}", rulesToApply.size() );
        for ( SemanticValidatorRule semanticValidatorRule : rulesToApply ) {
            boolean isThisRuleValid = validateRule( archive, validatorResult, semanticValidatorRule );
            isArchiveValid = isArchiveValid && isThisRuleValid;
            LOG.debug( format( "Rule %s is %s valid", semanticValidatorRule.getName(),
                               !isThisRuleValid ? "not" : "" ) );
        }
        validatorResult.setValid( isArchiveValid );
        return validatorResult;
    }

    private List<SemanticValidatorRule>
                    retrieveRulesToApply( SemanticValidableXPlanArchive archive,
                                          List<SemanticValidationOptions> semanticValidationOptions ) {
        XPlanVersion version = archive.getVersion();
        LOG.debug( "Find all rules for version {} and options {}.", version, semanticValidationOptions );
        return semanticValidatorConfiguration.getRules( version, semanticValidationOptions );
    }

    private boolean validateRule( SemanticValidableXPlanArchive archive, SemanticValidatorResult result,
                                  SemanticValidatorRule semanticValidatorRule ) {
        boolean isThisRuleValid = false;
        String name = semanticValidatorRule.getName();
        try {
            isThisRuleValid = semanticValidatorRule.validate( archive );
            XPlanVersion version = semanticValidatorRule.getXPlanVersion();
            String message = RulesMessagesAccessor.retrieveMessageForRule( name, version );
            result.addRule( name, isThisRuleValid, message );
        } catch ( ValidatorException e ) {
            LOG.error( "Error while semantically validating validation rule " + name + ", reason:" + e.getMessage() );
            LOG.debug( "Exception: ", e );
        }
        return isThisRuleValid;
    }

    private ValidatorDetail createDetail( SemanticValidableXPlanArchive archive ) {
        if ( semanticConformityLinkConfiguration != null ) {
            String link = semanticConformityLinkConfiguration.retrieveLink( archive.getVersion() );
            if ( link != null && !"".equals( link ) ) {
                return new ValidatorDetail( DETAILS_HINT, link );
            }
        }
        return null;
    }

    private void checkParameters( SemanticValidableXPlanArchive archive, List<SemanticValidationOptions> semanticValidationOptions ) {
        if ( archive == null )
            throw new IllegalArgumentException( "archive must not be null" );
        if ( semanticValidationOptions == null )
            throw new IllegalArgumentException( "options must not be null" );
    }

}
