#!/bin/bash

echo "MapServer config initialization..."

if [ -z ${MS_MAPFILE+x} ]; then
	echo "[$(date -Iseconds)] ERROR: environment variable MS_MAPFILE not set!"
	exit 1
fi

if [ -f "$MS_MAPFILE" ]; then
	echo "[$(date -Iseconds)] Init already done ($MS_MAPFILE)"
	exec /usr/local/bin/start-server
	exit 0
fi

echo "[$(date -Iseconds)] Initializing mapserver config ..."

echo "[$(date -Iseconds)] MapProxy config initialization..."

defined_envs="$(printf '${%s} ' $(env | cut -d'=' -f1))"
envsubst "$defined_envs" < /xplan-mapserver-docker/xplan-mapserver-config/mapserver.map > $MS_MAPFILE

XPLAN_SERVICES_DEFAULT_CRS_SRID="${XPLAN_SERVICES_DEFAULT_CRS_SRID:-25832}"
XPLAN_SERVICES_DEFAULT_CRS="${XPLAN_SERVICES_DEFAULT_CRS:-EPSG:25832}"
XPLAN_S3_BUCKET_NAME="${XPLAN_S3_BUCKET_NAME:-tobedefined}"
AWS_ACCESS_KEY_ID="${AWS_ACCESS_KEY_ID:-tobedefined}"
AWS_SECRET_ACCESS_KEY="${AWS_SECRET_ACCESS_KEY:-tobedefined}"
AWS_REGION="${AWS_REGION:-eu-central-1}"
AWS_S3_ENDPOINT="${AWS_S3_ENDPOINT}"

######################################
# Update content of mapserver config #
######################################

echo "[$(date -Iseconds)] Default CRS/srid is $XPLAN_SERVICES_DEFAULT_CRS/$XPLAN_SERVICES_DEFAULT_CRS_SRID"
sed -i 's|EPSG:25832|'$XPLAN_SERVICES_DEFAULT_CRS'|g' $MS_MAPFILE
sed -i 's|25832|'$XPLAN_SERVICES_DEFAULT_CRS_SRID'|g' $MS_MAPFILE

if [[ -z "${spring_profiles_active##*s3img*}" ]]
then
  echo "[$(date -Iseconds)] MapServer storage type is S3"
    sed -i 's|# CONFIG "AWS_ACCESS_KEY_ID" "XXX"|CONFIG "AWS_ACCESS_KEY_ID" "'$AWS_ACCESS_KEY_ID'"|g' $MS_MAPFILE
    sed -i 's|# CONFIG "AWS_SECRET_ACCESS_KEY" "XXX"|CONFIG "AWS_SECRET_ACCESS_KEY" "'$AWS_SECRET_ACCESS_KEY'"|g' $MS_MAPFILE
    sed -i 's|# CONFIG "AWS_REGION" "eu-central-1"|CONFIG "AWS_REGION" "'$AWS_REGION'"|g' $MS_MAPFILE
    if [ ! $AWS_S3_ENDPOINT = "" ]
      then
      sed -i 's|# CONFIG "AWS_S3_ENDPOINT" "s3.amazonaws.com"|CONFIG "AWS_S3_ENDPOINT" "'$AWS_S3_ENDPOINT'"|g' $MS_MAPFILE
    fi
  sed -i 's|SHAPEPATH "/etc/mapserver/data/"|SHAPEPATH "/vsis3/'$XPLAN_S3_BUCKET_NAME'/"|g' $MS_MAPFILE
fi

echo "[$(date -Iseconds)] Start mapserver..."
exec /usr/local/bin/start-server