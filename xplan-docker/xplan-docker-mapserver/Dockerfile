FROM camptocamp/mapserver:8.0

RUN mkdir -p /xplan-docker-mapserver
RUN chown 1001 /etc/mapserver

# create log files
RUN touch /var/log/mapserver.log
RUN chown 1001 /var/log/mapserver.log
RUN touch /var/log/gdal.log
RUN chown 1001 /var/log/gdal.log

ADD target/xplan-docker-mapserver-*.tgz /
ADD startupWithConfiguredMapserver.sh /xplan-docker-mapserver/

USER 1001

CMD ["/xplan-docker-mapserver/startupWithConfiguredMapserver.sh"]