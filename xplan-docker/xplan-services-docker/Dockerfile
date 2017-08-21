FROM tomcat:8.0-jre8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m"

# add xplan-wms
ADD target/xplan-wms-*.war /root/
RUN cd /root/ && unzip -q xplan-wms-*.war -d /usr/local/tomcat/webapps/xplan-wms

# add xplansyn-wfs
ADD target/xplansyn-wfs-*.war /root/
RUN cd /root/ && unzip -q xplansyn-wfs-*.war -d /usr/local/tomcat/webapps/xplansyn-wfs

# add xplan-wfs
ADD target/xplan-wfs-*.war /root/
RUN cd /root/ && unzip -q xplan-wfs-*.war -d /usr/local/tomcat/webapps/xplan-wfs

# run tomcat
CMD ["catalina.sh", "run"]