#!/bin/bash
set -e

echo "XPlan volume initialization..."

if [ -z ${XPLANBOX_VOLUMES+x} ]; then 
	echo "ERROR: environment variable XPLANBOX_VOLUMES not set!"
	exit 1
fi

MARKER_FILE=$XPLANBOX_VOLUMES/init-marker.txt
INIT_STARTED_FILE=$XPLANBOX_VOLUMES/init-start-marker.txt

if [ -f "$MARKER_FILE" ]; then
  if [[ -n "$XPLANBOX_VOLUME_INIT" && $XPLANBOX_VOLUME_INIT = "reset" ]]; then
    echo "[$(date -Iseconds)] Reset of existing dir $XPLANBOX_VOLUMES forced ..."
    rm -f $MARKER_FILE $INIT_STARTED_FILE
    rm -rf $XPLANBOX_VOLUMES/xplan-*
  else
    echo "[$(date -Iseconds)] Init already done in $XPLANBOX_VOLUMES ($(cat $MARKER_FILE))"
    exit 0
	fi
fi

echo "[$(date -Iseconds)] Initializing dir $XPLANBOX_VOLUMES ..."

mkdir -p $XPLANBOX_VOLUMES

# handle case of concurrently running inits
tmpFile=$(mktemp /tmp/init-script.XXXXXX)
echo "Init started by $(hostname) at $(date -Iseconds)" > $tmpFile
mv -n $tmpFile $INIT_STARTED_FILE
if [ -e $tmpFile ]
then
	echo "[$(date -Iseconds)] Seems other init process is running:"
	cat $INIT_STARTED_FILE
	echo "[$(date -Iseconds)] Waiting for termination of other process..."
	while [ ! -f $MARKER_FILE ]; do 
		sleep 5
	done
	echo "[$(date -Iseconds)] Init finished by other process in $XPLANBOX_VOLUMES ($(cat $MARKER_FILE))"
	exit 0
fi

