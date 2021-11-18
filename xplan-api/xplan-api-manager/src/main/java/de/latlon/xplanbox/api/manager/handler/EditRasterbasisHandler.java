package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.DuplicateRasterbasis;
import de.latlon.xplanbox.api.manager.exception.InvalidRasterbasisId;
import de.latlon.xplanbox.api.manager.v1.model.Rasterbasis;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles editing of Rasterbasis.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditRasterbasisHandler extends EditHandler {

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @return all Rasterbasis of the plan. May be an empty list but not <code>null</code>
     * @throws Exception
     */
    public List<Rasterbasis> retrieveRasterbasis( String planId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        if ( rasterBasis == null )
            return Collections.emptyList();
        List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();

        return rasterReferences.stream().map( rasterReference -> {
            String rasterbasisId = createRasterbasisId( rasterReference );
            return Rasterbasis.fromRasterReference( rasterbasisId, rasterReference );
        } ).collect( Collectors.toList() );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param rasterbasisId
     *                 the id of the Rasterbasis to retrieve, never <code>null</code>
     * @return the Rasterbasis with the passed id of the plan, never <code>null</code>
     * @throws Exception
     */
    public Rasterbasis retrieveRasterbasis( String planId, String rasterbasisId )
                    throws Exception {
        List<Rasterbasis> rasterbasis = retrieveRasterbasis( planId );
        return getRasterbasisById( planId, rasterbasisId, rasterbasis );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param rasterbasisModel
     *                 the Rasterbasis to add, never <code>null</code>
     * @param referenz
     *                 the referenz to add, may be <code>null</code>
     * @param geoReferenz
     *                 the geoReferenz to add, may be <code>null</code>
     * @return the added Rasterbasis, never <code>null</code>
     * @throws Exception
     */
    public Rasterbasis addRasterbasis( String planId, Rasterbasis rasterbasisModel, File referenz, File geoReferenz )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        if ( rasterBasis == null )
            rasterBasis = new RasterBasis();
        RasterReference rasterReferenceToAdd = rasterbasisModel.toRasterReference();
        String newRasterbasisId = createRasterbasisId( rasterReferenceToAdd );
        checkRasterbasisId( planId, rasterbasisModel, newRasterbasisId );

        rasterBasis.getRasterReferences().add( rasterReferenceToAdd );
        List<File> uploadedArtefacts = createUploadedArtefactsList( referenz, geoReferenz );
        manager.editPlan( plan, xPlanToEdit, false, uploadedArtefacts );
        return rasterbasisModel.id( newRasterbasisId );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param rasterbasisId
     *                 the id of the Rasterbasis to replace, never <code>null</code>
     * @param rasterbasisModel
     *                 the Rasterbasis to add, never <code>null</code>
     * @param referenz
     *                 the referenz to add, may be <code>null</code>
     * @param geoReferenz
     *                 the geoReferenz to add, may be <code>null</code>
     * @return the replaced Rasterbasis, never <code>null</code>
     * @throws Exception
     */
    public Rasterbasis replaceRasterbasis( String planId, String rasterbasisId,
                                           Rasterbasis rasterbasisModel, File referenz, File geoReferenz )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        RasterReference rasterReferenceToReplace = getRasterReferenceById( planId, rasterbasisId, rasterBasis );
        RasterReference rasterReferenceToAdd = rasterbasisModel.toRasterReference();
        String newRasterbasisId = createRasterbasisId( rasterReferenceToAdd );
        checkRasterbasisId( planId, rasterbasisId, rasterbasisModel, newRasterbasisId );

        rasterBasis.getRasterReferences().remove( rasterReferenceToReplace );
        rasterBasis.getRasterReferences().add( rasterReferenceToAdd );
        List<File> uploadedArtefacts = createUploadedArtefactsList( referenz, geoReferenz );
        manager.editPlan( plan, xPlanToEdit, false, uploadedArtefacts );
        return rasterbasisModel.id( newRasterbasisId );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param rasterbasisId
     *                 the id of the Rasterbasis to delete, never <code>null</code>
     * @return the deleted Rasterbasis, never <code>null</code>
     * @throws Exception
     */
    public Rasterbasis deleteRasterbasis( String planId, String rasterbasisId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        RasterBasis rasterBasis = xPlanToEdit.getRasterBasis();
        RasterReference rasterReferenceToDelete = getRasterReferenceById( planId, rasterbasisId, rasterBasis );
        rasterBasis.getRasterReferences().remove( rasterReferenceToDelete );
        if ( rasterBasis.getRasterReferences().isEmpty() )
            xPlanToEdit.setRasterBasis( null );
        manager.editPlan( plan, xPlanToEdit, false, Collections.emptyList() );
        return Rasterbasis.fromRasterReference( rasterbasisId, rasterReferenceToDelete );
    }

    private RasterReference getRasterReferenceById( String planId, String rasterbasisId,
                                                    RasterBasis rasterBasis )
                    throws InvalidRasterbasisId {
        List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
        if ( rasterReferences == null ) {
            throw new InvalidRasterbasisId( planId, rasterbasisId );
        }
        List<RasterReference> rasterReferencesById = rasterReferences.stream().filter(
                        rasterReference -> rasterbasisId.equals( createRasterbasisId( rasterReference ) ) ).collect(
                        Collectors.toList() );
        if ( rasterReferencesById.size() != 1 ) {
            throw new InvalidRasterbasisId( planId, rasterbasisId );
        }
        return rasterReferencesById.get( 0 );
    }

    private Rasterbasis getRasterbasisById( String planId, String rasterbasisId, List<Rasterbasis> rasterbasis )
                    throws InvalidRasterbasisId {
        List<Rasterbasis> rasterbasisWithId = rasterbasis.stream().filter(
                        rb -> rasterbasisId.equals( rb.getId() ) ).collect( Collectors.toList() );
        if ( rasterbasisWithId.size() != 1 ) {
            throw new InvalidRasterbasisId( planId, rasterbasisId );
        }
        return rasterbasisWithId.get( 0 );
    }

    private List<File> createUploadedArtefactsList( File referenz, File geoReferenz ) {
        List<File> uploadedArtefacts = new ArrayList<>();
        if ( referenz != null )
            uploadedArtefacts.add( referenz );
        if ( geoReferenz != null )
            uploadedArtefacts.add( geoReferenz );
        return uploadedArtefacts;
    }

    private static String createRasterbasisId( RasterReference rasterReference ) {
        StringBuilder id = new StringBuilder();
        if ( rasterReference.getReferenzName() != null )
            id.append( rasterReference.getReferenzName() );
        id.append( '-' );
        if ( rasterReference.getReference() != null )
            id.append( rasterReference.getReference() );
        return id.toString().replaceAll( "[^a-zA-Z0-9\\-_]", "" );
    }

    private void checkRasterbasisId( String planId, Rasterbasis rasterbasisModel, String newRasterbasisId )
                    throws Exception {
        List<Rasterbasis> rasterbasis = retrieveRasterbasis( planId );
        long noOfRasterbasisWithNewId = rasterbasis.stream().filter(
                        rb -> newRasterbasisId.equals( rb.getId() ) ).count();
        if ( noOfRasterbasisWithNewId > 0 ) {
            throw new DuplicateRasterbasis( planId, newRasterbasisId, rasterbasisModel.getReferenzName(),
                                            rasterbasisModel.getReferenzURL() );
        }
    }

    private void checkRasterbasisId( String planId, String rasterbasisId, Rasterbasis rasterbasisModel,
                                     String newRasterbasisId )
                    throws Exception {
        if ( rasterbasisId.equals( newRasterbasisId ) ) {
            return;
        }
        checkRasterbasisId( planId, rasterbasisModel, newRasterbasisId );
    }
}
