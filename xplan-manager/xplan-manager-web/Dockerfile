ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG WEB_CONTEXT=xplan-manager-web
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
ENV TOMCAT_ADDITIONAL_ARG_APP="-Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin" \
    TOMCAT_ADDITIONAL_ARG_JAVA17_EXPORTS="--add-exports=java.desktop/com.sun.imageio.spi=ALL-UNNAMED" \
	XPLANBOX_CONFIG="/xplanbox/xplan-manager-config/"

# add xplan-manager-web directly unpacked
COPY target/xplan-manager-web-*.war /tmp/
RUN mkdir /usr/local/tomcat/webapps/$WEB_CONTEXT \
	&& cd /usr/local/tomcat/webapps/$WEB_CONTEXT \
	&& jar xf /tmp/xplan-manager-web-*.war \
	&& rm /tmp/xplan-manager-web-*.war

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
