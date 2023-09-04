FROM camptocamp/mapserver:8.0

RUN mkdir -p /xplan-docker-mapserver
RUN chown 1001 /etc/mapserver

ADD target/xplan-docker-mapserver-*.tgz /
ADD startupWithConfiguredMapserver.sh /xplan-docker-mapserver/

USER 1001

CMD ["/xplan-docker-mapserver/startupWithConfiguredMapserver.sh"]