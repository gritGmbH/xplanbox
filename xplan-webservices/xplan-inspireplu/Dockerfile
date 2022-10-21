FROM tomcat:9-jdk11
ARG WEB_CONTEXT=xplan-inspireplu

# set environment variables
ENV CATALINA_OPTS="-Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Duser.timezone=Europe/Berlin"

# configure tomcat
ADD tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
ADD web.xml /usr/local/tomcat/conf/web.xml

RUN mkdir /usr/local/tomcat/webapps/$WEB_CONTEXT
ADD target/exploded-webapp /usr/local/tomcat/webapps/$WEB_CONTEXT

# run tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
