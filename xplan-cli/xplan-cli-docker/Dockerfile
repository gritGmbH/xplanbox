ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-volume-init:$XPLANBOX_VERSION as volumeInitBuilder 


FROM eclipse-temurin:11.0.21_9-jdk-alpine
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG XPLANBOX_VERSION=latest

# see https://github.com/opencontainers/image-spec/blob/main/annotations.md#pre-defined-annotation-keys
LABEL "org.opencontainers.image.created"="$BUILD_DATE" \
	"org.opencontainers.image.description"="ozgxplanung xPlanBox component" \
	"org.opencontainers.image.licenses"="GNU Affero General Public License & others" \
	"org.opencontainers.image.ref.name"="xplan-cli-docker" \
	"org.opencontainers.image.revision"="$GIT_REVISION" \
	"org.opencontainers.image.title"="xplan-cli-docker" \
	"org.opencontainers.image.url"="https://gitlab.opencode.de/diplanung/ozgxplanung" \
	"org.opencontainers.image.vendor"="lat/lon GmbH" \
	"org.opencontainers.image.version"="$XPLANBOX_VERSION"

ENV TZ=Europe/Berlin
RUN apk --no-cache add bash

COPY target/unpacked-deps/xplan-manager-cli-* /xplanbox/xplan-manager-cli/
COPY target/unpacked-deps/xplan-transform-cli-* /xplanbox/xplan-transform-cli/
COPY target/unpacked-deps/xplan-update-data-* /xplanbox/xplan-update-data/

RUN mkdir /xplanbox/xplan-manager-cli/logs \
	&& mkdir /xplanbox/xplan-transform-cli/logs \
	&& mkdir /xplanbox/xplan-update-data/logs \
	&& chmod a+xw /xplanbox/xplan-manager-cli/logs /xplanbox/xplan-transform-cli/logs /xplanbox/xplan-update-data/logs

COPY --from=volumeInitBuilder /xplan-volume-init/ ./xplan-volume-init
COPY resynthesize.sh /xplanbox/

ENTRYPOINT ["/bin/bash"]
CMD ["/xplanbox/resynthesize.sh"]
