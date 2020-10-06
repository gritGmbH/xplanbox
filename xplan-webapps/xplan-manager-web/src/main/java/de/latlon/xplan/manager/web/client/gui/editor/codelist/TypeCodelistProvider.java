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
package de.latlon.xplan.manager.web.client.gui.editor.codelist;

import com.google.gwt.core.client.GWT;
import de.latlon.xplan.manager.web.client.i18n.CodelistMessages;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;
import de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt;
import de.latlon.xplan.manager.web.shared.edit.MimeTypes;
import de.latlon.xplan.manager.web.shared.edit.RasterReferenceType;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGED_BY;
import static de.latlon.xplan.manager.web.shared.edit.ChangeType.CHANGES;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.DOKUMENT;
import static de.latlon.xplan.manager.web.shared.edit.ExterneReferenzArt.PLANMITGEOREFERENZ;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.LEGEND;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;
import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.TEXT;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GREEN_STRUCTURES_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.LEGISLATION_PLAN;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.REASON;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.FESTSETZUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.HINWEIS;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.KENNZEICHNUNG;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.NACHRICHTLICHEUEBERNAHME;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.UNBEKANNT;
import static de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType.VERMERK;

/**
 * Provides access to type code (mapping between code and value)
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class TypeCodelistProvider {

    private static final CodelistMessages MESSAGES = GWT.create( CodelistMessages.class );

    private static final Map<Class<?>, List<Code>> TYPECODES = initCodeLists();

    /**
     * @param enumClass
     *            the enumeration the value is part of, never <code>null</code>
     * @param enumValue
     *            the value to retrieve the item for, may be <code>null</code>
     * @return the item value of the {@link Code} where the code value equals the enumValue, if no codelist and/or code
     *         could be found enumValue.name() is returned
     */
    public <T extends Enum<T>> String translate( Class<T> enumClass, T enumValue ) {
        if ( enumValue == null )
            return null;
        List<Code> codes = TYPECODES.get( enumClass );
        if ( codes != null ) {
            for ( Code code : codes ) {
                if ( code.getCode().equals( enumValue.name() ) )
                    return code.getItem();
            }
        }
        return enumValue.name();
    }

    /**
     * @param enumClass
     *            the enumeration to retrieve as {@link Code}s
     * @return
     * @return a list of {@link Code}s or an empty list. of no codelist is available for the passed enumeration, never
     *         <code>null</code>
     */
    public <T extends Enum<T>> List<Code> retrieveItems( Class<T> enumClass ) {
        if ( TYPECODES.containsKey( enumClass ) )
            return TYPECODES.get( enumClass );
        return Collections.emptyList();
    }

    private static Map<Class<?>, List<Code>> initCodeLists() {
        HashMap<Class<?>, List<Code>> typeCode = new HashMap<Class<?>, List<Code>>();
        addChangeType( typeCode );
        addReferenceType( typeCode );
        addRasterReferenceType( typeCode );
        addExterneReferenzArtType( typeCode );
        addMimeTypesType( typeCode );
        addTextRechtscharakterType( typeCode );
        return typeCode;
    }

    private static void addChangeType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( CHANGED_BY.name(), MESSAGES.ChangeType_CHANGED_BY() ) );
        codes.add( new Code( CHANGES.name(), MESSAGES.ChangeType_CHANGES() ) );
        typeCode.put( ChangeType.class, codes );
    }

    private static void addReferenceType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( GREEN_STRUCTURES_PLAN.name(), MESSAGES.ReferenceType_GREEN_STRUCTURES_PLAN() ) );
        codes.add( new Code( LEGISLATION_PLAN.name(), MESSAGES.ReferenceType_LEGISLATION_PLAN() ) );
        codes.add( new Code( REASON.name(), MESSAGES.ReferenceType_REASON() ) );
        typeCode.put( ReferenceType.class, codes );
    }

    private static void addRasterReferenceType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( LEGEND.name(), MESSAGES.RasterReferenceType_LEGEND() ) );
        codes.add( new Code( SCAN.name(), MESSAGES.RasterReferenceType_SCAN() ) );
        codes.add( new Code( TEXT.name(), MESSAGES.RasterReferenceType_TEXT() ) );
        typeCode.put( RasterReferenceType.class, codes );
    }

    private static void addMimeTypesType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        for ( MimeTypes mimeTypeType : MimeTypes.values() ) {
            codes.add( new Code( mimeTypeType.name(), mimeTypeType.getCode() ) );
        }
        typeCode.put( MimeTypes.class, codes );
    }

    private static void addExterneReferenzArtType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( DOKUMENT.name(), MESSAGES.XP_ExterneReferenzArt_Dokument() ) );
        codes.add( new Code( PLANMITGEOREFERENZ.name(), MESSAGES.XP_ExterneReferenzArt_PlanMitGeoreferenz() ) );
        typeCode.put( ExterneReferenzArt.class, codes );
    }

    private static void addTextRechtscharakterType( HashMap<Class<?>, List<Code>> typeCode ) {
        List<Code> codes = new ArrayList<Code>();
        codes.add( new Code( FESTSETZUNG.name(), MESSAGES.TextAbschnitt_Festsetzung() ) );
        codes.add( new Code( HINWEIS.name(), MESSAGES.TextAbschnitt_Hinweis() ) );
        codes.add( new Code( KENNZEICHNUNG.name(), MESSAGES.TextAbschnitt_Kennzeichnung() ) );
        codes.add( new Code( NACHRICHTLICHEUEBERNAHME.name(), MESSAGES.TextAbschnitt_NachrichtlicheUebernahme() ) );
        codes.add( new Code( VERMERK.name(), MESSAGES.TextAbschnitt_Vermerk() ) );
        codes.add( new Code( UNBEKANNT.name(), MESSAGES.TextAbschnitt_Unbekannt() ) );
        typeCode.put( TextRechtscharacterType.class, codes );
    }

}