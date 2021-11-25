FROM tomcat:9-jdk11

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-validator-config/ -Djava.security.manager -Djava.security.policy=/usr/local/tomcat/conf/catalina.policy"

# configure tomcat
ADD catalina.policy /usr/local/tomcat/conf/catalina.policy

# add xplan-api-validator
ADD target/xplan-api-validator-*.war /root/
RUN cd /root/ && unzip -q xplan-api-validator-*.war -d /usr/local/tomcat/webapps/xplan-api-validator

# run tomcat
CMD ["catalina.sh", "run"]
