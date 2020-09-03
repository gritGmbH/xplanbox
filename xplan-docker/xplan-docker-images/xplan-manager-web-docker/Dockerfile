FROM xplanbox/buster-tomcat9-jdk8:latest

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV JAVA_OPTS="-Xmx8192m -DXPLANBOX_CONFIG=/root/xplan-manager-config/"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# install gdal plus jni
RUN apt-get update && apt-get -y install libgdal-java

# add xplan-manager-web
ADD target/xplan-manager-web-*.war /root/
RUN cd /root/ && unzip -q xplan-manager-web-*.war -d /usr/local/tomcat/webapps/xplan-manager-web

# add hale-cli
RUN cd / && wget https://github.com/halestudio/hale-cli/releases/download/v3.4.0/hale-cli-3.4.0.zip && unzip hale-cli-3.4.0.zip

# Fix for #96 (java.awt.AWTError: Assistive Technology not found: org.GNOME.Accessibility.AtkWrapper)
ADD accessibility.properties /etc/java-8-openjdk/accessibility.properties

# add xplan-root
ADD target/xplan-root-*-default.war /root/
RUN cd /root/ && unzip -q xplan-root-*-default.war -d /usr/local/tomcat/webapps/ROOT

# add xplan-benutzerhandbuch
ADD target/xplan-benutzerhandbuch-*-html.zip /root/
RUN cd /root/ && unzip -q xplan-benutzerhandbuch-*-html.zip -d /usr/local/tomcat/webapps/ROOT/

# add xplan-betriebshandbuch
ADD target/xplan-betriebshandbuch-*-html.zip /root/
RUN cd /root/ && unzip -q xplan-betriebshandbuch-*-html.zip -d /usr/local/tomcat/webapps/ROOT/

# add CLIs
ADD target/xplan-update-database-*.zip /root/
RUN cd /root/ && unzip -q xplan-update-database-*.zip
ADD target/xplan-manager-cli-*.zip /root/
RUN cd /root/ && unzip -q xplan-manager-cli-*.zip
ADD target/xplan-transform-cli-*.zip /root/
RUN cd /root/ && unzip -q xplan-transform-cli-*.zip

# run tomcat
CMD ["catalina.sh", "run"]
