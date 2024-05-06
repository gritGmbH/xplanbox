#!/bin/bash

set -e
executionMode=$1 

echo "[$(date -Iseconds)] MapProxy config initialization..."

defined_envs="$(printf '${%s} ' $(env | cut -d'=' -f1))"
envsubst "$defined_envs" < /xplan-mapproxy-docker/xplan-mapproxy-config/mapproxy.yaml > /srv/mapproxy/mapproxy.yaml
envsubst "$defined_envs" < /xplan-mapproxy-docker/xplan-mapproxy-config/seed.yaml > /srv/mapproxy/seed.yaml

XPLAN_SERVICES_METADATA_URL="${XPLAN_SERVICES_METADATA_URL}"
if [ -z "$XPLAN_SERVICES_METADATA_URL" ]
  then
    echo "[$(date -Iseconds)] Remove metadata configuration"
    sed -i '/\n/!N;/\n.*metadata/{$d;N;N;N;d};P;D' /srv/mapproxy/mapproxy.yaml
fi

if [ "$executionMode" == "seed" ]
then
	echo "[$(date -Iseconds)] MapProxy seed..."
	exec mapproxy-seed -f /srv/mapproxy/mapproxy.yaml -s /srv/mapproxy/seed.yaml -c $XPLAN_MAPPROXY_RESEED_CONCURRENCY --seed bpreseed_25832,fpreseed_25832,lpreseed_25832,rpreseed_25832,soreseed_25832,bpreseed_3857,fpreseed_3857,lpreseed_3857,rpreseed_3857,soreseed_3857
else
	echo "[$(date -Iseconds)] MapProxy startup..."
	exec uwsgi --ini /srv/mapproxy/uwsgi.ini
fi
