package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

import java.io.Closeable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface TransformationResultWriter extends Closeable {

    void writeResult( String id, String name, SyntacticValidatorResult validatorResult,
                      TransformationResult transformationResult );

}
