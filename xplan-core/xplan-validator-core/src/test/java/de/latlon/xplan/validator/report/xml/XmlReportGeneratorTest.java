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
package de.latlon.xplan.validator.report.xml;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;
import org.xmlunit.matchers.EvaluateXPathMatcher;
import org.xmlunit.matchers.HasXPathMatcher;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XmlReportGeneratorTest {

    private static final String PLAN_NAME = "PLAN_NAME";

    private static final String VALIDATION_NAME = "VALIDATION_NAME";

    @Test
    public void testGenerateXmlReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReport(), os );
        String xml = os.toString();

        assertThat( xml , EvaluateXPathMatcher.hasXPath( "/ValidationReport/name", equalTo( VALIDATION_NAME ) ) );
        assertThat( xml , EvaluateXPathMatcher.hasXPath( "/ValidationReport/Plan/name", equalTo( PLAN_NAME ) ) );
        assertThat( xml , HasXPathMatcher.hasXPath( "/ValidationReport/Validation" ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSyntacticDetailsHint()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSyntacticDetailHint(), os );
        String xml = os.toString();

        assertThat( xml, EvaluateXPathMatcher.hasXPath( "/ValidationReport/Validation/Syn/details", equalTo( "detailsHint" ) ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSemanticsResults()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSemanticFailures(), os );
        String xml = os.toString();

        assertThat( xml, EvaluateXPathMatcher.hasXPath( "/ValidationReport/Validation/Sem/result",
                                                        equalTo( "nicht valide" ) ) );
        assertThat( xml, EvaluateXPathMatcher.hasXPath( "count(/ValidationReport/Validation/Sem/Rules/Rule)",
                                                        equalTo( "2" ) ) );
        assertThat( xml,
                    EvaluateXPathMatcher.hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[1]/name",
                                                   equalTo( "1.1" ) ) );
        assertThat( xml,
                    EvaluateXPathMatcher.hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[2]/name",
                                                   equalTo( "1.2" ) ) );
    }

    @Test
    public void testGenerateXmlReport_OrderOfValidations()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithAllTypes(), os );
        String xml = os.toString();

        assertThat( xml, HasXPathMatcher.hasXPath( "/ValidationReport/Validation/Sem" ) );
        assertThat( xml, HasXPathMatcher.hasXPath( "/ValidationReport/Validation/*[local-name()= 'Sem']" ) );
        assertThat( xml,
                    EvaluateXPathMatcher.hasXPath(
                                    "count(/ValidationReport/Validation/*[local-name()= 'Sem']/preceding-sibling::*)+1.",
                                    equalTo( "1" ) ) );
        assertThat( xml,
                    EvaluateXPathMatcher.hasXPath(
                                    "count(/ValidationReport/Validation/*[local-name()= 'Geom']/preceding-sibling::*)+1.",
                                    equalTo( "2" ) ) );
        assertThat( xml,
                    EvaluateXPathMatcher.hasXPath(
                                    "count(/ValidationReport/Validation/*[local-name()= 'Syn']/preceding-sibling::*)+1.",
                                    equalTo( "3" ) ) );
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( null, new ByteArrayOutputStream(  ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullOutputStream()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( createValidatorReport(), null );
    }

    private ValidatorReport createValidatorReport() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( PLAN_NAME );
        validatorReport.setValidationName( VALIDATION_NAME );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithSyntacticDetailHint() {
        ValidatorReport validatorReport = createValidatorReport();
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( messages, false, detail );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithSemanticFailures() {
        ValidatorReport validatorReport = createValidatorReport();
        SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
        semanticValidatorResult.addRule( "1.1", "Test valid", Collections.emptyList() );
        semanticValidatorResult.addRule( "1.2", "Test in valid", Collections.singletonList( "id_12" ) );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithAllTypes() {
        ValidatorReport validatorReport = createValidatorReport();
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( Collections.emptyList(), true,
                                                                                          null );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );

        SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
        semanticValidatorResult.addRule( "1.1", "Test valid", Collections.emptyList() );
        semanticValidatorResult.addRule( "1.2", "Test in valid", Collections.singletonList( "id_12" ) );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );

        GeometricValidatorResult geometricValidatorResult = new GeometricValidatorResult( SYNTAX_ERRORS );
        validatorReport.setGeometricValidatorResult( geometricValidatorResult );
        return validatorReport;
    }

}
