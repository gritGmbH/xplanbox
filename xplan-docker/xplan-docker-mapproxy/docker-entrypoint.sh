#!/bin/bash

set -e

echo "[$(date -Iseconds)] MapProxy config initialization..."

defined_envs="$(printf '${%s} ' $(env | cut -d'=' -f1))"
envsubst "$defined_envs" < /xplan-docker-mapproxy/xplan-mapproxy-config/mapproxy.yaml > /srv/mapproxy/mapproxy.yaml
envsubst "$defined_envs" < /xplan-docker-mapproxy/xplan-mapproxy-config/seed.yaml > /srv/mapproxy/seed.yaml

echo "[$(date -Iseconds)] MapProxy startup..."
exec uwsgi --ini /srv/mapproxy/uwsgi.ini
