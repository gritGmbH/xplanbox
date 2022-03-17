FROM tomcat:9-jdk11

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD web.xml /usr/local/tomcat/conf/web.xml

# add xplan-inspireplu
ADD target/xplan-inspireplu-*.war /root/
RUN cd /root/ && unzip -q xplan-inspireplu-*.war -d /usr/local/tomcat/webapps/xplan-inspireplu

# run tomcat
CMD ["catalina.sh", "run"]