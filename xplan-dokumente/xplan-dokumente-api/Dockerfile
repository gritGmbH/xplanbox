ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat:$XPLANBOX_VERSION as builder

FROM eclipse-temurin:11.0.21_9-jre-alpine
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG WAR_FILE=target/*-repackaged.war
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

ENV TZ=Europe/Berlin

# copy jmx exporter from xplan-docker-tomcat until we switch to actuator based prometheus publishing
ENV JMX_EXPORTER_DIR=/xplanbox/prometheus
RUN mkdir -p $JMX_EXPORTER_DIR
COPY --from=builder $JMX_EXPORTER_DIR $JMX_EXPORTER_DIR

ENV JAVA_ADDITIONAL_ARG_JMX_EXPORTER='-javaagent:$JMX_EXPORTER_DIR/jmx_prometheus_javaagent-0.19.0.jar=12345:$JMX_EXPORTER_DIR/jmx-exporter.config.yaml'

# set environment variables
ENV DEEGREE_WORKSPACE_ROOT=/xplanbox/deegree \
	JAVA_ADDITIONAL_ARG_APP="-Duser.timezone=Europe/Berlin" \
	XPLANBOX_CONFIG="/xplanbox/xplan-dokumenten-config/"

COPY ${WAR_FILE} /xplanbox/app.war
COPY run.sh /xplanbox/

USER 1001

ENTRYPOINT ["/bin/sh", "/xplanbox/run.sh"]
