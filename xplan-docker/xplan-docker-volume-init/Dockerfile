FROM alpine:3.18.4
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

RUN apk --no-cache add bash curl tzdata unzip
ENV TZ=Europe/Berlin
# TODO: update

RUN mkdir -p /xplan-volume-init
ADD target/xplan-docker-volume-init-*.tgz /xplan-volume-init/
ADD setupVolumes.sh /xplan-volume-init/
ADD synthesizer /xplan-volume-init/synthesizer/

COPY src/main/resources/xplan-inspireplu-workspaces/webapps.properties /xplan-volume-init/xplan-inspirepluvalidator-workspaces/
COPY src/main/resources/xplan-validator-workspaces/webapps.properties /xplan-volume-init/xplan-validator-workspaces/
COPY src/main/resources/xplan-workspaces/webapps.properties /xplan-volume-init/xplan-workspaces/

CMD ["/xplan-volume-init/setupVolumes.sh"]
