FROM liquibase/liquibase:4.26.0
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

ENV TZ=Europe/Berlin \
  LC_TIME=en_US \
  LC_ALL= \
  XPLAN_JDBC_URL=define-me \
  XPLAN_DB_USER=define-me \
  XPLAN_DB_INIT_USER=define-me \
  XPLAN_DB_INIT_PASSWORD=define-me \
  XPLAN_SERVICES_DEFAULT_CRS_SRID=define-me

# Install PSQL (probably not really needed) 
USER root
RUN apt-get update -y \
  && locale-gen de_DE \
  && apt-get install --no-install-recommends -y postgresql-client \
  && rm -rf /var/lib/apt/lists/*

# Return to liquibase user space
USER liquibase

COPY src/main/resources /liquibase/changelog
ADD docker-entrypoint.sh /xplanbox/

ENTRYPOINT [ "/xplanbox/docker-entrypoint.sh" ]
