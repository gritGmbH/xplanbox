FROM tomcat:9-jdk8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-validator-config/"

# add xplan-api-validator
ADD target/xplan-api-validator-*.war /root/
RUN cd /root/ && unzip -q xplan-api-validator-*.war -d /usr/local/tomcat/webapps/xplan-api-validator

# run tomcat
CMD ["catalina.sh", "run"]
