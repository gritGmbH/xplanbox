ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION
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
ENV TOMCAT_ADDITIONAL_ARG_APP="-Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# configure tomcat
ADD web.xml /usr/local/tomcat/conf/web.xml

# add all unpacked webapps (prepared by maven)
COPY maven /usr/local/tomcat/webapps/

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
