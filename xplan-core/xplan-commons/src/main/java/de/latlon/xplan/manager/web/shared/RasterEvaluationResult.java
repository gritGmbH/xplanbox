//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.shared;

/**
 * Encapsulates results of the raster evaluation.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class RasterEvaluationResult {

    private String rasterName;

    private String rasterCrs;

    private String rasterConfigurationCrs;

    private boolean crsSet;

    private boolean configuredCrs;

    private boolean supportedImageFormat;

    public RasterEvaluationResult() {
    }

    /**
     * @param rasterName
     *            name of the raster file, never <code>null</code>
     * @param rasterCrs
     *            crs of the raster file, may be <code>null</code> if none is set
     * @param rasterConfigurationCrs
     *            crs of the raster configuration, may be <code>null</code> if none is set
     * @param isCrsSet
     *            true if a crs is set in the rasterfile, false otherwise
     * @param isConfiguredCrs
     *            true if the crs of the raster file is the same as the crs in the configuration, false if not or the
     *            crs is <code>null</code>
     * @param supportedImageFormat
     *            true if the image format is supported, false otherwise
     */
    public RasterEvaluationResult( String rasterName, String rasterCrs, String rasterConfigurationCrs,
                                   boolean isCrsSet, boolean isConfiguredCrs, boolean supportedImageFormat ) {
        this.rasterName = rasterName;
        this.rasterCrs = rasterCrs;
        this.rasterConfigurationCrs = rasterConfigurationCrs;
        this.crsSet = isCrsSet;
        this.configuredCrs = isConfiguredCrs;
        this.supportedImageFormat = supportedImageFormat;
    }

    /**
     * @return the name of the raster file, never <code>null</code>
     */
    public String getRasterName() {
        return rasterName;
    }

    /**
     * @param rasterName
     *            name of the raster file, never <code>null</code>
     */
    public void setRasterName( String rasterName ) {
        this.rasterName = rasterName;
    }

    /**
     * @return crs of the raster file, may be <code>null</code> if none is set
     */
    public String getRasterCrs() {
        return rasterCrs;
    }

    /**
     * @param rasterCrs
     *            crs of the raster file, may be <code>null</code>
     */
    public void setRasterCrs( String rasterCrs ) {
        this.rasterCrs = rasterCrs;
    }

    /**
     * @return crs of the raster configuration, may be <code>null</code> if none is set
     */
    public String getRasterConfigurationCrs() {
        return rasterConfigurationCrs;
    }

    /**
     * @param rasterConfigurationCrs
     *            crs of the raster configuration, may be <code>null</code>
     */
    public void setRasterConfigurationCrs( String rasterConfigurationCrs ) {
        this.rasterConfigurationCrs = rasterConfigurationCrs;
    }

    /**
     * @param crsSet
     *            true if a crs is set in the rasterfile, false otherwise
     */
    public void setCrsSet( boolean crsSet ) {
        this.crsSet = crsSet;
    }

    /**
     * @return the true if a crs is set in the rasterfile, false otherwise
     */
    public boolean isCrsSet() {
        return crsSet;
    }

    /**
     * @param configuredCrs
     *            true if the crs of the raster file is the same as the crs in the configuration, false if not or the
     *            crs is <code>null</code>
     */
    public void setConfiguredCrs( boolean configuredCrs ) {
        this.configuredCrs = configuredCrs;
    }

    /**
     * @return the true if the crs of the raster file is the same as the crs in the configuration, false if not or the
     *         crs is <code>null</code>
     */
    public boolean isConfiguredCrs() {
        return configuredCrs;
    }

    /**
     * @return true if the image format is supported, false otherwise
     */
    public boolean isSupportedImageFormat() {
        return supportedImageFormat;
    }

    /**
     * @param supportedImageFormat
     *            true if the image format is supported, false otherwise
     */
    public void setSupportedImageFormat( boolean supportedImageFormat ) {
        this.supportedImageFormat = supportedImageFormat;
    }

    @Override
    public String toString() {
        return "RasterEvaluationResult [rasterName=" + rasterName + ", rasterCrs=" + rasterCrs
               + ", rasterConfigurationCrs=" + rasterConfigurationCrs + ", crsSet=" + crsSet + ", configuredCrs="
               + configuredCrs + ", supportedImageFormat=" + supportedImageFormat + "]";
    }

}