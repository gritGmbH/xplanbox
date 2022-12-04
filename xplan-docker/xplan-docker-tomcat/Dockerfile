FROM tomcat:9-jdk11

RUN apt-get update && apt-get install -y unzip

# set environment variables
ENV CATALINA_OPTS="-DXPLANBOX_CONFIG=/root/xplan-validator-config/ -Dlog4j2.configurationFile=classpath:/log4j2.yaml -Djavax.xml.transform.TransformerFactory=net.sf.saxon.TransformerFactoryImpl -Djts.overlay=ng -Duser.timezone=Europe/Berlin"

# run tomcat
CMD ["catalina.sh", "run"]
