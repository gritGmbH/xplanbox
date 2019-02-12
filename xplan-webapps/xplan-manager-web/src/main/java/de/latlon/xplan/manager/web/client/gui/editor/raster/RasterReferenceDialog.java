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

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.widget.StrictDateBox;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;

import java.util.ArrayList;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_MSEXCEL;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_MSWORD;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_ODT;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_VND_OGC_GML;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_VND_OGC_SLD_XML;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.APPLICATION_VND_OGC_WMS_XML;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.IMAGE_SVG_XML;
import static de.latlon.xplan.manager.web.shared.edit.MimeTypes.TEXT_PLAIN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static java.util.Collections.singletonList;

/**
 * Dialog to edit an existing or create a new {@link RasterReference}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class RasterReferenceDialog extends EditDialogBoxWithRasterUpload {

    private final TypeCodeListBox<RasterReferenceType> refType;

    private final TypeCodeListBox<MimeTypes> refMimeType;

    private final TypeCodeListBox<MimeTypes> georefMimeType;

    private final TypeCodeListBox<ExterneReferenzArt> artType;

    private final TextBox referenzName = createTextInput();

    private final TextBox informationssystemURL = createTextInput();

    private final TextArea beschreibung = createTextAreaInput();

    private final StrictDateBox datum = createDateInput();

    private final RasterReference originalRasterReference;

    public RasterReferenceDialog( EditVersion version, boolean enableSelectionOfText ) {
        this( version, enableSelectionOfText, null, MESSAGES.editCaptionRasterBasisDialogNew() );
    }

    public RasterReferenceDialog( EditVersion version, boolean enableSelectionOfText,
                                  RasterReference rasterReference ) {
        this( version, enableSelectionOfText, rasterReference, MESSAGES.editCaptionRasterBasisDialogEdit() );
    }

    private RasterReferenceDialog( EditVersion version, boolean disableSelectionOfText, RasterReference rasterReference,
                                   String title ) {
        super( version, title );
        this.refType = createRefType( disableSelectionOfText );
        this.refMimeType = createMimeTypeType( version );
        this.georefMimeType = createMimeTypeType( version );
        this.artType = new TypeCodeListBox<ExterneReferenzArt>( ExterneReferenzArt.class, true );
        this.originalRasterReference = rasterReference;
        addChangeHandlers( version );
        initDialog( createFormContent() );
        setRasterReferenceValues();
    }

    @Override
    protected boolean isGeoreferenceUrlMandatory() {
        return false;
    }

    @Override
    protected boolean isReferenceUrlMandatory() {
        return false;
    }

    @Override
    public boolean isValid() {
        return validate();
    }

    public RasterReference getEditedRasterReference() {
        RasterReference rasterReference;
        if ( originalRasterReference != null )
            rasterReference = new RasterReference( originalRasterReference );
        else
            rasterReference = new RasterReference();
        rasterReference.setType( refType.getValueAsEnum() );
        rasterReference.setReference( reference.getFilename() );
        rasterReference.setReferenzMimeType( refMimeType.getValueAsEnum() );
        rasterReference.setGeoReference( georeference.getFilename() );
        rasterReference.setGeorefMimeType( georefMimeType.getValueAsEnum() );
        rasterReference.setArt( artType.getValueAsEnum() );
        rasterReference.setBeschreibung( beschreibung.getValue() );
        rasterReference.setDatum( datum.getValue() );
        rasterReference.setInformationssystemURL( informationssystemURL.getValue() );
        rasterReference.setReferenzName( referenzName.getValue() );
        return rasterReference;
    }

    private Widget createFormContent() {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_LEFT );

        int rowIndex = 1;
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisType() ) );
        layout.setWidget( rowIndex++, 2, refType );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisReference() ) );
        layout.setWidget( rowIndex++, 2, reference );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisReferenzMimeType() ) );
        layout.setWidget( rowIndex++, 2, refMimeType );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisGeoReference() ) );
        layout.setWidget( rowIndex++, 2, georeference );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisGeorefMimeType() ) );
        layout.setWidget( rowIndex++, 2, georefMimeType );
        if ( !XPLAN_3.equals( version ) ) {
            layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisArt() ) );
            layout.setWidget( rowIndex++, 2, artType );
        }
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisInformationssystemURL() ) );
        layout.setWidget( rowIndex++, 2, informationssystemURL );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisReferenzName() ) );
        layout.setWidget( rowIndex++, 2, referenzName );
        layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisBeschreibung() ) );
        layout.setWidget( rowIndex++, 2, beschreibung );
        if ( !XPLAN_3.equals( version ) ) {
            layout.setWidget( rowIndex, 1, new Label( MESSAGES.editCaptionRasterBasisDatum() ) );
            layout.setWidget( rowIndex++, 2, datum );
        }
        return layout;
    }

    private void addChangeHandlers( EditVersion version ) {
        if ( XPLAN_3.equals( version ) ) {
            refType.addChangeHandler( new ChangeHandler() {
                @Override
                public void onChange( ChangeEvent changeEvent ) {
                    if ( RasterReferenceType.SCAN.equals( refType.getValueAsEnum() ) ) {
                        georeference.setEnabled( true );
                        georefMimeType.setEnabled( true );
                    } else {
                        georeference.setEnabled( false );
                        georefMimeType.setEnabled( false );
                        georeference.setTitle( MESSAGES.editUnsupportedPropertyRefType() );
                        georefMimeType.setTitle( MESSAGES.editUnsupportedPropertyRefType() );
                    }
                }
            } );
        }
        artType.addChangeHandler( new ClearValidationErrorsCH() );
        georeference.addChangeHandler( new ClearValidationErrorsCH() );
        georefMimeType.addChangeHandler( new ClearValidationErrorsCH() );
        referenzName.addValueChangeHandler( new ClearValidationErrorsVCH<String>() );
        reference.addChangeHandler( new ClearValidationErrorsCH() );
    }

    private void setRasterReferenceValues() {
        if ( originalRasterReference != null ) {
            refType.selectItem( originalRasterReference.getType() );
            reference.setNameOfExistingFile( originalRasterReference.getReference() );
            refMimeType.selectItem( originalRasterReference.getReferenzMimeType() );
            georeference.setNameOfExistingFile( originalRasterReference.getGeoReference() );
            georefMimeType.selectItem( originalRasterReference.getGeorefMimeType() );
            artType.selectItem( originalRasterReference.getArt() );
            referenzName.setValue( originalRasterReference.getReferenzName() );
            informationssystemURL.setValue( originalRasterReference.getInformationssystemURL() );
            beschreibung.setValue( originalRasterReference.getBeschreibung() );
            datum.setValue( originalRasterReference.getDatum() );
        }
    }

    private TypeCodeListBox<RasterReferenceType> createRefType( boolean disableSelectionOfText ) {
        TypeCodeListBox<RasterReferenceType> codeListBox;
        if ( disableSelectionOfText ) {
            codeListBox = new TypeCodeListBox<RasterReferenceType>( RasterReferenceType.class, singletonList( TEXT ) );
            return codeListBox;
        }
        codeListBox = new TypeCodeListBox<RasterReferenceType>( RasterReferenceType.class );
        codeListBox.selectItem( RasterReferenceType.SCAN );
        return codeListBox;
    }

    private TypeCodeListBox createMimeTypeType( EditVersion version ) {
        if ( XPLAN_3.equals( version ) ) {
            List<MimeTypes> disabledItems = new ArrayList<MimeTypes>();
            disabledItems.add( APPLICATION_MSEXCEL );
            disabledItems.add( APPLICATION_MSWORD );
            disabledItems.add( APPLICATION_ODT );
            disabledItems.add( APPLICATION_VND_OGC_GML );
            disabledItems.add( APPLICATION_VND_OGC_SLD_XML );
            disabledItems.add( APPLICATION_VND_OGC_WMS_XML );
            disabledItems.add( IMAGE_SVG_XML );
            disabledItems.add( TEXT_PLAIN );
            return new TypeCodeListBox<MimeTypes>( MimeTypes.class, disabledItems, true );
        }
        return new TypeCodeListBox( MimeTypes.class, true );
    }

    private boolean validate() {
        boolean valid = super.isValid();
        List<String> validationFailures = new ArrayList<String>();

        if ( ( referenzName.getValue() == null || !( referenzName.getValue().length() > 0 ) )
             && !reference.isFileSelected() ) {
            valid = false;
            validationFailures.add( MESSAGES.editCaptionRasterBasisReferenceNameOrUrl() );
        }
        if ( artType.getValueAsEnum() != null && DOKUMENT.equals( artType.getValueAsEnum() ) ) {
            if ( georefMimeType.getValueAsEnum() != null ) {
                valid = false;
                validationFailures.add( MESSAGES.editCaptionRasterBasisGeoReferenceNotAllowed() );
            }
            if ( georeference.isFileSelected() ) {
                valid = false;
                validationFailures.add( MESSAGES.editCaptionRasterBasisGeoReferenceMimeTypeNotAllowed() );
            }
        }
        showValidationError( validationFailures );
        return valid;
    }

    private class ClearValidationErrorsCH implements ChangeHandler {

        @Override
        public void onChange( ChangeEvent changeEvent ) {
            validationErrors.setText( "" );
            validate();
        }
    }

    private class ClearValidationErrorsVCH<T> implements ValueChangeHandler<T> {
        @Override
        public void onValueChange( ValueChangeEvent<T> event ) {
            validationErrors.setText( "" );
            validate();
        }
    }

}