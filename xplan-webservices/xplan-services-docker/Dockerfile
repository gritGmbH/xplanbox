ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION

# set environment variables
ENV TOMCAT_ADDITIONAL_ARG_APP="-Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"
ENV LD_LIBRARY_PATH="/usr/lib/jni"

# configure tomcat
ADD web.xml /usr/local/tomcat/conf/web.xml

# add all unpacked webapps (prepared by maven)
COPY maven /usr/local/tomcat/webapps/

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]

