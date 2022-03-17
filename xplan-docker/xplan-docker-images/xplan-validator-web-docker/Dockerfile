FROM tomcat:9-jdk11

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-validator-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"

# add web.xml
ADD web.xml /usr/local/tomcat/conf/web.xml

# add xplan-validator-web
ADD target/xplan-validator-web-*.war /root/
RUN cd /root/ && unzip -q xplan-validator-web-*.war -d /usr/local/tomcat/webapps/xplan-validator-web

# add xplan-validator-wms
ADD target/xplan-validator-wms-*.war /root/
RUN cd /root/ && unzip -q xplan-validator-wms-*.war -d /usr/local/tomcat/webapps/xplan-validator-wms

# run tomcat
CMD ["catalina.sh", "run"]
