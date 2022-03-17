FROM xplanbox/focal-jdk11-tomcat9-gdal30:latest

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD web.xml /usr/local/tomcat/conf/web.xml

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
