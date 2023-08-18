#!/bin/bash

ALL_ADDITIONAL_ARGS=$(env | grep ^TOMCAT_ADDITIONAL_ | sed 's/^TOMCAT_ADDITIONAL_[[:alnum:]_]*=//' | tr '\n' ' ')

if [ -n "${ALL_ADDITIONAL_ARGS}" ]; then
    export TOMCAT_EXTRA_JAVA_OPTS="$ALL_ADDITIONAL_ARGS"
    echo "xPlanBox TOMCAT_EXTRA_JAVA_OPTS set -> $TOMCAT_EXTRA_JAVA_OPTS"
fi

. /opt/bitnami/scripts/tomcat-env.original.sh
