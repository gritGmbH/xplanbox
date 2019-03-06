package org.deegree.gml;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.GenericFeatureCollection;
import org.deegree.gml.feature.GMLFeatureWriter;

import javax.xml.stream.XMLStreamException;

import static org.deegree.commons.xml.CommonNamespaces.XLNNS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlFeatureWriter extends GMLFeatureWriter {
    private final XPlanVersion xPlanVersion;

    /**
     * Creates a new {@link GMLFeatureWriter} instance.
     *
     * @param gmlStreamWriter
     *                 GML stream writer, must not be <code>null</code>
     */
    public XPlanGmlFeatureWriter( GMLStreamWriter gmlStreamWriter, XPlanVersion xPlanVersion ) {
        super( gmlStreamWriter );
        this.xPlanVersion = xPlanVersion;
    }

    @Override
    public void export( Feature feature )
                    throws XMLStreamException, UnknownCRSException, TransformationException {
        if ( feature instanceof GenericFeatureCollection ) {
            writeStartElementWithNS( xPlanVersion.getNamespace(), "XPlanAuszug" );
            if ( feature.getId() != null ) {
                GMLVersion gmlVersion = xPlanVersion.getGmlVersion();
                writeAttributeWithNS( gmlVersion.getNamespace(), "id", feature.getId() );
            }
            for ( Feature member : ( (FeatureCollection) feature ) ) {
                String memberFid = member.getId();
                writeStartElementWithNS( gmlNs, "featureMember" );
                if ( memberFid != null && referenceExportStrategy.isObjectExported( memberFid ) ) {
                    writeAttributeWithNS( XLNNS, "href", "#" + memberFid );
                } else {
                    export( member, getResolveStateForNextLevel( referenceExportStrategy.getResolveOptions() ) );
                }
                writer.writeEndElement();
            }
            writer.writeEndElement();
        } else {
            super.export( feature );
        }
    }

}
