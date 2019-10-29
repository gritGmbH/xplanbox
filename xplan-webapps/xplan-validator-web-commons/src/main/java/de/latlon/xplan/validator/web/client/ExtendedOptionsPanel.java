package de.latlon.xplan.validator.web.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.validator.web.shared.ValidationOption;

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

    private static final String MIN_NODE_DISTANCE = "min-node-distance";

    private CheckBox ignoreSelfIntersection;

    private CheckBox ignoreOrientation;

    private CheckBox minNodeDistance;

    private DoubleBox minNodeDistanceUnit;

    public ExtendedOptionsPanel() {
        initDialog();
    }

    public List<ValidationOption> retrieveExtendedOptionsStatus() {
        List<ValidationOption> extendedOptions = new ArrayList<ValidationOption>();
        if ( ignoreSelfIntersection.getValue() )
            extendedOptions.add( new ValidationOption( IGNORE_SELF_INTERSECTION ) );
        if ( ignoreOrientation.getValue() )
            extendedOptions.add( new ValidationOption( IGNORE_ORIENTATION ) );
        if ( minNodeDistance.getValue() )
            extendedOptions.add( new ValidationOption( MIN_NODE_DISTANCE, minNodeDistanceUnit.getValue().toString() ) );
        return extendedOptions;
    }

    private void initDialog() {
        ignoreSelfIntersection = new CheckBox( messages.ignoreSelfIntersection() );
        ignoreOrientation = new CheckBox( messages.ignoreOrientation() );
        HorizontalPanel minNodeDistancePanel = createMinNodeDistancePanel();
        CheckBox ignoreOther = new CheckBox( messages.ignoreOther() );
        CheckBox ignorePresentation = new CheckBox( messages.ignorePresentation() );

        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setSpacing( 10 );
        verticalPanel.add( ignoreSelfIntersection );
        verticalPanel.add( ignoreOrientation );
        verticalPanel.add( minNodeDistancePanel );
        verticalPanel.add( ignoreOther );
        verticalPanel.add( ignorePresentation );

        add( verticalPanel );
    }

    private HorizontalPanel createMinNodeDistancePanel() {
        minNodeDistance = new CheckBox( messages.nodeTolerance() );
        HorizontalPanel baseDistancePanel = new HorizontalPanel();
        baseDistancePanel.setVerticalAlignment( HasVerticalAlignment.ALIGN_MIDDLE );
        minNodeDistanceUnit = new DoubleBox();

        minNodeDistanceUnit.setVisibleLength( 3 );
        minNodeDistanceUnit.setValue( 0.002 );
        baseDistancePanel.add( minNodeDistance );
        baseDistancePanel.add( minNodeDistanceUnit );
        baseDistancePanel.add( new Label( messages.nodeToleranceUnit() ) );
        return baseDistancePanel;
    }

}
