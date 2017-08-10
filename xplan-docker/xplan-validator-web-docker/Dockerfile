FROM tomcat:8.0-jre8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# add xplan-validator-web
ADD target/xplan-validator-web-*.war /root/
RUN cd /root/ && unzip -q xplan-validator-web-*.war -d /usr/local/tomcat/webapps/xplan-validator

# run tomcat
CMD ["catalina.sh", "run"]