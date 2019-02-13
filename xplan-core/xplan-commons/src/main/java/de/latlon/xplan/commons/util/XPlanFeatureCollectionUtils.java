package de.latlon.xplan.commons.util;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.parseFeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanFeatureCollectionUtils {

    private XPlanFeatureCollectionUtils() {
    }

    /**
     * Reads the {@link XPlanFeatureCollection} from the passed {@link XPlanArchive}
     *
     * @param archive
     *                 to parse, never <code>null</code>
     * @return never <code>null</code>
     * @throws XMLStreamException
     *                 if the plan could not be read
     * @throws UnknownCRSException
     *                 if the CRS of a geometry in the plan is not known
     */
    public static XPlanFeatureCollection parseXPlanFeatureCollection( XPlanArchive archive )
                    throws XMLStreamException, UnknownCRSException {
        XMLStreamReader plan = archive.getMainFileXmlReader();
        XPlanType type = archive.getType();
        XPlanVersion version = archive.getVersion();
        AppSchema appSchema = XPlanSchemas.getInstance().getAppSchema( version, archive.getAde() );
        return parseXPlanFeatureCollection( plan, type, version, archive.getAde(), appSchema );
    }

    /**
     * Reads the {@link XPlanFeatureCollection} from the passed {@link InputStream}
     *
     * @param inputStream
     *                 to parse, never <code>null</code>
     * @param type
     *                 of the plan, never <code>null</code>
     * @param version
     *                 of the plan, never <code>null</code>
     * @param ade
     *                 of the plan, may be <code>null</code>
     * @param appSchema
     *                 describing the plan, never <code>null</code>
     * @return never <code>null</code>
     * @throws XMLStreamException
     *                 if the plan could not be read
     * @throws UnknownCRSException
     *                 if the CRS of a geometry in the plan is not known
     */
    public static XPlanFeatureCollection parseXPlanFeatureCollection( InputStream inputStream, XPlanType type,
                                                                      XPlanVersion version, XPlanAde ade,
                                                                      AppSchema appSchema )
                    throws XMLStreamException, UnknownCRSException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader = null;
        try {
            xmlStreamReader = xmlInputFactory.createXMLStreamReader( inputStream );
            return parseXPlanFeatureCollection( xmlStreamReader, type, version, ade, appSchema );
        } finally {
            if ( xmlStreamReader != null )
                xmlStreamReader.close();
        }
    }

    private static XPlanFeatureCollection parseXPlanFeatureCollection( XMLStreamReader plan, XPlanType type,
                                                                       XPlanVersion version, XPlanAde ade,
                                                                       AppSchema appSchema )
                    throws XMLStreamException, UnknownCRSException {
        FeatureCollection xplanFeatures = parseFeatureCollection( plan, version, appSchema );
        return new XPlanFeatureCollection( xplanFeatures, version, type, ade );
    }

}
