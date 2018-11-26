package de.latlon.xplan.validator.geometric;

/**
 * Interface tests for {@link de.latlon.xplan.validator.geometric.GeometricValidatorImpl}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class GeometricValidatorImplInterfaceTest extends GeometricValidatorAbstractTest {

    @Override
    protected GeometricValidator createGeometricValidator() {
        return new GeometricValidatorImpl();
    }

}