cp -r /xplan-volume-init/xplan-docker-volumes/* $XPLANBOX_VOLUMES
chmod -R a+w $XPLANBOX_VOLUMES/*

XPLAN_WMS_URL_PUBLIC="${XPLAN_WMS_URL_PUBLIC:-tobedefined}"
XPLAN_VALIDATORWMS_URL_PUBLIC="${XPLAN_VALIDATORWMS_URL_PUBLIC:-tobedefined}"
XPLAN_MANAGERAPI_URL_PUBLIC=${XPLAN_MANAGERAPI_URL_PUBLIC:-xplan-manager-api}
XPLAN_VALIDATORAPI_URL_PUBLIC=${XPLAN_VALIDATORAPI_URL_PUBLIC:-xplan-validator-api}
XPLAN_DOCUMENTENAPI_URL_PUBLIC=${XPLAN_DOCUMENTENAPI_URL_PUBLIC:-xplan-dokumente-api}
XPLAN_MAPSERVER_URL_INTERNAL="${XPLAN_MAPSERVER_URL_INTERNAL:-http://xplan-mapserver}"
XPLAN_SERVICES_URL_INTERNAL=${XPLAN_SERVICES_URL_INTERNAL:-http://xplan-services}
XPLAN_SERVICES_API_KEY=${XPLAN_SERVICES_API_KEY:-xplanbox}
XPLAN_MANAGER_WORKSPACE_RELOAD_ACTION=${XPLAN_MANAGER_WORKSPACE_RELOAD_ACTION:-PLANWERKWMS}

XPLAN_DB_HOSTNAME="${XPLAN_DB_HOSTNAME:-tobedefined}"
XPLAN_DB_PORT="${XPLAN_DB_PORT:-5432}"
XPLAN_DB_NAME="${XPLAN_DB_NAME:-xplanbox}"
XPLAN_DB_USER="${XPLAN_DB_USER:-tobedefined}"
XPLAN_DB_PASSWORD="${XPLAN_DB_PASSWORD:-tobedefined}"
XPLAN_DB="$XPLAN_DB_HOSTNAME:$XPLAN_DB_PORT/$XPLAN_DB_NAME"

XPLAN_SERVICES_DEFAULT_CRS_SRID="${XPLAN_SERVICES_DEFAULT_CRS_SRID:-25832}"
XPLAN_SERVICES_DEFAULT_CRS="${XPLAN_SERVICES_DEFAULT_CRS:-EPSG:25832}"
XPLAN_SERVICES_QUERY_CRS="${XPLAN_SERVICES_QUERY_CRS:-EPSG:4326 EPSG:3857 EPSG:25833}"
XPLAN_SERVICES_QUERY_CRS_ARR=($XPLAN_SERVICES_QUERY_CRS)

# Konfiguration der ServiceProvider-Metadaten in den Capabilities der XPlanServices
XPLAN_SERVICES_PROVIDER_NAME="${XPLAN_SERVICES_PROVIDER_NAME:-lat/lon GmbH}"
XPLAN_SERVICES_PROVIDER_SITE="${XPLAN_SERVICES_PROVIDER_SITE:-http://www.lat-lon.de}"
XPLAN_SERVICES_PROVIDER_CONTACT_NAME="${XPLAN_SERVICES_PROVIDER_CONTACT_NAME:--}"
XPLAN_SERVICES_PROVIDER_CONTACT_POSITIONNAME="${XPLAN_SERVICES_PROVIDER_CONTACT_POSITIONNAME:--}"
XPLAN_SERVICES_PROVIDER_CONTACT_PHONE="${XPLAN_SERVICES_PROVIDER_CONTACT_PHONE:-+49(0)22824333784}"
XPLAN_SERVICES_PROVIDER_CONTACT_MAIL="${XPLAN_SERVICES_PROVIDER_CONTACT_MAIL:-info@lat-lon.de}"
XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_DELIVERYPOINT="${XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_DELIVERYPOINT:-Im Ellig 1}"
XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_CITY="${XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_CITY:-Wachtberg}"
XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_ADMINAREA="${XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_ADMINAREA:-Nordrhein-Westfalen}"
XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_POSTALCODE="${XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_POSTALCODE:-53343}"
XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_COUNTRY="${XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_COUNTRY:-Germany}"
XPLAN_SERVICES_PROVIDER_ONLINERESOURCE="${XPLAN_SERVICES_PROVIDER_ONLINERESOURCE:-http://www.deegree.org}"
XPLAN_SERVICES_PROVIDER_HOURSOFSERVICE="${XPLAN_SERVICES_PROVIDER_HOURSOFSERVICE:-24x7}"
XPLAN_SERVICES_PROVIDER_CONTACTINSTRUCTIONS="${XPLAN_SERVICES_PROVIDER_CONTACTINSTRUCTIONS:-Do not hesitate to call}"
XPLAN_SERVICES_FEES="${XPLAN_SERVICES_FEES:--}"
XPLAN_SERVICES_ACCESSCONSTRAINTS="${XPLAN_SERVICES_ACCESSCONSTRAINTS:--}"
XPLAN_SERVICES_WMS_MAXWIDTH="${XPLAN_SERVICES_WMS_MAXWIDTH:-3840}"
XPLAN_SERVICES_WMS_MAXHEIGHT="${XPLAN_SERVICES_WMS_MAXHEIGHT:-2160}"
XPLAN_SERVICES_METADATA_URL="${XPLAN_SERVICES_METADATA_URL}"

XPLAN_INIT_RASTERTYPE="${XPLAN_INIT_RASTERTYPE:-mapserver}"
XPLAN_INIT_INSPIREPLU="${XPLAN_INIT_INSPIREPLU:-disabled}"

XPLAN_S3_PUBLIC_URL="${XPLAN_S3_PUBLIC_URL}"

#############################
# Update content of volumes #
#############################

cd $XPLANBOX_VOLUMES

sed -i 's|apiUrl=|apiUrl='$XPLAN_VALIDATORAPI_URL_PUBLIC'|g' xplan-validator-config/validatorApiConfiguration.properties
sed -i 's|apiUrl=|apiUrl='$XPLAN_MANAGERAPI_URL_PUBLIC'|g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's|apiUrl=|apiUrl='$XPLAN_DOCUMENTENAPI_URL_PUBLIC'|g' xplan-dokumente-config/dokumenteApiConfiguration.properties
sed -i 's|wmsUrl=|wmsUrl='$XPLAN_WMS_URL_PUBLIC'/xplan-wms|g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's|workspaceReloadUrls=|workspaceReloadUrls='$XPLAN_SERVICES_URL_INTERNAL'/xplan-wms|g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadApiKey=/workspaceReloadApiKey='$XPLAN_SERVICES_API_KEY'/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadAction=ALL/workspaceReloadAction='$XPLAN_MANAGER_WORKSPACE_RELOAD_ACTION'/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/pathToHaleCli=/pathToHaleCli=\/hale\/bin\/hale/g' xplan-manager-config/managerConfiguration.properties
sed -i 's|http://localhost:8080|'$XPLAN_WMS_URL_PUBLIC'|g' xplan-manager-config/managerWebConfiguration.properties
sed -i 's|rasterConfigurationCrs=EPSG:25832|rasterConfigurationCrs='$XPLAN_SERVICES_DEFAULT_CRS'|g' xplan-manager-config/managerConfiguration.properties
sed -i 's|defaultCrs=EPSG:25832|defaultCrs='$XPLAN_SERVICES_DEFAULT_CRS'|g' xplan-manager-config/managerWebConfiguration.properties
chooseCrs=$( echo "$XPLAN_SERVICES_QUERY_CRS" | sed 's/^[ \t]*//;s/[ \t]*$//;s/[ ]/,/g' )
chooseCrs=$XPLAN_SERVICES_DEFAULT_CRS','$chooseCrs
sed -i 's|chooseCrs=EPSG:4326,EPSG:25833,EPSG:31466,EPSG:31467,EPSG:31468,EPSG:31469|chooseCrs='$chooseCrs'|g' xplan-manager-config/managerWebConfiguration.properties

