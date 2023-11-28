# latest linux/amd64 on 21.11.23
FROM camptocamp/mapserver@sha256:0ed39cd88641ea384f7ec3ae2cff02c841d7e20d5daea2c4d567e21e53d9c0c2
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
	"org.opencontainers.image.version"="$XPLANBOX_VERSION" \
	"maintainer"=""

ENV TZ=Europe/Berlin
RUN apt-get update && DEBIAN_FRONTEND=noninteractive apt-get -y install tzdata

RUN mkdir -p /xplan-docker-mapserver \
	&& chown 1001 /etc/mapserver

ADD target/xplan-docker-mapserver-*.tgz /
ADD startupWithConfiguredMapserver.sh /xplan-docker-mapserver/

USER 1001

CMD ["/xplan-docker-mapserver/startupWithConfiguredMapserver.sh"]