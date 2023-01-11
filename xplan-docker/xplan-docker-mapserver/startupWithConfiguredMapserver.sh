#!/bin/sh

echo "MapServer config initialization..."

if [ -z ${MS_MAPFILE+x} ]; then
	echo "ERROR: environment variable MS_MAPFILE not set!"
	exit 1
fi

if [ -f "$MS_MAPFILE" ]; then
	echo "[$(date -Iseconds)] Init already done ($MS_MAPFILE)"
	sh /usr/local/bin/start-server
	exit 0
fi

echo "[$(date -Iseconds)] Initializing mapserver config ..."

cp /xplan-docker-mapserver/xplan-mapserver-config/mapserver.map $MS_MAPFILE

MAPSERVER_PG_HOST="${MAPSERVER_PG_HOST:-xplan-db}"
MAPSERVER_PG_PORT="${MAPSERVER_PG_PORT:-5432}"
MAPSERVER_PG_DB="${MAPSERVER_PG_DB:-xplanbox}"
MAPSERVER_PG_USER="${MAPSERVER_PG_USER:-postgres}"
MAPSERVER_PG_PASSWORD="${MAPSERVER_PG_PASSWORD:-postgres}"

# filesystem or S3
MAPSERVER_STORAGETYPE="${MAPSERVER_STORAGETYPE:-filesystem}"
#MAPSERVER_S3_ACCESS_KEY
#MAPSERVER_S3_SECRET_ACCESS_KEY
MAPSERVER_S3_REGION="${MAPSERVER_S3_REGION:-eu-central-1}"
#MAPSERVER_S3_ENDPOINT
#MAPSERVER_S3_BUCKET_NAME

echo "[$(date -Iseconds)] MapServer storage type is $MAPSERVER_STORAGETYPE"

######################################
# Update content of mapserver config #
######################################

sed -i 's|CONNECTION "user=postgres password=postgres dbname=xplanbox host=xplan-db port=5432"|CONNECTION "user='$MAPSERVER_PG_USER' password='$MAPSERVER_PG_PASSWORD' dbname='$MAPSERVER_PG_DB' host='$MAPSERVER_PG_HOST' port='$MAPSERVER_PG_PORT'"|g' $MS_MAPFILE

if [ $MAPSERVER_STORAGETYPE = "s3" ]
then
  sed -i 's|# CONFIG "AWS_ACCESS_KEY_ID" "XXX"|CONFIG "AWS_ACCESS_KEY_ID" "'$MAPSERVER_S3_ACCESS_KEY'"|g' $MS_MAPFILE
  sed -i 's|# CONFIG "AWS_SECRET_ACCESS_KEY" "XXX"|CONFIG "AWS_SECRET_ACCESS_KEY" "'$MAPSERVER_S3_SECRET_ACCESS_KEY'"|g' $MS_MAPFILE
  sed -i 's|# CONFIG "AWS_REGION" "eu-central-1"|CONFIG "AWS_REGION" "'$MAPSERVER_S3_REGION'"|g' $MS_MAPFILE
  if [ ! $MAPSERVER_S3_ENDPOINT = "" ]
    then
    sed -i 's|# CONFIG "AWS_S3_ENDPOINT" "s3.amazonaws.com"|CONFIG "AWS_S3_ENDPOINT" "'MAPSERVER_S3_ENDPOINT'"|g' $MS_MAPFILE
  fi
  sed -i 's|# SHAPEPATH "/vsis3/bucket_name/"|SHAPEPATH "/vsis3/'$MAPSERVER_S3_BUCKET_NAME'/"|g' $MS_MAPFILE
  sed -i 's|SHAPEPATH "/etc/mapserver/data/"|# SHAPEPATH "/etc/mapserver/data/"|g' $MS_MAPFILE
fi

echo "start mapserver..."
exec /usr/local/bin/start-server