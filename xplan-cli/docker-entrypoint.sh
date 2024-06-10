#!/bin/bash
set -e

echo "xplan-cli-docker initialization..."

/xplan-volume-init/setupVolumes.sh

export DEEGREE_WORKSPACE_ROOT=$XPLANBOX_VOLUMES/xplan-workspaces
export XPLANBOX_CONFIG=$XPLANBOX_VOLUMES/xplan-manager-config

echo "Execute: xpb $@"

exec /xplanbox/xplan-cli/bin/xpb "$@"