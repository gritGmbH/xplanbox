package de.latlon.xplan.planwerkwms;

import org.apache.axiom.om.OMElement;
import org.deegree.commons.ows.metadata.DatasetMetadata;
import org.deegree.commons.ows.metadata.MetadataUrl;
import org.deegree.commons.ows.metadata.ServiceIdentification;
import org.deegree.commons.ows.metadata.ServiceProvider;
import org.deegree.commons.ows.metadata.layer.ExternalIdentifier;
import org.deegree.commons.tom.ows.LanguageString;
import org.deegree.services.metadata.OWSMetadataProvider;
import org.deegree.services.planwerk.jaxb.Planwerk;
import org.deegree.workspace.Resource;
import org.deegree.workspace.ResourceMetadata;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataProviderWrapper implements OWSMetadataProvider {

    private final OWSMetadataProvider wrappedProvider;

    private final Planwerk planwerk;

    public MetadataProviderWrapper( OWSMetadataProvider wrappedProvider, Planwerk planwerk ) {
        this.wrappedProvider = wrappedProvider;
        this.planwerk = planwerk;
    }

    @Override
    public ServiceIdentification getServiceIdentification() {
        ServiceIdentification serviceIdentification = wrappedProvider.getServiceIdentification();
        String name = getTitle( planwerk );
        if ( serviceIdentification == null )
            return new ServiceIdentification( name, null, null, null, null, null, null, null, null );
        List<LanguageString> titles = singletonList( new LanguageString( name, null ) );
        return new ServiceIdentification( name, titles, serviceIdentification.getAbstracts(),
                                          serviceIdentification.getKeywords(), serviceIdentification.getServiceType(),
                                          serviceIdentification.getServiceTypeVersion(),
                                          serviceIdentification.getProfiles(), serviceIdentification.getFees(),
                                          serviceIdentification.getAccessConstraints() );
    }

    @Override
    public ServiceProvider getServiceProvider() {
        return wrappedProvider.getServiceProvider();
    }

    @Override
    public Map<String, List<OMElement>> getExtendedCapabilities() {
        return wrappedProvider.getExtendedCapabilities();
    }

    @Override
    public List<DatasetMetadata> getDatasetMetadata() {
        return wrappedProvider.getDatasetMetadata();
    }

    @Override
    public Map<String, String> getExternalMetadataAuthorities() {
        return wrappedProvider.getExternalMetadataAuthorities();
    }

    @Override
    public DatasetMetadata getDatasetMetadata( QName name ) {
        return wrappedProvider.getDatasetMetadata( name );
    }

    @Override
    public List<DatasetMetadata> getAllDatasetMetadata( QName name ) {
        List<MetadataUrl> metadataUrls = new ArrayList<>();
        for ( String resourceIdentifier : planwerk.getResourceIdentifier() ) {
            metadataUrls.add( new MetadataUrl( resourceIdentifier, null, null ) );
        }
        List<ExternalIdentifier> externalIds = new ArrayList<>();
        for ( String externalId : planwerk.getDataMetadataUrl() ) {
            externalIds.add( new ExternalIdentifier( externalId, null ) );
        }
        List<DatasetMetadata> datasetMetadatas = new ArrayList<>();
        DatasetMetadata datasetMetadata = new DatasetMetadata( name, null, null, null, metadataUrls, externalIds, null,
                                                               null, null );
        datasetMetadatas.add( datasetMetadata );
        return datasetMetadatas;
    }

    @Override
    public ResourceMetadata<? extends Resource> getMetadata() {
        return wrappedProvider.getMetadata();
    }

    @Override
    public void init() {
        wrappedProvider.init();
    }

    @Override
    public void destroy() {
        wrappedProvider.destroy();
    }

    private String getTitle( Planwerk planwerk ) {
        List<String> wmsTitle = planwerk.getWmsTitle();
        if ( wmsTitle == null || wmsTitle.isEmpty() )
            return planwerk.getName();
        return wmsTitle.stream().collect( Collectors.joining( ", " ) );
    }

}