if [ $XPLAN_INIT_INSPIREPLU = "enabled" ]
then
  sed -i 's/activatePublishingInspirePlu=false/activatePublishingInspirePlu=true/g' xplan-manager-config/managerWebConfiguration.properties
fi

find -iname xplan.xml -exec sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' {} \;
find -iname xplan.xml -exec sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' {} \;
find -iname xplan.xml -exec sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' {} \;

find -iname xplancp.xml -exec sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' {} \;
find -iname xplancp.xml -exec sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' {} \;
find -iname xplancp.xml -exec sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' {} \;

sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplucp.xml
sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplucp.xml
sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplucp.xml

sed -i 's|http://localhost:8080/xplan-wms|'$XPLAN_WMS_URL_PUBLIC'/xplan-wms|g' xplan-workspaces/xplan-services-wms-workspace/services/html.gfi

sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' xplan-inspireplu-workspaces/xplan-webservices-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' xplan-inspireplu-workspaces/xplan-webservices-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' xplan-inspireplu-workspaces/xplan-webservices-inspireplu-workspace/jdbc/inspireplu.xml

echo "[$(date -Iseconds)] Configure XPlanServices StorageCRS with srid $XPLAN_SERVICES_DEFAULT_CRS_SRID"
find xplan-workspaces/xplan-manager-workspace/datasources/feature -iname *.xml -exec sed -i 's|<StorageCRS srid="25832"|<StorageCRS srid="'$XPLAN_SERVICES_DEFAULT_CRS_SRID'"|g' {} \;
find xplan-workspaces/xplan-services-wfs-workspace/datasources/feature -iname *.xml -exec sed -i 's|<StorageCRS srid="25832"|<StorageCRS srid="'$XPLAN_SERVICES_DEFAULT_CRS_SRID'"|g' {} \;
find xplan-workspaces/xplan-services-wms-workspace/datasources/feature -iname *.xml -exec sed -i 's|<StorageCRS srid="25832"|<StorageCRS srid="'$XPLAN_SERVICES_DEFAULT_CRS_SRID'"|g' {} \;
find xplan-workspaces/xplan-services-wfs-syn-workspace/datasources/feature -iname *.xml -exec sed -i 's|<StorageCRS srid="25832"|<StorageCRS srid="'$XPLAN_SERVICES_DEFAULT_CRS_SRID'"|g' {} \;
find xplan-workspaces/xplan-manager-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplan-services-wfs-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplan-services-wms-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplan-services-wfs-syn-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
echo "[$(date -Iseconds)] Configure XPlanWMS CRS"
find xplan-workspaces/xplan-services-wms-workspace/themes -iname *.xml -exec sed -i 's/<s:CRS>EPSG:25832 EPSG:25833 EPSG:31466 EPSG:31467 EPSG:31468 EPSG:31469 EPSG:4258 EPSG:4326 CRS:84 EPSG:4839<\/s:CRS>/<s:CRS>'"$XPLAN_SERVICES_DEFAULT_CRS $( echo ${XPLAN_SERVICES_QUERY_CRS_ARR[@]} )"'<\/s:CRS>/g' {} \;
find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's|<s:CRS>EPSG:25832</s:CRS>|<s:CRS>'$XPLAN_SERVICES_DEFAULT_CRS'</s:CRS>|g' {} \;
echo "[$(date -Iseconds)] Configure XPlanWFS QueryCRS"
find xplan-workspaces/xplan-services-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i '/<QueryCRS>EPSG:.*<\/QueryCRS>/d' {} \;

