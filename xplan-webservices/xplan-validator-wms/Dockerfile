FROM tomcat:9-jdk11
ARG WEB_CONTEXT=xplan-validator-wms

RUN apt-get update

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-validator-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"


ADD target/xplan-validator-wms-*.war /tmp/

RUN mkdir /usr/local/tomcat/webapps/$WEB_CONTEXT \
	&& cd /usr/local/tomcat/webapps/$WEB_CONTEXT \
	&& jar xf /tmp/xplan-validator-wms-*.war \
	&& rm /tmp/xplan-validator-wms-*.war
