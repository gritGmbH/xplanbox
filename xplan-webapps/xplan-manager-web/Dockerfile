ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION
ARG WEB_CONTEXT=ROOT

# set environment variables
ENV TOMCAT_ADDITIONAL_ARG_APP="-DXPLANBOX_CONFIG=/xplanbox/xplan-manager-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"

# add xplan-manager-web
# TODO: add unpacked
COPY target/xplan-manager-web-*.war /usr/local/tomcat/webapps/xplan-manager-web.war


# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
