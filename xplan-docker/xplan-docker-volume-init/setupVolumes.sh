#!/bin/sh

echo "XPlan volume initialization..."

if [ -z ${XPLANBOX_VOLUMES+x} ]; then 
	echo "ERROR: environment variable XPLANBOX_VOLUMES not set!"
	exit 1
fi

MARKER_FILE=$XPLANBOX_VOLUMES/init-marker.txt

if [ -f "$MARKER_FILE" ]; then
  if [[ -n "$XPLANBOX_VOLUME_INIT" && $XPLANBOX_VOLUME_INIT = "reset" ]]; then
    echo "[$(date -Iseconds)] Reset of existing dir $XPLANBOX_VOLUMES forced ..."
    rm $MARKER_FILE
    rm -rf $XPLANBOX_VOLUMES/xplan-*
  else
    echo "[$(date -Iseconds)] Init already done in $XPLANBOX_VOLUMES ($(cat $MARKER_FILE))"
    exit 0
	fi
fi

echo "[$(date -Iseconds)] Initializing dir $XPLANBOX_VOLUMES ..."

mkdir -p $XPLANBOX_VOLUMES

# handle case of concurrently running inits
INIT_STARTED_FILE=$XPLANBOX_VOLUMES/init-start-marker.txt
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

XPLANWMS_HOST_NAME="${XPLANWMS_HOST_NAME:-http://localhost:8083}"
XPLANVALIDATORWMS_HOST_NAME="${XPLANVALIDATORWMS_HOST_NAME:-http://localhost:8088}"
XPLANMAPSERVER_HOST_NAME="${XPLANMAPSERVER_HOST_NAME:-http://xplan-mapserver:8080}"

XPLANDB_HOST_NAME="${XPLANDB_HOST_NAME:-xplan-db}"
XPLANDB_PORT="${XPLANDB_PORT:-5432}"
XPLANDB="$XPLANDB_HOST_NAME:$XPLANDB_PORT"

# by default mapserver
RASTERTYPE="${XPLAN_RASTERTYPE:-mapserver}"

INSPIRE_PLU="${INSPIRE_PLU:-disabled}"

#############################
# Update content of volumes #
#############################

cd $XPLANBOX_VOLUMES

sed -i 's/apiUrl=/apiUrl=http:\/\/xplanbox.lat-lon.de/g' xplan-validator-config/validatorApiConfiguration.properties
sed -i 's/apiUrl=/apiUrl=http:\/\/xplanbox.lat-lon.de/g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's/wmsUrl=/wmsUrl=http:\/\/xplanbox.lat-lon.de\/xplan-wms/g' xplan-manager-config/managerApiConfiguration.properties
sed -i 's/workspaceReloadUrls=/workspaceReloadUrls=http:\/\/xplan-services:8080\/xplan-wms/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadUser=/workspaceReloadUser=deegree/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/workspaceReloadPassword=/workspaceReloadPassword=deegree/g' xplan-manager-config/managerConfiguration.properties
sed -i 's/pathToHaleCli=/pathToHaleCli=\/hale\/bin\/hale/g' xplan-manager-config/managerConfiguration.properties
sed -i 's|http://localhost:8080|'$XPLANWMS_HOST_NAME'|g' xplan-manager-config/managerWebConfiguration.properties
if [ $INSPIRE_PLU = "enabled" ]
then
  sed -i 's/activatePublishingInspirePlu=false/activatePublishingInspirePlu=true/g' xplan-manager-config/managerWebConfiguration.properties
fi

find -iname xplan.xml -exec sed -i 's/localhost:5432/'$XPLANDB'/g' {} \;
find -iname xplan.xml -exec sed -i 's/"xplanbox"/"postgres"/g' {} \;
sed -i 's/localhost:5432/'$XPLANDB'/g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's/"xplanbox"/"postgres"/g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's/http:\/\/localhost:8080\/xplan-wms/https:\/\/xplanbox.lat-lon.de\/xplan-wms/g' xplan-workspaces/xplansyn-wms-workspace/services/html.gfi
sed -i 's/localhost:5432/'$XPLANDB'/g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's/"xplanbox"/"postgres"/g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml

if [ $RASTERTYPE = "gdal" ]
then
  echo "[$(date -Iseconds)] Configure rastertype gdal"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=gdal/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplansyn-wms-workspace/gdal.ignore xplan-workspaces/xplansyn-wms-workspace/gdal.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.ignore xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.xml
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>dummyrasterlayer/<LayerStoreId>dummyrasterlayer/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/dummyrasterlayer<\/LayerStoreId>-->/dummyrasterlayer<\/LayerStoreId>/g' {} \;
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
  sed -i 's|http://localhost:8080/mapserver|'$XPLANMAPSERVER_HOST_NAME'/mapserver|g' xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.xml
fi

sed -i 's|validatorWmsEndpoint=|validatorWmsEndpoint='$XPLANVALIDATORWMS_HOST_NAME'\/xplan-validator-wms\/services\/wms|g' xplan-validator-config/validatorConfiguration.properties

BERLINER_PROFILE="${BERLINER_PROFILE:-disabled}"
if [ $BERLINER_PROFILE = "enabled" ]
then
  BERLINER_PROFILE_VERSION="${BERLINER_PROFILE_VERSION:-0.3}"
  BERLINER_PROFILE_URL=https://gitlab.opencode.de/api/v4/projects/397/packages/maven/de/xleitstelle/xplanung/regeln-berlin/$BERLINER_PROFILE_VERSION/regeln-berlin-$BERLINER_PROFILE_VERSION.jar
  echo "[$(date -Iseconds)] Add Berliner Profile: $BERLINER_PROFILE_URL"
  # Copy berliner profile
  curl $BERLINER_PROFILE_URL -s -o /tmp/regeln-berlin.jar
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
VALIDATOR_WMS="${VALIDATOR_WMS:-memory}"
if [ $VALIDATOR_WMS = "sql" ]
then
  sed -i 's|xplan-validator-wms-memory-workspace|xplan-validator-wms-sql-workspace|g' xplan-validator-workspaces/webapps.properties
fi

rm $INIT_STARTED_FILE
echo "Initialization finished at $(date)" > $MARKER_FILE 
cat $MARKER_FILE
