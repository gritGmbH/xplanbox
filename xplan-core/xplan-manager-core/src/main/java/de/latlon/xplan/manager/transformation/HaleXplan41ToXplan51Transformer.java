package de.latlon.xplan.manager.transformation;

import de.latlon.xplan.commons.hale.HaleIOProvider;
import de.latlon.xplan.commons.hale.HaleTransformer;
import de.latlon.xplan.commons.hale.TransformationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleXplan41ToXplan51Transformer {

    private static final String RELATIVE_PATH_TO_PROJEKT = "xplan41nach51/xplanGml41-xplanGml51.halex";

    public static final String XPLAN_ROOT_NAME = "XPlanAuszug";

    private final HaleTransformer haleTransformer;

    private String haleProject;

    public HaleXplan41ToXplan51Transformer( String haleCli, Path haleProjectDirectory ) {
        this.haleProject = resolvePathToHale( haleProjectDirectory );
        this.haleTransformer = new HaleTransformer( haleCli );
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
        HaleIOProvider haleIOProvider = new HaleIOProvider( "eu.esdihumboldt.hale.io.xml.writer" );
        haleIOProvider.addSetting( "xml.rootElement.name", XPLAN_ROOT_NAME );
        haleIOProvider.addSetting( "xml.rootElement.namespace", XPLAN_51.getNamespace() );
        haleTransformer.transform( haleProject, xPlanGml41.toString(), targetFile.toString(), haleIOProvider );
        return targetFile;
    }

    private Path createTargetFile()
                    throws TransformationException {
        try {
            return Files.createTempFile( "xplanGml51From41_", ".xml" );
        } catch ( IOException e ) {
            throw new TransformationException( "Could not create target file", e );
        }
    }

    private String resolvePathToHale( Path haleProjectDirectory ) {
        Path haleProject = haleProjectDirectory.resolve( RELATIVE_PATH_TO_PROJEKT );
        if ( Files.exists( haleProject ) )
            return haleProject.toString();
        throw new IllegalArgumentException(
                        "Could not find HALE project for the transformation from XPlanGML 4.1 to XPlanGML 5.1" );
    }

}