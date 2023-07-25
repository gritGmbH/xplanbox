#!/bin/bash

ALL_ADDITIONAL_ARGS=$(env | grep ^TOMCAT_ADDITIONAL_ | sed 's/^TOMCAT_ADDITIONAL_[[:alnum:]_]*=//' | tr '\n' ' ')

if [ -n "${ALL_ADDITIONAL_ARGS}" ]; then
    export CATALINA_OPTS="$ALL_ADDITIONAL_ARGS"
    echo "xPlanBox CATALINA_OPTS set -> $CATALINA_OPTS"
fi

