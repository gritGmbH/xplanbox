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
package de.latlon.xplan.validator.report.badgeometryimg;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.rendering.r2d.Renderer;
import org.deegree.rendering.r2d.context.LazyImageRenderContext;
import org.deegree.rendering.r2d.context.MapOptions;
import org.deegree.rendering.r2d.context.RenderContext;
import org.deegree.rendering.r2d.context.RenderingInfo;
import org.deegree.style.styling.LineStyling;
import org.deegree.style.styling.PointStyling;
import org.deegree.style.styling.PolygonStyling;
import org.deegree.style.styling.components.Fill;
import org.deegree.style.styling.components.Stroke;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates a PNG showing bad geometries from a {@link ValidatorReport}
 *
 * @author Florian Bingel
 */
public class BadGeometryImgGenerator {


    private static final Logger LOG = LoggerFactory.getLogger( BadGeometryImgGenerator.class );

    private static final String IMAGE_TYPE = "image/png";

    /**
     * Checks if the report contains some bad geometries to write in the image
     *
     * @param report
     *            to check for bad geometries, never <code>null</code>
     * @return <code>true</code> if the report contains {@link BadGeometry}s, <code>false</code> otherwise
     */
    public boolean hasBadGeometry( ValidatorReport report ) {
        GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
        if ( geometricValidatorResult != null ) {
            return !geometricValidatorResult.getBadGeometries().isEmpty();
        }
        return false;
    }

    /**
     * Writes an image with {@link BadGeometry}s into the passed {@link OutputStream}.
     *
     * @param report
     *            to retrieve bad geometries from, never <code>null</code>
     * @param os
     *            the OutputStream where the Image is written into, never <code>null</code>
     * @throws ReportGenerationException
     *             if an exception occurred during writing the image
     */
    public void generateReport( ValidatorReport report, OutputStream os )
                            throws ReportGenerationException {

        GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
        if ( geometricValidatorResult != null ) {
            writePNGImage( os, geometricValidatorResult.getBadGeometries() );
        }
    }

