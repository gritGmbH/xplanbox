#!/bin/bash

set -e
executionMode=$1 

echo "[$(date -Iseconds)] MapProxy config initialization..."

defined_envs="$(printf '${%s} ' $(env | cut -d'=' -f1))"
envsubst "$defined_envs" < /xplan-docker-mapproxy/xplan-mapproxy-config/mapproxy.yaml > /srv/mapproxy/mapproxy.yaml
envsubst "$defined_envs" < /xplan-docker-mapproxy/xplan-mapproxy-config/seed.yaml > /srv/mapproxy/seed.yaml

if [ "$executionMode" == "seed" ]
then
	echo "[$(date -Iseconds)] MapProxy seed..."
	exec mapproxy-seed -f /srv/mapproxy/mapproxy.yaml -s /srv/mapproxy/seed.yaml -c $XPLAN_MAPPROXY_RESEED_CONCURRENCY --seed bpreseed,fpreseed
else
	echo "[$(date -Iseconds)] MapProxy startup..."
	exec uwsgi --ini /srv/mapproxy/uwsgi.ini
fi
