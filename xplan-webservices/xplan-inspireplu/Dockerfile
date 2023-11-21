FROM tomcat:9.0.83-jdk11-temurin-jammy
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG XPLANBOX_VERSION=latest
ARG WEB_CONTEXT=xplan-inspireplu

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
ENV TOMCAT_ADDITIONAL_ARG_APP="-Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"

# configure tomcat
ADD web.xml /usr/local/tomcat/conf/web.xml

RUN mkdir /usr/local/tomcat/webapps/$WEB_CONTEXT
ADD target/exploded-webapp /usr/local/tomcat/webapps/$WEB_CONTEXT

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
