package de.latlon.xplan.manager.synthesizer.expression;

import java.io.IOException;
import java.nio.file.Path;

import javax.xml.stream.XMLStreamException;

import org.deegree.feature.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;

/**
 * {@link AbstractXplanCodeLookup} for translating codes from external {@link XPlanCodeLists} to their textual
 * representation.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanExternalCodeLookup extends AbstractXplanCodeLookup {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanExternalCodeLookup.class );

    private final String codeListFile;

    private final Path configurationFilePath;

    /**
     *
     * @param exp
     *            to the property to translate, never <code>null</code>
     * @param codeListFile
     *            relative path (from the rule configuration) to the file containing the code list, never
     *            <code>null</code>
     * @param codeListName
     *            the name of the code list to use for the translation, may be <code>null</code> if the codelists
     *            contains only one codelist
     * @param configurationDirectoryPath
     *            the absolute path to the directory containing the rules configuration, never <code>null</code>
     */
    public XPlanExternalCodeLookup( Expression exp, String codeListFile, String codeListName, Path configurationDirectoryPath ) {
        super( exp, codeListName );
        this.codeListFile = codeListFile;
        this.configurationFilePath = configurationDirectoryPath;
    }

    @Override
    protected XPlanCodeLists getXplanCodeLists( Feature feature ) {
        if ( configurationFilePath != null ) {
            Path codeList = configurationFilePath.resolve( codeListFile );
            LOG.info( "Use configured codelist  from {}.", codeList );
            try {
                return XPlanCodeListsFactory.getXPlanCodeLists( codeList.toUri().toURL() );
            } catch ( XMLStreamException e ) {
                LOG.error( "Could not parse code list " + codeList + ". Code will not be translated.", e );
            } catch ( IOException e ) {
                LOG.error( "Could not parse code list " + codeList + ". Code will not be translated.", e );
            }
        }
        return null;
    }

}