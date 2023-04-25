ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-manager-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"

# add xplan-api-manager
ADD target/xplan-api-manager-*.war /root/
RUN cd /root/ && unzip -q xplan-api-manager-*.war -d /usr/local/tomcat/webapps/xplan-api-manager

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
