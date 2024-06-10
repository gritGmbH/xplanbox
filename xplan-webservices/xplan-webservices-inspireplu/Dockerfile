ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat:$XPLANBOX_VERSION
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
ENV DEEGREE_WORKSPACE_ROOT=/xplanbox/deegree \
    TOMCAT_ADDITIONAL_ARG_APP="-Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin" \
    TOMCAT_ADDITIONAL_ARG_JAVA17_EXPORTS="--add-exports=java.desktop/com.sun.imageio.spi=ALL-UNNAMED"

USER root

# configure tomcat
ADD web.xml /opt/bitnami/tomcat/conf/web.xml

RUN mkdir /opt/bitnami/tomcat/webapps/$WEB_CONTEXT
ADD target/exploded-webapp /opt/bitnami/tomcat/webapps/$WEB_CONTEXT

USER 1001

# run tomcat
CMD ["/opt/bitnami/tomcat/bin/catalina.sh", "run"]
