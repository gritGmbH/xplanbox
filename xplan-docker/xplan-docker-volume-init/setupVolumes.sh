#!/bin/sh

echo "XPlan volume initialization..."

if [ -z ${XPLANBOX_VOLUMES+x} ]; then 
	echo "ERROR: environment variable XPLANBOX_VOLUMES not set!"
	exit 1
fi

MARKER_FILE=$XPLANBOX_VOLUMES/init-marker.txt

if [ -f "$MARKER_FILE" ]; then
	echo "[$(date -Iseconds)] Init already done in $XPLANBOX_VOLUMES ($(cat $MARKER_FILE))"
	exit 0 
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

# by default on same host
#XPLANWMS_HOST_NAME="${XPLANWMS_HOST_NAME:-localhost:8083}"
#XPLANVALIDATORWMS_HOST_NAME="${XPLANVALIDATORWMS_HOST_NAME:-localhost:8081}"

XPLANDB_HOST_NAME="${XPLANDB_HOST_NAME:-xplan-db}"
XPLANDB_PORT="${XPLANDB_PORT:-5432}"
XPLANDB="$XPLANDB_HOST_NAME:$XPLANDB_PORT"

# by default mapserver
RASTERTYPE="${XPLAN_RASTERTYPE:-mapserver}"

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
sed -i 's/activatePublishingInspirePlu=false/activatePublishingInspirePlu=true/g' xplan-manager-config/managerWebConfiguration.properties

find -iname xplan.xml -exec sed -i 's/localhost:5432/'$XPLANDB'/g' {} \;
find -iname xplan.xml -exec sed -i 's/"xplanbox"/"postgres"/g' {} \;
sed -i 's/localhost:5432/'$XPLANDB'/g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's/"xplanbox"/"postgres"/g' xplan-workspaces/xplan-manager-workspace/jdbc/inspireplu.xml
sed -i 's/http:\/\/localhost:8080\/xplan-wms/https:\/\/xplanbox.lat-lon.de\/xplan-wms/g' xplan-workspaces/xplansyn-wms-workspace/services/html.gfi
sed -i 's/localhost:5432/'$XPLANDB'/g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml
sed -i 's/"xplanbox"/"postgres"/g' xplan-inspireplu-workspaces/xplan-inspireplu-workspace/jdbc/inspireplu.xml

if [ $RASTERTYPE = "gdal" ]
then
  echo "Configure rastertype gdal"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=gdal/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplansyn-wms-workspace/gdal.ignore xplan-workspaces/xplansyn-wms-workspace/gdal.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/tile/tilematrixset/dummy.xml
  mv xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.ignore xplan-workspaces/xplansyn-wms-workspace/layers/dummyrasterlayer.xml
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>dummyrasterlayer/<LayerStoreId>dummyrasterlayer/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/dummyraster<\/LayerStoreId>-->/dummyraster<\/LayerStoreId>/g' {} \;
else
  echo "Configure rastertype mapserver"
  sed -i 's/rasterConfigurationType=geotiff/rasterConfigurationType=mapserver/g' xplan-manager-config/managerConfiguration.properties
  mv xplan-workspaces/xplansyn-wms-workspace/layers/mapserver.ignore xplan-workspaces/xplansyn-wms-workspace/layers/mapserver.xml
  mv xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.ignore xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.xml
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<LayerStoreId>mapserver/<LayerStoreId>mapserver/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/mapserver<\/LayerStoreId>-->/mapserver<\/LayerStoreId>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/<!--<Layer layerStore="mapserver"/<Layer layerStore="mapserver"/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterarchive<\/Layer>-->/planrasterarchive<\/Layer>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planrasterpre<\/Layer>-->/planrasterpre<\/Layer>/g' {} \;
  find xplan-workspaces/xplansyn-wms-workspace/themes -iname *raster.xml -exec sed -i 's/planraster<\/Layer>-->/planraster<\/Layer>/g' {} \;
  sed -i 's/http:\/\/localhost:8080\/mapserver/http:\/\/xplan-mapserver\/mapserver/g' xplan-workspaces/xplansyn-wms-workspace/datasources/remoteows/mapserver.xml
fi

sed -i 's/validatorWmsEndpoint=/validatorWmsEndpoint='$XPLANVALIDATORWMS_HOST_NAME'\/xplan-validator-wms\/services\/wms/g' xplan-validator-config/validatorConfiguration.properties


echo "Initialization finished at $(date)" > $MARKER_FILE 
cat $MARKER_FILE
