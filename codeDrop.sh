#!/bin/bash

set -e

# e.g. 1840
ticketIdNo=${1:-}
srcBranchOrTag=${2:-main}
opencodeBase=${3:-remote-opencode/main}
commitMessage="XPLANBOX-$ticketIdNo code drop"

echo "Creating code drop, ticketId is XPLANBOX-$ticketIdNo, source branch is $srcBranchOrTag"
branchName=xplanbox-$ticketIdNo-$(date +'%m-%d')-code-drop

xplanboxDir=$(dirname "$(readlink -f "$BASH_SOURCE")")

tmpDir=`mktemp -d`

pushd $tmpDir
echo "Cloning from $xplanboxDir..."
#git clone --single-branch -b $srcBranchOrTag git@bitbucket.org:latlon/xplanbox.git .
# faster: clone directory where xplanbox is already cloned and then fetch from bitbucket
git clone --single-branch -b $srcBranchOrTag $xplanboxDir .
git remote rename origin local-xplanbox

echo "Fetching from git@bitbucket.org:latlon/xplanbox.git..."
git remote add origin git@bitbucket.org:latlon/xplanbox.git
git fetch origin $srcBranchOrTag

echo "Fetching from OpenCoDE..."
git remote add remote-opencode git@gitlab.opencode.de:diplanung/ozgxplanung.git
git fetch remote-opencode main

echo "Preparing code drop"

git checkout -b $branchName $opencodeBase
echo "Branch $branchName created"

echo "Replacing content with $srcBranchOrTag..."
rm -rf *
git ls-files | grep '^\.' | xargs git rm
git checkout $srcBranchOrTag -- .
git add -A
lastMainCommit=`git rev-parse $srcBranchOrTag`
git commit -m "$commitMessage: $lastMainCommit"

echo "Pushing branch $branchName to OpenCoDE..."
git push remote-opencode $branchName

popd

echo "Done"
rm -rf $tmpDir
