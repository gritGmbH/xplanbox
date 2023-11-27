FROM python:3.10.13-slim

ARG MAPPROXY_VERSION=1.16.0
ARG BUILD_DATE=?
ARG DOCKER_IMAGE_NAME=?
ARG GIT_REVISION=?
ARG XPLANBOX_VERSION=latest

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

ENV	TZ=Europe/Berlin \
    PATH=/srv/mapproxy/venv/bin:$PATH \
    XPLAN_MAPPROXY_PATH=xplan-mapproxy \
    XPLAN_MAPPROXY_MINPROC=2 \
    XPLAN_MAPPROXY_MAXPROC=4 \
    XPLAN_MAPPROXY_RESEED_CONCURRENCY=4 \
    XPLAN_MAPPROXY_ROOT_LOGLEVEL=ERROR \
    XPLAN_MAPPROXY_SOURCES_LOGLEVEL=INFO \
    XPLAN_MAPPROXY_THREADS=10 

RUN apt update && apt -y install --no-install-recommends \
    gcc \
    python3-venv \
    libgdal-dev \
    libgeos-dev \
    gettext-base && \
    apt-get -y --purge autoremove && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    python -m venv --system-site-packages /srv/mapproxy/venv && \
    pip install mapproxy==$MAPPROXY_VERSION uwsgi pyproj pillow pyyaml boto3 shapely==1.7.1 && \
    pip cache purge && \
    addgroup --gid 1001 mapproxy && \
    adduser --uid 1001 --shell /bin/false --gid 1001 --no-create-home --disabled-login mapproxy && \
    chown -R 1001:1001 /srv/mapproxy

COPY --chown=1001:1001 ./uwsgi.ini /srv/mapproxy/uwsgi.ini
COPY --chown=1001:1001 ./app.py /srv/mapproxy/app.py
COPY --chown=1001:1001 ./log.ini /srv/mapproxy/log.ini

ADD target/xplan-docker-mapproxy-*.tgz /
ADD docker-entrypoint.sh /docker-entrypoint.sh

WORKDIR /srv/mapproxy
USER 1001

EXPOSE 8080

ENTRYPOINT [ "/docker-entrypoint.sh"]
CMD ["webserver"]