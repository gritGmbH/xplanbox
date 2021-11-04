package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.InvalidTextId;
import de.latlon.xplanbox.api.manager.v1.model.Text;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles editing of Text.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditTextHandler extends EditHandler {

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @return all Text of the plan. May be an empty list but not <code>null</code>
     * @throws Exception
     */
    public List<Text> retrieveTexte( String planId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();

        return texts.stream().map( text -> {
            String textId = createTextId( text );
            return Text.fromText( textId, text );
        } ).collect( Collectors.toList() );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param textId
     *                 the id of the Text to retrieve, never <code>null</code>
     * @return the Text with the passed id of the plan, never <code>null</code>
     * @throws Exception
     */
    public Text retrieveText( String planId, String textId )
                    throws Exception {
        List<Text> texte = retrieveTexte( planId );
        return getTextById( planId, textId, texte );
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param textModel
     *                 the Text to add, never <code>null</code>
     * @param file
     *                 the file to add, may be <code>null</code>
     * @return the added Text, never <code>null</code>
     * @throws Exception
     */
    public Text addText( String planId, Text textModel, File file )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();
        de.latlon.xplan.manager.web.shared.edit.Text textToAdd = textModel.toText();
        texts.add( textToAdd );

        List<File> uploadedArtefacts = file != null ? Collections.singletonList( file ) : Collections.emptyList();
        manager.editPlan( plan, xPlanToEdit, false, uploadedArtefacts );
        return textModel;
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param textId
     *                 the id of the Text to replace, never <code>null</code>
     * @param textModel
     *                 the Text to add, never <code>null</code>
     * @param file
     *                 the file to add, may be <code>null</code>
     * @return the replaced Text, never <code>null</code>
     * @throws Exception
     */
    public Text replaceText( String planId, String textId,
                             Text textModel, File file )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();
        de.latlon.xplan.manager.web.shared.edit.Text textToReplace = getOldTextById( planId, textId, texts );
        texts.remove( textToReplace );
        de.latlon.xplan.manager.web.shared.edit.Text textToAdd = textModel.toText();
        texts.add( textToAdd );
        List<File> uploadedArtefacts = file != null ? Collections.singletonList( file ) : Collections.emptyList();
        manager.editPlan( plan, xPlanToEdit, false, uploadedArtefacts );
        return textModel;
    }

    /**
     * @param planId
     *                 the ID of the plan, never <code>null</code>
     * @param textId
     *                 the id of the Text to delete, never <code>null</code>
     * @return the deleted Text, never <code>null</code>
     * @throws Exception
     */
    public Text deleteText( String planId, String textId )
                    throws Exception {
        XPlan plan = findPlanById( planId );
        XPlanToEdit xPlanToEdit = manager.getXPlanToEdit( plan );
        List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();
        de.latlon.xplan.manager.web.shared.edit.Text text = getOldTextById( planId, textId, texts );
        texts.remove( text );
        manager.editPlan( plan, xPlanToEdit, false, Collections.emptyList() );
        return Text.fromText( textId, text );
    }

    private de.latlon.xplan.manager.web.shared.edit.Text getOldTextById( String planId, String textId,
                                                                         List<de.latlon.xplan.manager.web.shared.edit.Text> texts )
                    throws InvalidTextId {
        List<de.latlon.xplan.manager.web.shared.edit.Text> textsById = texts.stream().filter(
                        text -> textId.equals( createTextId( text ) ) ).collect(
                        Collectors.toList() );
        if ( textsById.size() != 1 ) {
            throw new InvalidTextId( planId, textId );
        }
        return textsById.get( 0 );
    }

    private Text getTextById( String planId, String textId, List<Text> texte )
                    throws InvalidTextId {
        List<Text> texteWithId = texte.stream().filter(
                        text -> textId.equals( text.getId() ) ).collect( Collectors.toList() );
        if ( texteWithId.size() != 1 ) {
            throw new InvalidTextId( planId, textId );
        }
        return texteWithId.get( 0 );
    }

    private static String createTextId( de.latlon.xplan.manager.web.shared.edit.Text text ) {
        return text.getFeatureId();
    }
}
