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
package de.latlon.xplan.manager.web.client.gui.editor.text;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.Text;

/**
 * Dialog to edit an existing or create a new {@link Text}
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class TextDialog extends EditDialogBoxWithRasterUpload {

    private final TextBox key = createTextInput();

    private final TextArea text = createTextAreaInput();

    private final TextBox basis = createTextInput();

    private final Text textToEdit;

    /**
     * Instantiates a {@link TextDialog} to edit an existing {@link Change}
     * 
     * @param textToEdit
     *            the text to edit, should not <code>null</code> (a new change is created)
     */
    public TextDialog( EditVersion version, Text textToEdit ) {
        this( version, textToEdit, MESSAGES.editCaptionTextsDialogEdit() );
    }

    /**
     * Instantiates a {@link TextDialog} to create a new {@link Change}
     */
    public TextDialog( EditVersion version ) {
        this( version, null, MESSAGES.editCaptionTextsDialogNew() );
    }

    /**
     * @return the actual edited {@link Text}, may be <code>null</code>
     */
    public Text getEditedText() {
        Text editedText = new Text();
        if ( textToEdit != null )
            editedText.setFeatureId( textToEdit.getFeatureId() );
        editedText.setKey( key.getValue() );
        editedText.setBasis( basis.getValue() );
        editedText.setText( text.getValue() );
        editedText.setReference( reference.getFilename() );
        editedText.setGeoReference( georeference.getFilename() );
        return editedText;
    }

    @Override
    protected boolean isReferenceUrlMandatory() {
        return false;
    }

    private TextDialog( EditVersion version, Text textToEdit, String title ) {
        super( version, title );
        this.textToEdit = textToEdit;
        initDialog( createFormContent() );
        setText( textToEdit );
    }

    private FlexTable createFormContent() {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 3, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 4, 1, ALIGN_LEFT );

        layout.setWidget( 1, 1, new Label( MESSAGES.editCaptionTextsKey() ) );
        layout.setWidget( 1, 2, key );
        layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionTextsBasis() ) );
        layout.setWidget( 2, 2, basis );
        layout.setWidget( 3, 1, new Label( MESSAGES.editCaptionTextsText() ) );
        layout.setWidget( 3, 2, text );
        layout.setWidget( 4, 1, new Label( MESSAGES.editCaptionTextsReference() ) );
        layout.setWidget( 4, 2, reference );
        // #3305 - georeference is not needed.
        // if ( !XPLAN_3.equals( version ) ) {
        // layout.setWidget( 5, 1, new Label( MESSAGES.editCaptionTextsGeoReference() ) );
        // layout.setWidget( 5, 2, georeference );
        // }
        return layout;
    }

    private void setText( Text textToSet ) {
        if ( textToSet != null ) {
            key.setText( textToSet.getKey() );
            basis.setText( textToSet.getBasis() );
            text.setText( textToSet.getText() );
            reference.setNameOfExistingFile( textToSet.getReference() );
            georeference.setNameOfExistingFile( textToSet.getGeoReference() );
        }
    }

}