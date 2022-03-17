FROM tomcat:9-jdk11

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-validator-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"

# add xplan-api-validator
ADD target/xplan-api-validator-*.war /root/
RUN cd /root/ && unzip -q xplan-api-validator-*.war -d /usr/local/tomcat/webapps/xplan-api-validator

# run tomcat
CMD ["catalina.sh", "run"]
