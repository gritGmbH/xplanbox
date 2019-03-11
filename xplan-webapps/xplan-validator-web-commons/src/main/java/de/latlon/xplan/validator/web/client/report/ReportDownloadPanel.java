package de.latlon.xplan.validator.web.client.report;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PDF;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PNG;
import static de.latlon.xplan.validator.web.shared.ArtifactType.SHP;
import static de.latlon.xplan.validator.web.shared.ArtifactType.XML;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.validator.web.client.ValidatorWebCommonsMessages;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationSummary;

/**
 * Encapulates the download options.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ReportDownloadPanel extends CaptionPanel {

    private static final ValidatorWebCommonsMessages messages = GWT.create( ValidatorWebCommonsMessages.class );

    private final CheckBox htmlCheckBox = new CheckBox( messages.reportDownloadHtml() );

    private final CheckBox xmlCheckBox = new CheckBox( messages.reportDownloadXml() );

    private final CheckBox pdfCheckBox = new CheckBox( messages.reportDownloadPdf() );

    private final CheckBox shpCheckBox = new CheckBox( messages.reportDownloadShp() );

    private final CheckBox pngCheckBox = new CheckBox( messages.reportDownloadPng() );

    private final ReportUrlBuilder urlBuilder = new ReportUrlBuilder();

    private ValidationSummary validationSummary;

    public ReportDownloadPanel( ValidationSummary validationSummary ) {
        super( messages.reportDownloadBoxTitle() );
        this.validationSummary = validationSummary;
        initPanel();
    }

    private void initPanel() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setSpacing( 10 );

        mainPanel.add( htmlCheckBox );
        mainPanel.add( pdfCheckBox );
        mainPanel.add( xmlCheckBox );

        mainPanel.add( createGeometryErrorSeperator() );

        mainPanel.add( shpCheckBox );
        mainPanel.add( pngCheckBox );

        mainPanel.add( createDownloadButton() );

        setContentWidget( mainPanel );
    }

    private Widget createGeometryErrorSeperator() {
        Label label = new Label( messages.reportDownloadGeometryErrors() );
        return label;
    }

    private Widget createDownloadButton() {
        Button download = new Button( messages.reportDownloadButtonTitle() );
        download.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                List<ArtifactType> selectedArtifacts = getSelectedArtifacts();
                if ( selectedArtifacts.size() > 0 ) {
                    String zipUrl = urlBuilder.createZipUrl( validationSummary, selectedArtifacts );
                    GWT.log( "Requested URL to receive the zip file with reports: " + zipUrl );
                    Window.open( zipUrl, "", "" );
                } else {
                    Window.alert( messages.reportDownloadNoArtefactsSelected() );
                }
            }
        } );
        return download;
    }

    private List<ArtifactType> getSelectedArtifacts() {
        List<ArtifactType> selectedArtifacts = new ArrayList<ArtifactType>();
        if ( htmlCheckBox.getValue() )
            selectedArtifacts.add( HTML );
        if ( xmlCheckBox.getValue() )
            selectedArtifacts.add( XML );
        if ( pdfCheckBox.getValue() )
            selectedArtifacts.add( PDF );
        if ( shpCheckBox.getValue() )
            selectedArtifacts.add( SHP );
        if ( pngCheckBox.getValue() )
            selectedArtifacts.add( PNG );
        return selectedArtifacts;
    }

}