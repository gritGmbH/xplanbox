ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat:$XPLANBOX_VERSION
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG WEB_CONTEXT=ROOT
ARG XPLANBOX_VERSION=latest

# see https://github.com/opencontainers/image-spec/blob/main/annotations.md#pre-defined-annotation-keys
LABEL "org.opencontainers.image.created"="$BUILD_DATE" \
	"org.opencontainers.image.description"="ozgxplanung xPlanBox component" \
	"org.opencontainers.image.licenses"="GNU Affero General Public License & others" \
	"org.opencontainers.image.ref.name"="$DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.revision"="$GIT_REVISION" \
	"org.opencontainers.image.title"="ozgxplanung - $DOCKER_IMAGE_NAME" \
	"org.opencontainers.image.url"="https://gitlab.opencode.de/diplanung/ozgxplanung" \
	"org.opencontainers.image.vendor"="lat/lon GmbH" \
	"org.opencontainers.image.version"="$XPLANBOX_VERSION"

# set environment variables
ENV TOMCAT_ADDITIONAL_ARG_APP="-DXPLANBOX_CONFIG=/xplanbox/xplan-dokumenten-config -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Duser.timezone=Europe/Berlin"
ENV TOMCAT_ADDITIONAL_ARG_JAVA17_EXPORTS="--add-exports=java.desktop/com.sun.imageio.spi=ALL-UNNAMED"
ENV DEEGREE_WORKSPACE_ROOT=/xplanbox/deegree

USER root

# add xplan-api-dokumente
ADD target/xplan-api-dokumente-*.war /tmp/
RUN mkdir /opt/bitnami/tomcat/webapps/$WEB_CONTEXT \
    && cd /opt/bitnami/tomcat/webapps/$WEB_CONTEXT \
    && jar xf /tmp/xplan-api-dokumente-*.war \
    && rm /tmp/xplan-api-dokumente-*.war

USER 1001