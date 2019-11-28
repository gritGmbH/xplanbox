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