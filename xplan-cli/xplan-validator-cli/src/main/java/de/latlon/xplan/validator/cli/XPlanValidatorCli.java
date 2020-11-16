/*-
 * #%L
 * xplan-validator-cli - Kommandozeilentool des XPlan Validators
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
package de.latlon.xplan.validator.cli;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.cli.options.CliOptions;
import de.latlon.xplan.validator.cli.options.CliOptionsParser;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.web.shared.ValidationSettings;

/**
 * Command line tool to read zip files and a validation argument and validate the input files in the zip file
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanValidatorCli {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanValidatorCli.class );

    /**
     * Main Method.
     * 
     * @param args
     *            expects the options -validate and -option with String arguments
     */
    public static void main( String[] args ) {
        try {
            validate( args );
        } catch ( Exception e ) {
            LOG.error( "Error while performing validation: {}", e.getMessage() );
            LOG.debug( "Exception: ", e );
        }
    }

    /**
     * Parses CLI arguments and writes output as well as artifacts (html, xml, pdf)
     * 
     * @param args
     *            expects the options, never <code>null</code> -validate (file path for file to validate), -option
     *            (option of the validation) and -vo (validation options) with String arguments. Optional parameter: -xq
     *            (xquery file path)
     *            never <code>null</code>
     * @throws ReportGenerationException
     */
    static void validate( String[] args )
                    throws Exception {
        CliOptions options = new CliOptionsParser().parse( args );
        ValidationSettings settings = new ValidationSettings( options.getValidationName(), options.getValidationTypes(),
                        options.getVoOptions() );
        XPlanValidator validator = createValidator();
        validator.validate( settings, options.getArchive(), options.getArchive().getName() );
    }

    private static XPlanValidator createValidator() {
        ApplicationContext context = loadApplicationContext();
        return context.getBean( XPlanValidator.class );
    }

    /**
     * Loads the Spring context.
     * 
     * @return the readily instantiated spring context
     */
    private static AnnotationConfigApplicationContext loadApplicationContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register( XPlanValidatorCliSpringConfig.class );
        context.refresh();
        return context;
    }

}
