FROM bitnami/tomcat:9.0.74

ENV TOMCAT_INSTALL_DEFAULT_WEBAPPS=false
ENV ALLOW_EMPTY_PASSWORD=yes

USER root

# see https://github.com/adoptium/temurin-build/issues/693
RUN apt-get update && apt-get -y install fontconfig

RUN chmod o+w /opt/bitnami/tomcat/bin/setenv.sh \
	&& chmod o+w /opt/bitnami/tomcat/logs/ \
	&& chmod o+w /opt/bitnami/tomcat/conf /opt/bitnami/tomcat/conf/* \
	&& chmod o+w /opt/bitnami/tomcat/work/ \
	&& chmod o+w /opt/bitnami/tomcat/temp

USER 1001
