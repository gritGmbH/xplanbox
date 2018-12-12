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
package de.latlon.xplan.manager.web.client.gui.editor.dialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Encapsulates a {@link Label} to show the currently existing file and a {@link FileUpload} to allow the upload of a
 * new file replacing the existing one.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class PreserveExistingFileUpload extends VerticalPanel implements Validable {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private final FileUpload selectedFile;

    private final Button removeButton;

    private final boolean isMandatory;

    private final Label existingFile = new Label();

    /**
     * @param nameOfTheFileUploadField
     *            the name of the {@link FileUpload} element used to upload a new file, never <code>null</code>
     * @param isMandatory
     *            <code>true</code> if a delete button should be added to remove a selected file, <code>false</code>
     *            otherwise
     */
    public PreserveExistingFileUpload( String nameOfTheFileUploadField, boolean isMandatory ) {
        this.isMandatory = isMandatory;
        this.selectedFile = createFileUpload( nameOfTheFileUploadField );
        removeButton = createRemoveButton();
        initPanel();
    }

    /**
     * @param nameOfExistingFile
     *            the name of the file already existing, may be <code>null</code> if no file exists
     */
    public void setNameOfExistingFile( String nameOfExistingFile ) {
        existingFile.setText( nameOfExistingFile );
    }

    /**
     * @return the name of the file, this may be the one of the existing or selected file for upload, <code>null</code>
     *         if no file exists or is selected
     */
    public String getFilename() {
        return existingFile.getText();
    }

    @Override
    public boolean isValid() {
        if ( isMandatory ) {
            String filename = getFilename();
            return filename != null && filename.length() > 0;
        }
        return true;
    }

    /**
     * @param isEnabled true if enable, false otherwise
     */
    public void setEnabled( boolean isEnabled ) {
        selectedFile.setEnabled( isEnabled );
        removeButton.setEnabled( isEnabled );
    }

    private void initPanel() {
        HorizontalPanel existingPanel = new HorizontalPanel();
        existingPanel.setSpacing( 10 );
        existingPanel.add( new Label( MESSAGES.editCaptionReferencesCurrentFile() ) );
        existingPanel.add( existingFile );
        if ( !isMandatory )
            existingPanel.add( removeButton );
        add( existingPanel );
        add( selectedFile );
    }

    private Button createRemoveButton() {
        Button button = new Button();
        button.setTitle( MESSAGES.editCaptionReferencesRemoveFileTooltip() );
        button.setStyleName( "removeButtonColumn" );
        button.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                existingFile.setText( null );
            }
        } );
        return button;
    }

    private FileUpload createFileUpload( String name ) {
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName( name );
        fileUpload.addChangeHandler( new ChangeHandler() {
            @Override
            public void onChange( ChangeEvent event ) {
                String fileName = retrieveFileName();
                setNameOfExistingFile( fileName );
            }
        } );
        return fileUpload;
    }

    private String retrieveFileName() {
        String fileName = selectedFile.getFilename();
        if ( fileName.contains( "\\" ) )
            return fileName.substring( fileName.lastIndexOf( "\\" ) + 1 );
        if ( fileName.contains( "/" ) )
            return fileName.substring( fileName.lastIndexOf( "/" ) + 1 );
        return fileName;
    }

}