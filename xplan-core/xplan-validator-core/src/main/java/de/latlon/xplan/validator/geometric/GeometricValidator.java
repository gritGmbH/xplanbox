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
    GemetricValidatorParsingResult validateGeometry( XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
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
