FROM python:3.11-slim

ARG MAPPROXY_VERSION=1.16

ENV	TZ=Europe/Berlin \
    PATH=/srv/mapproxy/venv/bin:$PATH \
    XPLAN_MAPPROXY_PATH=xplan-mapproxy \
    XPLAN_MAPPROXY_MINPROC=2 \
    XPLAN_MAPPROXY_MAXPROC=4 \
    XPLAN_MAPPROXY_THREADS=10 \
    XPLAN_MAPPROXY_ROOT_LOGLEVEL=ERROR \
    XPLAN_MAPPROXY_SOURCES_LOGLEVEL=INFO

RUN apt update && apt -y install --no-install-recommends \
    uwsgi \
    uwsgi-plugin-python3 \
    python3-venv \
    libgdal-dev \
    libgeos-dev && \
    apt-get -y --purge autoremove && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* && \
    python -m venv --system-site-packages /srv/mapproxy/venv && \
    pip install mapproxy==$MAPPROXY_VERSION pyproj pillow pyyaml boto3 shapely && \
    pip cache purge && \
    addgroup --gid 1001 mapproxy && \
    adduser --uid 1001 --shell /bin/false --gid 1001 --no-create-home --disabled-login mapproxy && \
    chown -R 1001:1001 /srv/mapproxy

COPY --chown=1001:1001 ./uwsgi.ini /srv/mapproxy/uwsgi.ini
COPY --chown=1001:1001 ./app.py /srv/mapproxy/app.py
COPY --chown=1001:1001 ./log.ini /srv/mapproxy/log.ini

ADD target/xplan-docker-mapproxy-*.tgz /

WORKDIR /srv/mapproxy
USER 1001

EXPOSE 8080

CMD ["docker-entrypoint.sh"]