    private void writePNGImage( OutputStream os, List<BadGeometry> badGeometries )
                            throws ReportGenerationException {
        if ( badGeometries.isEmpty() )
            return;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PointStyling defaultPointStyle = createPointStyle();
            LineStyling defaultLineStyle = createLineStyle();
            PolygonStyling defaultPolygonStyle = createPolygonStyle();

            Envelope boundingBox = calculateBoundingBoxOfBadGeometries( badGeometries );
            RenderContext ctx = createRenderContext( baos, boundingBox );
            renderBadGeometries( badGeometries, defaultPointStyle, defaultLineStyle, defaultPolygonStyle, ctx );

            ctx.close();

            writeToOutputStream( os, baos );
        } catch ( IOException e ) {
            throw new ReportGenerationException( "PNG with bad geometries could not be written!", e );
        }

    }

    private void renderBadGeometries( List<BadGeometry> badGeometries, PointStyling defaultPointStyle,
                                      LineStyling defaultLineStyle, PolygonStyling defaultPolygonStyle,
                                      RenderContext ctx ) {
        Renderer renderer = ctx.getVectorRenderer();
        for ( BadGeometry badGeometry : badGeometries ) {
            renderGeometry( defaultPointStyle, defaultLineStyle, defaultPolygonStyle, renderer,
                            badGeometry.getOriginalGeometry() );
            badGeometry.getMarkerGeometries().values().forEach(
                                    g -> renderGeometry( defaultPointStyle, defaultLineStyle, defaultPolygonStyle,
                                                         renderer, g ) );
        }
    }

    private void renderGeometry( PointStyling defaultPointStyle, LineStyling defaultLineStyle,
                                 PolygonStyling defaultPolygonStyle, Renderer renderer, Geometry geom ) {
        try {
            if ( !Geometry.GeometryType.PRIMITIVE_GEOMETRY.equals( geom.getGeometryType() ) ) {
                LOG.warn( "Geometry is not primitive, rendering of non primitive geometries in png is currently not supported " );
                return;
            }
            GeometricPrimitive primitiveGeom = (GeometricPrimitive) geom;
            switch ( primitiveGeom.getPrimitiveType() ) {
            case Point:
                renderer.render( defaultPointStyle, geom );
                break;
            case Curve:
                renderer.render( defaultLineStyle, geom );
                break;
            case Surface:
                renderer.render( defaultPolygonStyle, geom );
                break;
            case Solid:
                break;
            default:
                LOG.warn( "Geometry type " + primitiveGeom.getPrimitiveType()
                          + " is not supported to be rendered in png." );
            }
        } catch ( Exception e ) {
            LOG.warn( "Geometry is broken (could not be rendered): " + e.getMessage() );
            LOG.trace( "Geometry is broken (could not be rendered).", e );
        }
    }

    private RenderContext createRenderContext( ByteArrayOutputStream baos, Envelope boundingBox ) {
        RenderingInfo info = createRenderingInfo( boundingBox );
        RenderContext ctx = new LazyImageRenderContext( info, baos );
        // Let the renderer do its best
        ctx.applyOptions( new MapOptions( MapOptions.Quality.HIGH, MapOptions.Interpolation.BICUBIC,
                                          MapOptions.Antialias.IMAGE, -1, 0 ) );
        return ctx;
    }

    private RenderingInfo createRenderingInfo( Envelope boundingBox ) {
        // calc pixel-size of the image
        double longestImageSide = 1000.0;
        double spanX = boundingBox.getSpan0();
        double spanY = boundingBox.getSpan1();

        double imageWidth = longestImageSide;
        double imageHeight = longestImageSide;

        if ( spanX > spanY )
            imageHeight = longestImageSide * ( spanY / spanX );
        else
            imageWidth = longestImageSide * ( spanX / spanY );

        Map<String, String> parameters = new HashMap<>();
        RenderingInfo info = new RenderingInfo( IMAGE_TYPE, (int) imageWidth, (int) imageHeight, false,
                                                java.awt.Color.white, boundingBox, 0.00028, parameters );
        return info;
    }

    private void writeToOutputStream( OutputStream os, ByteArrayOutputStream baos )
                            throws IOException {
        for ( byte b : baos.toByteArray() ) {
            os.write( b );
        }
    }

    private PointStyling createPointStyle() {
        PointStyling defaultPointStyle = new PointStyling();
        return defaultPointStyle;
    }

    private LineStyling createLineStyle() {
        LineStyling defaultLineStyle = new LineStyling();
        defaultLineStyle.stroke = new Stroke();
        defaultLineStyle.stroke.color = Color.red;
        return defaultLineStyle;
    }

    private PolygonStyling createPolygonStyle() {
        PolygonStyling defaultPolygonStyle = new PolygonStyling();

        defaultPolygonStyle.fill = new Fill();
        defaultPolygonStyle.fill.color = new Color( 255, 128, 128 );
        defaultPolygonStyle.stroke = new Stroke();
        defaultPolygonStyle.stroke.color = Color.red;
        return defaultPolygonStyle;
    }

    private Envelope calculateBoundingBoxOfBadGeometries( List<BadGeometry> badGeometries ) {
        // the envelope has to be bigger than the geometry, otherwise an exception will occur
        Envelope boundingBox = badGeometries.get( 0 ).getOriginalGeometry().getEnvelope();
        for ( BadGeometry badGeometry : badGeometries ) {
            try {
                Geometry geom = badGeometry.getOriginalGeometry();
                Envelope envelope = geom.getEnvelope();
                boundingBox = boundingBox.merge(envelope);
            } catch ( Exception e ){
                LOG.warn( "Geometry is broken (could not be used to calculate the bounding box " +
                        "of all broken geometries): " +  e.getMessage() );
                LOG.trace( "Geometry is broken (could not be used to calculate the bounding box " +
                        "of all broken geometries).", e );
            }
        }
        return boundingBox;
    }

}
