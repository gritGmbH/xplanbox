FROM ubuntu:focal

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-manager-config/"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# update
RUN apt update

# install utils
RUN apt -y install unzip wget

# install jdk
RUN DEBIAN_FRONTEND=noninteractive apt -y install openjdk-8-jdk

# install tomcat
RUN mkdir /usr/local/tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.45/bin/apache-tomcat-9.0.45.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-9.0.45/* /usr/local/tomcat/
EXPOSE 8080

# install gdal plus jni
RUN apt update && apt -y install libgdal-java
