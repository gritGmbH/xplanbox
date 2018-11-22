package de.latlon.xplan.inspire.plu.transformation.hale;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.TransformationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleCliInspirePluTransformator implements InspirePluTransformator {

    private static final Logger LOG = LoggerFactory.getLogger( HaleCliInspirePluTransformator.class );

    public static final String PATH_TO_HALE_PROJECT_41 = "xplan41/xplanGml41-inspirePlu.halex";

    public static final String PATH_TO_HALE_PROJECT_50 = "xplan50/xplanGml50-inspirePlu.halex";

    private final String haleCli;

    private final Path haleProjectDirectory;

    public HaleCliInspirePluTransformator( String haleCli, Path haleProjectDirectory ) {
        this.haleCli = haleCli;
        this.haleProjectDirectory = haleProjectDirectory;
    }

    @Override
    public Path transformToPlu( Path xPlanGml, XPlanVersion xPlanVersion )
                    throws TransformationException {
        try {
            String haleProject = findHaleProject( xPlanVersion );
            Path targetFile = Files.createTempFile( "inspirePlu", ".xml" );
            String command = buildCommand( xPlanGml, targetFile, haleProject );
            LOG.info( "Execute the following command to transform the plan to INSPIRE PLU: {}", command );
            Process process = Runtime.getRuntime().exec( command );
            process.waitFor();
            return targetFile;
        } catch ( IOException | InterruptedException e ) {
            LOG.error( "Could not transform to INSPIRE PLU", e );
            throw new TransformationException( "Could not transform to INSPIRE PLU", e );
        }
    }

    private String buildCommand( Path source, Path target, String haleProject ) {
        StringBuilder sb = new StringBuilder();
        sb.append( haleCli );
        sb.append( " transform" );
        sb.append( " -project " + haleProject );
        sb.append( " -source " + source.toString() );
        sb.append( " -target " + target.toString() );
        sb.append( " -providerId eu.esdihumboldt.hale.io.gml.writer" );
        return sb.toString();
    }

    private String findHaleProject( XPlanVersion xPlanVersion )
                    throws TransformationException {
        switch ( xPlanVersion ) {
        case XPLAN_41:
            return resolvePathToHale( xPlanVersion, PATH_TO_HALE_PROJECT_41 );
        case XPLAN_50:
            return resolvePathToHale( xPlanVersion, PATH_TO_HALE_PROJECT_50 );
        default:
            throw new TransformationException(
                            "Could not find HALE project from XPlan GML " + xPlanVersion.name() + " to INSPIRE PLU" );
        }
    }

    private String resolvePathToHale( XPlanVersion xPlanVersion, String haleProjectPath )
                    throws TransformationException {
        System.out.println( haleProjectPath );
        Path haleProject = haleProjectDirectory.resolve( haleProjectPath );
        System.out.println( haleProject );
        if ( Files.exists( haleProject ) )
            return haleProject.toString();
        throw new TransformationException(
                        "Could not find HALE project from XPlan GML " + xPlanVersion.name() + " to INSPIRE PLU" );
    }

}
