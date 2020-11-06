FROM xplanbox/buster-tomcat9-jdk8:latest

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-manager-config/"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# install gdal plus jni
RUN apt-get update && apt-get -y install libgdal-java

# add hale-cli
RUN cd / && wget https://github.com/halestudio/hale-cli/releases/download/v3.4.0/hale-cli-3.4.0.zip && unzip hale-cli-3.4.0.zip

# Fix for #96 (java.awt.AWTError: Assistive Technology not found: org.GNOME.Accessibility.AtkWrapper)
ADD accessibility.properties /etc/java-8-openjdk/accessibility.properties

# add xplan-api-manager
ADD target/xplan-api-manager-*.war /root/
RUN cd /root/ && unzip -q xplan-api-manager-*.war -d /usr/local/tomcat/webapps/xplan-api-manager

# run tomcat
CMD ["catalina.sh", "run"]
