/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
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

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.AbstractEditorSubPanelWithTable;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.TypeCodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.client.gui.widget.Validable;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

import java.util.Collections;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.StyleNames.EDITOR_VALIDATION_ERROR;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;

/**
 * Panel for raster basis.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class RasterBasisPanel extends AbstractEditorSubPanelWithTable<RasterReference> implements Validable {

    private static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    private static final TypeCodelistProvider CODELIST_PROVIDER = new TypeCodelistProvider();

    private RasterBasis rasterBasis;

    public RasterBasisPanel( EditVersion version ) {
        super( version, MESSAGES.editCaptionRasterBasis() );
    }

    @Override
    protected void initColumns( CellTable<RasterReference> rasterBasisList ) {
        addTypeColumn( rasterBasisList );
        addReferenceColumn( rasterBasisList );
        addReferenceNameColumn( rasterBasisList );
        if ( !XPLAN_3.equals( version ) ) {
            addGeoReferenceColumn( rasterBasisList );
        }
        TextHeader actionHeader = new TextHeader( MESSAGES.actions() );
        addEditColumn( rasterBasisList, actionHeader );
        addRemoveColumn( rasterBasisList, actionHeader );
    }

    @Override
    public boolean isValid() {
        return validate();
    }

    public void setRasterBasis( XPlanToEdit xPlanToEdit ) {
        if ( xPlanToEdit.isHasBereich() ) {
            add( createGui() );
            this.rasterBasis = xPlanToEdit.getRasterBasis();
            List<RasterReference> rasterBasisReferences = collectRasterReferences( this.rasterBasis );
            setValues( rasterBasisReferences );
        } else {
            add( createDisabledHint() );
        }
    }

    public RasterBasis retrieveRasterBasis() {
        List<RasterReference> values = getValues();
        if ( values.isEmpty() )
            return null;
        if ( rasterBasis == null ) {
            rasterBasis = new RasterBasis();
        }
        rasterBasis.setRasterReferences( values );
        return rasterBasis;
    }

    private boolean validate() {
        RasterBasis rasterBasis = retrieveRasterBasis();
        if ( rasterBasis == null || containsRasterReferenceOfType( SCAN ) ) {
            removeStyleName( EDITOR_VALIDATION_ERROR );
            setTitle( "" );
            return true;
        }
        addStyleName( EDITOR_VALIDATION_ERROR );
        setTitle( MESSAGES.editCaptionRasterBasisInvalid() );
        return false;
    }

    private Widget createDisabledHint() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( new Label( MESSAGES.editCaptionRasterBasisDisabled() ) );
        return panel;
    }

    private Widget createGui() {
        VerticalPanel panel = new VerticalPanel();
        panel.setSpacing( 5 );
        panel.setHorizontalAlignment( ALIGN_CENTER );
        panel.add( getTable() );
        panel.add( createNewButton() );
        return panel;
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

    private void addReferenceNameColumn( CellTable<RasterReference> table ) {
        TextColumn<RasterReference> referenceColumn = new TextColumn<RasterReference>() {
            @Override
            public String getValue( RasterReference rasterBasisData ) {
                return rasterBasisData.getReferenzName();
            }
        };
        referenceColumn.setCellStyleNames( "editRasterReferenceColumn referenceColumn" );
        table.addColumn( referenceColumn, MESSAGES.editCaptionRasterBasisReferenzName() );
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

    private Button createNewButton() {
        Button newButton = new Button( MESSAGES.editCaptionNewRasterBasis(), new ClickHandler() {
            public void onClick( ClickEvent event ) {
                final RasterReferenceDialog rasterReferenceDialog = new RasterReferenceDialog( version );
                rasterReferenceDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        RasterReference newRasterReference = rasterReferenceDialog.getEditedRasterReference();
                        List<RasterReference> rasterReferences = getValues();
                        rasterReferences.add( newRasterReference );
                        rasterReferenceDialog.hide();
                        validate();
                    }
                } );
                rasterReferenceDialog.center();
                rasterReferenceDialog.show();
            }
        } );
        return newButton;
    }

    private void addEditColumn( final CellTable<RasterReference> table, TextHeader columnHeader ) {
        ButtonCell editButtonCell = new ButtonCell();
        final Column<RasterReference, String> editButtonColumn = new Column<RasterReference, String>( editButtonCell ) {
            @Override
            public String getValue( RasterReference object ) {
                return "";
            }
        };
        editButtonColumn.setFieldUpdater( new FieldUpdater<RasterReference, String>() {
            public void update( final int index, final RasterReference rasterReference, String value ) {
                final RasterReferenceDialog rasterReferenceDialog = new RasterReferenceDialog( version, rasterReference );
                rasterReferenceDialog.addSaveHandler( new SavedHandler() {
                    @Override
                    public void changesSaved() {
                        RasterReference editedRasterReference = rasterReferenceDialog.getEditedRasterReference();
                        rasterReference.setReference( editedRasterReference.getReference() );
                        rasterReference.setGeoReference( editedRasterReference.getGeoReference() );
                        rasterReference.setType( editedRasterReference.getType() );
                        rasterReference.setReferenzMimeType( editedRasterReference.getReferenzMimeType() );
                        rasterReference.setGeorefMimeType( editedRasterReference.getGeorefMimeType() );
                        rasterReference.setArt( editedRasterReference.getArt() );
                        rasterReference.setBeschreibung( editedRasterReference.getBeschreibung() );
                        rasterReference.setDatum( editedRasterReference.getDatum() );
                        rasterReference.setInformationssystemURL( editedRasterReference.getInformationssystemURL() );
                        rasterReference.setReferenzName( editedRasterReference.getReferenzName() );
                        rasterReferenceDialog.hide();
                        table.redrawRow( index );
                        validate();
                    }
                } );
                rasterReferenceDialog.center();
                rasterReferenceDialog.show();
            }
        } );
        editButtonColumn.setCellStyleNames( "editRasterBaseColumn editButtonColumn" );
        table.addColumn( editButtonColumn, columnHeader );
    }

    private void addRemoveColumn( final CellTable<RasterReference> table, TextHeader columnHeader ) {
        ButtonCell removeButtonCell = new ButtonCell();
        final Column<RasterReference, String> removeButtonColumn = new Column<RasterReference, String>(
                        removeButtonCell ) {
            @Override
            public String getValue( RasterReference object ) {
                return "";
            }
        };
        removeButtonColumn.setFieldUpdater( new FieldUpdater<RasterReference, String>() {
            public void update( int index, RasterReference object, String value ) {
                List<RasterReference> rasterReferences = getValues();
                rasterReferences.remove( index );
                validate();
            }
        } );
        removeButtonColumn.setCellStyleNames( "editRasterBaseColumn removeButtonColumn" );
        table.addColumn( removeButtonColumn, columnHeader );
    }

    private List<RasterReference> collectRasterReferences( RasterBasis rasterBasis ) {
        if ( rasterBasis != null )
            return rasterBasis.getRasterReferences();
        return Collections.emptyList();
    }

    private boolean containsRasterReferenceOfType( RasterReferenceType referenceType ) {
        RasterBasis rasterBasis = retrieveRasterBasis();
        if ( rasterBasis != null ) {
            List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
            for ( RasterReference rasterReference : rasterReferences ) {
                if ( referenceType.equals( rasterReference.getType() ) )
                    return true;
            }
        }
        return false;
    }
}
