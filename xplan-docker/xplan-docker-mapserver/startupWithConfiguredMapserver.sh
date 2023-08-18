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

XPLAN_DB_HOSTNAME="${XPLAN_DB_HOSTNAME:-xplan-db}"
XPLAN_DB_PORT="${XPLAN_DB_PORT:-5432}"
XPLAN_DB_NAME="${XPLAN_DB_NAME:-xplanbox}"
XPLAN_DB_USER="${XPLAN_DB_USER:-postgres}"
XPLAN_DB_PASSWORD="${XPLAN_DB_PASSWORD:-postgres}"

XPLAN_S3_ACCESS_KEY="${XPLAN_S3_ACCESS_KEY:-tobedefined}"
XPLAN_S3_SECRET_ACCESS_KEY="${XPLAN_S3_SECRET_ACCESS_KEY:-tobedefined}"
XPLAN_S3_REGION="${XPLAN_S3_REGION:-eu-central-1}"
XPLAN_S3_BUCKET_NAME="${XPLAN_S3_BUCKET_NAME:-tobedefined}"
XPLAN_S3_ENDPOINT="${XPLAN_S3_ENDPOINT}"
# Remove http:// / https:// from endpoint URL
XPLAN_S3_ENDPOINT=$(sed -E 's/^(https|http):\/\///' <<< $XPLAN_S3_ENDPOINT)

######################################
# Update content of mapserver config #
######################################

sed -i 's|CONNECTION "user=postgres password=postgres dbname=xplanbox host=xplan-db port=5432"|CONNECTION "user='$XPLAN_DB_USER' password='$XPLAN_DB_PASSWORD' dbname='$XPLAN_DB_NAME' host='$XPLAN_DB_HOSTNAME' port='$XPLAN_DB_PORT'"|g' $MS_MAPFILE

if [[ -z "${spring_profiles_active##*s3img*}" ]]
then
  echo "[$(date -Iseconds)] MapServer storage type is S3"
  sed -i 's|# CONFIG "AWS_ACCESS_KEY_ID" "XXX"|CONFIG "AWS_ACCESS_KEY_ID" "'$XPLAN_S3_ACCESS_KEY'"|g' $MS_MAPFILE
  sed -i 's|# CONFIG "AWS_SECRET_ACCESS_KEY" "XXX"|CONFIG "AWS_SECRET_ACCESS_KEY" "'$XPLAN_S3_SECRET_ACCESS_KEY'"|g' $MS_MAPFILE
  sed -i 's|# CONFIG "AWS_REGION" "eu-central-1"|CONFIG "AWS_REGION" "'$XPLAN_S3_REGION'"|g' $MS_MAPFILE
  if [ ! $XPLAN_S3_ENDPOINT = "" ]
    then
    sed -i 's|# CONFIG "AWS_S3_ENDPOINT" "s3.amazonaws.com"|CONFIG "AWS_S3_ENDPOINT" "'$XPLAN_S3_ENDPOINT'"|g' $MS_MAPFILE
  fi
  sed -i 's|# SHAPEPATH "/vsis3/bucket_name/"|SHAPEPATH "/vsis3/'$XPLAN_S3_BUCKET_NAME'/"|g' $MS_MAPFILE
  sed -i 's|SHAPEPATH "/etc/mapserver/data/"|# SHAPEPATH "/etc/mapserver/data/"|g' $MS_MAPFILE
fi

echo "[$(date -Iseconds)] Start mapserver..."
exec /usr/local/bin/start-server