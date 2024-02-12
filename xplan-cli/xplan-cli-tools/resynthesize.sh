#!/bin/sh

echo "xplan-cli-docker initialization..."

export XPLANBOX_VOLUMES=/xplanbox/xplan-volumes

/xplan-volume-init/setupVolumes.sh

export DEEGREE_WORKSPACE_ROOT=$XPLANBOX_VOLUMES/xplan-workspaces
export XPLANBOX_CONFIG=$XPLANBOX_VOLUMES/xplan-manager-config

echo ""
echo "reSynthesizer..."

exec /xplanbox/xplan-cli/bin/xpb admin resynthesize