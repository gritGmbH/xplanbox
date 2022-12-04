ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat:$XPLANBOX_VERSION
ARG WEB_CONTEXT=ROOT

# add xplan-api-validator
RUN mkdir /usr/local/tomcat/webapps/$WEB_CONTEXT
ADD target/exploded-webapp /usr/local/tomcat/webapps/$WEB_CONTEXT

