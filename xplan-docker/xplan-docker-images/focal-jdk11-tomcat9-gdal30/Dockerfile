FROM ubuntu:focal

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

ARG TOMCAT_VERSION="9.0.56"
# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-manager-config/"
ENV JAVA_OPTS="-Xmx8192m"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# update and install apt-transport-https (needed to install java 11)
RUN apt update
RUN apt -y install apt-transport-https
RUN apt update

# install utils
RUN apt -y install unzip wget

# install jdk
RUN DEBIAN_FRONTEND=noninteractive apt -y install openjdk-11-jdk

# install tomcat
RUN mkdir /usr/local/tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-${TOMCAT_VERSION}/* /usr/local/tomcat/
EXPOSE 8080

# install gdal plus jni
RUN apt update && apt -y install libgdal-java
