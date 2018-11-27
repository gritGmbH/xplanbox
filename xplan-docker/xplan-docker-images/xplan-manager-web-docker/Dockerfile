FROM tomcat:8.0-jre8

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DMANAGER_WEB=/root/xplan-manager-config/"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# install gdal plus jni
RUN apt-get update && apt-get -y install libgdal-java

# add xplan-manager-web
ADD target/xplan-manager-web-*.war /root/
RUN cd /root/ && unzip -q xplan-manager-web-*.war -d /usr/local/tomcat/webapps/xplan-manager

# add hale-cli
RUN cd / && wget https://github.com/halestudio/hale-cli/releases/download/v3.3.1/hale-cli-3.3.1.zip && unzip hale-cli-3.3.1.zip

# Fix for #96 (java.awt.AWTError: Assistive Technology not found: org.GNOME.Accessibility.AtkWrapper)
ADD accessibility.properties /etc/java-8-openjdk/accessibility.properties

# run tomcat
CMD ["catalina.sh", "run"]