find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<ProviderName>lat/lon GmbH</ProviderName>|<ProviderName>'"$XPLAN_SERVICES_PROVIDER_NAME"'</ProviderName>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<ProviderSite>http://www.lat-lon.de</ProviderSite>|<ProviderSite>'"$XPLAN_SERVICES_PROVIDER_SITE"'</ProviderSite>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<IndividualName>-</IndividualName>|<IndividualName>'"$XPLAN_SERVICES_PROVIDER_CONTACT_NAME"'</IndividualName>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<PositionName/>|<PositionName>'"$XPLAN_SERVICES_PROVIDER_CONTACT_POSITIONNAME"'</PositionName>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<Phone>+49(0)22824333784</Phone>|<Phone>'"$XPLAN_SERVICES_PROVIDER_CONTACT_PHONE"'</Phone>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<ElectronicMailAddress>info@lat-lon.de</ElectronicMailAddress>|<ElectronicMailAddress>'"$XPLAN_SERVICES_PROVIDER_CONTACT_MAIL"'</ElectronicMailAddress>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<DeliveryPoint>Im Ellig 1</DeliveryPoint>|<DeliveryPoint>'"$XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_DELIVERYPOINT"'</DeliveryPoint>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<City>Wachtberg</City>|<City>'"$XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_CITY"'</City>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<AdministrativeArea>Nordrhein-Westfalen</AdministrativeArea>|<AdministrativeArea>'"$XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_ADMINAREA"'</AdministrativeArea>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<PostalCode>53343</PostalCode>|<PostalCode>'"$XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_POSTALCODE"'</PostalCode>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<Country>Germany</Country>|<Country>'"$XPLAN_SERVICES_PROVIDER_CONTACT_ADDRESS_COUNTRY"'</Country>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<OnlineResource>http://www.deegree.org</OnlineResource>|<OnlineResource>'"$XPLAN_SERVICES_PROVIDER_ONLINERESOURCE"'</OnlineResource>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<HoursOfService>24x7</HoursOfService>|<HoursOfService>'"$XPLAN_SERVICES_PROVIDER_HOURSOFSERVICE"'</HoursOfService>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<ContactInstructions>Do not hesitate to call</ContactInstructions>|<ContactInstructions>'"$XPLAN_SERVICES_PROVIDER_CONTACTINSTRUCTIONS"'</ContactInstructions>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<Fees/>|<Fees>'"$XPLAN_SERVICES_FEES"'</Fees>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<AccessConstraints/>|<AccessConstraints>'"$XPLAN_SERVICES_ACCESSCONSTRAINTS"'</AccessConstraints>|g' {} \;
if [ -n "$XPLAN_SERVICES_METADATA_URL" ]
  then
    echo "[$(date -Iseconds)] Configure metadata url $XPLAN_SERVICES_METADATA_URL"
    find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<!--DatasetMetadata>|<DatasetMetadata>|g' {} \;
    find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|</DatasetMetadata-->|</DatasetMetadata>|g' {} \;
    find xplan-workspaces/xplan*-workspace/services -iname *_metadata.xml -exec sed -i 's|<MetadataUrlTemplate>http://some.url.de/csw?request=GetRecordById&amp;amp;service=CSW&amp;amp;version=2.0.2&amp;amp;outputschema=http://www.isotc211.org/2005/gmd&amp;amp;elementsetname=full&amp;amp;id=${metadataSetId}</MetadataUrlTemplate>|<MetadataUrlTemplate>'"$XPLAN_SERVICES_METADATA_URL"'</MetadataUrlTemplate>|g' {} \;
