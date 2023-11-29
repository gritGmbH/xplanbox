#!/bin/sh

ALL_ADDITIONAL_ARGS=$(env | grep ^JAVA_ADDITIONAL_ | sed 's/^JAVA_ADDITIONAL_[[:alnum:]_]*=//' | tr '\n' ' ')

if [ -n "${ALL_ADDITIONAL_ARGS}" ]; then
    JAVA_OPTS=$(eval "echo $ALL_ADDITIONAL_ARGS")
    echo "xPlanBox JAVA_OPTS set: $JAVA_OPTS"
fi

exec java $JAVA_OPTS -jar /xplanbox/app.war
