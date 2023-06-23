ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat:$XPLANBOX_VERSION
ARG WEB_CONTEXT=ROOT

# set environment variables
ENV TOMCAT_ADDITIONAL_ARG_APP="-DXPLANBOX_CONFIG=/xplanbox/xplan-validator-config/ -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"
ENV DEEGREE_WORKSPACE_ROOT=/xplanbox/deegree

USER root

# add xplan-api-validator
ADD target/xplan-api-validator-*.war /tmp/
RUN mkdir /opt/bitnami/tomcat/webapps/$WEB_CONTEXT \
    && cd /opt/bitnami/tomcat/webapps/$WEB_CONTEXT \
    && jar xf /tmp/xplan-api-validator-*.war \
    && rm /tmp/xplan-api-validator-*.war

USER 1001