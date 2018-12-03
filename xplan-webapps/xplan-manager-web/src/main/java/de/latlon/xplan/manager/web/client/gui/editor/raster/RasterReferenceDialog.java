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

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;

/**
 * Dialog to edit an existing or create a new {@link RasterReference}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class RasterReferenceDialog extends EditDialogBoxWithRasterUpload {

    private final TypeCodeListBox<RasterReferenceType> refType = new TypeCodeListBox<RasterReferenceType>(
                    RasterReferenceType.class );

    private final RasterReference originalRasterReference;

    public RasterReferenceDialog( EditVersion version ) {
        this( version, null, MESSAGES.editCaptionRasterBasisDialogNew() );
    }

    public RasterReferenceDialog( EditVersion version, RasterReference rasterReference ) {
        this( version, rasterReference, MESSAGES.editCaptionRasterBasisDialogEdit() );
    }

    public RasterReferenceDialog( EditVersion version, RasterReference rasterReference, String title ) {
        super( version, title );
        this.originalRasterReference = rasterReference;
        initDialog( createFormContent() );
        setRasterReferenceValues();
    }

    @Override
    protected boolean isGeoreferenceUrlMandatory() {
        return false;
    }

    public RasterReference getEditedRasterReference() {
        RasterReference rasterReference;
        if ( originalRasterReference != null )
            rasterReference = new RasterReference( originalRasterReference );
        else
            rasterReference = new RasterReference();
        rasterReference.setReference( reference.getFilename() );
        rasterReference.setGeoReference( georeference.getFilename() );
        rasterReference.setType( refType.getValueAsEnum() );
        return rasterReference;
    }

    private Widget createFormContent() {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_LEFT );

        layout.setWidget( 1, 1, new Label( MESSAGES.editCaptionRasterBasisReference() ) );
        layout.setWidget( 1, 2, reference );
        if ( !XPLAN_3.equals( version ) ) {
            layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionRasterBasisGeoReference() ) );
            layout.setWidget( 2, 2, georeference );
        }
        layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionRasterBasisType() ) );
        layout.setWidget( 3, 2, refType );
        return layout;
    }

    private void setRasterReferenceValues() {
        if ( originalRasterReference != null ) {
            reference.setNameOfExistingFile( originalRasterReference.getReference() );
            georeference.setNameOfExistingFile( originalRasterReference.getGeoReference() );
            refType.selectItem( originalRasterReference.getType() );
        }
    }

}