fi
find xplan-workspaces/xplan*-workspace/services -iname '*wms*.xml' -a ! -iname '*_metadata.xml' -exec sed -i 's|<wms:MaxWidth>3840</wms:MaxWidth>|<wms:MaxWidth>'"$XPLAN_SERVICES_WMS_MAXWIDTH"'</wms:MaxWidth>|g' {} \;
find xplan-workspaces/xplan*-workspace/services -iname '*wms*.xml' -a ! -iname '*_metadata.xml' -exec sed -i 's|<wms:MaxHeight>2160</wms:MaxHeight>|<wms:MaxHeight>'"$XPLAN_SERVICES_WMS_MAXHEIGHT"'</wms:MaxHeight>|g' {} \;

for crs in "${XPLAN_SERVICES_QUERY_CRS_ARR[@]}"
do
  find xplan-workspaces/xplan-services-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$crs'<\/QueryCRS>/g' {} \;
done
find xplan-workspaces/xplan-services-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$XPLAN_SERVICES_DEFAULT_CRS'<\/QueryCRS>/g' {} \;
find xplan-workspaces/xplan-services-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>//g' {} \;

echo "[$(date -Iseconds)] Configure XPlanSynWFS QueryCRS"
find xplan-workspaces/xplan-services-wfs-syn-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i '/<QueryCRS>EPSG:.*<\/QueryCRS>/d' {} \;
for crs in "${XPLAN_SERVICES_QUERY_CRS_ARR[@]}"
do
  find xplan-workspaces/xplan-services-wfs-syn-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$crs'<\/QueryCRS>/g' {} \;
done
find xplan-workspaces/xplan-services-wfs-syn-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$XPLAN_SERVICES_DEFAULT_CRS'<\/QueryCRS>/g' {} \;
find xplan-workspaces/xplan-services-wfs-syn-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>//g' {} \;

echo $XPLAN_SERVICES_API_KEY >> xplan-workspaces/config.apikey

