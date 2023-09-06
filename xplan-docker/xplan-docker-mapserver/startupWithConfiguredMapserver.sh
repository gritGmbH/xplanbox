#!/bin/bash

echo "MapServer config initialization..."

if [ -z ${MS_MAPFILE+x} ]; then
	echo "ERROR: environment variable MS_MAPFILE not set!"
	exit 1
fi

if [ -f "$MS_MAPFILE" ]; then
	echo "[$(date -Iseconds)] Init already done ($MS_MAPFILE)"
	exec /usr/local/bin/start-server
	exit 0
fi

echo "[$(date -Iseconds)] Initializing mapserver config ..."

cp /xplan-docker-mapserver/xplan-mapserver-config/mapserver.map $MS_MAPFILE

XPLAN_S3_BUCKET_NAME="${XPLAN_S3_BUCKET_NAME:-tobedefined}"

######################################
# Update content of mapserver config #
######################################

if [[ -z "${spring_profiles_active##*s3img*}" ]]
then
  echo "[$(date -Iseconds)] MapServer storage type is S3"
  sed -i 's|SHAPEPATH "/etc/mapserver/data/"|SHAPEPATH "/vsis3/'$XPLAN_S3_BUCKET_NAME'/"|g' $MS_MAPFILE
fi

echo "[$(date -Iseconds)] Start mapserver..."
exec /usr/local/bin/start-server