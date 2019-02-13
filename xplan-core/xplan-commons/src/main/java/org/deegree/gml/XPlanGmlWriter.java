package org.deegree.gml;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.gml.feature.GMLFeatureWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlWriter extends GMLStreamWriter {

    private final XPlanVersion xPlanVersion;

    /**
     * Creates a new {@link GMLStreamWriter} instance.
     *
     * @param xPlanVersion
     *                 {@link XPlanVersion} of the output, must not be <code>null</code>
     * @param xmlStream
     *                 XML stream used to write the output, must not be <code>null</code>
     * @throws XMLStreamException
     */
    public XPlanGmlWriter( XPlanVersion xPlanVersion, XMLStreamWriter xmlStream )
                    throws XMLStreamException {
        super( xPlanVersion.getGmlVersion(), xmlStream );
        this.xPlanVersion = xPlanVersion;
        getNamespaceBindings().put( "xplan", xPlanVersion.getNamespace() );
    }

    @Override
    public GMLFeatureWriter getFeatureWriter() {
        return new XPlanGmlFeatureWriter( this, xPlanVersion );
    }

}
