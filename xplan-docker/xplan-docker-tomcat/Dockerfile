FROM bitnami/tomcat:9.0

ENV TOMCAT_INSTALL_DEFAULT_WEBAPPS=false
ENV ALLOW_EMPTY_PASSWORD=yes

USER root

# see https://github.com/adoptium/temurin-build/issues/693
RUN apt-get update && apt-get -y install fontconfig

RUN chmod o+w /opt/bitnami/tomcat/bin/setenv.sh \
	&& chmod o+w /opt/bitnami/tomcat/logs/ \
	&& chmod o+w /opt/bitnami/tomcat/conf /opt/bitnami/tomcat/conf/* \
	&& chmod o+w /opt/bitnami/tomcat/work/ \
	&& chmod o+w /opt/bitnami/tomcat/temp \
	&& cat /opt/bitnami/tomcat/conf/server.xml | tr '\n' '|' | sed 's|<Valve [^>]*/>||g' | tr '|' '\n' > /tmp/server.xml && mv /tmp/server.xml /opt/bitnami/tomcat/conf \
	&& sed -i /opt/bitnami/tomcat/conf/logging.properties -e 's/^handlers/handlers = java.util.logging.ConsoleHandler\n# handlers/'


RUN cp /opt/bitnami/scripts/tomcat-env.sh /opt/bitnami/scripts/tomcat-env.original.sh

ADD tomcat-env.sh /opt/bitnami/scripts/tomcat-env.sh

ENV JMX_EXPORTER_DIR=/xplanbox/prometheus

RUN mkdir -p $JMX_EXPORTER_DIR
COPY target/deps/jmx_prometheus_javaagent* $JMX_EXPORTER_DIR
ADD jmx-exporter.config.yaml $JMX_EXPORTER_DIR

ENV TOMCAT_ADDITIONAL_ARG_JMX_EXPORTER='-javaagent:$JMX_EXPORTER_DIR/jmx_prometheus_javaagent-0.19.0.jar=12345:$JMX_EXPORTER_DIR/jmx-exporter.config.yaml'

USER 1001
