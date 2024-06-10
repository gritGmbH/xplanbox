FROM ubuntu:focal
ARG TOMCAT_VERSION="10.1.24"
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
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
ENV TZ=Europe/Berlin
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# apt-transport-https (needed to install java 11)
RUN apt update && apt -y install apt-transport-https unzip wget


# install jdk
RUN DEBIAN_FRONTEND=noninteractive apt -y install openjdk-17-jdk

# install tomcat and allow non-root usage
RUN wget https://archive.apache.org/dist/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz --quiet -O /tmp/tomcat.tar.gz \
	&& tar xfz /tmp/tomcat.tar.gz --directory /usr/local \
	&& mv /usr/local/apache-tomcat-${TOMCAT_VERSION} /usr/local/tomcat \
	&& rm /tmp/tomcat.tar.gz \
	&& chmod -R a+rX /usr/local/tomcat \
	&& chmod a+w /usr/local/tomcat/conf /usr/local/tomcat/logs /usr/local/tomcat/webapps /usr/local/tomcat/work /usr/local/tomcat/temp \
	&& rm -rf /usr/local/tomcat/webapps/* \
	&& cat /usr/local/tomcat/conf/server.xml | tr '\n' '|' | sed 's|<Valve [^>]*/>||g' | tr '|' '\n' > /tmp/server.xml && mv /tmp/server.xml /usr/local/tomcat/conf

ADD setenv.sh /usr/local/tomcat/bin/
ADD logging.properties /usr/local/tomcat/conf/

EXPOSE 8080

# install gdal plus jni
RUN apt update && apt -y install libgdal-java

ENV JMX_EXPORTER_DIR=/xplanbox/prometheus

RUN mkdir -p $JMX_EXPORTER_DIR
COPY target/deps/jmx_prometheus_javaagent* $JMX_EXPORTER_DIR
ADD jmx-exporter.config.yaml $JMX_EXPORTER_DIR

ENV TOMCAT_ADDITIONAL_ARG_JMX_EXPORTER='-javaagent:$JMX_EXPORTER_DIR/jmx_prometheus_javaagent-0.19.0.jar=12345:$JMX_EXPORTER_DIR/jmx-exporter.config.yaml'
