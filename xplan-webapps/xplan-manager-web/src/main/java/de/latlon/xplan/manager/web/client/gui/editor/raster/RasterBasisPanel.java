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
package de.latlon.xplan.manager.web.client.gui.editor.raster;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;

import java.util.Collections;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.TypeCodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.RasterWithReferences;

/**
 * Panel for raster basis.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class RasterBasisPanel extends CaptionPanel {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final TypeCodelistProvider CODELIST_PROVIDER = new TypeCodelistProvider();

    private final ListDataProvider<RasterReference> rasterBasisProvider = new ListDataProvider<RasterReference>();

    private RasterWithReferences rasterBasis;

    private EditVersion version;

    public RasterBasisPanel( EditVersion version ) {
        this.version = version;
        setCaptionText( MESSAGES.editCaptionRasterBasis() );
    }

    public void setRasterBasis( RasterWithReferences rasterBasis ) {
        this.rasterBasis = rasterBasis;
        List<RasterReference> rasterBasisReferences = collectRasterReferences( rasterBasis );
        clear();
        if ( rasterBasisReferences.isEmpty() ) {
            add( createNoRasterReferencesHint() );
        } else {
            CellTable<RasterReference> rasterBasisTable = createTable();
            add( createRasterReferenceList( rasterBasisTable ) );
            rasterBasisTable.setRowCount( rasterBasisReferences.size(), true );
            List<RasterReference> providedChanges = rasterBasisProvider.getList();
            providedChanges.clear();
            providedChanges.addAll( rasterBasisReferences );
        }
    }

    public RasterWithReferences retrieveRasterBasis() {
        if ( rasterBasis != null ) {
            rasterBasis.setRasterReferences( rasterBasisProvider.getList() );
            return rasterBasis;
        }
        return null;
    }

    private Widget createRasterReferenceList( CellTable<RasterReference> rasterBasisTable ) {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( rasterBasisTable );
        return panel;
    }

    private Widget createNoRasterReferencesHint() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        Label label = new Label( MESSAGES.editCaptionRasterBasisEmptyHint() );
        panel.add( label );
        return panel;
    }

    private CellTable<RasterReference> createTable() {
        CellTable<RasterReference> rasterBasisList = new CellTable<RasterReference>();
        initColumns( rasterBasisList );
        rasterBasisProvider.addDataDisplay( rasterBasisList );
        return rasterBasisList;
    }

    private void initColumns( CellTable<RasterReference> rasterBasisList ) {
        addReferenceColumn( rasterBasisList );
        if ( !XPLAN_3.equals( version ) )
            addGeoReferenceColumn( rasterBasisList );
        addTypeColumn( rasterBasisList );

        TextHeader actionHeader = new TextHeader( MESSAGES.actions() );
        addEditColumn( rasterBasisList, actionHeader );
    }

    private void addReferenceColumn( CellTable<RasterReference> table ) {
        TextColumn<RasterReference> referenceColumn = new TextColumn<RasterReference>() {
            @Override
            public String getValue( RasterReference rasterBasisData ) {
                return rasterBasisData.getReference();
            }
        };
        referenceColumn.setCellStyleNames( "editRasterReferenceColumn referenceColumn" );
        table.addColumn( referenceColumn, MESSAGES.editCaptionRasterBasisReference() );
    }

    private void addGeoReferenceColumn( CellTable<RasterReference> table ) {
        TextColumn<RasterReference> geoReferenceColumn = new TextColumn<RasterReference>() {
            @Override
            public String getValue( RasterReference rasterBasisData ) {
                return rasterBasisData.getGeoReference();
            }
        };
        geoReferenceColumn.setCellStyleNames( "editRasterReferenceColumn geoReferenceColumn" );
        table.addColumn( geoReferenceColumn, MESSAGES.editCaptionRasterBasisGeoReference() );
    }

    private void addTypeColumn( CellTable<RasterReference> table ) {
        TextColumn<RasterReference> typeColumn = new TextColumn<RasterReference>() {
            @Override
            public String getValue( RasterReference rasterBasisData ) {
                if ( rasterBasisData.getType() != null )
                    return CODELIST_PROVIDER.translate( RasterReferenceType.class, rasterBasisData.getType() );
                return "";
            }
        };
        typeColumn.setCellStyleNames( "editRasterReferenceColumn typeColumn" );
        table.addColumn( typeColumn, MESSAGES.editCaptionRasterBasisType() );
    }

    private void addEditColumn( final CellTable<RasterReference> table, TextHeader columnHeader ) {
        ButtonCell editButtonCell = new ButtonCell();
        final Column<RasterReference, String> editButtonColumn = new Column<RasterReference, String>( editButtonCell) {
            @Override
            public String getValue( RasterReference object ) {
                return "";
            }
        };
        editButtonColumn.setFieldUpdater( new FieldUpdater<RasterReference, String>() {
            public void update( final int index, final RasterReference rasterReference, String value ) {
                String dialogTitle = MESSAGES.editCaptionRasterBasisDialogEdit();
                final RasterReferenceDialog rasterReferenceDialog = new RasterReferenceDialog( version, dialogTitle,
                                rasterReference );
                rasterReferenceDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        RasterReference editedRasterReference = rasterReferenceDialog.getEditedRasterReference();
                        rasterReference.setReference( editedRasterReference.getReference() );
                        rasterReference.setGeoReference( editedRasterReference.getGeoReference() );
                        rasterReference.setType( editedRasterReference.getType() );
                        rasterReferenceDialog.hide();
                        table.redrawRow( index );
                    }
                } );
                rasterReferenceDialog.center();
                rasterReferenceDialog.show();
            }
        } );
        editButtonColumn.setCellStyleNames( "editRasterBaseColumn editButtonColumn" );
        table.addColumn( editButtonColumn, columnHeader );
    }

    private List<RasterReference> collectRasterReferences( RasterWithReferences rasterBasis ) {
        if ( rasterBasis != null )
            return rasterBasis.getRasterReferences();
        return Collections.emptyList();
    }

}