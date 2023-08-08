FROM ubuntu:focal

ARG TOMCAT_VERSION="9.0.78"
# set environment variables
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# apt-transport-https (needed to install java 11)
RUN apt update && apt -y install apt-transport-https unzip wget

# install jdk
RUN DEBIAN_FRONTEND=noninteractive apt -y install openjdk-11-jdk

# install tomcat and allow non-root usage
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz --quiet -O /tmp/tomcat.tar.gz \
	&& tar xfz /tmp/tomcat.tar.gz --directory /usr/local \
	&& mv /usr/local/apache-tomcat-${TOMCAT_VERSION} /usr/local/tomcat \
	&& rm /tmp/tomcat.tar.gz \
	&& chmod -R a+rX /usr/local/tomcat \
	&& chmod a+w /usr/local/tomcat/conf /usr/local/tomcat/logs /usr/local/tomcat/webapps /usr/local/tomcat/work /usr/local/tomcat/temp \
	&& rm -rf /usr/local/tomcat/webapps/*

EXPOSE 8080

# install gdal plus jni
RUN apt update && apt -y install libgdal-java
