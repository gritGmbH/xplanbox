FROM ubuntu:focal

ARG TOMCAT_VERSION="9.0.65"
# set environment variables
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
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz --quiet -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-${TOMCAT_VERSION}/* /usr/local/tomcat/
EXPOSE 8080

# TODO: remove host-manager, manager, docs, examples & ROOT

# install gdal plus jni
RUN apt update && apt -y install libgdal-java

RUN ls -al /usr/local/tomcat/bin/
