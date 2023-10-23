#!/bin/bash

echo "[$(date -Iseconds)] MapProxy config initialization..."
cp /xplan-docker-mapproxy/xplan-mapproxy-config/mapproxy.yaml /srv/mapproxy/mapproxy.yaml

echo "[$(date -Iseconds)] MapProxy startup..."
exec "uwsgi --ini /srv/mapproxy/uwsgi.ini --plugin python311,cheaper_busyness"