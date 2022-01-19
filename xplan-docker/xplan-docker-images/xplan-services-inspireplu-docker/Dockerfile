FROM tomcat:9-jdk11

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-Xmx8192m -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl"

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD web.xml /usr/local/tomcat/conf/web.xml
#ADD catalina.policy /usr/local/tomcat/conf/catalina.policy

# add xplan-inspireplu
ADD target/xplan-inspireplu-*.war /root/
RUN cd /root/ && unzip -q xplan-inspireplu-*.war -d /usr/local/tomcat/webapps/xplan-inspireplu

# run tomcat
CMD ["catalina.sh", "run"]