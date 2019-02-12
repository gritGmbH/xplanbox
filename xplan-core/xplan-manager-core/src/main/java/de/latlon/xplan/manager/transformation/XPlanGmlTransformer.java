package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.hale.TransformationException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlTransformer {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanGmlTransformer.class );

    private final HaleXplan41ToXplan51Transformer xplan41ToXplan51Transformer;

    /**
     * @param haleXplan41ToXplan51Transformer
     *                 the underlying transformer used for the transformation between XPlanGML 4.1 and XPlanGML 5.1, never <code>null</code>
     */
    public XPlanGmlTransformer( HaleXplan41ToXplan51Transformer haleXplan41ToXplan51Transformer ) {
        this.xplan41ToXplan51Transformer = haleXplan41ToXplan51Transformer;
    }

    /**
     * Transforms the XPLanGML in the passed {@link XPlanArchive} to an XPlanGML 5.1 {@link XPlanFeatureCollection} if the archive is XPlanVersion 4.1.
     *
     * @param archive
     *                 to transform, never <code>null</code>
     * @return the {@link TransformationResult} or <code>null</code> if the version of the source is not 4.1
     * @throws TransformationException
     *                 if the transformation failed
     */
    public TransformationResult transform( XPlanArchive archive )
                    throws TransformationException {
        if ( XPLAN_41.equals( archive.getVersion() ) ) {
            LOG.info( "Transform XPlanGML 4.1 to XPlanGml 5.1" );
            try ( InputStream xplanGml = archive.getMainFileInputStream() ) {
                Path source = storeAsTmpFile( xplanGml );
                LOG.debug( "Source file (XPlanGML 4.1) of the transformation: " + source );
                Path target = xplan41ToXplan51Transformer.transformToXPlanGml51( source );
                LOG.debug( "Transformed XPlanGML 5.1 : " + target );
                return new TransformationResult( Files.readAllBytes( target ), XPLAN_51 );
            } catch ( IOException e ) {
                LOG.error( "Could not transform plan to XPlanGML 5.1", e );
                throw new TransformationException( "Could not transform plan to XPlanGML 5.1", e );
            }
        }
        return null;
    }

    private Path storeAsTmpFile( InputStream sourceStream )
                    throws IOException {
        Path sourceFile = Files.createTempFile( "xplanGmlSource_", ".xml" );
        try ( FileOutputStream sourceFileStream = new FileOutputStream( sourceFile.toFile() ) ) {
            IOUtils.copy( sourceStream, sourceFileStream );
            sourceStream.close();
        }
        return sourceFile;
    }

}