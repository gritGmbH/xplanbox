#!/bin/bash
set -e

exec /liquibase/docker-entrypoint.sh --search-path=changelog --changelog-file=changelog_xplandb.yaml --url=$XPLAN_JDBC_URL --username=$XPLAN_DB_INIT_USER --password=$XPLAN_DB_INIT_PASSWORD \
    update -Dxplan.db.user=$XPLAN_DB_USER -Dxplan.srid=$XPLAN_SERVICES_DEFAULT_CRS_SRID

