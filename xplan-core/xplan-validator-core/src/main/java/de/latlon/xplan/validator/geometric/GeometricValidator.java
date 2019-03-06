package de.latlon.xplan.validator.geometric;

import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * Validates <link>XPlanArchives</link> geometrically
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public interface GeometricValidator {

    /**
     * Validate geometrically and return a <link>List</link> of error messages
     * 
     * @param archive
     *            the archive to validate, never <code>null</code>
     * @param crs
     *            the crs to validate against, never <code>null</code>
     * @param schema
     *            the application schema, never <code>null</code>
     * @param force
     *            true if validation shall continue on eror, false if not
     * @param voOptions
     *            options to specify validation, may be empty, but never <code>null</code>
     * @return a <link>ValidatorReport</link> containing the result of the validation
     * @throws ValidatorException
     *             - validation failed
     */
    ValidatorResult validateGeometry( XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
                                      List<ValidationOption> voOptions )
                    throws ValidatorException;

    /**
     * Validate geometrically and return a <link>XPlanFeatureCollection</link>
     * 
     * @param archive
     *            the archive to validate, never <code>null</code>
     * @param crs
     *            the crs to validate against, never <code>null</code>
     * @param schema
     *            the application schema, never <code>null</code>
     * @param force
     *            true if validation shall continue on error, false if not
     * @param internalId
     *            the internalId is added to the feature collection. It represents a property of a *Plan feature (see
     *            schema). If <code>null</code>, internalId property is not added to the feature collection.
     * @return a <link>XPlanFeatureCollection</link> containing all valid features
     * @throws ValidatorException
     *             - validation failed
     */
    XPlanFeatureCollection retrieveGeometricallyValidXPlanFeatures( XPlanArchive archive, ICRS crs, AppSchema schema,
                                                                    boolean force, String internalId )
                    throws XMLStreamException, UnknownCRSException, ValidatorException;
}
