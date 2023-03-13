#!/bin/bash

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
    rm $MARKER_FILE $INIT_STARTED_FILE
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
chmod -R a+w $XPLANBOX_VOLUMES

XPLAN_WMS_URL_PUBLIC="${XPLAN_WMS_URL_PUBLIC:-tobedefined}"
XPLAN_VALIDATORWMS_URL_PUBLIC="${XPLAN_VALIDATORWMS_URL_PUBLIC:-tobedefined}"
XPLAN_MANAGERAPI_URL_PUBLIC=${XPLAN_MANAGERAPI_URL_PUBLIC:-xplan-api-manager}
XPLAN_VALIDATORAPI_URL_PUBLIC=${XPLAN_VALIDATORAPI_URL_PUBLIC:-xplan-api-validator}
XPLAN_MAPSERVER_URL_INTERNAL="${XPLAN_MAPSERVER_URL_INTERNAL:-http://xplan-mapserver}"
XPLAN_SERVICES_URL_INTERNAL=${XPLAN_SERVICES_URL_INTERNAL:-http://xplan-services}
XPLAN_SERVICES_API_USER=${XPLAN_SERVICES_API_USER:-deegree}
XPLAN_SERVICES_API_PASSWORT=${XPLAN_SERVICES_API_PASSWORT:-deegree}
XPLAN_MANAGER_WORKSPACE_RELOAD_ACTION=${XPLAN_MANAGER_WORKSPACE_RELOAD_ACTION:-PLANWERKWMS}
XPLAN_WMS_URL_INTERNAL=${XPLAN_WMS_URL_INTERNAL:-xplan-services}

XPLAN_DB_HOSTNAME="${XPLAN_DB_HOSTNAME:-tobedefined}"
XPLAN_DB_PORT="${XPLAN_DB_PORT:-5432}"
XPLAN_DB_NAME="${XPLAN_DB_NAME:-xplanbox}"
XPLAN_DB_USER="${XPLAN_DB_USER:-tobedefined}"
XPLAN_DB_PASSWORD="${XPLAN_DB_PASSWORD:-tobedefined}"
XPLAN_DB="$XPLAN_DB_HOSTNAME:$XPLAN_DB_PORT/$XPLAN_DB_NAME"

XPLAN_SERVICES_DEFAULT_CRS="${XPLAN_SERVICES_DEFAULT_CRS:-EPSG:25832}"
XPLAN_SERVICES_QUERY_CRS="${XPLAN_SERVICES_QUERY_CRS:-EPSG:4326 EPSG:3857 EPSG:25833}"
XPLAN_SERVICES_QUERY_CRS_ARR=($XPLAN_SERVICES_QUERY_CRS)

XPLAN_INIT_RASTERTYPE="${XPLAN_INIT_RASTERTYPE:-mapserver}"
XPLAN_INIT_INSPIREPLU="${XPLAN_INIT_INSPIREPLU:-disabled}"

XPLAN_S3_PUBLIC_URL="${XPLAN_S3_PUBLIC_URL}"

#############################
# Update content of volumes #
#############################

cd $XPLANBOX_VOLUMES

sed -i 's|apiUrl=|apiUrl='$XPLAN_VALIDATORAPI_URL_PUBLIC'|g' xplan-validator-config/validatorApiConfiguration.properties
sed -i 's|apiUrl=|apiUrl='$XPLAN_MANAGERAPI_URL_PUBLIC'|g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's|wmsUrl=|wmsUrl='$XPLAN_WMS_URL_INTERNAL'/xplan-wms|g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's|workspaceReloadUrls=|workspaceReloadUrls='$XPLAN_SERVICES_URL_INTERNAL'/xplan-wms|g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadUser=/workspaceReloadUser='$XPLAN_SERVICES_API_USER'/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadPassword=/workspaceReloadPassword='$XPLAN_SERVICES_API_PASSWORT'/g' xplan-manager-config/managerConfiguration.properties
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

sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml

if [[ -z "${spring_profiles_active##*s3doc*}" ]]
then
  echo "[$(date -Iseconds)] Document storage type is S3"
  sed -i 's|var S3_URL;|var S3_URL="'$XPLAN_S3_PUBLIC_URL'";|g' xplan-workspaces/xplansyn-wms-workspace/services/html.gfi
else
  sed -i 's|http://localhost:8080/xplan-wms|'$XPLAN_WMS_URL_PUBLIC'/xplan-wms|g' xplan-workspaces/xplansyn-wms-workspace/services/html.gfi
fi

sed -i 's|localhost:5432/xplanbox|'$XPLAN_DB'|g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's|name="username" value="xplanbox"|name="username" value="'$XPLAN_DB_USER'"|g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's|name="password" value="xplanbox"|name="password" value="'$XPLAN_DB_PASSWORD'"|g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml

echo "[$(date -Iseconds)] Configure XPlanServices StorageCRS"
find xplan-workspaces/xplan-manager-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplan-wfs-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplansyn-wms-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
find xplan-workspaces/xplansyn-wfs-workspace/datasources/feature -iname *.xml -exec sed -i 's|EPSG:25832</StorageCRS>|'$XPLAN_SERVICES_DEFAULT_CRS'</StorageCRS>|g' {} \;
echo "[$(date -Iseconds)] Configure XPlanWMS CRS"
find xplan-workspaces/xplansyn-wms-workspace/themes -iname *.xml -exec sed -i 's/<s:CRS>EPSG:25832 EPSG:25833 EPSG:31466 EPSG:31467 EPSG:31468 EPSG:31469 EPSG:4258 EPSG:4326 CRS:84 EPSG:4839<\/s:CRS>/<s:CRS>'"$XPLAN_SERVICES_DEFAULT_CRS $( echo ${XPLAN_SERVICES_QUERY_CRS_ARR[@]} )"'<\/s:CRS>/g' {} \;
echo "[$(date -Iseconds)] Configure XPlanWFS QueryCRS"
find xplan-workspaces/xplan-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i '/<QueryCRS>EPSG:.*<\/QueryCRS>/d' {} \;
for crs in "${XPLAN_SERVICES_QUERY_CRS_ARR[@]}"
do
  find xplan-workspaces/xplan-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$crs'<\/QueryCRS>/g' {} \;
done
find xplan-workspaces/xplan-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$XPLAN_SERVICES_DEFAULT_CRS'<\/QueryCRS>/g' {} \;
find xplan-workspaces/xplan-wfs-workspace/services -iname wfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>//g' {} \;

echo "[$(date -Iseconds)] Configure XPlanSynWFS QueryCRS"
find xplan-workspaces/xplansyn-wfs-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i '/<QueryCRS>EPSG:.*<\/QueryCRS>/d' {} \;
for crs in "${XPLAN_SERVICES_QUERY_CRS_ARR[@]}"
do
  find xplan-workspaces/xplansyn-wfs-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$crs'<\/QueryCRS>/g' {} \;
done
find xplan-workspaces/xplansyn-wfs-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>/<QueryCRS>CRS:84<\/QueryCRS><QueryCRS>'$XPLAN_SERVICES_DEFAULT_CRS'<\/QueryCRS>/g' {} \;
find xplan-workspaces/xplansyn-wfs-workspace/services -iname xplansynwfs*.xml -not -iname *_metadata.xml -exec sed -i 's/<QueryCRS>CRS:84<\/QueryCRS>//g' {} \;

# Rastertype
echo "[$(date -Iseconds)] Configured rastertype: $XPLAN_INIT_RASTERTYPE"
if [ $XPLAN_INIT_RASTERTYPE = "gdal" ]
then
  echo "[$(date -Iseconds)] Configure rastertype gdal"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=gdal/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplansyn-wms-workspace/gdal.ignore xplan-workspaces/xplansyn-wms-workspace/gdal.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.ignore xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.xml
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>dummyrasterlayer/<LayerStoreId>dummyrasterlayer/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/dummyrasterlayer<\/LayerStoreId>-->/dummyrasterlayer<\/LayerStoreId>/g' {} \;
elif [ $XPLAN_INIT_RASTERTYPE = "geotiff" ]
then
  echo "[$(date -Iseconds)] Configure rastertype geotiff"
  rm xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.ignore
  rm xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.ignore
  rm xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.ignore
else
  echo "[$(date -Iseconds)] Configure rastertype mapserver"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=mapserver/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplansyn-wms-workspace/layers/mapserver.ignore xplan-workspaces/xplansyn-wms-workspace/layers/mapserver.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.xml
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>mapserver/<LayerStoreId>mapserver/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/mapserver<\/LayerStoreId>-->/mapserver<\/LayerStoreId>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<Layer layerStore="mapserver"/<Layer layerStore="mapserver"/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterarchive<\/Layer>-->/planrasterarchive<\/Layer>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterpre<\/Layer>-->/planrasterpre<\/Layer>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planraster<\/Layer>-->/planraster<\/Layer>/g' {} \;
  sed -i 's|http://localhost:8080/mapserver|'$XPLAN_MAPSERVER_URL_INTERNAL'/mapserver|g' xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.xml
fi

sed -i 's|validatorWmsEndpoint=|validatorWmsEndpoint='$XPLAN_VALIDATORWMS_URL_PUBLIC'\/xplan-validator-wms\/services\/wms|g' xplan-validator-config/validatorConfiguration.properties

XPLAN_INIT_BERLINERPROFIL="${XPLAN_INIT_BERLINERPROFIL:-disabled}"
if [ $XPLAN_INIT_BERLINERPROFIL = "enabled" ]
then
  XPLAN_INIT_BERLINERPROFIL_VERSION="${XPLAN_INIT_BERLINERPROFIL_VERSION:-0.3}"
  BERLINERPROFIL_URL=https://gitlab.opencode.de/api/v4/projects/397/packages/maven/de/xleitstelle/xplanung/regeln-berlin/$XPLAN_INIT_BERLINERPROFIL_VERSION/regeln-berlin-$XPLAN_INIT_BERLINERPROFIL_VERSION.jar
  echo "[$(date -Iseconds)] Add Berliner Profile: $BERLINERPROFIL_URL"
  # Copy berliner profile
  curl $BERLINERPROFIL_URL -s -o /tmp/regeln-berlin.jar
  # XPlanValidator
  mkdir $XPLANBOX_VOLUMES/xplan-validator-config/profiles
  unzip -q /tmp/regeln-berlin.jar -x "META-INF/*" -d $XPLANBOX_VOLUMES/xplan-validator-config/profiles
  # XPlanManager
  mkdir $XPLANBOX_VOLUMES/xplan-manager-config/profiles
  unzip -q /tmp/regeln-berlin.jar -x "META-INF/*" -d $XPLANBOX_VOLUMES/xplan-manager-config/profiles
  # cleanup
  rm /tmp/regeln-berlin.jar
fi

# memory or sql
if [[ -z "${spring_profiles_active##*validatorwmssql*}" ]]
then
  echo "[$(date -Iseconds)] Configure XPlanValidatorWMS with database"
  sed -i 's|xplan-validator-wms-memory-workspace|xplan-validator-wms-sql-workspace|g' xplan-validator-workspaces/webapps.properties
fi

rm $INIT_STARTED_FILE
echo "Initialization finished at $(date)" > $MARKER_FILE 
cat $MARKER_FILE
