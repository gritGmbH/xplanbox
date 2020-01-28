package de.latlon.xplan.validator.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.validator.web.shared.ValidationOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Shows all extended validation options.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
class ExtendedOptionsPanel extends HorizontalPanel {

    private static final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    private static final String IGNORE_SELF_INTERSECTION = "ignore-self-intersection";

    private static final String IGNORE_ORIENTATION = "ignore-orientation";

    private static final String SKIP_FLAECHENSCHLUSS = "skip-flaechenschluss";

    private static final String SKIP_GELTUNGSBEREICH = "skip-geltungsbereich";

    private CheckBox ignoreSelfIntersection;

    private CheckBox ignoreOrientation;

    private CheckBox skipFlaechenschluss;

    private CheckBox skipGeltungsbereich;

    public ExtendedOptionsPanel() {
        initDialog();
    }

    public List<ValidationOption> retrieveExtendedOptionsStatus() {
        List<ValidationOption> extendedOptions = new ArrayList<ValidationOption>();
        if ( ignoreSelfIntersection.getValue() )
            extendedOptions.add( new ValidationOption( IGNORE_SELF_INTERSECTION ) );
        if ( ignoreOrientation.getValue() )
            extendedOptions.add( new ValidationOption( IGNORE_ORIENTATION ) );
        if ( skipFlaechenschluss.getValue() )
            extendedOptions.add( new ValidationOption( SKIP_FLAECHENSCHLUSS, Boolean.TRUE.toString() ) );
        if ( skipGeltungsbereich.getValue() )
            extendedOptions.add( new ValidationOption( SKIP_GELTUNGSBEREICH, Boolean.TRUE.toString() ) );
        return extendedOptions;
    }

    private void initDialog() {
        ignoreSelfIntersection = new CheckBox( messages.ignoreSelfIntersection() );
        ignoreOrientation = new CheckBox( messages.ignoreOrientation() );
        skipFlaechenschluss = new CheckBox( messages.skipFlaechenschluss() );
        skipFlaechenschluss.setValue( true );
        skipGeltungsbereich = new CheckBox( messages.skipGeltungsbereich() );
        skipGeltungsbereich.setValue( true );

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing( 10 );
        verticalPanel.add( skipFlaechenschluss );
        verticalPanel.add( skipGeltungsbereich );
        verticalPanel.add( ignoreSelfIntersection );
        verticalPanel.add( ignoreOrientation );

        add( verticalPanel );
    }

}
