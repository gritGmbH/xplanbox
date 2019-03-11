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

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;

import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.gui.editor.AbstractEditorSubPanelWithTable;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.shared.edit.Text;

/**
 * Panel for texts.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class TextsPanel extends AbstractEditorSubPanelWithTable<Text> {

    /**
     * @param version
     *            of the plan to edit, never <code>null</code>
     */
    public TextsPanel( EditVersion version ) {
        super( version, MESSAGES.editCaptionTexts() );
        add( createGui() );
    }

    @Override
    protected void initColumns( CellTable<Text> textsList ) {
        addKeyColumn( textsList );
        addBasisColumn( textsList );
        addTextColumn( textsList );
        addReferenceColumn( textsList );
        // #3305 - georeference is not needed.
        // if ( !XPLAN_3.equals( version ) )
        // addGeoReferenceColumn( textsList );

        TextHeader actionHeader = new TextHeader( MESSAGES.actions() );
        addEditColumn( textsList, actionHeader );
        addRemoveColumn( textsList, actionHeader );
    }

    private Widget createGui() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( getTable() );
        panel.add( createNewButton() );
        return panel;
    }

    private Button createNewButton() {
        Button newButton = new Button( MESSAGES.editCaptionNewText(), new ClickHandler() {
            public void onClick( ClickEvent event ) {
                final TextDialog textDialog = new TextDialog( version );
                textDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        Text newText = textDialog.getEditedText();
                        List<Text> texts = getValues();
                        texts.add( newText );
                        textDialog.hide();
                    }
                } );
                textDialog.center();
                textDialog.show();
            }
        } );
        return newButton;
    }

    private void addKeyColumn( CellTable<Text> table ) {
        TextColumn<Text> keyColumn = new TextColumn<Text>() {
            @Override
            public String getValue( Text textData ) {
                return shortText( textData.getKey() );
            }
        };
        keyColumn.setCellStyleNames( "editTextsColumn keyColumn" );
        table.addColumn( keyColumn, MESSAGES.editCaptionTextsKey() );
    }

    private void addBasisColumn( CellTable<Text> table ) {
        TextColumn<Text> basisColumn = new TextColumn<Text>() {
            @Override
            public String getValue( Text textData ) {
                return shortText( textData.getBasis() );
            }
        };
        basisColumn.setCellStyleNames( "editTextsColumn basisColumn" );
        table.addColumn( basisColumn, MESSAGES.editCaptionTextsBasis() );
    }

    private void addTextColumn( CellTable<Text> table ) {
        TextColumn<Text> textColumn = new TextColumn<Text>() {
            @Override
            public String getValue( Text textData ) {
                return shortText( textData.getText() );
            }
        };
        textColumn.setCellStyleNames( "editTextsColumn textColumn" );
        table.addColumn( textColumn, MESSAGES.editCaptionTextsText() );
    }

    private void addReferenceColumn( CellTable<Text> table ) {
        TextColumn<Text> referenceColumn = new TextColumn<Text>() {
            @Override
            public String getValue( Text textData ) {
                return textData.getReference();
            }
        };
        referenceColumn.setCellStyleNames( "editTextsColumn referenceColumn" );
        table.addColumn( referenceColumn, MESSAGES.editCaptionTextsReference() );
    }

    // #3305 - georeference is not needed.
    // private void addGeoReferenceColumn( CellTable<Text> table ) {
    // TextColumn<Text> geoReferenceColumn = new TextColumn<Text>() {
    // @Override
    // public String getValue( Text textData ) {
    // return textData.getGeoReference();
    // }
    // };
    // geoReferenceColumn.setCellStyleNames( "editTextsColumn geoReferenceColumn" );
    // table.addColumn( geoReferenceColumn, MESSAGES.editCaptionTextsGeoReference() );
    // }

    private void addEditColumn( final CellTable<Text> table, TextHeader columnHeader ) {
        ButtonCell editButtonCell = new ButtonCell();
        final Column<Text, String> editButtonColumn = new Column<Text, String>( editButtonCell) {
            @Override
            public String getValue( Text object ) {
                return "";
            }
        };
        editButtonColumn.setFieldUpdater( new FieldUpdater<Text, String>() {
            public void update( final int index, Text text, String value ) {
                final TextDialog textDialog = new TextDialog( version, text );
                textDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        Text editedtext = textDialog.getEditedText();
                        List<Text> texts = getValues();
                        texts.remove( index );
                        texts.add( index, editedtext );
                        textDialog.hide();
                    }
                } );
                textDialog.center();
                textDialog.show();
            }
        } );
        editButtonColumn.setCellStyleNames( "editTextsColumn editButtonColumn" );
        table.addColumn( editButtonColumn, columnHeader );
    }

    private void addRemoveColumn( final CellTable<Text> table, TextHeader columnHeader ) {
        ButtonCell downloadButtonCell = new ButtonCell();
        final Column<Text, String> removeButtonColumn = new Column<Text, String>( downloadButtonCell) {
            @Override
            public String getValue( Text object ) {
                return "";
            }
        };
        removeButtonColumn.setFieldUpdater( new FieldUpdater<Text, String>() {
            public void update( int index, Text object, String value ) {
                List<Text> texts = getValues();
                texts.remove( index );
            }
        } );
        removeButtonColumn.setCellStyleNames( "editTextsColumn removeButtonColumn" );
        table.addColumn( removeButtonColumn, columnHeader );
    }

}