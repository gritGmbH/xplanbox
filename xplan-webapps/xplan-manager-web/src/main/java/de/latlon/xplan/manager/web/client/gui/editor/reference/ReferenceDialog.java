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
package de.latlon.xplan.manager.web.client.gui.editor.reference;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.EditDialogBoxWithRasterUpload;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.TypeCodeListBox;
import de.latlon.xplan.manager.web.client.gui.editor.text.TextDialog;
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;

import java.util.ArrayList;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;

/**
 * Dialog to edit an existing or create a new {@link Reference}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReferenceDialog extends EditDialogBoxWithRasterUpload {

    private final TypeCodeListBox<ReferenceType> refType;

    /**
     * Instantiates a {@link TextDialog} to create a new {@link Change}
     */
    public ReferenceDialog( EditVersion version ) {
        this( version, MESSAGES.editCaptionReferencesDialogNew() );
    }

    private ReferenceDialog( EditVersion version, String title ) {
        super( version, title );
        refType = createRefType( version );
        initDialog( createFormContent() );
    }

    /**
     * @return the actual edited {@link Reference}, may be <code>null</code>
     */
    public Reference getReference() {
        Reference ref = new Reference();
        ref.setReference( reference.getFilename() );
        ref.setGeoReference( georeference.getFilename() );
        ref.setType( refType.getValueAsEnum() );
        return ref;
    }

    private Widget createFormContent() {
        FlexTable layout = new FlexTable();
        FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
        formatter.setHorizontalAlignment( 1, 1, ALIGN_LEFT );
        formatter.setHorizontalAlignment( 2, 1, ALIGN_LEFT );

        layout.setWidget( 1, 1, new Label( MESSAGES.editCaptionReferencesReference() ) );
        layout.setWidget( 1, 2, reference );
        // #3305 - georeference is not needed.
        // if ( !XPLAN_3.equals( version ) ) {
        // layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionReferencesGeoReference() ) );
        // layout.setWidget( 2, 2, georeference );
        // }
        layout.setWidget( 2, 1, new Label( MESSAGES.editCaptionReferencesType() ) );
        layout.setWidget( 2, 2, refType );

        return layout;
    }

    private TypeCodeListBox<ReferenceType> createRefType( EditVersion version ) {
        List<ReferenceType> supportedReferenceTypes = new ArrayList<ReferenceType>();
        for ( ReferenceType referenceType : ReferenceType.values() ) {
            if ( referenceType.isXPlanVersionSupported( version.name() ) )
                supportedReferenceTypes.add( referenceType );
        }
        return new TypeCodeListBox<ReferenceType>( ReferenceType.class, supportedReferenceTypes );
    }

}