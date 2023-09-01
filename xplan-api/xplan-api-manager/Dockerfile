ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-tomcat-gdal:$XPLANBOX_VERSION

# set environment variables
ENV TOMCAT_ADDITIONAL_ARG_APP="-DXPLANBOX_CONFIG=/xplanbox/xplan-manager-config/ -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"

# add xplan-api-manager
ADD target/xplan-api-manager-*.war /tmp/
RUN cd /tmp/ && unzip -q xplan-api-manager-*.war -d /usr/local/tomcat/webapps/xplan-api-manager \
	&& rm /tmp/*.war

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
