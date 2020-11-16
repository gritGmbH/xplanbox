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
package de.latlon.xplan.manager.web.client.gui.editor;

import java.util.Collections;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RowCountChangeEvent;

import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.TypeCodelistProvider;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

/**
 * Base class for all sub panels with a table in the {@link EditorPanel}.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractEditorSubPanelWithTable<T> extends CaptionPanel {

    private static final int TEXT_LIMIT = 500;

    protected static final XPlanWebMessages MESSAGES = GWT.create( XPlanWebMessages.class );

    protected static final CodelistProvider CODELIST_PROVIDER = new CodelistProvider();

    protected static final TypeCodelistProvider TYPE_CODELIST_PROVIDER = new TypeCodelistProvider();

    protected final EditVersion version;

    private final ListDataProvider<T> provider = new ListDataProvider<T>();

    private final CellTable<T> table;

    /**
     * @param version
     *            of the plan to edit, never <code>null</code>
     * @param captionText
     *            the text of the caption, never <code>null</code>
     */
    public AbstractEditorSubPanelWithTable( EditVersion version, String captionText ) {
        this.version = version;
        setCaptionText( captionText );
        this.table = createTable();
    }

    /**
     * @return the table, never <code>null</code>
     */
    public CellTable<T> getTable() {
        return table;
    }

    /**
     * @return the values displayed in the table, may be empty but never <code>null</code>
     */
    public List<T> getValues() {
        return provider.getList();
    }

    /**
     * Adds the passed values to the table.
     * 
     * @param values
     *            to add, may be <code>null</code> or empty
     */
    public void setValues( List<T> values ) {
        if ( values == null )
            values = Collections.emptyList();
        table.setRowCount( values.size(), true );
        List<T> providedChanges = getValues();
        providedChanges.clear();
        providedChanges.addAll( values );
    }

    /**
     * Adds the columns displayed by the table.
     * 
     * @param table
     *            to add the columns to, never <code>null</code>
     */
    protected abstract void initColumns( CellTable<T> table );

    protected String shortText( String textToShort ) {
        if ( textToShort != null && textToShort.length() > TEXT_LIMIT + 5 ) {
            String shortendText = textToShort.substring( 0, TEXT_LIMIT );
            return shortendText + "...";
        }
        return textToShort;

    }

    private CellTable<T> createTable() {
        final CellTable<T> table = new CellTable<T>();
        table.addRowCountChangeHandler( new RowCountChangeEvent.Handler() {
            @Override
            public void onRowCountChange( RowCountChangeEvent event ) {
                table.setVisibleRange( new Range( 0, event.getNewRowCount() ) );
            }
        } );
        initColumns( table );
        provider.addDataDisplay( table );
        return table;
    }

}
