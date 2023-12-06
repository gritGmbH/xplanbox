#!/usr/bin/env bash
# to be run in a folder with pom.xml

xplanboxVersion=$(mvn -N help:evaluate -Dexpression=project.version -q -DforceStdout)

echo -n "xplanboxVersion=$([[ $xplanboxVersion == *SNAPSHOT ]] && echo 'latest' || echo $xplanboxVersion)
"