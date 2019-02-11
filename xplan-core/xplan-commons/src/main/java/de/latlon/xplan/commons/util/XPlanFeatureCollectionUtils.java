package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanFeatureCollectionUtils {

    /**
     * Reads the {@link XPlanFeatureCollection} from the passed {@link XPlanArchive}
     *
     * @param archive
     *                 never <code>null</code>
     * @return never <code>null</code>
     * @throws XMLStreamException
     * @throws UnknownCRSException
     */
    public static XPlanFeatureCollection parseXPlanFeatureCollection( XPlanArchive archive )
                    throws XMLStreamException, UnknownCRSException {
        XMLStreamReader plan = archive.getMainFileXmlReader();
        XPlanType type = archive.getType();
        XPlanVersion version = archive.getVersion();
        AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema( version, archive.getAde() );
        return parseXPlanFeatureCollection( plan, type, version, archive.getAde(), appSchema );
    }

    public static XPlanFeatureCollection parseXPlanFeatureCollection( XMLStreamReader plan, XPlanType type,
                                                                      XPlanVersion version, XPlanAde ade,
                                                                      AppSchema appSchema )
                    throws XMLStreamException, UnknownCRSException {
        FeatureCollection xplanFeatures = parseFeatureCollection( plan, version, appSchema );
        return new XPlanFeatureCollection( xplanFeatures, version, type, ade );
    }

}
