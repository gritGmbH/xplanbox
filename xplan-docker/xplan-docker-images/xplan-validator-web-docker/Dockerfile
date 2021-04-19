FROM tomcat:9-jdk8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-validator-config/"

# add xplan-validator-web
ADD target/xplan-validator-web-*.war /root/
RUN cd /root/ && unzip -q xplan-validator-web-*.war -d /usr/local/tomcat/webapps/xplan-validator-web

# add xplan-validator-wms
ADD target/xplan-validator-wms-*.war /root/
RUN cd /root/ && unzip -q xplan-validator-wms-*.war -d /usr/local/tomcat/webapps/xplan-validator-wms

# run tomcat
CMD ["catalina.sh", "run"]
