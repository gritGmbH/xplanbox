FROM xplanbox/focal-jdk11-tomcat9-gdal30:latest

MAINTAINER Dirk Stenger <stenger@lat-lon.de>
MAINTAINER Lyn Goltz <goltz@lat-lon.de>

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-manager-config/ -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl"
ENV JAVA_OPTS="-Xmx8192m"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# add hale-cli which is using Java 8 JDK
RUN cd / && wget https://github.com/halestudio/hale-cli/releases/download/v3.4.0/hale-cli-3.4.0.zip && unzip hale-cli-3.4.0.zip
RUN apt update && apt -y install openjdk-8-jdk
RUN sed -i '2iJAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64' /hale/bin/hale

# Fix for #96 (java.awt.AWTError: Assistive Technology not found: org.GNOME.Accessibility.AtkWrapper)
ADD accessibility.properties /etc/java-8-openjdk/accessibility.properties

# add xplan-api-manager
ADD target/xplan-api-manager-*.war /root/
RUN cd /root/ && unzip -q xplan-api-manager-*.war -d /usr/local/tomcat/webapps/xplan-api-manager

# run tomcat
CMD ["catalina.sh", "run"]
