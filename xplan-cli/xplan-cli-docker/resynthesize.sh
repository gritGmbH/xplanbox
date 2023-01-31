#!/bin/sh

echo "xplan-cli-docker initialization..."

export XPLANBOX_VOLUMES=/tmp/foo

/xplan-volume-init/setupVolumes.sh


export DEEGREE_WORKSPACE_ROOT=$XPLANBOX_VOLUMES/xplan-workspaces

echo ""
echo "reSynthesizer..."

exec /xplanbox/xplan-update-data/bin/reSynthesizer -c $XPLANBOX_VOLUMES/xplan-manager-config/