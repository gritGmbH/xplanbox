FROM tomcat:8.0-jre8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# install java
RUN apt-get -y remove openjdk-8-jre-headless
RUN apt-get update && apt-get -y install openjdk-8-jre

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml

# add xplan-inspireplu
ADD target/xplan-inspireplu-*.war /root/
RUN cd /root/ && unzip -q xplan-inspireplu-*.war -d /usr/local/tomcat/webapps/xplan-inspireplu

# run tomcat
CMD ["catalina.sh", "run"]