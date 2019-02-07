package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.hale.HaleTransformator;
import de.latlon.xplan.commons.hale.TransformationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleXplan41ToXplan51Transformator {

    private final HaleTransformator haleTransformator;

    private String haleProject;

    public HaleXplan41ToXplan51Transformator( String haleCli, String haleProject ) {
        this.haleProject = haleProject;
        this.haleTransformator = new HaleTransformator( haleCli );
    }

    /**
     * Transformes the passed XPlanGML 4.1 document to XPlanGML 5.1.
     *
     * @param xPlanGml41
     *                 the XPlanGML 4.1 document to transform
     * @return
     * @throws TransformationException
     */
    public Path transformToXPlanGml51( Path xPlanGml41 )
                    throws TransformationException {
        Path targetFile = createTargetFile();
        haleTransformator.transform( haleProject, xPlanGml41.toString(), targetFile.toString() );
        return targetFile;
    }

    private Path createTargetFile()
                    throws TransformationException {
        try {
            return Files.createTempFile( "inspirePlu", ".xml" );
        } catch ( IOException e ) {
            throw new TransformationException( "Could not create target file", e );
        }
    }

}