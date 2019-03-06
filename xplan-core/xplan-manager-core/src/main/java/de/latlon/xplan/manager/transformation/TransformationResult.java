package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Encapsulates the result of a transformation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformationResult {

    private final byte[] transformationResult;

    private final XPlanVersion versionOfTheResult;

    /**
     * @param transformationResult
     *                 the transformed XPlanGML, never <code>null</code>
     * @param versionOfTheResult
     *                 the version of the transformed XPlanGML, never <code>null</code>
     */
    public TransformationResult( byte[] transformationResult, XPlanVersion versionOfTheResult ) {
        this.transformationResult = transformationResult;
        this.versionOfTheResult = versionOfTheResult;
    }

    /**
     * @return the transformed XPlanGML, never <code>null</code>
     */
    public byte[] getTransformationResult() {
        return transformationResult;
    }

    /**
     * @return the version of the transformed XPlanGML, never <code>null</code>
     */
    public XPlanVersion getVersionOfTheResult() {
        return versionOfTheResult;
    }

}