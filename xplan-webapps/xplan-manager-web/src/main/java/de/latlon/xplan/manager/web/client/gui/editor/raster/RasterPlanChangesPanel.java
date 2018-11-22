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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.CaptionPanel;
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
 * Panel for raster plan changes.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class RasterPlanChangesPanel extends CaptionPanel {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final TypeCodelistProvider CODELIST_PROVIDER = new TypeCodelistProvider();

    private final ListDataProvider<RasterReferenceWithFeatureId> rasterPlanChangesProvider = new ListDataProvider<RasterReferenceWithFeatureId>();

    private final CellTable<RasterReferenceWithFeatureId> rasterPlanChangesTable;

    private EditVersion version;

    public RasterPlanChangesPanel( EditVersion version ) {
        this.version = version;
        setCaptionText( MESSAGES.editCaptionRasterPlanChanges() );
        this.rasterPlanChangesTable = createTable();
        add( createRasterPlanChangesList() );
    }

    public void setRasterPlanChanges( List<RasterWithReferences> rasterPlanChanges ) {
        List<RasterReferenceWithFeatureId> rasterReferences = collectRasterReferences( rasterPlanChanges );
        rasterPlanChangesTable.setRowCount( rasterReferences.size(), true );
        List<RasterReferenceWithFeatureId> providedChanges = rasterPlanChangesProvider.getList();
        providedChanges.clear();
        providedChanges.addAll( rasterReferences );

    }

    public List<RasterWithReferences> retrieveRasterPlanChanges() {
        return collectRasterPlanChanges();
    }

    private Widget createRasterPlanChangesList() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( rasterPlanChangesTable );
        return panel;
    }

    private CellTable<RasterReferenceWithFeatureId> createTable() {
        CellTable<RasterReferenceWithFeatureId> rasterPlanChangesList = new CellTable<RasterReferenceWithFeatureId>();
        initColumns( rasterPlanChangesList );
        rasterPlanChangesProvider.addDataDisplay( rasterPlanChangesList );
        return rasterPlanChangesList;
    }

    private void initColumns( CellTable<RasterReferenceWithFeatureId> rasterPlanChangesList ) {
        addReferenceColumn( rasterPlanChangesList );
        if ( !XPLAN_3.equals( version ) )
            addGeoReferenceColumn( rasterPlanChangesList );
        addTypeColumn( rasterPlanChangesList );

        TextHeader actionHeader = new TextHeader( MESSAGES.actions() );
        addEditColumn( rasterPlanChangesList, actionHeader );
    }

    private void addReferenceColumn( CellTable<RasterReferenceWithFeatureId> table ) {
        TextColumn<RasterReferenceWithFeatureId> referenceColumn = new TextColumn<RasterReferenceWithFeatureId>() {
            @Override
            public String getValue( RasterReferenceWithFeatureId rasterPlanChangeData ) {
                return rasterPlanChangeData.rasterReference.getReference();
            }
        };
        referenceColumn.setCellStyleNames( "editRasterPlanChangesColumn referenceColumn" );
        table.addColumn( referenceColumn, MESSAGES.editCaptionRasterPlanChangesReference() );
    }

    private void addGeoReferenceColumn( CellTable<RasterReferenceWithFeatureId> table ) {
        TextColumn<RasterReferenceWithFeatureId> geoReferenceColumn = new TextColumn<RasterReferenceWithFeatureId>() {
            @Override
            public String getValue( RasterReferenceWithFeatureId rasterPlanChangeData ) {
                return rasterPlanChangeData.rasterReference.getGeoReference();
            }
        };
        geoReferenceColumn.setCellStyleNames( "editRasterPlanChangesColumn geoReferenceColumn" );
        table.addColumn( geoReferenceColumn, MESSAGES.editCaptionRasterPlanChangesGeoReference() );
    }

    private void addTypeColumn( CellTable<RasterReferenceWithFeatureId> table ) {
        TextColumn<RasterReferenceWithFeatureId> typeColumn = new TextColumn<RasterReferenceWithFeatureId>() {
            @Override
            public String getValue( RasterReferenceWithFeatureId rasterPlanChangeData ) {
                if ( rasterPlanChangeData != null )
                    return CODELIST_PROVIDER.translate( RasterReferenceType.class,
                                                        rasterPlanChangeData.rasterReference.getType() );
                return "";
            }
        };
        typeColumn.setCellStyleNames( "editRasterPlanChangesColumn typeColumn" );
        table.addColumn( typeColumn, MESSAGES.editCaptionRasterPlanChangesType() );
    }

    private void addEditColumn( final CellTable<RasterReferenceWithFeatureId> table, TextHeader columnHeader ) {
        ButtonCell editButtonCell = new ButtonCell();
        final Column<RasterReferenceWithFeatureId, String> editButtonColumn = new Column<RasterReferenceWithFeatureId, String>(
                        editButtonCell) {
            @Override
            public String getValue( RasterReferenceWithFeatureId object ) {
                return "";
            }
        };
        editButtonColumn.setFieldUpdater( new FieldUpdater<RasterReferenceWithFeatureId, String>() {
            public void update( final int index, final RasterReferenceWithFeatureId rasterReferenceWithFeatureId,
                                String value ) {
                String dialogTitle = MESSAGES.editCaptionRasterPlanChangesDialogEdit();
                final RasterReference rasterReferenceToEdit = rasterReferenceWithFeatureId.rasterReference;
                final RasterReferenceDialog rasterReferenceDialog = new RasterReferenceDialog( version, dialogTitle,
                                rasterReferenceToEdit );
                rasterReferenceDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        RasterReference editedRasterReference = rasterReferenceDialog.getEditedRasterReference();
                        rasterReferenceToEdit.setReference( editedRasterReference.getReference() );
                        rasterReferenceToEdit.setGeoReference( editedRasterReference.getGeoReference() );
                        rasterReferenceToEdit.setType( editedRasterReference.getType() );
                        rasterReferenceDialog.hide();
                        rasterPlanChangesTable.redrawRow( index );
                    }
                } );
                rasterReferenceDialog.center();
                rasterReferenceDialog.show();
            }
        } );
        editButtonColumn.setCellStyleNames( "editRasterPlanChangesColumn editButtonColumn" );
        table.addColumn( editButtonColumn, columnHeader );
    }

    private List<RasterReferenceWithFeatureId> collectRasterReferences( List<RasterWithReferences> rasterPlanChanges ) {
        List<RasterReferenceWithFeatureId> rasterReferences = new ArrayList<RasterReferenceWithFeatureId>();
        for ( RasterWithReferences rasterPlanChange : rasterPlanChanges ) {
            String featureId = rasterPlanChange.getFeatureId();
            for ( RasterReference rasterPlanChangeReferences : rasterPlanChange.getRasterReferences() ) {
                rasterReferences.add( new RasterReferenceWithFeatureId( featureId, rasterPlanChangeReferences ) );
            }
        }
        return rasterReferences;
    }

    private List<RasterWithReferences> collectRasterPlanChanges() {
        List<RasterWithReferences> rasterPlanChanges = new ArrayList<RasterWithReferences>();
        List<RasterReferenceWithFeatureId> rasterReferencesWithFeatureId = rasterPlanChangesProvider.getList();
        for ( RasterReferenceWithFeatureId rasterReferenceWithFeatureId : rasterReferencesWithFeatureId ) {
            String featureId = rasterReferenceWithFeatureId.featureId;
            RasterReference rasterReference = rasterReferenceWithFeatureId.rasterReference;
            RasterWithReferences rasterPlanChange = retrieveRasterPlanChangeById( rasterPlanChanges, featureId );
            if ( rasterPlanChange != null ) {
                rasterPlanChange.addRasterReference( rasterReference );
            } else {
                RasterWithReferences newRasterWithReferences = new RasterWithReferences( featureId );
                newRasterWithReferences.addRasterReference( rasterReference );
                rasterPlanChanges.add( newRasterWithReferences );
            }

        }
        return rasterPlanChanges;
    }

    private RasterWithReferences retrieveRasterPlanChangeById( List<RasterWithReferences> rasterPlanChanges,
                                                               String featureId ) {
        for ( RasterWithReferences rasterPlanChange : rasterPlanChanges ) {
            if ( featureId != null && featureId.equals( rasterPlanChange.getFeatureId() ) )
                return rasterPlanChange;
        }
        return null;
    }

    private class RasterReferenceWithFeatureId {
        private String featureId;

        private RasterReference rasterReference;

        private RasterReferenceWithFeatureId( String featureId, RasterReference rasterReference ) {
            this.featureId = featureId;
            this.rasterReference = rasterReference;
        }
    }

}