# Rastertype
echo "[$(date -Iseconds)] Configured rastertype: $XPLAN_INIT_RASTERTYPE"
if [ $XPLAN_INIT_RASTERTYPE = "gdal" ]
then
  echo "[$(date -Iseconds)] Configure rastertype gdal"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=gdal/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplan-services-wms-workspace/gdal.ignore xplan-workspaces/xplan-services-wms-workspace/gdal.xml
  mv xplan-workspaces/xplan-services-wms-workspace/datasources/tile/dummy.ignore xplan-workspaces/xplan-services-wms-workspace/datasources/tile/dummy.xml
  mv xplan-workspaces/xplan-services-wms-workspace/datasources/tile/tilematrixset/dummy.ignore xplan-workspaces/xplan-services-wms-workspace/datasources/tile/tilematrixset/dummy.xml
  mv xplan-workspaces/xplan-services-wms-workspace/layers/dummyrasterlayer.ignore xplan-workspaces/xplan-services-wms-workspace/layers/dummyrasterlayer.xml
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>dummyrasterlayer/<LayerStoreId>dummyrasterlayer/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/dummyrasterlayer<\/LayerStoreId>-->/dummyrasterlayer<\/LayerStoreId>/g' {} \;
elif [ $XPLAN_INIT_RASTERTYPE = "geotiff" ]
then
  echo "[$(date -Iseconds)] Configure rastertype geotiff"
  rm xplan-workspaces/xplan-services-wms-workspace/datasources/tile/dummy.ignore
  rm xplan-workspaces/xplan-services-wms-workspace/datasources/tile/tilematrixset/dummy.ignore
  rm xplan-workspaces/xplan-services-wms-workspace/layers/dummyrasterlayer.ignore
else
  echo "[$(date -Iseconds)] Configure rastertype mapserver"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=mapserver/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplan-services-wms-workspace/layers/mapserver.ignore xplan-workspaces/xplan-services-wms-workspace/layers/mapserver.xml
  mv xplan-workspaces/xplan-services-wms-workspace/datasources/remoteows/mapserver.ignore xplan-workspaces/xplan-services-wms-workspace/datasources/remoteows/mapserver.xml
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>mapserver/<LayerStoreId>mapserver/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/mapserver<\/LayerStoreId>-->/mapserver<\/LayerStoreId>/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<Layer layerStore="mapserver"/<Layer layerStore="mapserver"/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterarchive<\/Layer>-->/planrasterarchive<\/Layer>/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterpre<\/Layer>-->/planrasterpre<\/Layer>/g' {} \;
  find xplan-workspaces/xplan-services-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planraster<\/Layer>-->/planraster<\/Layer>/g' {} \;
  sed -i 's|http://localhost:8080/mapserver|'$XPLAN_MAPSERVER_URL_INTERNAL'/mapserver|g' xplan-workspaces/xplan-services-wms-workspace/datasources/remoteows/mapserver.xml
fi

sed -i 's|validatorWmsEndpoint=|validatorWmsEndpoint='$XPLAN_VALIDATORWMS_URL_PUBLIC'\/xplan-validator-wms\/services\/wms|g' xplan-validator-config/validatorConfiguration.properties

# memory or sql
if [[ -z "${spring_profiles_active##*validatorwmssql*}" ]]
then
  echo "[$(date -Iseconds)] Configure XPlanValidatorWMS with database"
  sed -i 's|xplan-webservices-validator-wms-memory-workspace|xplan-webservices-validator-wms-sql-workspace|g' xplan-validator-workspaces/webapps.properties
fi

#copy example external codelist
XPLAN_INIT_EXAMPLE_CODELIST="${XPLAN_INIT_EXAMPLE_CODELIST:-disabled}"
if [ $XPLAN_INIT_EXAMPLE_CODELIST = "enabled" ]
then
  echo "[$(date -Iseconds)] Add example codelist"
  cp -r /xplan-volume-init/synthesizer/ xplan-manager-config/
fi

rm $INIT_STARTED_FILE
echo "Initialization finished at $(date)" > $MARKER_FILE 
cat $MARKER_FILE
