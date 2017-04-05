package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;

import org.deegree.feature.Feature;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.codelists.XPlanCodeLists;
import de.latlon.xplan.manager.codelists.XPlanCodeListsFactory;

/**
 * {@link Expression} for translating codes from internal codelists (aka key enumerations) to their textual
 * representation.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XplanCodeLookup extends AbstractXplanCodeLookup {

    public XplanCodeLookup( Expression exp, String codeListName ) {
        super( exp, codeListName );
    }

    @Override
    protected XPlanCodeLists getXplanCodeLists( Feature feature ) {
        XPlanVersion version = determineBaseVersion( feature.getName() );
        return XPlanCodeListsFactory.get( version );
    }

}
