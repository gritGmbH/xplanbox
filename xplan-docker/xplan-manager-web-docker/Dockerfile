FROM tomcat:8.0-jre7

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DMANAGER_WEB=/root/xplan-manager-config/"

# add xplan-manager-web
ADD target/xplan-manager-web-*.war /root/
RUN cd /root/ && unzip -q xplan-manager-web-*.war -d /usr/local/tomcat/webapps/xplan-manager

# run tomcat
CMD ["catalina.sh", "run"]