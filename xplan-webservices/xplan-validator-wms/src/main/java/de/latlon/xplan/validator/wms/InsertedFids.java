/*-
 * #%L
 * xplan-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.validator.wms;

import java.util.Calendar;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InsertedFids {

    public static final String INSERTED_FIDS_KEY = "insertedFids";

    private Calendar insertTime;

    private List<String> fids;

    /**
     * @param fids
     *                         Ids of the inserted features, never <code>null</code>
     */
    public InsertedFids( List<String> fids ) {
        this.insertTime = Calendar.getInstance();
        this.fids = fids;
    }

    /**
     * @return the date this {@link InsertedFids} insatence was created, never <code>null</code>
     */
    public Calendar getInsertTime() {
        return insertTime;
    }

    /**
     * @return Ids of the inserted features, never <code>null</code>
     */
    public List<String> getFids() {
        return fids;
    }

    @Override
    public String toString() {
        return "InsertedFids{" + "insertTime=" + insertTime + ", fids=" + fids + '}';
    }
}
