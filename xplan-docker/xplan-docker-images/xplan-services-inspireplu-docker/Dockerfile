FROM tomcat:9-jdk8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-Xmx8192m -Djava.security.manager -Djava.security.policy=/usr/local/tomcat/conf/catalina.policy"

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD web.xml /usr/local/tomcat/conf/web.xml
ADD catalina.policy /usr/local/tomcat/conf/catalina.policy

# add xplan-inspireplu
ADD target/xplan-inspireplu-*.war /root/
RUN cd /root/ && unzip -q xplan-inspireplu-*.war -d /usr/local/tomcat/webapps/xplan-inspireplu

# run tomcat
CMD ["catalina.sh", "run"]