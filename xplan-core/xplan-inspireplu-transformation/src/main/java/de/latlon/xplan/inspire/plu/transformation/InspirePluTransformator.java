package de.latlon.xplan.inspire.plu.transformation;

import de.latlon.xplan.commons.XPlanVersion;

import java.nio.file.Path;

/**
 * Transformation from XPlan GML to INSPIRE PLU
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface InspirePluTransformator {

    /**
     * Transforms the passed XPlan GML to INSPIRE PLU
     * 
     * @param xPlanGml
     *            the XPlan GML document to transform. never <code>null</code>
     * @param xPlanVersion
     *            the Version of the XPlan GML to transform. never <code>null</code>
     * @return the transformed document, never <code>null</code>
     *
     * @throws TransformationException
     *             if the transformation failed
     */
    Path transformToPlu( Path xPlanGml, XPlanVersion xPlanVersion )
                            throws TransformationException;

}
